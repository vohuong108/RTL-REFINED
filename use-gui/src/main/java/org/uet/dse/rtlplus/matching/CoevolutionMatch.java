package org.uet.dse.rtlplus.matching;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.tzi.use.parser.shell.ShellCommandCompiler;
import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.ocl.expr.VarDecl;
import org.tzi.use.uml.ocl.type.TupleType;
import org.tzi.use.uml.ocl.value.ObjectValue;
import org.tzi.use.uml.ocl.value.TupleValue;
import org.tzi.use.uml.ocl.value.TupleValue.Part;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemException;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.uml.sys.soil.MStatement;
import org.tzi.use.util.soil.VariableEnvironment;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.mm.MTggRule;

public class CoevolutionMatch extends Match {

	public CoevolutionMatch(MTggRule rule, MOperation operation, Map<String, MObject> objectList, boolean sync) {
		super(rule, operation, objectList, sync);
	}

	@Override
	public boolean run(MSystemState systemState, PrintWriter logWriter) {
		List<String> commands = rule.getSrcRule().genLetCommandsLeft("matchSL");
		// Create new source objects
		commands.addAll(rule.getSrcRule().genCreationCommands("matchSL", systemState));
		// Set attributes for source objects
		commands.addAll(rule.getSrcRule().genSetCommands());
		// Create new target objects
		commands.addAll(rule.getTrgRule().genCreationCommands("matchTL", systemState));
		// Set attributes for target objects
		commands.addAll(rule.getTrgRule().genSetCommands());
		// Create new correlation objects
		commands.addAll(rule.getCorrRule().genCreationCommands("matchCL", systemState));
		// Update attributes
		commands.addAll(rule.getCorrRule().genAttributeCommands(TransformationType.COEVOLUTION));
		// Variable assignments
		VariableEnvironment varEnv = systemState.system().getVariableEnvironment();
		for (VarDecl varDecl : operation.paramList()) {
			TupleType type = (TupleType) varDecl.type();
			List<Part> parts = new ArrayList<>();
			for (String key : type.getParts().keySet()) {
				parts.add(new Part(0, key, new ObjectValue(objectList.get(key).cls(), objectList.get(key))));
			}
			TupleValue tuple = new TupleValue(type, parts);
			varEnv.assign(varDecl.name(), tuple);
		}
		// Openter and opexit
		int count = 0;
		String openter = "openter rc " + operation.name() + "("
				+ operation.paramNames().stream().collect(Collectors.joining(", ")) + ")";
		commands.add(0, openter);
		// Execute commands
		for (String cmd : commands) {
			MStatement statement = ShellCommandCompiler.compileShellCommand(systemState.system().model(), systemState,
					systemState.system().getVariableEnvironment(), cmd, "<input>", logWriter, false);
			if (statement == null) {
				logWriter.println("Cannot parse command: " + cmd);
				doOpExit(systemState, logWriter, count);
			} else try {
				systemState.system().execute(statement);
				count++;
			} catch (MSystemException e) {
				logWriter.println(e.getMessage());
				// System.out.println(varEnv);
				for (int i=0; i<count; i++) {
					try {
						systemState.system().undoLastStatement();
					} catch (MSystemException e1) {
						doOpExit(systemState, logWriter, count);
					}
				}
				varEnv.clear();
				return false;
			}
		}
		// Opexit
		doOpExit(systemState, logWriter, count);
		varEnv.clear();
		return true;
	}

	private boolean doOpExit(MSystemState systemState, PrintWriter logWriter, int count) {
		try {
			systemState.system().execute(ShellCommandCompiler.compileShellCommand(systemState.system().model(),
					systemState, systemState.system().getVariableEnvironment(), "opexit", "<input>", logWriter, false));
			return true;
		} catch (MSystemException e) {
			if (e.getMessage().contains("postcondition false")) {
				for (int i = 0; i < count; i++) {
					try {
						systemState.system().undoLastStatement();
					} catch (MSystemException e1) {
						logWriter.println(e1.getMessage());
					}
				}
			} else
				logWriter.println(e.getMessage());
			return false;
		}
	}

	@Override
	public int getNewObjectsNum() {
		return rule.getSrcRule().getNewObjects().size() + rule.getTrgRule().getNewObjects().size();
	}

	@Override
	public int getNewLinksNum() {
		return rule.getSrcRule().getNewLinks().size() + rule.getTrgRule().getNewLinks().size();
	}

	@Override
	public int getNewCorrsNum() {
		return rule.getCorrRule().getNewObjects().size();
	}
}

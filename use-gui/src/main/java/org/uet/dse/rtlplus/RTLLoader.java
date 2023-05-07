package org.uet.dse.rtlplus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.main.Session;
import org.tzi.use.parser.use.USECompiler;
import org.tzi.use.uml.mm.MModel;
import org.tzi.use.uml.mm.ModelFactory;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.uml.sys.MSystemException;
import org.uet.dse.rtlplus.mm.MRuleCollection;
import org.uet.dse.rtlplus.parser.RTLCompiler;
import org.uet.dse.rtlplus.parser.RTLKeyword;

public class RTLLoader {
	private Session session;
	private File srcModelFile;
	private File trgModelFile;
	private String tggName;
	private PrintWriter logWriter;
	
	private MModel model1;
	private MModel model2;
	private MModel mergedModel;
	private MModel diagramModel;
	private MRuleCollection ruleCollection;
	private String modelName;
	private StringBuilder useContent = new StringBuilder();
	
	public RTLLoader(Session session, File srcModelFile, File trgModelFile, String tggName, PrintWriter logWriter) {
		super();
		this.session = session;
		this.srcModelFile = srcModelFile;
		this.trgModelFile = trgModelFile;
		this.tggName = tggName;
		this.logWriter = logWriter;
	}
	
	public boolean run() {
		// Parse metamodel
		parseModels();
		// Parse TGG rule
		parseTGGRule();
		// gen USE file
		genUSEContent();
		writeUSEFile();

		/* Load model and RTL rules */
		if (mergedModel != null) {
			/* Load USE model */
			MModel newModel = null;
			MSystem system = null;
			try {
				newModel = USECompiler.compileSpecification(useContent.toString(), modelName, logWriter,
						new ModelFactory());
				logWriter.println("Load model " + modelName + " ...");
				if (newModel != null) {
					logWriter.println(newModel.getStats());
					// create system
					system = new MSystem(newModel);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// set new system (may be null if compilation failed)
			final MSystem system2 = system;
			session.setSystem(system2);
			try {
				UseSystemApi.create(session).createObject("RuleCollection", "rc");
			} catch (UseApiException e) {
				e.printStackTrace();
				return false;
			}
			Main.setRTLRule(ruleCollection);
			logWriter.println("Compilation successful");
			return true;
		} else {
			logWriter.println("Compilation failed");
			return false;
		}
	}
	
	private void genUSEContent() {
		logWriter.println("Compile USE model specification...");
		useContent.append("\n\n---------- CorrPart ----------\n");
		useContent.append(ruleCollection.getContext().generateCorrelations());
		useContent.append(ruleCollection.genCorrInvs());
		useContent.append(genRuleCollection());
	}
	

	private StringBuilder genRuleCollection() {
		StringBuilder ops = new StringBuilder("---------- RuleCollection ----------\nclass RuleCollection\n");
		StringBuilder cons = new StringBuilder("\n\n---------- Transformation constraints ----------\nconstraints");
		ops.append(RTLKeyword.startOperation);
		ops.append("\n---------- Forward transformations ----------\n");
		ruleCollection.genForwardTransformation(ops, cons);
		ops.append("\n---------- Backward transformations ----------\n");
		ruleCollection.genBackwardTransformation(ops, cons);
		ops.append("\n---------- Integration transformations ----------\n");
		ruleCollection.genIntegration(ops, cons);
		ops.append("\n---------- Co-evolution transformations ----------\n");
		ruleCollection.genCoevolution(ops, cons);
		ops.append(RTLKeyword.endClass).append('\n');
		ops.append(cons);
		return ops;
	}

	/**
	 * Join source and target model
	 * 
	 * @return A string containing the two models
	 */
	private String unionModel() {
		String result = "";
		FileInputStream stream;
		try {
			String mm1 = "", mm2 = "";
			stream = new FileInputStream(srcModelFile);
			model1 = USECompiler.compileSpecification(stream, srcModelFile.getName(), logWriter, new ModelFactory());
			byte[] bytes = Files.readAllBytes(srcModelFile.toPath());
			mm1 = new String(bytes, "UTF-8");
			mm1 = mm1.substring(mm1.indexOf('\n', mm1.indexOf(RTLKeyword.model)));

			
			stream = new FileInputStream(trgModelFile);
			model2 = USECompiler.compileSpecification(stream, trgModelFile.getName(), logWriter, new ModelFactory());
			bytes = Files.readAllBytes(trgModelFile.toPath());
			mm2 = new String(bytes, "UTF-8");
			mm2 = mm2.substring(mm2.indexOf('\n', mm2.indexOf(RTLKeyword.model)));
			modelName = model1.name() + "2" + model2.name();
			// All model
			result = RTLKeyword.model + " " + modelName + "\n" + mm1 + "\n" + mm2;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Parse source and target model
	 */
	private void parseModels() {
		logWriter.println("Loading source and target metamodel...");
		String model = unionModel();
		useContent.append(model);
		mergedModel = USECompiler.compileSpecification(model, modelName, logWriter, new ModelFactory());
		diagramModel = USECompiler.compileSpecification(model.replace("abstract class ", "class "), modelName, logWriter, new ModelFactory());
	}

	/**
	 * Parse TGG rule from .tgg file
	 */
	private void parseTGGRule() {
		logWriter.println("Compile TGG rules...");
		try {
			ruleCollection = RTLCompiler.compileSpecification(tggName, logWriter, mergedModel, diagramModel);
			ruleCollection.setSourceModel(model1);
			ruleCollection.setTargetModel(model2);
		} catch (MSystemException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write USE specification for models and transformations
	 */
	private void writeUSEFile() {
		File f = new File(tggName);
		String fTGGsRule = modelName + ".use";
		String path = f.getPath();
		String fullPath = path.substring(0, path.length() - f.getName().length());
		logWriter.println("Writing USE model specification to file: " + Paths.get(fullPath, fTGGsRule).toString());
		try {
			PrintWriter out = new PrintWriter(new FileWriter(Paths.get(fullPath, fTGGsRule).toString()));
			out.print(useContent);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}

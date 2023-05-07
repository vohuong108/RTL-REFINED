package org.uet.dse.rtlplus.mm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.parser.ast.tgg.AstInvariantCondition;

public class MCorrRule extends MRule {
	public MCorrRule(MPattern left, MPattern right) {
		super(left, right);
	}
	
	public MPattern getLhs() {
		return lhs;
	}

	public MPattern getRhs() {
		return rhs;
	}
	
	public List<String> genAttributeCommands(TransformationType type){
		List<String> commands = new ArrayList<>();
		for (MObject obj : rhs.getObjectList()) {
			List<AstInvariantCondition> invs = rhs.getInvariantList().get(obj.cls().name());
			if (invs != null) {
				for (AstInvariantCondition inv : invs) {
					commands.add("let self = " + obj.name());
					switch (type) {
					case FORWARD:
					case COEVOLUTION:
						if (inv.getForwardImpl() == null)
							commands.add(inv.getCondition().replace("=", ":="));
						else
							commands.addAll(Arrays.asList(inv.getForwardImpl().split(";"))); 
							
						break;
					case BACKWARD:
						if (inv.getBackwardImpl() == null) {
							String[] parts = inv.getCondition().split("=");
							if (parts.length == 2) {
								String newCommand = parts[1] + ":=" + parts[0];
								commands.add(newCommand);
							}
						} else
							commands.addAll(Arrays.asList(inv.getBackwardImpl().split(";")));
						break;
					default:
						break;
					}
				}
			}
		}
		return commands;
	}
}

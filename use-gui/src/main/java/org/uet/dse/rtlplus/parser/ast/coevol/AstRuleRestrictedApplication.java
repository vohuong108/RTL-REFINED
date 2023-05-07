package org.uet.dse.rtlplus.parser.ast.coevol;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.uml.ocl.value.BooleanValue;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MSystemState;

public class AstRuleRestrictedApplication extends AstRuleApplication {
	
	private List<AstRuleApplication> ruleApplications = new ArrayList<>();
	private String condition;

	public void setCondition(Object object) {
		String str = object.toString();
		condition = str.substring(1, str.length() - 1);
	}

	public void addRuleApplication(AstRuleApplication ruleApp) {
		ruleApplications.add(ruleApp);
		
	}

	@Override
	public boolean run(MSystemState state, PrintWriter logWriter) {
		UseSystemApi api = UseSystemApi.create(state.system(), false);
		try {
			Value res = api.evaluate(condition);
			if (res.isBoolean() && ((BooleanValue) res).isTrue()) {
				for (AstRuleApplication app : ruleApplications) {
					boolean result = app.run(state, logWriter);
					if (! result)
						return result;
				}
				return true;
			}
		} catch (UseApiException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

}

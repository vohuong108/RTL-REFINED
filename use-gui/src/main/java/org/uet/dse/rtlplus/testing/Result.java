package org.uet.dse.rtlplus.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.sync.OperationEnterEvent;

public class Result {

	private MSystemState state;
	private List<Value> termSolution;
	private List<Value> otherTermSolution;
	private List<String> termEvalLogs;
	private List<String> otherTermEvalLogs;
	private List<OperationEnterEvent> transformations;
	private ValidationResult result;

	public Result(MSystemState mSystemState, LinkedHashMap<ClassifyingTerm, Value> map, List<Value> list, List<String> list2, List<String> list3, List<OperationEnterEvent> list4, List<Mapping> mappings) {
		state = mSystemState;
		termSolution = new ArrayList<>(map.values());
		otherTermSolution = list;
		termEvalLogs = list2;
		otherTermEvalLogs = list3;
		transformations = list4;
		result = new ValidationResult(termSolution, otherTermSolution, mappings);
	}

	public MSystemState getState() {
		return state;
	}

	public List<Value> getTermSolution() {
		return termSolution;
	}

	public List<Value> getOtherTermSolution() {
		return otherTermSolution;
	}

	public String getTermSolutionString() {
		return termSolution.stream().map(it -> it.toString()).collect(Collectors.joining(", ", "[", "]"));
	}

	public String getOtherTermSolutionString() {
		return otherTermSolution.stream().map(it -> it.toString()).collect(Collectors.joining(", ", "[", "]"));
	}

	public List<String> getTermEvalLogs() {
		return termEvalLogs;
	}

	public List<String> getOtherTermEvalLogs() {
		return otherTermEvalLogs;
	}
	
	public ValidationResult getResult() {
		return result;
	}

	public List<OperationEnterEvent> getTransformations() {
		return transformations;
	}

	public static class ValidationResult {
		public static enum ResultType { 
			PASS, FAIL, NA;

			@Override
			public String toString() {
				switch (this) {
				case PASS:
					return "Pass";
				case FAIL:
					return "Fail";
				default:
					return "N/A";
				}
			}
		}
		
		private HashMap<Mapping, Boolean> matchedMappings = new LinkedHashMap<>();
		private ResultType finalResult = ResultType.PASS;

		public ValidationResult(List<Value> leftVals, List<Value> rightVals, List<Mapping> mappings) {
			for (Mapping m : mappings) {
				if (m.matchLeft(leftVals)) {
					boolean match = m.matchRight(rightVals);
					if (!match)
						finalResult = ResultType.FAIL;
					matchedMappings.put(m, match);
				}
			}
			if (matchedMappings.isEmpty())
				finalResult = ResultType.NA;
		}

		public HashMap<Mapping, Boolean> getMatchedMappings() {
			return matchedMappings;
		}

		public ResultType getFinalResult() {
			return finalResult;
		}
	}
}

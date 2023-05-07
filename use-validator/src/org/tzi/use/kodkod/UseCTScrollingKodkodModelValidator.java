package org.tzi.use.kodkod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.tzi.kodkod.helper.LogMessages;
import org.tzi.kodkod.model.config.impl.ModelConfigurator;
import org.tzi.kodkod.model.type.TypeAtoms;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.use.kodkod.transform.TransformationException;
import org.tzi.use.main.Session;
import org.tzi.use.uml.ocl.expr.Evaluator;
import org.tzi.use.uml.ocl.expr.Expression;
import org.tzi.use.uml.ocl.type.Type.VoidHandling;
import org.tzi.use.uml.ocl.value.BooleanValue;
import org.tzi.use.uml.ocl.value.IntegerValue;
import org.tzi.use.uml.ocl.value.UndefinedValue;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.util.StringUtil;

import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.IntExpression;
import kodkod.ast.Node;
import kodkod.ast.Relation;
import kodkod.instance.TupleSet;

/**
 * Class for the model validation with scrolling functionality using classifier terms.
 *
 * @author Frank Hilken
 */
public class UseCTScrollingKodkodModelValidator extends UseScrollingKodkodModelValidator {

	protected static class ClassifyingTerm {
		private final String name;
		private final Expression oclExpr;
		private final Node kodkodExpr;
		
		public ClassifyingTerm(String name, Expression oclExpr, Node kodkodExpr) {
			this.name = name;
			this.oclExpr = oclExpr;
			this.kodkodExpr = kodkodExpr;
		}

		public String name() {
			return name;
		}
		
		public Expression oclExpression() {
			return oclExpr;
		}

		public Node kodkodExpression() {
			return kodkodExpr;
		}
	}

	protected List<ClassifyingTerm> classifyingTerms = new ArrayList<ClassifyingTerm>();
	protected List<Map<ClassifyingTerm, Value>> termSolutions = new ArrayList<>();

	public UseCTScrollingKodkodModelValidator(Session session) {
		super(session);
	}
	
	@Override
	protected void handleSolution() {
		boolean errors = createObjectDiagram(solution.instance().relationTuples());
		if (!errors) {
			readSolutionTerms(session.system().state());
			
			solutions.add(solution.instance().relationTuples());
			LOG.info(LogMessages.pagingNext);
			previousLog();
		} else {
			session.reset();
			newSolution(solution.instance().relationTuples());
		}
	}

	/**
	 * Constructs a Kodkod {@link Formula} checking the equality of the given
	 * expression and value.
	 */
	private Formula encodeSolutionValue(kodkod.ast.Node exp, Value value) {
		if(exp instanceof kodkod.ast.Expression){
			if(value instanceof IntegerValue){
				return ((kodkod.ast.Expression) exp).eq(IntConstant.constant(((IntegerValue) value).value()).toExpression());
			}
			else if(value instanceof BooleanValue) {
				Map<String, kodkod.ast.Expression> typeLiterals = model.typeFactory().booleanType().typeLiterals();
				if(((BooleanValue) value).value()){
					return ((kodkod.ast.Expression) exp).eq(typeLiterals.get(TypeConstants.BOOLEAN_TRUE));
				} else {
					return ((kodkod.ast.Expression) exp).eq(typeLiterals.get(TypeConstants.BOOLEAN_FALSE));
				}
			}
			else if(value instanceof UndefinedValue){
				TypeAtoms typeLiteral;
				if(value.type().isKindOfCollection(VoidHandling.INCLUDE_VOID)){
					typeLiteral = model.typeFactory().undefinedSetType();
				} else {
					typeLiteral = model.typeFactory().undefinedType();
				}
				return ((kodkod.ast.Expression) exp).eq(typeLiteral.expression());
			}
			else {
				throw new TransformationException("Unsupported expression type found. (" + exp.getClass().toString() + " --- " + value.getClass().toString() + ")");
			}
		}
		else if(exp instanceof Formula){
			return ((BooleanValue) value).value() ? (Formula) exp : ((Formula) exp).not() ;
		}
		else if(exp instanceof IntExpression){
			return ((IntExpression) exp).eq(IntConstant.constant(((IntegerValue) value).value()));
		}
		throw new TransformationException("Unsupported expression type found. (" + exp.getClass().toString() + " --- " + value.getClass().toString() + ")");
	}
	
	@Override
	protected void newSolution(Map<Relation, TupleSet> relationTuples) {
		Formula f = genClassifyingTermFormula();
		((ModelConfigurator) model.getConfigurator()).setSolutionFormula(f);
		validate(model);
	}

	protected Formula genClassifyingTermFormula() {
		Formula f = null;
		
		for (Map<ClassifyingTerm, Value> solutions : termSolutions) {
			Formula currSolution = null;
			
			for (ClassifyingTerm ct : classifyingTerms) {
				Value value = solutions.get(ct);
				Formula solFormula = encodeSolutionValue(ct.kodkodExpr, value);
				currSolution = (currSolution == null) ? solFormula : currSolution.and( solFormula ) ;
			}
			
			f = (f == null) ? currSolution.not() : f.and(currSolution.not()) ;
		}
		return f;
	}

	protected void readSolutionTerms(MSystemState state) {
		Evaluator eval = new Evaluator();
		Map<ClassifyingTerm, Value> solutionMap = new LinkedHashMap<>();
		
		for (ClassifyingTerm ct : classifyingTerms) {
			Value kodkodEval = evaluateKodkod(ct, evaluator);
			Value useEval = eval.eval(ct.oclExpr, state);
			
			if(kodkodEval != null && !kodkodEval.equals(useEval)){
				LOG.error("Kodkod and USE evaluate classifying term " + StringUtil.inQuotes(ct.name) + " differently. Kodkod: " + kodkodEval + "; USE: " + useEval);
				LOG.info("Using evaluated value of USE for further steps.");
			}
			solutionMap.put(ct, useEval);
		}
		termSolutions.add(solutionMap);
		printCurrentSolutionClassifyingTermValues(termSolutions.size()-1);
	}

	protected void printCurrentSolutionClassifyingTermValues(int idx) {
		List<String> solutionPrints = new ArrayList<String>(classifyingTerms.size());
		
		Map<ClassifyingTerm, Value> map = termSolutions.get(idx);
		for (Entry<ClassifyingTerm, Value> entry : map.entrySet()) {
			solutionPrints.add(entry.getKey().name + ": " + entry.getValue());
		}
		
		LOG.info("Term values for this solution:" + StringUtil.NEWLINE + StringUtil.fmtSeq(solutionPrints, StringUtil.NEWLINE));
	}

	protected Value evaluateKodkod(ClassifyingTerm ct, kodkod.engine.Evaluator evaluatorEnginge) {
		Value kodkodEval = null;
		if(evaluatorEnginge != null){
			if(ct.kodkodExpr instanceof kodkod.ast.Expression){
				TupleSet res = evaluatorEnginge.evaluate((kodkod.ast.Expression) ct.kodkodExpr);
				// result should always be a single value of Integer, Undefined, Undefined_Set, Boolean_True or Boolean_False
				if(res.arity() == 1){
					//TODO might cause an exception if result is not in universe (for integers, see lolis mail)
					Object intermRes;
					try {
						intermRes = res.iterator().next().atom(0);
					}
					catch(IndexOutOfBoundsException ex){
						LOG.warn("Could not compare evaluation results of USE and Kodkod.");
						return null;
					}
					
					if(intermRes instanceof Integer){
						kodkodEval = IntegerValue.valueOf((int) intermRes);
					} else if(intermRes instanceof String){
						switch((String) intermRes){
						case TypeConstants.UNDEFINED:
						case TypeConstants.UNDEFINED_SET:
							kodkodEval = UndefinedValue.instance;
							break;
						case TypeConstants.BOOLEAN_TRUE:
						case TypeConstants.TRUE:
							kodkodEval = BooleanValue.TRUE;
							break;
						case TypeConstants.BOOLEAN_FALSE:
						case TypeConstants.FALSE:
							kodkodEval = BooleanValue.FALSE;
							break;
						default:
							LOG.error("Unexpected kodkod result when evaluating classifying term." + StringUtil.NEWLINE
									+ "Classifying term " + StringUtil.inQuotes(ct.name) + " yields: "
									+ res.toString());
						}
					}
				} else {
					LOG.error("Arity of kodkod result is not one!" + StringUtil.NEWLINE
							+ "Classifying term " + StringUtil.inQuotes(ct.name)
							+ " yields: " + res.toString());
				}
			} else if(ct.kodkodExpr instanceof IntExpression){
				int res  = evaluatorEnginge.evaluate((IntExpression) ct.kodkodExpr);
				kodkodEval = IntegerValue.valueOf(res);
			} else if(ct.kodkodExpr instanceof Formula){
				boolean res = evaluatorEnginge.evaluate((Formula) ct.kodkodExpr);
				kodkodEval = BooleanValue.get(res);
			}
		}
		return kodkodEval;
	}
	
	public void addClassifyingTerm(String name, Expression term, Node termKodkod){
		classifyingTerms.add(new ClassifyingTerm(name, term, termKodkod));
	}
	
	public int classifyingTermCount(){
		return classifyingTerms.size();
	}
	
	@Override
	public void showSolution(int index) {
		int oldIdx = solutionIndex;
		super.showSolution(index);
		if(solutionIndex != oldIdx){
			printCurrentSolutionClassifyingTermValues(solutionIndex);
		}
	}
	
}

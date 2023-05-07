package org.tzi.use.kodkod;

import java.util.Map;

import kodkod.ast.Relation;
import kodkod.instance.TupleSet;

import org.apache.log4j.Logger;
import org.tzi.kodkod.KodkodModelValidator;
import org.tzi.kodkod.helper.LogMessages;
import org.tzi.kodkod.helper.ProofHelper;
import org.tzi.kodkod.model.config.impl.ModelConfigurator;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IInvariant;
import org.tzi.use.api.UseApiException;
import org.tzi.use.kodkod.solution.ObjectDiagramCreator;
import org.tzi.use.kodkod.solution.ObjectDiagramCreator.ErrorType;
import org.tzi.use.main.Session;

/**
 * Class for a simple model validation with subsequent object diagram
 * creation.
 * 
 * @author Hendrik Reitmann
 * 
 */
public class UseKodkodModelValidator extends KodkodModelValidator {

	protected static final Logger LOG = Logger.getLogger(UseKodkodModelValidator.class);

	protected Session session;

	public UseKodkodModelValidator(Session session) {
		this.session = session;
	}

	@Override
	protected void satisfiable() {
		handleSolution();
	}

	@Override
	protected void trivially_satisfiable() {
		handleSolution();
	}

	/**
	 * Handles a satisfiable solution.
	 */
	protected void handleSolution() {
		if(createObjectDiagram(solution.instance().relationTuples())){
			LOG.info("USE found errors in the solution. Trying to find a new solution!");
			
			session.reset();
			newSolution(solution.instance().relationTuples());
		}
		evaluateInactiveInvariants();
	}

	@Override
	protected void trivially_unsatisfiable() {
		handleFailure();
	}

	@Override
	protected void unsatisfiable() {
		handleFailure();
	}

	private void handleFailure() {
		if(solution.proof() != null){
			LOG.info(ProofHelper.buildProofString(solution.proof(), true));
		}
	}
	
	/**
	 * Starts the object diagram creation.
	 * 
	 * @param relationTuples
	 */
	protected boolean createObjectDiagram(Map<Relation, TupleSet> relationTuples) {
		LOG.info(LogMessages.objDiagramCreation);

		session.reset();
		ObjectDiagramCreator diagramCreator = new ObjectDiagramCreator(model, session);
		try {
			diagramCreator.create(relationTuples);
			// only retry another solution if the current one contains structural errors
			return (diagramCreator.hasDiagramErrors() == ErrorType.STRUCTURE);
		} catch (UseApiException ex) {
			if (LOG.isDebugEnabled()) {
				LOG.error(LogMessages.objDiagramCreationError, ex);
			} else {
				LOG.error(LogMessages.objDiagramCreationError + " Reason: " + ex.getMessage());
			}
			//hanhdd-begin
			//return false;
			return true;
			//hanhdd-end
		}
	}

	/**
	 * Set up the model to forbid previous solutions and start a model
	 * validation to find a new solution.
	 */
	protected void newSolution(Map<Relation, TupleSet> relationTuples) {
		ModelConfigurator modelConfigurator = (ModelConfigurator) model.getConfigurator();
		modelConfigurator.forbid(relationTuples);
		validate(model);
	}

	/**
	 * Evaluation of the inactive invariants.
	 * 
	 * Probably for the generation of default properties file. If a normal run
	 * fails with that, all invariants are deactivated and another run is tried.
	 * If it succeeds, all invariants that are valid in the found solution are
	 * enabled in the properties file.
	 */
	private void evaluateInactiveInvariants() {
		boolean info = false;
		for (IClass clazz : model.classes()) {
			for (IInvariant invariant : clazz.invariants()) {
				if (!invariant.isActivated()) {
					if (!info) {
						LOG.debug(LogMessages.inactiveInvariantEval);
						info = true;
					}

					LOG.debug("Invariant " + invariant.qualifiedName() + ": " + evaluator.evaluate(invariant.formula()));
				}
			}
		}
	}
}

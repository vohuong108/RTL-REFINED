/**
 */
package jobCollection.util;

import jobCollection.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see JobCollectionPackage
 * @generated
 */
public class JobCollectionSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JobCollectionPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobCollectionSwitch() {
		if (modelPackage == null) {
			modelPackage = JobCollectionPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case JobCollectionPackage.JOB_COLLECTION: {
				JobCollection jobCollection = (JobCollection)theEObject;
				T result = caseJobCollection(jobCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.LIQUID_TRANSFER_JOB: {
				LiquidTransferJob liquidTransferJob = (LiquidTransferJob)theEObject;
				T result = caseLiquidTransferJob(liquidTransferJob);
				if (result == null) result = caseJob(liquidTransferJob);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.LABWARE: {
				Labware labware = (Labware)theEObject;
				T result = caseLabware(labware);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.TROUGH: {
				Trough trough = (Trough)theEObject;
				T result = caseTrough(trough);
				if (result == null) result = caseLabware(trough);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.MICROPLATE: {
				Microplate microplate = (Microplate)theEObject;
				T result = caseMicroplate(microplate);
				if (result == null) result = caseLabware(microplate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.TUBE_RUNNER: {
				TubeRunner tubeRunner = (TubeRunner)theEObject;
				T result = caseTubeRunner(tubeRunner);
				if (result == null) result = caseLabware(tubeRunner);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.TIP_LIQUID_TRANSFER: {
				TipLiquidTransfer tipLiquidTransfer = (TipLiquidTransfer)theEObject;
				T result = caseTipLiquidTransfer(tipLiquidTransfer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.JOB: {
				Job job = (Job)theEObject;
				T result = caseJob(job);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.WASH_JOB: {
				WashJob washJob = (WashJob)theEObject;
				T result = caseWashJob(washJob);
				if (result == null) result = caseJob(washJob);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JobCollectionPackage.INCUBATE_JOB: {
				IncubateJob incubateJob = (IncubateJob)theEObject;
				T result = caseIncubateJob(incubateJob);
				if (result == null) result = caseJob(incubateJob);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJobCollection(JobCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Liquid Transfer Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Liquid Transfer Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiquidTransferJob(LiquidTransferJob object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Labware</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Labware</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabware(Labware object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trough</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trough</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrough(Trough object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Microplate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Microplate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMicroplate(Microplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tube Runner</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tube Runner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTubeRunner(TubeRunner object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tip Liquid Transfer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tip Liquid Transfer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTipLiquidTransfer(TipLiquidTransfer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJob(Job object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wash Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wash Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWashJob(WashJob object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Incubate Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Incubate Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncubateJob(IncubateJob object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //JobCollectionSwitch

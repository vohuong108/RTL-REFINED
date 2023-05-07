/**
 */
package jobCollection.util;

import jobCollection.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see JobCollectionPackage
 * @generated
 */
public class JobCollectionAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JobCollectionPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobCollectionAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = JobCollectionPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JobCollectionSwitch<Adapter> modelSwitch =
		new JobCollectionSwitch<Adapter>() {
			@Override
			public Adapter caseJobCollection(JobCollection object) {
				return createJobCollectionAdapter();
			}
			@Override
			public Adapter caseLiquidTransferJob(LiquidTransferJob object) {
				return createLiquidTransferJobAdapter();
			}
			@Override
			public Adapter caseLabware(Labware object) {
				return createLabwareAdapter();
			}
			@Override
			public Adapter caseTrough(Trough object) {
				return createTroughAdapter();
			}
			@Override
			public Adapter caseMicroplate(Microplate object) {
				return createMicroplateAdapter();
			}
			@Override
			public Adapter caseTubeRunner(TubeRunner object) {
				return createTubeRunnerAdapter();
			}
			@Override
			public Adapter caseTipLiquidTransfer(TipLiquidTransfer object) {
				return createTipLiquidTransferAdapter();
			}
			@Override
			public Adapter caseJob(Job object) {
				return createJobAdapter();
			}
			@Override
			public Adapter caseWashJob(WashJob object) {
				return createWashJobAdapter();
			}
			@Override
			public Adapter caseIncubateJob(IncubateJob object) {
				return createIncubateJobAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link JobCollection <em>Job Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see JobCollection
	 * @generated
	 */
	public Adapter createJobCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link LiquidTransferJob <em>Liquid Transfer Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see LiquidTransferJob
	 * @generated
	 */
	public Adapter createLiquidTransferJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Labware <em>Labware</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Labware
	 * @generated
	 */
	public Adapter createLabwareAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Trough <em>Trough</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Trough
	 * @generated
	 */
	public Adapter createTroughAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Microplate <em>Microplate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Microplate
	 * @generated
	 */
	public Adapter createMicroplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link TubeRunner <em>Tube Runner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see TubeRunner
	 * @generated
	 */
	public Adapter createTubeRunnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link TipLiquidTransfer <em>Tip Liquid Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see TipLiquidTransfer
	 * @generated
	 */
	public Adapter createTipLiquidTransferAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Job
	 * @generated
	 */
	public Adapter createJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link WashJob <em>Wash Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see WashJob
	 * @generated
	 */
	public Adapter createWashJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link IncubateJob <em>Incubate Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see IncubateJob
	 * @generated
	 */
	public Adapter createIncubateJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //JobCollectionAdapterFactory

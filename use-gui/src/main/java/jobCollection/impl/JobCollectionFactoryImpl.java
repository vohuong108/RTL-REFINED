/**
 */
package jobCollection.impl;

import jobCollection.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JobCollectionFactoryImpl extends EFactoryImpl implements JobCollectionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JobCollectionFactory init() {
		try {
			JobCollectionFactory theJobCollectionFactory = (JobCollectionFactory)EPackage.Registry.INSTANCE.getEFactory(JobCollectionPackage.eNS_URI);
			if (theJobCollectionFactory != null) {
				return theJobCollectionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JobCollectionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobCollectionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case JobCollectionPackage.JOB_COLLECTION: return createJobCollection();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB: return createLiquidTransferJob();
			case JobCollectionPackage.LABWARE: return createLabware();
			case JobCollectionPackage.TROUGH: return createTrough();
			case JobCollectionPackage.MICROPLATE: return createMicroplate();
			case JobCollectionPackage.TUBE_RUNNER: return createTubeRunner();
			case JobCollectionPackage.TIP_LIQUID_TRANSFER: return createTipLiquidTransfer();
			case JobCollectionPackage.WASH_JOB: return createWashJob();
			case JobCollectionPackage.INCUBATE_JOB: return createIncubateJob();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case JobCollectionPackage.JOB_STATUS:
				return createJobStatusFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case JobCollectionPackage.JOB_STATUS:
				return convertJobStatusToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobCollection createJobCollection() {
		JobCollectionImpl jobCollection = new JobCollectionImpl();
		return jobCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiquidTransferJob createLiquidTransferJob() {
		LiquidTransferJobImpl liquidTransferJob = new LiquidTransferJobImpl();
		return liquidTransferJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labware createLabware() {
		LabwareImpl labware = new LabwareImpl();
		return labware;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trough createTrough() {
		TroughImpl trough = new TroughImpl();
		return trough;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Microplate createMicroplate() {
		MicroplateImpl microplate = new MicroplateImpl();
		return microplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TubeRunner createTubeRunner() {
		TubeRunnerImpl tubeRunner = new TubeRunnerImpl();
		return tubeRunner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TipLiquidTransfer createTipLiquidTransfer() {
		TipLiquidTransferImpl tipLiquidTransfer = new TipLiquidTransferImpl();
		return tipLiquidTransfer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WashJob createWashJob() {
		WashJobImpl washJob = new WashJobImpl();
		return washJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IncubateJob createIncubateJob() {
		IncubateJobImpl incubateJob = new IncubateJobImpl();
		return incubateJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobStatus createJobStatusFromString(EDataType eDataType, String initialValue) {
		JobStatus result = JobStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJobStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobCollectionPackage getJobCollectionPackage() {
		return (JobCollectionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static JobCollectionPackage getPackage() {
		return JobCollectionPackage.eINSTANCE;
	}

} //JobCollectionFactoryImpl

/**
 */
package jobCollection;

import org.eclipse.emf.ecore.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see JobCollectionFactory
 * @model kind="package"
 * @generated
 */
public interface JobCollectionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jobCollection";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.transformation-tool-contest.eu/ttc21/jobCollection";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jobs";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JobCollectionPackage eINSTANCE = jobCollection.impl.JobCollectionPackageImpl.init();

	/**
	 * The meta object id for the '{@link jobCollection.impl.JobCollectionImpl <em>Job Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.JobCollectionImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getJobCollection()
	 * @generated
	 */
	int JOB_COLLECTION = 0;

	/**
	 * The feature id for the '<em><b>Labware</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_COLLECTION__LABWARE = 0;

	/**
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_COLLECTION__JOBS = 1;

	/**
	 * The number of structural features of the '<em>Job Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_COLLECTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link jobCollection.impl.JobImpl <em>Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.JobImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getJob()
	 * @generated
	 */
	int JOB = 7;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__STATE = 0;

	/**
	 * The feature id for the '<em><b>Protocol Step Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__PROTOCOL_STEP_NAME = 1;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__PREVIOUS = 2;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NEXT = 3;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link jobCollection.impl.LiquidTransferJobImpl <em>Liquid Transfer Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.LiquidTransferJobImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getLiquidTransferJob()
	 * @generated
	 */
	int LIQUID_TRANSFER_JOB = 1;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB__STATE = JOB__STATE;

	/**
	 * The feature id for the '<em><b>Protocol Step Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB__PROTOCOL_STEP_NAME = JOB__PROTOCOL_STEP_NAME;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB__PREVIOUS = JOB__PREVIOUS;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB__NEXT = JOB__NEXT;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB__TARGET = JOB_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tips</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB__TIPS = JOB_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB__SOURCE = JOB_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Liquid Transfer Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIQUID_TRANSFER_JOB_FEATURE_COUNT = JOB_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link jobCollection.impl.LabwareImpl <em>Labware</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.LabwareImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getLabware()
	 * @generated
	 */
	int LABWARE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABWARE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Labware</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABWARE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link jobCollection.impl.TroughImpl <em>Trough</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.TroughImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getTrough()
	 * @generated
	 */
	int TROUGH = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TROUGH__NAME = LABWARE__NAME;

	/**
	 * The number of structural features of the '<em>Trough</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TROUGH_FEATURE_COUNT = LABWARE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jobCollection.impl.MicroplateImpl <em>Microplate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.MicroplateImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getMicroplate()
	 * @generated
	 */
	int MICROPLATE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROPLATE__NAME = LABWARE__NAME;

	/**
	 * The number of structural features of the '<em>Microplate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROPLATE_FEATURE_COUNT = LABWARE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jobCollection.impl.TubeRunnerImpl <em>Tube Runner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.TubeRunnerImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getTubeRunner()
	 * @generated
	 */
	int TUBE_RUNNER = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUBE_RUNNER__NAME = LABWARE__NAME;

	int TUBE_RUNNER__BARCODESFLAT = LABWARE_FEATURE_COUNT;

	/**
	 * The feature id for the '<em><b>Barcodes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUBE_RUNNER__BARCODES = LABWARE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tube Runner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUBE_RUNNER_FEATURE_COUNT = LABWARE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link jobCollection.impl.TipLiquidTransferImpl <em>Tip Liquid Transfer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.TipLiquidTransferImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getTipLiquidTransfer()
	 * @generated
	 */
	int TIP_LIQUID_TRANSFER = 6;

	/**
	 * The feature id for the '<em><b>Source Cavity Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX = 0;

	/**
	 * The feature id for the '<em><b>Volume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIP_LIQUID_TRANSFER__VOLUME = 1;

	/**
	 * The feature id for the '<em><b>Target Cavity Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX = 2;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIP_LIQUID_TRANSFER__STATUS = 3;

	/**
	 * The number of structural features of the '<em>Tip Liquid Transfer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIP_LIQUID_TRANSFER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link jobCollection.impl.WashJobImpl <em>Wash Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.WashJobImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getWashJob()
	 * @generated
	 */
	int WASH_JOB = 8;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WASH_JOB__STATE = JOB__STATE;

	/**
	 * The feature id for the '<em><b>Protocol Step Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WASH_JOB__PROTOCOL_STEP_NAME = JOB__PROTOCOL_STEP_NAME;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WASH_JOB__PREVIOUS = JOB__PREVIOUS;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WASH_JOB__NEXT = JOB__NEXT;

	/**
	 * The feature id for the '<em><b>Microplate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WASH_JOB__MICROPLATE = JOB_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cavities</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WASH_JOB__CAVITIES = JOB_FEATURE_COUNT + 3;

	int WASH_JOB__CAVITIESFLAT = JOB_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Wash Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WASH_JOB_FEATURE_COUNT = JOB_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link jobCollection.impl.IncubateJobImpl <em>Incubate Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jobCollection.impl.IncubateJobImpl
	 * @see jobCollection.impl.JobCollectionPackageImpl#getIncubateJob()
	 * @generated
	 */
	int INCUBATE_JOB = 9;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB__STATE = JOB__STATE;

	/**
	 * The feature id for the '<em><b>Protocol Step Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB__PROTOCOL_STEP_NAME = JOB__PROTOCOL_STEP_NAME;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB__PREVIOUS = JOB__PREVIOUS;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB__NEXT = JOB__NEXT;

	/**
	 * The feature id for the '<em><b>Microplate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB__MICROPLATE = JOB_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB__TEMPERATURE = JOB_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB__DURATION = JOB_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Incubate Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCUBATE_JOB_FEATURE_COUNT = JOB_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link JobStatus <em>Job Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see JobStatus
	 * @see jobCollection.impl.JobCollectionPackageImpl#getJobStatus()
	 * @generated
	 */
	int JOB_STATUS = 10;


	/**
	 * Returns the meta object for class '{@link JobCollection <em>Job Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job Collection</em>'.
	 * @see JobCollection
	 * @generated
	 */
	EClass getJobCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link JobCollection#getLabware <em>Labware</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Labware</em>'.
	 * @see JobCollection#getLabware()
	 * @see #getJobCollection()
	 * @generated
	 */
	EReference getJobCollection_Labware();

	/**
	 * Returns the meta object for the containment reference list '{@link JobCollection#getJobs <em>Jobs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jobs</em>'.
	 * @see JobCollection#getJobs()
	 * @see #getJobCollection()
	 * @generated
	 */
	EReference getJobCollection_Jobs();

	/**
	 * Returns the meta object for class '{@link LiquidTransferJob <em>Liquid Transfer Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Liquid Transfer Job</em>'.
	 * @see LiquidTransferJob
	 * @generated
	 */
	EClass getLiquidTransferJob();

	/**
	 * Returns the meta object for the reference '{@link LiquidTransferJob#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see LiquidTransferJob#getTarget()
	 * @see #getLiquidTransferJob()
	 * @generated
	 */
	EReference getLiquidTransferJob_Target();

	/**
	 * Returns the meta object for the containment reference list '{@link LiquidTransferJob#getTips <em>Tips</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tips</em>'.
	 * @see LiquidTransferJob#getTips()
	 * @see #getLiquidTransferJob()
	 * @generated
	 */
	EReference getLiquidTransferJob_Tips();

	/**
	 * Returns the meta object for the reference '{@link LiquidTransferJob#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see LiquidTransferJob#getSource()
	 * @see #getLiquidTransferJob()
	 * @generated
	 */
	EReference getLiquidTransferJob_Source();

	/**
	 * Returns the meta object for class '{@link Labware <em>Labware</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Labware</em>'.
	 * @see Labware
	 * @generated
	 */
	EClass getLabware();

	/**
	 * Returns the meta object for the attribute '{@link Labware#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Labware#getName()
	 * @see #getLabware()
	 * @generated
	 */
	EAttribute getLabware_Name();

	/**
	 * Returns the meta object for class '{@link Trough <em>Trough</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trough</em>'.
	 * @see Trough
	 * @generated
	 */
	EClass getTrough();

	/**
	 * Returns the meta object for class '{@link Microplate <em>Microplate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Microplate</em>'.
	 * @see Microplate
	 * @generated
	 */
	EClass getMicroplate();

	/**
	 * Returns the meta object for class '{@link TubeRunner <em>Tube Runner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tube Runner</em>'.
	 * @see TubeRunner
	 * @generated
	 */
	EClass getTubeRunner();

	/**
	 * Returns the meta object for the attribute list '{@link TubeRunner#getBarcodes <em>Barcodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Barcodes</em>'.
	 * @see TubeRunner#getBarcodes()
	 * @see #getTubeRunner()
	 * @generated
	 */
	EAttribute getTubeRunner_Barcodes();

	EAttribute getTubeRunner_BarcodesFlat();

	/**
	 * Returns the meta object for class '{@link TipLiquidTransfer <em>Tip Liquid Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tip Liquid Transfer</em>'.
	 * @see TipLiquidTransfer
	 * @generated
	 */
	EClass getTipLiquidTransfer();

	/**
	 * Returns the meta object for the attribute '{@link TipLiquidTransfer#getSourceCavityIndex <em>Source Cavity Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Cavity Index</em>'.
	 * @see TipLiquidTransfer#getSourceCavityIndex()
	 * @see #getTipLiquidTransfer()
	 * @generated
	 */
	EAttribute getTipLiquidTransfer_SourceCavityIndex();

	/**
	 * Returns the meta object for the attribute '{@link TipLiquidTransfer#getVolume <em>Volume</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Volume</em>'.
	 * @see TipLiquidTransfer#getVolume()
	 * @see #getTipLiquidTransfer()
	 * @generated
	 */
	EAttribute getTipLiquidTransfer_Volume();

	/**
	 * Returns the meta object for the attribute '{@link TipLiquidTransfer#getTargetCavityIndex <em>Target Cavity Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Cavity Index</em>'.
	 * @see TipLiquidTransfer#getTargetCavityIndex()
	 * @see #getTipLiquidTransfer()
	 * @generated
	 */
	EAttribute getTipLiquidTransfer_TargetCavityIndex();

	/**
	 * Returns the meta object for the attribute '{@link TipLiquidTransfer#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see TipLiquidTransfer#getStatus()
	 * @see #getTipLiquidTransfer()
	 * @generated
	 */
	EAttribute getTipLiquidTransfer_Status();

	/**
	 * Returns the meta object for class '{@link Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job</em>'.
	 * @see Job
	 * @generated
	 */
	EClass getJob();

	/**
	 * Returns the meta object for the attribute '{@link Job#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see Job#getState()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_State();

	/**
	 * Returns the meta object for the attribute '{@link Job#getProtocolStepName <em>Protocol Step Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Protocol Step Name</em>'.
	 * @see Job#getProtocolStepName()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_ProtocolStepName();

	/**
	 * Returns the meta object for the reference list '{@link Job#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Previous</em>'.
	 * @see Job#getPrevious()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Previous();

	/**
	 * Returns the meta object for the reference list '{@link Job#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Next</em>'.
	 * @see Job#getNext()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Next();

	/**
	 * Returns the meta object for class '{@link WashJob <em>Wash Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wash Job</em>'.
	 * @see WashJob
	 * @generated
	 */
	EClass getWashJob();

	/**
	 * Returns the meta object for the reference '{@link WashJob#getMicroplate <em>Microplate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Microplate</em>'.
	 * @see WashJob#getMicroplate()
	 * @see #getWashJob()
	 * @generated
	 */
	EReference getWashJob_Microplate();

	/**
	 * Returns the meta object for the attribute list '{@link WashJob#getCavities <em>Cavities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cavities</em>'.
	 * @see WashJob#getCavities()
	 * @see #getWashJob()
	 * @generated
	 */
	EAttribute getWashJob_Cavities();

	EAttribute getWashJob_CavitiesFlat();

	/**
	 * Returns the meta object for class '{@link IncubateJob <em>Incubate Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Incubate Job</em>'.
	 * @see IncubateJob
	 * @generated
	 */
	EClass getIncubateJob();

	/**
	 * Returns the meta object for the reference '{@link IncubateJob#getMicroplate <em>Microplate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Microplate</em>'.
	 * @see IncubateJob#getMicroplate()
	 * @see #getIncubateJob()
	 * @generated
	 */
	EReference getIncubateJob_Microplate();

	/**
	 * Returns the meta object for the attribute '{@link IncubateJob#getTemperature <em>Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temperature</em>'.
	 * @see IncubateJob#getTemperature()
	 * @see #getIncubateJob()
	 * @generated
	 */
	EAttribute getIncubateJob_Temperature();

	/**
	 * Returns the meta object for the attribute '{@link IncubateJob#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see IncubateJob#getDuration()
	 * @see #getIncubateJob()
	 * @generated
	 */
	EAttribute getIncubateJob_Duration();

	/**
	 * Returns the meta object for enum '{@link JobStatus <em>Job Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Job Status</em>'.
	 * @see JobStatus
	 * @generated
	 */
	EEnum getJobStatus();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	JobCollectionFactory getJobCollectionFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link jobCollection.impl.JobCollectionImpl <em>Job Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.JobCollectionImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getJobCollection()
		 * @generated
		 */
		EClass JOB_COLLECTION = eINSTANCE.getJobCollection();

		/**
		 * The meta object literal for the '<em><b>Labware</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB_COLLECTION__LABWARE = eINSTANCE.getJobCollection_Labware();

		/**
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB_COLLECTION__JOBS = eINSTANCE.getJobCollection_Jobs();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.LiquidTransferJobImpl <em>Liquid Transfer Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.LiquidTransferJobImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getLiquidTransferJob()
		 * @generated
		 */
		EClass LIQUID_TRANSFER_JOB = eINSTANCE.getLiquidTransferJob();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIQUID_TRANSFER_JOB__TARGET = eINSTANCE.getLiquidTransferJob_Target();

		/**
		 * The meta object literal for the '<em><b>Tips</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIQUID_TRANSFER_JOB__TIPS = eINSTANCE.getLiquidTransferJob_Tips();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIQUID_TRANSFER_JOB__SOURCE = eINSTANCE.getLiquidTransferJob_Source();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.LabwareImpl <em>Labware</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.LabwareImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getLabware()
		 * @generated
		 */
		EClass LABWARE = eINSTANCE.getLabware();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABWARE__NAME = eINSTANCE.getLabware_Name();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.TroughImpl <em>Trough</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.TroughImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getTrough()
		 * @generated
		 */
		EClass TROUGH = eINSTANCE.getTrough();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.MicroplateImpl <em>Microplate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.MicroplateImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getMicroplate()
		 * @generated
		 */
		EClass MICROPLATE = eINSTANCE.getMicroplate();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.TubeRunnerImpl <em>Tube Runner</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.TubeRunnerImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getTubeRunner()
		 * @generated
		 */
		EClass TUBE_RUNNER = eINSTANCE.getTubeRunner();

		/**
		 * The meta object literal for the '<em><b>Barcodes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
//		EAttribute TUBE_RUNNER__BARCODES = eINSTANCE.getTubeRunner_Barcodes();

		EAttribute TUBE_RUNNER__BARCODESFLAT = eINSTANCE.getTubeRunner_BarcodesFlat();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.TipLiquidTransferImpl <em>Tip Liquid Transfer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.TipLiquidTransferImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getTipLiquidTransfer()
		 * @generated
		 */
		EClass TIP_LIQUID_TRANSFER = eINSTANCE.getTipLiquidTransfer();

		/**
		 * The meta object literal for the '<em><b>Source Cavity Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX = eINSTANCE.getTipLiquidTransfer_SourceCavityIndex();

		/**
		 * The meta object literal for the '<em><b>Volume</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIP_LIQUID_TRANSFER__VOLUME = eINSTANCE.getTipLiquidTransfer_Volume();

		/**
		 * The meta object literal for the '<em><b>Target Cavity Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX = eINSTANCE.getTipLiquidTransfer_TargetCavityIndex();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIP_LIQUID_TRANSFER__STATUS = eINSTANCE.getTipLiquidTransfer_Status();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.JobImpl <em>Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.JobImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getJob()
		 * @generated
		 */
		EClass JOB = eINSTANCE.getJob();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__STATE = eINSTANCE.getJob_State();

		/**
		 * The meta object literal for the '<em><b>Protocol Step Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__PROTOCOL_STEP_NAME = eINSTANCE.getJob_ProtocolStepName();

		/**
		 * The meta object literal for the '<em><b>Previous</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__PREVIOUS = eINSTANCE.getJob_Previous();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__NEXT = eINSTANCE.getJob_Next();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.WashJobImpl <em>Wash Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.WashJobImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getWashJob()
		 * @generated
		 */
		EClass WASH_JOB = eINSTANCE.getWashJob();

		/**
		 * The meta object literal for the '<em><b>Microplate</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WASH_JOB__MICROPLATE = eINSTANCE.getWashJob_Microplate();

		/**
		 * The meta object literal for the '<em><b>Cavities</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
//		EAttribute WASH_JOB__CAVITIES = eINSTANCE.getWashJob_Cavities();

		EAttribute WASH_JOB__CAVITIESFLAT = eINSTANCE.getWashJob_CavitiesFlat();

		/**
		 * The meta object literal for the '{@link jobCollection.impl.IncubateJobImpl <em>Incubate Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jobCollection.impl.IncubateJobImpl
		 * @see jobCollection.impl.JobCollectionPackageImpl#getIncubateJob()
		 * @generated
		 */
		EClass INCUBATE_JOB = eINSTANCE.getIncubateJob();

		/**
		 * The meta object literal for the '<em><b>Microplate</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INCUBATE_JOB__MICROPLATE = eINSTANCE.getIncubateJob_Microplate();

		/**
		 * The meta object literal for the '<em><b>Temperature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCUBATE_JOB__TEMPERATURE = eINSTANCE.getIncubateJob_Temperature();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCUBATE_JOB__DURATION = eINSTANCE.getIncubateJob_Duration();

		/**
		 * The meta object literal for the '{@link JobStatus <em>Job Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see JobStatus
		 * @see jobCollection.impl.JobCollectionPackageImpl#getJobStatus()
		 * @generated
		 */
		EEnum JOB_STATUS = eINSTANCE.getJobStatus();

	}

} //JobCollectionPackage

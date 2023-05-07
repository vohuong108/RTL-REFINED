/**
 */
package jobCollection.impl;

import jobCollection.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JobCollectionPackageImpl extends EPackageImpl implements JobCollectionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass liquidTransferJobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labwareEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass troughEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass microplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tubeRunnerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tipLiquidTransferEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass washJobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass incubateJobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum jobStatusEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see JobCollectionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JobCollectionPackageImpl() {
		super(eNS_URI, JobCollectionFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link JobCollectionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JobCollectionPackage init() {
		if (isInited) return (JobCollectionPackage)EPackage.Registry.INSTANCE.getEPackage(JobCollectionPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredJobCollectionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		JobCollectionPackageImpl theJobCollectionPackage = registeredJobCollectionPackage instanceof JobCollectionPackageImpl ? (JobCollectionPackageImpl)registeredJobCollectionPackage : new JobCollectionPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theJobCollectionPackage.createPackageContents();

		// Initialize created meta-data
		theJobCollectionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJobCollectionPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(JobCollectionPackage.eNS_URI, theJobCollectionPackage);
		return theJobCollectionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJobCollection() {
		return jobCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJobCollection_Labware() {
		return (EReference)jobCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJobCollection_Jobs() {
		return (EReference)jobCollectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiquidTransferJob() {
		return liquidTransferJobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLiquidTransferJob_Target() {
		return (EReference)liquidTransferJobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLiquidTransferJob_Tips() {
		return (EReference)liquidTransferJobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLiquidTransferJob_Source() {
		return (EReference)liquidTransferJobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabware() {
		return labwareEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabware_Name() {
		return (EAttribute)labwareEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrough() {
		return troughEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMicroplate() {
		return microplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTubeRunner() {
		return tubeRunnerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTubeRunner_Barcodes() {
		return (EAttribute)tubeRunnerEClass.getEStructuralFeatures().get(1);
	}

	public EAttribute getTubeRunner_BarcodesFlat() {
		return (EAttribute)tubeRunnerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTipLiquidTransfer() {
		return tipLiquidTransferEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTipLiquidTransfer_SourceCavityIndex() {
		return (EAttribute)tipLiquidTransferEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTipLiquidTransfer_Volume() {
		return (EAttribute)tipLiquidTransferEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTipLiquidTransfer_TargetCavityIndex() {
		return (EAttribute)tipLiquidTransferEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTipLiquidTransfer_Status() {
		return (EAttribute)tipLiquidTransferEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJob() {
		return jobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJob_State() {
		return (EAttribute)jobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJob_ProtocolStepName() {
		return (EAttribute)jobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJob_Previous() {
		return (EReference)jobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJob_Next() {
		return (EReference)jobEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWashJob() {
		return washJobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWashJob_Microplate() {
		return (EReference)washJobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWashJob_Cavities() {
		return (EAttribute)washJobEClass.getEStructuralFeatures().get(0);
	}

	public EAttribute getWashJob_CavitiesFlat() {
		return (EAttribute)(washJobEClass.getEStructuralFeatures().get(1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIncubateJob() {
		return incubateJobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIncubateJob_Microplate() {
		return (EReference)incubateJobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIncubateJob_Temperature() {
		return (EAttribute)incubateJobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIncubateJob_Duration() {
		return (EAttribute)incubateJobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getJobStatus() {
		return jobStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobCollectionFactory getJobCollectionFactory() {
		return (JobCollectionFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		jobCollectionEClass = createEClass(JOB_COLLECTION);
		createEReference(jobCollectionEClass, JOB_COLLECTION__LABWARE);
		createEReference(jobCollectionEClass, JOB_COLLECTION__JOBS);

		liquidTransferJobEClass = createEClass(LIQUID_TRANSFER_JOB);
		createEReference(liquidTransferJobEClass, LIQUID_TRANSFER_JOB__TARGET);
		createEReference(liquidTransferJobEClass, LIQUID_TRANSFER_JOB__TIPS);
		createEReference(liquidTransferJobEClass, LIQUID_TRANSFER_JOB__SOURCE);

		labwareEClass = createEClass(LABWARE);
		createEAttribute(labwareEClass, LABWARE__NAME);

		troughEClass = createEClass(TROUGH);

		microplateEClass = createEClass(MICROPLATE);

		tubeRunnerEClass = createEClass(TUBE_RUNNER);
//		createEAttribute(tubeRunnerEClass, TUBE_RUNNER__BARCODES);
		createEAttribute(tubeRunnerEClass, TUBE_RUNNER__BARCODESFLAT);

		tipLiquidTransferEClass = createEClass(TIP_LIQUID_TRANSFER);
		createEAttribute(tipLiquidTransferEClass, TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX);
		createEAttribute(tipLiquidTransferEClass, TIP_LIQUID_TRANSFER__VOLUME);
		createEAttribute(tipLiquidTransferEClass, TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX);
		createEAttribute(tipLiquidTransferEClass, TIP_LIQUID_TRANSFER__STATUS);

		jobEClass = createEClass(JOB);
		createEAttribute(jobEClass, JOB__STATE);
		createEAttribute(jobEClass, JOB__PROTOCOL_STEP_NAME);
		createEReference(jobEClass, JOB__PREVIOUS);
		createEReference(jobEClass, JOB__NEXT);

		washJobEClass = createEClass(WASH_JOB);
		createEReference(washJobEClass, WASH_JOB__MICROPLATE);
//		createEAttribute(washJobEClass, WASH_JOB__CAVITIES);
		createEAttribute(washJobEClass, WASH_JOB__CAVITIESFLAT);

		incubateJobEClass = createEClass(INCUBATE_JOB);
		createEReference(incubateJobEClass, INCUBATE_JOB__MICROPLATE);
		createEAttribute(incubateJobEClass, INCUBATE_JOB__TEMPERATURE);
		createEAttribute(incubateJobEClass, INCUBATE_JOB__DURATION);

		// Create enums
		jobStatusEEnum = createEEnum(JOB_STATUS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		liquidTransferJobEClass.getESuperTypes().add(this.getJob());
		troughEClass.getESuperTypes().add(this.getLabware());
		microplateEClass.getESuperTypes().add(this.getLabware());
		tubeRunnerEClass.getESuperTypes().add(this.getLabware());
		washJobEClass.getESuperTypes().add(this.getJob());
		incubateJobEClass.getESuperTypes().add(this.getJob());

		// Initialize classes and features; add operations and parameters
		initEClass(jobCollectionEClass, JobCollection.class, "JobCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJobCollection_Labware(), this.getLabware(), null, "labware", null, 0, -1, JobCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJobCollection_Jobs(), this.getJob(), null, "jobs", null, 1, -1, JobCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(liquidTransferJobEClass, LiquidTransferJob.class, "LiquidTransferJob", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLiquidTransferJob_Target(), this.getLabware(), null, "target", null, 1, 1, LiquidTransferJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLiquidTransferJob_Tips(), this.getTipLiquidTransfer(), null, "tips", null, 1, 8, LiquidTransferJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLiquidTransferJob_Source(), this.getLabware(), null, "source", null, 1, 1, LiquidTransferJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labwareEClass, Labware.class, "Labware", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabware_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Labware.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(troughEClass, Trough.class, "Trough", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(microplateEClass, Microplate.class, "Microplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tubeRunnerEClass, TubeRunner.class, "TubeRunner", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
//		initEAttribute(getTubeRunner_Barcodes(), theXMLTypePackage.getString(), "barcodes", null, 0, 16, TubeRunner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTubeRunner_BarcodesFlat(), theXMLTypePackage.getString(), "barcodes", null, 1, 1, TubeRunner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tipLiquidTransferEClass, TipLiquidTransfer.class, "TipLiquidTransfer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTipLiquidTransfer_SourceCavityIndex(), theXMLTypePackage.getInt(), "sourceCavityIndex", null, 1, 1, TipLiquidTransfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTipLiquidTransfer_Volume(), theXMLTypePackage.getDouble(), "volume", null, 1, 1, TipLiquidTransfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTipLiquidTransfer_TargetCavityIndex(), theXMLTypePackage.getInt(), "targetCavityIndex", null, 1, 1, TipLiquidTransfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTipLiquidTransfer_Status(), this.getJobStatus(), "status", null, 1, 1, TipLiquidTransfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(jobEClass, Job.class, "Job", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJob_State(), this.getJobStatus(), "state", null, 1, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJob_ProtocolStepName(), theXMLTypePackage.getString(), "protocolStepName", null, 1, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Previous(), this.getJob(), this.getJob_Next(), "previous", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Next(), this.getJob(), this.getJob_Previous(), "next", null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(washJobEClass, WashJob.class, "WashJob", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWashJob_Microplate(), this.getMicroplate(), null, "microplate", null, 1, 1, WashJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
//		initEAttribute(getWashJob_Cavities(), theXMLTypePackage.getInt(), "cavities", null, 1, -1, WashJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWashJob_CavitiesFlat(), theXMLTypePackage.getString(), "cavities", null, 1, 1, WashJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(incubateJobEClass, IncubateJob.class, "IncubateJob", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIncubateJob_Microplate(), this.getMicroplate(), null, "microplate", null, 1, 1, IncubateJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncubateJob_Temperature(), theXMLTypePackage.getDouble(), "temperature", "293.15", 1, 1, IncubateJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncubateJob_Duration(), theXMLTypePackage.getInt(), "duration", null, 1, 1, IncubateJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(jobStatusEEnum, JobStatus.class, "JobStatus");
		addEEnumLiteral(jobStatusEEnum, JobStatus.PLANNED);
		addEEnumLiteral(jobStatusEEnum, JobStatus.EXECUTING);
		addEEnumLiteral(jobStatusEEnum, JobStatus.SUCCEEDED);
		addEEnumLiteral(jobStatusEEnum, JobStatus.FAILED);

		// Create resource
		createResource(eNS_URI);
	}

} //JobCollectionPackageImpl

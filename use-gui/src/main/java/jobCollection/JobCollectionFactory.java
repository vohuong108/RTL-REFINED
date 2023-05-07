/**
 */
package jobCollection;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see JobCollectionPackage
 * @generated
 */
public interface JobCollectionFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JobCollectionFactory eINSTANCE = jobCollection.impl.JobCollectionFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Job Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Job Collection</em>'.
	 * @generated
	 */
	JobCollection createJobCollection();

	/**
	 * Returns a new object of class '<em>Liquid Transfer Job</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Liquid Transfer Job</em>'.
	 * @generated
	 */
	LiquidTransferJob createLiquidTransferJob();

	/**
	 * Returns a new object of class '<em>Labware</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Labware</em>'.
	 * @generated
	 */
	Labware createLabware();

	/**
	 * Returns a new object of class '<em>Trough</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trough</em>'.
	 * @generated
	 */
	Trough createTrough();

	/**
	 * Returns a new object of class '<em>Microplate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Microplate</em>'.
	 * @generated
	 */
	Microplate createMicroplate();

	/**
	 * Returns a new object of class '<em>Tube Runner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tube Runner</em>'.
	 * @generated
	 */
	TubeRunner createTubeRunner();

	/**
	 * Returns a new object of class '<em>Tip Liquid Transfer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tip Liquid Transfer</em>'.
	 * @generated
	 */
	TipLiquidTransfer createTipLiquidTransfer();

	/**
	 * Returns a new object of class '<em>Wash Job</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wash Job</em>'.
	 * @generated
	 */
	WashJob createWashJob();

	/**
	 * Returns a new object of class '<em>Incubate Job</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Incubate Job</em>'.
	 * @generated
	 */
	IncubateJob createIncubateJob();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JobCollectionPackage getJobCollectionPackage();

} //JobCollectionFactory

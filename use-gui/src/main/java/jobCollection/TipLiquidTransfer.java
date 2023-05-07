/**
 */
package jobCollection;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tip Liquid Transfer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link TipLiquidTransfer#getSourceCavityIndex <em>Source Cavity Index</em>}</li>
 *   <li>{@link TipLiquidTransfer#getVolume <em>Volume</em>}</li>
 *   <li>{@link TipLiquidTransfer#getTargetCavityIndex <em>Target Cavity Index</em>}</li>
 *   <li>{@link TipLiquidTransfer#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @see JobCollectionPackage#getTipLiquidTransfer()
 * @model
 * @generated
 */
public interface TipLiquidTransfer extends EObject {
	/**
	 * Returns the value of the '<em><b>Source Cavity Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Cavity Index</em>' attribute.
	 * @see #setSourceCavityIndex(int)
	 * @see JobCollectionPackage#getTipLiquidTransfer_SourceCavityIndex()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 * @generated
	 */
	int getSourceCavityIndex();

	/**
	 * Sets the value of the '{@link TipLiquidTransfer#getSourceCavityIndex <em>Source Cavity Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Cavity Index</em>' attribute.
	 * @see #getSourceCavityIndex()
	 * @generated
	 */
	void setSourceCavityIndex(int value);

	/**
	 * Returns the value of the '<em><b>Volume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volume</em>' attribute.
	 * @see #setVolume(double)
	 * @see JobCollectionPackage#getTipLiquidTransfer_Volume()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getVolume();

	/**
	 * Sets the value of the '{@link TipLiquidTransfer#getVolume <em>Volume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Volume</em>' attribute.
	 * @see #getVolume()
	 * @generated
	 */
	void setVolume(double value);

	/**
	 * Returns the value of the '<em><b>Target Cavity Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Cavity Index</em>' attribute.
	 * @see #setTargetCavityIndex(int)
	 * @see JobCollectionPackage#getTipLiquidTransfer_TargetCavityIndex()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 * @generated
	 */
	int getTargetCavityIndex();

	/**
	 * Sets the value of the '{@link TipLiquidTransfer#getTargetCavityIndex <em>Target Cavity Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Cavity Index</em>' attribute.
	 * @see #getTargetCavityIndex()
	 * @generated
	 */
	void setTargetCavityIndex(int value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link JobStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see JobStatus
	 * @see #setStatus(JobStatus)
	 * @see JobCollectionPackage#getTipLiquidTransfer_Status()
	 * @model required="true"
	 * @generated
	 */
	JobStatus getStatus();

	/**
	 * Sets the value of the '{@link TipLiquidTransfer#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see JobStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(JobStatus value);

} // TipLiquidTransfer

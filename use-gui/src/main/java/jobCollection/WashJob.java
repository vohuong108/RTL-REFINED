/**
 */
package jobCollection;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wash Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link WashJob#getMicroplate <em>Microplate</em>}</li>
 *   <li>{@link WashJob#getCavities <em>Cavities</em>}</li>
 * </ul>
 *
 * @see JobCollectionPackage#getWashJob()
 * @model
 * @generated
 */
public interface WashJob extends Job {
	/**
	 * Returns the value of the '<em><b>Microplate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Microplate</em>' reference.
	 * @see #setMicroplate(Microplate)
	 * @see JobCollectionPackage#getWashJob_Microplate()
	 * @model required="true"
	 * @generated
	 */
	Microplate getMicroplate();

	/**
	 * Sets the value of the '{@link WashJob#getMicroplate <em>Microplate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Microplate</em>' reference.
	 * @see #getMicroplate()
	 * @generated
	 */
	void setMicroplate(Microplate value);

	/**
	 * Returns the value of the '<em><b>Cavities</b></em>' attribute list.
	 * The list contents are of type {@link Integer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cavities</em>' attribute list.
	 * @see JobCollectionPackage#getWashJob_Cavities()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 * @generated
	 */
	EList<Integer> getCavities();

	void setCavitiesFlat(String newCavitiesFlat);

} // WashJob

/**
 */
package jobCollection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Job#getState <em>State</em>}</li>
 *   <li>{@link Job#getProtocolStepName <em>Protocol Step Name</em>}</li>
 *   <li>{@link Job#getPrevious <em>Previous</em>}</li>
 *   <li>{@link Job#getNext <em>Next</em>}</li>
 * </ul>
 *
 * @see JobCollectionPackage#getJob()
 * @model abstract="true"
 * @generated
 */
public interface Job extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link JobStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see JobStatus
	 * @see #setState(JobStatus)
	 * @see JobCollectionPackage#getJob_State()
	 * @model required="true"
	 * @generated
	 */
	JobStatus getState();

	/**
	 * Sets the value of the '{@link Job#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see JobStatus
	 * @see #getState()
	 * @generated
	 */
	void setState(JobStatus value);

	/**
	 * Returns the value of the '<em><b>Protocol Step Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Protocol Step Name</em>' attribute.
	 * @see #setProtocolStepName(String)
	 * @see JobCollectionPackage#getJob_ProtocolStepName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getProtocolStepName();

	/**
	 * Sets the value of the '{@link Job#getProtocolStepName <em>Protocol Step Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Protocol Step Name</em>' attribute.
	 * @see #getProtocolStepName()
	 * @generated
	 */
	void setProtocolStepName(String value);

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' reference list.
	 * The list contents are of type {@link Job}.
	 * It is bidirectional and its opposite is '{@link Job#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous</em>' reference list.
	 * @see JobCollectionPackage#getJob_Previous()
	 * @see Job#getNext
	 * @model opposite="next"
	 * @generated
	 */
	EList<Job> getPrevious();

	/**
	 * Returns the value of the '<em><b>Next</b></em>' reference list.
	 * The list contents are of type {@link Job}.
	 * It is bidirectional and its opposite is '{@link Job#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' reference list.
	 * @see JobCollectionPackage#getJob_Next()
	 * @see Job#getPrevious
	 * @model opposite="previous"
	 * @generated
	 */
	EList<Job> getNext();

} // Job

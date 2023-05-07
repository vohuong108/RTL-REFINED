/**
 */
package jobCollection;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Incubate Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link IncubateJob#getMicroplate <em>Microplate</em>}</li>
 *   <li>{@link IncubateJob#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link IncubateJob#getDuration <em>Duration</em>}</li>
 * </ul>
 *
 * @see JobCollectionPackage#getIncubateJob()
 * @model
 * @generated
 */
public interface IncubateJob extends Job {
	/**
	 * Returns the value of the '<em><b>Microplate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Microplate</em>' reference.
	 * @see #setMicroplate(Microplate)
	 * @see JobCollectionPackage#getIncubateJob_Microplate()
	 * @model required="true"
	 * @generated
	 */
	Microplate getMicroplate();

	/**
	 * Sets the value of the '{@link IncubateJob#getMicroplate <em>Microplate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Microplate</em>' reference.
	 * @see #getMicroplate()
	 * @generated
	 */
	void setMicroplate(Microplate value);

	/**
	 * Returns the value of the '<em><b>Temperature</b></em>' attribute.
	 * The default value is <code>"293.15"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temperature</em>' attribute.
	 * @see #setTemperature(double)
	 * @see JobCollectionPackage#getIncubateJob_Temperature()
	 * @model default="293.15" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getTemperature();

	/**
	 * Sets the value of the '{@link IncubateJob#getTemperature <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temperature</em>' attribute.
	 * @see #getTemperature()
	 * @generated
	 */
	void setTemperature(double value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(int)
	 * @see JobCollectionPackage#getIncubateJob_Duration()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 * @generated
	 */
	int getDuration();

	/**
	 * Sets the value of the '{@link IncubateJob#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(int value);

} // IncubateJob

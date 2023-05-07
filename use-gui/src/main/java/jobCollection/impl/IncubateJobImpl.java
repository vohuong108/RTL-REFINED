/**
 */
package jobCollection.impl;

import jobCollection.*;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Incubate Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link IncubateJobImpl#getMicroplate <em>Microplate</em>}</li>
 *   <li>{@link IncubateJobImpl#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link IncubateJobImpl#getDuration <em>Duration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IncubateJobImpl extends JobImpl implements IncubateJob {
	/**
	 * The cached value of the '{@link #getMicroplate() <em>Microplate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMicroplate()
	 * @generated
	 * @ordered
	 */
	protected Microplate microplate;

	/**
	 * The default value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected static final double TEMPERATURE_EDEFAULT = 293.15;

	/**
	 * The cached value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected double temperature = TEMPERATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int DURATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected int duration = DURATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IncubateJobImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JobCollectionPackage.Literals.INCUBATE_JOB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Microplate getMicroplate() {
		if (microplate != null && microplate.eIsProxy()) {
			InternalEObject oldMicroplate = (InternalEObject)microplate;
			microplate = (Microplate)eResolveProxy(oldMicroplate);
			if (microplate != oldMicroplate) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JobCollectionPackage.INCUBATE_JOB__MICROPLATE, oldMicroplate, microplate));
			}
		}
		return microplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Microplate basicGetMicroplate() {
		return microplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMicroplate(Microplate newMicroplate) {
		Microplate oldMicroplate = microplate;
		microplate = newMicroplate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.INCUBATE_JOB__MICROPLATE, oldMicroplate, microplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemperature(double newTemperature) {
		double oldTemperature = temperature;
		temperature = newTemperature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.INCUBATE_JOB__TEMPERATURE, oldTemperature, temperature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(int newDuration) {
		int oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.INCUBATE_JOB__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JobCollectionPackage.INCUBATE_JOB__STATE:
				return getState();
			case JobCollectionPackage.INCUBATE_JOB__PROTOCOL_STEP_NAME:
				return getProtocolStepName();
			case JobCollectionPackage.INCUBATE_JOB__PREVIOUS:
				return getPrevious();
			case JobCollectionPackage.INCUBATE_JOB__NEXT:
				return getNext();
			case JobCollectionPackage.INCUBATE_JOB__MICROPLATE:
				if (resolve) return getMicroplate();
				return basicGetMicroplate();
			case JobCollectionPackage.INCUBATE_JOB__TEMPERATURE:
				return getTemperature();
			case JobCollectionPackage.INCUBATE_JOB__DURATION:
				return getDuration();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JobCollectionPackage.INCUBATE_JOB__STATE:
				setState((JobStatus)newValue);
				return;
			case JobCollectionPackage.INCUBATE_JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName((String)newValue);
				return;
			case JobCollectionPackage.INCUBATE_JOB__PREVIOUS:
				getPrevious().clear();
				getPrevious().addAll((Collection<? extends Job>)newValue);
				return;
			case JobCollectionPackage.INCUBATE_JOB__NEXT:
				getNext().clear();
				getNext().addAll((Collection<? extends Job>)newValue);
				return;
			case JobCollectionPackage.INCUBATE_JOB__MICROPLATE:
				setMicroplate((Microplate)newValue);
				return;
			case JobCollectionPackage.INCUBATE_JOB__TEMPERATURE:
				setTemperature((Double)newValue);
				return;
			case JobCollectionPackage.INCUBATE_JOB__DURATION:
				setDuration((Integer)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JobCollectionPackage.INCUBATE_JOB__STATE:
				setState(STATE_EDEFAULT);
				return;
			case JobCollectionPackage.INCUBATE_JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName(PROTOCOL_STEP_NAME_EDEFAULT);
				return;
			case JobCollectionPackage.INCUBATE_JOB__PREVIOUS:
				getPrevious().clear();
				return;
			case JobCollectionPackage.INCUBATE_JOB__NEXT:
				getNext().clear();
				return;
			case JobCollectionPackage.INCUBATE_JOB__MICROPLATE:
				setMicroplate((Microplate)null);
				return;
			case JobCollectionPackage.INCUBATE_JOB__TEMPERATURE:
				setTemperature(TEMPERATURE_EDEFAULT);
				return;
			case JobCollectionPackage.INCUBATE_JOB__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JobCollectionPackage.INCUBATE_JOB__STATE:
				return state != STATE_EDEFAULT;
			case JobCollectionPackage.INCUBATE_JOB__PROTOCOL_STEP_NAME:
				return PROTOCOL_STEP_NAME_EDEFAULT == null ? protocolStepName != null : !PROTOCOL_STEP_NAME_EDEFAULT.equals(protocolStepName);
			case JobCollectionPackage.INCUBATE_JOB__PREVIOUS:
				return previous != null && !previous.isEmpty();
			case JobCollectionPackage.INCUBATE_JOB__NEXT:
				return next != null && !next.isEmpty();
			case JobCollectionPackage.INCUBATE_JOB__MICROPLATE:
				return microplate != null;
			case JobCollectionPackage.INCUBATE_JOB__TEMPERATURE:
				return temperature != TEMPERATURE_EDEFAULT;
			case JobCollectionPackage.INCUBATE_JOB__DURATION:
				return duration != DURATION_EDEFAULT;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (temperature: ");
		result.append(temperature);
		result.append(", duration: ");
		result.append(duration);
		result.append(')');
		return result.toString();
	}

} //IncubateJobImpl

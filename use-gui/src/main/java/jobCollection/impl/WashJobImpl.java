/**
 */
package jobCollection.impl;

import jobCollection.*;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wash Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link WashJobImpl#getMicroplate <em>Microplate</em>}</li>
 *   <li>{@link WashJobImpl#getCavities <em>Cavities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WashJobImpl extends JobImpl implements WashJob {
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
	 * The cached value of the '{@link #getCavities() <em>Cavities</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCavities()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> cavities;

	protected String cavitiesFlat;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WashJobImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JobCollectionPackage.Literals.WASH_JOB;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JobCollectionPackage.WASH_JOB__MICROPLATE, oldMicroplate, microplate));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.WASH_JOB__MICROPLATE, oldMicroplate, microplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getCavities() {
		if (cavities == null) {
			cavities = new EDataTypeUniqueEList<Integer>(Integer.class, this, JobCollectionPackage.WASH_JOB__CAVITIES);
		}
		return cavities;
	}

	public String getCavitiesFlat() {
		return cavitiesFlat;
	}

	public void setCavitiesFlat(String newCavitiesFlat) {
		String old = cavitiesFlat;
		cavitiesFlat = newCavitiesFlat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this, Notification.SET, JobCollectionPackage.WASH_JOB__CAVITIESFLAT, old, cavitiesFlat));

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JobCollectionPackage.WASH_JOB__STATE:
				return getState();
			case JobCollectionPackage.WASH_JOB__PROTOCOL_STEP_NAME:
				return getProtocolStepName();
			case JobCollectionPackage.WASH_JOB__PREVIOUS:
				return getPrevious();
			case JobCollectionPackage.WASH_JOB__NEXT:
				return getNext();
			case JobCollectionPackage.WASH_JOB__MICROPLATE:
				if (resolve) return getMicroplate();
				return basicGetMicroplate();
			case JobCollectionPackage.WASH_JOB__CAVITIES:
				return getCavities();
			case JobCollectionPackage.WASH_JOB__CAVITIESFLAT:
				return getCavitiesFlat();
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
			case JobCollectionPackage.WASH_JOB__STATE:
				setState((JobStatus)newValue);
				return;
			case JobCollectionPackage.WASH_JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName((String)newValue);
				return;
			case JobCollectionPackage.WASH_JOB__PREVIOUS:
				getPrevious().clear();
				getPrevious().addAll((Collection<? extends Job>)newValue);
				return;
			case JobCollectionPackage.WASH_JOB__NEXT:
				getNext().clear();
				getNext().addAll((Collection<? extends Job>)newValue);
				return;
			case JobCollectionPackage.WASH_JOB__MICROPLATE:
				setMicroplate((Microplate)newValue);
				return;
			case JobCollectionPackage.WASH_JOB__CAVITIES:
				getCavities().clear();
				getCavities().addAll((Collection<? extends Integer>)newValue);
				return;
			case JobCollectionPackage.WASH_JOB__CAVITIESFLAT:
				setCavitiesFlat((String) newValue);
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
			case JobCollectionPackage.WASH_JOB__STATE:
				setState(STATE_EDEFAULT);
				return;
			case JobCollectionPackage.WASH_JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName(PROTOCOL_STEP_NAME_EDEFAULT);
				return;
			case JobCollectionPackage.WASH_JOB__PREVIOUS:
				getPrevious().clear();
				return;
			case JobCollectionPackage.WASH_JOB__NEXT:
				getNext().clear();
				return;
			case JobCollectionPackage.WASH_JOB__MICROPLATE:
				setMicroplate((Microplate)null);
				return;
			case JobCollectionPackage.WASH_JOB__CAVITIES:
				getCavities().clear();
				return;
			case JobCollectionPackage.WASH_JOB__CAVITIESFLAT:
				setCavitiesFlat(null);
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
			case JobCollectionPackage.WASH_JOB__STATE:
				return state != STATE_EDEFAULT;
			case JobCollectionPackage.WASH_JOB__PROTOCOL_STEP_NAME:
				return PROTOCOL_STEP_NAME_EDEFAULT == null ? protocolStepName != null : !PROTOCOL_STEP_NAME_EDEFAULT.equals(protocolStepName);
			case JobCollectionPackage.WASH_JOB__PREVIOUS:
				return previous != null && !previous.isEmpty();
			case JobCollectionPackage.WASH_JOB__NEXT:
				return next != null && !next.isEmpty();
			case JobCollectionPackage.WASH_JOB__MICROPLATE:
				return microplate != null;
			case JobCollectionPackage.WASH_JOB__CAVITIES:
				return cavities != null && !cavities.isEmpty();
			case JobCollectionPackage.WASH_JOB__CAVITIESFLAT:
				return PROTOCOL_STEP_NAME_EDEFAULT == null ? cavitiesFlat != null : !PROTOCOL_STEP_NAME_EDEFAULT.equals(cavitiesFlat);
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
		result.append(" (cavities: ");
		result.append(cavities);
		result.append(')');
		return result.toString();
	}

} //WashJobImpl

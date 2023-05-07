/**
 */
package jobCollection.impl;

import jobCollection.Job;
import jobCollection.JobCollectionPackage;
import jobCollection.JobStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link JobImpl#getState <em>State</em>}</li>
 *   <li>{@link JobImpl#getProtocolStepName <em>Protocol Step Name</em>}</li>
 *   <li>{@link JobImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link JobImpl#getNext <em>Next</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class JobImpl extends MinimalEObjectImpl.Container implements Job {
	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final JobStatus STATE_EDEFAULT = JobStatus.PLANNED;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected JobStatus state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProtocolStepName() <em>Protocol Step Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtocolStepName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROTOCOL_STEP_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProtocolStepName() <em>Protocol Step Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtocolStepName()
	 * @generated
	 * @ordered
	 */
	protected String protocolStepName = PROTOCOL_STEP_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrevious() <em>Previous</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrevious()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> previous;

	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> next;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JobImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JobCollectionPackage.Literals.JOB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobStatus getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(JobStatus newState) {
		JobStatus oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.JOB__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProtocolStepName() {
		return protocolStepName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProtocolStepName(String newProtocolStepName) {
		String oldProtocolStepName = protocolStepName;
		protocolStepName = newProtocolStepName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.JOB__PROTOCOL_STEP_NAME, oldProtocolStepName, protocolStepName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Job> getPrevious() {
		if (previous == null) {
			previous = new EObjectWithInverseResolvingEList.ManyInverse<Job>(Job.class, this, JobCollectionPackage.JOB__PREVIOUS, JobCollectionPackage.JOB__NEXT);
		}
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Job> getNext() {
		if (next == null) {
			next = new EObjectWithInverseResolvingEList.ManyInverse<Job>(Job.class, this, JobCollectionPackage.JOB__NEXT, JobCollectionPackage.JOB__PREVIOUS);
		}
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JobCollectionPackage.JOB__PREVIOUS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPrevious()).basicAdd(otherEnd, msgs);
			case JobCollectionPackage.JOB__NEXT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNext()).basicAdd(otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JobCollectionPackage.JOB__PREVIOUS:
				return ((InternalEList<?>)getPrevious()).basicRemove(otherEnd, msgs);
			case JobCollectionPackage.JOB__NEXT:
				return ((InternalEList<?>)getNext()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JobCollectionPackage.JOB__STATE:
				return getState();
			case JobCollectionPackage.JOB__PROTOCOL_STEP_NAME:
				return getProtocolStepName();
			case JobCollectionPackage.JOB__PREVIOUS:
				return getPrevious();
			case JobCollectionPackage.JOB__NEXT:
				return getNext();
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
			case JobCollectionPackage.JOB__STATE:
				setState((JobStatus)newValue);
				return;
			case JobCollectionPackage.JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName((String)newValue);
				return;
			case JobCollectionPackage.JOB__PREVIOUS:
				getPrevious().clear();
				getPrevious().addAll((Collection<? extends Job>)newValue);
				return;
			case JobCollectionPackage.JOB__NEXT:
				getNext().clear();
				getNext().addAll((Collection<? extends Job>)newValue);
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
			case JobCollectionPackage.JOB__STATE:
				setState(STATE_EDEFAULT);
				return;
			case JobCollectionPackage.JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName(PROTOCOL_STEP_NAME_EDEFAULT);
				return;
			case JobCollectionPackage.JOB__PREVIOUS:
				getPrevious().clear();
				return;
			case JobCollectionPackage.JOB__NEXT:
				getNext().clear();
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
			case JobCollectionPackage.JOB__STATE:
				return state != STATE_EDEFAULT;
			case JobCollectionPackage.JOB__PROTOCOL_STEP_NAME:
				return PROTOCOL_STEP_NAME_EDEFAULT == null ? protocolStepName != null : !PROTOCOL_STEP_NAME_EDEFAULT.equals(protocolStepName);
			case JobCollectionPackage.JOB__PREVIOUS:
				return previous != null && !previous.isEmpty();
			case JobCollectionPackage.JOB__NEXT:
				return next != null && !next.isEmpty();
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
		result.append(" (state: ");
		result.append(state);
		result.append(", protocolStepName: ");
		result.append(protocolStepName);
		result.append(')');
		return result.toString();
	}

} //JobImpl

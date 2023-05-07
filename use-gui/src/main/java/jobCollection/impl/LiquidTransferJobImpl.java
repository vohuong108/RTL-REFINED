/**
 */
package jobCollection.impl;

import jobCollection.*;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Liquid Transfer Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link LiquidTransferJobImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link LiquidTransferJobImpl#getTips <em>Tips</em>}</li>
 *   <li>{@link LiquidTransferJobImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LiquidTransferJobImpl extends JobImpl implements LiquidTransferJob {
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Labware target;

	/**
	 * The cached value of the '{@link #getTips() <em>Tips</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTips()
	 * @generated
	 * @ordered
	 */
	protected EList<TipLiquidTransfer> tips;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Labware source;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LiquidTransferJobImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JobCollectionPackage.Literals.LIQUID_TRANSFER_JOB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labware getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Labware)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JobCollectionPackage.LIQUID_TRANSFER_JOB__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labware basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Labware newTarget) {
		Labware oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.LIQUID_TRANSFER_JOB__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TipLiquidTransfer> getTips() {
		if (tips == null) {
			tips = new EObjectContainmentEList<TipLiquidTransfer>(TipLiquidTransfer.class, this, JobCollectionPackage.LIQUID_TRANSFER_JOB__TIPS);
		}
		return tips;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labware getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Labware)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JobCollectionPackage.LIQUID_TRANSFER_JOB__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labware basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Labware newSource) {
		Labware oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.LIQUID_TRANSFER_JOB__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PREVIOUS:
				return ((InternalEList<?>)getPrevious()).basicRemove(otherEnd, msgs);
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__NEXT:
				return ((InternalEList<?>)getNext()).basicRemove(otherEnd, msgs);
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TIPS:
				return ((InternalEList<?>)getTips()).basicRemove(otherEnd, msgs);
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
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__STATE:
				return getState();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PROTOCOL_STEP_NAME:
				return getProtocolStepName();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PREVIOUS:
				return getPrevious();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__NEXT:
				return getNext();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TIPS:
				return getTips();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
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
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__STATE:
				setState((JobStatus)newValue);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName((String)newValue);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PREVIOUS:
				getPrevious().clear();
				getPrevious().addAll((Collection<? extends Job>)newValue);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__NEXT:
				getNext().clear();
				getNext().addAll((Collection<? extends Job>)newValue);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TARGET:
				setTarget((Labware)newValue);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TIPS:
				getTips().clear();
				getTips().addAll((Collection<? extends TipLiquidTransfer>)newValue);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__SOURCE:
				setSource((Labware)newValue);
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
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__STATE:
				setState(STATE_EDEFAULT);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PROTOCOL_STEP_NAME:
				setProtocolStepName(PROTOCOL_STEP_NAME_EDEFAULT);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PREVIOUS:
				getPrevious().clear();
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__NEXT:
				getNext().clear();
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TARGET:
				setTarget((Labware)null);
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TIPS:
				getTips().clear();
				return;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__SOURCE:
				setSource((Labware)null);
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
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__STATE:
				return state != STATE_EDEFAULT;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PROTOCOL_STEP_NAME:
				return PROTOCOL_STEP_NAME_EDEFAULT == null ? protocolStepName != null : !PROTOCOL_STEP_NAME_EDEFAULT.equals(protocolStepName);
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__PREVIOUS:
				return previous != null && !previous.isEmpty();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__NEXT:
				return next != null && !next.isEmpty();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TARGET:
				return target != null;
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__TIPS:
				return tips != null && !tips.isEmpty();
			case JobCollectionPackage.LIQUID_TRANSFER_JOB__SOURCE:
				return source != null;
		}
		return eDynamicIsSet(featureID);
	}

} //LiquidTransferJobImpl

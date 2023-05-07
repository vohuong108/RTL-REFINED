/**
 */
package jobCollection.impl;

import jobCollection.JobCollectionPackage;
import jobCollection.JobStatus;
import jobCollection.TipLiquidTransfer;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tip Liquid Transfer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link TipLiquidTransferImpl#getSourceCavityIndex <em>Source Cavity Index</em>}</li>
 *   <li>{@link TipLiquidTransferImpl#getVolume <em>Volume</em>}</li>
 *   <li>{@link TipLiquidTransferImpl#getTargetCavityIndex <em>Target Cavity Index</em>}</li>
 *   <li>{@link TipLiquidTransferImpl#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TipLiquidTransferImpl extends MinimalEObjectImpl.Container implements TipLiquidTransfer {
	/**
	 * The default value of the '{@link #getSourceCavityIndex() <em>Source Cavity Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceCavityIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int SOURCE_CAVITY_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSourceCavityIndex() <em>Source Cavity Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceCavityIndex()
	 * @generated
	 * @ordered
	 */
	protected int sourceCavityIndex = SOURCE_CAVITY_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getVolume() <em>Volume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolume()
	 * @generated
	 * @ordered
	 */
	protected static final double VOLUME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getVolume() <em>Volume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolume()
	 * @generated
	 * @ordered
	 */
	protected double volume = VOLUME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetCavityIndex() <em>Target Cavity Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetCavityIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int TARGET_CAVITY_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTargetCavityIndex() <em>Target Cavity Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetCavityIndex()
	 * @generated
	 * @ordered
	 */
	protected int targetCavityIndex = TARGET_CAVITY_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final JobStatus STATUS_EDEFAULT = JobStatus.PLANNED;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected JobStatus status = STATUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TipLiquidTransferImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JobCollectionPackage.Literals.TIP_LIQUID_TRANSFER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSourceCavityIndex() {
		return sourceCavityIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceCavityIndex(int newSourceCavityIndex) {
		int oldSourceCavityIndex = sourceCavityIndex;
		sourceCavityIndex = newSourceCavityIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX, oldSourceCavityIndex, sourceCavityIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVolume(double newVolume) {
		double oldVolume = volume;
		volume = newVolume;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.TIP_LIQUID_TRANSFER__VOLUME, oldVolume, volume));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTargetCavityIndex() {
		return targetCavityIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetCavityIndex(int newTargetCavityIndex) {
		int oldTargetCavityIndex = targetCavityIndex;
		targetCavityIndex = newTargetCavityIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX, oldTargetCavityIndex, targetCavityIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobStatus getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(JobStatus newStatus) {
		JobStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JobCollectionPackage.TIP_LIQUID_TRANSFER__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX:
				return getSourceCavityIndex();
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__VOLUME:
				return getVolume();
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX:
				return getTargetCavityIndex();
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__STATUS:
				return getStatus();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX:
				setSourceCavityIndex((Integer)newValue);
				return;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__VOLUME:
				setVolume((Double)newValue);
				return;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX:
				setTargetCavityIndex((Integer)newValue);
				return;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__STATUS:
				setStatus((JobStatus)newValue);
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
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX:
				setSourceCavityIndex(SOURCE_CAVITY_INDEX_EDEFAULT);
				return;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__VOLUME:
				setVolume(VOLUME_EDEFAULT);
				return;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX:
				setTargetCavityIndex(TARGET_CAVITY_INDEX_EDEFAULT);
				return;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__STATUS:
				setStatus(STATUS_EDEFAULT);
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
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__SOURCE_CAVITY_INDEX:
				return sourceCavityIndex != SOURCE_CAVITY_INDEX_EDEFAULT;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__VOLUME:
				return volume != VOLUME_EDEFAULT;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__TARGET_CAVITY_INDEX:
				return targetCavityIndex != TARGET_CAVITY_INDEX_EDEFAULT;
			case JobCollectionPackage.TIP_LIQUID_TRANSFER__STATUS:
				return status != STATUS_EDEFAULT;
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
		result.append(" (sourceCavityIndex: ");
		result.append(sourceCavityIndex);
		result.append(", volume: ");
		result.append(volume);
		result.append(", targetCavityIndex: ");
		result.append(targetCavityIndex);
		result.append(", status: ");
		result.append(status);
		result.append(')');
		return result.toString();
	}

} //TipLiquidTransferImpl

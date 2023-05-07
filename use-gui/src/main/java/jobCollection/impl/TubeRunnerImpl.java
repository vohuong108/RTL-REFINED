/**
 */
package jobCollection.impl;

import jobCollection.JobCollectionPackage;
import jobCollection.TubeRunner;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tube Runner</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link TubeRunnerImpl#getBarcodes <em>Barcodes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TubeRunnerImpl extends LabwareImpl implements TubeRunner {
	/**
	 * The cached value of the '{@link #getBarcodes() <em>Barcodes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBarcodes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> barcodes;
	protected String barcodesFlat = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TubeRunnerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JobCollectionPackage.Literals.TUBE_RUNNER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getBarcodes() {
		if (barcodes == null) {
			barcodes = new EDataTypeUniqueEList<String>(String.class, this, JobCollectionPackage.TUBE_RUNNER__BARCODES);
		}
		return barcodes;
	}

	@Override
	public void setBarcodesFlat(String newBarcodesFlat) {
		String old = barcodesFlat;
		barcodesFlat = newBarcodesFlat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this, Notification.SET, JobCollectionPackage.TUBE_RUNNER__BARCODESFLAT, old, barcodesFlat));
	}

	@Override
	public String getBarcodesFlat() {
		return barcodesFlat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JobCollectionPackage.TUBE_RUNNER__NAME:
				return getName();
			case JobCollectionPackage.TUBE_RUNNER__BARCODESFLAT:
				return getBarcodesFlat();
			case JobCollectionPackage.TUBE_RUNNER__BARCODES:
				return getBarcodes();
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
			case JobCollectionPackage.TUBE_RUNNER__NAME:
				setName((String)newValue);
				return;
			case JobCollectionPackage.TUBE_RUNNER__BARCODESFLAT:
				setBarcodesFlat((String)newValue);
				return;
			case JobCollectionPackage.TUBE_RUNNER__BARCODES:
				getBarcodes().clear();
				getBarcodes().addAll((Collection<? extends String>)newValue);
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
			case JobCollectionPackage.TUBE_RUNNER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JobCollectionPackage.TUBE_RUNNER__BARCODESFLAT:
				setBarcodesFlat(null);
				return;
			case JobCollectionPackage.TUBE_RUNNER__BARCODES:
				getBarcodes().clear();
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
			case JobCollectionPackage.TUBE_RUNNER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JobCollectionPackage.TUBE_RUNNER__BARCODESFLAT:
				return NAME_EDEFAULT == null ? barcodesFlat != null : !NAME_EDEFAULT.equals(barcodesFlat);
			case JobCollectionPackage.TUBE_RUNNER__BARCODES:
				return barcodes != null && !barcodes.isEmpty();
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
		result.append(" (barcodes: ");
		result.append(barcodes);
		result.append(')');
		return result.toString();
	}

} //TubeRunnerImpl

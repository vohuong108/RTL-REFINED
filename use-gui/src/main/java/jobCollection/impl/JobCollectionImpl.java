/**
 */
package jobCollection.impl;

import jobCollection.Job;
import jobCollection.JobCollection;
import jobCollection.JobCollectionPackage;
import jobCollection.Labware;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Job Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link JobCollectionImpl#getLabware <em>Labware</em>}</li>
 *   <li>{@link JobCollectionImpl#getJobs <em>Jobs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JobCollectionImpl extends MinimalEObjectImpl.Container implements JobCollection {
	/**
	 * The cached value of the '{@link #getLabware() <em>Labware</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabware()
	 * @generated
	 * @ordered
	 */
	protected EList<Labware> labware;

	/**
	 * The cached value of the '{@link #getJobs() <em>Jobs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobs()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> jobs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JobCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JobCollectionPackage.Literals.JOB_COLLECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Labware> getLabware() {
		if (labware == null) {
			labware = new EObjectContainmentEList<Labware>(Labware.class, this, JobCollectionPackage.JOB_COLLECTION__LABWARE);
		}
		return labware;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Job> getJobs() {
		if (jobs == null) {
			jobs = new EObjectContainmentEList<Job>(Job.class, this, JobCollectionPackage.JOB_COLLECTION__JOBS);
		}
		return jobs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JobCollectionPackage.JOB_COLLECTION__LABWARE:
				return ((InternalEList<?>)getLabware()).basicRemove(otherEnd, msgs);
			case JobCollectionPackage.JOB_COLLECTION__JOBS:
				return ((InternalEList<?>)getJobs()).basicRemove(otherEnd, msgs);
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
			case JobCollectionPackage.JOB_COLLECTION__LABWARE:
				return getLabware();
			case JobCollectionPackage.JOB_COLLECTION__JOBS:
				return getJobs();
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
			case JobCollectionPackage.JOB_COLLECTION__LABWARE:
				getLabware().clear();
				getLabware().addAll((Collection<? extends Labware>)newValue);
				return;
			case JobCollectionPackage.JOB_COLLECTION__JOBS:
				getJobs().clear();
				getJobs().addAll((Collection<? extends Job>)newValue);
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
			case JobCollectionPackage.JOB_COLLECTION__LABWARE:
				getLabware().clear();
				return;
			case JobCollectionPackage.JOB_COLLECTION__JOBS:
				getJobs().clear();
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
			case JobCollectionPackage.JOB_COLLECTION__LABWARE:
				return labware != null && !labware.isEmpty();
			case JobCollectionPackage.JOB_COLLECTION__JOBS:
				return jobs != null && !jobs.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

} //JobCollectionImpl

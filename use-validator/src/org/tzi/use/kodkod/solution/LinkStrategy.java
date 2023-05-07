package org.tzi.use.kodkod.solution;

import java.util.Map;

import kodkod.instance.Tuple;

import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.sys.MLinkSet;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MObjectState;
import org.tzi.use.uml.sys.MSystemState;

/**
 * Strategy for the creation of links for an assocation.
 * 
 * @author Hendrik Reitmann
 * 
 */
public class LinkStrategy extends ElementStrategy {

	private MAssociation mAssociation;

	public LinkStrategy(UseSystemApi sApi, Map<String, MObjectState> objectStates, String associationName) {
		super(sApi, objectStates);
		mAssociation = mModel.getAssociation(associationName);
	}

	@Override
	public boolean canDo() {
		return mAssociation != null && !mAssociation.isDerived();
	}

	@Override
	public void createElement(Tuple currentTuple) throws UseApiException {
		MObject[] tupleObjects = new MObject[currentTuple.arity()];
		Object atom;
		for (int i = 0; i < currentTuple.arity(); i++) {
			atom = currentTuple.atom(i);
			if(atom != TypeConstants.UNDEFINED){
				tupleObjects[i] = objectStates.get(currentTuple.atom(i)).object();
			}
			else{
				return;
			}
		}
		
		// hanhdd-begin ==================================
		//System.out.println("hanhdd@association.name = " + mAssociation.name());
		MClass[] mClasses= new MClass[tupleObjects.length];
		for(int i = 0; i < tupleObjects.length; i++) {
			mClasses[i] = tupleObjects[i].cls();
			//System.out.println("hanhdd@connectedObject=" + tupleObjects[i].name() + "  class="+ mClasses[i].name());
		}
		for(MAssociation assoc: mAssociation.getRedefinedByClosure() ) {
			//System.out.println("hanhdd@assoc.name = " + assoc.name() );
			//System.out.println("hanhdd@assoc.isAssignableFrom = " + assoc.isAssignableFrom(mClasses) );
			if( assoc.isAssignableFrom(mClasses) ) {
				mAssociation = assoc;
				break;
			}
		}
		
		MSystemState sysState = systemApi.getSystem().state();
		MLinkSet linkSet = sysState.linksOfAssociation(mAssociation);
		if( !linkSet.hasLinkBetweenObjects(tupleObjects) && mAssociation.isAssignableFrom(mClasses) ) {
			systemApi.createLinkEx(mAssociation, tupleObjects);
		}
		//systemApi.createLinkEx(mAssociation, tupleObjects);
		//hanhdd-end ====================================
	}

}

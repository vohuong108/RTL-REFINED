package org.tzi.kodkod.clever.mmodel2imodel;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.uml.mm.MModel;

/**
 * Exception for the case that in an {@link IModel} a property of a
 * {@link MModel} can not be found.
 * 
 * @author Jan Prien
 *
 */
public final class NoMappingFoundException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -2758226117306291144L;

}

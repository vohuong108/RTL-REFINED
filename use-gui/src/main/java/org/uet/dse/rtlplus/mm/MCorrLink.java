package org.uet.dse.rtlplus.mm;

import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;

public class MCorrLink {
	
	public MObject getObj() {
		return obj;
	}

	public MLink getSrcLink() {
		return srcLink;
	}

	public MLink getTrgLink() {
		return trgLink;
	}

	private MObject obj;
	private MLink srcLink;
	private MLink trgLink;

	public MCorrLink(MObject corrObj, MLink srcAndCorrLink, MLink trgAndCorrLink) {
		obj = corrObj;
		srcLink = srcAndCorrLink;
		trgLink = trgAndCorrLink;
	}

}

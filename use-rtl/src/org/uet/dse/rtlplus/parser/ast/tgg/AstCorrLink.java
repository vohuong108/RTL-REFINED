package org.uet.dse.rtlplus.parser.ast.tgg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tzi.use.uml.mm.MAggregationKind;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.mm.MInvalidModelException;
import org.tzi.use.uml.mm.MModel;
import org.tzi.use.uml.mm.MMultiplicity;
import org.tzi.use.uml.mm.ModelFactory;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.uml.sys.MSystemException;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.mm.MCorrLink;
import org.uet.dse.rtlplus.parser.Context;

public class AstCorrLink {

	private String sourceClass;
	private String targetClass;
	private String sourceObject;
	private String targetObject;
	private String name;
	private String role1;
	private String role2;
	private String className;
	private boolean multipleSource = false;
	private boolean multipleTarget = false;

	public String getSourceClass() {
		return sourceClass;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public String getSourceObject() {
		return sourceObject;
	}

	public String getTargetObject() {
		return targetObject;
	}

	public String getName() {
		return name;
	}

	public String getRole1() {
		return role1;
	}

	public String getRole2() {
		return role2;
	}

	public String getClassName() {
		return className;
	}

	public void setSourceClass(Object object) {
		sourceClass = (String) object;
	}

	public void setTargetClass(Object object) {
		targetClass = (String) object;
	}

	public void setSourceObject(Object object) {
		sourceObject = (String) object;
	}

	public void setTargetObject(Object object) {
		targetObject = (String) object;
	}

	public void setName(Object object) {
		name = (String) object;
	}

	public void setRoleNames(Object object, Object object2) {
		role1 = (String) object;
		role2 = (String) object2;
	}

	public void setClass(Object object) {
		className = (String) object;
	}

	public void setMultipleSource() {
		multipleSource = true;
	}

	public void setMultipleTarget() {
		multipleTarget = true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		if (sourceClass != null)
			sb.append('(').append(sourceClass).append(") ");
		sb.append(sourceObject);
		if (multipleSource)
			sb.append('*');
		sb.append(',');
		if (targetClass != null)
			sb.append('(').append(targetClass).append(") ");
		sb.append(targetObject);
		if (multipleTarget)
			sb.append('*');
		sb.append(") ");
		if (role1 != null) {
			sb.append("as ").append('(').append(role1).append(',').append(role2).append(") ");
		}
		sb.append("in ").append(className).append(':').append(className);
		return sb.toString();
	}

	public MCorrLink gen(Context ctx) throws MInvalidModelException, MSystemException {
		MModel model = ctx.getDiagramModel();
		MSystemState systemState = ctx.systemState();
		MObject srcObj = systemState.objectByName(sourceObject);
		MObject trgObj = systemState.objectByName(targetObject);
		MClass srcCls = model.getClass((sourceClass == null) ? srcObj.cls().name() : sourceClass);
		MClass trgCls = model.getClass((targetClass == null) ? trgObj.cls().name() : targetClass);
		// Create correlation class and correlation object
		MClass corrCls = model.getClass(className);
		if (corrCls == null) {
			corrCls = ctx.modelFactory().createClass(className, false);
			model.addClass(corrCls);
			ctx.addCorrClass(corrCls);
		}
		MObject corrObj = systemState.objectByName(name);
		if (corrObj == null)
			corrObj = systemState.createObject(corrCls, name);
		List<MAssociation> assocs = createAssociations(ctx, srcCls, trgCls, corrCls);
		// Need to create a new system state because the model has changed.
		// Using the old system state causes NPEs because the new link/obj map entries
		// are not there.
		MSystem newSystem = new MSystem(model);
		MSystemState newSS = newSystem.state();
		for (MObject obj : systemState.allObjects()) {
			newSS.createObject(obj.cls(), obj.name());
		}
		for (MLink lnk : systemState.allLinks()) {
			newSS.insertLink(lnk);
		}
		// Create link between source and correlation object
		List<MObject> srcAndCorr = Arrays.asList(srcObj, corrObj);
		MLink srcAndCorrLink;
		if (newSS.hasLink(assocs.get(0), srcAndCorr, null))
			srcAndCorrLink = newSS.linkBetweenObjects(assocs.get(0), srcAndCorr).iterator().next();
		else
			srcAndCorrLink = newSS.createLink(assocs.get(0), srcAndCorr, null);
		// Create link between target and correlation object
		MLink trgAndCorrLink;
		List<MObject> trgAndCorr = Arrays.asList(trgObj, corrObj);
		if (newSS.hasLink(assocs.get(1), trgAndCorr, null))
			trgAndCorrLink = newSS.linkBetweenObjects(assocs.get(1), trgAndCorr).iterator().next();
		else
			trgAndCorrLink = newSS.createLink(assocs.get(1), trgAndCorr, null);
		// Add link conditions if necessary
		if (srcLnkCons != null) {
			if (assocs.get(0).getAssociationEnd(corrCls, corrCls.name().toLowerCase())
					.multiplicity() == MMultiplicity.ZERO_ONE)
				srcLnkCons.add(sourceObject + "." + corrCls.name().toLowerCase() + "->size() = 0");
			if (assocs.get(1).getAssociationEnd(corrCls, corrCls.name().toLowerCase())
					.multiplicity() == MMultiplicity.ZERO_ONE)
				trgLnkCons.add(targetObject + "." + corrCls.name().toLowerCase() + "->size() = 0");
		}
		ctx.setSystemState(newSS);
		return new MCorrLink(corrObj, srcAndCorrLink, trgAndCorrLink);
	}

	private List<MAssociation> createAssociations(Context ctx, MClass src, MClass trg, MClass corr)
			throws MInvalidModelException {
		if (role1 == null)
			role1 = src.name().toLowerCase();
		if (role2 == null)
			role2 = trg.name().toLowerCase();
		Set<MClass> classes = new HashSet<>(2);
		classes.add(corr);
		classes.add(src);
		Set<MAssociation> assocs = ctx.getDiagramModel().getAssociationsBetweenClasses(classes);
		List<MAssociation> result = new ArrayList<>(2);
		if (assocs.isEmpty()) {
			ModelFactory mf = ctx.modelFactory();
			MAssociation ass = mf.createAssociation(corr.name() + "_" + src.name());
			ass.addAssociationEnd(
					mf.createAssociationEnd(src, role1, MMultiplicity.ONE, MAggregationKind.NONE, false, null));
			MMultiplicity mult = multipleSource ? MMultiplicity.ZERO_MANY : MMultiplicity.ZERO_ONE;
			ass.addAssociationEnd(
					mf.createAssociationEnd(corr, corr.name().toLowerCase(), mult, MAggregationKind.NONE, false, null));
			ctx.getDiagramModel().addAssociation(ass);
			result.add(ass);
			ctx.addCorrAssociation(ass);
		} else
			result.add(assocs.iterator().next());
		classes = new HashSet<>(2);
		classes.add(corr);
		classes.add(trg);
		assocs = ctx.getDiagramModel().getAssociationsBetweenClasses(classes);
		if (assocs.isEmpty()) {
			ModelFactory mf = ctx.modelFactory();
			MAssociation ass = mf.createAssociation(corr.name() + "_" + trg.name());
			ass.addAssociationEnd(
					mf.createAssociationEnd(trg, role2, MMultiplicity.ONE, MAggregationKind.NONE, false, null));
			MMultiplicity mult = multipleTarget ? MMultiplicity.ZERO_MANY : MMultiplicity.ZERO_ONE;
			ass.addAssociationEnd(
					mf.createAssociationEnd(corr, corr.name().toLowerCase(), mult, MAggregationKind.NONE, false, null));
			ctx.getDiagramModel().addAssociation(ass);
			result.add(ass);
			ctx.addCorrAssociation(ass);
		} else
			result.add(assocs.iterator().next());
		return result;
	}

	private List<String> srcLnkCons;
	private List<String> trgLnkCons;

	// Generate correlation link with conditions
	public MCorrLink gen(Context ctx, List<String> srcLnkCons, List<String> trgLnkCons)
			throws MInvalidModelException, MSystemException {
		this.srcLnkCons = srcLnkCons;
		this.trgLnkCons = trgLnkCons;
		return gen(ctx);
	}

}

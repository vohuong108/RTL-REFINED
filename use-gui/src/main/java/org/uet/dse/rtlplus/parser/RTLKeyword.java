package org.uet.dse.rtlplus.parser;

public class RTLKeyword {
	/**
	 * Keyword For USE Specification
	 */
	public static String model = "model";
	public static String startClass = "class";
	public static String endClass = "end";
	public static String startAttributes = "attributes";
	public static String startAssociation = "association";
	public static String startOperation = "operations";
	public static String endAssociation = "end";
	public static String between = "between";
	public static String self = "self";
	public static String startConstraint = "constraints";
	public static String endConstraint = "end";
	public static String role = "role";
	public static String indent = "    ";
	public static String context = "context";
	
	public static String multiplicity01 = "[0..1]";
	public static String multiplicity1n = "[1..*]";
	public static String multiplicity11 = "[1..1]";
	public static String multiplicity0n = "[0..*]";
	
	
	
	/**
	 * Keyword For TGGRule
	 */
	public static String transformation = "transformation";
	public static String direction = "direction";
	public static String startTGGRule = "rule";
	public static String endTGGRule = "end";
	public static String checkSource = "checkSource";
	public static String checkTarget = "checkTarget";
	public static String checkCorr = "checkCorr";
	
	/**
	 * Keyword When We Generate File
	 */
	public static String forwardTransform = "_forwTrafo";
	public static String backwardTransform = "_backTrafo";
	public static String coEvolution = "_coEvol";
	public static String integration = "_integration";
	public static String pre = "_pre:";
	public static String post = "_post:";
	public static String S_pre = "--S_precondition";
	public static String S_post = "--S_postcondition";
	public static String T_pre = "--T_precondition";
	public static String T_post = "--T_postcondition";
	public static String C_pre = "--C_precondition";
	public static String C_post = "--C_postcondition";
	
	public static String matchSL = "matchSL";
	public static String matchSR = "matchSR";
	public static String matchTL = "matchTL";
	public static String matchTR = "matchTR";
	public static String matchCL = "matchCL";
	public static String matchCR = "matchCR";
	public static String matchS = "matchS";
	public static String matchC = "matchC";
	public static String matchT = "matchT";
	
	
	
	public static String and = " and";
	public static String updatemap = "Update the mapped attributes by correspondence links";
	
	public static String indent(int number) {
		if(number <= 0)
			return "";
		String spaces = new String(new char[number]).replace('\0', ' ');
		return spaces;
	}
	
	public static String comment(int number, Character c) {
		if(number <= 0)
			return "";
		String spaces = new String(new char[number]).replace('\0', c);
		return spaces;
	}
}

package org.uet.dse.rtlplus.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.tzi.use.uml.ocl.value.Value;
import org.uet.dse.rtlplus.testing.Mapping.Pattern.PatternType;

public class Mapping {
	
	List<Pattern> lhs = new ArrayList<>();
	List<Pattern> rhs = new ArrayList<>();
	
	
	public static Mapping fromString (String str, int numLeftTerms, int numRightTerms) {
		Mapping mapping = new Mapping();
		String[] sides = str.split("->");
		if (sides.length != 2)
			return null;
		String[] left = sides[0].split(",");
		if (left.length == 0)
			return null;
		for (String p : left) {
			String pat = p.trim();
			if (pat.length() == 0)
				return null;
			else if (pat.equals("*"))
				mapping.lhs.add(new Pattern(PatternType.WILDCARD, "*"));
			else if (pat.charAt(0) == '!') 
				mapping.lhs.add(new Pattern(PatternType.NEGATIVE, pat.substring(1)));
			else mapping.lhs.add(new Pattern(PatternType.POSITIVE, pat));
		}
		String[] right = sides[1].split(",");
		if (right.length == 0)
			return null;
		for (String p : right) {
			String pat = p.trim();
			if (pat.length() == 0)
				return null;
			else if (pat.equals("*"))
				mapping.rhs.add(new Pattern(PatternType.WILDCARD, "*"));
			else if (pat.charAt(0) == '!') 
				mapping.rhs.add(new Pattern(PatternType.NEGATIVE, pat.substring(1)));
			else mapping.rhs.add(new Pattern(PatternType.POSITIVE, pat));
		}
		if (mapping.lhs.size() != numLeftTerms || mapping.rhs.size() != numRightTerms) {
			System.out.println("Size mismatch");
			return null;
		}
		return mapping;
	}
	
	public boolean matchLeft(List<Value> vals) {
		if (lhs.size() != vals.size())
			return false;
		boolean res = true;
		for (int i = 0; i < vals.size(); i++) {
			res &= lhs.get(i).match(vals.get(i));
		}
		return res;
	}
	
	public boolean matchRight(List<Value> vals) {
		if (rhs.size() != vals.size())
			return false;
		boolean res = true;
		for (int i = 0; i < vals.size(); i++) {
			res &= rhs.get(i).match(vals.get(i));
		}
		return res;
	}
	
	@Override
	public String toString() {
		return lhs.stream().map((it) -> it.toString()).collect(Collectors.joining(", "))
				+ " -> "
				+ rhs.stream().map((it) -> it.toString()).collect(Collectors.joining(", "));
	}
	
	static class Pattern {
		enum PatternType {
			POSITIVE, NEGATIVE, WILDCARD
		}
		
		private PatternType type;
		private String pattern;
		
		public Pattern(PatternType type, String pattern) {
			this.type = type;
			this.pattern = pattern;
		}
		
		public boolean match(Value val) {
			switch (type) {
			case POSITIVE:
				return val.toString().equals(pattern);
			case NEGATIVE:
				return ! val.toString().equals(pattern);
			case WILDCARD:
				return true;
			}
			return true;
		}
		
		@Override
		public String toString() {
			switch (type) {
			case POSITIVE:
				return pattern;
			case NEGATIVE:
				return "!" + pattern;
			case WILDCARD:
				return "*";
			}
			return "";
		}
	}

}

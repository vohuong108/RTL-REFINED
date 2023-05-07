/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2012 Mark Richters, University of Bremen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.tzi.use.uml.ocl.expr;

import java.io.PrintWriter;

import com.google.common.html.HtmlEscapers;

/**
 * Work in progress for a HTML output of an expression with colors, etc.
 * @author Lars Hamann
 *
 */
public class GenerateHTMLExpressionVisitor extends ExpressionPrintVisitor {
	
	/**
	 * @param pw
	 */
	public GenerateHTMLExpressionVisitor(PrintWriter pw) {
		super(pw);
	}

	@Override
	protected String quoteContent(String s) {
		return HtmlEscapers.htmlEscaper().escape(s);
	}
	
	@Override
	public String toString() {
		return "<html>" + super.toString() + "</html>";
	}

	@Override
	protected String formatOperation(String s, Expression exp) {
		return "<i>" + s + "</i>";
	}
	
	@Override
	protected String formatKeyword(String s, Expression exp) {
		return "<b>" + s + "</b>";
	}
}

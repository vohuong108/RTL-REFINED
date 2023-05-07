/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2010 Mark Richters, University of Bremen
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

// $Id$

package org.tzi.use.uml.sys.ppcHandling;

import static org.tzi.use.util.StringUtil.inQuotes;

import java.io.PrintWriter;
import java.util.Deque;
import java.util.List;

import org.tzi.use.uml.mm.MPrePostCondition;
import org.tzi.use.uml.ocl.expr.Evaluator;
import org.tzi.use.uml.ocl.expr.Expression;
import org.tzi.use.uml.sys.MOperationCall;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.util.Log;

/**
 * PPC handler which logs to a {@link PrintWriter}.
 * The singleton instance returned by {@link #getDefaultOutputHandler()}
 * logs to {@link Log#out}.
 * @author Daniel Gent
 *
 */
public class ExpressionPPCHandler implements PPCHandler {
	
	private static ExpressionPPCHandler defaultHandlerToLog;
	
	/**
	 * Get the singleton default <code>ExpressionPPCHandler</code>
	 * which outputs to <code>{@link Log#out}</code>.
	 * @return Default handler to <code>Log.out</code> 
	 */
	public static ExpressionPPCHandler getDefaultOutputHandler() {
		if (defaultHandlerToLog == null) {
			defaultHandlerToLog = new ExpressionPPCHandler();
		}
		
		return defaultHandlerToLog;
	}
	
	private PrintWriter fOutput;
	
	/**
	 * Constructs a new handler with default output to <code>output</code>.
	 */
	public ExpressionPPCHandler(PrintWriter output) {
		fOutput = output;
	}
	
	
	/**
	 * Constructs a new handler with default output to <code>{@link Log#out}</code>.
	 * Only used by singleton instance getter.
	 */
	private ExpressionPPCHandler() {
		fOutput = new PrintWriter(Log.out(), true);
	}

	@Override
	public void handlePreConditions(
			MSystem system,
			MOperationCall operationCall) throws PreConditionCheckFailedException {
		
		List<MPrePostCondition> failedPreConditions = 
			operationCall.getFailedPreConditions();
		
		int numFailedPreConditions = failedPreConditions.size();
		
		if (numFailedPreConditions > 0) {
			fOutput.println(
					"\n[Warning] " +
					numFailedPreConditions +
					" precondition" +
					(numFailedPreConditions > 1 ? "s " : " ") +
					"in operation call " +
					inQuotes(operationCall) +
					" do" +
					(numFailedPreConditions > 1 ? " " : "es ") +
					"not hold:");
		}
		
		for (MPrePostCondition preCondition : failedPreConditions) {
			fOutput.println(
					"  " + 
					preCondition.name() + 
					": " + 
					preCondition.expression());
			
			printDetailedPPC(
					system, 
					operationCall.getPreState(), 
					preCondition.expression());
			
			fOutput.println();
		}
		
		if (numFailedPreConditions > 0) {
			fOutput.println("  call stack at the time of evaluation:");
			Deque<MOperationCall> callStack = system.getCallStack();
			int index = callStack.size();
			for (MOperationCall opCall : callStack) {
				fOutput.print("    " + index-- + ". ");
				fOutput.println(opCall + " " + opCall.getCallerString());
			}
		}
	}

	@Override
	public void handlePostConditions(
			MSystem system,
			MOperationCall operationCall) throws PostConditionCheckFailedException {
		
		
		List<MPrePostCondition> failedPostConditions = 
			operationCall.getFailedPostConditions();
		
		int numFailedPostConditions = failedPostConditions.size();
		
		if (numFailedPostConditions > 0) {
			fOutput.println(
					"\n[Warning] " +
					numFailedPostConditions +
					" postcondition" +
					(numFailedPostConditions > 1 ? "s " : " ") +
					"in operation call " +
					inQuotes(operationCall) +
					" do" +
					(numFailedPostConditions > 1 ? " " : "es ") +
					"not hold:");
		}
		
		for (MPrePostCondition postCondition : failedPostConditions) {
			fOutput.println(
					"  " + 
					postCondition.name() + 
					": " + 
					postCondition.expression());
			
			printDetailedPPC(
					system, 
					operationCall.getPreState(), 
					postCondition.expression());
			
			fOutput.println();
		}
				
		if (numFailedPostConditions > 0) {
			fOutput.println("  call stack at the time of evaluation:");
			Deque<MOperationCall> callStack = system.getCallStack();
			int index = callStack.size();
			for (MOperationCall opCall : callStack) {
				fOutput.print("    " + index-- + ". ");
				fOutput.println(opCall + " " + opCall.getCallerString());
			}
		}
	}
	
	private void printDetailedPPC(
			MSystem system, 
			MSystemState preState, 
			Expression ppc) {
		
		Evaluator oclEvaluator = new Evaluator();
		oclEvaluator.eval(
				ppc,
				preState,
				system.state(), 
				system.getVariableEnvironment().constructVarBindings(), 
				fOutput,
				"    ");
	}


	@Override
	public void handleTransitionsPre(MSystem system,
			MOperationCall operationCall)
			throws PreConditionCheckFailedException {
		// Should never happen, because query operations do not change system state		
	}


	@Override
	public void handleTransitionsPost(MSystem system,
			MOperationCall operationCall)
			throws PostConditionCheckFailedException {
		// Should never happen, because query operations do not change system state		
	}
}

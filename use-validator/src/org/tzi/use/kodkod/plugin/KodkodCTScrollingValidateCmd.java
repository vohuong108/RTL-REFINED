package org.tzi.use.kodkod.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tzi.kodkod.helper.LogMessages;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.use.kodkod.UseCTScrollingKodkodModelValidator;
import org.tzi.use.kodkod.transform.TransformationException;
import org.tzi.use.kodkod.transform.ocl.DefaultExpressionVisitor;
import org.tzi.use.parser.ocl.OCLCompiler;
import org.tzi.use.uml.ocl.expr.Expression;
import org.tzi.use.uml.ocl.value.VarBindings;
import org.tzi.use.util.StringUtil;
import org.tzi.use.util.input.Readline;
import org.tzi.use.util.input.ShellReadline;
import org.tzi.use.util.input.StreamReadline;

import kodkod.ast.Node;
import kodkod.ast.Variable;

/**
 * Cmd-Class for the scrolling in the solutions using classifier terms.
 *
 * @author Frank Hilken
 */
public class KodkodCTScrollingValidateCmd extends KodkodScrollingValidateCmd {

	@Override
	protected void noArguments() {
		LOG.info(LogMessages.pagingCmdError);
	}

	@Override
	protected void handleArguments(String[] arguments) {
		String ctFile = readArgument(arguments, "f");
		if(ctFile == null){
			ctFile = readArgument(arguments, "ct");
		}
		arguments = removeNullValues(arguments);
		
		String firstArgument = arguments[0];
		
		if (firstArgument.equalsIgnoreCase("next")) {
			if (checkValidatorPresent()) {
				validator.nextSolution();
			}
		} else if (firstArgument.equalsIgnoreCase("previous")) {
			if (checkValidatorPresent()) {
				validator.previousSolution();
			}
		} else {
			String argumentsAsString = StringUtil.fmtSeq(arguments, " ");
			Pattern showPattern = Pattern.compile("show\\s*\\(\\s*(\\d+)\\s*\\)", Pattern.CASE_INSENSITIVE);
			Matcher m = showPattern.matcher(argumentsAsString);
			
			if (m.matches()) {
				if (checkValidatorPresent()) {
					int index = Integer.parseInt(m.group(1));
					validator.showSolution(index);
				}
			} else {
				// check if properties file exists and cancel command if it does not
				if(!new File(useShell.getFilenameToOpen(firstArgument, false)).exists()){
					LOG.error("Properties file not found.");
					return;
				}
				
				resetValidator();
				
				try {
					if(ctFile != null){
						// read terms from file
						String filenameToOpen = useShell.getFilenameToOpen(ctFile, false);
						if(!readClassifyingTermsFromFile(filenameToOpen)){
							LOG.info("None or faulty classifying terms given. Aborting.");
							return;
						}
					} else {
						// read terms from interactively
						if(!readClassifyingTerms()){
							LOG.info("None or faulty classifying terms given. Aborting.");
							return;
						}
					}
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
					return;
				}
				
				super.handleArguments(arguments, true);
			}
		}
	}

	/**
	 * Checks if the arguments contain the given argument and returns the value for
	 * it. The relevant values from the argument array are set to null.
	 * 
	 * @return value for argument or null if not given
	 */
	private String readArgument(String[] arguments, String arg) {
		for (int i = 0; i < arguments.length; i++) {
			if(arguments[i] == null){
				continue;
			}
			
			// check if argument matches pattern
			if(arguments[i].equalsIgnoreCase("-" + arg) && i +1 < arguments.length){
				String retVal = arguments[i+1];
				
				// splice array (set values to null)
				arguments[i] = null;
				arguments[i+1] = null;
				
				return retVal;
			}
			
			// check if argument starts with pattern
			if(arguments[i].toLowerCase().startsWith("-" + arg.toLowerCase())){
				String retVal = arguments[i].charAt(0) == '-' ? arguments[i].substring(arg.length() +1) : arguments[i].substring(arg.length());
				arguments[i] = null;
				return retVal;
			}
		}
		return null;
	}
	
	private String[] removeNullValues(String[] arguments) {
		ArrayList<String> res = new ArrayList<String>();
		
		for (String string : arguments) {
			if(string != null){
				res.add(string);
			}
		}
		
		return res.toArray(new String[res.size()]);
	}

	protected boolean readClassifyingTermsFromFile(String filename) throws IOException {
		try(Readline r = new StreamReadline(new BufferedReader(new CTInputReader(new USECommentFilterReader(new FileReader(filename)))), false, "")){
			return readClassifyingTerms(r, false);
		}
	}
	
	/**
	 * Reads classifying terms from the shell.
	 * @see #readClassifyingTerms(BufferedReader, boolean)
	 */
	protected boolean readClassifyingTerms() throws IOException {
		try(Readline r = new ShellReadline(useShell)){
			return readClassifyingTerms(r, true);
		}
	}
	
	/**
	 * Reads classifying terms from a BufferedReader. Repeatedly, one line for
	 * the name and one line for the expression is read. If the name is empty, a
	 * name is generated in the form 'Term<n>', where n is the number of this
	 * classifying terms.
	 */
	protected boolean readClassifyingTerms(Readline readline, boolean interactive) throws IOException {
		UseCTScrollingKodkodModelValidator v = (UseCTScrollingKodkodModelValidator) validator;
		int terms = 1;
		
		//XXX we request the model once to issue the transformation and avoid the transformation output in between term inputs
		model();
		
		if(interactive){
			System.out.println("Input classifying terms (leave empty to abort, enter `v' or `validate' to start validation, names may be empty)");
		}
		
		do {
			String name = readline.readline("Enter name for term " + terms + " or `v' to start validation: ");
			
			if(name == null){
				// EOF caught
				break;
			}
			name = name.trim();
			
			if(name.isEmpty()){
				name = "Term" + terms;
			}
			
			if(interactive && (name.equalsIgnoreCase("v") || name.equalsIgnoreCase("validate"))){
				break;
			}
			
			String line = readline.readline("Enter term " + StringUtil.inQuotes(name) + ": ");
			
			if(line == null){
				// EOF caught
				break;
			}
			line = line.trim();
			
			if(line.isEmpty()){
				// abort
				return false;
			} else if(interactive && (line.equalsIgnoreCase("v") || line.equalsIgnoreCase("validate"))){
				break;
			}
				
			StringWriter err = new StringWriter();
			Expression result = OCLCompiler.compileExpression(session.system().model(), line, "<classifying term::" + name + ">", new PrintWriter(err), new VarBindings());

			// error checking
			if(result == null){
				LOG.error(err.toString());
				if(!interactive){
					// abort on error when reading from file
					return false;
				}
				continue;
			}
			
			if(!result.type().isTypeOfInteger() && !result.type().isTypeOfBoolean()){
				LOG.error(name + ": The expression must result in type `Boolean' or `Integer'.");
				if(!interactive){
					// abort on error when reading from file
					return false;
				}
				continue;
			}
			
			// transform into kodkod
			Node obsTermKodkod;
			try {
				DefaultExpressionVisitor ev = new DefaultExpressionVisitor(model(),
						new HashMap<String, Node>(), new HashMap<String, IClass>(),
						new HashMap<String, Variable>(), new ArrayList<String>());
				result.processWithVisitor(ev);
				obsTermKodkod = ev.getObject();
			}
			catch(TransformationException ex){
				LOG.error("The expression cannot be transformed by the model validator. Reason: " + ex.getMessage());
				continue;
			}
			
			// success
			v.addClassifyingTerm(name, result, obsTermKodkod);
			LOG.info(String.format("Read term [%s]", name));
			terms++;
		} while(true);
		
		// set of classifying terms must contain at least one
		return v.classifyingTermCount() > 0;
	}
	
	@Override
	protected void resetValidator() {
		validator = new UseCTScrollingKodkodModelValidator(session);
	}
}

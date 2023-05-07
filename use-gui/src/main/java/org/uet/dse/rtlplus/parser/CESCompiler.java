package org.uet.dse.rtlplus.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.tzi.use.parser.ParseErrorHandler;
import org.uet.dse.rtlplus.parser.ast.coevol.AstCoevolScenario;

public class CESCompiler {
	
	public AstCoevolScenario complieSpecification(File file, PrintWriter err) {
		try {
			InputStream inStream = new FileInputStream(file);
			ParseErrorHandler errHandler = new ParseErrorHandler(file.getName(), err);
			ANTLRInputStream input = new ANTLRInputStream(inStream);
			RTLCoevolLexer lexer = new RTLCoevolLexer(input);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			RTLCoevolParser parser = new RTLCoevolParser(tokenStream);
			lexer.init(errHandler);
			parser.init(errHandler);
			AstCoevolScenario result = parser.coevolScenario();
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (RecognitionException e) {
			e.printStackTrace();
			return null;
		}
	}

}

grammar RTLCoevol;

options {
  superClass = BaseParser;
}

@header {
/*
 *  @author Duc-Hanh Dang, Khoa-Hai Nguyen, Xuan-Loi Vu
 */

package org.uet.dse.rtlplus.parser;

import org.tzi.use.parser.base.BaseParser;
import org.uet.dse.rtlplus.parser.ast.coevol.*;
}

@lexer::header {
package org.uet.dse.rtlplus.parser;

import org.tzi.use.parser.ParseErrorHandler;
}

@lexer::members {
    private ParseErrorHandler fParseErrorHandler;

    public String getFilename() {
        return fParseErrorHandler.getFileName();
    }

    public void emitErrorMessage(String msg) {
       	fParseErrorHandler.reportError(msg);
	}

    public void init(ParseErrorHandler handler) {
        fParseErrorHandler = handler;
    }
}

coevolScenario returns [AstCoevolScenario n] 
@init {
	n = new AstCoevolScenario();
}
:
	(ruleApplication SEMI
		{$n.addRuleApplication($ruleApplication.n);})*
	EOF
;

ruleApplication returns [AstRuleApplication n]:
	simpleApp=simpleApplication {n = $simpleApp.n;}
	| multiApp=multiApplication {n = $multiApp.n;}
	| restrictedApp=restrictedApplication {n = $restrictedApp.n;}
;

simpleApplication returns [AstRuleSimpleApplication n]:
	name=IDENT LPAREN RPAREN
	{n = new AstRuleSimpleApplication($name.text);}
;

multiApplication returns [AstRuleMultiApplication n]
@init {
	n = new AstRuleMultiApplication();
}
:
	'do'
	(ruleApp=ruleApplication SEMI {n.addRuleApplication($ruleApp.n);})+
	'while'
	cond=COND_EXPR {n.setCondition($cond.text);}
;

restrictedApplication returns [AstRuleRestrictedApplication n]
@init {
	n = new AstRuleRestrictedApplication();
}	
:
	'if'
	cond=COND_EXPR {n.setCondition($cond.text);}
	LBRACE
	(ruleApp = ruleApplication SEMI {n.addRuleApplication($ruleApp.n);})*
	RBRACE
;
WS:
    ( ' '
    | '\t'
    | '\f'
    | NEWLINE
    )
    { $channel=HIDDEN; }
    ;

// Single-line comments
SL_COMMENT:
    ('//' | '--')
    (~('\n'|'\r'))* NEWLINE
    { $channel=HIDDEN; }
    ;

// multiple-line comments
ML_COMMENT:
    '/*' ( options {greedy=false;} : . )* '*/' { $channel=HIDDEN; };

fragment
NEWLINE	:
    '\r\n' | '\r' | '\n';

// Use paraphrases for nice error messages
ARROW 		 : '->';
AT     		 : '@';
BAR 		 : '|';
COLON 		 : ':';
COLON_COLON	 : '::';
COLON_EQUAL	 : ':=';
COMMA 		 : ',';
DOT 		 : '.';
DOTDOT 		 : '..';
EQUAL 		 : '=';
GREATER 	 : '>';
GREATER_EQUAL : '>=';
HASH 		 : '#';
LBRACE 		 : '{';
LBRACK 		 : '[';
LESS 		 : '<';
LESS_EQUAL 	 : '<=';
LPAREN 		 : '(';
MINUS 		 : '-';
NOT_EQUAL 	 : '<>';
PLUS 		 : '+';
RBRACE 		 : '}';
RBRACK 		 : ']';
RPAREN		 : ')';
SEMI		 : ';';
SLASH 		 : '/';
STAR 		 : '*';

	
IDENT:
    ('$' | 'a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*
    ;
fragment
VOCAB:
    '\U0003'..'\U0377'
    ;

/*
--------- Start of file RTLLexerRules.gpart --------------------
*/
COND_EXPR
:
    ('[') (~(']'))* (']')
    ;
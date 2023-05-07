grammar RTL;

options {
  superClass = BaseParser;
}

@header {
/*
 *  @author Duc-Hanh Dang, Khoa-Hai Nguyen, Xuan-Loi Vu
 */

package org.uet.dse.rtlplus.parser;

import org.tzi.use.parser.base.BaseParser;
import org.uet.dse.rtlplus.parser.ast.tgg.*;
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

tggRuleCollection returns [AstRuleCollection n]
	:	
		'transformation' name=IDENT { $n = new AstRuleCollection($name.text); }  
		'direction' direction=('forward'|'backward'|'integration'|'co-evolution'|'synchronization_forward'|'synchronization_backward'|'synchronization') { $n.setDirection($direction.text); }
		(rule=tggRuleDefinition { $n.addRuleDefinition($rule.n); } )+
		EOF
	;

tggRuleDefinition returns [AstTggRule n]
	:	
		'rule' name=IDENT
		'checkSource' src=ruleDefinition
		'checkTarget' trg=ruleDefinition
		'checkCorr' corr=corrRuleDefinition
		'end'
		{ $n = new AstTggRule($name.text, $src.n, $trg.n, $corr.n); }

	;
	
ruleDefinition returns [AstRule n]
	:
		LPAREN
		lhs=patternDefinition
		RPAREN LBRACE
		rhs=patternDefinition
		RBRACE
		{ $n = new AstRule($lhs.n, $rhs.n); }
	;
	
corrRuleDefinition returns [AstCorrRule n]
	:	
		LPAREN
		lhs=corrPatternDefinition
		RPAREN LBRACE
		rhs=corrPatternDefinition
		RBRACE
		{ $n = new AstCorrRule($lhs.n, $rhs.n); }
	;
	
patternDefinition returns [AstPattern n]
@init {
	n = new AstPattern();
}
	:	
		(obj=objectDefinition { $n.addObject($obj.n); })*
		(lnk=linkDefinition { $n.addLink($lnk.n); })*
		(cond=(COND_EXPR | EQUAL_COND_EXPR) { $n.addCondition($cond.text); })*
	;

corrPatternDefinition returns [AstCorr n]
@init {
	n = new AstCorr();
}
	:	
		//(obj=objectDefinition)*
		(corrLnk=corrLinkDefinition { $n.addCorrLink($corrLnk.n); })*
		//(lnk=linkDefinition)*
		(inv=invariantTGG { $n.addInv($inv.n); } )*
		//(cond=conditionDefinition)*
	;
	

invariantTGG returns [AstInvariantTgg n]
	:	
		name=IDENT { $n = new AstInvariantTgg($name.text); }
		COLON 
		(cond=invariantCondition { $n.addCondition($cond.n); } )+
	;
	
invariantCondition returns [AstInvariantCondition n]
	:
		cond=EQUAL_COND_EXPR { $n = new AstInvariantCondition($cond.text); }
		(fImpl=COND_IMPL { $n.setForwardImpl($fImpl.text); }
		 bImpl=COND_IMPL { $n.setBackwardImpl($bImpl.text); } )?
	;
	
/*
attrCorr returns [AstAttrCorr n]
	:	
		LBRACK lhs=ANY_CHAR+ EQUAL rhs=ANY_CHAR+ RBRACK
		{ $n = new AstAttrCorr($lhs.text, $rhs.text); }
	;
*/

objectDefinition returns [AstObject n]
	:	
		obj=IDENT COLON cls=IDENT
		{ $n = new AstObject($obj.text, $cls.text); }
	;

linkDefinition returns [AstLink n]
@init {
	n = new AstLink();
}
	:	
		LPAREN obj1=IDENT { $n.addObject($obj1.text); } 
		(COMMA obj2=IDENT { $n.addObject($obj2.text);} )+ RPAREN COLON
		asc=IDENT { $n.setAssociation($asc.text); } 
	;

corrLinkDefinition returns [AstCorrLink n]
@init {
	n = new AstCorrLink();
}	
	:
		LPAREN
		(LPAREN srcCls=IDENT RPAREN { $n.setSourceClass($srcCls.text); })?
		srcObj=IDENT (STAR { $n.setMultipleSource(); })? COMMA { $n.setSourceObject($srcObj.text); }
		(LPAREN trgCls=IDENT RPAREN { $n.setTargetClass($trgCls.text); })?
		trgObj=IDENT (STAR { $n.setMultipleTarget(); })? RPAREN  { $n.setTargetObject($trgObj.text); }
		('as' LPAREN role1=IDENT COMMA role2=IDENT RPAREN { $n.setRoleNames($role1.text, $role2.text); } )?
		'in' corrObj=IDENT COLON corrCls=IDENT 
		{ $n.setName($corrObj.text); $n.setClass($corrCls.text); }
	;
	
	
/*
--------- Start of file OCLLexerRules.gpart --------------------
*/

// Whitespace -- ignored
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
BTICK        : '`';	

fragment
INT:
    ('0'..'9')+
    ;

fragment
REAL:
    INT ('.' INT (('e' | 'E') ('+' | '-')? INT)? | ('e' | 'E') ('+' | '-')? INT)
    ;

RANGE_OR_INT:
      ( INT '..' )      => INT    { $type=INT; }
    | ( REAL )          => REAL   { $type=REAL; }
    |   INT                       { $type=INT; }
    ;

// String literals
STRING:
    '\'' ( ~('\''|'\\') | ESC)* '\'';

NON_OCL_STRING:
    '"' ( ~('"'|'\\') | ESC)* '"';

// escape sequence -- note that this is protected; it can only be called
//   from another lexer rule -- it will not ever directly return a token to
//   the parser
// There are various ambiguities hushed in this rule.  The optional
// '0'...'7' digit matches should be matched here rather than letting
// them go back to STRING_LITERAL to be matched.  ANTLR does the
// right thing by matching immediately; hence, it's ok to shut off
// the FOLLOW ambig warnings.
fragment
ESC
:
    '\\'
     ( 'n'
     | 'r'
     | 't'
     | 'b'
     | 'f'
     | '"'
     | '\''
     | '\\'
     | 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
     | '0'..'3' ('0'..'7' ('0'..'7')? )?  | '4'..'7' ('0'..'7')?
     )
     ;

// hexadecimal digit (again, note it's protected!)
fragment
HEX_DIGIT:
    ( '0'..'9' | 'A'..'F' | 'a'..'f' );


// An identifier.  Note that testLiterals is set to true!  This means
// that after we match the rule, we look in the literals table to see
// if it's a literal or really an identifer.

IDENT:
    ('$' | 'a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*
    ;

// A dummy rule to force vocabulary to be all characters (except
// special ones that ANTLR uses internally (0 to 2)

fragment
VOCAB:
    '\U0003'..'\U0377'
    ;

/*
--------- Start of file RTLLexerRules.gpart --------------------
*/

COND_IMPL
	:	
		BTICK (~BTICK)* BTICK {setText(getText().substring(1, getText().length()-1));}
	;

EQUAL_COND_EXPR
	:
	 	LBRACK (~(RBRACK|EQUAL|SEMI))* EQUAL (~(RBRACK|EQUAL))* RBRACK {setText(getText().substring(1, getText().length()-1));}
	;

COND_EXPR
	:
	    LBRACK (~(RBRACK|SEMI))* RBRACK {setText(getText().substring(1, getText().length()-1));}
	;

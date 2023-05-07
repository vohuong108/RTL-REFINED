// $ANTLR 3.5.1 /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g 2018-04-03 22:10:00

package org.uet.dse.rtlplus.parser;

import org.tzi.use.parser.ParseErrorHandler;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("all")
public class RTLLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int T__61=61;
	public static final int T__62=62;
	public static final int T__63=63;
	public static final int ARROW=4;
	public static final int AT=5;
	public static final int BAR=6;
	public static final int BTICK=7;
	public static final int COLON=8;
	public static final int COLON_COLON=9;
	public static final int COLON_EQUAL=10;
	public static final int COMMA=11;
	public static final int COND_EXPR=12;
	public static final int COND_IMPL=13;
	public static final int DOT=14;
	public static final int DOTDOT=15;
	public static final int EQUAL=16;
	public static final int EQUAL_COND_EXPR=17;
	public static final int ESC=18;
	public static final int GREATER=19;
	public static final int GREATER_EQUAL=20;
	public static final int HASH=21;
	public static final int HEX_DIGIT=22;
	public static final int IDENT=23;
	public static final int INT=24;
	public static final int LBRACE=25;
	public static final int LBRACK=26;
	public static final int LESS=27;
	public static final int LESS_EQUAL=28;
	public static final int LPAREN=29;
	public static final int MINUS=30;
	public static final int ML_COMMENT=31;
	public static final int NEWLINE=32;
	public static final int NON_OCL_STRING=33;
	public static final int NOT_EQUAL=34;
	public static final int PLUS=35;
	public static final int RANGE_OR_INT=36;
	public static final int RBRACE=37;
	public static final int RBRACK=38;
	public static final int REAL=39;
	public static final int RPAREN=40;
	public static final int SEMI=41;
	public static final int SLASH=42;
	public static final int SL_COMMENT=43;
	public static final int STAR=44;
	public static final int STRING=45;
	public static final int VOCAB=46;
	public static final int WS=47;

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


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public RTLLexer() {} 
	public RTLLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public RTLLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g"; }

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:23:7: ( 'as' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:23:9: 'as'
			{
			match("as"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:24:7: ( 'backward' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:24:9: 'backward'
			{
			match("backward"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:25:7: ( 'checkCorr' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:25:9: 'checkCorr'
			{
			match("checkCorr"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:26:7: ( 'checkSource' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:26:9: 'checkSource'
			{
			match("checkSource"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:27:7: ( 'checkTarget' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:27:9: 'checkTarget'
			{
			match("checkTarget"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:28:7: ( 'co-evolution' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:28:9: 'co-evolution'
			{
			match("co-evolution"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:29:7: ( 'direction' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:29:9: 'direction'
			{
			match("direction"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:30:7: ( 'end' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:30:9: 'end'
			{
			match("end"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:31:7: ( 'forward' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:31:9: 'forward'
			{
			match("forward"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:32:7: ( 'in' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:32:9: 'in'
			{
			match("in"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:33:7: ( 'integration' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:33:9: 'integration'
			{
			match("integration"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:34:7: ( 'rule' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:34:9: 'rule'
			{
			match("rule"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:35:7: ( 'synchronization' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:35:9: 'synchronization'
			{
			match("synchronization"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:36:7: ( 'synchronization_backward' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:36:9: 'synchronization_backward'
			{
			match("synchronization_backward"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:37:7: ( 'synchronization_forward' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:37:9: 'synchronization_forward'
			{
			match("synchronization_forward"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:38:7: ( 'transformation' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:38:9: 'transformation'
			{
			match("transformation"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__63"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:161:3: ( ( ' ' | '\\t' | '\\f' | NEWLINE ) )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:162:5: ( ' ' | '\\t' | '\\f' | NEWLINE )
			{
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:162:5: ( ' ' | '\\t' | '\\f' | NEWLINE )
			int alt1=4;
			switch ( input.LA(1) ) {
			case ' ':
				{
				alt1=1;
				}
				break;
			case '\t':
				{
				alt1=2;
				}
				break;
			case '\f':
				{
				alt1=3;
				}
				break;
			case '\n':
			case '\r':
				{
				alt1=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:162:7: ' '
					{
					match(' '); if (state.failed) return;
					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:163:7: '\\t'
					{
					match('\t'); if (state.failed) return;
					}
					break;
				case 3 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:164:7: '\\f'
					{
					match('\f'); if (state.failed) return;
					}
					break;
				case 4 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:165:7: NEWLINE
					{
					mNEWLINE(); if (state.failed) return;

					}
					break;

			}

			if ( state.backtracking==0 ) { _channel=HIDDEN; }
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "SL_COMMENT"
	public final void mSL_COMMENT() throws RecognitionException {
		try {
			int _type = SL_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:171:11: ( ( '//' | '--' ) (~ ( '\\n' | '\\r' ) )* NEWLINE )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:172:5: ( '//' | '--' ) (~ ( '\\n' | '\\r' ) )* NEWLINE
			{
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:172:5: ( '//' | '--' )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='/') ) {
				alt2=1;
			}
			else if ( (LA2_0=='-') ) {
				alt2=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:172:6: '//'
					{
					match("//"); if (state.failed) return;

					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:172:13: '--'
					{
					match("--"); if (state.failed) return;

					}
					break;

			}

			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:173:5: (~ ( '\\n' | '\\r' ) )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\u0000' && LA3_0 <= '\t')||(LA3_0 >= '\u000B' && LA3_0 <= '\f')||(LA3_0 >= '\u000E' && LA3_0 <= '\uFFFF')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			mNEWLINE(); if (state.failed) return;

			if ( state.backtracking==0 ) { _channel=HIDDEN; }
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SL_COMMENT"

	// $ANTLR start "ML_COMMENT"
	public final void mML_COMMENT() throws RecognitionException {
		try {
			int _type = ML_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:178:11: ( '/*' ( options {greedy=false; } : . )* '*/' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:179:5: '/*' ( options {greedy=false; } : . )* '*/'
			{
			match("/*"); if (state.failed) return;

			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:179:10: ( options {greedy=false; } : . )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0=='*') ) {
					int LA4_1 = input.LA(2);
					if ( (LA4_1=='/') ) {
						alt4=2;
					}
					else if ( ((LA4_1 >= '\u0000' && LA4_1 <= '.')||(LA4_1 >= '0' && LA4_1 <= '\uFFFF')) ) {
						alt4=1;
					}

				}
				else if ( ((LA4_0 >= '\u0000' && LA4_0 <= ')')||(LA4_0 >= '+' && LA4_0 <= '\uFFFF')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:179:38: .
					{
					matchAny(); if (state.failed) return;
					}
					break;

				default :
					break loop4;
				}
			}

			match("*/"); if (state.failed) return;

			if ( state.backtracking==0 ) { _channel=HIDDEN; }
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ML_COMMENT"

	// $ANTLR start "NEWLINE"
	public final void mNEWLINE() throws RecognitionException {
		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:183:9: ( '\\r\\n' | '\\r' | '\\n' )
			int alt5=3;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='\r') ) {
				int LA5_1 = input.LA(2);
				if ( (LA5_1=='\n') ) {
					alt5=1;
				}

				else {
					alt5=2;
				}

			}
			else if ( (LA5_0=='\n') ) {
				alt5=3;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:184:5: '\\r\\n'
					{
					match("\r\n"); if (state.failed) return;

					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:184:14: '\\r'
					{
					match('\r'); if (state.failed) return;
					}
					break;
				case 3 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:184:21: '\\n'
					{
					match('\n'); if (state.failed) return;
					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEWLINE"

	// $ANTLR start "ARROW"
	public final void mARROW() throws RecognitionException {
		try {
			int _type = ARROW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:186:10: ( '->' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:186:12: '->'
			{
			match("->"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARROW"

	// $ANTLR start "AT"
	public final void mAT() throws RecognitionException {
		try {
			int _type = AT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:187:11: ( '@' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:187:13: '@'
			{
			match('@'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AT"

	// $ANTLR start "BAR"
	public final void mBAR() throws RecognitionException {
		try {
			int _type = BAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:188:8: ( '|' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:188:10: '|'
			{
			match('|'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BAR"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:189:10: ( ':' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:189:12: ':'
			{
			match(':'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON"

	// $ANTLR start "COLON_COLON"
	public final void mCOLON_COLON() throws RecognitionException {
		try {
			int _type = COLON_COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:190:14: ( '::' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:190:16: '::'
			{
			match("::"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON_COLON"

	// $ANTLR start "COLON_EQUAL"
	public final void mCOLON_EQUAL() throws RecognitionException {
		try {
			int _type = COLON_EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:191:14: ( ':=' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:191:16: ':='
			{
			match(":="); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON_EQUAL"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:192:10: ( ',' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:192:12: ','
			{
			match(','); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:193:8: ( '.' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:193:10: '.'
			{
			match('.'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "DOTDOT"
	public final void mDOTDOT() throws RecognitionException {
		try {
			int _type = DOTDOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:194:11: ( '..' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:194:13: '..'
			{
			match(".."); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOTDOT"

	// $ANTLR start "EQUAL"
	public final void mEQUAL() throws RecognitionException {
		try {
			int _type = EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:195:10: ( '=' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:195:12: '='
			{
			match('='); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUAL"

	// $ANTLR start "GREATER"
	public final void mGREATER() throws RecognitionException {
		try {
			int _type = GREATER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:196:11: ( '>' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:196:13: '>'
			{
			match('>'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GREATER"

	// $ANTLR start "GREATER_EQUAL"
	public final void mGREATER_EQUAL() throws RecognitionException {
		try {
			int _type = GREATER_EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:197:15: ( '>=' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:197:17: '>='
			{
			match(">="); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GREATER_EQUAL"

	// $ANTLR start "HASH"
	public final void mHASH() throws RecognitionException {
		try {
			int _type = HASH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:198:9: ( '#' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:198:11: '#'
			{
			match('#'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HASH"

	// $ANTLR start "LBRACE"
	public final void mLBRACE() throws RecognitionException {
		try {
			int _type = LBRACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:199:11: ( '{' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:199:13: '{'
			{
			match('{'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LBRACE"

	// $ANTLR start "LBRACK"
	public final void mLBRACK() throws RecognitionException {
		try {
			int _type = LBRACK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:200:11: ( '[' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:200:13: '['
			{
			match('['); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LBRACK"

	// $ANTLR start "LESS"
	public final void mLESS() throws RecognitionException {
		try {
			int _type = LESS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:201:9: ( '<' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:201:11: '<'
			{
			match('<'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LESS"

	// $ANTLR start "LESS_EQUAL"
	public final void mLESS_EQUAL() throws RecognitionException {
		try {
			int _type = LESS_EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:202:14: ( '<=' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:202:16: '<='
			{
			match("<="); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LESS_EQUAL"

	// $ANTLR start "LPAREN"
	public final void mLPAREN() throws RecognitionException {
		try {
			int _type = LPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:203:11: ( '(' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:203:13: '('
			{
			match('('); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAREN"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:204:10: ( '-' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:204:12: '-'
			{
			match('-'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "NOT_EQUAL"
	public final void mNOT_EQUAL() throws RecognitionException {
		try {
			int _type = NOT_EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:205:13: ( '<>' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:205:15: '<>'
			{
			match("<>"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT_EQUAL"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:206:9: ( '+' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:206:11: '+'
			{
			match('+'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "RBRACE"
	public final void mRBRACE() throws RecognitionException {
		try {
			int _type = RBRACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:207:11: ( '}' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:207:13: '}'
			{
			match('}'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RBRACE"

	// $ANTLR start "RBRACK"
	public final void mRBRACK() throws RecognitionException {
		try {
			int _type = RBRACK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:208:11: ( ']' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:208:13: ']'
			{
			match(']'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RBRACK"

	// $ANTLR start "RPAREN"
	public final void mRPAREN() throws RecognitionException {
		try {
			int _type = RPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:209:10: ( ')' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:209:12: ')'
			{
			match(')'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAREN"

	// $ANTLR start "SEMI"
	public final void mSEMI() throws RecognitionException {
		try {
			int _type = SEMI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:210:8: ( ';' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:210:10: ';'
			{
			match(';'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMI"

	// $ANTLR start "SLASH"
	public final void mSLASH() throws RecognitionException {
		try {
			int _type = SLASH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:211:10: ( '/' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:211:12: '/'
			{
			match('/'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SLASH"

	// $ANTLR start "STAR"
	public final void mSTAR() throws RecognitionException {
		try {
			int _type = STAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:212:9: ( '*' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:212:11: '*'
			{
			match('*'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STAR"

	// $ANTLR start "BTICK"
	public final void mBTICK() throws RecognitionException {
		try {
			int _type = BTICK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:213:14: ( '`' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:213:16: '`'
			{
			match('`'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BTICK"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:217:4: ( ( '0' .. '9' )+ )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:218:5: ( '0' .. '9' )+
			{
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:218:5: ( '0' .. '9' )+
			int cnt6=0;
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt6 >= 1 ) break loop6;
					if (state.backtracking>0) {state.failed=true; return;}
					EarlyExitException eee = new EarlyExitException(6, input);
					throw eee;
				}
				cnt6++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "REAL"
	public final void mREAL() throws RecognitionException {
		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:222:5: ( INT ( '.' INT ( ( 'e' | 'E' ) ( '+' | '-' )? INT )? | ( 'e' | 'E' ) ( '+' | '-' )? INT ) )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:5: INT ( '.' INT ( ( 'e' | 'E' ) ( '+' | '-' )? INT )? | ( 'e' | 'E' ) ( '+' | '-' )? INT )
			{
			mINT(); if (state.failed) return;

			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:9: ( '.' INT ( ( 'e' | 'E' ) ( '+' | '-' )? INT )? | ( 'e' | 'E' ) ( '+' | '-' )? INT )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0=='.') ) {
				alt10=1;
			}
			else if ( (LA10_0=='E'||LA10_0=='e') ) {
				alt10=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:10: '.' INT ( ( 'e' | 'E' ) ( '+' | '-' )? INT )?
					{
					match('.'); if (state.failed) return;
					mINT(); if (state.failed) return;

					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:18: ( ( 'e' | 'E' ) ( '+' | '-' )? INT )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0=='E'||LA8_0=='e') ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:19: ( 'e' | 'E' ) ( '+' | '-' )? INT
							{
							if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
								input.consume();
								state.failed=false;
							}
							else {
								if (state.backtracking>0) {state.failed=true; return;}
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:31: ( '+' | '-' )?
							int alt7=2;
							int LA7_0 = input.LA(1);
							if ( (LA7_0=='+'||LA7_0=='-') ) {
								alt7=1;
							}
							switch (alt7) {
								case 1 :
									// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
									{
									if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
										input.consume();
										state.failed=false;
									}
									else {
										if (state.backtracking>0) {state.failed=true; return;}
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

							}

							mINT(); if (state.failed) return;

							}
							break;

					}

					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:52: ( 'e' | 'E' ) ( '+' | '-' )? INT
					{
					if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:223:64: ( '+' | '-' )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0=='+'||LA9_0=='-') ) {
						alt9=1;
					}
					switch (alt9) {
						case 1 :
							// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
							{
							if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
								input.consume();
								state.failed=false;
							}
							else {
								if (state.backtracking>0) {state.failed=true; return;}
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					mINT(); if (state.failed) return;

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REAL"

	// $ANTLR start "RANGE_OR_INT"
	public final void mRANGE_OR_INT() throws RecognitionException {
		try {
			int _type = RANGE_OR_INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:225:13: ( ( INT '..' )=> INT | ( REAL )=> REAL | INT )
			int alt11=3;
			int LA11_0 = input.LA(1);
			if ( ((LA11_0 >= '0' && LA11_0 <= '9')) ) {
				int LA11_1 = input.LA(2);
				if ( ((LA11_1 >= '0' && LA11_1 <= '9')) && (synpred2_RTL())) {
					alt11=2;
				}
				else if ( (LA11_1=='.') && (synpred2_RTL())) {
					alt11=2;
				}
				else if ( (LA11_1=='E'||LA11_1=='e') && (synpred2_RTL())) {
					alt11=2;
				}
				else if ( (synpred1_RTL()) ) {
					alt11=1;
				}
				else if ( (true) ) {
					alt11=3;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:226:7: ( INT '..' )=> INT
					{
					mINT(); if (state.failed) return;

					if ( state.backtracking==0 ) { _type=INT; }
					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:227:7: ( REAL )=> REAL
					{
					mREAL(); if (state.failed) return;

					if ( state.backtracking==0 ) { _type=REAL; }
					}
					break;
				case 3 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:228:9: INT
					{
					mINT(); if (state.failed) return;

					if ( state.backtracking==0 ) { _type=INT; }
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RANGE_OR_INT"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:232:7: ( '\\'' (~ ( '\\'' | '\\\\' ) | ESC )* '\\'' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:233:5: '\\'' (~ ( '\\'' | '\\\\' ) | ESC )* '\\''
			{
			match('\''); if (state.failed) return;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:233:10: (~ ( '\\'' | '\\\\' ) | ESC )*
			loop12:
			while (true) {
				int alt12=3;
				int LA12_0 = input.LA(1);
				if ( ((LA12_0 >= '\u0000' && LA12_0 <= '&')||(LA12_0 >= '(' && LA12_0 <= '[')||(LA12_0 >= ']' && LA12_0 <= '\uFFFF')) ) {
					alt12=1;
				}
				else if ( (LA12_0=='\\') ) {
					alt12=2;
				}

				switch (alt12) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:233:12: ~ ( '\\'' | '\\\\' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:233:27: ESC
					{
					mESC(); if (state.failed) return;

					}
					break;

				default :
					break loop12;
				}
			}

			match('\''); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "NON_OCL_STRING"
	public final void mNON_OCL_STRING() throws RecognitionException {
		try {
			int _type = NON_OCL_STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:235:15: ( '\"' (~ ( '\"' | '\\\\' ) | ESC )* '\"' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:236:5: '\"' (~ ( '\"' | '\\\\' ) | ESC )* '\"'
			{
			match('\"'); if (state.failed) return;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:236:9: (~ ( '\"' | '\\\\' ) | ESC )*
			loop13:
			while (true) {
				int alt13=3;
				int LA13_0 = input.LA(1);
				if ( ((LA13_0 >= '\u0000' && LA13_0 <= '!')||(LA13_0 >= '#' && LA13_0 <= '[')||(LA13_0 >= ']' && LA13_0 <= '\uFFFF')) ) {
					alt13=1;
				}
				else if ( (LA13_0=='\\') ) {
					alt13=2;
				}

				switch (alt13) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:236:11: ~ ( '\"' | '\\\\' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:236:25: ESC
					{
					mESC(); if (state.failed) return;

					}
					break;

				default :
					break loop13;
				}
			}

			match('\"'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NON_OCL_STRING"

	// $ANTLR start "ESC"
	public final void mESC() throws RecognitionException {
		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:249:5: ( '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT | '0' .. '3' ( '0' .. '7' ( '0' .. '7' )? )? | '4' .. '7' ( '0' .. '7' )? ) )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:250:5: '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT | '0' .. '3' ( '0' .. '7' ( '0' .. '7' )? )? | '4' .. '7' ( '0' .. '7' )? )
			{
			match('\\'); if (state.failed) return;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:251:6: ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT | '0' .. '3' ( '0' .. '7' ( '0' .. '7' )? )? | '4' .. '7' ( '0' .. '7' )? )
			int alt17=11;
			switch ( input.LA(1) ) {
			case 'n':
				{
				alt17=1;
				}
				break;
			case 'r':
				{
				alt17=2;
				}
				break;
			case 't':
				{
				alt17=3;
				}
				break;
			case 'b':
				{
				alt17=4;
				}
				break;
			case 'f':
				{
				alt17=5;
				}
				break;
			case '\"':
				{
				alt17=6;
				}
				break;
			case '\'':
				{
				alt17=7;
				}
				break;
			case '\\':
				{
				alt17=8;
				}
				break;
			case 'u':
				{
				alt17=9;
				}
				break;
			case '0':
			case '1':
			case '2':
			case '3':
				{
				alt17=10;
				}
				break;
			case '4':
			case '5':
			case '6':
			case '7':
				{
				alt17=11;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:251:8: 'n'
					{
					match('n'); if (state.failed) return;
					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:252:8: 'r'
					{
					match('r'); if (state.failed) return;
					}
					break;
				case 3 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:253:8: 't'
					{
					match('t'); if (state.failed) return;
					}
					break;
				case 4 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:254:8: 'b'
					{
					match('b'); if (state.failed) return;
					}
					break;
				case 5 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:255:8: 'f'
					{
					match('f'); if (state.failed) return;
					}
					break;
				case 6 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:256:8: '\"'
					{
					match('\"'); if (state.failed) return;
					}
					break;
				case 7 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:257:8: '\\''
					{
					match('\''); if (state.failed) return;
					}
					break;
				case 8 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:258:8: '\\\\'
					{
					match('\\'); if (state.failed) return;
					}
					break;
				case 9 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:259:8: 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
					{
					match('u'); if (state.failed) return;
					mHEX_DIGIT(); if (state.failed) return;

					mHEX_DIGIT(); if (state.failed) return;

					mHEX_DIGIT(); if (state.failed) return;

					mHEX_DIGIT(); if (state.failed) return;

					}
					break;
				case 10 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:260:8: '0' .. '3' ( '0' .. '7' ( '0' .. '7' )? )?
					{
					matchRange('0','3'); if (state.failed) return;
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:260:17: ( '0' .. '7' ( '0' .. '7' )? )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( ((LA15_0 >= '0' && LA15_0 <= '7')) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:260:18: '0' .. '7' ( '0' .. '7' )?
							{
							matchRange('0','7'); if (state.failed) return;
							// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:260:27: ( '0' .. '7' )?
							int alt14=2;
							int LA14_0 = input.LA(1);
							if ( ((LA14_0 >= '0' && LA14_0 <= '7')) ) {
								alt14=1;
							}
							switch (alt14) {
								case 1 :
									// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
										input.consume();
										state.failed=false;
									}
									else {
										if (state.backtracking>0) {state.failed=true; return;}
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

							}

							}
							break;

					}

					}
					break;
				case 11 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:260:45: '4' .. '7' ( '0' .. '7' )?
					{
					matchRange('4','7'); if (state.failed) return;
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:260:54: ( '0' .. '7' )?
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( ((LA16_0 >= '0' && LA16_0 <= '7')) ) {
						alt16=1;
					}
					switch (alt16) {
						case 1 :
							// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
								input.consume();
								state.failed=false;
							}
							else {
								if (state.backtracking>0) {state.failed=true; return;}
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ESC"

	// $ANTLR start "HEX_DIGIT"
	public final void mHEX_DIGIT() throws RecognitionException {
		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:266:10: ( ( '0' .. '9' | 'A' .. 'F' | 'a' .. 'f' ) )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HEX_DIGIT"

	// $ANTLR start "IDENT"
	public final void mIDENT() throws RecognitionException {
		try {
			int _type = IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:273:6: ( ( '$' | 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:274:5: ( '$' | 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			{
			if ( input.LA(1)=='$'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:274:39: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( ((LA18_0 >= '0' && LA18_0 <= '9')||(LA18_0 >= 'A' && LA18_0 <= 'Z')||LA18_0=='_'||(LA18_0 >= 'a' && LA18_0 <= 'z')) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop18;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENT"

	// $ANTLR start "VOCAB"
	public final void mVOCAB() throws RecognitionException {
		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:282:6: ( '\\U0003' .. '\\U0377' )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
			{
			if ( (input.LA(1) >= '\u0003' && input.LA(1) <= '\u0377') ) {
				input.consume();
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOCAB"

	// $ANTLR start "COND_IMPL"
	public final void mCOND_IMPL() throws RecognitionException {
		try {
			int _type = COND_IMPL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:290:2: ( BTICK (~ BTICK )* BTICK )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:291:3: BTICK (~ BTICK )* BTICK
			{
			mBTICK(); if (state.failed) return;

			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:291:9: (~ BTICK )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( ((LA19_0 >= '\u0000' && LA19_0 <= '_')||(LA19_0 >= 'a' && LA19_0 <= '\uFFFF')) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '_')||(input.LA(1) >= 'a' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop19;
				}
			}

			mBTICK(); if (state.failed) return;

			if ( state.backtracking==0 ) {setText(getText().substring(1, getText().length()-1));}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COND_IMPL"

	// $ANTLR start "EQUAL_COND_EXPR"
	public final void mEQUAL_COND_EXPR() throws RecognitionException {
		try {
			int _type = EQUAL_COND_EXPR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:295:2: ( LBRACK (~ ( RBRACK | EQUAL | SEMI ) )* EQUAL (~ ( RBRACK | EQUAL ) )* RBRACK )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:296:4: LBRACK (~ ( RBRACK | EQUAL | SEMI ) )* EQUAL (~ ( RBRACK | EQUAL ) )* RBRACK
			{
			mLBRACK(); if (state.failed) return;

			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:296:11: (~ ( RBRACK | EQUAL | SEMI ) )*
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( ((LA20_0 >= '\u0000' && LA20_0 <= ':')||LA20_0=='<'||(LA20_0 >= '>' && LA20_0 <= '\\')||(LA20_0 >= '^' && LA20_0 <= '\uFFFF')) ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= ':')||input.LA(1)=='<'||(input.LA(1) >= '>' && input.LA(1) <= '\\')||(input.LA(1) >= '^' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop20;
				}
			}

			mEQUAL(); if (state.failed) return;

			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:296:41: (~ ( RBRACK | EQUAL ) )*
			loop21:
			while (true) {
				int alt21=2;
				int LA21_0 = input.LA(1);
				if ( ((LA21_0 >= '\u0000' && LA21_0 <= '<')||(LA21_0 >= '>' && LA21_0 <= '\\')||(LA21_0 >= '^' && LA21_0 <= '\uFFFF')) ) {
					alt21=1;
				}

				switch (alt21) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '<')||(input.LA(1) >= '>' && input.LA(1) <= '\\')||(input.LA(1) >= '^' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop21;
				}
			}

			mRBRACK(); if (state.failed) return;

			if ( state.backtracking==0 ) {setText(getText().substring(1, getText().length()-1));}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUAL_COND_EXPR"

	// $ANTLR start "COND_EXPR"
	public final void mCOND_EXPR() throws RecognitionException {
		try {
			int _type = COND_EXPR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:300:2: ( LBRACK (~ ( RBRACK | SEMI ) )* RBRACK )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:301:6: LBRACK (~ ( RBRACK | SEMI ) )* RBRACK
			{
			mLBRACK(); if (state.failed) return;

			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:301:13: (~ ( RBRACK | SEMI ) )*
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( ((LA22_0 >= '\u0000' && LA22_0 <= ':')||(LA22_0 >= '<' && LA22_0 <= '\\')||(LA22_0 >= '^' && LA22_0 <= '\uFFFF')) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= ':')||(input.LA(1) >= '<' && input.LA(1) <= '\\')||(input.LA(1) >= '^' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop22;
				}
			}

			mRBRACK(); if (state.failed) return;

			if ( state.backtracking==0 ) {setText(getText().substring(1, getText().length()-1));}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COND_EXPR"

	@Override
	public void mTokens() throws RecognitionException {
		// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:8: ( T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | WS | SL_COMMENT | ML_COMMENT | ARROW | AT | BAR | COLON | COLON_COLON | COLON_EQUAL | COMMA | DOT | DOTDOT | EQUAL | GREATER | GREATER_EQUAL | HASH | LBRACE | LBRACK | LESS | LESS_EQUAL | LPAREN | MINUS | NOT_EQUAL | PLUS | RBRACE | RBRACK | RPAREN | SEMI | SLASH | STAR | BTICK | RANGE_OR_INT | STRING | NON_OCL_STRING | IDENT | COND_IMPL | EQUAL_COND_EXPR | COND_EXPR )
		int alt23=54;
		alt23 = dfa23.predict(input);
		switch (alt23) {
			case 1 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:10: T__48
				{
				mT__48(); if (state.failed) return;

				}
				break;
			case 2 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:16: T__49
				{
				mT__49(); if (state.failed) return;

				}
				break;
			case 3 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:22: T__50
				{
				mT__50(); if (state.failed) return;

				}
				break;
			case 4 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:28: T__51
				{
				mT__51(); if (state.failed) return;

				}
				break;
			case 5 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:34: T__52
				{
				mT__52(); if (state.failed) return;

				}
				break;
			case 6 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:40: T__53
				{
				mT__53(); if (state.failed) return;

				}
				break;
			case 7 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:46: T__54
				{
				mT__54(); if (state.failed) return;

				}
				break;
			case 8 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:52: T__55
				{
				mT__55(); if (state.failed) return;

				}
				break;
			case 9 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:58: T__56
				{
				mT__56(); if (state.failed) return;

				}
				break;
			case 10 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:64: T__57
				{
				mT__57(); if (state.failed) return;

				}
				break;
			case 11 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:70: T__58
				{
				mT__58(); if (state.failed) return;

				}
				break;
			case 12 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:76: T__59
				{
				mT__59(); if (state.failed) return;

				}
				break;
			case 13 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:82: T__60
				{
				mT__60(); if (state.failed) return;

				}
				break;
			case 14 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:88: T__61
				{
				mT__61(); if (state.failed) return;

				}
				break;
			case 15 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:94: T__62
				{
				mT__62(); if (state.failed) return;

				}
				break;
			case 16 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:100: T__63
				{
				mT__63(); if (state.failed) return;

				}
				break;
			case 17 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:106: WS
				{
				mWS(); if (state.failed) return;

				}
				break;
			case 18 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:109: SL_COMMENT
				{
				mSL_COMMENT(); if (state.failed) return;

				}
				break;
			case 19 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:120: ML_COMMENT
				{
				mML_COMMENT(); if (state.failed) return;

				}
				break;
			case 20 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:131: ARROW
				{
				mARROW(); if (state.failed) return;

				}
				break;
			case 21 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:137: AT
				{
				mAT(); if (state.failed) return;

				}
				break;
			case 22 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:140: BAR
				{
				mBAR(); if (state.failed) return;

				}
				break;
			case 23 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:144: COLON
				{
				mCOLON(); if (state.failed) return;

				}
				break;
			case 24 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:150: COLON_COLON
				{
				mCOLON_COLON(); if (state.failed) return;

				}
				break;
			case 25 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:162: COLON_EQUAL
				{
				mCOLON_EQUAL(); if (state.failed) return;

				}
				break;
			case 26 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:174: COMMA
				{
				mCOMMA(); if (state.failed) return;

				}
				break;
			case 27 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:180: DOT
				{
				mDOT(); if (state.failed) return;

				}
				break;
			case 28 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:184: DOTDOT
				{
				mDOTDOT(); if (state.failed) return;

				}
				break;
			case 29 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:191: EQUAL
				{
				mEQUAL(); if (state.failed) return;

				}
				break;
			case 30 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:197: GREATER
				{
				mGREATER(); if (state.failed) return;

				}
				break;
			case 31 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:205: GREATER_EQUAL
				{
				mGREATER_EQUAL(); if (state.failed) return;

				}
				break;
			case 32 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:219: HASH
				{
				mHASH(); if (state.failed) return;

				}
				break;
			case 33 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:224: LBRACE
				{
				mLBRACE(); if (state.failed) return;

				}
				break;
			case 34 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:231: LBRACK
				{
				mLBRACK(); if (state.failed) return;

				}
				break;
			case 35 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:238: LESS
				{
				mLESS(); if (state.failed) return;

				}
				break;
			case 36 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:243: LESS_EQUAL
				{
				mLESS_EQUAL(); if (state.failed) return;

				}
				break;
			case 37 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:254: LPAREN
				{
				mLPAREN(); if (state.failed) return;

				}
				break;
			case 38 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:261: MINUS
				{
				mMINUS(); if (state.failed) return;

				}
				break;
			case 39 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:267: NOT_EQUAL
				{
				mNOT_EQUAL(); if (state.failed) return;

				}
				break;
			case 40 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:277: PLUS
				{
				mPLUS(); if (state.failed) return;

				}
				break;
			case 41 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:282: RBRACE
				{
				mRBRACE(); if (state.failed) return;

				}
				break;
			case 42 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:289: RBRACK
				{
				mRBRACK(); if (state.failed) return;

				}
				break;
			case 43 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:296: RPAREN
				{
				mRPAREN(); if (state.failed) return;

				}
				break;
			case 44 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:303: SEMI
				{
				mSEMI(); if (state.failed) return;

				}
				break;
			case 45 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:308: SLASH
				{
				mSLASH(); if (state.failed) return;

				}
				break;
			case 46 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:314: STAR
				{
				mSTAR(); if (state.failed) return;

				}
				break;
			case 47 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:319: BTICK
				{
				mBTICK(); if (state.failed) return;

				}
				break;
			case 48 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:325: RANGE_OR_INT
				{
				mRANGE_OR_INT(); if (state.failed) return;

				}
				break;
			case 49 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:338: STRING
				{
				mSTRING(); if (state.failed) return;

				}
				break;
			case 50 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:345: NON_OCL_STRING
				{
				mNON_OCL_STRING(); if (state.failed) return;

				}
				break;
			case 51 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:360: IDENT
				{
				mIDENT(); if (state.failed) return;

				}
				break;
			case 52 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:366: COND_IMPL
				{
				mCOND_IMPL(); if (state.failed) return;

				}
				break;
			case 53 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:376: EQUAL_COND_EXPR
				{
				mEQUAL_COND_EXPR(); if (state.failed) return;

				}
				break;
			case 54 :
				// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:1:392: COND_EXPR
				{
				mCOND_EXPR(); if (state.failed) return;

				}
				break;

		}
	}

	// $ANTLR start synpred1_RTL
	public final void synpred1_RTL_fragment() throws RecognitionException {
		// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:226:7: ( INT '..' )
		// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:226:9: INT '..'
		{
		mINT(); if (state.failed) return;

		match(".."); if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_RTL

	// $ANTLR start synpred2_RTL
	public final void synpred2_RTL_fragment() throws RecognitionException {
		// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:227:7: ( REAL )
		// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTL.g:227:9: REAL
		{
		mREAL(); if (state.failed) return;

		}

	}
	// $ANTLR end synpred2_RTL

	public final boolean synpred1_RTL() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_RTL_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred2_RTL() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred2_RTL_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}


	protected DFA23 dfa23 = new DFA23(this);
	static final String DFA23_eotS =
		"\1\uffff\12\44\1\uffff\1\62\1\64\2\uffff\1\67\1\uffff\1\71\1\uffff\1\73"+
		"\2\uffff\1\74\1\102\7\uffff\1\103\4\uffff\1\105\6\44\1\115\3\44\26\uffff"+
		"\2\44\1\uffff\1\44\1\127\2\44\1\uffff\3\44\3\uffff\3\44\1\uffff\2\44\1"+
		"\142\7\44\1\uffff\20\44\1\174\3\44\1\u0080\4\44\1\uffff\3\44\1\uffff\1"+
		"\u0088\2\44\1\u008b\3\44\1\uffff\2\44\1\uffff\3\44\1\u0094\1\u0095\1\u0096"+
		"\2\44\3\uffff\5\44\1\u009e\1\u00a0\1\uffff\1\44\1\uffff\15\44\1\u00b0"+
		"\1\u00b1\2\uffff";
	static final String DFA23_eofS =
		"\u00b2\uffff";
	static final String DFA23_minS =
		"\1\11\1\163\1\141\1\150\1\151\1\156\1\157\1\156\1\165\1\171\1\162\1\uffff"+
		"\1\52\1\55\2\uffff\1\72\1\uffff\1\56\1\uffff\1\75\2\uffff\1\0\1\75\7\uffff"+
		"\1\0\4\uffff\1\60\1\143\1\145\1\55\1\162\1\144\1\162\1\60\1\154\1\156"+
		"\1\141\15\uffff\2\0\7\uffff\1\153\1\143\1\uffff\1\145\1\60\1\167\1\145"+
		"\1\uffff\1\145\1\143\1\156\1\0\2\uffff\1\167\1\153\1\143\1\uffff\1\141"+
		"\1\147\1\60\1\150\1\163\1\141\1\103\1\164\2\162\1\uffff\1\162\1\146\1"+
		"\162\2\157\1\141\1\151\1\144\1\141\2\157\1\144\1\162\1\165\1\162\1\157"+
		"\1\60\1\164\1\156\1\162\1\60\2\162\1\147\1\156\1\uffff\2\151\1\155\1\uffff"+
		"\1\60\1\143\1\145\1\60\1\157\1\172\1\141\1\uffff\1\145\1\164\1\uffff\1"+
		"\156\1\141\1\164\3\60\1\164\1\151\3\uffff\1\151\2\157\2\156\2\60\1\uffff"+
		"\1\142\1\uffff\1\141\1\157\1\143\1\162\1\153\2\167\2\141\2\162\2\144\2"+
		"\60\2\uffff";
	static final String DFA23_maxS =
		"\1\175\1\163\1\141\1\157\1\151\1\156\1\157\1\156\1\165\1\171\1\162\1\uffff"+
		"\1\57\1\76\2\uffff\1\75\1\uffff\1\56\1\uffff\1\75\2\uffff\1\uffff\1\76"+
		"\7\uffff\1\uffff\4\uffff\1\172\1\143\1\145\1\55\1\162\1\144\1\162\1\172"+
		"\1\154\1\156\1\141\15\uffff\2\uffff\7\uffff\1\153\1\143\1\uffff\1\145"+
		"\1\172\1\167\1\145\1\uffff\1\145\1\143\1\156\1\uffff\2\uffff\1\167\1\153"+
		"\1\143\1\uffff\1\141\1\147\1\172\1\150\1\163\1\141\1\124\1\164\2\162\1"+
		"\uffff\1\162\1\146\1\162\2\157\1\141\1\151\1\144\1\141\2\157\1\144\1\162"+
		"\1\165\1\162\1\157\1\172\1\164\1\156\1\162\1\172\2\162\1\147\1\156\1\uffff"+
		"\2\151\1\155\1\uffff\1\172\1\143\1\145\1\172\1\157\1\172\1\141\1\uffff"+
		"\1\145\1\164\1\uffff\1\156\1\141\1\164\3\172\1\164\1\151\3\uffff\1\151"+
		"\2\157\2\156\2\172\1\uffff\1\146\1\uffff\1\141\1\157\1\143\1\162\1\153"+
		"\2\167\2\141\2\162\2\144\2\172\2\uffff";
	static final String DFA23_acceptS =
		"\13\uffff\1\21\2\uffff\1\25\1\26\1\uffff\1\32\1\uffff\1\35\1\uffff\1\40"+
		"\1\41\2\uffff\1\45\1\50\1\51\1\52\1\53\1\54\1\56\1\uffff\1\60\1\61\1\62"+
		"\1\63\13\uffff\1\22\1\23\1\55\1\24\1\46\1\30\1\31\1\27\1\34\1\33\1\37"+
		"\1\36\1\42\2\uffff\1\66\1\44\1\47\1\43\1\57\1\64\1\1\2\uffff\1\6\4\uffff"+
		"\1\12\4\uffff\2\65\3\uffff\1\10\12\uffff\1\14\31\uffff\1\11\3\uffff\1"+
		"\2\7\uffff\1\3\2\uffff\1\7\10\uffff\1\4\1\5\1\13\7\uffff\1\20\1\uffff"+
		"\1\15\17\uffff\1\17\1\16";
	static final String DFA23_specialS =
		"\27\uffff\1\4\10\uffff\1\3\34\uffff\1\2\1\0\22\uffff\1\1\140\uffff}>";
	static final String[] DFA23_transitionS = {
			"\2\13\1\uffff\2\13\22\uffff\1\13\1\uffff\1\43\1\25\1\44\2\uffff\1\42"+
			"\1\31\1\35\1\37\1\32\1\21\1\15\1\22\1\14\12\41\1\20\1\36\1\30\1\23\1"+
			"\24\1\uffff\1\16\32\44\1\27\1\uffff\1\34\1\uffff\1\44\1\40\1\1\1\2\1"+
			"\3\1\4\1\5\1\6\2\44\1\7\10\44\1\10\1\11\1\12\6\44\1\26\1\17\1\33",
			"\1\45",
			"\1\46",
			"\1\47\6\uffff\1\50",
			"\1\51",
			"\1\52",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\1\57",
			"",
			"\1\61\4\uffff\1\60",
			"\1\60\20\uffff\1\63",
			"",
			"",
			"\1\65\2\uffff\1\66",
			"",
			"\1\70",
			"",
			"\1\72",
			"",
			"",
			"\73\75\1\uffff\1\75\1\76\37\75\1\77\uffa2\75",
			"\1\100\1\101",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\0\104",
			"",
			"",
			"",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\23\44\1\114\6\44",
			"\1\116",
			"\1\117",
			"\1\120",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\73\75\1\uffff\1\75\1\76\37\75\1\77\uffa2\75",
			"\73\121\1\123\1\121\1\77\37\121\1\122\uffa2\121",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\124",
			"\1\125",
			"",
			"\1\126",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\130",
			"\1\131",
			"",
			"\1\132",
			"\1\133",
			"\1\134",
			"\73\121\1\123\1\121\1\77\37\121\1\122\uffa2\121",
			"",
			"",
			"\1\135",
			"\1\136",
			"\1\137",
			"",
			"\1\140",
			"\1\141",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\143",
			"\1\144",
			"\1\145",
			"\1\146\17\uffff\1\147\1\150",
			"\1\151",
			"\1\152",
			"\1\153",
			"",
			"\1\154",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"\1\161",
			"\1\162",
			"\1\163",
			"\1\164",
			"\1\165",
			"\1\166",
			"\1\167",
			"\1\170",
			"\1\171",
			"\1\172",
			"\1\173",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\175",
			"\1\176",
			"\1\177",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083",
			"\1\u0084",
			"",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u0089",
			"\1\u008a",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"",
			"\1\u008f",
			"\1\u0090",
			"",
			"\1\u0091",
			"\1\u0092",
			"\1\u0093",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u0097",
			"\1\u0098",
			"",
			"",
			"",
			"\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c",
			"\1\u009d",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\12\44\7\uffff\32\44\4\uffff\1\u009f\1\uffff\32\44",
			"",
			"\1\u00a1\3\uffff\1\u00a2",
			"",
			"\1\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"\1\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"\1\u00ad",
			"\1\u00ae",
			"\1\u00af",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"",
			""
	};

	static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
	static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
	static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
	static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
	static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
	static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
	static final short[][] DFA23_transition;

	static {
		int numStates = DFA23_transitionS.length;
		DFA23_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
		}
	}

	protected class DFA23 extends DFA {

		public DFA23(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 23;
			this.eot = DFA23_eot;
			this.eof = DFA23_eof;
			this.min = DFA23_min;
			this.max = DFA23_max;
			this.accept = DFA23_accept;
			this.special = DFA23_special;
			this.transition = DFA23_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | WS | SL_COMMENT | ML_COMMENT | ARROW | AT | BAR | COLON | COLON_COLON | COLON_EQUAL | COMMA | DOT | DOTDOT | EQUAL | GREATER | GREATER_EQUAL | HASH | LBRACE | LBRACK | LESS | LESS_EQUAL | LPAREN | MINUS | NOT_EQUAL | PLUS | RBRACE | RBRACK | RPAREN | SEMI | SLASH | STAR | BTICK | RANGE_OR_INT | STRING | NON_OCL_STRING | IDENT | COND_IMPL | EQUAL_COND_EXPR | COND_EXPR );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA23_62 = input.LA(1);
						s = -1;
						if ( ((LA23_62 >= '\u0000' && LA23_62 <= ':')||LA23_62=='<'||(LA23_62 >= '>' && LA23_62 <= '\\')||(LA23_62 >= '^' && LA23_62 <= '\uFFFF')) ) {s = 81;}
						else if ( (LA23_62==']') ) {s = 82;}
						else if ( (LA23_62==';') ) {s = 83;}
						else if ( (LA23_62=='=') ) {s = 63;}
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA23_81 = input.LA(1);
						s = -1;
						if ( (LA23_81==']') ) {s = 82;}
						else if ( ((LA23_81 >= '\u0000' && LA23_81 <= ':')||LA23_81=='<'||(LA23_81 >= '>' && LA23_81 <= '\\')||(LA23_81 >= '^' && LA23_81 <= '\uFFFF')) ) {s = 81;}
						else if ( (LA23_81==';') ) {s = 83;}
						else if ( (LA23_81=='=') ) {s = 63;}
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA23_61 = input.LA(1);
						s = -1;
						if ( (LA23_61=='=') ) {s = 62;}
						else if ( ((LA23_61 >= '\u0000' && LA23_61 <= ':')||LA23_61=='<'||(LA23_61 >= '>' && LA23_61 <= '\\')||(LA23_61 >= '^' && LA23_61 <= '\uFFFF')) ) {s = 61;}
						else if ( (LA23_61==']') ) {s = 63;}
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA23_32 = input.LA(1);
						s = -1;
						if ( ((LA23_32 >= '\u0000' && LA23_32 <= '\uFFFF')) ) {s = 68;}
						else s = 67;
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA23_23 = input.LA(1);
						s = -1;
						if ( ((LA23_23 >= '\u0000' && LA23_23 <= ':')||LA23_23=='<'||(LA23_23 >= '>' && LA23_23 <= '\\')||(LA23_23 >= '^' && LA23_23 <= '\uFFFF')) ) {s = 61;}
						else if ( (LA23_23=='=') ) {s = 62;}
						else if ( (LA23_23==']') ) {s = 63;}
						else s = 60;
						if ( s>=0 ) return s;
						break;
			}
			if (state.backtracking>0) {state.failed=true; return -1;}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 23, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}

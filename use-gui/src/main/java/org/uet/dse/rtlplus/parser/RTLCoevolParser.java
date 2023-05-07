// $ANTLR 3.5.1 /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g 2018-01-26 10:28:35

/*
 *  @author Duc-Hanh Dang, Khoa-Hai Nguyen, Xuan-Loi Vu
 */

package org.uet.dse.rtlplus.parser;

import org.tzi.use.parser.base.BaseParser;
import org.uet.dse.rtlplus.parser.ast.coevol.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class RTLCoevolParser extends BaseParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARROW", "AT", "BAR", "COLON", 
		"COLON_COLON", "COLON_EQUAL", "COMMA", "COND_EXPR", "DOT", "DOTDOT", "EQUAL", 
		"GREATER", "GREATER_EQUAL", "HASH", "IDENT", "LBRACE", "LBRACK", "LESS", 
		"LESS_EQUAL", "LPAREN", "MINUS", "ML_COMMENT", "NEWLINE", "NOT_EQUAL", 
		"PLUS", "RBRACE", "RBRACK", "RPAREN", "SEMI", "SLASH", "SL_COMMENT", "STAR", 
		"VOCAB", "WS", "'do'", "'if'", "'while'"
	};
	public static final int EOF=-1;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int ARROW=4;
	public static final int AT=5;
	public static final int BAR=6;
	public static final int COLON=7;
	public static final int COLON_COLON=8;
	public static final int COLON_EQUAL=9;
	public static final int COMMA=10;
	public static final int COND_EXPR=11;
	public static final int DOT=12;
	public static final int DOTDOT=13;
	public static final int EQUAL=14;
	public static final int GREATER=15;
	public static final int GREATER_EQUAL=16;
	public static final int HASH=17;
	public static final int IDENT=18;
	public static final int LBRACE=19;
	public static final int LBRACK=20;
	public static final int LESS=21;
	public static final int LESS_EQUAL=22;
	public static final int LPAREN=23;
	public static final int MINUS=24;
	public static final int ML_COMMENT=25;
	public static final int NEWLINE=26;
	public static final int NOT_EQUAL=27;
	public static final int PLUS=28;
	public static final int RBRACE=29;
	public static final int RBRACK=30;
	public static final int RPAREN=31;
	public static final int SEMI=32;
	public static final int SLASH=33;
	public static final int SL_COMMENT=34;
	public static final int STAR=35;
	public static final int VOCAB=36;
	public static final int WS=37;

	// delegates
	public BaseParser[] getDelegates() {
		return new BaseParser[] {};
	}

	// delegators


	public RTLCoevolParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public RTLCoevolParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return RTLCoevolParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g"; }



	// $ANTLR start "coevolScenario"
	// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:40:1: coevolScenario returns [AstCoevolScenario n] : ( ruleApplication SEMI )* EOF ;
	public final AstCoevolScenario coevolScenario() throws RecognitionException {
		AstCoevolScenario n = null;


		AstRuleApplication ruleApplication1 =null;


			n = new AstCoevolScenario();

		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:44:2: ( ( ruleApplication SEMI )* EOF )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:45:2: ( ruleApplication SEMI )* EOF
			{
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:45:2: ( ruleApplication SEMI )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==IDENT||(LA1_0 >= 38 && LA1_0 <= 39)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:45:3: ruleApplication SEMI
					{
					pushFollow(FOLLOW_ruleApplication_in_coevolScenario60);
					ruleApplication1=ruleApplication();
					state._fsp--;

					match(input,SEMI,FOLLOW_SEMI_in_coevolScenario62); 
					n.addRuleApplication(ruleApplication1);
					}
					break;

				default :
					break loop1;
				}
			}

			match(input,EOF,FOLLOW_EOF_in_coevolScenario71); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return n;
	}
	// $ANTLR end "coevolScenario"



	// $ANTLR start "ruleApplication"
	// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:50:1: ruleApplication returns [AstRuleApplication n] : (simpleApp= simpleApplication |multiApp= multiApplication |restrictedApp= restrictedApplication );
	public final AstRuleApplication ruleApplication() throws RecognitionException {
		AstRuleApplication n = null;


		AstRuleSimpleApplication simpleApp =null;
		AstRuleMultiApplication multiApp =null;
		AstRuleRestrictedApplication restrictedApp =null;

		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:50:47: (simpleApp= simpleApplication |multiApp= multiApplication |restrictedApp= restrictedApplication )
			int alt2=3;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt2=1;
				}
				break;
			case 38:
				{
				alt2=2;
				}
				break;
			case 39:
				{
				alt2=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:51:2: simpleApp= simpleApplication
					{
					pushFollow(FOLLOW_simpleApplication_in_ruleApplication86);
					simpleApp=simpleApplication();
					state._fsp--;

					n = simpleApp;
					}
					break;
				case 2 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:52:4: multiApp= multiApplication
					{
					pushFollow(FOLLOW_multiApplication_in_ruleApplication95);
					multiApp=multiApplication();
					state._fsp--;

					n = multiApp;
					}
					break;
				case 3 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:53:4: restrictedApp= restrictedApplication
					{
					pushFollow(FOLLOW_restrictedApplication_in_ruleApplication104);
					restrictedApp=restrictedApplication();
					state._fsp--;

					n = restrictedApp;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return n;
	}
	// $ANTLR end "ruleApplication"



	// $ANTLR start "simpleApplication"
	// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:56:1: simpleApplication returns [AstRuleSimpleApplication n] : name= IDENT LPAREN RPAREN ;
	public final AstRuleSimpleApplication simpleApplication() throws RecognitionException {
		AstRuleSimpleApplication n = null;


		Token name=null;

		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:56:55: (name= IDENT LPAREN RPAREN )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:57:2: name= IDENT LPAREN RPAREN
			{
			name=(Token)match(input,IDENT,FOLLOW_IDENT_in_simpleApplication121); 
			match(input,LPAREN,FOLLOW_LPAREN_in_simpleApplication123); 
			match(input,RPAREN,FOLLOW_RPAREN_in_simpleApplication125); 
			n = new AstRuleSimpleApplication((name!=null?name.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return n;
	}
	// $ANTLR end "simpleApplication"



	// $ANTLR start "multiApplication"
	// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:61:1: multiApplication returns [AstRuleMultiApplication n] : 'do' (ruleApp= ruleApplication SEMI )+ 'while' cond= COND_EXPR ;
	public final AstRuleMultiApplication multiApplication() throws RecognitionException {
		AstRuleMultiApplication n = null;


		Token cond=null;
		AstRuleApplication ruleApp =null;


			n = new AstRuleMultiApplication();

		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:65:2: ( 'do' (ruleApp= ruleApplication SEMI )+ 'while' cond= COND_EXPR )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:66:2: 'do' (ruleApp= ruleApplication SEMI )+ 'while' cond= COND_EXPR
			{
			match(input,38,FOLLOW_38_in_multiApplication147); 
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:67:2: (ruleApp= ruleApplication SEMI )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==IDENT||(LA3_0 >= 38 && LA3_0 <= 39)) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:67:3: ruleApp= ruleApplication SEMI
					{
					pushFollow(FOLLOW_ruleApplication_in_multiApplication153);
					ruleApp=ruleApplication();
					state._fsp--;

					match(input,SEMI,FOLLOW_SEMI_in_multiApplication155); 
					n.addRuleApplication(ruleApp);
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			match(input,40,FOLLOW_40_in_multiApplication162); 
			cond=(Token)match(input,COND_EXPR,FOLLOW_COND_EXPR_in_multiApplication167); 
			n.setCondition((cond!=null?cond.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return n;
	}
	// $ANTLR end "multiApplication"



	// $ANTLR start "restrictedApplication"
	// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:72:1: restrictedApplication returns [AstRuleRestrictedApplication n] : 'if' cond= COND_EXPR LBRACE (ruleApp= ruleApplication SEMI )* RBRACE ;
	public final AstRuleRestrictedApplication restrictedApplication() throws RecognitionException {
		AstRuleRestrictedApplication n = null;


		Token cond=null;
		AstRuleApplication ruleApp =null;


			n = new AstRuleRestrictedApplication();

		try {
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:76:2: ( 'if' cond= COND_EXPR LBRACE (ruleApp= ruleApplication SEMI )* RBRACE )
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:77:2: 'if' cond= COND_EXPR LBRACE (ruleApp= ruleApplication SEMI )* RBRACE
			{
			match(input,39,FOLLOW_39_in_restrictedApplication189); 
			cond=(Token)match(input,COND_EXPR,FOLLOW_COND_EXPR_in_restrictedApplication194); 
			n.setCondition((cond!=null?cond.getText():null));
			match(input,LBRACE,FOLLOW_LBRACE_in_restrictedApplication199); 
			// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:80:2: (ruleApp= ruleApplication SEMI )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==IDENT||(LA4_0 >= 38 && LA4_0 <= 39)) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /home/pnh/NCKH/workspace-java/RTLPlus/src/org/uet/dse/rtlplus/parser/RTLCoevol.g:80:3: ruleApp= ruleApplication SEMI
					{
					pushFollow(FOLLOW_ruleApplication_in_restrictedApplication207);
					ruleApp=ruleApplication();
					state._fsp--;

					match(input,SEMI,FOLLOW_SEMI_in_restrictedApplication209); 
					n.addRuleApplication(ruleApp);
					}
					break;

				default :
					break loop4;
				}
			}

			match(input,RBRACE,FOLLOW_RBRACE_in_restrictedApplication216); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return n;
	}
	// $ANTLR end "restrictedApplication"

	// Delegated rules



	public static final BitSet FOLLOW_ruleApplication_in_coevolScenario60 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_SEMI_in_coevolScenario62 = new BitSet(new long[]{0x000000C000040000L});
	public static final BitSet FOLLOW_EOF_in_coevolScenario71 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleApplication_in_ruleApplication86 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multiApplication_in_ruleApplication95 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_restrictedApplication_in_ruleApplication104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_simpleApplication121 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_LPAREN_in_simpleApplication123 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_RPAREN_in_simpleApplication125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_38_in_multiApplication147 = new BitSet(new long[]{0x000000C000040000L});
	public static final BitSet FOLLOW_ruleApplication_in_multiApplication153 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_SEMI_in_multiApplication155 = new BitSet(new long[]{0x000001C000040000L});
	public static final BitSet FOLLOW_40_in_multiApplication162 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COND_EXPR_in_multiApplication167 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_39_in_restrictedApplication189 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COND_EXPR_in_restrictedApplication194 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_LBRACE_in_restrictedApplication199 = new BitSet(new long[]{0x000000C020040000L});
	public static final BitSet FOLLOW_ruleApplication_in_restrictedApplication207 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_SEMI_in_restrictedApplication209 = new BitSet(new long[]{0x000000C020040000L});
	public static final BitSet FOLLOW_RBRACE_in_restrictedApplication216 = new BitSet(new long[]{0x0000000000000002L});
}

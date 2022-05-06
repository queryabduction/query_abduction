// Generated from org\gu\dcore\antlr4\EDLG.g4 by ANTLR 4.7
package org.gu.dcore.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EDLGParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, BRACKETED=7, DESCRIPTION=8, 
		STRING=9, WS=10;
	public static final int
		RULE_program = 0, RULE_prule = 1, RULE_exrule = 2, RULE_constraint = 3, 
		RULE_atomset = 4, RULE_atom = 5, RULE_terms = 6, RULE_term = 7, RULE_predicate = 8;
	public static final String[] ruleNames = {
		"program", "prule", "exrule", "constraint", "atomset", "atom", "terms", 
		"term", "predicate"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':-'", "'.'", "'!'", "','", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "BRACKETED", "DESCRIPTION", 
		"STRING", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "EDLG.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EDLGParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(EDLGParser.EOF, 0); }
		public List<PruleContext> prule() {
			return getRuleContexts(PruleContext.class);
		}
		public PruleContext prule(int i) {
			return getRuleContext(PruleContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << BRACKETED) | (1L << DESCRIPTION))) != 0)) {
				{
				{
				setState(18);
				prule();
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(24);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PruleContext extends ParserRuleContext {
		public ExruleContext exrule() {
			return getRuleContext(ExruleContext.class,0);
		}
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public PruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterPrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitPrule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitPrule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PruleContext prule() throws RecognitionException {
		PruleContext _localctx = new PruleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prule);
		try {
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BRACKETED:
			case DESCRIPTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				exrule();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				constraint();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExruleContext extends ParserRuleContext {
		public List<AtomsetContext> atomset() {
			return getRuleContexts(AtomsetContext.class);
		}
		public AtomsetContext atomset(int i) {
			return getRuleContext(AtomsetContext.class,i);
		}
		public ExruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exrule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterExrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitExrule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitExrule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExruleContext exrule() throws RecognitionException {
		ExruleContext _localctx = new ExruleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_exrule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			atomset();
			setState(31);
			match(T__0);
			setState(32);
			atomset();
			setState(33);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public AtomsetContext atomset() {
			return getRuleContext(AtomsetContext.class,0);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__2);
			setState(36);
			match(T__0);
			setState(37);
			atomset();
			setState(38);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomsetContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomsetContext atomset() {
			return getRuleContext(AtomsetContext.class,0);
		}
		public AtomsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterAtomset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitAtomset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitAtomset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomsetContext atomset() throws RecognitionException {
		AtomsetContext _localctx = new AtomsetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atomset);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				atom();
				setState(42);
				match(T__3);
				setState(43);
				atomset();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atom);
		try {
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				predicate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				predicate();
				setState(49);
				match(T__4);
				setState(50);
				terms();
				setState(51);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermsContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public TermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitTerms(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitTerms(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermsContext terms() throws RecognitionException {
		TermsContext _localctx = new TermsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_terms);
		try {
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				term();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				term();
				setState(57);
				match(T__3);
				setState(58);
				terms();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TerminalNode BRACKETED() { return getToken(EDLGParser.BRACKETED, 0); }
		public TerminalNode DESCRIPTION() { return getToken(EDLGParser.DESCRIPTION, 0); }
		public TerminalNode STRING() { return getToken(EDLGParser.STRING, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BRACKETED) | (1L << DESCRIPTION) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public TerminalNode BRACKETED() { return getToken(EDLGParser.BRACKETED, 0); }
		public TerminalNode DESCRIPTION() { return getToken(EDLGParser.DESCRIPTION, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EDLGListener ) ((EDLGListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EDLGVisitor ) return ((EDLGVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if ( !(_la==BRACKETED || _la==DESCRIPTION) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\fE\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\7\2\26"+
		"\n\2\f\2\16\2\31\13\2\3\2\3\2\3\3\3\3\5\3\37\n\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6\60\n\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\78\n\7\3\b\3\b\3\b\3\b\3\b\5\b?\n\b\3\t\3\t\3\n\3\n\3\n\2\2\13"+
		"\2\4\6\b\n\f\16\20\22\2\4\3\2\t\13\3\2\t\n\2@\2\27\3\2\2\2\4\36\3\2\2"+
		"\2\6 \3\2\2\2\b%\3\2\2\2\n/\3\2\2\2\f\67\3\2\2\2\16>\3\2\2\2\20@\3\2\2"+
		"\2\22B\3\2\2\2\24\26\5\4\3\2\25\24\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2"+
		"\27\30\3\2\2\2\30\32\3\2\2\2\31\27\3\2\2\2\32\33\7\2\2\3\33\3\3\2\2\2"+
		"\34\37\5\6\4\2\35\37\5\b\5\2\36\34\3\2\2\2\36\35\3\2\2\2\37\5\3\2\2\2"+
		" !\5\n\6\2!\"\7\3\2\2\"#\5\n\6\2#$\7\4\2\2$\7\3\2\2\2%&\7\5\2\2&\'\7\3"+
		"\2\2\'(\5\n\6\2()\7\4\2\2)\t\3\2\2\2*\60\5\f\7\2+,\5\f\7\2,-\7\6\2\2-"+
		".\5\n\6\2.\60\3\2\2\2/*\3\2\2\2/+\3\2\2\2\60\13\3\2\2\2\618\5\22\n\2\62"+
		"\63\5\22\n\2\63\64\7\7\2\2\64\65\5\16\b\2\65\66\7\b\2\2\668\3\2\2\2\67"+
		"\61\3\2\2\2\67\62\3\2\2\28\r\3\2\2\29?\5\20\t\2:;\5\20\t\2;<\7\6\2\2<"+
		"=\5\16\b\2=?\3\2\2\2>9\3\2\2\2>:\3\2\2\2?\17\3\2\2\2@A\t\2\2\2A\21\3\2"+
		"\2\2BC\t\3\2\2C\23\3\2\2\2\7\27\36/\67>";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
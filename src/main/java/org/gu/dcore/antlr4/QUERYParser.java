// Generated from org\gu\dcore\antlr4\QUERY.g4 by ANTLR 4.7
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
public class QUERYParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, BRACKETED=8, DESCRIPTION=9, 
		STRING=10, WS=11;
	public static final int
		RULE_query = 0, RULE_ansVar = 1, RULE_atomset = 2, RULE_atom = 3, RULE_terms = 4, 
		RULE_term = 5, RULE_predicate = 6;
	public static final String[] ruleNames = {
		"query", "ansVar", "atomset", "atom", "terms", "term", "predicate"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':-'", "'.'", "'?'", "'('", "')'", "'?()'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "BRACKETED", "DESCRIPTION", 
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
	public String getGrammarFileName() { return "QUERY.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QUERYParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public AnsVarContext ansVar() {
			return getRuleContext(AnsVarContext.class,0);
		}
		public AtomsetContext atomset() {
			return getRuleContext(AtomsetContext.class,0);
		}
		public TerminalNode EOF() { return getToken(QUERYParser.EOF, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QUERYVisitor ) return ((QUERYVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			ansVar();
			setState(15);
			match(T__0);
			setState(16);
			atomset();
			setState(17);
			match(T__1);
			setState(18);
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

	public static class AnsVarContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public AnsVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ansVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).enterAnsVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).exitAnsVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QUERYVisitor ) return ((QUERYVisitor<? extends T>)visitor).visitAnsVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnsVarContext ansVar() throws RecognitionException {
		AnsVarContext _localctx = new AnsVarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ansVar);
		try {
			setState(26);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				match(T__2);
				setState(21);
				match(T__3);
				setState(22);
				terms();
				setState(23);
				match(T__4);
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				match(T__5);
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
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).enterAtomset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).exitAtomset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QUERYVisitor ) return ((QUERYVisitor<? extends T>)visitor).visitAtomset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomsetContext atomset() throws RecognitionException {
		AtomsetContext _localctx = new AtomsetContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atomset);
		try {
			setState(33);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				atom();
				setState(30);
				match(T__6);
				setState(31);
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
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QUERYVisitor ) return ((QUERYVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(35);
				predicate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				predicate();
				setState(37);
				match(T__3);
				setState(38);
				terms();
				setState(39);
				match(T__4);
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
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).enterTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).exitTerms(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QUERYVisitor ) return ((QUERYVisitor<? extends T>)visitor).visitTerms(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermsContext terms() throws RecognitionException {
		TermsContext _localctx = new TermsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_terms);
		try {
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				term();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				term();
				setState(45);
				match(T__6);
				setState(46);
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
		public TerminalNode BRACKETED() { return getToken(QUERYParser.BRACKETED, 0); }
		public TerminalNode DESCRIPTION() { return getToken(QUERYParser.DESCRIPTION, 0); }
		public TerminalNode STRING() { return getToken(QUERYParser.STRING, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QUERYVisitor ) return ((QUERYVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
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
		public TerminalNode BRACKETED() { return getToken(QUERYParser.BRACKETED, 0); }
		public TerminalNode DESCRIPTION() { return getToken(QUERYParser.DESCRIPTION, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QUERYListener ) ((QUERYListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QUERYVisitor ) return ((QUERYVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r9\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3\35\n\3\3\4\3\4\3\4\3\4\3\4\5\4$\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5,\n\5\3\6\3\6\3\6\3\6\3\6\5\6\63\n\6\3\7\3\7\3\b\3\b"+
		"\3\b\2\2\t\2\4\6\b\n\f\16\2\4\3\2\n\f\3\2\n\13\2\65\2\20\3\2\2\2\4\34"+
		"\3\2\2\2\6#\3\2\2\2\b+\3\2\2\2\n\62\3\2\2\2\f\64\3\2\2\2\16\66\3\2\2\2"+
		"\20\21\5\4\3\2\21\22\7\3\2\2\22\23\5\6\4\2\23\24\7\4\2\2\24\25\7\2\2\3"+
		"\25\3\3\2\2\2\26\27\7\5\2\2\27\30\7\6\2\2\30\31\5\n\6\2\31\32\7\7\2\2"+
		"\32\35\3\2\2\2\33\35\7\b\2\2\34\26\3\2\2\2\34\33\3\2\2\2\35\5\3\2\2\2"+
		"\36$\5\b\5\2\37 \5\b\5\2 !\7\t\2\2!\"\5\6\4\2\"$\3\2\2\2#\36\3\2\2\2#"+
		"\37\3\2\2\2$\7\3\2\2\2%,\5\16\b\2&\'\5\16\b\2\'(\7\6\2\2()\5\n\6\2)*\7"+
		"\7\2\2*,\3\2\2\2+%\3\2\2\2+&\3\2\2\2,\t\3\2\2\2-\63\5\f\7\2./\5\f\7\2"+
		"/\60\7\t\2\2\60\61\5\n\6\2\61\63\3\2\2\2\62-\3\2\2\2\62.\3\2\2\2\63\13"+
		"\3\2\2\2\64\65\t\2\2\2\65\r\3\2\2\2\66\67\t\3\2\2\67\17\3\2\2\2\6\34#"+
		"+\62";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
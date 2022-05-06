// Generated from org\gu\dcore\antlr4\EDLG.g4 by ANTLR 4.7
package org.gu.dcore.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EDLGParser}.
 */
public interface EDLGListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EDLGParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(EDLGParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(EDLGParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#prule}.
	 * @param ctx the parse tree
	 */
	void enterPrule(EDLGParser.PruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#prule}.
	 * @param ctx the parse tree
	 */
	void exitPrule(EDLGParser.PruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#exrule}.
	 * @param ctx the parse tree
	 */
	void enterExrule(EDLGParser.ExruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#exrule}.
	 * @param ctx the parse tree
	 */
	void exitExrule(EDLGParser.ExruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(EDLGParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(EDLGParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#atomset}.
	 * @param ctx the parse tree
	 */
	void enterAtomset(EDLGParser.AtomsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#atomset}.
	 * @param ctx the parse tree
	 */
	void exitAtomset(EDLGParser.AtomsetContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(EDLGParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(EDLGParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#terms}.
	 * @param ctx the parse tree
	 */
	void enterTerms(EDLGParser.TermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#terms}.
	 * @param ctx the parse tree
	 */
	void exitTerms(EDLGParser.TermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(EDLGParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(EDLGParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link EDLGParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(EDLGParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link EDLGParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(EDLGParser.PredicateContext ctx);
}
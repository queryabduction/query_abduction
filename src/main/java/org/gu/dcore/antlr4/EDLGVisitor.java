// Generated from org\gu\dcore\antlr4\EDLG.g4 by ANTLR 4.7
package org.gu.dcore.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EDLGParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EDLGVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EDLGParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(EDLGParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#prule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrule(EDLGParser.PruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#exrule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExrule(EDLGParser.ExruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint(EDLGParser.ConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#atomset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomset(EDLGParser.AtomsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(EDLGParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#terms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerms(EDLGParser.TermsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(EDLGParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link EDLGParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(EDLGParser.PredicateContext ctx);
}
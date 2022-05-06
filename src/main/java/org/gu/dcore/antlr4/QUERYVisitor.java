// Generated from org\gu\dcore\antlr4\QUERY.g4 by ANTLR 4.7
package org.gu.dcore.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QUERYParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QUERYVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QUERYParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(QUERYParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link QUERYParser#ansVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnsVar(QUERYParser.AnsVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link QUERYParser#atomset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomset(QUERYParser.AtomsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QUERYParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(QUERYParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link QUERYParser#terms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerms(QUERYParser.TermsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QUERYParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(QUERYParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link QUERYParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(QUERYParser.PredicateContext ctx);
}
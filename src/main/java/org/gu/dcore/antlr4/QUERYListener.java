// Generated from org\gu\dcore\antlr4\QUERY.g4 by ANTLR 4.7
package org.gu.dcore.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QUERYParser}.
 */
public interface QUERYListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QUERYParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(QUERYParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QUERYParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(QUERYParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QUERYParser#ansVar}.
	 * @param ctx the parse tree
	 */
	void enterAnsVar(QUERYParser.AnsVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link QUERYParser#ansVar}.
	 * @param ctx the parse tree
	 */
	void exitAnsVar(QUERYParser.AnsVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link QUERYParser#atomset}.
	 * @param ctx the parse tree
	 */
	void enterAtomset(QUERYParser.AtomsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QUERYParser#atomset}.
	 * @param ctx the parse tree
	 */
	void exitAtomset(QUERYParser.AtomsetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QUERYParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(QUERYParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QUERYParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(QUERYParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link QUERYParser#terms}.
	 * @param ctx the parse tree
	 */
	void enterTerms(QUERYParser.TermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QUERYParser#terms}.
	 * @param ctx the parse tree
	 */
	void exitTerms(QUERYParser.TermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QUERYParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(QUERYParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link QUERYParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(QUERYParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link QUERYParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(QUERYParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link QUERYParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(QUERYParser.PredicateContext ctx);
}
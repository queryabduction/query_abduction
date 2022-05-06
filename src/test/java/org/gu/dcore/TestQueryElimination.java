package org.gu.dcore;

import java.util.List;

import org.gu.dcore.model.ConjunctiveQuery;
import org.gu.dcore.model.Program;
import org.gu.dcore.model.Rule;
import org.gu.dcore.parsing.DcoreParser;
import org.gu.dcore.parsing.QueryParser;
import org.gu.dcore.preprocessing.QueryElimination;
import org.gu.dcore.rewriting.ModularizedRewriting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestQueryElimination extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public TestQueryElimination( String testName )
	{
	    super( testName );
	}
	
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
	    return new TestSuite( TestQueryElimination.class );
	}
	
	public void test1() {
    	DcoreParser parser = new DcoreParser();
    	
    	Program P = parser.parse("A(X, Y) :- D(Y, X). B(X) :- A(X, Y).  C(X,Z):-D(X,Y). B(X):-C(X,X).\n");
    	
    	ConjunctiveQuery query = new QueryParser().parse("?(X) :- D(X, Y), D(X, Z).");
    	
    	System.out.println("============");
    	System.out.println(P);
    	System.out.println(query);
    	
    	QueryElimination qe = new QueryElimination(P.getRuleSet());
    	
    	qe.eliminate(query);
    	
    	System.out.println(query);
    	
	    assertTrue( true );		
	}
}

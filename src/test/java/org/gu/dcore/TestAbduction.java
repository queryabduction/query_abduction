package org.gu.dcore;

import java.io.IOException;
import java.util.List;

import org.gu.dcore.abduction.ConcreteAbduction;
import org.gu.dcore.model.AtomSet;
import org.gu.dcore.model.ConjunctiveQuery;
import org.gu.dcore.model.Program;
import org.gu.dcore.parsing.DcoreParser;
import org.gu.dcore.parsing.QueryParser;
import org.gu.dcore.store.DatalogEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.semanticweb.rulewerk.parser.ParsingException;

public class TestAbduction extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestAbduction( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestAbduction.class );
    }

    public void test() throws IOException, ParsingException {
        DcoreParser parser = new DcoreParser();
//        Program P = parser.parse("");
        Program P = parser.parse("A(X) :- B(X, Y).");

        ConjunctiveQuery query = new QueryParser().parse("?() :- A(X),B(X,a).");
//           	ConjunctiveQuery query = new QueryParser().parse("?() :- D(Y), A(X,Y), C(Y).");

        DatalogEngine engine = new DatalogEngine();
        //   	engine.answerAtomicQuery("B(?a,?b)");

//           	engine.addFacts("A(a, b) . B(a, c) . C(b) .");
//        engine.addFacts("B(\"a\",\"c\") .");
//        engine.addFacts("B(\"a\",\"c\") .");
//    	Set<Predicate> abdu = new HashSet<>();
//    	abdu.add(PredicateFactory.instance().getPredicate("D"));
//    	abdu.add(PredicateFactory.instance().getPredicate("R"));

        ConcreteAbduction abduction = new ConcreteAbduction(P.getRuleSet(), query.getBody(), engine);
//    	NormalQueryAbduction abduction = new NormalQueryAbduction(P.getRuleSet(), query, engine);

        //  	System.out.println(engine.answerAtomicQuery("A(a, ?b) ."));
        List<AtomSet> expl = abduction.getExplanations();

        if(expl == null) System.out.println("Trivial Abduction");
        else for(AtomSet atomset : expl) System.out.println(atomset);
    }
}
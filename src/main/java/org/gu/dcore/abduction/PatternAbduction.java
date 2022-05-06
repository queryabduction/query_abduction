package org.gu.dcore.abduction;

import java.io.IOException;
import java.util.*;

import org.gu.dcore.factories.AtomFactory;
import org.gu.dcore.factories.PredicateFactory;
import org.gu.dcore.factories.RuleFactory;
import org.gu.dcore.grd.GraphOfPredicateDependencies;
import org.gu.dcore.grd.IndexedByHeadPredRuleSet;
import org.gu.dcore.model.*;
import org.gu.dcore.reasoning.SinglePieceUnifier;
import org.gu.dcore.reasoning.Unify;
import org.gu.dcore.rewriting.ModularizedRewriting;
import org.gu.dcore.rewriting.RepComparator;
import org.gu.dcore.store.Column;
import org.gu.dcore.store.DatalogEngine;
import org.gu.dcore.store.utils.Utils;
import org.gu.dcore.tuple.Pair;
import org.gu.dcore.tuple.Tuple;
import org.semanticweb.rulewerk.parser.ParsingException;


public class PatternAbduction extends AbstactQueryAbduction {
	
	public PatternAbduction(List<Rule> onto, AtomSet q, DatalogEngine D) {
		super(onto, q, D);
	}
	
	public PatternAbduction(List<Rule> onto, AtomSet q, DatalogEngine D, Set<Predicate> abdu) {
		super(onto, q, D, abdu);
	}	
	
	public List<AtomSet> getExplanations() throws IOException, ParsingException {
    	System.out.println("Observation: " + this.query);
    	
		AtomSet observation = pre_reduce(this.query);
		if(observation.isEmpty()) return null;
		
		List<AtomSet> rewriting_set = new LinkedList<>();		
		LinkedList<AtomSet> exploration_set = new LinkedList<>();
		
		exploration_set.add(observation);		
		
		while(!exploration_set.isEmpty()) {
			AtomSet current = exploration_set.poll();
			
			if(allAbducibles(current)) rewriting_set.add(current);
		
			List<AtomSet> rewritings = new LinkedList<>();
			for(AtomSet rewriting : rewrite(current)) {
				AtomSet reduced_rewriting = pre_reduce(rewriting);
				if(reduced_rewriting.isEmpty()) return null;
				rewritings.add(rewriting);
			}
			
			Utils.removeSubsumed(rewritings, rewriting_set);
			Utils.removeSubsumed(rewritings, exploration_set);
			Utils.removeSubsumed(exploration_set, rewritings);
			Utils.removeSubsumed(rewriting_set, rewritings);
			
			exploration_set.addAll(rewritings);
		}
		
		Utils.computeCoverSet(rewriting_set);

//		List<AtomSet> compact_explanations_set = new LinkedList<>();
//
//		for(AtomSet rewriting : rewriting_set) {
//			List<AtomSet> reduce_result = atomset_reduce(rewriting);
//			if(reduce_result == null) return null;
//			else {
//				boolean pattern_covered = false;
//
//				for(AtomSet atomSet : reduce_result) {
//					if (Utils.isMoreGeneral(atomSet, rewriting)) {
//						compact_explanations_set.add(atomSet);
//						pattern_covered = true;
//					}
//				}
//
//				if(!pattern_covered) {
//					compact_explanations_set.add(rewriting);
//				}
//			}
//		}
//
//		compact_reduce(compact_explanations_set, new RepComparator());
//
//		return compact_explanations_set;
		return rewriting_set;
	}
}

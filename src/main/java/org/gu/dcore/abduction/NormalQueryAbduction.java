package org.gu.dcore.abduction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gu.dcore.factories.RuleFactory;
import org.gu.dcore.model.Atom;
import org.gu.dcore.model.AtomSet;
import org.gu.dcore.model.ConjunctiveQuery;
import org.gu.dcore.model.LiftedAtomSet;
import org.gu.dcore.model.Predicate;
import org.gu.dcore.model.Rule;
import org.gu.dcore.model.Term;
import org.gu.dcore.model.Variable;
import org.gu.dcore.reasoning.Partition;
import org.gu.dcore.rewriting.CompactComparator;
import org.gu.dcore.rewriting.RepComparator;
import org.gu.dcore.store.Column;
import org.gu.dcore.store.DatalogEngine;
import org.gu.dcore.store.utils.Utils;
import org.gu.dcore.tuple.Tuple;
import org.semanticweb.rulewerk.parser.ParsingException;


public class NormalQueryAbduction extends AbstactQueryAbduction {
	public NormalQueryAbduction(List<Rule> onto, AtomSet q, DatalogEngine D) {
		super(onto, q, D);
	}
	
	public NormalQueryAbduction(List<Rule> onto, AtomSet q, DatalogEngine D, Set<Predicate> abdu) {
		super(onto, q, D, abdu);
	}
	
	@Override
	public List<AtomSet> getExplanations() throws IOException, ParsingException {
    	System.out.println("Observation: " + this.query);
    	
		AtomSet observation = pre_reduce(this.query);
		if(observation.isEmpty()) return null;
				
		List<AtomSet> rewriting_set = new LinkedList<>();
		List<AtomSet> compact_explanations_set = new LinkedList<>();
		
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
		
		for(AtomSet rewriting : rewriting_set) {
			compact_explanations_set.add(rewriting);
			List<AtomSet> reduce_result = atomset_reduce(rewriting);
			if(reduce_result == null) return null;
			else compact_explanations_set.addAll(reduce_result);
		}
		
		compact_reduce(compact_explanations_set, new RepComparator());
		
		return compact_explanations_set;
	}
}

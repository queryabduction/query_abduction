package org.gu.dcore.rewriting;
/*
 * Copyright (C) 2018 - 2020 Artificial Intelligence and Semantic Technology, 
 * Griffith University
 * 
 * Contributors:
 * Peng Xiao (sharpen70@gmail.com)
 * Zhe wang
 * Kewen Wang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.gu.dcore.factories.AtomFactory;
import org.gu.dcore.factories.PredicateFactory;
import org.gu.dcore.factories.RuleFactory;
import org.gu.dcore.factories.TermFactory;
import org.gu.dcore.grd.IndexedBlockRuleSet;
import org.gu.dcore.homomorphism.HomoUtils;
import org.gu.dcore.model.Atom;
import org.gu.dcore.model.AtomSet;
import org.gu.dcore.model.ConjunctiveQuery;
import org.gu.dcore.model.Constant;
import org.gu.dcore.model.Predicate;
import org.gu.dcore.model.Rule;
import org.gu.dcore.model.Term;
import org.gu.dcore.model.Variable;
import org.gu.dcore.modularization.BaseMarking;
import org.gu.dcore.modularization.Block;
import org.gu.dcore.modularization.BlockRule;
import org.gu.dcore.modularization.Modularizor;
import org.gu.dcore.modularization.RuleBasedMark;
import org.gu.dcore.reasoning.SinglePieceUnifier;
import org.gu.dcore.reasoning.Unify;
import org.gu.dcore.tuple.Tuple;
import org.gu.dcore.tuple.Tuple6;
import org.gu.dcore.store.utils.Utils;

public class ModularizedRewriting2 {
	private IndexedBlockRuleSet ibr;
	private BlockRule bQr;
	private final Constant blank = TermFactory.instance().createConstant("blank");
	private Set<Integer> selected;
	
	public ModularizedRewriting2(Modularizor modularizor, ConjunctiveQuery q) {
		this.ibr = modularizor.getIndexedBlockOnto();			
				
		Rule Qr = RuleFactory.instance().createQueryRule(q);		
		BaseMarking marking = modularizor.getMarking();
		RuleBasedMark rbm = marking.markQueryRule(Qr);
		
		this.bQr = marking.getBlockRule(Qr, rbm);
	}	
	
	public List<Rule> rewrite() {
		this.selected = new HashSet<>();
		PredicateFactory.instance().rewrite_reset();
		
//		System.out.println(bQr);
		
		List<Rule> result = new LinkedList<>();
		Queue<BlockRule> rewQueue = new LinkedList<>();
		rewQueue.add(bQr);
		
		boolean first = true;
		
		while(!rewQueue.isEmpty()) {
			BlockRule r = rewQueue.poll();
			
			if(!selected.add(r.getRuleIndex())) continue;
		
			AtomSet body = new AtomSet();
			
			for(Block b : r.getBlocks()) {
				body.add(createBlockAtom(b));
				rewriteBlock(r, b, true, result, rewQueue);
			}
			for(Atom a : r.getNormalAtoms()) {
				if(a.getPredicate().getName().equals("ANS")) continue;
				
				Set<BlockRule> brs = this.ibr.getRules(a.getPredicate());
				for(BlockRule nr : brs) {
					if(!nr.isExRule()) rewQueue.add(nr);
				}
				body.add(a);
			}
			
			if(!r.isNormalRule() || first)
				result.add(RuleFactory.instance().createRule(r.getHead(), body));
			else 
				result.add(r);
			
			first = false;
		}
		
		return result;
	}

	/*
	 * restricted, whether to consider the variable in the rule head
	 */
	private void rewriteBlock(BlockRule blockRule, Block block, boolean restricted, List<Rule> result, Queue<BlockRule> rewQueue) {
		Queue<Tuple<BlockRule, Block, Boolean>> bqueue = new LinkedList<>();
		
		bqueue.add(new Tuple<>(blockRule, block, restricted));
		
		while(!bqueue.isEmpty()) {
			Tuple<BlockRule, Block, Boolean> br_b = bqueue.poll();
			BlockRule br = br_b.a;
			Block b = br_b.b;
			
			Atom blockAtom = createBlockAtom(b);
			AtomSet na = new AtomSet(b.getBricks());
			
			Rule init_rule = RuleFactory.instance().createRule(new AtomSet(blockAtom), na);
			result.add(init_rule);
			
			Queue<Tuple6<ArrayList<Term>, AtomSet, AtomSet, AtomSet, AtomSet, Map<Atom, Set<Integer>>>> queue = new LinkedList<>();
			queue.add(new Tuple6<>(blockAtom.getTerms(), br.getBody(), na, new AtomSet(), new AtomSet(), new HashMap<>()));
			
			List<AtomSet> rewrited = new LinkedList<>();
			
			while(!queue.isEmpty()) {
				Tuple6<ArrayList<Term>, AtomSet, AtomSet, AtomSet, AtomSet, Map<Atom, Set<Integer>>> t = queue.poll();	
				
				AtomSet rewrite_target = t.c;
				Set<BlockRule> rs = this.ibr.getRules(rewrite_target);
				
				for(BlockRule hr : rs) {
					Set<Variable> restricted_var = br_b.c ? blockRule.getFrontierVariables() : new HashSet<>();
					List<SinglePieceUnifier> unifiers = Unify.getSinglePieceUnifiers(t.c, t.b, hr, restricted_var);
	//				List<AggregateUnifier> unifiers = Unify.getAggregatedPieceUnifier(t.c, t.b, hr, restricted_var);
					
					if(!unifiers.isEmpty()) {
						boolean ex_rew = hr.isExRule();
						
						AtomSet next_target = new AtomSet();
						
						AtomSet tails = new AtomSet();
						
						for(Block hb : hr.getPassBlocks()) {
							next_target.addAll(hb.getBricks());
						}
						
						for(Block hb : hr.getMblocks()) {
							tails.add(createBlockAtom(hb));
							bqueue.add(new Tuple<>(hr, hb, false));
						}
						
						for(Atom a : hr.getNormalAtoms()) {
							Set<BlockRule> brs = this.ibr.getRules(a.getPredicate());
							if(brs != null) rewQueue.addAll(brs);
	
						}
						tails.addAll(hr.getNormalAtoms());					
	
						if(!ex_rew && selected.add(hr.getRuleIndex())) {
							if(hr.getMblocks().isEmpty()) result.add(hr);
							else {
								Rule n_rule = RuleFactory.instance().
									createRule(hr.getHead(), next_target, tails);
								result.add(n_rule);
							}
						}
						
						for(SinglePieceUnifier u : unifiers) {
							Map<Atom, Set<Integer>> trace_used_nonex_rules = new HashMap<>(t.f);
							
							if(!ex_rew) {
								Atom a = u.getB().iterator().next();
								Set<Integer> used_rules = trace_used_nonex_rules.get(a);
								
								if(used_rules != null && used_rules.contains(hr.getRuleIndex())) continue;
							}
							
						    AtomSet rew_bbody = u.getImageOf(t.c, 0);
						    
						    AtomSet rew_hbody = u.getImageOf(hr.getBody(), 1);
							AtomSet rew_current_target = u.getImageOf(next_target, 1);
				
							AtomSet up = u.getImageOfPiece();
						
							rew_bbody = HomoUtils.minus(rew_bbody, up);
							AtomSet a_rewriting = HomoUtils.simple_union(rew_bbody, rew_hbody);
							AtomSet rewriting = HomoUtils.simple_union(rew_bbody, rew_current_target);
							
							AtomSet o_rewriting = RewriteUtils.rewrite(t.b, hr.getBody(), u);
							
							if(ex_rew)  {
								boolean subsumed = false;
								//Remove redundant rewritings
								Iterator<AtomSet> it = rewrited.iterator();
								while(it.hasNext()) {
									AtomSet rew = it.next();
									
									if(Utils.isMoreGeneral(rew, a_rewriting)) {
										subsumed = true; break;
									}
									if(Utils.isMoreGeneral(a_rewriting, rew)) {
										it.remove();
									}
								}
								if(!subsumed) {
									rewrited.add(a_rewriting);
								}
								else continue;
							}							
							
							Set<Term> eliminated = new HashSet<>();
							for(Variable v : hr.getExistentials()) {
								eliminated.add(u.getImageOf(v, 1));
							}
							
							ArrayList<Term> rw_t = new ArrayList<>();
							for(int i = 0; i < t.a.size(); i++) {
								Term _t = t.a.get(i);
								if(eliminated.contains(_t)) rw_t.add(blank);
								else rw_t.add(u.getImageOf(_t, 0));
							}
	
							AtomSet uc = u.getImageOf(tails, 1);
							
							uc.addAll(t.d);
							
							if(ex_rew) {							
								if(!t.e.isEmpty()) {
									boolean all_hit = true;
									for(Atom a : t.e) {
										if(!u.getB().contains(a)) {
											all_hit = false;
											break;
										}
									}
									if(!all_hit) continue;
								}
								Atom newhead =  AtomFactory.instance().createAtom(blockAtom.getPredicate(), rw_t);
								Rule rw_rule = RuleFactory.instance().createRule(new AtomSet(newhead), rewriting, uc);
//								System.out.println(rw_rule);
								result.add(rw_rule);
							}
							
							if(!next_target.isEmpty()) {
								AtomSet rewrited_target;
								if(ex_rew) {
									for(Atom a : u.getB()) trace_used_nonex_rules.remove(a);									
									rewrited_target = new AtomSet();
								}
								else {
									rewrited_target = new AtomSet(t.e);
									rewrited_target.addAll(rew_current_target);
									
									Atom a = u.getB().iterator().next();
									Set<Integer> trace = trace_used_nonex_rules.get(a);
									Set<Integer> used_rules;
									if(trace != null) used_rules = new HashSet<>(trace);
									else used_rules = new HashSet<>();
									
									used_rules.add(hr.getRuleIndex());
									trace_used_nonex_rules.remove(a);
									
									for(Atom target_atom : rew_current_target) {
										trace_used_nonex_rules.put(target_atom, used_rules);
									}
								}
//								System.out.println(rewriting);
								queue.add(new Tuple6<>(rw_t, o_rewriting, rewriting, uc, rewrited_target, trace_used_nonex_rules));
							}	
						}	
					}
				}
			}
		}
	}
	
	private Atom createBlockAtom(Block b) {
		Set<Term> variables = b.getVariables();
		ArrayList<Term> atom_t = new ArrayList<>(variables);;
		
		Predicate blockPred = PredicateFactory.instance().createBlockPredicate(b.getBlockName(), atom_t.size());
		Atom blockAtom = AtomFactory.instance().createAtom(blockPred, variables);	
		
		return blockAtom;
	}
}
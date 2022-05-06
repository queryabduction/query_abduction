package org.gu.dcore.abduction;

import java.io.IOException;
import java.util.*;

import org.gu.dcore.factories.AtomFactory;
import org.gu.dcore.factories.TermFactory;
import org.gu.dcore.grd.IndexedByHeadPredRuleSet;
import org.gu.dcore.model.Atom;
import org.gu.dcore.model.AtomSet;
import org.gu.dcore.model.ConjunctiveQuery;
import org.gu.dcore.model.LiftedAtomSet;
import org.gu.dcore.model.Predicate;
import org.gu.dcore.model.RepConstant;
import org.gu.dcore.model.Rule;
import org.gu.dcore.model.Term;
import org.gu.dcore.model.Variable;
import org.gu.dcore.reasoning.Partition;
import org.gu.dcore.reasoning.SinglePieceUnifier;
import org.gu.dcore.reasoning.Unify;
import org.gu.dcore.rewriting.CompactComparator;
import org.gu.dcore.rewriting.Comparator;
import org.gu.dcore.rewriting.RepComparator;
import org.gu.dcore.rewriting.RewriteUtils;
import org.gu.dcore.store.Column;
import org.gu.dcore.store.DatalogEngine;
import org.gu.dcore.store.utils.Utils;
import org.gu.dcore.tuple.Tuple;
import org.semanticweb.rulewerk.parser.ParsingException;

public abstract class AbstactQueryAbduction implements QueryAbduction {
	protected AtomSet query;
	protected List<Rule> ontology;
	protected DatalogEngine store;
	protected Set<Predicate> abducibles;
	
	protected IndexedByHeadPredRuleSet irs;
	
	protected boolean abduce_all = true;
	
	public AbstactQueryAbduction(List<Rule> onto, AtomSet q, DatalogEngine D) {		
		this.store = D;
		this.ontology = onto;
		this.query = q;
		
		this.irs = new IndexedByHeadPredRuleSet(onto);
	}
	
	public AbstactQueryAbduction(List<Rule> onto, AtomSet q, DatalogEngine D, Set<Predicate> abdu) {
		this(onto, q, D);
		
		this.abduce_all = false;
		this.abducibles = abdu;
	}
	
	protected LiftedAtomSet liftAtomSet(AtomSet A, Map<Variable, Integer> var_index, boolean[] selected_atoms, Column column) {
		Map<Term, Term> repConstant_map = new HashMap<>();
		ArrayList<Integer> rep_index = new ArrayList<>();
		
		boolean[] position_blank = column.getPosition_blank();
		boolean[] actual_occupy = new boolean[column.getArity()];
		
		int map_index = 0;
		
		AtomSet atomset_lifted = new AtomSet();
		for(int i = 0; i < A.size(); i++) {
			if(!selected_atoms[i]) {
				Atom a = A.getAtom(i);
				Predicate pred = a.getPredicate();
				ArrayList<Term> terms = a.getTerms();
				ArrayList<Term> new_terms = new ArrayList<>(); 
				
				for(int ti = 0; ti < terms.size(); ti++) {
					Term t = terms.get(ti);
					if(t.isVariable()) {
						Term c = repConstant_map.get(t);
						if(c == null) {
							Integer v_idx = var_index.get(t);
							if(v_idx != null && position_blank[v_idx]) {
								actual_occupy[v_idx] = true;
								RepConstant rc = TermFactory.instance().getRepConstant(map_index++);
								repConstant_map.put(t, rc);
								rep_index.add(v_idx);
								new_terms.add(rc);
							}
							else new_terms.add(t);
						}
						else new_terms.add(c);

					}
					else new_terms.add(t);
				}
				Atom liftedAtom = AtomFactory.instance().createAtom(pred, new_terms);
				atomset_lifted.add(liftedAtom);
			}
		}
		
		column.distinct(actual_occupy);
		column.setRepMap(rep_index);
		
		return new LiftedAtomSet(atomset_lifted, column);
	}
	
	protected LiftedAtomSet liftAtomSet(AtomSet A, Map<Variable, Integer> var_index, boolean[] selected_atoms, 
			Column column, ArrayList<Column> columns) {
		Map<Term, Term> repConstant_map = new HashMap<>();
		ArrayList<Integer> rep_index = new ArrayList<>();
		
		boolean[] position_blank = column.getPosition_blank();
		boolean[] actual_occupy = new boolean[column.getArity()];
		
		int map_index = 0;
		
		AtomSet atomset_lifted = new AtomSet();
		Set<Variable> mapped_variables = new HashSet<>();
		
		for(int i = 0; i < A.size(); i++) {
			Atom a = A.getAtom(i);
			if(!selected_atoms[i]) {
				Predicate pred = a.getPredicate();
				ArrayList<Term> terms = a.getTerms();
				ArrayList<Term> new_terms = new ArrayList<>(); 
				
				for(int ti = 0; ti < terms.size(); ti++) {
					Term t = terms.get(ti);
					if(t.isVariable()) {
						Term c = repConstant_map.get(t);
						if(c == null) {
							Integer v_idx = var_index.get(t);
							if(v_idx != null && position_blank[v_idx]) {
								actual_occupy[v_idx] = true;
								RepConstant rc = TermFactory.instance().getRepConstant(map_index++);
								repConstant_map.put(t, rc);
								rep_index.add(v_idx);
								new_terms.add(rc);
							}
							else new_terms.add(t);
						}
						else new_terms.add(c);

					}
					else new_terms.add(t);
				}
				Atom liftedAtom = AtomFactory.instance().createAtom(pred, new_terms);
				atomset_lifted.add(liftedAtom);
			}
			else mapped_variables.addAll(a.getVariables());
		}
		
		for(int i = 0; i < A.size(); i++) {
			if(!selected_atoms[i]) {
				Atom a = A.getAtom(i);
				if(mapped_variables.containsAll(a.getVariables())) {
					column.outerJoin(columns.get(i));
				}
			}
		}
		
		column.distinct(actual_occupy);
		column.setRepMap(rep_index);
		
		return new LiftedAtomSet(atomset_lifted, column);
	}
	
	protected boolean allAbducibles(AtomSet atomset) {
		if(this.abduce_all) return true;
		
		for(Atom a : atomset) {
			if(!this.abducibles.contains(a.getPredicate()))
				return false;
		}
		
		return true;
	}
	
	protected List<AtomSet> rewrite(AtomSet atomset) {
		List<AtomSet> rewritings = new LinkedList<>();
		
		for(Atom a : atomset) {
			Set<Rule> rules_to_rewrite = this.irs.getRulesByPredicate(a.getPredicate());
			if(!this.abduce_all && !this.abducibles.contains(a.getPredicate()) &&
					rules_to_rewrite.isEmpty()) return new LinkedList<>();
			
			for(Rule r : rules_to_rewrite) {
				List<SinglePieceUnifier> unifiers = Unify.getSinglePieceUnifiers(atomset, r);
				
				for(SinglePieceUnifier u : unifiers) {
					 rewritings.add(RewriteUtils.rewrite(atomset, r.getBody(), u));
				}
			}	
		}
		
		return rewritings;
	}
	
	protected AtomSet pre_reduce(AtomSet obs) throws IOException, ParsingException {
		AtomSet result = new AtomSet();
		
		for(Atom a : obs) {
			if(a.getVariables().size() == 0) {
				Column col = this.store.answerAtomicQuery(a, new ArrayList<>());
				if(col.size() == 0) result.add(a);
			}
			else result.add(a);
		}
		
		return result;
	}

	protected List<AtomSet> atomset_reduce(AtomSet e) throws IOException, ParsingException {
		int size = e.size();

		/* build the index of variable in retrieved table */
		Set<Variable> vars = e.getJoinVariables();
		Map<Variable, Integer> var_index = new HashMap<>();
		int index = 0;
		for(Variable v : vars) {
			var_index.put(v, index++);
		}

		e.resort();

		ArrayList<Column> columns = new ArrayList<>();

		for(int i = 0; i < size; i++) {
			Atom a = e.getAtom(i);
			int[] mapping = new int[a.getTerms().size()];
			for(int m = 0; m < a.getTerms().size(); m++) {
				Term t = a.getTerm(m);
				if(t instanceof Variable) {
					Variable v = (Variable)a.getTerm(m);
					Integer vi = var_index.get(v);
					if(vi != null) mapping[m] = vi;
					else mapping[m] = -1;
				}
				else mapping[m] = -1;
			}
			columns.add(this.store.answerAtomicQuery(a, mapping, vars.size()));
		}

		LinkedList<Tuple<Column, boolean[], Integer>> queue = new LinkedList<>();
		List<AtomSet> result = new LinkedList<>();

		for(int i = 0; i < size; i++) {
			boolean[] selected_atoms = new boolean[size];
			selected_atoms[i] = true;
			Column column = columns.get(i);
			if(column.size() != 0) {
				LiftedAtomSet atomset = liftAtomSet(e, var_index, selected_atoms, columns.get(i).getCopy(), columns);
				if(atomset.isEmpty()) return null;
				if(atomset.getColumn().size() != 0) result.add(atomset);
				queue.add(new Tuple<>(columns.get(i), selected_atoms, i + 1));
			}
		}

		while(!queue.isEmpty()) {
			Tuple<Column, boolean[], Integer> current_t = queue.poll();
			Column cur_column = current_t.a;
			boolean[] selected_atoms = current_t.b;
			int level = current_t.c;

			if(level < size) {
				Column next_column = columns.get(level);
				if(next_column.size() != 0) {
					Column join_column = Utils.innerJoinColumn(cur_column, next_column);

					boolean[] next_selected_atoms = selected_atoms.clone();
					next_selected_atoms[level] = true;
					if(join_column.size() != 0) {
						LiftedAtomSet atomset = liftAtomSet(e, var_index, next_selected_atoms, join_column, columns);
						if(atomset.isEmpty()) return null;
						if(atomset.getColumn().size() != 0) result.add(atomset);
						queue.add(new Tuple<>(join_column, next_selected_atoms, level + 1));

					}
				}
				boolean[] next_selected_atoms = selected_atoms.clone();
				next_selected_atoms[level] = false;
				queue.add(new Tuple<>(cur_column, next_selected_atoms, level + 1));
			}
		}
		return result;
	}

	protected void compact_reduce(List<AtomSet> rewritings, Comparator cmp) {
		Iterator<AtomSet> it1 = rewritings.iterator();

		while(it1.hasNext()) {
			AtomSet rew1 = it1.next();
			Iterator<AtomSet> it2 = rewritings.iterator();
			while(it2.hasNext()) {
				AtomSet rew2 = it2.next();
				if(!rew1.equals(rew2)) {
					if(Utils.isMoreGeneral(rew2, rew1)) {
						it1.remove();
						break;
					}

					if (rew1 instanceof  LiftedAtomSet && rew2 instanceof LiftedAtomSet) {
						CompactComparator cc = new CompactComparator(rew2, rew1);
						List<Partition> parts = cc.getCompactUnifiers(cmp);
						if(!parts.isEmpty()) {
							LiftedAtomSet lrew1 = (LiftedAtomSet) rew1;
							LiftedAtomSet lrew2 = (LiftedAtomSet) rew2;

							Utils.refineCompactExplanation(lrew1, lrew2, parts);
						}
					}
					else {
						if (cmp.compare(rew2, rew1)) {
							it1.remove();
							break;
						}
					}
//					if(!(rew2 instanceof LiftedAtomSet)) {
//						if(new RepComparator().compare(rew2, rew1)) {
//							it1.remove();
//							break;
//						}
//					}
//					else  if (rew1 instanceof LiftedAtomSet) {
//						CompactComparator cc = new CompactComparator(rew2, rew1);
//						List<Partition> parts = cc.getCompactUnifiers(cmp);
//						if (!parts.isEmpty()) {
//							LiftedAtomSet lrew1 = (LiftedAtomSet) rew1;
//							LiftedAtomSet lrew2 = (LiftedAtomSet) rew2;
//
//							Utils.refineCompactExplanation(lrew1, lrew2, parts);
//						}
//					}
				}
			}
		}
	}
}

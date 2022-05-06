package org.gu.dcore.rewriting;

import java.util.ArrayList;

import org.gu.dcore.model.Atom;
import org.gu.dcore.model.AtomSet;
import org.gu.dcore.model.RepConstant;
import org.gu.dcore.model.Term;
import org.gu.dcore.reasoning.NormalSubstitution;

public class MinComparator implements Comparator {
	public MinComparator() {

	}
	
	@Override
	public boolean compare(AtomSet source, AtomSet target) {
		if(source.size() >= target.size()) return false;
		
		ArrayList<ArrayList<NormalSubstitution>> renmaings = getSubsitutionsForSourceAtoms(source, target);
		if(renmaings == null) return false;
		
		return backtrack(source, target, renmaings); 
	}
	
	/* renaming: whether the substitution for comparison is a renaming */
	private boolean backtrack(AtomSet source, AtomSet target, 
			ArrayList<ArrayList<NormalSubstitution>> substitutionsPerLv) {
		int level_size = source.size();
				
		NormalSubstitution[] subsCurrentPath = new NormalSubstitution[level_size + 1];
		int[] directions = new int[level_size];
		
		for(int i = 0; i < level_size; i++) {
			directions[i] = -1;
		}
		
		subsCurrentPath[0] = new NormalSubstitution();
		
		int level = 0;		
		
		while(level >= 0 && level < level_size) {
			int dir = directions[level];
			NormalSubstitution cur_sub = subsCurrentPath[level];
			ArrayList<NormalSubstitution> subs = substitutionsPerLv.get(level);
			
			int i = dir + 1;
			for(; i < subs.size(); i++) {
				NormalSubstitution lv_sub = subs.get(i);
				NormalSubstitution merged_sub = cur_sub.add(lv_sub, true);
				if(merged_sub != null) {
					cur_sub = merged_sub;
					break;
				}
			}
			if(i >= subs.size()) {
				directions[level] = -1;
				level--;
			}
			else {
				directions[level] = i;
				level++;
				subsCurrentPath[level] = cur_sub;
			}
		}
		
		return level > 0;
	}
	
	private NormalSubstitution match(Atom source, Atom target) {
		if(!source.getPredicate().equals(target.getPredicate())) return null;
		
		NormalSubstitution sub = new NormalSubstitution();
		
		for(int i = 0; i < source.getTerms().size(); i++) {
			Term st = source.getTerm(i);
			Term tt = target.getTerm(i);
			
			if(st instanceof RepConstant) {
				if(!st.equals(tt)) return null;
			}
			else {
				if(!sub.add(st, tt, true)) return null;
			}
		}
		
		return sub;
	}
	
	private ArrayList<ArrayList<NormalSubstitution>> getSubsitutionsForSourceAtoms(AtomSet source, AtomSet target) {
		int size = source.size();
		ArrayList<ArrayList<NormalSubstitution>> substitutionsPerLv = new ArrayList<>(size);
		
		for(int i = 0; i < size; i++) {
			substitutionsPerLv.add(null);
		}
		
		for(int i = 0; i < source.size(); i++) {
			ArrayList<NormalSubstitution> subs = new ArrayList<>();
			for(int j = 0; j < target.size(); j++) {
				Atom sa = source.getAtom(i);
				Atom sb = target.getAtom(j);
				NormalSubstitution sub = match(sa, sb);
				if(sub != null) subs.add(sub);
			}
			if(subs.isEmpty()) return null;
			else substitutionsPerLv.set(i, subs);
		}
		return substitutionsPerLv;
	}
}

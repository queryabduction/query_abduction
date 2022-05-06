package org.gu.dcore.grd;
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
import java.util.HashSet;
import java.util.Set;

import org.gu.dcore.model.Predicate;

public class PredPosition {
	private Predicate predicate;
	private Set<Integer> indice;
	
	public PredPosition(Predicate p, Set<Integer> indice) {
		this.predicate = p;
		this.indice = indice;
	}
	
	public PredPosition(Predicate p, int index) {
		this.predicate = p;
		this.indice = new HashSet<>();
		this.indice.add(index);
	}
	
	public Predicate getPredicate() {
		return this.predicate;
	}
	
	public Set<Integer> getIndice() {
		return this.indice;
	}
	
	@Override
	public String toString() {
		return "" + this.predicate + this.indice;
	}
	
	@Override
	public int hashCode() {
		return this.predicate.hashCode() + this.indice.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PredPosition)) return false;
		PredPosition _obj = (PredPosition)obj;
		
		if(this.predicate.equals(_obj.predicate)) 
			return this.indice.equals(_obj.indice);
		else return false;
	}
}

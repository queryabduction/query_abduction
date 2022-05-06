package org.gu.dcore.grd;

import org.gu.dcore.model.Predicate;

public class NPredPosition {
	private Predicate predicate;
	private int index;

	public NPredPosition(Predicate p, int index) {
		this.predicate = p;
		this.index = index;
	}

	public Predicate getPredicate() {
		return this.predicate;
	}

	public int getIndex() {
		return this.index;
	}

	@Override
	public String toString() {
		return "" + this.predicate + this.index;
	}

	@Override
	public int hashCode() {
		return this.predicate.hashCode() + this.index;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NPredPosition))
			return false;
		NPredPosition _obj = (NPredPosition) obj;

		if (this.predicate.equals(_obj.predicate))
			return this.index == _obj.index;
		else
			return false;
	}
}

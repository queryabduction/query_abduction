package org.gu.dcore.parsing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomVisitorEx;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitorEx;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;

public class DcoreOWL2Filter implements Predicate<OWLAxiom> {
	public DcoreOWL2Filter() {

	}
	
	public boolean isDGLPClassExp(OWLClassExpression exp) {
		OWLClassExpressionVisitorEx<Boolean> ev = new OWLClassExpressionVisitorEx<Boolean>() {
			@Override
			public Boolean visit(OWLClass ce) {
				return true;
			}
			@Override
			public Boolean visit(OWLObjectSomeValuesFrom ce) {
				return true;
			}
			@Override
			public Boolean visit(OWLObjectUnionOf ce) {
				return false;
			}
			@Override
			public Boolean visit(OWLObjectIntersectionOf ce) {
				return true;			
			}			
		};
		
		Boolean b = exp.accept(ev);
		if(b == null) return false;
		else return b;
	}
	
	private OWLAxiomVisitorEx<Boolean> av = new OWLAxiomVisitorEx<Boolean>() {
		public Boolean visit(OWLClassAssertionAxiom axiom) {
			return true;
		};
		
		public Boolean visit(OWLObjectPropertyAssertionAxiom axiom) {
			return true;
		};
		
		/* We don't consider constraints at this stage */
		
		public Boolean visit(OWLDisjointClassesAxiom axiom) {
			return false;
		};
			
		public Boolean visit(OWLDisjointObjectPropertiesAxiom axiom) {
			return false;
		};
		
		public Boolean visit(OWLObjectPropertyDomainAxiom axiom) {
			OWLClassExpression exp1 = axiom.getDomain();
			return isDGLPClassExp(exp1);
		}
		
		public Boolean visit(OWLObjectPropertyRangeAxiom axiom) {
			OWLClassExpression exp1 = axiom.getRange();
			return isDGLPClassExp(exp1);
		}
		
		public Boolean visit(OWLEquivalentClassesAxiom axiom) {
			List<OWLClassExpression> exps = axiom.classExpressions().collect(Collectors.toList());
			for(OWLClassExpression e : exps) {
				if(!isDGLPClassExp(e)) return false;
			}
			return true;
		};
		
		public Boolean visit(OWLSubClassOfAxiom axiom) {
			OWLClassExpression exp1 = axiom.getSubClass();
			OWLClassExpression exp2 = axiom.getSuperClass();
			
			return isDGLPClassExp(exp1) && isDGLPClassExp(exp2);
		};
		
		public Boolean visit(OWLSubObjectPropertyOfAxiom axiom) {
			return true;
		};
	};
	
	public boolean test(OWLAxiom t) {
		Boolean b = t.accept(av);
		if(b == null) return false;
		return b;
	}
}

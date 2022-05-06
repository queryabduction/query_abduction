package org.gu.dcore.abduction;

import java.io.IOException;
import java.util.List;

import org.gu.dcore.model.AtomSet;
import org.semanticweb.rulewerk.parser.ParsingException;


public interface QueryAbduction {
	List<AtomSet> getExplanations() throws IOException, ParsingException;
}

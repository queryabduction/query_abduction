package org.gu.dcore;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.gu.dcore.abduction.NormalQueryAbduction;
import org.gu.dcore.abduction.PatternAbduction;
import org.gu.dcore.abduction.QueryAbduction;
import org.gu.dcore.factories.RuleFactory;
import org.gu.dcore.abduction.ConcreteAbduction;
import org.gu.dcore.model.AtomSet;
import org.gu.dcore.model.ConjunctiveQuery;
import org.gu.dcore.model.LiftedAtomSet;
import org.gu.dcore.model.Program;
import org.gu.dcore.model.Rule;
import org.gu.dcore.parsing.DcoreParser;
import org.gu.dcore.parsing.QueryParser;
import org.gu.dcore.store.Column;
import org.gu.dcore.store.DatalogEngine;
import org.semanticweb.rulewerk.parser.ParsingException;

public class Abduction {
	public static void main(String[] args) throws IOException, ParsingException {
		String ontologyfile = null;
		String queriesfile = null;
		String datafile = null;

		int abdu_mode = 0;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				String flag = args[i].substring(1);
				if (flag.equals("o")) {
					ontologyfile = args[++i];
				}
				if (flag.equals("m"))
					abdu_mode = Integer.parseInt(args[++i]);
				if (flag.equals("d"))
					datafile = args[++i];
				if (flag.equals("q")) {
					queriesfile = args[++i];
				}
			}
		}

		if (ontologyfile == null || datafile == null || queriesfile == null) {
			System.out.println("Missing input !");
			return;
		}

		DcoreParser parser = new DcoreParser();
		Program P = parser.parseFile(ontologyfile);
		System.out.println("Finish Parsing Files ...");

		System.out.println(P);
		List<Rule> ruleset = P.getRuleSet();

		long start, end;

		start = System.currentTimeMillis();
		DatalogEngine engine = new DatalogEngine();
		engine.addSourceFromCSVDir(datafile);
		end = System.currentTimeMillis();
		System.out.println("Finish Loading data, cost " + (end - start) + " ms");

		Scanner scn = new Scanner(new File(queriesfile));

		while (scn.hasNextLine()) {
			String line = scn.nextLine();

			ConjunctiveQuery query = new QueryParser(parser.getDefaultPrefix()).parse(line);
			QueryAbduction abduction;

			Rule qr = RuleFactory.instance().createQueryRule(query);
			AtomSet obs = qr.getBody();

			switch (abdu_mode) {
				case 1:
					abduction = new ConcreteAbduction(ruleset, obs, engine);
					break;
				case 2:
					abduction = new PatternAbduction(ruleset, obs, engine);
					break;
				default:
					abduction = new NormalQueryAbduction(ruleset, obs, engine);
					break;
			}

			System.out.println("Abduction on query: " + query.toString());

			start = System.currentTimeMillis();
			List<AtomSet> expl = abduction.getExplanations();
			end = System.currentTimeMillis();

			if (expl == null) {
				System.out.println("The observation is already satisfied");
			} else {

				int full_number = 0;
				int num_atoms = 0;
				int num_atoms_in_rules = 0;

				for (AtomSet atomset : expl) {
					System.out.println(atomset.toShort());
					// System.out.println(atomset.toString());
					num_atoms_in_rules += atomset.size();

					if (atomset instanceof LiftedAtomSet) {
						Column col = ((LiftedAtomSet) atomset).getColumn();
						full_number += col.size();
						num_atoms += col.size() * atomset.size();
					} else {
						full_number++;
						num_atoms += atomset.size();
					}
				}
				System.out.println("Number of Explanations: (Compact) " + expl.size() + " (Full) " + full_number
						+ " Size(Compact): " + num_atoms_in_rules + " Size(Full): " + num_atoms + " cost: "
						+ (end - start) + " ms");
			}
		}

		scn.close();
	}
}

package org.gu.dcore.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.gu.dcore.model.Atom;
import org.gu.dcore.model.Rule;
import org.semanticweb.rulewerk.core.model.api.PositiveLiteral;
import org.semanticweb.rulewerk.core.model.api.QueryResult;
import org.semanticweb.rulewerk.core.model.api.Statement;
import org.semanticweb.rulewerk.core.reasoner.Algorithm;
import org.semanticweb.rulewerk.core.reasoner.KnowledgeBase;
import org.semanticweb.rulewerk.core.reasoner.QueryResultIterator;
import org.semanticweb.rulewerk.parser.ParsingException;
import org.semanticweb.rulewerk.parser.RuleParser;
import org.semanticweb.rulewerk.reasoner.vlog.VLogReasoner;

public class DatalogEngine {
	private KnowledgeBase kb = null;
	private VLogReasoner reasoner = null;
	
	public DatalogEngine() throws ParsingException {
		configureLogging(Level.OFF);
		
		kb = new KnowledgeBase();
	}
	
	public void addRules(List<Rule> rules) throws ParsingException {
		String import_str = "";
		
		for(Rule r :rules) {
			import_str += r.toVLog() + "\n";
		}		
		
//		System.out.print(import_str);
		addRules(import_str);
	}
	
	public void addRules(String rules) throws ParsingException {
		RuleParser.parseInto(kb, rules);
	}
	
	public void setSkolemAlgorithm() {
		if(reasoner == null) {
			reasoner = new VLogReasoner(kb);
		}
		reasoner.setAlgorithm(Algorithm.SKOLEM_CHASE);
	}
	
//	public void load() throws IOException {
//		if(reasoner == null) {
//			reasoner = new VLogReasoner(kb);
//		}
//		reasoner.load();
//	}
	
	public void materialize() throws IOException {
		if(reasoner == null) {
			reasoner = new VLogReasoner(kb);
		}
		reasoner.reason();
	}
	
	public Column answerAtomicQuery(Atom atom, int[] mapping, int arity) throws IOException, ParsingException {
		PositiveLiteral query = RuleParser.parsePositiveLiteral(atom.toVLog());
			
		if(reasoner == null) materialize();
		
		final QueryResultIterator answers = reasoner.answerQuery(query, false);
		
		return new Column(arity, answers, mapping);
	}
	
	/* Answer Atomic query with specified answer variables */
	public Column answerAtomicQuery(Atom atom, ArrayList<Integer> ansVar) throws ParsingException, IOException {
//		System.out.println("check 2: in answerAtomicQuery() query_atom= "+atom);
		PositiveLiteral query = RuleParser.parsePositiveLiteral(atom.toVLog());
//		System.out.println("check 3: in answerAtomicQuery() query= "+query.toString());

		Column result = new Column(atom.getTerms().size());
		
		if(reasoner == null) materialize();

//		System.out.println("KB:");
//		for(Statement st: this.kb){
//			System.out.println(st);
//		}
		
		final QueryResultIterator answers = reasoner.answerQuery(query, false);
//		System.out.println("check 4: in answerAtomicQuery() answers:");
		while(answers.hasNext()){
			System.out.println(answers.next());
		}
		
		answers.forEachRemaining(answer -> result.addwithFilter(answer, ansVar));
		
//		result.distinct();
		
		return result;
	}
	
	public Column answerAtomicQuery(String atomic_q) throws IOException, ParsingException {
		PositiveLiteral query = RuleParser.parsePositiveLiteral(atomic_q);
		
		Column result = new Column(query.getArguments().size());
		
		if(reasoner == null) materialize();

		final QueryResultIterator answers = reasoner.answerQuery(query, false);

		answers.forEachRemaining(answer -> result.add(answer));
		
		return result;
	}
	
	public void addSourceFromCSVDir(String fileDir) throws FileNotFoundException, ParsingException {
//		System.out.println(fileDir);
		File dir = new File(fileDir);
//		if(dir.exists()){
//			System.out.println("Yes");
//		}
		int cnt=0;
		for(File csv : dir.listFiles()){
			addSourceFromCSV(csv);
			System.out.println(++cnt);
		}
	}
	
	public void addSourceFromCSV(File csv) throws ParsingException, FileNotFoundException {
		String fname = csv.getName();
		String pname = fname.substring(0, fname.indexOf("."));
		Scanner scanner = new Scanner(csv);
		
		String line;
		int arity = 0;
	
		if(scanner.hasNextLine()) {
			line = scanner.nextLine();
			
//			Pattern p = Pattern.compile("\"([^\"]*)\"|[a-zA-Z][a-zA-Z0-9_-]*");
			Pattern p = Pattern.compile("\"([^\"]*)\"|[^,]+");
			Matcher m = p.matcher(line);
			
			while(m.find()) {
				arity++;
			}
		}
		
		scanner.close();

		String import_str = "@source <" + pname + ">[" + arity + "] : load-csv(\"" + csv.getAbsolutePath() + "\") .";
//		String import_str = "@source <" + pname + ">[" + arity + "] : load-csv(\""+fname+"\") .";
		System.out.println(import_str);
		RuleParser.parseInto(kb, import_str);
	}
	
	public void addFacts(Atom fact) throws ParsingException {
		addFacts(fact.toRDFox());
	}
	
	public void addFacts(String fact) throws ParsingException {
		RuleParser.parseInto(kb, fact);
	}
	
	/**
	 * Defines how messages should be logged. This method can be modified to
	 * restrict the logging messages that are shown on the console or to change
	 * their formatting. See the documentation of Log4J for details on how to do
	 * this.
	 * 
	 * Note: The VLog C++ backend performs its own logging that is configured
	 * independently with the reasoner. It is also possible to specify a separate
	 * log file for this part of the logs.
	 * 
	 * @param level the log level to be used
	 */
	private void configureLogging(Level level) {
		// Create the appender that will write log messages to the console.
		final ConsoleAppender consoleAppender = new ConsoleAppender();
		// Define the pattern of log messages.
		// Insert the string "%c{1}:%L" to also show class name and line.
		final String pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n";
		consoleAppender.setLayout(new PatternLayout(pattern));
		// Change to Level.ERROR for fewer messages:
		consoleAppender.setThreshold(level);

		consoleAppender.activateOptions();
		Logger.getRootLogger().addAppender(consoleAppender);
	}
}

package edu.ufal.dac.scraper;

public class Main {

	private static final String SEARCH_ENGINE = "http://search.yahoo.com/search?p=";

	// File containing a list of authors from RBIE
	//private static final String INPUT_FILE = "/home/josmario/AUTHORS_RBIE.in";
	//private static final String INPUT_FILE = "data/AUTHORS_RBIE.in";
	private static final String INPUT_FILE = "/home/jonathas/workspace/dac/Researchgate/data/AUTHORS_RBIE.in";

	// JSON file to place extracted data from Researchgate
	//private static final String OUTPUT_DIR = "/home/josmario/RESEARCHGATE_AUTHORS/";
	private static final String OUTPUT_DIR = "/home/jonathas/workspace/dac/Researchgate/data/RESEARCHGATE_AUTHORS/";

	public static void main(String[] args) {
		
		PageScraper ps = new PageScraper();
		
		ps.processData(SEARCH_ENGINE, INPUT_FILE, OUTPUT_DIR);
	}
}

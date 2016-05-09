package edu.ufal.dac.scraper;

public class Main {

	private static final String SEARCH_ENGINE = "http://search.yahoo.com/search?p=";

	// File containing a list of authors from RBIE
	private static final String INPUT_FILE = "/home/josmario/AUTHORS_GOOGLE.in";

	// JSON file to place extracted data from Researchgate
	private static final String OUTPUT = "/home/josmario/AUTHORS_GOOGLE.out";

	public static void main(String[] args) {
		
		PageScraper ps = new PageScraper();
		
		ps.processData(SEARCH_ENGINE, INPUT_FILE, OUTPUT);
	}
}

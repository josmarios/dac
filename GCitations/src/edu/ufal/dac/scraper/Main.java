package edu.ufal.dac.scraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {


	// File containing a list of authors from RBIE
	private static final String INPUT_FILE = "data/pending_rbiesbiewbie_clean_completelist.csv";

	// JSON file to place extracted data from Researchgate
	private static final String OUTPUT = "data/AUTHORS_GOOGLE.out";

	public static void main(String[] args) throws IOException {

		PageScraper ps = new PageScraper();
		ps.processData(INPUT_FILE, OUTPUT);
	}
}

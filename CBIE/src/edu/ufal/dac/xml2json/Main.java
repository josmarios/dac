package edu.ufal.dac.xml2json;

import java.io.File;

public class Main {

	private static final String INPUT_DIRECTORY = "data/SBIE/";
	private static final String OUTPUT_DIRECTORY = "data/output/";
	private static final String OUTPUT_NAMES = "SBIE_AUTHORS_NAMES.csv";

	public static void main(String[] args) {

		// Reads all files under 'INPUT_DIRECTORY', generates a unique JSON
		// file, and places the result into 'OUTPUT_DIRECTORY'
		Converter converter = new Converter();
		converter.processData(INPUT_DIRECTORY, OUTPUT_DIRECTORY);

		// Gets a list containing authors' names
		new NamesRetrieval().processNames(converter.loadFile(new File(OUTPUT_DIRECTORY + "RBIE_PAPERS_JSON")),
				OUTPUT_DIRECTORY + OUTPUT_NAMES);
	}
}

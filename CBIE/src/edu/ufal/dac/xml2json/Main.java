package edu.ufal.dac.xml2json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	private static final String INPUT_DIRECTORY = "data/SBIE/";
	private static final String OUTPUT_DIRECTORY = "data/output/";
	private static final String OUTPUT_NAMES = "SBIE_AUTHORS_NAMES.csv";
	private static final String OUTPUT_FILE = "CBIE_PAPERS.JSON"; 
	public static void main(String[] args) throws IOException {

		// Reads all files under 'INPUT_DIRECTORY', generates a unique JSON
		// file, and places the result into 'OUTPUT_DIRECTORY'
		Converter converter = new Converter();
		//converter.processData(INPUT_DIRECTORY, OUTPUT_DIRECTORY, OUTPUT_FILE);

		// Gets a list containing authors' names
		new NamesRetrieval().processNames(converter.loadFile(new File("data/output/CBIE_PAPERS_FIXED.json")), OUTPUT_DIRECTORY + OUTPUT_NAMES);
	}	
}

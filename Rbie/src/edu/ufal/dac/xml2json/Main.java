package edu.ufal.dac.xml2json;

public class Main {

	private static final String INPUT_DIRECTORY = "/home/josmario/Desktop/RBIE/Articles/";
	private static final String OUTPUT_DIRECTORY = "/home/josmario/Desktop/RBIE/";

	public static void main(String[] args) {

		Converter converter = new Converter();

		// Reads all files under 'INPUT_DIRECTORY', generates a unique JSON
		// file, and places the result into 'OUTPUT_DIRECTORY'
		converter.processData(INPUT_DIRECTORY, OUTPUT_DIRECTORY);
	}
}

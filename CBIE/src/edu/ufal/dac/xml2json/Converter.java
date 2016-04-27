package edu.ufal.dac.xml2json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Converter {

	/**
	 * Returns file content as a String
	 * @param file
	 * @return output
	 */
	
	//int a=1;
	
	public String loadFile(File file) {
		System.out.println("Loading: " + file.getName());
		String line, output = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null)
				output += "\n" + line;

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * Parses XML file and converts to JSON
	 * @param xmlContent
	 * @return
	 */
	private String convert(String xmlContent) {

		try {
			String cleanXML = xmlContent.replaceAll("(?s)<galley locale=\"pt_BR\"[^>]*>.*?</galley>", "");
			
			Document doc = Jsoup.parse(cleanXML);
			String content = doc.getElementsByTag("article").toString();

			// removes 'galley' element
			// ERROR: Was only removing the first occurrence.
			// Substituted by the line above.
			/*
			int begTrashPos = content.indexOf("<galley");
			int endTrashPos = content.indexOf("</galley>");
			
			if (begTrashPos > 0) {
				// Beginning position so <galley locale="pt_br"> is excluded. 
				String trash = content.substring(begTrashPos, endTrashPos+9);
				content = content.replace(trash, "");
			} */
			
			JSONObject json = XML.toJSONObject(content);
			return json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * @param inputDir
	 * @param outputDir
	 */
	public void processData(String inputDir, String outputDir, String outputFileName) {

		// Check input directory
		File inDir = new File(inputDir);
		if (!inDir.exists()) {
			System.err.println("Input directory not found!");
			System.exit(-1);
		}
		// Check output directory
		File outDir = new File(outputDir);
		if (!outDir.exists()) {
			outDir.mkdirs();
		}
		
		// Output JSON file
		File jsonMaster = new File(outDir.getPath() + "/" + outputFileName);

		try {
			FileWriter fw = new FileWriter(jsonMaster);
			
			fw.write("{\"papers\":[");

			for (int i=0; i<inDir.listFiles().length; i++) {
				File file = inDir.listFiles()[i];
					if (file.toString().contains(".xml")) {
						fw.write(convert(loadFile(file)) + ",");
					}
			}

			fw.write("]}");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}

}

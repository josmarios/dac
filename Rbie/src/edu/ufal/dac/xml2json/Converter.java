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
		// System.out.println(output);
		return output;
	}

	public String convert(String xmlContent) {

		try {
			Document doc = Jsoup.parse(xmlContent);

			String content = doc.getElementsByTag("article").toString();

			// removes 'galley' element
			int pos = content.indexOf("<galley");

			if (pos > 0) {
				String trash = content.substring(pos);
				content = content.replace(trash, "</article>");
			}

			JSONObject json = XML.toJSONObject(content);

			return json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public void processData(String inputDir, String outputDir) {

		// input directory
		File inDir = new File(inputDir);
		if (!inDir.exists())
			System.err.println("Input directory not found!");

		// output directory
		File outDir = new File(outputDir);
		if (!outDir.exists())
			outDir.mkdirs();

		// Output JSON file
		File jsonMaster = new File(outDir.getPath() + "/RBIE_PAPERS_JSON");

		try {
			FileWriter fw = new FileWriter(jsonMaster);

			fw.write("{\"papers\":[");
			
			for (File file : inDir.listFiles()) {
				fw.write(convert(loadFile(file)) + ",");
			}

			fw.write("]}");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

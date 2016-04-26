package edu.ufal.dac.xml2json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class NamesRetrieval {

	private List<String> getAuthorList(String data) {
		List<String> names = new ArrayList<String>();
		JSONObject json = new JSONObject(data);
		
		for (Object obj : json.getJSONArray("papers")) {
			JSONObject paper = (JSONObject) obj;
			
			//TODO issue appears to be in here
			try {				
				for (Object art: paper.getJSONArray("article")) {
					JSONObject article = (JSONObject) art;
					
					if (article.get("author") instanceof JSONObject) {
						JSONObject author = ((JSONObject) article.get("author"));
						names.add(joinNames(author));
					} else {
						JSONArray coAuthors = ((JSONArray) article.get("author"));
						
						for (Object object : coAuthors) {
							JSONObject author = (JSONObject) object;
							names.add(joinNames(author));
						}
					}
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				continue;
			}
		}
		return names;
	}

	private void saveNames(List<String> names, String filename) {
		// removes duplicates
		Set<String> set = new LinkedHashSet<String>(names);
		names.clear();
		names.addAll(set);
		
		try {
			FileWriter fw = new FileWriter(new File(filename));
			for (String name : names)
				fw.write("\"" + name + "\",\n");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String joinNames(JSONObject author) {
		String fullName = "";
		String first, middle, last;

		first = author.has("firstname") ? (String) author.get("firstname") : "";
		middle = author.has("middlename") ? (String) author.get("middlename") : "";
		last = author.has("lastname") ? (String) author.get("lastname") : "";

		fullName = first + " " + middle + " " + last;
		return fullName;
	}

	public void processNames(String jsonData, String filename) {
		System.out.println("Started processing names");
		saveNames(getAuthorList(jsonData), filename);
	}

}

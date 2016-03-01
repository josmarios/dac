package br.ufal.ic.dac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

public class PageScraper {

	private final String SEARCH_ENGINE = "http://search.yahoo.com/search?p=";
	private final String INPUT_FILE = "/home/josmario/data.input";
	private final String OUTPUT_FILE = "/home/josmario/data.output";

	/**
	 * Testing....
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		PageScraper ps = new PageScraper();

		ps.processData(ps.INPUT_FILE);

	}

	/**
	 * Gets a HTML page given its URL.
	 * 
	 * @param pageUrl
	 *            Link to the page.
	 * @return Returns the page content (HTML format)
	 */
	public String getPageContent(String pageUrl) {
		String content = "";

		System.out.println("\nGetting content: " + pageUrl);
		try {
			URL url = new URL(pageUrl);
			URLConnection connection = url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;
			while ((line = br.readLine()) != null)
				content += line + "\n";

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(content);
		return content;
	}

	public String getProfileUrl(String page) {
		String regex = "www.researchgate.net/profile/" + "[a-zA-Z_0-9]*" + "\\/";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(page);

		while (matcher.find())
			return "https://" + matcher.group();

		return "NOT_FOUND";
	}

	public String searchProfile(String query) {
		String profile = "";

		query = query.replace(" ", "+");

		String search = SEARCH_ENGINE + "researchgate+" + query;

		profile = getPageContent(getProfileUrl(getPageContent(search)));

		return profile;
	}

	public String getProfileBasics(String profileUrl) {
		String basics = "";

		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return basics;
	}

	public List<String> loadData(String fileUrl) {
		List<String> authors = new ArrayList<String>();
		File file = new File(fileUrl);
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null)
				authors.add(line);

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return authors;
	}

	public void saveData(List<String> urls, String output) {
		File file = new File(output);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (String string : urls)
				writer.write(string + "\n");

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void processData(String input) {

		List<String> authors = loadData(input);
		List<String> urls = new ArrayList<>();

		for (String query : authors) {
			urls.add(getProfileUrl(searchProfile(query)));
		}

		saveData(urls, OUTPUT_FILE);

	}
}

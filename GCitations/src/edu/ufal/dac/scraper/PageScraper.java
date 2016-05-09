package edu.ufal.dac.scraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

public class PageScraper {

	/**
	 * Gets a HTML page given its URL.
	 * 
	 * @param pageUrl
	 *            Link to the page.
	 * @return Returns the page content (HTML format)
	 */
	public String getPageContent(String pageUrl) {
		String content = "";

		System.out.println("Getting: " + pageUrl);
		try {

			if (pageUrl.contains("http")) {

				URL url = new URL(pageUrl);
				URLConnection connection = url.openConnection();

				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String line;
				while ((line = br.readLine()) != null)
					content += line + "\n";

				br.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(content);
		return content;
	}

	/**
	 * Locates a profile URL inside a specific HTML page.
	 * 
	 * @param page
	 *            A HTML page containing the URL on it.
	 * @return A Researchgate profile's URL
	 */
	public String getProfileUrl(String page) {

		String userId = "";
		Document doc = Jsoup.parse(page);
		String url;
		String profileUrl = "https://scholar.google.com.br/citations?user=";

		for (Element el : doc.select("a")) {
			url = el.attr("href");

			if (url.contains("citations?user=")) {

				//System.out.println("here: " + url);
				userId = url.split("user=")[1].split("&")[0];
				break;
				//System.out.println("user: " + userId);

				/*
				 * if (url.contains("/")) username = url.split("/")[0];
				 */
			}
		}

		//System.out.println(profileUrl + userId);
		return profileUrl + userId;

	}
	

	public String searchProfile(String searchEngine, String query) {
		String profile = "";

		query = query.replace(" ", "+");

		String search = searchEngine + "google+scholar+" + query;

		String profileUrl = getProfileUrl(getPageContent(search));

		// profile = getPageContent(profileUrl);

		// return profile;
		return profileUrl;
	}

	/**
	 * Opens the fileUrl file, processes the authors and returns a List<String>
	 * 
	 * @param fileUrl
	 * @return authors
	 */
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

	

	/**
	 * Processes and saves author data on the queryList to the output directory
	 * using the search engine provided
	 * 
	 * @param searchEngine
	 * @param queryList
	 * @param output
	 */
	public void processData(String searchEngine, String queryList, String output) {

		List<String> queryItems = loadData(queryList);
		Author author;
		FileWriter failedWriter = null;

		File dir = new File(output);
		if (!dir.exists())
			dir.mkdir();

		// failed list
		try {
			failedWriter = new FileWriter(new File(dir.getAbsoluteFile() + "/_ERROR"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < queryItems.size(); i++) {
			try {

				// author = extractAuthorInfo(this.searchProfile(searchEngine,
				// queryItems.get(i)));
				 FileWriter fw = new FileWriter(new File(output));
				//
				//
				// // fw.write(new JSONObject(author).toString()); // error:
				// Unsupported major.minor version 52.0
				//
				 fw.write(this.searchProfile(searchEngine, queryItems.get(i)));
				// //fw.write(author.toString());
				// fw.close();

				System.out.println(this.searchProfile(searchEngine, queryItems.get(i)));

			} catch (Exception e) {
				System.err.println("Failed: " + queryItems.get(i));
				try {

					failedWriter.write(queryItems.get(i) + "\n");
					failedWriter.flush();
				} catch (IOException e1) {
					// e1.printStackTrace();
				}
				// e.printStackTrace();
				continue;
			}
		}
		try {
			failedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

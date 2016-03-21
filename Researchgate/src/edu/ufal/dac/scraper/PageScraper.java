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

		 //System.out.println(content);
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

		String username = "";
		Document doc = Jsoup.parse(page);
		String url;
		String researchGateUrl = "https://www.researchgate.net/";
		
			
		for (Element el : doc.select("a")) {
			url = el.attr("href");
			
			if (url.contains("profile/")) {
				username = url.split("profile/")[1].split("/")[0];
				
				/*if (url.contains("/"))
					username = url.split("/")[0]; */
			}
		}
		
		https://www.researchgate.net/profile/Ig_Bittencourt/citations?sorting=recent&page=2

		System.out.println(researchGateUrl+"profile/" + username);
		return researchGateUrl+"profile/" + username;

	}
	//
	// public String getProfileUrl(String page) {
	//
	// String regex = "www\\.researchgate\\.net/profile/" + "[a-zA-Z_0-9]*" +
	// "\\/";
	//
	// Pattern pattern = Pattern.compile(regex);
	//
	// Matcher matcher = pattern.matcher(page);
	//
	// while(matcher.find()){
	// return "https://" + matcher.group();
	// }
	//
	// return "https://www.researchgate.net/profile/Ig_Bittencourt";
	//
	// }

	public String searchProfile(String searchEngine, String query) {
		String profile = "";

		query = query.replace(" ", "+");

		String search = searchEngine + "researchgate+" + query;
		
		String profileUrl = getProfileUrl(getPageContent(search));
		
		//profile = getPageContent(profileUrl); 

		//return profile;
		return profileUrl;
	}

	/**
	 * Opens the fileUrl file, processes the authors and returns a List<String> 
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
	 * Extracts author information from ResearchGate 
	 * @param profileUrl
	 * @return
	 */
	public Author extractAuthorInfo(String profileUrl) {

		Author author = new Author();

		Document doc = Jsoup.parse(getPageContent(profileUrl));

		// profile url
		author.setProfileUrl(profileUrl);

		// name
		String aName = doc.getElementsByTag("title").text();
		aName = aName.contains("\\(")?doc.getElementsByTag("title").text().split(" \\(")[0]:aName;
		author.setName(aName);

		// institution
		author.setAffiliation(
				doc.select("div.ga-institution-name-on-profile-header").first().select("meta").attr("content"));

		// topics/interests
		List<String> interests = new ArrayList<>();
		for (Element topic : doc.select("a.keyword-list-token-text")) {
			interests.add(topic.text());
		}
		author.setInterestAreas(interests);

		// publications
		author.setNumPublications(
				doc.select("li.ga-publications-count").first().select("div.stats-count").first().text());

		// citations
		author.setNumCitations(doc.select("li.ga-citation-count").first().select("div.stats-count").first().text());

		// reads
		author.setNumProfileViews(doc.select("li.ga-read-count").first().select("div.stats-count").first().text());

		// articles
		author.setArticles(extractPublications(profileUrl, Integer.parseInt(author.getNumPublications())));
		//System.out.println(author.getName() + "\n" + author.getAffiliation() + "\nPublications: " + author.getNumPublications() + "\nCitations: " + author.getNumCitations() + "\nReads: " + author.getNumProfileViews());

		return author;
	}

	public List<Article> extractPublications(String profileUrl, int numPublications) {

		int numPages = ((numPublications % 20) == 0) ? numPublications / 20 : (numPublications / 20 + 1);
		Document doc;
		List<Article> publications = new ArrayList<>();

		String baseUrl = "https://www.researchgate.net/";

		for (int i = 0; i < numPages; i++) {
			
			// get publications' current page (publications shown on each page)

			String currentPage = getPageContent(profileUrl + "/publications/" + (i + 1));

			doc = Jsoup.parse(currentPage);

			for (Element element : doc.select("li.li-publication")) {
				Article currentArticle = new Article();
				List<String> coAuthors = new ArrayList<>();

				try {
					// temporary title (just in case of error it will be used)
					currentArticle.setTitle("undefined");

					// url
					String articleUrl = element.select("a.ga-publication-viewer").first().attr("href");
					currentArticle.setUrl(baseUrl + articleUrl);

					// Gets the article's details page based on the url above
					String htmlPage = getPageContent(baseUrl + articleUrl);
					Document docDetails = Jsoup.parse(htmlPage);

					// title
					currentArticle.setTitle(docDetails.select("h1.pub-title").first().text());

					// abstract
					currentArticle.setAbst(
							docDetails.select("div.js-expander-container").first().select("div").first().text());

					// Publisher
					String publisher = docDetails.select("meta[name=citation_journal_title]").attr("content");
					if (publisher == "") {
						publisher = docDetails.select("div[class=pub-details js-pub-details]").first().toString();
						// 1) Fix formatting
						// 2) Test additional possible tags
					}
					currentArticle.setPublisher(publisher);

					// publication date
					//currentArticle.setPublicationDate(docDetails.select("div.publication-meta-date").toString());

					// co-authors
					//for (Element elem : docDetails.select("a.publication-author-name")) {
					for (Element elem : docDetails.select("meta[name=citation_author]")) {
						String coAuthor = elem.attr("content");
						coAuthors.add(coAuthor);
					}
					currentArticle.setAuthors(coAuthors);

					// add article to the list
					publications.add(currentArticle);

				} catch (Exception e) {
					System.err.println(currentArticle.getTitle());
					continue;
				}
			}
		}
		return publications;
	}

	/**
	 * Processes and saves author data on the queryList to the output directory using the search engine provided
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
		
		//failed list
		try {
			failedWriter = new FileWriter(new File(dir.getAbsoluteFile()+"/_ERROR"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	

		for (int i = 0; i < queryItems.size(); i++) {
			try {

				author = extractAuthorInfo(this.searchProfile(searchEngine, queryItems.get(i)));
				FileWriter fw = new FileWriter(new File(output + author.getName()));
				
				
			//	fw.write(new JSONObject(author).toString()); // error: Unsupported major.minor version 52.0
				
				fw.write(new Gson().toJson(author));
				//fw.write(author.toString());
				fw.close();

			} catch (Exception e) {
				System.err.println("Failed: " + queryItems.get(i));
				try {
					
					failedWriter.write(queryItems.get(i)+"\n");
					failedWriter.flush();
				} catch (IOException e1) {
					//e1.printStackTrace();
				}
			//	e.printStackTrace();
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

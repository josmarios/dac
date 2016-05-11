package edu.ufal.dac.scraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

public class PageScraper {
	private String[] userAgents;
	private String profileNotFoundExceptionMessage = "Profile Not Found";
	private String blockedByServerExceptionMessage = "Blocked By Server";

	public PageScraper() {
		userAgents = new String[]{"Mozilla/6.0 (Windows NT 6.2; WOW64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; ja-jp) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27", "Opera/9.80 (X11; Linux i686; Ubuntu/14.10) Presto/2.12.388 Version/12.16"};
	}
	/**
	 * Gets a HTML page given its URL.
	 *
	 * @param pageUrl
	 *            Link to the page.
	 * @return Returns the page content (HTML format)
	 * @throws Exception
	 */
	public String getPageContent(String pageUrl, int timeoutErrorCode) throws Exception {
		String content = "";
		String userAgent = userAgents[new Random().nextInt(userAgents.length)];

		// Wasn't making a difference, so the wait period was commented out:
		/*try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/

		if (pageUrl.contains("http")) {

			URL url = new URL(pageUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", userAgent);

			if (connection.getResponseCode() == timeoutErrorCode) {
				throw new Exception(blockedByServerExceptionMessage);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			while ((line = br.readLine()) != null)
				content += line + "\n";

			br.close();
		}

		return content;
	}

	/**
	 * Locates a profile URL inside a specific HTML page.
	 * Warning: returns first result found. If there are two authors with similar names, the first one on the page ranking will be returned.
	 *
	 * @param page
	 *            A HTML page containing the URL on it.
	 * @return A Researchgate profile's URL
	 * @throws Exception
	 */
	public String getProfileUrl(String page) throws Exception {
		String userId = "";
		Document doc = Jsoup.parse(page);
		String url;
		String profileUrl = "https://scholar.google.com.br/citations?user=";

		boolean foundUrl = false;

		for (Element el : doc.select("a")) {
			url = el.attr("href");

			if (url.contains("citations?user=")) {
				userId = url.split("user=")[1].split("&")[0];
				if (userId != "") {
					foundUrl = true;
				}
				break;
			}
		}

		if (foundUrl == false) {
			throw new Exception(profileNotFoundExceptionMessage);
		}

		return profileUrl + userId;
	}

	/**
	 * Search profile using Yahoo (searchEngine).
	 * @param searchEngine
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public String searchProfile(String searchEngine, String query) throws Exception {
		String profile = "";
		query = query.replace(" ", "+");
		String search = searchEngine + "google+scholar+" + query;
		String profileUrl = getProfileUrl(getPageContent(search, 403));
		return profileUrl;
	}

	/**
	 * Search for profile using Duck Duck Go
	 * Problems:
	 * 1) Being blocked by server if using searchURL
	 * 2) URL from results are hidden if using searchURL2 (api)
	 * @param authorName
	 * @return
	 * @throws Exception
	 * @throws
	 */
	private String searchProfileDuckDuckGo(String authorName) throws Exception {
		authorName = authorName.replace(" ", "+");

		String searchURL = "https://duckduckgo.com/html/?q=%22" + authorName + "+-+google+scholar+citations%22";
		//String searchURL2 = "https://api.duckduckgo.com/?q=%22" + authorName + "+-+google+scholar+citations%22";

		String profileUrl = getProfileUrl(getPageContent(searchURL, 403));
		System.out.println("URL for " + authorName + ": " + profileUrl);

		return profileUrl;
	}

	/**
	 * Search for profile using Google Citation's search URL.
	 * Problem: being blocked by server.
	 * @param authorName
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	private String searchProfileGCitations(String authorName) throws Exception {
		authorName = authorName.replace(" ", "+");
		String searchURL = "https://scholar.google.com.br/citations?mauthors=" + authorName + "&hl=pt-BR&view_op=search_authors";
		String profileUrl = getProfileUrl(getPageContent(searchURL, 503));
		System.out.println("URL for " + authorName + ": " + profileUrl);
		return profileUrl;
	}

	/**
	 * Searchs for profile using Bing API
	 * @param authorName
	 * @return
	 * @throws Exception
	 */
	private String searchProfileBing(String authorName) throws Exception {
		String profileUrl = null;
		authorName = authorName.replace(" ", "%20");

		final String accountKey = "<your_bing_key>"; //to register: https://datamarket.azure.com/dataset/bing/search
		final String bingUrl = "https://api.datamarket.azure.com/Bing/Search/v1/Web?Query=%27%22" + authorName + "%20-%20google%20scholar%20citations%22%27&$format=JSON";
        final String accountKeyEnc = Base64.getEncoder().encodeToString((accountKey + ":" + accountKey).getBytes());
        final URL url = new URL(bingUrl);
        final URLConnection connection = url.openConnection();

        connection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            final StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            final JSONObject json = new JSONObject(response.toString());
            final JSONObject d = json.getJSONObject("d");
            final JSONArray results = d.getJSONArray("results");
            final int resultsLength = results.length();

            for (int i = 0; i < resultsLength; i++) {
                final JSONObject aResult = results.getJSONObject(0);
                profileUrl = (String) aResult.get("Url");
                if (profileUrl.contains("scholar.google")) {
                	break;
                } else {
                	profileUrl = null;
                }
            }
        }

        if (profileUrl == null) {
			throw new Exception("Profile Not Found");
        }
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
	 * Processes and saves author data on the queryList to the output directory.
	 * WATCH OUT: this will remove authors from the input file if:
	 * 1) its profile URL was found - added to the output file.
	 * 2) it was not found - added to the failed_output file.
	 * @param queryListFile
	 * @param output
	 * @throws IOException
	 */
	public void processData(String queryListFile, String output) throws IOException {
		List<String> queryItems = loadData(queryListFile);

		FileWriter authorsWithoutURL = null;

		// failed list
		try {
			authorsWithoutURL = new FileWriter(new File("data/_NoURLFoundError.out"),true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		FileWriter fw = new FileWriter(new File(output), true);

		for (int i = 0; i < queryItems.size(); i++) {
			try {

//				String profileURLToOutput = searchProfile("http://search.yahoo.com/search?p=", queryItems.get(i));
				String profileURLToOutput = searchProfileGCitations(queryItems.get(i));
//				String profileURLToOutput = searchProfileDuckDuckGo(queryItems.get(i));
//				String profileURLToOutput = searchProfileBing(queryItems.get(i));

				fw.append(queryItems.get(i) + " " + profileURLToOutput + "\n");
				removeLineFromFile(queryListFile, queryItems.get(i));
			} catch (Exception e) {
				if (e.getLocalizedMessage() == blockedByServerExceptionMessage) {
					System.err.println(blockedByServerExceptionMessage);
					break;
				} else if (e.getLocalizedMessage() == profileNotFoundExceptionMessage) {
					System.err.println(profileNotFoundExceptionMessage + ": " + queryItems.get(i));
					try {
						authorsWithoutURL.append(queryItems.get(i) + "\n");
						removeLineFromFile(queryListFile, queryItems.get(i));

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				continue;
			}
		}

		try {
			authorsWithoutURL.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes the author line from the file. Used in case of success or failure - needed if the execution needs to continue after a 403 HTTP error or similar and needs a checkpoint.
	 * @param file
	 * @param lineToRemove
	 */
	private void removeLineFromFile(String file, String lineToRemove) {

        try {
            File inFile = new File(file);
            if (!inFile.isFile()) {
                System.err.println("File doesn't exist");
                return;
            }

            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            // Read f/ the original file & write to the new, unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            // Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package edu.ufal.dac.scraper;

import java.util.List;

public class Article {
	private String url;
	private String title;
	private String abst;
	private String publicationDate;
	private String publisher;
	private List<String> authors;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		System.out.println("Article URL: " + this.url);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		System.out.println("Article Title: " + this.title);
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
		System.out.println("Article Authors: " + this.authors);

	}

	public String getAbst() {
		return abst;
	}

	public void setAbst(String abst) {
		this.abst = abst;
		System.out.println("Article Abstract: " + this.abst);

	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
		System.out.println("Article publication date: " + this.publicationDate);

	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
		System.out.println("Article publisher: " + this.publisher);

	}

}

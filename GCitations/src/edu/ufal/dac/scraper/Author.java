package edu.ufal.dac.scraper;

import java.util.List;

public class Author {
	private String profileUrl;
	private String name;
	private String affiliation;
	private String numCitations;
	private String numPublications;
	private String numProfileViews;
	private List<String> interestAreas;
	private List<Article> articles;

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public List<String> getInterestAreas() {
		return interestAreas;
	}

	public void setInterestAreas(List<String> interestAreas) {
		this.interestAreas = interestAreas;
	}

	public String getNumCitations() {
		return numCitations;
	}

	public void setNumCitations(String numCitations) {
		this.numCitations = numCitations;
	}

	public String getNumPublications() {
		return numPublications;
	}

	public void setNumPublications(String numPublications) {
		this.numPublications = numPublications;
	}

	public String getNumProfileViews() {
		return numProfileViews;
	}

	public void setNumProfileViews(String numProfileViews) {
		this.numProfileViews = numProfileViews;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return this.name + "\n" + this.affiliation + "\nPublications: " + this.numPublications
				+ "\nCitations: " + this.numCitations + "\nReads: " + this.numProfileViews + "\n*\n";
	}

}

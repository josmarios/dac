package br.ufal.ic.dac.lattes;

import java.util.List;

public class Author {
	private String profileUrl;
	private String name;
	private String affiliation;
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

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return this.name + "\n" + this.affiliation + "\n*\n";
	}

}

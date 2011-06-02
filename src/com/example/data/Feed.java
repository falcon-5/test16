package com.example.data;

import java.util.ArrayList;

public class Feed extends BaseData
{
	private String id;
	private String updated;
	private Category category;
	private String title;
	private String logo;
	private ArrayList<Link> link;
	private Author author;
	private Generator genarator;
	private int totalResults;
	private int startIndex;
	private int itemPerPage;
	private ArrayList<Entry> entry;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public ArrayList<Link> getLink() {
		return link;
	}
	public void setLink(ArrayList<Link> link) {
		this.link = link;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Generator getGenarator() {
		return genarator;
	}
	public void setGenarator(Generator genarator) {
		this.genarator = genarator;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getItemPerPage() {
		return itemPerPage;
	}
	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}
	public ArrayList<Entry> getEntry() {
		return entry;
	}
	public void setEntry(ArrayList<Entry> entry) {
		this.entry = entry;
	}
}

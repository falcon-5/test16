package com.example.data;

import java.util.ArrayList;

public class Entry {
	private String id;
	private String published;
	private String updated;
	private ArrayList<Category> category;
	private String title;
	private String content;
	private ArrayList<Link> link;
	private Author author;
	private Comments comments;
	private Group group;
	private Rating rating;
	private Statistics statistics;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public ArrayList<Category> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<Category> category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Comments getComments() {
		return comments;
	}
	public void setComments(Comments comments) {
		this.comments = comments;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
}

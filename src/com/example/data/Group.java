package com.example.data;

import java.util.ArrayList;

public class Group
{
	private Category category;
	private ArrayList<Content> content;
	private String description;
	private String Keywords;
	private Player player;
	private ArrayList<Thumbnail> thumbnail;
	private String title;
	private long duration;

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public ArrayList<Content> getContent() {
		return content;
	}
	public void setContent(ArrayList<Content> content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return Keywords;
	}
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ArrayList<Thumbnail> getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(ArrayList<Thumbnail> thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
}

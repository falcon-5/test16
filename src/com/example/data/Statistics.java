package com.example.data;

public class Statistics
{
	private long viewCount;
	private long videoWatchCount;
	private long subscriberCount;
	private String lastWebAccess;
	private long favoriteCount;

	public long getViewCount() {
		return viewCount;
	}
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}
	public long getVideoWatchCount() {
		return videoWatchCount;
	}
	public void setVideoWatchCount(long videoWatchCount) {
		this.videoWatchCount = videoWatchCount;
	}
	public long getSubscriberCount() {
		return subscriberCount;
	}
	public void setSubscriberCount(long subscriberCount) {
		this.subscriberCount = subscriberCount;
	}
	public String getLastWebAccess() {
		return lastWebAccess;
	}
	public void setLastWebAccess(String lastWebAccess) {
		this.lastWebAccess = lastWebAccess;
	}
	public long getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
}

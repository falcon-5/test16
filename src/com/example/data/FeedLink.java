package com.example.data;

public class FeedLink
{
	private String rel;
	private String href;
	private long countHint;

	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public long getCountHint() {
		return countHint;
	}
	public void setCountHint(long countHint) {
		this.countHint = countHint;
	}
}

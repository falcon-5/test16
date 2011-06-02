package com.example.data;

public class Rating
{
	private int min;
	private int max;
	private long numRaters;
	private double average;

	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public long getNumRaters() {
		return numRaters;
	}
	public void setNumRaters(long numRaters) {
		this.numRaters = numRaters;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
}

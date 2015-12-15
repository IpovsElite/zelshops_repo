package com.ipovselite;

public class SearchParameters {
	private String pattern;
	private String spec;
	private double currentLat;
	private double currentLng;
	private boolean isGeoEnabled;
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public double getCurrentLat() {
		return currentLat;
	}
	public void setCurrentLat(double currentLat) {
		this.currentLat = currentLat;
	}
	public double getCurrentLng() {
		return currentLng;
	}
	public void setCurrentLng(double currentLng) {
		this.currentLng = currentLng;
	}
	public boolean getIsGeoEnabled() {
		return isGeoEnabled;
	}
	public void setIsGeoEnabled(boolean isGeoEnabled) {
		this.isGeoEnabled = isGeoEnabled;
	}
}

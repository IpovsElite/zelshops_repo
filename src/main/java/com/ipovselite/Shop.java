package com.ipovselite;

public class Shop {
	private int id;
	private String name;
	private String address;
	private String site;
	private String telephone;
	private String spec;
	private int status;
	private String description;
	private float rating;
	private int voters;
	private double lat;
	private double lng;
	
	public Shop() {
		id=0;
		name="";
		address="";
		site="";
		telephone="";
		spec="";
		status=0;
		description="";
		rating=0;
		voters=0;
		lat=0;
		lng=0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getVoters() {
		return voters;
	}
	public void setVoters(int voters) {
		this.voters = voters;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
}

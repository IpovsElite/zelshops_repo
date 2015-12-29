package com.ipovselite;

import java.util.ArrayList;
import java.util.List;

public class Geotagging {
	public static int[] limits = { 500, 1000, 1500, 2000, 2500, 3000};
	private static double getDistance(double lat1,double lng1,double lat2,double lng2) {
		double d = 111.2*Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lng2-lng1));
		System.out.println("IN GETDISTANCE:"+ d);
		return d;
	}
	public static List<Shop> GetNearestShops(double lat,double lng, double radius, List<Shop> shops) {
		List<Shop> nearestShops = new ArrayList<Shop>();
		for (Shop s : shops) {
			double distance= getDistance(s.getLat(),s.getLng(),lat,lng);
			if (distance<=radius) {
				s.setDistance((int)(distance*1000/100)*100);
				nearestShops.add(s);
			}
		}
		return nearestShops;
	}
}

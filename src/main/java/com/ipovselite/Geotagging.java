package com.ipovselite;

import java.util.ArrayList;
import java.util.List;

public class Geotagging {
	private static double getDistance(double lat1,double lng1,double lat2,double lng2) {
		double d = 111.2*Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lng2-lng1));
		return d;
	}
	public static List<Shop> GetNearestShops(double lat,double lng, double radius, List<Shop> shops) {
		List<Shop> nearestShops = new ArrayList<Shop>();
		for (Shop s : shops) {
			double distance= getDistance(s.getLat(),s.getLng(),lat,lng);
			if (distance<=radius)
				nearestShops.add(s);
		}
		return nearestShops;
	}
}

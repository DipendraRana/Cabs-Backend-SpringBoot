package org.codejudge.sb.Utility;

public class HaversineDistanceFinder {
	
	private static double findDisatnce(double Originlat,double Originlong,double Destinationlat,double Destinationlong) {
		
		// distance between latitudes and longitudes
		double dLat = Math.toRadians(Destinationlat - Originlat);
		double dLon = Math.toRadians(Destinationlong - Originlong);
		
		// convert to radians
		Originlat = Math.toRadians(Originlat);
		Destinationlat = Math.toRadians(Destinationlat);
		
		double hav = Math.pow(Math.sin(dLat/2), 2) + Math.pow(Math.sin(dLon/2), 2) * Math.cos(Originlat) * Math.cos(Destinationlat);
		//radius of earth
		double radius = 6371;
		double distance = (2 * Math.asin(Math.sqrt(hav))) * radius;
		
		return distance;
	}
	
	public static double getHaversineDistance(double Originlat,double Originlong,double Destinationlat,double Destinationlong) {
		return findDisatnce(Originlat, Originlong, Destinationlat, Destinationlong);
	}

}

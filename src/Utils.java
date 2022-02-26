import java.util.ArrayList;

import org.json.simple.JSONArray;

public class Utils
{
	
	/**
	 * Geodesic distance function. Used to calculate the distance between two
	 * points, given the latitude and longitude of those two points.
	 * 
	 * 
	 * @param lat1 Latitude of point 1
	 * @param lon1 Longitude of point 1
	 * @param lat2 Latitude of point 2
	 * @param lon2 Longitude of point 2
	 * @return distance between points in miles
	 */
	public static double geoDist(double lat1, double lon1, double lat2, double lon2) {
		// calculate distance in meters and convert to miles before returning final result
		final double phi1 = lat1 * Math.PI / 180.0; // phi, lambda in radians
		final double phi2 = lat2 * Math.PI / 180.0;
		final double delta_phi = (lat2-lat1) * Math.PI / 180.0;
		final double delta_lambda = (lon2-lon1) * Math.PI / 180.0;
		final double a = Math.sin(delta_phi/2.0) * Math.sin(delta_phi/2.0) + 
						 Math.cos(phi1) * Math.cos(phi2) *
						 Math.sin(delta_lambda/2.0) * Math.sin(delta_lambda/2.0);
		final double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		// d = R * c; R = 6371000
		final double d = 6371000.0 * c; // distance in meters
		final double distance_in_miles = metersToMiles(d);
		return distance_in_miles;
	}
	
	
	public static double milesToMeters(double valueInMiles) {
		return valueInMiles * 1609.34;
	}
	
	public static double metersToMiles(double valueInMeters) {
		return valueInMeters * 0.000621371;
	}
	
	/**
	 * Convert a JSONArray object into an ArrayList object.
	 */
    public static ArrayList<Object> convertToArrayList(JSONArray jArr) {
    
	    ArrayList<Object> list = new ArrayList<Object>();
	    
	    try {
	    	for (int i=0, l= jArr.size(); i<l; i++) {
	    		list.add(jArr.get(i));
	        }
	    	
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    
	    return list;
    }

}
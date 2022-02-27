import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;

public class Utils
{
	/** miles per hour */
	public static final double TRUCK_SPEED = 55.0;
	/** $ per mile */
	public static final double FUEL_COST_PER_MILE = 0.4;
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
	
	// converts a string of yyyy-MM-dd to Date and Time
	/*public static LocalDateTime stringToTime(String string) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		string = "2022-02-28 00:00:00";
        LocalDateTime dateTime = LocalDateTime.parse(string, formatter);
		System.out.println(dateTime);
		return dateTime; 
	}*/
	
	 // find difference between two time strings in seconds
	 public static long findDifference(String start_date, String end_date){

		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 long difference_In_Seconds = 0;
		 
		 try {
			 Date d1 = sdf.parse(start_date);
			 Date d2 = sdf.parse(end_date);
			 
			 long difference_In_Time = d2.getTime() - d1.getTime();
			 
			 difference_In_Seconds = (difference_In_Time / 1000) % 60;
			 long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

			 long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
		 }
		 
		 catch (ParseException e) {
	            e.printStackTrace();
	        }
		 
		 return difference_In_Seconds;
	 }
	 
	 
	// cost incurred to travel from one stop to another 
		public static double costOfPath(double miles) {
			double cost = 0;
			cost = miles * FUEL_COST_PER_MILE;
			return cost;
		}
		
		//Time taken to cover from one stop to another
		public static double timeOfPath(double miles) {
			double time = 1;
			time = miles / TRUCK_SPEED;
			return time;
		}
		
	// amount earned per mile
		public static double amountPerMile(Load load) {
			double olat = load.getOriginLatitude();
			double olong = load.getOriginLongitude();
			double dlat = load.getDestinationLatitude();
			double dlong = load.getDestinationLongitude();
			
			double meters = geoDist(olat, olong, dlat, dlong);
			double miles = metersToMiles(meters);
			
			double apm = (load.getAmount() / miles);
			return apm;
			
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
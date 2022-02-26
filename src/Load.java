import org.json.simple.JSONObject;

/**
 * this is the load class
 */
public class Load {
	
	private long load_id;
	private String origin_city;
	private String origin_state;
	private double origin_latitude;
	private double origin_longitude;
	private String destination_city;
	private String destination_state;
	private double destination_latitude;
	private double destination_longitude;
	private long amount;
	private String pickup_date_time;
	
	/**
	 * Constructor to create a new load object given a JSONObject representing the load as input.
	 */
	public Load (JSONObject loaddata) {
		this.load_id 				= (long)	loaddata.get("load_id");
		
		this.origin_city 			= (String)	loaddata.get("origin_city");
	    this.origin_state 			= (String)	loaddata.get("origin_state");
	    
	    this.origin_latitude 		= (double)	loaddata.get("origin_latitude");
	    this.origin_longitude 		= (double)	loaddata.get("origin_longitude");
	    
	    this.destination_city 		= (String)	loaddata.get("destination_city");
	    this.destination_state 		= (String)	loaddata.get("destination_state");
	    
	    this.destination_latitude 	= (double)	loaddata.get("destination_latitude");
	    this.destination_longitude 	= (double)	loaddata.get("destination_longitude");
	    
	    this.amount 				= (long)	loaddata.get("amount");
	    this.pickup_date_time	    = (String)	loaddata.get("pickup_date_time");
	}

	/**
	 * @return the load_id
	 */
	public long getLoadId() {
		return load_id;
	}

	/**
	 * @return the origin_city
	 */
	public String getOriginCity() {
		return origin_city;
	}

	/**
	 * @return the origin_state
	 */
	public String getOriginState() {
		return origin_state;
	}

	/**
	 * @return the origin_latitude
	 */
	public double getOriginLatitude() {
		return origin_latitude;
	}

	/**
	 * @return the origin_longitude
	 */
	public double getOriginLongitude() {
		return origin_longitude;
	}

	/**
	 * @return the destination_city
	 */
	public String getDestinationCity() {
		return destination_city;
	}

	/**
	 * @return the destination_state
	 */
	public String getDestinationState() {
		return destination_state;
	}

	/**
	 * @return the destination_latitude
	 */
	public double getDestinationLatitude() {
		return destination_latitude;
	}

	/**
	 * @return the destination_longitude
	 */
	public double getDestinationLongitude() {
		return destination_longitude;
	}

	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}
	

	/**
	 * @return the pickup_date_time
	 */
	public String getPickupDateTime() {
		return pickup_date_time;
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "\t\"load_id\": " + this.load_id
				+ ",\n\t\"origin_city\": " + this.origin_city
				+ ",\n\t\"origin_state\": " + this.origin_state
				+ ",\n\t\"origin_latitude\": " + this.origin_latitude
				+ ",\n\t\"origin_longitude\": " + this.origin_longitude
				+ ",\n\t\"destination_city\": " + this.destination_city
				+ ",\n\t\"destination_state\": " + this.destination_state
				+ ",\n\t\"destination_latitude\": " + this.destination_latitude
				+ ",\n\t\"destination_longitude\": " + this.destination_longitude
				+ ",\n\t\"amount\": " + this.amount
				+ ",\n\t\"pickup_date_time\": " + this.pickup_date_time
				+ "\n}";
	}
}

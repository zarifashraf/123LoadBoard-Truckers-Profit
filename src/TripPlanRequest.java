import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * Class for storing data about input trip planning requests from truckers.
 */
public class TripPlanRequest {
	
	private long input_trip_id;
	private double start_latitude;
	private double start_longitude;
	private String start_time;
	private String max_destination_time;
	
	
	public ArrayList<Long> load_ids;
	
	public TripPlanRequest(JSONObject tripData) {
		this.input_trip_id = (long) tripData.get("input_trip_id");
		this.start_latitude = (double) tripData.get("start_latitude");
		this.start_longitude = (double) tripData.get("start_longitude");
		this.start_time = (String) tripData.get("start_time");
		this.setMax_destination_time((String) tripData.get("max_destination_time"));
		load_ids = new ArrayList<Long>();
	}

	/**
	 * @return the input_trip_id
	 */
	public long getInputTripId() {
		return input_trip_id;
	}

	/**
	 * @return the start_latitude
	 */
	public double getStartLatitude() {
		return start_latitude;
	}

	/**
	 * @return the start_longitude
	 */
	public double getStartLongitude() {
		return start_longitude;
	}

	/**
	 * @return the start_time
	 */
	public String getStartTime() {
		return start_time;
	}

	/**
	 * @return the max_destination_time
	 */
	public String getMaxDestinationTime() {
		return max_destination_time;
	}

	/**
	 * @param max_destination_time the max_destination_time to set
	 */
	public void setMax_destination_time(String max_destination_time) {
		this.max_destination_time = max_destination_time;
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "\t\"input_trip_id\": " + this.input_trip_id
				+ ",\n\t\"start_latitude\": " + this.start_latitude
				+ ",\n\t\"start_longitude\": " + this.start_longitude
				+ ",\n\t\"start_time\": " + this.start_time
				+ ",\n\t\"max_destination_time\": " + this.max_destination_time
				+ "\n}";
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Date;
public class TP {
	
	
	public static void main(String[] args) {
		
		// Load load data from json
		List<Load> loads = new ArrayList<Load>();
		
		try {
			JSONArray jsonarray = (JSONArray) JSONValue.parse(new FileReader(new File("util").getAbsoluteFile().toString()
				+ "/123Loadboard_CodeJam_2022_dataset.json"));
			
			for (Object o : jsonarray) {
			    
				JSONObject loaddata = (JSONObject) o;
			    
			    Load loado = new Load(loaddata);
			    
			    loads.add(loado);
			}
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		}
		
		System.out.printf("Loaded %d loads from json file.\n", loads.size());
		
		
		// Load Input trip plan requests
		List<TripPlanRequest> trips = new ArrayList<TripPlanRequest>();
		List<TripPlanRequest> trips2 = new ArrayList<TripPlanRequest>();
		try {
			JSONArray jsonarray2 = (JSONArray) JSONValue.parse(new FileReader(new File("util").getAbsoluteFile().toString()
			   + "/123Loadboard_Codejam_2022_input_sample_s300.json"));
		
			for (Object o : jsonarray2) {
				JSONObject tripdata = (JSONObject) o;
				TripPlanRequest tpr = new TripPlanRequest(tripdata);
				trips2.add(tpr);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Loaded %d trips from s300 sample file.\n", trips2.size());
		
		
		
		//System.out.println(loads.get(1));
		//System.out.println(trips.get(0));
		
		/*
		testGeoDist(loads.get(1).getOriginLatitude(),
					loads.get(1).getOriginLongitude(),
					loads.get(1).getDestinationLatitude(),
					loads.get(1).getDestinationLongitude());
		*/
		
		
		// Begin calculations
		for (TripPlanRequest trip : trips) {
			// find most profitable route for trip
			
			
			trip.load_ids.add(loads.get(0).getLoadId());
		}
		
		
		
		// Output to json
		String json_output = "";
		List<Map<String, Object>> output_data = new ArrayList<Map<String, Object>>();
		for (TripPlanRequest trip : trips) {
			Map<String, Object> trip_output_map = new LinkedHashMap<String, Object>();
			trip_output_map.put("input_trip_id", trip.getInputTripId());
			trip_output_map.put("loads_ids", trip.load_ids);
			output_data.add(trip_output_map);
		}
		StringWriter out = new StringWriter();
		try {
			JSONValue.writeJSONString(output_data, out);
			json_output = out.toString();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// pretify json
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = new JsonParser().parse(json_output);
		String pretty_json_output = gson.toJson(je);
		// write json to file
		//System.out.println(json_output);
		try {
			String outputFilePath = new File("output").getAbsoluteFile().toString() + "\\trip_plan_output.json";
			PrintWriter printOut = new PrintWriter(outputFilePath);
			printOut.print(pretty_json_output);
			printOut.close();
			System.out.println("Trip plan results outputted to " + outputFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@SuppressWarnings("unused")
	private static void testGeoDist(double lat1, double lon1, double lat2, double lon2) {
		System.out.println(lat1);
		System.out.println(lon1);
		System.out.println(lat2);
		System.out.println(lon2);
		double dist = Utils.geoDist(lat1, lon1, lat2, lon2);
		System.out.println("Dist in miles: " + dist);
		System.out.println("Dist in meters: " + Utils.milesToMeters(dist));
		System.out.println("Dist in km    : " + Utils.milesToMeters(dist) / 1000.0);
	}


}

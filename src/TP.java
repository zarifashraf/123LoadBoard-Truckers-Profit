import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class TP {

	public static void main(String[] args) {
		
		
		// Load load data
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
			JSONArray jsonarray = (JSONArray) JSONValue.parse(new FileReader(new File("util").getAbsoluteFile().toString()
			   + "/123Loadboard_Codejam_2022_input_sample.json"));
			JSONArray jsonarray2 = (JSONArray) JSONValue.parse(new FileReader(new File("util").getAbsoluteFile().toString()
			   + "/123Loadboard_Codejam_2022_input_sample_s300.json"));
		
			for (Object o : jsonarray) {
				JSONObject tripdata = (JSONObject) o;
				TripPlanRequest tpr = new TripPlanRequest(tripdata);
				trips.add(tpr);
			}
		
			for (Object o : jsonarray2) {
				JSONObject tripdata = (JSONObject) o;
				TripPlanRequest tpr = new TripPlanRequest(tripdata);
				trips2.add(tpr);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Loaded %d trips from s200 sample file.\n", trips.size());
		System.out.printf("Loaded %d trips from s300 sample file.\n", trips2.size());
		
		
		System.out.println(loads.get(0));
		System.out.println(trips.get(0));
		
	}


}

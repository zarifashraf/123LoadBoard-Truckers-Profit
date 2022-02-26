import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class TP {

	public static void main(String[] args) {
		
		JSONParser parser = new JSONParser();
		List<Load> loads = new ArrayList<Load>();
		
		try {
		JSONArray jsonarray = (JSONArray) parser.parse(new FileReader(new File("util").getAbsoluteFile().toString()
				+"/123Loadboard_CodeJam_2022_dataset.json"));
		
		loads = jsonarray;
			
		//Load data = new Gson().fromJson(jsonarray, Load.class);
		
		for (Object o : jsonarray) {
		    
			JSONObject load = (JSONObject) o;

		    long load_id = (long) load.get("load_id");

		    String origin_city = (String) load.get("origin_city");

		    String origin_state = (String) load.get("origin_state");
		    
		    double origin_latitude = (double) load.get("origin_latitude");
		    
		    double origin_longitude = (double) load.get("origin_longitude");
		    
		    String destination_city = (String) load.get("destination_city");
		    
		    String destination_state = (String) load.get("destination_state");
		    
		    double destination_latitude = (double) load.get("destination_latitude");
		    
		    double destination_longitude = (double) load.get("destination_longitude");
		    
		    long amount = (long) load.get("amount");
		    
		    String pickup_date_time = (String) load.get("pickup_date_time");
		    
		  }
		
		} 
		
		catch (FileNotFoundException e) {
            e.printStackTrace();
        
		} 
		
		catch (IOException e) {
            e.printStackTrace();
		}
		
		catch (ParseException e) {
            e.printStackTrace();
        }
		
		
	}


}

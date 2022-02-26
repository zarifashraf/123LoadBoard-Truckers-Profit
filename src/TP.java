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
		
		//JSONParser parser = new JSONParser();
		List<Load> loads = new ArrayList<Load>();
		
		try {
			JSONArray jsonarray = (JSONArray) JSONValue.parse(new FileReader(new File("util").getAbsoluteFile().toString()
				+ "/123Loadboard_CodeJam_2022_dataset.json"));
			
			//loads = jsonarray;
				
			//Load data = new Gson().fromJson(jsonarray, Load.class);
			
			for (Object o : jsonarray) {
			    
				JSONObject loaddata = (JSONObject) o;
			    
			    Load loado = new Load(loaddata);
			    
			    loads.add(loado);
			}
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		}
		
		//System.out.println("ID: " + loads.get(0).getLoadId() + ", ORIGIN_CITY: " + loads.get(0).getOriginCity());
		System.out.printf("Loaded %d loads from json file.\n", loads.size());
	}


}

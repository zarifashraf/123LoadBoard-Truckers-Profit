import java.util.ArrayList;

import org.json.simple.JSONArray;

public class ArrayUtil
{
    public static ArrayList<Object> convertToArrayList(JSONArray jArr)
    
    {
    
    ArrayList<Object> list = new ArrayList<Object>();
    
    try {
            for (int i=0, l= jArr.size(); i<l; i++){
                 list.add(jArr.get(i));
            
        }}	
    
    catch (Exception e) {
    	System.out.println(e);
        	}


        return list;
    }

}
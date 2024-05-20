import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class APIAccessProvider 
{
	private static APIAccessProvider component = null;
	private static int mode;
	
	public static APIAccessProvider getCoordinateTracker()
	{
		component = new APIAccessProvider();
		mode = 0;
		
		return component;
	}
	
	public static APIAccessProvider getCovidDataset()
	{
		component = new APIAccessProvider();
		mode = 1;
		
		return component;
	}
	
	private String getResult(String URL) throws Exception
	{
		final URL url = new URI(URL).toURL();
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		StringBuffer result = new StringBuffer();
		
		connection.setRequestMethod("GET");
		
		try
		{
			if(connection.getResponseCode() != 200)
			{
				System.out.println("INVALID ADDERSS");
				return null;
			}
			
			InputStreamReader input_stream = new InputStreamReader(connection.getInputStream());
			BufferedReader reader = new BufferedReader(input_stream);
			String input;
			
			while((input = reader.readLine()) != null)
			{
				result.append(input);
			}
			reader.close();
		}
		catch(UnknownHostException e)
		{
			Main.app.reportError("Internet connection required, please try again.");
			Main.app.endApp();
		}
		
		return result.toString();
	}
	
	private JSONArray getJSONArray(String result)
	{
		JSONArray array = null;
		if(result != null)
		{
			Object json_result = JSONValue.parse(result);
			
			if(json_result instanceof JSONArray)
			{
				array = (JSONArray) json_result;
			}
		}
		
		return array;
	}
	
	public HashMap<String, String> getInformation(String location) throws Exception
	{
		HashMap<String, String> coords = new HashMap<String, String>();
		StringBuffer link = new StringBuffer();
		String[]location_words = location.split(" ");
		String result = null;
		
		if (mode == 0)
		{
			link.append("https://nominatim.openstreetmap.org/search?q=");
			
			for (int i = 0; i < location_words.length; i++)
			{
				link.append(location_words[i]);
				
				if (i < (location_words.length - 1))
				{
					link.append("+");
				}
			}
			
			link.append("&format=jsonv2");
			
			try
			{
				result = getResult(link.toString());
			}
			catch (NullPointerException e)
			{
				System.out.println("LINK INVALID: " + link);
				
			}
			
			JSONArray array = getJSONArray(result);
			
			if(array.size() > 0)
			{
				JSONObject  json_object = (JSONObject) array.get(0);
				
				String name = (String) json_object.get("name");
				String longitude = (String) json_object.get("lon");
				String latitude = (String) json_object.get("lat");
				String type = (String) json_object.get("type");
				String address = (String) json_object.get("display_name");
				
				coords.put("lat", latitude);
				coords.put("lon", longitude);
				coords.put("type", type);
				coords.put("address", address);
				coords.put("name", name);
			}
		}
		else
		{
			throw new Exception("Invalid Command.");
		}
		
		return coords;
	}
	
	public ArrayList<HashMap<String, String>> getInformation(String start_date, String end_date) throws Exception
	{
		ArrayList<HashMap<String, String>> case_list = new ArrayList<HashMap<String, String>>();
		StringBuffer link = new StringBuffer();
		String result = null;
		
		if (mode == 1)
		{
			link.append("https://api.data.gov.my/data-catalogue/?id=covid_cases&date_start=" + 
						start_date+ "@date&date_end=" + end_date + "@date" + 
						"&exclude=cases_new,cases_import,cases_recovered,cases_cluster");
			
			try
			{
				result = getResult(link.toString());
			}
			catch (NullPointerException e)
			{
				System.out.println("LINK INVALID: " + link);
			}
			
			JSONArray array = getJSONArray(result);
			
			if(array.size() > 0)
			{
				for(int i = 0; i < array.size(); i++)
				{
					HashMap<String, String> cases = new HashMap<String, String>();
					JSONObject json_object = (JSONObject) array.get(i);
					
					String cases_active = Long.toString((long) json_object.get("cases_active"));
					String date = (String) json_object.get("date");
					String state = (String) json_object.get("state");
					
					cases.put("cases_active", cases_active);
					cases.put("date", date);
					cases.put("state", state);
					
					case_list.add(cases);
				}
			}
		}
		else 
		{
			throw new Exception ("Invalid Command.");
		}
		Collections.sort(case_list, new HashMapComparator("date"));
		Collections.sort(case_list, new HashMapComparator("state"));
		return case_list;
		
	}
}

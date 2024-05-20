import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;


public class CoordinateTracker 
{
	private Scanner scan;
	private ArrayList<HashMap<String, String>> found_coords = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> clinic_coords = new ArrayList<HashMap<String,String>>();
	private ArrayList<HashMap<String, String>> nearest_coords = new ArrayList<HashMap<String, String>>();
	
	private void readData() throws FileNotFoundException
	{
		scan = new Scanner(new File("clinic.txt"));
		scan.useDelimiter("\t");
		
		while(scan.hasNextLine())
		{			
			String temp_str = scan.nextLine();
			Scanner scan_line = new Scanner(temp_str);
			scan_line.useDelimiter("\t");

			HashMap<String, String> temp_coord = new HashMap<String, String>();
			temp_coord.put("name", scan_line.next());
			temp_coord.put("address", scan_line.next());
			temp_coord.put("coordinate", scan_line.next());
			clinic_coords.add(temp_coord);	
			scan_line.close();
		}
	}
	
	public ArrayList<HashMap<String, String>> getNearestClinic()
	{
		Collections.sort(found_coords, new HashMapComparator("distance"));
		int count = 0;
		
		for(HashMap<String, String> temp_coords : found_coords)
		{
			nearest_coords.add(temp_coords);
			count++;

			if(count == 3)
			{
				break;
			}
		}
		
		return nearest_coords;
	}
	
	public HashMap<String, String> getInformation(String location) throws Exception
	{
		APIAccessProvider api_access = APIAccessProvider.getCoordinateTracker();
		
		return api_access.getInformation(location);
	}
	
	public void scanRadius(double origin_lat, double origin_lon) throws Exception
	{
		readData();
		double compare_lat, compare_lon = 0;
		CoordinatePoint origin_coord = new CoordinatePoint(origin_lat, origin_lon);
		CoordinatePoint compare_coord;

		for (HashMap<String, String> temp_coord : clinic_coords)
		{
			String coord_str = temp_coord.get("coordinate");
			Scanner scan = new Scanner(coord_str);
			compare_lat = Double.parseDouble(scan.next());
			compare_lon = Double.parseDouble(scan.next());
			scan.close();
			
			compare_coord = new CoordinatePoint(compare_lat, compare_lon);
			
			CoordinateDistance distance = new CoordinateDistance(origin_coord, compare_coord);
			distance.calculateDistance();
			temp_coord.put("distance", distance.getDistance().toString());
			if(distance.getDistance()/1000 <= 10.0)
			{
				found_coords.add(temp_coord);
			}
		}
	}
}

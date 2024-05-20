import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.*;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;


public class NearestClinicPage extends Page
{
	private String location;
	private CoordinatePoint current_coords;
	private HashMap<String, String> current_location;
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	public NearestClinicPage(String location)
	{
		this.location = location;
		setLayout(new BorderLayout());
		
		JLabel internet_label = new JLabel("Connection Error. Internet required to continue.");
		internet_label.setFont(new Font("Consolas", Font.BOLD, 24));
		internet_label.setHorizontalAlignment(JLabel.CENTER);
		
		add(internet_label, BorderLayout.CENTER);
		add(BackButton(), BorderLayout.SOUTH);
	}
	
	public NearestClinicPage() throws Exception
	{
		setLayout(new BorderLayout());
		
		location = askLocation();
		CoordinateTracker tracker = new CoordinateTracker();
		current_location = tracker.getInformation(location);

		if(!current_location.isEmpty())
		{
			current_coords = new CoordinatePoint(Double.parseDouble(current_location.get("lat")), Double.parseDouble(current_location.get("lon")));
			JMapViewer map = Map();
			
			JPanel clinic_info = new JPanel();
			clinic_info.setLayout(new GridLayout(3, 1));
			
			tracker.scanRadius(current_coords.getLatitude(), current_coords.getLongitude());
			
			ArrayList<HashMap<String, String>> clinic_list = tracker.getNearestClinic();
			
			for(HashMap<String, String> clinic : clinic_list)
			{
				clinic_info.add(ClinicDetails(clinic.get("name"), clinic.get("address"), Double.parseDouble(clinic.get("distance"))));
				
				
				String[] str = clinic.get("coordinate").split(" ");
				Coordinate clinic_coords = new Coordinate(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
				
				MapMarkerDot clinic_dot = new MapMarkerDot(clinic.get("name"), clinic_coords);
				clinic_dot.setFont(new Font("Consolas", Font.BOLD, 18));
				clinic_dot.setBackColor(Color.CYAN);
				map.addMapMarker(clinic_dot);
			}
			add(BackButton(), BorderLayout.SOUTH);
			add(map, BorderLayout.CENTER);
			add(clinic_info, BorderLayout.EAST);		
		}
		else
		{
			JLabel error_label = new JLabel("Sorry. Location not found.");
			error_label.setFont(new Font("Consolas", Font.BOLD, 24));
			error_label.setHorizontalAlignment(JLabel.CENTER);
			
			add(error_label, BorderLayout.CENTER);
			add(BackButton(), BorderLayout.SOUTH);
		}
	}
	
	private JPanel ClinicDetails(String clinic_name, String clinic_address, double distance)
	{
		JTextField name = new JTextField(clinic_name);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setFont(new Font("Consolas", Font.BOLD, 15));
		name.setEditable(false);
		
		String[] address_words = (clinic_address.substring(1, clinic_address.length() - 1)).split(", ");
		
		JTextArea address = new JTextArea(20, 24);
		address.setFont(new Font("Consolas", Font.BOLD, 13));
		address.append(decimalFormat.format(distance/1000) + "km\n");
		address.setEditable(false);
		
		for(String str : address_words)
		{
			address.append(str + "\n");
		}
		
		address.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(address);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(name);
		panel.add(scroll, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JMapViewer Map()
	{
		JMapViewer map = new JMapViewer();
		Coordinate current = new Coordinate(current_coords.getLatitude(), current_coords.getLongitude());
		
		MapMarkerDot current_dot = new MapMarkerDot(current_location.get("name"), current);
		current_dot.setFont(new Font("Consolas", Font.BOLD, 18));
		current_dot.setBackColor(Color.MAGENTA);
		
		map.addMapMarker(current_dot);
		map.setDisplayPosition(current, 12);
		map.setTileSource(new OsmTileSource.CycleMap());
		
		return map;
	}
	
	public String askLocation()
	{
		location = JOptionPane.showInputDialog(null, "Enter your location (building name, residential area etc)", "Location Tracker", JOptionPane.INFORMATION_MESSAGE);
		
		if(location == null)
			askLocation();
		
		return location;
	}

}

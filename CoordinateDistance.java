public class CoordinateDistance 
{
	private double lat1, lat2, long1, long2, distance;
	private final double EARTH_RADIUS = 6378137;
	
	public CoordinateDistance(CoordinatePoint coord1, CoordinatePoint coord2)
	{
		lat1 = coord1.getLatitude();
		long1 = coord1.getLongitude();
		lat2 = coord2.getLatitude();
		long2 = coord2.getLongitude();
	}
	
	public void calculateDistance()
	{
		double lat1_rad = Math.toRadians(lat1);
		double lat2_rad = Math.toRadians(lat2);
		double del_lat = Math.toRadians(lat2 - lat1);
		double del_long = Math.toRadians(long2 - long1);
		
		// Haversine of half the chord length between the coordinates
		double haversine = Math.pow(Math.sin(del_lat / 2.0), 2.0) + (Math.cos(lat1_rad) * Math.cos(lat2_rad) * Math.pow(Math.sin(del_long / 2.0), 2.0));
		
		//Angle of the arc between the points
		double angle = 2 * (Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine)));
		
		distance = EARTH_RADIUS * angle;
	}

	
	public Double getDistance()
	{
		return this.distance;
	}
}

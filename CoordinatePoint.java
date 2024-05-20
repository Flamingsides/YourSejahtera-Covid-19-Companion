
public class CoordinatePoint 
{
	private double lat_point, long_point;
	
	public CoordinatePoint(double lat_point, double long_point)
	{
		this.lat_point = lat_point;
		this.long_point = long_point;
	}
	
	public double getLatitude()
	{
		return this.lat_point;
	}
	
	public double getLongitude()
	{
		return this.long_point;
	}
}

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database
{
	private static final String DATA_PATH = new File("database.sql").getAbsolutePath();
	
	private static Connection conn;
	private PreparedStatement command;
	
	public Database()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + DATA_PATH);
		}
		catch (SQLException e)
		{
			System.out.println("DATABASE ERROR: Unable to connect to database: "  + DATA_PATH);
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("DATABASE ERROR: Database class not found: "  + "org.sqlite.JDBC");
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String... args)
	{
		ResultSet result = null;
		try
		{
			command = conn.prepareStatement(args[0]);
			
			for (int i = 1; i < args.length; i++)
			{
				command.setString(i, args[i]);
			}
			
			result = command.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println("DATABASE ERROR: Unable to execute query: " + args.toString());
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Database.java: Invalid Execute Command");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int executeUpdate(String... args)
	{
		int result = -1;
		try
		{
			command = conn.prepareStatement(args[0]);
			
			for (int i = 1; i < args.length; i++)
			{
				command.setString(i, args[i]);
			}
			
			result = command.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("DATABASE ERROR: Unable to execute query: " + args.toString());
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Database.java: Invalid Execute Command");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void closeConnection()
	{
		try
		{			
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("DATABASE ERROR: Unable to close database");
			e.printStackTrace();
		}
	}
}


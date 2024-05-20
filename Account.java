import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class Account extends Database
{
	private PasswordAuthentication auth;
	
	// Logged-in user's username (unless no user is logged-in)
	public static String username;
	
	public Account()
	{
		super();
		auth = new PasswordAuthentication();
	}
	
	public boolean register(String username, String password, String passwordConfirmation) throws Account.PasswordsDontMatchException, Account.UsernameAlreadyTakenException
	{	
		String hash = auth.hash(password.toCharArray());
		
		// Passwords must match
		if (!auth.authenticate(passwordConfirmation.toCharArray(), hash))
		{
			throw new PasswordsDontMatchException("Passwords Don't Match!");
		}
		
		if (usernamePresent(username))
		{
			throw new UsernameAlreadyTakenException("Username is already registered!");
		}
	
		executeUpdate("INSERT INTO users (username, hash) VALUES (?, ?)", username, hash);
		
		// Success
		return true;
	}
	
	public boolean login(String username, String password) throws Account.IncorrectUsernameOrPasswordException
	{
		String hash;
		try
		{
			hash = executeQuery("SELECT hash FROM users WHERE username = ?", username).getString("hash");
		}
		catch (SQLException e)
		{
			System.out.println("DATABASE ERROR: Unable to get stored hash for " + username);
			e.printStackTrace();
			
			// Login failed
			return false;
		}
		try
		{			
			if (auth.authenticate(password.toCharArray(), hash))
			{
				Account.username = username;
			}
			else
			{
				throw new IncorrectUsernameOrPasswordException("Incorrect Username or Password");
			}
		}
		catch (Exception e)
		{
			throw new IncorrectUsernameOrPasswordException("Incorrect Username or Password");
		}
		
		// Success
		return true;
	}
	
	public void logout()
	{
		Account.username = null;
		Main.app.showPage(App.pageNames.START_PAGE);
	}
	
	public void logSymptomsCount(int symptomsCount)
	{
		if (Account.username == null)
		{
			Main.app.showPage(App.pageNames.START_PAGE);
			return;
		}
		
		String time = getNow();
		
		executeUpdate("INSERT INTO history (time, symptoms_count, user_id) VALUES (?, ?, (SELECT id FROM users WHERE username = ?))", time, String.valueOf(symptomsCount), Account.username);
	}
	
	public boolean usernamePresent(String username)
	{
		ResultSet rs = executeQuery("SELECT username FROM users WHERE username = ?", username);
	
		try
		{
			if (rs.next())
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			System.out.println("DATABASE ERROR: ResultSet.next()");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public LinkedHashMap<LocalDateTime, Integer> getLog(int records)
	{
		LinkedHashMap<LocalDateTime, Integer> logs = new LinkedHashMap<LocalDateTime, Integer>();
		
		if (Account.username == null)
		{
			Main.app.showPage(App.pageNames.START_PAGE);
			return logs;
		}

		ResultSet rs = executeQuery("SELECT time, symptoms_count FROM history WHERE user_id = (SELECT id FROM users WHERE username = ?) ORDER BY time DESC LIMIT ?", Account.username, String.valueOf(records));
		
		try
		{
			while (rs.next())
			{
				LocalTime t = rs.getTime("time").toLocalTime();
				LocalDate d = rs.getDate("time").toLocalDate();
				
				LocalDateTime when = LocalDateTime.of(d, t);
				
				logs.put(when, rs.getInt("symptoms_count"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return logs;
	}
	
	private String getNow()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return formatter.format(now);
	}
	
	public class PasswordsDontMatchException extends Exception
	{
		public PasswordsDontMatchException(String message)
		{
			super(message);
		}
	}
	
	public class UsernameAlreadyTakenException extends Exception
	{
		public UsernameAlreadyTakenException(String message)
		{
			super(message);
		}
	}
	
	public class IncorrectUsernameOrPasswordException extends Exception
	{
		public IncorrectUsernameOrPasswordException(String message)
		{
			super(message);
		}
	}
}

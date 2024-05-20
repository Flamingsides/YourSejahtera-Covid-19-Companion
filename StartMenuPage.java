import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class StartMenuPage extends Page
{
	JButton regButton;
	JButton loginButton;
	
	public StartMenuPage()
	{
		super();
		
		setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		setLayout(new GridLayout(2, 1, 50, 50));
		
		regButton = new JButton("Register");
		loginButton = new JButton("Login");
		
		regButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me)
			{
				open(App.pageNames.REGISTRATION_PAGE);
			}
		});
		
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me)
			{
				open(App.pageNames.LOGIN_PAGE);
			}
		});
		
		add(regButton);
		add(loginButton);
		
		setVisible(true);
	}
}

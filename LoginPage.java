import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class LoginPage extends Page
{
	private JPanel headerPanel;
	private JPanel usernamePanel;
	private JPanel passwordPanel;
	private JPanel formPanel;
	private JPanel footerPanel;
	private JLabel usernamePrompt;
	private JLabel passwordPrompt;
	private JLabel instructionsText;
	private JTextField usernameInput;
	private JTextField passwordInput;
	private JButton submitButton;
	private JButton backButton;
	
	public LoginPage()
	{
		super();
		
		headerPanel = new JPanel();
		formPanel = new JPanel(new GridLayout(2, 1, 30, 30));
		footerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		usernamePanel = new JPanel(new GridLayout(1, 2, 30, 30));
		passwordPanel = new JPanel(new GridLayout(1, 2, 30, 30));
		
		instructionsText = new JLabel("Enter the login details then click \"log in\" to log in!");
		instructionsText.setHorizontalAlignment(JLabel.CENTER);
		
		usernamePrompt = new JLabel("USERNAME: ");
		passwordPrompt = new JLabel("PASSWORD: ");
		usernamePrompt.setHorizontalAlignment(JLabel.CENTER);
		passwordPrompt.setHorizontalAlignment(JLabel.CENTER);
		
		usernameInput = new JTextField();
		passwordInput = new JTextField();
		usernameInput.setFont(new Font("Consolas", Font.PLAIN, 14));
		passwordInput.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		
		backButton = new JButton("Back");
		backButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me)
			{
				open(App.pageNames.START_PAGE);
			}
		});
		
		submitButton = new JButton("Log In");
		submitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me)
			{
				String username = usernameInput.getText();
				String password = passwordInput.getText();
				
				if (username == null || password == null || username.isBlank() || password.isBlank())
				{
					instructionsText.setText("Blank Inputs Not Allowed. Please Try Again.");
					return;
				}
				
				if (!Main.app.account.usernamePresent(username))
				{
					instructionsText.setText("Username not found");
					
					return;
				}
				
				try
				{
					Main.app.account.login(username, password);
					open(App.pageNames.GRAPHING_PAGE);
				}
				catch (Account.IncorrectUsernameOrPasswordException e)
				{
					instructionsText.setText(e.getMessage() + " Please Try Again.");
				}
			}
		});
		
		headerPanel.add(instructionsText);
		
		formPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
		usernamePanel.add(usernamePrompt);
		usernamePanel.add(usernameInput);
		passwordPanel.add(passwordPrompt);
		passwordPanel.add(passwordInput);
		formPanel.add(usernamePanel);
		formPanel.add(passwordPanel);
		
		footerPanel.add(backButton);
		footerPanel.add(submitButton);
		
		setLayout(new BorderLayout(20, 20));
		add(headerPanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
}

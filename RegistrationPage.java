import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class RegistrationPage extends Page
{
	private JPanel headerPanel;
	private JPanel formPanel;
	private JPanel footerPanel;
	private JLabel instructionsText;
	private JTextField usernameInput;
	private JTextField passwordInput;
	private JTextField passConfirmInput;
	private JButton submitButton;
	private JButton backButton;
	
	public RegistrationPage()
	{
		super();
		
		headerPanel = new JPanel();
		formPanel = new JPanel(new GridLayout(4, 2, 30, 30));
		footerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		instructionsText = new JLabel("Enter the following details and submit to register!");
		instructionsText.setHorizontalAlignment(JLabel.CENTER);
		
		usernameInput = new JTextField();
		passwordInput = new JTextField();
		passConfirmInput = new JTextField();
		usernameInput.setFont(new Font("Consolas", Font.PLAIN, 14));
		passwordInput.setFont(new Font("Consolas", Font.PLAIN, 14));
		passConfirmInput.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		backButton = new JButton("Back");
		backButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me)
			{
				open(App.pageNames.START_PAGE);
			}
		});
		
		submitButton = new JButton("Submit");
		submitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me)
			{
				String username = usernameInput.getText();
				String password = passwordInput.getText();
				String passConfirm = passConfirmInput.getText();
				
				if (username == null || password == null || passConfirm == null
						|| username.isBlank() || password.isBlank() || passConfirm.isBlank())
				{
					instructionsText.setText("Blank Inputs Not Allowed. Please Try Again.");
					return;
				}
				
				if (!password.equals(passConfirm))
				{
					instructionsText.setText("Passwords Don't Match!");
				}
				
				try
				{
					Main.app.account.register(username, password, passConfirm);
					open(App.pageNames.LOGIN_PAGE);
				}
				catch (Account.PasswordsDontMatchException e)
				{
					instructionsText.setText(e.getMessage());
				}
				catch (Account.UsernameAlreadyTakenException e)
				{
					instructionsText.setText(e.getMessage());
				}
			}
		});
		
		headerPanel.add(instructionsText);
		
		formPanel.add(new JLabel("Enter Username"));
		formPanel.add(usernameInput);
		formPanel.add(new JLabel("Enter Password"));
		formPanel.add(passwordInput);
		formPanel.add(new JLabel("Re-enter Password"));
		formPanel.add(passConfirmInput);
		
		footerPanel.add(backButton);
		footerPanel.add(submitButton);
		
		setLayout(new BorderLayout(20, 20));
		add(headerPanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
}


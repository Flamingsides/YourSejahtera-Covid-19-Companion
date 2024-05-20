import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends JFrame
{
	public static enum pageNames
	{
		START_PAGE("START PAGE"), // Includes buttons for signing in and registering
		REGISTRATION_PAGE("REGISTRATION PAGE"), // Allows user to register as a new user
		LOGIN_PAGE("LOGIN PAGE"), // Allows signing into an account
		GRAPHING_PAGE("GRAPHING PAGE"), // Shows Covid-19 statistics and has buttons to search for clinics or self-test
		NEAREST_CLINIC_PAGE("NEAREST CLINIC PAGE"), //Shows nearest clinic given an address
		HISTORY_PAGE("HISTORY PAGE"), //Shows the history of question attempts
		QUESTION_PAGE("QUESTION PAGE"), //Shows questions
		RESULTS_PAGE("RESULTS PAGE"),
		ADVICE_PAGE("ADVICE PAGE");
		
		private final String name;
		
		private pageNames(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString()
		{
			return this.name;
		}
	}

	public Account account;
	
	// Visual Settings
	private static final String APP_NAME = "YourSejahtera™";
	private static final String[] CONTRIBUTERS = {"Jei, Hao Wen (Howard)", "Loh, Yue Hung (Cheerful)", "Tan, Li Qin", "Zuberi, Suhaib Hameed"};
	private static final Color FORE_COLOR = Color.WHITE;
	
	// Adapted (while darkened) From: https://visme.co/blog/wp-content/uploads/2016/09/website24.jpg
	private static final Color BACK_COLOR = new Color(200, 222, 221); 
	
	// Components
	private CardLayout cardControl = new CardLayout();
	private JPanel contentPanel;
	private JPanel bodyPanel;
	private JPanel headerPanel;
	private JPanel footerPanel;
	private String signedInText;
	private JLabel signedInDisplay;
	private Border defaultPadding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
	
	// Pages
	private Page startPage;
	private Page regPage;
	private Page loginPage;
	private Page graphPage;
	private Page clinicPage;
	private Page historyPage;
	private Page questionPage;
	private Page resultsPage;
	private Page advicePage;
	
	
	public App(int width, int height)
	{
		// Initialise JFrame
		super();
		
		account = new Account();
		
		// Frame settings
    	setSize(width, height);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        WindowListener listener = new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {
                int result = JOptionPane.showConfirmDialog(App.this, "Close the application");
                if (result == JOptionPane.OK_OPTION) 
                {
                    endApp();
                }
            }
        };
        addWindowListener(listener);
    	
    	// Setting contentPanel with borders
    	contentPanel = new JPanel();
    	contentPanel.setBorder(defaultPadding);
    	contentPanel.setLayout(new BorderLayout());
    	setContentPane(contentPanel);
    	
    	// Configuring Body Panel
    	bodyPanel = new JPanel();
    	bodyPanel.setLayout(cardControl);
    	bodyPanel.setBorder(defaultPadding);
    	contentPanel.add(bodyPanel, BorderLayout.CENTER);
    	
    	// Configure Header Panel
    	JLabel appName = new JLabel(APP_NAME);
    	appName.setFont(new Font("Arial", Font.BOLD, 15));
    	headerPanel = new JPanel(new BorderLayout());
    	headerPanel.setBorder(defaultPadding);
    	headerPanel.setBackground(BACK_COLOR);
    	headerPanel.setForeground(FORE_COLOR);
    	headerPanel.add(appName, BorderLayout.WEST);
    	signedInText = Account.username == null ? "Not Signed-In" : "Welcome " + Account.username + "!";
    	signedInDisplay = new JLabel(signedInText);
    	headerPanel.add(signedInDisplay, BorderLayout.EAST);
    	contentPanel.add(headerPanel, BorderLayout.NORTH);
    	
    	// Configure Footer Panel
    	footerPanel = new JPanel(new GridLayout(2, 2, 0, 2));
    	footerPanel.setBorder(defaultPadding);
    	footerPanel.setBackground(BACK_COLOR);
    	footerPanel.setForeground(FORE_COLOR);
    	
    	// Add contributers name
    	for (int i = 0; i < CONTRIBUTERS.length; i++)
    	{
    		JLabel label = new JLabel(CONTRIBUTERS[i]);
    		label.setHorizontalAlignment(JLabel.CENTER);
    		footerPanel.add(label);
    	}
    	contentPanel.add(footerPanel, BorderLayout.SOUTH);
	}
	
	private void initialisePages()
	{
		// Initialise Pages
		startPage = new StartMenuPage();
		regPage = new RegistrationPage();
		loginPage = new LoginPage();
		graphPage = new Graphing();
		clinicPage = new NearestClinicPage(" ");
		historyPage = new HistoryPage();
		questionPage = new QuestionPage();
		resultsPage = new ResultsPage(0);
		advicePage = new AdvicePage();
		
		
		
		// Add pages to bodyPanel
		bodyPanel.add(pageNames.START_PAGE.toString(), startPage);
		bodyPanel.add(pageNames.REGISTRATION_PAGE.toString(), regPage);
		bodyPanel.add(pageNames.LOGIN_PAGE.toString(), loginPage);
		bodyPanel.add(pageNames.GRAPHING_PAGE.toString(), graphPage);
		bodyPanel.add(pageNames.NEAREST_CLINIC_PAGE.toString(), clinicPage);
		bodyPanel.add(pageNames.HISTORY_PAGE.toString(), historyPage);
		bodyPanel.add(pageNames.QUESTION_PAGE.toString(), questionPage);
		bodyPanel.add(pageNames.RESULTS_PAGE.toString(), resultsPage);
		bodyPanel.add(pageNames.ADVICE_PAGE.toString(), advicePage);
		
	}
	
	public void start()
	{
		initialisePages();
		
		showPage(pageNames.GRAPHING_PAGE);
		
		setVisible(true);
	}
	
	public void endApp()
    {
        account.closeConnection();
        setVisible(false);
        System.exit(ERROR);
    }
	
	public void showPage(App.pageNames pageName)
	{		
		if (Account.username == null)
		{
			signedInText = "Not Signed-In";
			if (!(pageName.equals(App.pageNames.LOGIN_PAGE)
					|| pageName.equals(App.pageNames.REGISTRATION_PAGE)
					|| pageName.equals(App.pageNames.START_PAGE)))
			{
				pageName = App.pageNames.START_PAGE;
			}
		}
		else
		{
			signedInText = "Welcome " + Account.username + "!";
		}
		
		signedInDisplay.setText(signedInText);
		
		cardControl.show(bodyPanel, pageName.toString());
	}
	
	public void resetPage(Page page, App.pageNames name)
	{
		bodyPanel.add(page, name.toString());
	}
	
	public void reportError(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}
}

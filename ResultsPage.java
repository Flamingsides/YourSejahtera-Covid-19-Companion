import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ResultsPage extends Page
{
	private int positive_num;
	
	public ResultsPage(int num)
	{
		this.positive_num = num;
		setLayout(new BorderLayout());
		
		add(ResultBox(), BorderLayout.NORTH);
		add(ConclusionBox(), BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3, 20, 20));
		buttonPanel.add(BackButton());
		buttonPanel.add(GoToButton());
		buttonPanel.add(TakeCareButton());
		
		if(positive_num > 2)
			add(buttonPanel, BorderLayout.SOUTH);
		else
			add(BackButton(), BorderLayout.SOUTH);
		
	}
	
	private JLabel ResultBox()
	{
		JLabel resultBox = new JLabel("You got " + positive_num + "/6 symptoms positive!", SwingConstants.CENTER);
		resultBox.setFont(new Font("Consolas", Font.BOLD, 40));
		return resultBox;
	}
	
	private JLabel ConclusionBox()
	{
		JLabel conclusionBox = new JLabel ();
		conclusionBox.setFont(new Font("Consolas", Font.PLAIN, 25));
		conclusionBox.setHorizontalAlignment(JLabel.CENTER);
		
		if(positive_num > 2)
			conclusionBox.setText("<html>You have a high chance of being infected with COVID-19!<br /> Consider getting tested and undergo self-isolation to prevent the potential spread of the virus.</html>");
		else
			conclusionBox.setText("<html>You have a low chance of being infected with COVID-19!<br /> Still, remember to monitor your health and seek medical<br /> advice if your condition worsens.</html>");
		
		conclusionBox.revalidate();
		
		return conclusionBox;
	}
	
	private JButton GoToButton()
	{
		JButton goToButton = new JButton("Link to Buy Test-Kits");
		goToButton.addActionListener(new ActionListener() 
			{
			    public void actionPerformed(ActionEvent e) {
			            try 
			            {
							java.awt.Desktop.getDesktop().browse(new URI("https://shp.ee/bm2qy5f"));
						} 
			            catch (IOException | URISyntaxException e1) 
			            {
							e1.printStackTrace();
						}
			        }
			});
		return goToButton;
	}
	
	private JButton TakeCareButton()
	{
		JButton takeCareButton = new JButton("Advice if Infected with COVID-19");
		
		takeCareButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						open(App.pageNames.ADVICE_PAGE);
					}
				});
		
		return takeCareButton;
		
	}		
	
}

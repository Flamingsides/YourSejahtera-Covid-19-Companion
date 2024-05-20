import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AdvicePage extends Page 
{
    public AdvicePage()
    {
    	setLayout(new BorderLayout());
    	add(Title(), BorderLayout.NORTH);
    	add(AdviceText(), BorderLayout.CENTER);
    	add(BackButton(), BorderLayout.SOUTH);
    }

    private JLabel Title()
    {
    	JLabel Title = new JLabel ("<html>Tips for When you Have COVID-19</html>");
    	Title.setFont(new Font("Consolas", Font.BOLD, 25));
    	Title.setHorizontalAlignment(JLabel.CENTER);
		return Title;
    }
    
    private JScrollPane AdviceText()
    {
    	JTextArea advice = new JTextArea(10, 50);
    	advice.setLineWrap(true);
    	advice.setWrapStyleWord(true);
    	advice.setEditable(false);
    	advice.setFont(new Font("Consolas", Font.PLAIN, 20));
    	
    	advice.append("Rest: Make sure to get plenty of rest to help your body fight the virus." + 
    				"\n\nStay Hydrated: Drink plenty of fluids, such as water, to stay hydrated." + 
    				"\n\nIsolate Yourself: Stay at home and avoid contact with others to prevent the spread of the virus. Stay in a separate room and use a separate bathroom if possible." + 
    				"\n\nWear a Mask: If you must be around others, wear a mask to prevent spreading the virus to them." + 
    				"\n\nEat Nutritious Foods: Eat a healthy, balanced diet with plenty of fruits, vegetables, and etc to support you immune system.");
    	
    	JScrollPane scroll = new JScrollPane(advice);
    	
    	return scroll;
    }
    
    public JButton BackButton()
    {
    	JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				open(App.pageNames.RESULTS_PAGE);
			}
		});
		
		return button;
    }
}
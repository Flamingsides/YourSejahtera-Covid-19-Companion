import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Page extends JPanel
{	
	public void open(App.pageNames page)
	{
		Main.app.showPage(page);
	}
	
	public void logSymptoms(int count)
	{
		Main.app.account.logSymptomsCount(count);
	}
	
	public LinkedHashMap<LocalDateTime, Integer> getLogs(int records)
	{
		return Main.app.account.getLog(records);
	}
	
	public void refreshPage(Page page, App.pageNames name)
	{
		Main.app.resetPage(page, name);
	}
	
	public JButton BackButton()
	{
		JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				open(App.pageNames.GRAPHING_PAGE);
			}
		});
		
		return button;
	}
}

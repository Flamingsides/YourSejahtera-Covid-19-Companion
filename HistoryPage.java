import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class HistoryPage extends Page
{
	public HistoryPage()
	{
		final CategoryDataset CATEGORY_DATASET = getDataset();
		final JFreeChart BAR_CHART = createChart(CATEGORY_DATASET);
		
		setLayout(new BorderLayout());
		JPanel chart_panel = new JPanel();
		chart_panel.add(new ChartPanel(BAR_CHART));
		
		add(chart_panel, BorderLayout.CENTER);
		add(BackButton(), BorderLayout.SOUTH);
	}
	
	private CategoryDataset getDataset()
	{
		LinkedHashMap<LocalDateTime, Integer> user_logs = getLogs(3);
		DefaultCategoryDataset history_dataset = new DefaultCategoryDataset();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		for(LocalDateTime date : user_logs.keySet())
		{
			history_dataset.addValue(user_logs.get(date), "Postive Symptoms", date.format(dateFormat));
		}
		
		return history_dataset;
	}
	
	private JFreeChart createChart(CategoryDataset dataset)
	{
		final JFreeChart bar_chart = ChartFactory.createBarChart("User History", "Date", "Positive Symptoms", dataset);
		
		return bar_chart;
	}
}

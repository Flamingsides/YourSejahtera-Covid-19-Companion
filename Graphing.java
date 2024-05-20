import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

public class Graphing extends Page implements ActionListener
{
	JComboBox c1;
	JButton b1, b2, b3, b4;
	JPanel panel, Gpanel, Bpanel;
	ChartPanel c;
	JFrame f;
	JFreeChart chart;
	CategoryDataset dataset;
	StateDatasets SD;
	
	public Graphing()
	{
		super();
		setLayout(new BorderLayout(20, 10));
		
		SD = new StateDatasets();
		panel = new JPanel();
		Gpanel = new JPanel();
		b1 = new JButton("COVID-19 Self Test");
		b2 = new JButton("Nearby Clinics");
		b3 = new JButton("Log Out");
		b4 = new JButton("Show History");
		
		b1.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						refreshPage(new QuestionPage(), App.pageNames.QUESTION_PAGE);
						open(App.pageNames.QUESTION_PAGE);
					}
				});
		
		b2.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						try 
						{
							refreshPage(new NearestClinicPage(), App.pageNames.NEAREST_CLINIC_PAGE);
						} 
						catch (Exception e1) 
						{
							
							e1.printStackTrace();
						}
						open(App.pageNames.NEAREST_CLINIC_PAGE);
					}
				});
		
		b3.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Main.app.account.logout();
			}
		});
		
		b4.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						refreshPage(new HistoryPage(), App.pageNames.HISTORY_PAGE);
						open(App.pageNames.HISTORY_PAGE);
					}
				});
		
		panel.setLayout(new GridLayout(4, 1, 20, 20));
		Gpanel.setLayout(new BorderLayout());
		
		Gpanel.add(new ChartPanel(CreateChart(dataset)), BorderLayout.CENTER);
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b4);
		panel.add(b3);
		add(StateSelect(), BorderLayout.SOUTH);
		add(panel, BorderLayout.EAST);
		add(Gpanel, BorderLayout.CENTER);
	}
	
	public JComboBox StateSelect() {
		String[] states = {"Malaysia","Johor", "Kedah", "Kelantan", "Melaka", "Negeri Sembilan","Pahang", "Perak","Perlis","Pulau Pinang","Sabah","Sarawak","Selangor","Terrengganu","W.P. Kuala Lumpur","W.P. Labuan","W.P. Putrajaya"};
		c1 = new JComboBox(states);
		c1.setSize(10,50);
		c1.addActionListener(this);
		return c1;
	}
	
	public void actionPerformed(ActionEvent ae) {
		String a = (String) c1.getSelectedItem();

		if(a != null) {
			 if(a.equals("Malaysia")) {
					Gpanel.removeAll();
						try {
							Gpanel.add(new ChartPanel(CreateChart(SD.MalaysiaDataset())));
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			 else if (a.equals("Johor")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.JohorDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if(a.equals("Selangor")) {
			Gpanel.removeAll();
				try {
					Gpanel.add(new ChartPanel(CreateChart(SD.SelangorDataset())));
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
			 else if(a.equals("Kedah")) {
					Gpanel.removeAll();
						try {
							Gpanel.add(new ChartPanel(CreateChart(SD.KedahDataset())));
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			 else if(a.equals("Kelantan")) {
					Gpanel.removeAll();
						try {
							Gpanel.add(new ChartPanel(CreateChart(SD.KelantanDataset())));
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			 else if (a.equals("Melaka")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.MelakaDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Negeri Sembilan")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.NSDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Pahang")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.PahangDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Perak")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.PerakDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Perlis")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.PerlisDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Pulau Pinang")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.PPDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Sabah")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.SabahDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Sarawak")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.SarawakDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("Terrenganu")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.TerrenganuDataset())));
					} catch (Exception e) {
						System.out.println("try again");
					}
				}
			 else if (a.equals("w.P. Kuala Lumpur")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.KLDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("W.P. Labuan")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.LabuanDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 else if (a.equals("W.P. Putrajaya")){
					Gpanel.removeAll();
					try {
						Gpanel.add(new ChartPanel(CreateChart(SD.PJDataset())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		Gpanel.revalidate();
		Gpanel.repaint();
		}
		} 
		public JFreeChart CreateChart(CategoryDataset dataset) {
			chart = ChartFactory.createLineChart("Latest Covid Cases", "Date", "Number of Active Cases", dataset);
			   return chart;
		}

}


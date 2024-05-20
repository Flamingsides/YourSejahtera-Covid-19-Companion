import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionPage extends Page
{
	private String[] questions_list;
	private int yes_count, no_count, count;
	private JLabel start_question;
	
	private class AnswerListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			JButton button = (JButton) e.getSource();
			if(button.getText().equals("YES"))
				yes_count++;
			else
				no_count++;
			
			if (count < 5)
				start_question.setText(questions_list[count + 1]);
			count++;

			if(count > 5)
			{
				logSymptoms(yes_count);
				count = 0;
				
				refreshPage(new ResultsPage(yes_count), App.pageNames.RESULTS_PAGE);
				open(App.pageNames.RESULTS_PAGE);
				
				count = 0;
				yes_count = 0;
				no_count = 0;
			}
			
		}
	}
	
	public QuestionPage()
	{
		questions_list = new String[6];
		count = 0;
		questions_list[0] = "<html>Do you have a fever?</html>";
		questions_list[1] = "<html>Do you have a persistent cough?</html>";
		questions_list[2] = "<html>Are you experiencing breathing<br /> difficulties and/or shortness<br /> of breath?</html>";
		questions_list[3] = "<html>Do you have a loss of<br /> taste and/or smell?</html>";
		questions_list[4] = "<html>Are you experiencing fatigue<br /> and/or weakness?</html>";
		questions_list[5] = "<html>Do you have muscle or body aches?</html>";
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(QuestionButton("YES"));
		panel.add(QuestionButton("NO"));
		
		start_question = QuestionPrompt(questions_list[0]); 
		add(start_question, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

	}
	
	private JButton QuestionButton(String yes_no)
	{
		JButton button = new JButton(yes_no);
		button.addMouseListener(new AnswerListener());

		return button;
	}
	
	private JLabel QuestionPrompt(String question) //change to jlabel
	{
		JLabel label = new JLabel(question);
		label.setFont(new Font("Consolas", Font.PLAIN, 24));
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}
	
	public int getYesCount()
	{
		return yes_count;
	}
	
	public int getNoCount()
	{
		return no_count;
	}
	
	
}

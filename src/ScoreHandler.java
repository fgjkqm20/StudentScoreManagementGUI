import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScoreHandler implements ActionListener {
	private StudentScore seletedStudent;
	private List<StudentScore> ssl;
	
	private JFrame frm;
	private JDialog dialog;
	
	Font font = new Font("맑은 고딕", Font.PLAIN, 30);

	public ScoreHandler(StudentScore seletedStudent, List<StudentScore> ssl, 
			JFrame frm, JDialog dialog) {
		this.seletedStudent = seletedStudent;
		this.ssl = ssl;
		this.frm = frm;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(StudentScore ss : ssl) {
			if(ss.equals(seletedStudent)) {
				dialog.setVisible(false);
				
				JDialog dialog2 = new JDialog(frm, "학생 점수 입력", true);
				dialog2.setLayout(null);
				dialog2.setBounds(300, 120, 300, 300);
				
				JLabel[] score = new JLabel[4];
				score[0] = new JLabel("국어");
				score[1] = new JLabel("수학");
				score[2] = new JLabel("사회");
				score[3] = new JLabel("과학");
				
				for(int i=0; i<score.length; i++) {
					score[i].setFont(font);
					score[i].setBounds(0, 40*i, 500, 40);
					dialog2.add(score[i]);
				}
				
				JTextField[] input = new JTextField[4];
				input[0] = new JTextField();
				input[1] = new JTextField();
				input[2] = new JTextField();
				input[3] = new JTextField();
				
				for(int i=0; i<input.length; i++) {
					input[i].setFont(font);
					input[i].setBounds(75, 40*i, 100, 40);
					dialog2.add(input[i]);
				}
				
				JButton confirm = new JButton("확인");
				confirm.setFont(font);
				confirm.setBounds(0, 180, 100, 40);
				confirm.addActionListener(
						new ScoreInputHandler(seletedStudent, ssl, input, dialog2));
				dialog2.add(confirm);
				
				dialog2.setVisible(true);
				break;
			}
		}
	}
}

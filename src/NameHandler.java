import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NameHandler implements ActionListener {
	private JFrame frm;
	private JDialog dialog;
	private JTextField inputName;
	private List<StudentScore> ssl;
	private List<StudentScore> seletedStudent = new ArrayList<>();

	public NameHandler(JFrame frm, JDialog dialog, JTextField inputName,
			List<StudentScore> ssl) {
		this.frm = frm;
		this.dialog = dialog;
		this.inputName = inputName;
		this.ssl = ssl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog2;
		List<JLabel> nameList = new ArrayList<>();
		Font font = new Font("맑은 고딕", Font.PLAIN, 30);
		boolean nameCheck = true;
		int count = 0;
		
		if(inputName.getText().equals("")) {
			nameCheck = false;
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
		} else {
			for(int i=0; i<inputName.getText().length(); i++) {
				if(inputName.getText().charAt(i) < 0xAC00 || 
						inputName.getText().charAt(i) > 0xD7AF) {
					nameCheck = false;
					JOptionPane.showMessageDialog(null, "한글만 입력해주세요.");
					break;
				}
			}
		}
		
		if(nameCheck) {
			dialog.setVisible(false);
			
			dialog2 = new JDialog(dialog, "학생 점수 입력", true);
			dialog2.setLayout(null);
			dialog2.setBounds(300, 120, 500, 500);
			
			for(StudentScore ss : ssl) {
				if(ss.getStudent().getName().equals(inputName.getText())) {
					count++;
					seletedStudent.add(ss);
					nameList.add(new JLabel(count + ". " + 
							ss.getStudent().getName() + " " + 
							ss.getStudent().getGrade() + "학년 " + 
							ss.getStudent().getClassNumber() + "반 " + 
							ss.getStudent().getStudentNumber() + "번"));
					nameList.get(count-1).setFont(font);
					nameList.get(count-1).setBounds(0, 40*(count-1), 500, 40);
					dialog2.add(nameList.get(count-1));
				}
			}
			
			if(count == 1) {
				dialog2.setVisible(false);
				
				JDialog dialog3 = new JDialog(frm, "학생 점수 입력", true);
				dialog3.setLayout(null);
				dialog3.setBounds(300, 120, 300, 300);
				
				JLabel[] score = new JLabel[4];
				score[0] = new JLabel("국어");
				score[1] = new JLabel("수학");
				score[2] = new JLabel("사회");
				score[3] = new JLabel("과학");
				
				for(int i=0; i<score.length; i++) {
					score[i].setFont(font);
					score[i].setBounds(0, 40*i, 500, 40);
					dialog3.add(score[i]);
				}
				
				JTextField[] input = new JTextField[4];
				input[0] = new JTextField();
				input[1] = new JTextField();
				input[2] = new JTextField();
				input[3] = new JTextField();
				
				for(int i=0; i<input.length; i++) {
					input[i].setFont(font);
					input[i].setBounds(75, 40*i, 100, 40);
					dialog3.add(input[i]);
				}
				
				JButton confirm = new JButton("확인");
				confirm.setFont(font);
				confirm.setBounds(0, 180, 100, 40);
				confirm.addActionListener(new ScoreInputHandler(seletedStudent.get(0), ssl, input, dialog3));
				dialog3.add(confirm);
				
				dialog3.setVisible(true);
			}
			
			else if(count > 1) {
				JLabel selectLabel = new JLabel("선택");
				selectLabel.setFont(font);
				selectLabel.setBounds(0, 40*(count+1), 500, 40);
				dialog2.add(selectLabel);
				
				Vector<Integer> selectrBounds = new Vector<>();
				for(int i=1; i<=count; i++) {
					selectrBounds.add(i);
				}
				
				JComboBox<Integer> select = new JComboBox<Integer>(selectrBounds);
				select.setFont(font);
				select.setBounds(75, 40*(count+1), 100, 40);
				dialog2.add(select);
				
				JButton confirm = new JButton("확인");
				confirm.setFont(font);
				confirm.setBounds(200, 40*(count+1), 100, 40);
				confirm.addActionListener
					(new ScoreHandler(seletedStudent.get((Integer)select.getSelectedItem()-1), 
					ssl, frm, dialog2));
				dialog2.add(confirm);
				
				dialog2.setVisible(true);
			}
			
			else {
				JOptionPane.showMessageDialog(null, "존재하지 않는 학생입니다.");
				dialog2.setVisible(false);
				inputName.setText("");
				dialog.setVisible(true);
			}
		}
	}
}

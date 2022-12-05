import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowListHandler implements ActionListener {
	private JDialog dialog;
	private JComboBox<Integer> select;
	private List<StudentScore> ssl;
	private Font font;
	
	public ShowListHandler(JDialog dialog, JComboBox<Integer> select, 
			List<StudentScore> ssl) {
		this.dialog = dialog;
		this.select = select;
		this.ssl = ssl;
		font = new Font("맑은 고딕", Font.PLAIN, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch((Integer)select.getSelectedItem()) {
		case 1:
			showAllList();
			break;
		case 2:
			showSelectedGradeList();
			break;
		case 3:
			showSelectedClassList();
			break;
		case 4:
			dialog.setVisible(false);
			break;
		}
	}
	
	private void showAllList() {
		JDialog dialog2 = new JDialog(dialog, "전체 목록", true);
		dialog2.setLayout(null);
		dialog2.setBounds(300, 120, 700, 500);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(font);
		JScrollPane scroll=new JScrollPane(textArea);
		scroll.setBounds(0, 0, 690, 465);
		dialog2.add(scroll);
		
		for(StudentScore ss : ssl) {
			textArea.append(ss.getStudent().getName() + " " + 
					ss.getStudent().getGrade() + "학년 " + 
					ss.getStudent().getClassNumber() + "반 " + 
					ss.getStudent().getStudentNumber() + "번\n" +
					"국어: " + ss.getScore().getKorean() + "점 " +
					"수학: " + ss.getScore().getMath() + "점 " +
					"사회: " + ss.getScore().getSocial() + "점 " +
					"과학: " + ss.getScore().getScience() + "점\n\n");
		}
		textArea.setEditable(false);
		
		dialog2.setVisible(true);
	}
	
	private void showSelectedGradeList() {
		JDialog dialog2 = new JDialog(dialog, "선택한 학년 목록", true);
		dialog2.setLayout(null);
		dialog2.setBounds(300, 120, 400, 200);
		
		JLabel grade = new JLabel("학년");
		grade.setFont(font);
		grade.setBounds(0, 0, 500, 40);
		dialog2.add(grade);
		
		Vector<Integer> selectBounds = new Vector<Integer>();
		for(int i=1; i<=6; i++) {
			selectBounds.add(i);
		}
		
		JComboBox<Integer> select = new JComboBox<Integer>(selectBounds);
		select.setFont(font);
		select.setBounds(75, 0, 50, 40);
		dialog2.add(select);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(150, 0, 100, 40);
		confirm.addActionListener(new ShowListHandler2(dialog2, select, ssl));
		dialog2.add(confirm);
		
		dialog2.setVisible(true);
	}
	
	private void showSelectedClassList() {
		JDialog dialog2 = new JDialog(dialog, "선택한 학년 반 목록", true);
		dialog2.setLayout(null);
		dialog2.setBounds(300, 120, 400, 200);
		
		JLabel grade = new JLabel("학년");
		grade.setFont(font);
		grade.setBounds(0, 0, 500, 40);
		dialog2.add(grade);
		
		Vector<Integer> selectBounds = new Vector<Integer>();
		for(int i=1; i<=6; i++) {
			selectBounds.add(i);
		}
		
		JComboBox<Integer> select = new JComboBox<Integer>(selectBounds);
		select.setFont(font);
		select.setBounds(75, 0, 50, 40);
		select.setMaximumRowCount(5);
		dialog2.add(select);
		
		JLabel classNumber = new JLabel("반");
		classNumber.setFont(font);
		classNumber.setBounds(0, 40, 500, 40);
		dialog2.add(classNumber);
		
		Vector<Integer> selectBounds2 = new Vector<Integer>();
		for(int i=1; i<=10; i++) {
			selectBounds2.add(i);
		}
		
		JComboBox<Integer> select2 = new JComboBox<Integer>(selectBounds2);
		select2.setFont(font);
		select2.setBounds(75, 40, 50, 40);
		select2.setMaximumRowCount(5);
		dialog2.add(select2);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(0, 80, 100, 40);
		confirm.addActionListener(new ShowListHandler3(dialog2, select, select2, ssl));
		dialog2.add(confirm);
		
		dialog2.setVisible(true);
	}
}

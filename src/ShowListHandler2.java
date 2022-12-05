import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowListHandler2 implements ActionListener {
	private JDialog dialog;
	private JComboBox<Integer> select;
	private List<StudentScore> ssl;
	private Font font;
	
	public ShowListHandler2(JDialog dialog, JComboBox<Integer> select, 
			List<StudentScore> ssl) {
		this.dialog = dialog;
		this.select = select;
		this.ssl = ssl;
		font = new Font("맑은 고딕", Font.PLAIN, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog2 = new JDialog(dialog, "선택한 학년 목록", true);
		dialog2.setLayout(null);
		dialog2.setBounds(300, 120, 700, 500);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(font);
		JScrollPane scroll=new JScrollPane(textArea);
		scroll.setBounds(0, 0, 690, 465);
		dialog2.add(scroll);
		
		for(StudentScore ss : ssl) {
			if(ss.getStudent().getGrade() == (Integer)select.getSelectedItem()) {
				textArea.append(ss.getStudent().getName() + " " + 
						ss.getStudent().getGrade() + "학년 " + 
						ss.getStudent().getClassNumber() + "반 " + 
						ss.getStudent().getStudentNumber() + "번\n" +
						"국어: " + ss.getScore().getKorean() + "점 " +
						"수학: " + ss.getScore().getMath() + "점 " +
						"사회: " + ss.getScore().getSocial() + "점 " +
						"과학: " + ss.getScore().getScience() + "점\n\n");
			}
		}
		textArea.setEditable(false);
		
		dialog2.setVisible(true);
	}
}

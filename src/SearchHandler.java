import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SearchHandler implements ActionListener {
	private JDialog dialog;
	private JTextField inputName;
	private List<StudentScore> ssl;
	
	public SearchHandler(JDialog dialog, JTextField inputName, 
			List<StudentScore> ssl) {
		this.dialog = dialog;
		this.inputName = inputName;
		this.ssl = ssl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog2;
		List<JLabel> infoList = new ArrayList<>();
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
			dialog2 = new JDialog(dialog, "검색 결과", true);
			dialog2.setLayout(null);
			dialog2.setBounds(300, 120, 500, 500);
			
			for(StudentScore ss : ssl) {
				if(ss.getStudent().getName().equals(inputName.getText())) {
					count++;
					infoList.add(new JLabel(count + ". " + 
							ss.getStudent().getName() + " " + 
							ss.getStudent().getGrade() + "학년 " + 
							ss.getStudent().getClassNumber() + "반 " + 
							ss.getStudent().getStudentNumber() + "번"));
					infoList.get(count-1).setFont(font);
					infoList.get(count-1).setBounds(0, 40*(count-1), 500, 40);
					dialog2.add(infoList.get(count-1));
					
					count++;
					infoList.add(new JLabel("   " +
							ss.getScore().getKorean() + "점 " +
							ss.getScore().getMath() + "점 " +
							ss.getScore().getSocial() + "점 " + 
							ss.getScore().getScience() + "점"));
					infoList.get(count-1).setFont(font);
					infoList.get(count-1).setBounds(0, 40*(count-1), 500, 40);
					dialog2.add(infoList.get(count-1));
				}
			}
			
			dialog2.setVisible(true);
		}
	}
}

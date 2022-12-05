import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ScoreInputHandler implements ActionListener {
	private StudentScore seletedStudent;
	private List<StudentScore> ssl;
	private JTextField[] input;
	private JDialog dialog;
	
	public ScoreInputHandler(StudentScore seletedStudent, 
			List<StudentScore> ssl, JTextField[] input, JDialog dialog) {
		this.seletedStudent = seletedStudent;
		this.ssl = ssl;
		this.input = input;
		this.dialog = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean inputAble = true;
		
		for(int i=0; i<input.length; i++) {
			if(input[i].getText().equals("")) {
				JOptionPane.showMessageDialog(null, "값을 입력해주세요.");
				inputAble = false;
				break;
			}
			
			else if(Integer.parseInt(input[i].getText()) < 0 ||
				Integer.parseInt(input[i].getText()) > 100) {
				JOptionPane.showMessageDialog(null, "0~100의 값을 입력해주세요.");
				inputAble = false;
				break;
			}
		}
		
		if(inputAble) {
			for(StudentScore ss : ssl) {
				if(ss.equals(seletedStudent)) {
					dialog.setVisible(false);
					ss.getScore().setKorean(Integer.parseInt(input[0].getText()));
					ss.getScore().setMath(Integer.parseInt(input[1].getText()));
					ss.getScore().setSocial(Integer.parseInt(input[2].getText()));
					ss.getScore().setScience(Integer.parseInt(input[3].getText()));
					JOptionPane.showMessageDialog(null, "점수가 입력되었습니다.");
				}
			}
		}
	}
}

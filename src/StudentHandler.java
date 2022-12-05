import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentHandler implements ActionListener {
	private JDialog dialog;
	private JTextField name;
	private JComboBox<Integer> grade;
	private JComboBox<Integer> classNumbe;
	private JComboBox<Integer> studentNumber;
	
	private Student student;
	private List<StudentScore> ssl;
	
	public StudentHandler(JDialog dialog, JTextField name, 
			JComboBox<Integer> grade, JComboBox<Integer> classNumbe, 
			JComboBox<Integer> studentNumber, List<StudentScore> ssl) {
		this.dialog = dialog;
		this.name = name;
		this.grade = grade;
		this.classNumbe = classNumbe;
		this.studentNumber = studentNumber;
		this.student = new Student();
		this.ssl = ssl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean input = true;
		
		if(name.getText().equals("")) {
			input = false;
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
		} else {
			for(int i=0; i<name.getText().length(); i++) {
				if(name.getText().charAt(i) < 0xAC00 || 
					name.getText().charAt(i) > 0xD7AF) {
					input = false;
					JOptionPane.showMessageDialog(null, "한글만 입력해주세요.");
					break;
				}
			}
			
			if(input) {
				student.setName(name.getText());
				student.setGrade((Integer)grade.getSelectedItem());
				student.setClassNumber((Integer)classNumbe.getSelectedItem());
				student.setStudentNumber((Integer)studentNumber.getSelectedItem());
				
				boolean add = true;
				
				for(StudentScore ss : ssl) {
					if(ss.getStudent().getGrade() == student.getGrade() &&
						ss.getStudent().getClassNumber() == student.getClassNumber() &&
						ss.getStudent().getStudentNumber() == student.getStudentNumber()) {
						
						JOptionPane.showMessageDialog(null, "이미 등록된 학생입니다.");
						add = false;
						break;
					}
				}
				
				if(add) {
					ssl.add(new StudentScore(student, new Score()));
					JOptionPane.showMessageDialog(null, "학생이 추가되었습니다.");
					dialog.setVisible(false);
					Collections.sort(ssl);
				}
			}
		}
	}
}

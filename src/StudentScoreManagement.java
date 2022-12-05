import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class StudentScoreManagement {
	private ArrayList<StudentScore> ssl;
	private JFrame frm;
	private Font font;
	
	public StudentScoreManagement() {
		ssl = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream("학생 점수.save");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object readObject = ois.readObject();
			
			if(readObject instanceof ArrayList<?>) {
				for(Object obj : (ArrayList<?>)readObject) {
					if(obj instanceof StudentScore) {
						ssl.add((StudentScore)obj);
					}
				}
			}

			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			ssl = new ArrayList<>();
		}
		
		frm = new JFrame("초등학교 점수 관리 프로그램");
		frm.setBounds(300, 120, 500, 500);
		frm.setLayout(null);
		
		font = new Font("맑은 고딕", Font.PLAIN, 30);
	}
	
	public void showMenu() {
		JLabel[] menu = new JLabel[8];
		menu[0] = new JLabel("1. 학생 추가");
		menu[1] = new JLabel("2. 학생 점수 입력");
		menu[2] = new JLabel("3. 학생 삭제");
		menu[3] = new JLabel("4. 검색");
		menu[4] = new JLabel("5. 목록 보기");
		menu[5] = new JLabel("6. 초기화");
		menu[6] = new JLabel("7. 종료");
		menu[7] = new JLabel("선택");
		
		for(int i=0; i<menu.length; i++) {
			menu[i].setFont(font);
			menu[i].setBounds(0, 40*i, 500, 40);
			frm.add(menu[i]);
		}
		
		Vector<Integer> selectBounds = new Vector<Integer>();
		for(int i=1; i<=7; i++) {
			selectBounds.add(i);
		}
		
		JComboBox<Integer> select = new JComboBox<Integer>(selectBounds);
		select.setFont(font);
		select.setBounds(75, 280, 50, 40);
		frm.add(select);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(150, 280, 100, 40);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch((Integer)select.getSelectedItem()) {
				case 1: 
					addStudent(); 
					fileSave();
					break;
				case 2:
					inputStudentScore(); 
					fileSave();
					break;
				case 3: 
					removeStudent();
					fileSave();
					break;
				case 4: 
					search(); 
					break;
				case 5: 
					showList(); 
					break;
				case 6: 
					initialization();
					fileSave();
					break;
				case 7:
					System.exit(0);
					break;
				}
			}
		});
		frm.add(confirm);
		
		frm.setVisible(true);	
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void addStudent() {
		JDialog dialog = new JDialog(frm, "학생 정보 입력", true);
		dialog.setLayout(null);
		dialog.setBounds(300, 120, 300, 300);
		
		JLabel[] studentInfo = new JLabel[4];
		studentInfo[0] = new JLabel("이름");
		studentInfo[1] = new JLabel("학년");
		studentInfo[2] = new JLabel("반");
		studentInfo[3] = new JLabel("번호");
		
		for(int i=0; i<studentInfo.length; i++) {
			studentInfo[i].setFont(font);
			studentInfo[i].setBounds(0, 40*i, 500, 40);
			dialog.add(studentInfo[i]);
		}
		
		JTextField name = new JTextField();
		name.setFont(font);
		name.setBounds(75, 0, 100, 40);
		dialog.add(name);
		
		Vector<Integer> gradeBounds = new Vector<>();
		for(int i=1; i<=6; i++) {
			gradeBounds.add(i);
		}
		
		Vector<Integer> classNumberBounds = new Vector<>();
		for(int i=1; i<=10; i++) {
			classNumberBounds.add(i);
		}
		
		Vector<Integer> studentNumberBounds = new Vector<>();
		for(int i=1; i<=30; i++) {
			studentNumberBounds.add(i);
		}
		
		JComboBox<Integer> grade = new JComboBox<Integer>(gradeBounds);
		grade.setFont(font);
		grade.setMaximumRowCount(5);
		grade.setBounds(75, 40, 100, 40);
		dialog.add(grade);
		
		JComboBox<Integer> classNumbe = new JComboBox<Integer>(classNumberBounds);
		classNumbe.setFont(font);
		classNumbe.setMaximumRowCount(5);
		classNumbe.setBounds(75, 80, 100, 40);
		dialog.add(classNumbe);
		
		JComboBox<Integer> studentNumber = new JComboBox<Integer>(studentNumberBounds);
		studentNumber.setFont(font);
		studentNumber.setMaximumRowCount(5);
		studentNumber.setBounds(75, 120, 100, 40);
		dialog.add(studentNumber);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(0, 180, 100, 40);
		confirm.addActionListener(new StudentHandler(dialog, name, grade, classNumbe, studentNumber, ssl));
		dialog.add(confirm);
		
		dialog.setVisible(true);
	}
	
	private void inputStudentScore() {
		JDialog dialog = new JDialog(frm, "학생 점수 입력", true);
		dialog.setLayout(null);
		dialog.setBounds(300, 120, 500, 200);
		
		JLabel nameLabel = new JLabel("점수 입력할 학생 이름");
		nameLabel.setFont(font);
		nameLabel.setBounds(5, 0, 500, 40);
		dialog.add(nameLabel);
		
		JTextField inputName = new JTextField();
		inputName.setFont(font);
		inputName.setBounds(5, 40, 100, 40);
		dialog.add(inputName);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(120, 40, 100, 40);
		confirm.addActionListener(new NameHandler(frm, dialog, inputName, ssl));
		dialog.add(confirm);
		
		dialog.setVisible(true);
	}

	private void removeStudent() {
		JDialog dialog = new JDialog(frm, "학생 삭제", true);
		dialog.setLayout(null);
		dialog.setBounds(300, 120, 500, 200);
		
		JLabel nameLabel = new JLabel("삭제할 학생 이름");
		nameLabel.setFont(font);
		nameLabel.setBounds(5, 0, 500, 40);
		dialog.add(nameLabel);
		
		JTextField inputName = new JTextField();
		inputName.setFont(font);
		inputName.setBounds(5, 40, 100, 40);
		dialog.add(inputName);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(120, 40, 100, 40);
		confirm.addActionListener(new RemoveHandler(dialog, inputName, ssl));
		dialog.add(confirm);
		
		dialog.setVisible(true);
	}
	
	private void search() {
		JDialog dialog = new JDialog(frm, "검색 하기", true);
		dialog.setLayout(null);
		dialog.setBounds(300, 120, 500, 200);
		
		JLabel nameLabel = new JLabel("찾을 학생 이름");
		nameLabel.setFont(font);
		nameLabel.setBounds(5, 0, 500, 40);
		dialog.add(nameLabel);
		
		JTextField inputName = new JTextField();
		inputName.setFont(font);
		inputName.setBounds(5, 40, 100, 40);
		dialog.add(inputName);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(120, 40, 100, 40);
		confirm.addActionListener(new SearchHandler(dialog, inputName, ssl));
		dialog.add(confirm);
		
		dialog.setVisible(true);
	}
	
	private void showList() {
		JDialog dialog = new JDialog(frm, "목록 보기", true);
		dialog.setLayout(null);
		dialog.setBounds(300, 120, 500, 500);
		
		JLabel[] menu = new JLabel[5];
		menu[0] = new JLabel("1. 전체 목록 보기");
		menu[1] = new JLabel("2. 특정 학년 목록 보기");
		menu[2] = new JLabel("3. 특정 반 목록 보기");
		menu[3] = new JLabel("4. 메뉴로 돌아가기");
		menu[4] = new JLabel("선택");
		
		for(int i=0; i<menu.length; i++) {
			menu[i].setFont(font);
			menu[i].setBounds(0, 40*i, 500, 40);
			dialog.add(menu[i]);
		}
		
		Vector<Integer> selectBounds = new Vector<Integer>();
		for(int i=1; i<=4; i++) {
			selectBounds.add(i);
		}
		
		JComboBox<Integer> select = new JComboBox<Integer>(selectBounds);
		select.setFont(font);
		select.setBounds(75, 160, 50, 40);
		dialog.add(select);
		
		JButton confirm = new JButton("확인");
		confirm.setFont(font);
		confirm.setBounds(150, 160, 100, 40);
		confirm.addActionListener(new ShowListHandler(dialog, select, ssl));
		dialog.add(confirm);
		
		dialog.setVisible(true);
	}
	
	private void initialization() {
		ssl.clear();
		JOptionPane.showMessageDialog(null, "초기화 되었습니다.");
	}
	
	private void fileSave() {
		try {
			FileOutputStream fos = new FileOutputStream("학생 점수.save");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			oos.writeObject(ssl);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "save 파일 생성에 문제가 생겼습니다.");
			System.exit(-1);
		}
	}
}
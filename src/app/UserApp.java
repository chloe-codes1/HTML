package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.UsersDao;
import service.UserService;
import service.UserServiceImpl;
import vo.UsersVO;

public class UserApp {

	public static void main(String[] args) {

		new User_Gui();
	}

}

class User_Gui extends JFrame {

	private static final String SansSerif = null;
	JButton login, join, update, withdraw, exit, intro, instruction;
	TextField id, pw, name, role;
	TextArea output;

	UsersDao dao = new UsersDao();
	UserService service = new UserServiceImpl(dao);

	public User_Gui() {

		super("User Service");

		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(1, 5));
		p1.add(login = new JButton("Log-In"));
		p1.add(join = new JButton("Join"));
		p1.add(update = new JButton("Update"));
		p1.add(withdraw = new JButton("Withdraw"));
		p1.add(exit = new JButton("Exit"));
		p1.setBackground(Color.GRAY);
		add(p1, BorderLayout.SOUTH);

		Panel panel = new Panel();
		panel.setLayout(new GridLayout(4, 1));
		panel.add(intro = new JButton("하단의 버튼을 눌러 Log-In, Join, Update 작업을 하세요."));
		add("North", panel);

		Panel p2 = new Panel();
		p2.add(new JLabel("    ID    "));
		p2.add(id = new TextField(10));
		p2.add(new JLabel("    PW    "));
		p2.add(pw = new TextField(10));
		p2.add(new JLabel("    Name    "));
		p2.add(name = new TextField(10));
		p2.add(new JLabel("    Role    "));
		p2.add(role = new TextField(10));
		role.setText("user");
		p2.setBackground(Color.LIGHT_GRAY);

		panel.add("North", p2);

		Panel pan = new Panel();
		pan.add(instruction = new JButton("Need Help?"));
		panel.add(pan);

		Panel p3 = new Panel();
		p3.setLayout(new GridLayout(2, 1));
		ImageIcon icon = new ImageIcon("e://work//workspace_0708//java_02_ex//src//day21_ex/chubbyCat.jpg");
		Image img = icon.getImage();
		Image modifiedImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		ImageIcon modifiedIcon = new ImageIcon(modifiedImg);
		JLabel imageLabel = new JLabel(modifiedIcon);
		p3.add(imageLabel);

		
		p3.add(output = new TextArea(15,40));
		output.setFont(new Font(SansSerif, 4, 15));

		add(p3, BorderLayout.CENTER);

		setSize(700, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		intro.addActionListener(e -> {
			output.setText("");
			output.setText("하단의 버튼을 누르세요!\n");

		});

		instruction.addActionListener(e -> {
			output.setText("  ===== Introduction ===== \n\n1. To Log-In  ->  Input ID & PW + Log-in Button \n");
			output.append("\n2. To Join   ->   Input ID & PW & Name & Role + Click the Join Button \n");
			output.append("\n3. To Update the info   ->   Input ID & PW & Name & Role + Update Button Click \n");
			output.append("\n4. To Exit  ->  Click the Exit Button");
		});

		exit.addActionListener(e -> System.exit(0));

		login.addActionListener(e -> {
			if (id.getText().isEmpty() || pw.getText().isEmpty()) {
				output.setText(" [ Log-In 검색 실패  ] \n");
				output.append("ID와 PW를 모두 입력하세요!\n");
				return;
			}
//			} else if (service.login(id.getText(), pw.getText()) == false) {
//				output.setText(" [ Log-In 실패 ] \nID와 PW를 확인하세요.\n");
//			
//			} else if (service.login(id.getText(), pw.getText()) == true) {
				output.setText(" [ Log-In 성공 ]\n");
				output.append(id.getText() + "님 안녕하세요!");
			id.setText("");
			pw.setText("");
		});

		join.addActionListener(e -> {

			UsersVO vo = new UsersVO();
			if (id.getText().isEmpty() || pw.getText().isEmpty() || name.getText().isEmpty()) {
				output.setText("회원가입을 위해 상단의 모든 칸을 입력하세요.\n");
				return;
			} 
			vo.setId(id.getText());
			vo.setPassword(pw.getText());
			vo.setName(name.getText());
			vo.setRole(role.getText());
			int result = service.addUser(vo);
			if(result ==1) {
				output.setText(" [ 회원 가입 성공 ]\n");
				output.append(id.getText()+"님 회원가입이 완료되었습니다.");
				id.setText("");
				pw.setText("");
				name.setText("");
			} else {
				output.setText(" [ 회원 가입 실패 ] \n");
				output.append("ID, PW, Name을 확인해주세요.");
				id.setText("");
				pw.setText("");
				name.setText("");
			}
		});

		withdraw.addActionListener(e -> {

			UsersVO vo = new UsersVO();
			if (id.getText().isEmpty()) {
				output.setText("회원 탈퇴를 위해 아이디를 입력하세요.\n");
				return;
			} 
			vo.setId(id.getText());
			int result = service.deleteUser(vo.getId());
			if(result ==1) {
				output.setText(" [ 회원 탈퇴 성공 ]\n");
				output.append(id.getText()+"님 회원 탈퇴 되었습니다.");
				id.setText("");
			} else {
				output.setText(" [ 회원 탈퇴 실패 ] \n");
				output.append("ID를 확인해주세요.");
				id.setText("");
			}
		});
		
		
		
		
		update.addActionListener(e -> {
			UsersVO vo = new UsersVO();
			if (id.getText().isEmpty() || pw.getText().isEmpty() || name.getText().isEmpty()) {
				output.setText("회원 정보 변경을 위해 상단의 모든 칸을 입력하세요.\n");
				return;
			}
			vo.setPassword(pw.getText());
			vo.setName(name.getText());
			vo.setRole(role.getText());
			vo.setId(id.getText());
			
			int result = service.updateUser(vo);
			service.updateUser(vo);
			if(result ==1) {
				output.setText(" [ Update 성공 ] \n");
				output.append(id.getText()+ "님 회원정보가 수정되었습니다.");
				id.setText("");
				pw.setText("");
				name.setText("");
			}else {
				output.setText(" [ Update 실패 ] \n");
				output.append("입력하신 정보를 다시 확인해주세요.");
				id.setText("");
				pw.setText("");
				name.setText("");
			}
		});

		
		
	}

}
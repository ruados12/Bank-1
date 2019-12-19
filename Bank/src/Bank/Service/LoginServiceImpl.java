package Bank.Service;

import java.io.IOException;

import Bank.Controller;
import Bank.DAO.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{
	static CommonService common = new CommonServiceImpl();
	private DatabaseService dbServ = new DatabaseServiceImpl();

	@Override
	public void LoginProc(Parent root) {
		String id = new String();
		String pw = new String();
		if(!GetIdPw(root, id, pw)) return;
		
		if(!true) {
			System.out.println("로그인 실패");
			return;
		}
		
		System.out.println("로그인 성공");
	}
	private boolean GetIdPw(Parent root, String id, String pw) {
		TextField userId = common.GetTextField(root, fx_Id);
		TextField userPw = common.GetTextField(root, fx_Id);
		if(userId==null || userPw==null) {
			common.ErrorMsg("Login Error", "아이디, 비밀번호를 불러오는데 실패하였습니다.\n 관리자에게 문의해주세요.");
			return false;
		}
		
		id = userId.getText();
		pw = userPw.getText();
		if(id.isEmpty()) {
			common.InfoMsg("아이디를 입력해 주세요.");
			userId.requestFocus();
			return false;
		}else if(pw.isEmpty()) {
			common.InfoMsg("비밀번호를 입력해 주세요.");
			userPw.requestFocus();
			return false;
		}
		
		return true;
	}

	@Override
	public void OpenMember() {
		Stage membershipForm = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Membership.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		}catch(IOException e) {
			e.printStackTrace();
		}
		membershipForm.setScene(new Scene(parent));
		
		Controller ctr = loader.getController();
		ctr.setMembershipForm(parent);
		
		MembershipService membershipSrv = new MembershipServiceImpl();
		membershipForm.show();
	}

}

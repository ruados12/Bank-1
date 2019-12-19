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
		String id = GetId(root);
		String pw = GetPw(root);
		if(id==null || pw==null) return;
		
		DatabaseService dbSQL = new DatabaseServiceImpl();
		if(!dbSQL.Open()) return;
		if(!dbSQL.IdEnter(id, pw)) {
			return;
		}
		
		common.InfoMsg("로그인 성공");
	}
	private String GetId(Parent root) {
		TextField userId = common.GetTextField(root, fx_Id);
		if(userId==null) {
			common.ErrorMsg("Login Error", "아이디를 불러오는데 실패하였습니다.\n 관리자에게 문의해주세요.");
			return null;
		}
		
		String id = userId.getText();
		if(id.isEmpty()) {
			common.InfoMsg("아이디를 입력해 주세요.");
			userId.requestFocus();
			return null;
		}
		
		return id;
	}
	private String GetPw(Parent root) {
		TextField userPw = common.GetTextField(root, fx_Pw);
		if(userPw==null) {
			common.ErrorMsg("Login Error", "비밀번호를 불러오는데 실패하였습니다.\n 관리자에게 문의해주세요.");
			return null;
		}
		
		String pw = userPw.getText();
		if(pw.isEmpty()) {
			common.InfoMsg("아이디를 입력해 주세요.");
			userPw.requestFocus();
			return null;
		}
		
		return pw;
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

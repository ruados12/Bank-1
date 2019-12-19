package Bank;

import java.net.URL;
import java.util.ResourceBundle;

import Bank.Service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller implements Initializable{
	private Parent loginForm;
	private Parent membershipForm;
	private LoginService loginServ;
	private CommonService commonServ;
	private MembershipService membershipServ;
	
	public void setLoginForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	public void setMembershipForm(Parent membershipForm) {
		this.membershipForm = membershipForm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginServ = new LoginServiceImpl();
		commonServ = new CommonServiceImpl();
		membershipServ = new MembershipServiceImpl();
	}

	public void LoginProc() {
		loginServ.LoginProc(loginForm);
	}
	public void CancelProc(ActionEvent event) {
		commonServ.WindowClose(event);
	}
	public void OpenMember() {
		loginServ.OpenMember();
	}
	public void MembershipProc() {
		membershipServ.membershipProc(membershipForm);
	}
}

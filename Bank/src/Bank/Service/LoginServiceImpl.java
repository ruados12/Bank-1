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

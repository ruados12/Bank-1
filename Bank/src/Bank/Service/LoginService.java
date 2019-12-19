package Bank.Service;

import javafx.scene.Parent;

public interface LoginService {
	String fx_Id = "#userId";
	String fx_Pw = "#userPw";
	
	public void LoginProc(Parent root);
	public void OpenMember();
}

package Bank.Service;

import javafx.scene.Parent;

public interface MembershipService {
	// Membership fx:id 
	String fx_Name = "#";
	String fx_Id = "#";
	String fx_Pw = "#";
	String fx_IdNum = "#";
	String fx_AccNum = "#";
	String fx_PhoneNum = "#";
	String fx_Money = "#";
	
	public void membershipProc(Parent membershipForm);
}


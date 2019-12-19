package Bank.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Bank.Member;
import Bank.DAO.*;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class MembershipServiceImpl implements MembershipService{
	static CommonService common = new CommonServiceImpl();

	final static String[] fxml_Id= {fx_Name, fx_Id, fx_Pw, fx_PwOk, fx_IdNum1, fx_IdNum2, fx_AccNum, fx_PhoneNum2, fx_PhoneNum3, fx_Money}; 
	
	Map<String, TextField> db_Data;
	
	Map<String, String> print_Txt;
	// 공란 fxId값, 문구 저장용
	
	
	private Map<String, TextField> getTextFieldInfo(Parent membershipForm) {
		// DB저장, 데이터 HashMap형태로 저장
		Map<String, TextField> txtFldMap = new HashMap();
		for(String txtFldId:fxml_Id) {
			TextField txtFld = (TextField)membershipForm.lookup(txtFldId);
			// txtFld 텍스트필드 내용 저장용
			txtFldMap.put(txtFldId, txtFld);
			// txtFldMap에 텍스트필드 ID와 내용 저장
		}
		return txtFldMap;
	}

	
	@Override
	public void membershipProc(Parent membershipForm) {
		
		boolean bl = txtIsEmpty(membershipForm); // 텍스트필드 공란, 암호확인 메서드
		// 문제가 생기면 true, 문제가 없으면 false
		if(bl) return;
		
		
		
		
		
		db_Data = getTextFieldInfo(membershipForm);
		// db_Data db에 저장할 내용 
		// key - FXML ID값
		// value - TextField 내용 저장
		System.out.println(db_Data.get(fx_Name));
		
		Member member = new Member();

		
		member.setName((common.GetTextField(membershipForm, fx_Name)).getText().toString());
		member.setId((common.GetTextField(membershipForm, fx_Id)).getText().toString());
		member.setPw((common.GetTextField(membershipForm, fx_Pw)).getText().toString());
		
		member.setIdNum(SumFx_IdNum(
						common.GetTextField(membershipForm, fx_IdNum1),
						common.GetTextField(membershipForm, fx_IdNum2)
						));
						
		member.setAccNum((common.GetTextField(membershipForm, fx_AccNum)).getText().toString());
		
		member.setPhoneNum(SumFx_PhoneNum(
				common.GetComboBox(membershipForm, fx_PhoneNum1),
				common.GetTextField(membershipForm, fx_PhoneNum2),
				common.GetTextField(membershipForm, fx_PhoneNum3)
				));
				
		
		member.setMoney(Integer.parseInt((common.GetTextField(membershipForm, fx_Money)).getText()));
		// 잔액은 Integer로 형변환
		
		
		System.out.println(member.getName());
		System.out.println(member.getId());
		System.out.println(member.getPw());
		System.out.println(member.getIdNum());
		System.out.println(member.getMoney());
		System.out.println(member.getPhoneNum());
		
		
	}
	
	
	public boolean txtIsEmpty(Parent membershipForm) {
		// 텍스트필드 공란 확인용
		print_Txt = new HashMap<String, String>();
		String fxml_Txt[] = {
				"이름","아이디","패스워드","패스워드","주민등록번호","주민등록번호","계좌번호","핸드폰번호","핸드폰번호","잔액"
				};
		int cnt = 0; // for문 카운트용
		
		for(String fxIdList:fxml_Id) {
			print_Txt.put(fxIdList, fxml_Txt[cnt++]);
			if(((TextField)membershipForm.lookup(fxIdList)).getText().isEmpty()) {
				common.InfoMsg("주의", print_Txt.get(fxIdList) + "를(을) 입력해 주세요.");
				membershipForm.lookup(fxIdList).requestFocus();
				return true;
			}
		}
		
		// 패스워드 중복 확인
		
		TextField pwFld = (TextField)membershipForm.lookup(fxml_Id[2]);
		TextField pwOkFld = (TextField)membershipForm.lookup(fxml_Id[3]); 
		// clear, requesFocus를 위해 따로 생성
		String pw = pwFld.getText(); // 2 Pw 
		String pwOk = pwOkFld.getText(); // 3 PwOk
		System.out.println(pw);
		System.out.println(pwOk);
		if(!pw.equals(pwOk)) {
			common.InfoMsg("패스워드가 다릅니다.","패스워드를 다시 입력하세요.");
			pwFld.clear();
			pwOkFld.clear();
			pwFld.requestFocus();
			return true;
		}
		return false;
	}
	private String SumFx_PhoneNum(ComboBox<String> firNum, TextField midNum, TextField lastNum) {
		String fx_PhoneNum = firNum.getValue() + "-" + midNum + "-" + lastNum; 
		
		return fx_PhoneNum;
	}


	public String SumFx_IdNum(TextField forword, TextField back) {
		String fx_IdNum = forword.getText() + "-" + back.getText();
		return fx_IdNum;
	}

	
	
}

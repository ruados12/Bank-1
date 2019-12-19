package Bank;

public class Member {
	private String name;		// 이름
	private String id;			// 아이디
	private String pw;			// 비밀번호
	private String idNum;		// 주민등록번호 (Identification Number)
	private String accNum;		// 계좌번호 (Account Number)
	private String phoneNum; 	// 전화번호
	private long money;			// 잔액
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String regNum) {
		this.idNum = regNum;
	}
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
}


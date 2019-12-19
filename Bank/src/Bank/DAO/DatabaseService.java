package Bank.DAO;

import Bank.Member;

public interface DatabaseService {
   public boolean Open();
   public boolean Select(String id, String pw);
   public void Insert(Member member);

}
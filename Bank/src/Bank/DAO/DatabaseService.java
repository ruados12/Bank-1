package Bank.DAO;

import Bank.Member;

public interface DatabaseService {
   public boolean Open();
   public void Insert(Member member);
   public boolean IdEnter(String id, String pw);
   public boolean IdCopy(String id);

}
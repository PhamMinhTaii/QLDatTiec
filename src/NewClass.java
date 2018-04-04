
import DAO.UserDAO;
import entity.User;


public class NewClass {
    
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        String id = "tinh";
        String userName = "tin1h";
         String password = "1213";
         
          
        User entity = new User(id, userName, password, "Tinh", "Tinh", "Tinh", "Tinh", "Tinh", "Tinh", "Tinh");
   
        
       
    }
    
}


package CommonConstance;
public class ReplaceString {
    
    public  static String UserName(String userName){        
        return  userName.trim().replaceAll("\\s+", "").toLowerCase();       
    }
    
}

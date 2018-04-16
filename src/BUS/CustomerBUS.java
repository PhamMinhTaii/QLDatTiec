
package BUS;

import DAO.CustomDAO;

public class CustomerBUS {
    
    private final CustomDAO customDAO = new CustomDAO();
    
     public String findUserName(String CustomerID) {
         try {
             return  customDAO.findUserName(CustomerID);
         } catch (Exception e) {
             throw e;
         }
     }
}

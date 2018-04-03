package BUS;

import CommonConstance.Encryption;
import CommonConstance.ReplaceString;
import DAO.UserDAO;
import entity.User;
import java.util.List;

public class UserBUS {

    private final UserDAO userDao;

    public UserBUS() {
        userDao = new UserDAO();
    }
    
    public List<User> findListUser(){
        try {
             return userDao.findListUser();
        } catch (Exception e) {
            return null;
        }
       
    }

    public int Login(String userName, String PassWord) {

        try {

            User user = userDao.Login(userName);          
            // so sánh pass truyền vào với pass mã hoá trong csdl
            if (user.getPassword().equals(Encryption.sha1(PassWord))) {

                if ("Kích Hoạt".equals(user.getActive())) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -2;
            }

        } catch (Exception e) {
            return 0;
        }
    }

    public int validUser(String userName, String password, String confirm, String firstName, String lastName, String email) {

        if (!userName.matches("([a-zA-Z_0-9]{8,12}+)")) {
            return -1;
        }
        if (!password.matches("^(?=.*[A-Z].*)(?=.*[a-z].*)(?=.*[^a-zA-Z_0-9].*)(?=.*[0-9].*)(?=.*[\\S].*).{8,16}$")) {
            return -2;
        }
        if (!password.matches(confirm)) {
            return -3;
        }
        String regex = "[\\p{L}\\s]+";
        if ((!firstName.matches(regex) || !lastName.matches(regex))) {
            return -4;
        }
        if (!email.isEmpty() && (!email.matches("[a-zA-Z_0-9]+@\\D+[.]\\D+"))) {
            return -5;
        }
        return 1;
    }

    public int addUser(User user, String confirmPass) {

        String validUserName = ReplaceString.UserName(user.getUserName()); // chuẩn hoá lại tên user

        if (userDao.findUserName(validUserName) == null) {
            if (validUser(validUserName, user.getPassword(), confirmPass, user.getFirstName(), user.getLastName(), user.getEmail()) == 1) {

                try {
                    String encryp = Encryption.sha1(user.getPassword());
                    boolean kq = userDao.insertUser(user);
                    if (kq) {
                        return 1;
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
            return validUser(validUserName, user.getPassword(), confirmPass, user.getFirstName(), user.getLastName(), user.getEmail()); // return ra lỗi
        }
        return -10; // user đã tồn tại
    }
}

package BUS;

import CommonConstance.*;
import DAO.UserDAO;
import entity.User;
import java.util.List;

public class UserBUS {

    private final UserDAO userDao;

    public UserBUS() {
        userDao = new UserDAO();
    }

    public List<User> findListUser(String keyword) {
        try {
            return userDao.findListUser(keyword);
        } catch (Exception e) {
            return null;
        }

    }

    public String findRole(String userName) {
        try {
            return userDao.findRole(userName);
        } catch (Exception e) {
            throw e;
        }
    }

    public int countColunms(String property, String value) {
        try {
            return userDao.countColunms(property, value);
        } catch (Exception e) {
            throw e;
        }

    }

    public int Login(String userName, String PassWord) {
        String validUserName = ReplaceString.UserName(userName);
        String validPass = ReplaceString.UserName(PassWord);

        try {
            User user = userDao.Login(validUserName);
            // so sánh pass truyền vào với pass mã hoá trong csdl
            if (user.getPassword().equals(Encryption.sha1(validPass))) {
                if (user.getActive().equals(ComBoBox.active)) {
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
    
    public String findUserName(String userID){
        try {         
            return userDao.findUserName(userID);
        } catch (Exception e) {
            throw  e;
        }
    }
  
    public int validUser(String userName, String password, String confirm, String firstName, String lastName, String email) {
        //  System.out.println(password  + " " + confirm );
        if (!userName.matches("([a-zA-Z_0-9]{8,12}+)")) {
            return -1;
        }
        if (!password.matches("^(?=.*[A-Z].*)(?=.*[a-z].*)(?=.*[^a-zA-Z_0-9].*)(?=.*[0-9].*)(?=.*[\\S].*).{8,16}$")) {
            return -2;
        }
        if (!password.equals(confirm)) {
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

        if (userDao.findUser(validUserName) == null) {
            if (validUser(validUserName, user.getPassword(), confirmPass, user.getFirstName(),
                    user.getLastName(), user.getEmail()) == 1) {

                try {
                    String encryp = Encryption.sha1(user.getPassword());
                    user.setPassword(encryp); // set pass là pass mới mã hoá
                    user.setUserName(validUserName);
                    return userDao.insertUser(user);

                } catch (Exception e) {
                    throw e;
                }
            }
            return validUser(validUserName, user.getPassword(), confirmPass, user.getFirstName(),
                    user.getLastName(), user.getEmail()); // return ra lỗi
        }
        return -10; // user đã tồn tại
    }

    public int updateUser(User user, String confirmPass) {
        if (validUser(user.getUserName(), user.getPassword(), confirmPass, user.getFirstName(), user.getLastName(), user.getEmail()) == 1) {
            try {
                String encryp = Encryption.sha1(user.getPassword());
                user.setPassword(encryp); // set pass là pass mới mã hoá
                return userDao.updateUser(user);
            } catch (Exception e) {
                throw e;
            }
        }
        return validUser(user.getUserName(), user.getPassword(), confirmPass,
                user.getFirstName(), user.getLastName(), user.getEmail()); // return ra lỗi
    }

    public int deleteUser(String userName) {
        try {
            return userDao.deleleUser(userDao.findUser(userName));
        } catch (Exception e) {
            throw e;
        }
    }
    
    
}

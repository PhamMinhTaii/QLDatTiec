/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import CommonConstance.AlertOfMe;
import CommonConstance.ReplaceString;
import DAO.BooksDAO;
import entity.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Taii
 */
public class BooksBUS {

    private final BooksDAO booksDao;

    public BooksBUS() {
        booksDao = new BooksDAO();
    }

    public int addCustomer(Customer c) {
        try {
            if (ktraCustomer(c.getFirstName(), c.getLastName(), c.getPhone(), c.getAddress()) == 1) {
                booksDao.addCustomer(c);
                return 1;
            }
            return ktraCustomer(c.getFirstName(), c.getLastName(),
                    c.getPhone(), c.getAddress());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int ktraCustomer(String firstName, String lastName, String phone, String address) {
        firstName = ReplaceString.UserName(firstName);// chuan hoa lai ten
        lastName = ReplaceString.UserName(lastName);
        String regexName = "[\\p{L}\\s]+";
        if (!firstName.matches(regexName) || !lastName.matches(regexName)) {
             AlertOfMe.alert("Tên khách hàng không đúng định dạng");
            return -1;
        }
        if (!phone.matches("\\d{9,15}")) {
             AlertOfMe.alert("SĐT không đúng định dạng");
            return -2;
        }
        if (!address.matches("[\\p{L}\\s\\d/]+")) {
             AlertOfMe.alert("Địa chỉ không đúng định dạng");
            return -3;
        }
        return 1;
    }

    public List<Menu> loadListMenu() {
        try {
            return booksDao.loadListMenu();
        } catch (Exception ex) {
            throw ex;
        }
    }

    // load combobox concept
    public List<Concept> loadConcept() {
        try {
            return booksDao.loadConcept();
        } catch (Exception ex) {
            throw ex;
        }
    }

    // su ly combobox concept
    public List<Concept> loadConceptForText(String name) {
        try {
            return booksDao.loadConceptForText(name);
        } catch (Exception ex) {
            throw ex;
        }
    }

    // load combobox room
    public List<Room> loadCbbRom() {
        try {
            return booksDao.loadCbbRom();
        } catch (Exception ex) {
            throw ex;
        }
    }

    // load room id
    public Room getRomId(String nameRoom) {
        try {
            return booksDao.getRomId(nameRoom);
        } catch (Exception e) {
            throw e;
        }
    }

    // add book
    public int addBook(Booking b) {
        try {
            if (ktraBook(b.getRoom(), b.getBookingDate(), b.getShift()) == 1) {
                booksDao.addBook(b);
                return 1;
            }
            return ktraBook(b.getRoom(), b.getBookingDate(), b.getShift());
        } catch (Exception e) {
            throw e;
        }
    }

    //kiem tra booking    
    public int ktraBook(Room room, Date dateBook, String isShift) {
        if (room == null) {      
              AlertOfMe.alert("Bạn chưa chọn Sảnh đặt tiệc!");
            return -4;
        }
        if (dateBook == null) { 
              AlertOfMe.alert("Bạn chưa chọn ngày đặt tiệc!");
            return -5;
        }
        if (isShift == null) {    
            AlertOfMe.alert("Bạn chưa chọn giờ đãi tiệc!");
            return -6;
        }
        return 1;
    }

    //get concept
    public Concept getConcept(String idConcept) {
        try {
            return booksDao.getConcept(idConcept);
        } catch (Exception e) {
            throw e;
        }
    }

    // add bookDetail
    public void addBookDetail(BookingDetail bd) {
        try {
            booksDao.addBookDetail(bd);
        } catch (Exception e) {
            throw e;
        }
    }

    // get user
    public User getUSer(String userName) {
        try {
            return booksDao.getUSer(userName);
        } catch (Exception e) {
            throw e;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import CommonConstance.ReplaceString;
import DAO.BooksDAO;
import entity.*;
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
            return -1;
        }
        if (!phone.matches("\\d{10,13}")) {
            return -2;
        }
        if (!address.matches("[\\p{L}\\s\\d/]+")) {
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
    // su ly combobox

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
         return booksDao.getRomId(nameRoom);
     }
     // add book
      private void addBook(Booking b){
          booksDao.addBook(b);
      }

}

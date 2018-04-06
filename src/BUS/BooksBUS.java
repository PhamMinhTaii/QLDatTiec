/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

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

    public int addCustomer(Customer customer) {
        try {
            booksDao.addCustomer(customer);
            return 1;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Menu> loadListMenu() {
        try {
            return booksDao.loadListMenu();
        } catch (Exception ex) {
            throw ex;
        }
    }

}

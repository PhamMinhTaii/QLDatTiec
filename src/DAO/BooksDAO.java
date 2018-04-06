/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Minh Taii
 */
public class BooksDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;

    public BooksDAO() {

    }

    public void connect() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

    }

    // Them Khach hang
    public int addCustomer(Customer customer) {
        try {
            connect();
            session.save(customer);
            transaction.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return 0;
        } finally {
            session.close();
        }
    }

    // load listview Menu
    public List<Menu> loadListMenu() {
        try {
            connect();
            List<Menu> list = null;
            String hql = "FROM Menu WHERE status = 1 AND isSelect = 1";
            Query query = session.createQuery(hql);
            list = query.list();
            for (Menu mn : list) {
                System.out.println(mn.getMenuName() + " " + mn.getPrice());
            }
            return query.list();
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return null;
        } finally {
            session.close();
        }
    }
}

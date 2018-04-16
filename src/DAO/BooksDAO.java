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
    // load combobox concept

    public List<Concept> loadConcept() {
        try {
            connect();
            String hql = "FROM Concept";
            Query query = session.createQuery(hql);
            List<Concept> listConcept = query.list();
            return listConcept;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // su ly combobox
    public List<Concept> loadConceptForText(String name) {
        try {
            connect();
            String hql = String.format("FROM Concept WHERE Color = '%s'", name);
            Query query = session.createQuery(hql);
            List<Concept> listConcept = query.list();
            return listConcept;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // load combobox room
    public List<Room> loadCbbRom() {
        try {
            connect();
            String hql = String.format("FROM Room");
            Query query = session.createQuery(hql);
            List<Room> listRoom = query.list();
            return listRoom;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // load room id
    public Room getRomId(String nameRoom) {
        try {
            connect();
            String hql = String.format("FROM Room WHERE roomName = '%s'", nameRoom);
            Query query = session.createQuery(hql);
            List<Room> list = query.list();
            Room rm = new Room();
            for (Room r : list) {
                rm = r;
            }
            return rm;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // add book
    public void addBook(Booking b) {
        try {
            connect();
            session.save(b);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
    }

    // get concept
    public Concept getConcept(String idConcept) {
        try {
            connect();
            String hql = String.format("FROM Concept WHERE conceptId = '%s'", idConcept);
            Query query = session.createQuery(hql);
            List<Concept> list = query.list();
            Concept rm = new Concept();
            for (Concept r : list) {
                rm = r;
            }
            return rm;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // get menu
    public void addBookDetail(BookingDetail bd) {
        try {
            connect();
            session.save(bd);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
    }

    // get user
    public User getUSer(String userName) {
        try {
            connect();
            String hql = String.format("FROM User WHERE userName = '%s'", userName);
            Query query = session.createQuery(hql);
            List<User> list = query.list();
            User us = new User();
            for (User u : list) {
                us = u;
            }
            return us;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

}

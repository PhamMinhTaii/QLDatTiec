/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mchange.v2.async.StrandedTaskReporting;
import entity.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Minh Taii
 */
public class MenuDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;

    public MenuDAO() {
        //  connect();
    }

    public void connect() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    public List<Menu> loadMenu(String titleMenu) {
        try {
            connect();
            Session session = HibernateUtil.getSessionFactory().openSession();
//            Criteria cr = session.createCriteria(Menu.class);            
//            Menu mn=new Menu();
//            cr.add(Restrictions.eq(mn.getTitleMenu().toString(),"TM"));            
//            return cr.list();

            String hql = "FROM Menu mn WHERE mn.titleMenu = '" + titleMenu + "' AND status=1";
            Query query = session.createQuery(hql);
            List<Menu> listMenu = query.list();

            return listMenu;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // List load All
    public List<Menu> loadAllMenu() {
        try {
            connect();
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Menu WHERE status=1";
            Query query = session.createQuery(hql);
            List<Menu> listMenu = query.list();
            return listMenu;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // List title menu
    public List<TitleMenu> loadTitleMenu() {
        try {
            connect();
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM TitleMenu";
            Query query = session.createQuery(hql);
            List<TitleMenu> listMenu = query.list();
            return listMenu;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }

    // Luu Checkbox chon mon
    public void update(Menu mn) {
        try {
            connect();
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            session.update(mn);
//            session.getTransaction().commit();
            session.update(mn);
            transaction.commit();

        } catch (Exception ex) {
            //throw ex;
            System.out.println(ex.toString());
        } finally {
            session.close();
        }

    }

    //them mon an
    public int addMenu(Menu mn) {
        try {
            connect();
            session.save(mn);
            transaction.commit();
            return 1;
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
    }

    // lay titlemenu
    public TitleMenu getTitleMenu(String id) {
        try {
            connect();
            String hql = String.format("FROM TitleMenu WHERE titleId = '%s'", id);
            Query query = session.createQuery(hql);
            List<TitleMenu> list = query.list();
            TitleMenu tm = new TitleMenu();
            for (TitleMenu t : list) {
                tm = t;
                System.out.println(t.getTitleId() + " " + t.getTitleName());
            }
            return tm;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        } finally {
            session.close();
        }
    }

    // Reset isSelect 
    public void resetSelect() {
        connect();
        Menu temp = new Menu();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mchange.v2.async.StrandedTaskReporting;
import entity.Menu;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Minh Taii
 */
public class MenuDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;

    public MenuDAO() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    public List<Menu> loadMenu() {
        try {
            List<Menu> dsMenu = null;
            String hql = "SELECT * FROM menu";
            Query query = session.createQuery(hql);
            dsMenu = query.list();
            return dsMenu;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }
}

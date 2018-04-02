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
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    public List<Menu> loadMenu(String titleMenu) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
//            Criteria cr = session.createCriteria(Menu.class);            
//            Menu mn=new Menu();
//            cr.add(Restrictions.eq(mn.getTitleMenu().toString(),"TM"));            
//            return cr.list();

            String hql="FROM Menu mn WHERE mn.titleMenu = '"+titleMenu+"' AND status=1";
            Query query=session.createQuery(hql);
            List<Menu> listMenu=query.list();
           
            return listMenu;
        } catch (Exception ex) {
            return null;
        } finally {
            session.close();
        }
    }
}

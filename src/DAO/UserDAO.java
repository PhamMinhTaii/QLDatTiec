package DAO;

import entity.*;
import java.util.List;
import java.util.NoSuchElementException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class UserDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction trans;

    public void connettion() {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
    }

    public List<User> findListUser() {
        connettion();
        try {

            return session.createCriteria(User.class).list();
        } catch (HibernateException e) {
            trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public User findUserName(String userName) {
        connettion();
        Criteria cr = session.createCriteria(User.class);

        try {
            Criterion user = Restrictions.eq("userName", userName);
            cr.add(user);
            return (User) cr.list().stream().findFirst().get();

        } catch (NoSuchElementException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public User Login(String userName) {
        return findUserName(userName);
    }

    public int insertUser(User entity) {
        connettion();
        try {
            session.save(entity);
            trans.commit();
            return 1;

        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public int updateUser(User entity) {
    
        connettion();
        try {           
            session.update(entity);          
            trans.commit();
            return 1;

        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

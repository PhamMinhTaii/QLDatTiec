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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction trans;

    public void connettion() {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
    }

    public List<User> findListUser(String keyword) {
        connettion();
        try {
            Criteria criteria = session.createCriteria(User.class);
            if (!keyword.isEmpty()) {
                String s = String.format("%%%s%%", keyword);
                Criterion c1 = Restrictions.ilike("userName", s);
                Criterion c2 = Restrictions.ilike("firstName", s);
                Criterion c3 = Restrictions.ilike("lastName", s);
                Criterion c4 = Restrictions.ilike("role", s);
                Criterion c5 = Restrictions.ilike("active", s);
                Criterion c6 = Restrictions.ilike("gender", s);
                Criterion c7 = Restrictions.ilike("address", s);
                criteria.add(Restrictions.or(c1, c2, c3, c4, c5, c6, c7));
            }
            return criteria.list();

        } catch (HibernateException e) {
            trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public int countColunms(String property, String value) {
        connettion();
        Criteria criteria = session.createCriteria(User.class);
        try {
            if (!property.isEmpty()) {
                Criterion c = Restrictions.ilike(property, value);
                criteria.add(c);
                return Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
            }
            return Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        } catch (HibernateException e) {
            throw e;
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

    public String findRole(String userName) {
        connettion();
        Criteria cr = session.createCriteria(User.class);

        try {
            Criterion user = Restrictions.eq("userName", userName);
            cr.add(user);
            return ((User) cr.list().stream().findFirst().get()).getRole();

        } catch (Exception e) {
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

    public int deleleUser(User entity) {

        connettion();
        try {
            session.delete(entity);
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


package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Connettion {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Session session;
    protected Transaction trans;

    public void connettion() {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
    }
}

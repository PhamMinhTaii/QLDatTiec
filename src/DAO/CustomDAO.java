package DAO;

import entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class CustomDAO extends Connettion {

    public String findUserName(String CustomerID) {
        connettion();
        Criteria criteria = session.createCriteria(Customer.class);
        try {
            Criterion id = Restrictions.eq("customterId", CustomerID);
            criteria.add(id);
            return ((Customer) criteria.list().stream().findFirst().get()).getFirstName() + " "
                    + ((Customer) criteria.list().stream().findFirst().get()).getLastName();
        } catch (HibernateException e) {
            trans.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}

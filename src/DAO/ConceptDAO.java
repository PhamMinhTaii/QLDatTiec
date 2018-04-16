
package DAO;

import entity.Concept;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ConceptDAO extends Connettion{
    
    public double priceConcept(String conceptID){
        connettion();
        Criteria criteria = session.createCriteria(Concept.class);
        try {
            Criterion id = Restrictions.eq("conceptId", conceptID);
            criteria.add(id);
            String price = ((Concept) criteria.list().stream().findFirst().get()).getPrice();
            return Double.parseDouble(price);          
        } catch (HibernateException e) {
            trans.rollback();  
            throw e;
        } finally {
            session.close();
        }
    }
    
}

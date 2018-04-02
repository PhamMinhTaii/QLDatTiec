
package DAO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAO {       
    
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private   Session   session ;
    private  Transaction trans;

    public UserDAO() {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
    }
    
    public User Login(String userName){
        
        try {            
            String query = String.format("select * from user where user_name = '%s'", userName);          
            // Hack con mẹ nó não mày cái return này... =]]. 2 tiếng google của t đó.
            return (User)session.createSQLQuery(query).addEntity(User.class).list().stream().findFirst().get();
        } catch (Exception e) {
           return  null;
        }finally{            
            session.close();
        }
    }
    
    public  boolean insertUser(){
        
        User user = new User("US5","Tìnhh", "passs", true, true);      
        
        try{            
            session.save(user);
            trans.commit();
            return true;
            
        } catch (Exception e) {
            throw e;
        }finally{            
            session.close();
        }
    }
    
    
    
    
}

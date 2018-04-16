package DAO;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author USER
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
             Configuration config = new Configuration();      
            config.configure("hibernate.cfg.xml");   
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
              .applySettings(config.getProperties());
              sessionFactory = config.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

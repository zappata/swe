package database;

import model.Field;
import model.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
  
  private static SessionFactory sessionFactory = null;
  
  //Database connection
  private static SessionFactory configureSessionFactory() throws HibernateException {
    sessionFactory = new Configuration().configure().buildSessionFactory();
    return sessionFactory;
  }
  
  public static void main(String args[]) {
    configureSessionFactory();
    Session session = null;
    Transaction transaction = null;
    
    try {
      session = sessionFactory.openSession();
      
      /*
      Map map = session.createNamedQuery("get_data", Map.class)
          .getSingleResult();
      */
      
      transaction = session.beginTransaction();
      //map = new Map(1, null);
      Field field = new Field(1, "W", 1, 1); 
      
      session.save(field);
      session.flush();
      transaction.commit();
      
    } catch(Exception e){
      e.printStackTrace();
      transaction.rollback();
    } finally {
      if(session!=null) {
        session.close();
      }
    }
  }
}

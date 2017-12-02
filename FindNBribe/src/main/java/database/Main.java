package database;

import model.*;
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
    System.out.println("Databse loaded");
    return sessionFactory;
  }
  
  public static void main(String args[]) {
    configureSessionFactory();
    Session session = null;
    Transaction transaction = null;
    
    try {
      
      Player player = new Player(1, "alex", 1, "nichts");
      Map map = new Map(1, player);
      Field field = new Field(1, "W", 1, 1, map);
      Turn turn = new Turn(1, 1, 1, 1, 2, 1, player);
      
      map.getFields().add(field);
      player.getTurns().add(turn);
      
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();
      
      session.save(player);
      session.save(map);
      session.save(field);
      session.save(turn);
      
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

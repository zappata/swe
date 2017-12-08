package network.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecuteTurn {
  
  private static SessionFactory sessionFactory = null;

  private static SessionFactory configureSessionFactory() throws HibernateException {
    sessionFactory = new Configuration().configure().buildSessionFactory();
    return sessionFactory;
  }

  @RequestMapping("/turn")
  public String playerTurn() {
    
    configureSessionFactory();
    Session session = null;
    session = sessionFactory.openSession();
    
    
    
    session.flush();
    session.close();
    
    return "";
  }
  
}

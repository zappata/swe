package network.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import model.Player;

@RestController
public class Result {

  private static SessionFactory sessionFactory = null;

  private static SessionFactory configureSessionFactory() throws HibernateException {
    sessionFactory = new Configuration().configure().buildSessionFactory();
    return sessionFactory;
  }

  @RequestMapping("/result")
  public String getResult() {

    //Configuration of Database
    configureSessionFactory();
    Session session = null;
    String result = "";
    
    //Connection to Database
    session = sessionFactory.openSession();

    //Get the Winner from Database
    Player winner = session.createNamedQuery("get_winners", Player.class)
        .setParameter("status", "winner").getSingleResult();

    session.flush();
    session.close();
    
    //Display the result
    if (winner != null) {
      result = "Der Spieler: " + winner.getName() + " ist der Gewinner";
    } else {
      result = "Es gibt keinen Gewinner";
    }
    
    return result;
    
  }

}

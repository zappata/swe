package network.controller;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import model.Player;

@RestController
public class AssignUser {


  private static SessionFactory sessionFactory = null;

  private static SessionFactory configureSessionFactory() throws HibernateException {
    sessionFactory = new Configuration().configure().buildSessionFactory();
    return sessionFactory;
  }


  @RequestMapping("/assign")
  public String addUser() {
    configureSessionFactory();
    Session session = null;
    session = sessionFactory.openSession();

    List<Player> players = session.createNamedQuery("get_players", Player.class).getResultList();

    if (players.size() < 2) {
      Player new_player = new Player();
      session.persist(new_player);
      //Give the information of Player

      session.update(new_player);
      session.flush();
      session.close();
      
      return "Sie sind zum Spielen angemeldet";
    }
    
    return "Das Spiel ist voll";
  }


}

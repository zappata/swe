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
    
    //Configuration of Database
    configureSessionFactory();
    Session session = null;
    
    //Connection to Database
    session = sessionFactory.openSession();

    //Get the players
    List<Player> players = session.createNamedQuery("get_players", Player.class).getResultList();

    //Check the Players count
    if (players.size() < 2) {
      Player new_player = new Player();
      session.persist(new_player);
      
      /*Give some information of Player
       * 
       * 
       */
      
      //Save the information
      session.update(new_player);
      session.flush();
      session.close();
      
      return "Sie sind zum Spielen angemeldet";
    }
    
    return "Das Spiel ist voll";
  }


}

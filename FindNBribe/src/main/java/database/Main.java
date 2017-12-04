package database;

import model.*;
import rule.*;
import interfaces.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Main {

  private static SessionFactory sessionFactory = null;

  // Database connection
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

      IRules rules = new Rules();

      Field field = null;
      Turn turn = null;
      Map map = null;
      Player player = null;
      List<Player> players = new ArrayList<Player>();

      int direction = 0;
      int row = 0;
      int column = 0;
      String type = "";
      int id = 0;

     /* player = new Player(1, "hamed", 1, "nichts");
      map = new Map(1, player);

      session.save(player);
      session.save(map);
      */

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      /* Insert Data */
      for (int i = 0; i < 2; i++) {
        System.out.println("Geben Sie den Namen vom Spieler ein:");
        String name = br.readLine();

        player = new Player(i, name, i, "nichts");
        map = new Map(i, player);

        for (int j = 1; j < 5; j++) {
          for (int k = 1; k < 9; k++) {
            type = "";
            do {
              System.out.println("Geben Sie Die Art des " + j + " " + (id + 1)
                  + ". Feldes ein: Achtung! zulässige Eingabe sind w, b, g");
              type = br.readLine();
            } while (!type.equals("w") && !type.equals("b") && !type.equals("g"));
            
            field = new Field(id, type, j, k, map);
            map.getFields().add(field);
            
            
            id++;
          }
        }

        System.out.println("Geben Sie die Reihe des Schlosses an: ");
        type = br.readLine();
        map.setCastle_row(Integer.parseInt(type));

        System.out.println("Geben Sie die Spalte des Schlosses an: ");
        type = br.readLine();
        map.setCastle_column(Integer.parseInt(type));

        session.save(map);
        session.save(field);
        
        if (!rules.ControllMapSize(map) || !rules.ControllMapConditions(map)
            || !rules.ControllMapWaterCondition(map) || !rules.ControllServerDataGeneration(map)
            || !rules.ControllTreasureCastlePlace(map)
            || !rules.ControllTreasureCastleWaterPlace(map)) {
          player.setStatus("loser");
          System.out.println("Sie haben gegen die Regeln verstoßen und somit verloren");
        }

        players.add(player);

      }

      for (int j = 1; j < 21; j++) {
        System.out.println("Geben Sie die Richtung ein:");



        try {
          direction = Integer.parseInt(br.readLine());
        } catch (Exception e) {
          System.out.println("Zulässige Eingaben sind 1-4");
          j--;
        }

        row = 1;
        column = 1;
        switch (direction) {
          case 1:
            row = row + 1;
            break;
          case 2:
            row = column + 1;
            break;
          case 3:
            row = row - 1;
            break;
          case 4:
            row = column - 1;
            break;
        }
        if(j%2==0) {
          turn = new Turn(2, (j + 1), row, column, direction, player); 
          players.get(2).getTurns().add(turn);
          session.save(turn);
        }else {
          turn = new Turn(1, (j + 1), row, column, direction, player); 
          players.get(1).getTurns().add(turn);
          session.save(turn);
        }
      }

      if (!rules.ControllPlayerEntry(turn, map) && !rules.ConrollRounds(turn)) {

      } else {
        player.setStatus("loser");
      }

      /* Persisitierung */
      for (int i = 1; i <= 2; i++) {
        session.save(players.get(i));
      }

      /*
      // Query Gewinner
      Query winner = session.createQuery("from ");
      winner.setParameter(1, "");

      // Query Alle Spieler
      Query all_player = session.createQuery("");
      all_player.setParameter(1, "");

      // Query verlierer
      Query loser = session.createQuery("");
      loser.setParameter(1, "");

      // Query 10. bis 20. Spielzüge
      Query all_turns = session.createQuery("");
      all_turns.setParameter(1, "");
      */
      session.flush();
      transaction.commit();

    } catch (Exception e) {
      e.printStackTrace();
      transaction.rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}

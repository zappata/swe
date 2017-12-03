package database;

import model.*;
import rule.*;
import interfaces.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


      int direction = 0;
      int row = 0;
      int column = 0;
      String type = "";

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      /* Einfügen der Daten */
      for (int i = 0; i < 2; i++) {
        System.out.println("Geben Sie den Namen vom Spieler ein:");
        String name = br.readLine();

        player = new Player(i, name, i, "nichts");
        map = new Map(i, player);

        for (int j = 1; j < 5; j++) {
          int id = 0;
          for (int k = 1; k < 9; k++) {
            type = "";
            do {
              System.out.println("Geben Sie Die Art des " + (id + 1)
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
        System.out.println("Geben Sie die Reihe des Schlosses an: ");
        type = br.readLine();
        map.setCastle_column(Integer.parseInt(type));
        
        if (rules.ControllMapSize(map) && rules.ControllMapConditions(map)
            && rules.ControllMapWaterCondition(map) && !rules.ControllServerDataGeneration(map)) {
          
        } else {
          player.setStatus("loser");
        }



        for (int j = 1; j < 31; j++) {
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
          turn = new Turn(j, (j + 1), row, column, direction, player);
          player.getTurns().add(turn);
        }
        if(rules.ControllPlayerEntry(turn, map) && rules.ConrollRounds(turn)) {
          
        }else{
          player.setStatus("loser");
        }

        /* Persisitierung */
        session.save(player);
        session.save(map);
        session.save(field);
        session.save(turn);

      }



      // Query Gewinner
      Query winner = session.createQuery("");
      winner.setParameter(1, "");

      // Query Alle Spieler
      Query all_player = session.createQuery("");
      all_player.setParameter(1, "");

      // Query verlierer
      Query loser = session.createQuery("");
      loser.setParameter(1, "");

      // Query 10. bis 20. Spielzüge
      Query turns = session.createQuery("");
      turns.setParameter(1, "");

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

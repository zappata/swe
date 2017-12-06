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
import org.apache.log4j.BasicConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  private static SessionFactory sessionFactory = null;

  // Database connection
  private static SessionFactory configureSessionFactory() throws HibernateException {
    sessionFactory = new Configuration().configure().buildSessionFactory();
    logger.info("Databse Loaded successfully");
    return sessionFactory;
  }

  public static void main(String args[]) {
    configureSessionFactory();
    Session session = null;
    Transaction transaction = null;

    try {

      // *************Variables ****
      IRules rules = new Rules();
      Field field = null;
      Turn turn = null;
      Map map = null;
      Player player = null;

      List<Player> players = new ArrayList<Player>();

      int direction = 0;

      // Begin Position of players
      int player_1_row = 1;
      int player_1_column = 1;
      int player_2_row = 8;
      int player_2_column = 8;

      int id = 0;
      String type = "";

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      // **************** Variables

      session = sessionFactory.openSession();

      // Retrieving datas
      try {
        List<Field> felder = session.createNamedQuery("get_field", Field.class)
            .setParameter("type", "w").getResultList();

        for (Field feld : felder) {
          System.out.println(
              "Die Reihe ist " + feld.getRow() + " und die Spalte ist " + feld.getColumn());
        }

        Player winners = session.createNamedQuery("get_winners", Player.class)
            .setParameter("status", "winner").getSingleResult();

        System.out.println("Der Gewinner ist " + winners.getName());

        Player result = session.createNamedQuery("get_player", Player.class)
            .setParameter("name", "Alex").getSingleResult();

        System.out.println("Der Spieler ist " + result.getStatus());

        int from = 9;
        int til = 21;

        List<Turn> all_turns = session.createNamedQuery("get_game_rounds", Turn.class)
            .setParameter("from", from).setParameter("til", til).getResultList();

        for (Turn t : all_turns) {
          System.out.println("Die Richtung war " + t.getDirection());
        }
      } catch (Exception e) {
        logger.error("Keine Daten vorhanden");
      }

      transaction = session.beginTransaction();

      // Insert Data
      for (int i = 0; i < 2; i++) {
        System.out.println("Geben Sie den Namen vom Spieler ein:");
        String name = br.readLine();
        player = new Player(i, name, (i + 1), "winner");
        map = new Map(i, player);

        session.persist(player);
        session.persist(map);

        for (int j = 1; j < 5; j++) {
          for (int k = 1; k < 9; k++) {
            type = "";
            do {

              System.out.println("Geben Sie Die Art des " + j + " " + (id + 1)
                  + ". Feldes ein: Achtung zulässige Eingabe sind w, b, g");
              type = br.readLine();
            } while (!type.equals("w") && !type.equals("b") && !type.equals("g"));

            field = new Field(id, type, j, k, map);
            map.getFields().add(field);
            session.persist(field);
            id++;
          }
        }

        System.out.println("Geben Sie die Reihe des Schlosses an: ");
        type = br.readLine();
        map.setCastle_row(Integer.parseInt(type));

        System.out.println("Geben Sie die Spalte des Schlosses an: ");

        type = br.readLine();

        map.setCastle_column(Integer.parseInt(type));



        if (!rules.ControllServerDataGeneration(map)) {
          player.setStatus("loser");
        }


        map.setTreasure_row(2);
        map.setTreasure_column(1);

        if (!rules.ControllMapSize(map) || !rules.ControllMapConditions(map)
            || !rules.ControllMapWaterCondition(map) || !rules.ControllTreasureCastlePlace(map)
            || !rules.ControllTreasureCastleWaterPlace(map)) {
          player.setStatus("loser");
        }


        players.add(player);
        session.update(player);
        session.update(map);

      }

      session.flush();
      transaction.commit();


      // Creating the Full Map
      Map fullmap = new Map();

      List<Map> maps = session.createNamedQuery("get_maps", Map.class).getResultList();

      for (int i = 0; i < 2; i++) {
        if (i == 1) {
          maps.get(i).setCastle_row(maps.get(i).getCastle_row() + 4);
          maps.get(i).setCastle_column(maps.get(i).getCastle_column() + 4);
          maps.get(i).setTreasure_row(maps.get(i).getTreasure_row() + 4);
          maps.get(i).setTreasure_column(maps.get(i).getTreasure_column() + 4);
          for (int k = 0; k < 32; k++) {
            maps.get(i).getFields().get(k).setRow(maps.get(i).getFields().get(k).getRow() + 4);
            logger.info("Die Zeile ist jetzt: " + maps.get(i).getFields().get(k).getRow());
          }
        }
        for (int j = 0; j < maps.get(i).getFields().size(); j++) {
          fullmap.getFields().add(maps.get(i).getFields().get(j));
        }
      }

      transaction = session.beginTransaction();


      for (int j = 0; j < 20; j++) {
        if (players.get(0).getStatus().equals("loser")
            || players.get(1).getStatus().equals("loser")) {
          break;
        }
        System.out.println("Geben Sie die Richtung ein:");

        try {
          direction = Integer.parseInt(br.readLine());
        } catch (Exception e) {
          System.out.println("Zulässige Eingaben sind 1 - 4");
          j--;
        }

        if (j % 2 == 0) {
          switch (direction) {
            case 1:
              player_1_row++;
              break;
            case 2:
              player_1_column++;
              break;
            case 3:
              player_1_row--;
              break;
            case 4:
              player_1_column--;
              break;
          }
        } else {
          switch (direction) {
            case 1:
              player_2_row++;
              break;
            case 2:
              player_2_column++;
              break;
            case 3:
              player_2_row--;
              break;
            case 4:
              player_2_column--;
              break;
          }
        }

        if (j % 2 == 0) {
          turn = new Turn(j, ((j + 1) / 2), player_1_row, player_1_column, direction, player);
          players.get(0).getTurns().add(turn);
          session.persist(turn);
          logger.info("Speiler_1 row: " + player_1_row);
          logger.info("Speiler_1 col: " + player_1_column);

          if (!rules.ControllPlayerEntry(turn, fullmap) || !rules.ConrollRounds(turn)) {
            players.get(0).setStatus("loser");
            break;
          }

        } else {
          turn = new Turn(j, ((j + 1) / 2), player_2_row, player_2_column, direction, player);
          players.get(1).getTurns().add(turn);
          session.persist(turn);
          logger.info("Speiler_2 row: " + player_2_row);
          logger.info("Speiler_2 col: " + player_2_column);

          if (!rules.ControllPlayerEntry(turn, fullmap) || !rules.ConrollRounds(turn)) {
            players.get(1).setStatus("loser");
            break;
          }
        }

      }

      // Persisitierung
      for (int i = 1; i <= 2; i++) {
        session.update(player);
      }

      session.flush();
      transaction.commit();

    } catch (

    Exception e) {
      e.printStackTrace();
      transaction.rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}

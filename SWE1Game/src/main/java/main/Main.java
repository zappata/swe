package main;

import model.*;
import rule.*;
import interfaces.*;
import jaxb.PlayerJAXB;
import jaxb.TurnJAXB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.KI;
import controller.MapGeneration;
import controller.PlayerGeneration;

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


    PlayerGeneration playerGeneration = new PlayerGeneration();
    MapGeneration mapGeneration = new MapGeneration();
    KI ki = new KI();

    configureSessionFactory();
    Session session = null;
    Transaction transaction = null;

    try {

      // *************Variables ****
      IRules rules = new Rules();
      Turn turn = null;
      Map map = null;
      Player player = null;
      int[][] costs = null;
      String[][] mapView = new String[8][8];
      List<Integer> visited = new ArrayList<Integer>();
      List<Integer> visited2 = new ArrayList<Integer>();
      int row = 0;
      int col = 0;
      List<Map> maps = new ArrayList<Map>();

      // Players List (2)
      List<Player> players = new ArrayList<Player>();

      // Begin Position of players
      int player_1_row = 1;
      int player_1_column = 1;
      int player_2_row = 8;
      int player_2_column = 8;
      int p2 = 0;

      int id = 0;
      String type = "";

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      // **************** Variables

      session = sessionFactory.openSession();

      transaction = session.beginTransaction();

      // Insert Data
      for (int i = 0; i < 2; i++) {

        System.out.println("Geben Sie den Namen vom Spieler ein:");
        String name = br.readLine();

        player = playerGeneration.generatePlayer(name, id, i);

        map = mapGeneration.generateMap(player, id);

        id++;

        // Check if Client set the Treasure
        if (!rules.ControllServerDataGeneration(map)) {
          player.setStatus("loser");
        }

        // Set the castle row
        System.out.println("Geben Sie die Reihe des Schlosses an: ");
        type = br.readLine();
        map.setCastle_row(Integer.parseInt(type));

        // Set the castle column
        System.out.println("Geben Sie die Spalte des Schlosses an: ");
        type = br.readLine();
        map.setCastle_column(Integer.parseInt(type));

        // set the row of treasure
        System.out.println("Geben Sie die Reihe des Schatzes an: ");
        type = br.readLine();
        map.setTreasure_row(Integer.parseInt(type));

        // set the column of treasure
        System.out.println("Geben Sie die Spalte des Schatzes an: ");
        type = br.readLine();
        map.setTreasure_column(Integer.parseInt(type));

        // set the start_row of player
        System.out.println("Geben Sie die Reihe des Spielers an: ");
        type = br.readLine();
        player.setRow(Integer.parseInt(type));

        // set the start_column of player
        System.out.println("Geben Sie die Spalte des Spielers an: ");
        type = br.readLine();
        player.setColumn(Integer.parseInt(type) + p2);

        // Check the Conditions and the rules of the Map
        if (!rules.ControllMapSize(map) || !rules.ControllMapConditions(map)
            || !rules.ControllMapWaterCondition(map) || !rules.ControllTreasureCastlePlace(map)
            || !rules.ControllTreasureCastleWaterPlace(map)) {
          player.setStatus("loser");
        }

        // save player and map
        session.persist(player);
        session.persist(map);
        maps.add(map);
        players.add(player);

      }

      session.flush();
      transaction.commit();


      // Creating the Full Map
      Map fullmap = new Map();

      fullmap = mapGeneration.mergeMaps(maps);

      transaction = session.beginTransaction();



      // Player make their Turns
      for (int j = 0; j < 200; j++) {

        if (players.get(0).getStatus().equals("loser")
            || players.get(1).getStatus().equals("loser")) {
          break;
        }

        row = 1;
        col = 1;

        for (int i = 0; i < 64; i++) {

          mapView[row - 1][col - 1] = fullmap.getFields().get(i).getType();

          if (col == 8) {
            row = row + 1;
            col = 0;
          }

          col++;

        }

        //Set the position of treaure, castle and player on the view map
        mapView[players.get(0).getRow() - 1][players.get(0).getColumn() - 1] = "P1";
        mapView[maps.get(0).getTreasure_row() - 1][maps.get(0).getTreasure_column() - 1] = "T1";
        mapView[maps.get(0).getCastle_row() - 1][maps.get(0).getCastle_column() - 1] = "C1";

        mapView[players.get(1).getRow() - 1][players.get(1).getColumn() - 1] = "P2";
        
        logger.debug("T_Row: " + maps.get(1).getTreasure_row());
        logger.debug("T_Column: " + maps.get(1).getTreasure_column());
        
        mapView[((maps.get(1).getTreasure_row()) - 1)][((maps.get(1).getTreasure_column()) - 1)] = "T2";
        mapView[((maps.get(1).getCastle_row()) - 1)][((maps.get(1).getCastle_column()) - 1)] = "C2";

        //show the map
        for (int j1 = 0; j1 < 8; j1++) {
          for (int k = 0; k < 8; k++) {
            System.out.print(mapView[j1][k] + "    ");
          }

          System.out.println("");
        }

        costs = ki.mapCosts(fullmap);

        // Save the Turn of the Players (j % 2 == 0 -> first player)
        if (j % 2 == 0) {
          if (visited.size() != 0) {
            visited = ki.steps(players.get(0), costs, visited);
          } else {
            turn = new Turn(j, ((j + 1) / 2), visited.get(0), visited.get(1), players.get(0));
            players.get(0).getTurns().add(turn);
            session.persist(turn);
            visited.remove(0);
            visited.remove(1);
          }
          logger.info("Speiler_1 row: " + player_1_row);
          logger.info("Speiler_1 col: " + player_1_column);

          // Check the Rules of Turns
          if (!rules.ControllPlayerEntry(turn, fullmap) || !rules.ConrollRounds(turn)) {
            players.get(0).setStatus("loser");
            break;
          }

        } else if (j % 2 != 0) {
          if (visited.size() != 0) {
            visited2 = ki.steps(players.get(1), costs, visited);
          } else {
            turn = new Turn(j, ((j + 1) / 2), visited2.get(0), visited2.get(1), players.get(1));
            players.get(1).getTurns().add(turn);
            session.persist(turn);
            visited2.remove(0);
            visited2.remove(1);
          }
          logger.info("Speiler_2 row: " + player_2_row);
          logger.info("Speiler_2 col: " + player_2_column);

          // Check the Rules of Turns
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

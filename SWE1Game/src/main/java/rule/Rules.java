package rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import interfaces.IRules;
import model.Map;
import model.Turn;
import model.*;

public class Rules implements IRules {

  private static final Logger logger = LoggerFactory.getLogger(Rules.class);

  @Override
  public boolean ControllMapSize(Map map) {
    // TODO Auto-generated method stub
    if (map.getFields().size() > 32) {
      logger.info("Sie haben gegen die MapSize verstoßen : Ihre MapSize: " + map.getFields().size()
          + "und somit verloren");
      return false;
    }
    return true;
  }

  @Override
  public boolean ControllMapConditions(Map map) {
    // TODO Auto-generated method stub
    int lawn = 0;
    int mountan = 0;
    int water = 0;
    
    //Check the whole Map
    for (Field field : map.getFields()) {
      switch (field.getType()) {
        case "w":
          water++;
          break;
        case "b":
          mountan++;
          break;
        case "g":
          lawn++;
          break;
      }
    }
    //Check if the conditions are right
    if (water < 4 || mountan < 3 || lawn < 5) {
      System.out.println("Sie haben gegen die MapConditions verstoßen und somit verloren");
      logger.info("water: " + water);
      logger.info("Mountan: " + mountan);
      logger.info("Lawn: " + lawn);
      return false;
    }
    return true;
  }

  @Override
  public boolean ControllMapWaterCondition(Map map) {
    // TODO Auto-generated method stub
    int id = 0;
    int water = 0;
    
    //The Row of the Map
    for (int i = 0; i < 4; i++) {
      water = 0;
      //The Column of Map
      for (int j = 0; j < 8; j++) {
        if (map.getFields().get(id).getType().equals("w")) {
          water++;
        }
        id++;
      }
      logger.info(String.valueOf(water));
      //Check if there are more 
      if (water > 3) {
        System.out.println("Sie haben gegen die MapWater verstoßen und somit verloren");
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean ControllServerDataGeneration(Map map) {
    // TODO Auto-generated method stub
    
    //Check if the client placed the treasure
    if (map.getTreasure_column() != 0 && map.getTreasure_row() != 0) {
      System.out.println("Sie haben gegen die ServerDataGeneration verstoßen und somit verloren");
      return false;
    }
    return true;
  }

  @Override
  public boolean ConrollRounds(Turn turn) {
    // TODO Auto-generated method stub
    
    //Check if the client consumed his Actions
    if (turn.getCount() > 200) {
      System.out.println("Sie haben gegen die ControllRounds verstoßen und somit verloren");
      logger.info("Die Anzahl beträgt: " + turn.getCount());
      return false;
    }
    return true;
  }

  @Override
  public boolean ControllPlayerEntry(Turn turn, Map map) {
    // TODO Auto-generated method stub
    
    //Check if the player stayed in on the map frame
    if (turn.getRow() < 1 || turn.getRow() > 8 || turn.getColumn() < 1 || turn.getColumn() > 8) {
      System.out.println("Sie haben gegen die PlayerEntry verstoßen und somit verloren");
      logger.info("Zeile: " + String.valueOf(turn.getRow()));
      logger.info("Spalte" + String.valueOf(turn.getColumn()));
      return false;
    } 
    //Check if player entered any water field
    else if (map.getTypeOfField(turn.getRow(), turn.getColumn()).equals("w")) {
      System.out.println("Sie haben gegen die PlayerEntry verstoßen und somit verloren");
      logger.info("Zeile: " + String.valueOf(turn.getRow()));
      logger.info("Spalte" + String.valueOf(turn.getColumn()));
      logger.info("Das Feld ist : " + map.getTypeOfField(turn.getRow(), turn.getColumn()));

      return false;
    }
    return true;
  }

  @Override
  public boolean ControllTreasureCastlePlace(Map map) {
    // TODO Auto-generated method stub
    
    //Check if treasure and castle is an the same field
    if (map.getCastle_column() == map.getTreasure_column()
        && map.getCastle_row() == map.getTreasure_row()) {
      System.out.println("Sie haben gegen die TreasureCastlePlace verstoßen und somit verloren");
      return false;
    }
    return true;
  }

  @Override
  public boolean ControllTreasureCastleWaterPlace(Map map) {
    // TODO Auto-generated method stub
    int treasure_row = map.getTreasure_row();
    int treasure_col = map.getTreasure_column();
    int castle_row = map.getCastle_row();
    int castle_col = map.getCastle_column();

    boolean result = false;

    // Check if treasure is on a water field
    if (map.getTypeOfField(treasure_row, treasure_col).equals("w")) {
      System.out
          .println("Sie haben gegen die TreasureCastleWaterPlace verstoßen und somit verloren");
    }// Check if castle is on a water field 
    else if (map.getTypeOfField(castle_row, castle_col).equals("w")) {
      System.out
          .println("Sie haben gegen die TreasureCastleWaterPlace verstoßen und somit verloren");
      return false;
    } else {
      result = true;
    }
    if(!result) {
      logger.info("Treasure Zeile und Spalte: " + treasure_row + " ," + treasure_col);
      logger.info("Castle Zeile und Spalte: " + castle_row + " ," + castle_col);
    }
    return result;
  }
}

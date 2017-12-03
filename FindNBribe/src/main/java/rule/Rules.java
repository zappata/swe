package rule;

import interfaces.IRules;
import model.Map;
import model.Turn;
import model.*;

public class Rules implements IRules {


  @Override
  public boolean ControllMapSize(Map map) {
    // TODO Auto-generated method stub
    if (map.getFields().size() > 32) {
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
      if (water < 4 && mountan < 3 && lawn < 5) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean ControllMapWaterCondition(Map map) {
    // TODO Auto-generated method stub
    int id = 0;
    int water = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 8; j++) {
        if (map.getFields().get(id).getType().equals("w")) {
          water++;
        }
        id++;
      }
      if (water > 3) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean ControllServerDataGeneration(Map map) {
    // TODO Auto-generated method stub
    if (map.getTreasure_column() != 0 && map.getTreasure_row() != 0) {
      return false;
    }
    return true;
  }

  @Override
  public boolean ConrollRounds(Turn turn) {
    // TODO Auto-generated method stub
    if (turn.getCount() > 200) {
      return false;
    }
    return true;
  }

  @Override
  public boolean ControllPlayerEntry(Turn turn, Map map) {
    // TODO Auto-generated method stub
    if (turn.getRow() < 1 || turn.getRow() > 8 || turn.getColumn() < 1 || turn.getColumn() > 4) {
      return false;
    } else if (map.getTypeOfField(turn.getRow(), turn.getColumn()).equals("w")) {
      return false;
    }
    return true;
  }

  @Override
  public boolean ControllTreasureCastlePlace(Map map) {
    // TODO Auto-generated method stub
    if (map.getCastle_column() == map.getTreasure_column()
        && map.getCastle_row() == map.getTreasure_row()) {
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

    if (map.getTypeOfField(treasure_row, treasure_col).equals("w")) {
      return false;
    } else if (map.getTypeOfField(castle_row, castle_col).equals("w")) {
      return false;
    }
    return true;
  }
}

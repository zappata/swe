package interfaces;

import model.*;

public interface IRules {
  boolean ControllMapSize(Map map);
  boolean ControllMapConditions(Map map);
  boolean ControllMapWaterCondition(Map map);
  boolean ControllServerDataGeneration(Map map);
  boolean ConrollRounds(Turn turn);
  boolean ControllPlayerEntry(Turn turn, Map map);
  boolean ControllTreasureCastlePlace(Map map);
  boolean ControllTreasureCastleWaterPlace(Map map);
}

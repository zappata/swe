package unitTest;

import org.junit.Assert;
import org.junit.Test;
import interfaces.IRules;
import model.Map;
import rule.Rules;

public class ControllTreasureCastlePlaceTest {

  @Test
  public void TreasureCastleTest() {
    
    IRules rules = new Rules();
    Map map = new Map();
    
    map.setCastle_column(1);
    map.setCastle_row(1);
    map.setTreasure_column(1);
    map.setTreasure_row(2);
    
    Assert.assertTrue(rules.ControllTreasureCastlePlace(map));
  }
  
}

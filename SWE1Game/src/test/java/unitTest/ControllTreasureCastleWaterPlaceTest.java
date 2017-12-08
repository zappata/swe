package unitTest;

import model.Field;
import org.junit.Assert;
import org.junit.Test;
import interfaces.IRules;
import model.Map;
import rule.Rules;

public class ControllTreasureCastleWaterPlaceTest {

  @Test
  public void TreasureCastleWaterPlaceTest() {

    IRules rules = new Rules();
    Map map = new Map();
    Field field = new Field(1, "g", 1, 1, map);
    Field field_2 = new Field(2, "g", 1, 2, map);
    Field field_3 = new Field(3, "w", 1, 3, map);
    map.setField(field);
    map.setField(field_2);
    map.setField(field_3);


    map.setCastle_row(1);
    map.setCastle_column(1);

    map.setTreasure_row(1);
    map.setTreasure_column(2);

    Assert.assertTrue(rules.ControllTreasureCastleWaterPlace(map));
  }

}

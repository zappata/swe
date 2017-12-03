package unitTest;

import org.junit.Assert;
import org.junit.Test;
import interfaces.IRules;
import model.Field;
import model.Map;
import rule.Rules;

public class ControllMapWaterCoditionTest {

  @Test
  public void mapWaterCondition() {
    Map map = new Map();
    Field field;
    IRules rules = new Rules();
    String type = "w";

    for (int i = 1; i < 5; i++) {
      for (int j = 1; j < 9; j++) {
        if (j == 4) {
          type = "b";
        }
        field = new Field(i, type, i, j, map);
        map.setField(field);
      }
    }

    Assert.assertTrue(rules.ControllMapWaterCondition(map));

  }

}

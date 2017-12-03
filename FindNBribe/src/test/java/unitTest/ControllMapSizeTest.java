package unitTest;

import org.junit.Assert;
import org.junit.Test;
import model.*;
import rule.Rules;
import interfaces.IRules;

public class ControllMapSizeTest {

  @Test
  public void mapSizeTest() {
    Map map = new Map();
    Field field;
    IRules rules = new Rules();

    for (int i = 1; i < 5; i++) {
      for (int j = 1; j < 9; j++) {
        field = new Field(i, "w", i, j, map);
        map.setField(field);
      }
    }
    
    Assert.assertTrue(rules.ControllMapSize(map));
    
  }

}

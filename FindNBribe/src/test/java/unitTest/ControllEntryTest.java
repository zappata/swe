package unitTest;

import org.junit.Assert;
import org.junit.Test;
import interfaces.IRules;
import model.Field;
import model.Map;
import model.Turn;
import rule.Rules;

public class ControllEntryTest {

  @Test
  public void RoundsTest() {
    
    Map map = new Map();
    Field field = new Field(1, "b", 1, 1, map);
    map.setField(field);
    Turn turn = new Turn(1, 1, 1, 1, 1, null);
    IRules rules = new Rules();

    
    Assert.assertTrue(rules.ControllPlayerEntry(turn, map));
    
  }
  
}

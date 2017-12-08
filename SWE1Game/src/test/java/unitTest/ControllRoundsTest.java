package unitTest;

import org.junit.Assert;
import org.junit.Test;
import interfaces.IRules;
import model.Player;
import model.Turn;
import rule.Rules;

public class ControllRoundsTest {

  @Test
  public void RoundsTest() {
    Turn turn = new Turn();
    IRules rules = new Rules();

    for (int i = 0; i < 201; i++) {
      turn.setCount(i);
    }
    
    Assert.assertTrue(rules.ConrollRounds(turn));
    
  }
  
}

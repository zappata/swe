package unitTest;

import org.junit.Assert;
import org.junit.Test;
import model.Turn;

public class TurnTest {

  @Test
  public void createTurn() {
    
    Turn turn = new Turn(1, 24, 1, 1, null);
    
    Assert.assertEquals(24, turn.getCount());
  }
  
}

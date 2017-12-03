package unitTest;

import org.junit.Assert;
import org.junit.Test;
import model.Player;

public class PlayerTest {

  @Test
  public void createPlayer() {
    
    Player player = new Player(1, "hamed", 1, "nichts");
    
    Assert.assertTrue(player.getStatus().equals("nichts"));
  }
}

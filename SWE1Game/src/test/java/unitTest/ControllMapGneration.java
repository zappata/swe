package unitTest;

import org.junit.Assert;
import org.junit.Test;
import controller.MapGeneration;
import controller.PlayerGeneration;
import model.Map;
import model.Player;

public class ControllMapGneration {
  @Test
  public void mapGenerationTest() {
    PlayerGeneration playerGeneration = new PlayerGeneration();
    MapGeneration mapGeneration = new MapGeneration();

    Player player = playerGeneration.generatePlayer("hamed");
    Map map = mapGeneration.generateMap(player);

    for (int i = 0; i < map.getFields().size(); i++) {
      System.out.print(map.getFields().get(i).getType() + " ");
      if (i % 8 == 7) {
        System.out.println("");
      }
    }

    Assert.assertEquals(map.getPlayer(), player);

  }
}

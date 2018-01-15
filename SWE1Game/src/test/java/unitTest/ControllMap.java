package unitTest;

import org.junit.Assert;
import org.junit.Test;
import controller.MapGeneration;
import controller.PlayerGeneration;
import interfaces.IRules;
import model.Map;
import model.Player;
import rule.Rules;

public class ControllMap {
  @Test
  public void mapGenerationTest() {
    PlayerGeneration playerGeneration = new PlayerGeneration();
    MapGeneration mapGeneration = new MapGeneration(); 
    IRules rules = new Rules();
    
    Player player = playerGeneration.generatePlayer("hamed", 1, 1);
    Map map = mapGeneration.generateMap(player, 1);

    for (int i = 0; i < map.getFields().size(); i++) {
      System.out.print(map.getFields().get(i).getType() + " ");
      if (i % 8 == 7) {
        System.out.println("");
      }
    }

    Assert.assertEquals(map.getPlayer(), player);
    Assert.assertTrue(rules.ControllMapSize(map));
    Assert.assertTrue(rules.ControllMapWaterCondition(map));


  }
}

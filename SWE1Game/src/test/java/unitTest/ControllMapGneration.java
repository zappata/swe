package unitTest;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import controller.KI;
import controller.MapGeneration;
import controller.PlayerGeneration;
import model.Map;
import model.Player;

public class ControllMapGneration {
  @Test
  public void mapGenerationTest() {
    PlayerGeneration playerGeneration = new PlayerGeneration();
    MapGeneration mapGeneration = new MapGeneration();
    KI ki = new KI();
    List<Map> maps = new ArrayList<>();
    List<Integer> steps = new ArrayList<Integer>();
    int[][] costs;
    int typ = 0;

    Player player = playerGeneration.generatePlayer("hamed", 1, 1);
    Player player2 = playerGeneration.generatePlayer("hamed", 1, 1);
    Map map = mapGeneration.generateMap(player, 0);
    maps.add(map);
    Map map2 = mapGeneration.generateMap(player2, 1);
    maps.add(map2);

    Assert.assertEquals(map.getPlayer(), player);

    Map fullMap = mapGeneration.mergeMaps(maps);

    for (int i = 0; i < fullMap.getFields().size(); i++) {
      System.out.print(fullMap.getFields().get(i).getType() + " ");
      if (i % 8 == 7) {
        System.out.println("");
      }
    }

    Assert.assertEquals(fullMap.getTypeOfField(1, 1), map.getTypeOfField(1, 1));
    
    costs = ki.mapCosts(fullMap);


    if (fullMap.getTypeOfField(1, 1).equals("w")) {
      typ = 99;
    } else if (fullMap.getTypeOfField(1, 1).equals("b")) {
      typ = 2;
    } else {
      typ = 1;
    }
    
    Assert.assertEquals(costs[0][0], typ);
  }
}

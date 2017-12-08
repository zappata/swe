package unitTest;

import org.junit.Assert;
import org.junit.Test;
import model.Map;

public class MapTest {
  
  @Test
  public void createMap() {
    
    Map map = new Map(1, null);
    
    Assert.assertEquals(0, map.getCastle_row());
    
  }
}

package unitTest;

import model.*;
import org.junit.Test;
import org.junit.Assert;


public class FieldTest {

  @Test
  public void createField() {

    Field field = new Field(1, "w", 1, 1, null);

    Assert.assertTrue(field.getType().equals("w"));
  }
}

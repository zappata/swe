package unitTest;

import model.*;
import org.junit.Test;
import org.junit.Assert;

public class FieldTest {

  public void createField() {
    String type = "w";

    Field field = new Field(1, "w", 1, 1, null);

    Assert.assertTrue(field.getType().equals(type));
  }
}

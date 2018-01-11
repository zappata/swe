package controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MapFields {
  w, b, g;

  private static final List<MapFields> VALUES =
      Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  public static MapFields randomLetter() {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}

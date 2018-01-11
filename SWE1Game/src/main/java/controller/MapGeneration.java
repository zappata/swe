package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import database.Main;
import model.*;

public class MapGeneration {

  private int id;
  private String type;
  private int waterFields;
  private int water;
  private int mount;
  private int gras;
  private MapFields mapFields;
  private Field field;
  private int[][] waterFieldsnumber;
  boolean fieldReachable;
  int lastrow;

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public Map generateMap(Player player) {
    Map map;

    do {

      map = new Map(0, player);
      id = 0;
      mount = 0;
      gras = 0;
      waterFields = 0;
      waterFieldsnumber = new int[4][3];
      lastrow = 0;

      for (int j = 1; j < 5; j++) {

        water = 0;
        lastrow = j - 1;

        for (int k = 1; k < 9; k++) {

          //Generate a field type randomly
          mapFields = MapFields.randomLetter();
          type = mapFields.toString();

          //Check if there are already 3 water fields in a row
          if (water >= 3 && type.equals("w")) {
            fieldReachable = false;
          } else {
            fieldReachable = true;
          }

          if (type.equals("w")) {
            
            //Check if all fields are reachable
            if (j > 1 && fieldReachable) {
              for (int i = 0; i < 3; i++) {
                if ((waterFieldsnumber[lastrow - 1][i] - 1) == k
                    || (waterFieldsnumber[lastrow - 1][i] + 1) == k
                    || (waterFieldsnumber[lastrow - 1][i]) == k) {
                  fieldReachable = false;
                  }
                }
            }

            if (fieldReachable) {
              //save the last row to check if all fields are reachable
              waterFieldsnumber[lastrow][water] = k;
              water++;
              waterFields++;
              }


          } else if (type.equals("b")) {
            mount++;
          } else if (type.equals("g")) {
            gras++;
          }

          //check if water conditions are suffused
          while (!fieldReachable) {
            mapFields = MapFields.randomLetter();
            type = mapFields.toString();
            if (!type.equals("w")) {
              fieldReachable = true;
            }
          }


          logger.debug(type + " ");
          //Add a field into the map
          field = new Field(id, type, j, k, map);
          map.getFields().add(field);
          id++;
        }

        logger.debug("-------");

      }

      logger.info("w: " + waterFields);
      logger.info("b: " + mount);
      logger.info("g: " + gras);

    } while (mount < 3 || waterFields < 4 || gras < 5);

    return map;
  }

}

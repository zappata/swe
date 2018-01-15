package controller;

import java.util.ArrayList;
import java.util.List;
import model.Map;
import model.Player;

public class KI {

  public int[][] mapCosts(Map fullMap) {

    String[][] map = new String[8][8];
    int[][] costs = new int[8][8];

    int row = 0;
    int column = 0;

    for (int i = 0; i < fullMap.getFields().size(); i++) {

      map[row][column] = fullMap.getFields().get(i).getType();

      if (map[row][column].equals("b")) {
        costs[row][column] = 2;
      } else if (map[row][column].equals("g")) {
        costs[row][column] = 1;
      } else if (map[row][column].equals("w")) {
        costs[row][column] = 99;
      }

      column++;

      if (column == 8) {
        row = row + 1;
        column = 0;
      }

    }

    return costs;
  }

  public List<Integer> steps(Player player, int[][] costs, List<Integer> visited) {

    List<Integer> steps = new ArrayList<Integer>();
    List<Integer> grasFields = new ArrayList<Integer>();
    List<Integer> path = new ArrayList<Integer>();
    int player_row = 1;// player.getRow();
    int player_col = 1;// player.getColumn();
    int smallest_row = 99;
    int smallest_col = 99;
    int min = 0;
    int next_row = 0;
    int next_col = 0;

    // Check the grasFields
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (i != player_row && j != smallest_col) {
          if (costs[i][j] == 1) {
            grasFields.add(i + 1);
            grasFields.add(j + 1);
          }
        }
      }
    }

    for (int i = 0; i < grasFields.size(); i++) {
      System.out.print(grasFields.get(i) + "   ");
    }

    // Check for visited paths
    for (int i = 0; i < visited.size(); i++) {
      for (int j = 0; j < grasFields.size(); j++) {
        if (visited.get(i) == grasFields.get(i) && visited.get(i + 1) == grasFields.get(i + 1)) {
          grasFields.remove(i);
          grasFields.remove(i + 1);
        }
      }
    }

    System.out.println("");

    // Check for costs
    for (int i = 0; i < grasFields.size() - 2; i = i + 2) {
      // if (player_row != grasFields.get(i) && player_col != grasFields.get(i + 1)) {
      path.add(player_row - grasFields.get(i));
      path.add(player_col - grasFields.get(i + 1));
      // }
    }

    for (int i = 0; i < path.size(); i++) {
      System.out.print(path.get(i) + "   ");
    }

    smallest_row = Math.abs(path.get(0));;
    smallest_col = Math.abs(path.get(1));
    min = smallest_row + smallest_col;

    // Shortest path
    for (int i = 2; i < grasFields.size() - 2; i = i + 2) {
      next_row = Math.abs(path.get(i));
      next_col = Math.abs(path.get(i + 1));

      if ((min) > (next_col + next_row)) {
        smallest_row = next_row;
        smallest_col = next_col;
        min = next_col + next_row;
      }
    }

    System.out.println("");
    System.out.println("row: " + smallest_row);
    System.out.println("col: " + smallest_col);

    steps.add(smallest_row);
    steps.add(smallest_col);

    return steps;
  }

}

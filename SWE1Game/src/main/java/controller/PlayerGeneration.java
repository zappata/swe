package controller;

import model.Player;

public class PlayerGeneration {

  public Player generatePlayer(String name) {
    Player player = new Player(0, name, 0, "");
    return player;
  }
  
}

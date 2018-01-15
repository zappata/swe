package controller;

import model.Player;

public class PlayerGeneration {

  public Player generatePlayer(String name, int id, int number) {
    Player player = new Player(id, name, number);
    return player;
  }
  
}

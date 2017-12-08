package jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import model.Player;
import model.Turn;

public class TurnJAXB {

  public void marshell(List<Turn> turns) {
    try {

      Player player = new Player();
      player.setTurns(turns);
      for (int i = 0; i < turns.size(); i++) {
        //Set the Player_name of Turns
        player.getTurns().get(i).setPlayer_name(player.getTurns().get(i).getPlayer().getName());
        player.getTurns().get(i).setPlayer(null);
      }

      // Create a new new Instance of class Player(Liste von Turns) for JAXB
      JAXBContext j_context = JAXBContext.newInstance(Player.class);
      // Create a marshaller
      Marshaller marsheller = j_context.createMarshaller();
      // declare the format of the object output
      marsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      // Console Output
      marsheller.marshal(player, System.out);
      // File Output
      marsheller.marshal(player, new File("turn.xml"));

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

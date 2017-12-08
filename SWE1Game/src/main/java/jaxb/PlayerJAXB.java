package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import model.Player;

public class PlayerJAXB {

  public void marshell(Player player) {
    try {
      // Create a new new Instance of class player for JAXB
      JAXBContext j_context = JAXBContext.newInstance(Player.class);
      // Create a marshaller
      Marshaller marsheller = j_context.createMarshaller();
      // declare the format of the object output
      marsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      // Console Output
      marsheller.marshal(player, System.out);
      // File outpput
      marsheller.marshal(player, new File("player.xml"));
      
    }catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

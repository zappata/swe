package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import model.Player;

public class PlayerJAXB {

  public void marshell(Player player) {
    try {
      JAXBContext j_context = JAXBContext.newInstance(Player.class);
      Marshaller marsheller = j_context.createMarshaller();
      marsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marsheller.marshal(player, System.out);
      marsheller.marshal(player, new File("player.xml"));
      
    }catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

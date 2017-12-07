package jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import model.Player;
import model.Turn;
import model.TurnList;

public class TurnJAXB {

  public void marshell(List<Turn> turns) {
    try {

      TurnList list = new TurnList();
      list.setTurns(turns);
      for (int i = 0; i < turns.size(); i++) {
        list.getTurns().get(i).setPlayer_name(list.getTurns().get(i).getPlayer().getName());
        list.getTurns().get(i).setPlayer(null);
      }

      JAXBContext j_context = JAXBContext.newInstance(TurnList.class);
      Marshaller marsheller = j_context.createMarshaller();
      marsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marsheller.marshal(list, System.out);
      marsheller.marshal(list, new File("turn.xml"));

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

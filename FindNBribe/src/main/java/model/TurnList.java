package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="turnList")
public class TurnList {

  private List<Turn> turns = new ArrayList<Turn>();

  @XmlElement
  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turs) {
    this.turns = turs;
  }
 
}

package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@NamedQueries({@NamedQuery(name = "get_winners", query = "from Player where status = :status"),
    @NamedQuery(name = "get_player", query = "from Player where name = :name")})


@Entity
@Table(name = "Player")
@XmlRootElement(name = "player")
public class Player {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "numbers")
  private int number;

  @Column(name = "status")
  private String status;

  @OneToMany(mappedBy = "player")
  private List<Turn> turns = new ArrayList<Turn>();

  public Player() {}

  public Player(int id, String name, int number, String status) {
    setId(id);
    setName(name);
    setNumber(number);
    setStatus(status);
  }

  @XmlElement
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @XmlElement
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @XmlElement(name = "turns")
  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turns) {
    this.turns = turns;
  }


}

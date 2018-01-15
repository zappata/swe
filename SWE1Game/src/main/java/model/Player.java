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


@NamedQueries({
    @NamedQuery(name = "get_winners", query = "from Player where status = :status"),
    @NamedQuery(name = "get_player", query = "from Player where name = :name"),
    @NamedQuery(name = "get_players", query = "from Player")
  }
)


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
  
  @Column(name = "row")
  private int row;
  
  @Column(name = "column")
  private int column;
  
  @Column(name = "castle")
  private boolean castle;
  
  @Column(name = "treasure")
  private boolean treasure;

  @OneToMany(mappedBy = "player")
  private List<Turn> turns = new ArrayList<Turn>();

  public Player() {}

  public Player(int id, String name, int number) {
    setId(id);
    setName(name);
    setNumber(number);
    setStatus("");
    setRow(0);
    setColumn(0);
    setCastle(false);
    setTreasure(false);
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

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public boolean isCastle() {
    return castle;
  }

  public void setCastle(boolean castle) {
    this.castle = castle;
  }

  public boolean isTreasure() {
    return treasure;
  }

  public void setTreasure(boolean treasure) {
    this.treasure = treasure;
  }

  @XmlElement(name = "turns")
  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turns) {
    this.turns = turns;
  }


}

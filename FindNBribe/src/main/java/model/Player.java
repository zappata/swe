package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Player")
public class Player {
  
  @Id
  @Column(name="id")
  private int id;
  
  @Column(name="name")
  private String name;
  
  @Column(name="numbers")
  private int number;
  
  @Column(name="status")
  private String status;
  
  @OneToMany(mappedBy ="player")
  private List<Turn> turns = new ArrayList<Turn>();
  
  public Player(){}
  
  public Player(int id, String name, int number, String status) {
    setId(id);
    setName(name);
    setNumber(number);
    setStatus(status);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turns) {
    this.turns = turns;
  }
 

}

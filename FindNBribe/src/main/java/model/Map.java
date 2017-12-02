package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Map")
public class Map {
  
  @Id
  @Column(name="id")
  private int id;
  
  @OneToOne
  private Player player;
  
  @OneToMany(mappedBy="map")
  private List<Field> fields = new ArrayList<Field>();
  
  public List<Field> getFields() {
    return fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  public Map() {}
  
  public Map(int id, Player player) {
    setId(id);
    setPlayer(player);
    fields = new ArrayList <>();
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  

}

package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
  @NamedQuery(
        name = "get_maps",
        query="from Map"
      )
})

@Entity
@Table(name = "Map")
@XmlRootElement(name="map")
public class Map {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "treasure_row")
  private int treasure_row;

  @Column(name = "treasure_column")
  private int treasure_column;

  @Column(name = "castle_row")
  private int castle_row;

  @Column(name = "castle_column")
  private int castle_column;

  @OneToOne
  private Player player;

  @OneToMany(mappedBy = "map")
  private List<Field> fields = new ArrayList<Field>();

  public Map() {}

  public Map(int id, Player player) {
    setId(id);
    setPlayer(player);
    setCastle_row(0);
    setCastle_column(0);
    setTreasure_column(0);
    setTreasure_row(0);
  }

  @XmlElement
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  @XmlElement
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public int getTreasure_row() {
    return treasure_row;
  }

  public void setTreasure_row(int treasure_row) {
    this.treasure_row = treasure_row;
  }

  @XmlElement
  public int getTreasure_column() {
    return treasure_column;
  }

  public void setTreasure_column(int treasure_column) {
    this.treasure_column = treasure_column;
  }

  @XmlElement
  public int getCastle_row() {
    return castle_row;
  }

  public void setCastle_row(int castle_row) {
    this.castle_row = castle_row;
  }

  @XmlElement
  public int getCastle_column() {
    return castle_column;
  }

  public void setCastle_column(int castle_column) {
    this.castle_column = castle_column;
  }
  
  @XmlElement
  public List<Field> getFields() {
    return fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }
  
  public void setField(Field field) {
    this.fields.add(field);
  }
  

  public String getTypeOfField(int row, int column) {
    for (int i = 0; i < fields.size(); i++) {
      if (fields.get(i).getRow() == row && fields.get(i).getColumn() == column) {
        return fields.get(i).getType();
      }
    }
    return null;
  }

}

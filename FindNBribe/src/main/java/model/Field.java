package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Field")
public class Field {
  
  @Id
  @Column(name="id")
  private int id;

  @Column(name="type")
  private String type;
  
  @Column(name="row")
  private int row;
  
  @Column(name="column")
  private int column;
  
  @ManyToOne
  private Map map;
  
  public Field() {}
  
  public Field(int id, String type, int row, int column, Map map) {
    setId(id);
    setType(type);
    setRow(row);
    setColumn(column);
    setMap(map);
  } 
  
  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
  
}

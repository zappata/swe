package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
  @NamedQuery(
        name = "get_field",
        query="from Field where type = :type"
      )
})

@Entity
@Table(name="Field")
@XmlRootElement
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
  
  @XmlElement
  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  @XmlElement
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  @XmlElement
  public int getRow() {
    return row;
  }
  
  public void setRow(int row) {
    this.row = row;
  }
  
  @XmlElement
  public int getColumn() {
    return column;
  }
  
  public void setColumn(int column) {
    this.column = column;
  }
  
}

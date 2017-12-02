package model;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
@NamedQueries({
  @NamedQuery(
      name = "get_data",
      query = "from Map"
      )
})
*/


@Entity
@Table(name="map")
public class Map {
  
  @Id
  @Column(name="id")
  private int id;
  
  
  @OneToMany(mappedBy="map_id", cascade= CascadeType.ALL)
  private ArrayList<Field> map = new ArrayList <Field>();
  
  public Map() {}
  
  public Map(int id, ArrayList<Field> maps) {
    setId(id);
    setMap(maps);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ArrayList<Field> getMap() {
    return map;
  }

  public void setMap(ArrayList<Field> map) {
    this.map = map;
  }
  
  
}

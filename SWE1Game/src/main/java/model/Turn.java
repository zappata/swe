package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
    @NamedQuery(name = "get_game_rounds", query = "from Turn where id > :from AND id < :til")})

@Entity
@Table(name = "Turn")
@XmlRootElement(name = "turn")
public class Turn {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "current_count")
  private int count;

  @Column(name = "current_row")
  private int row;

  @Column(name = "current_column")
  private int column;

  @Column(name = "direction")
  private int direction;

  @ManyToOne
  private Player player;
  
  @Transient
  private String Player_name;

  public Turn() {}
  
  public Turn(int id, int count, int row, int column, int direction, Player player) {
    setId(id);
    setCount(count);
    setRow(row);
    setColumn(column);
    setDirection(direction);
    setPlayer(player);
  }

  
  @XmlElement
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
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

  @XmlElement
  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  @XmlElement
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  @XmlElement
  public String getPlayer_name() {
    return Player_name;
  }

  public void setPlayer_name(String player_name) {
    Player_name = player_name;
  }

}

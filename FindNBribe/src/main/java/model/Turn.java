package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Turn")
public class Turn {

  @Id
  @Column(name="id")
  private int id;
  
  @Column(name="current_count")
  private int count;
  
  @Column(name="current_row")
  private int row;
  
  @Column(name="current_column")
  private int column;
  
  @Column(name="direction")
  private int direction;
  
  @ManyToOne
  private Player player;

  public Turn() {}
  
  public Turn(int id, int player_id, int count,
      int row, int column, int direction, Player player) {
    setId(id);
    setCount(count);
    setRow(row);
    setColumn(column);
    setDirection(direction);
    setPlayer(player);
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
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

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }
  
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
  
}

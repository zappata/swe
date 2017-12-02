package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spielzug")
public class Turn {

  @Id
  @Column(name="id")
  private int id;
  
  @Id
  @Column(name="spieler_id")
  private int player_id;
  
  @Id
  @Column(name="spieler_name")
  private String player_name;

  @Column(name="aktuelle_Anzahl")
  private int count;
  
  @Column(name="aktuelle_Zeile")
  private int row;
  
  @Column(name="aktuelle_Spalte")
  private int column;
  
  @Column(name="richtung")
  private int direction;
  
  public Turn() {}
  
  public Turn(int id, int player_id, String player_name, int count,
      int row, int column, int direction) {
    setId(id);
    setPlayer_id(player_id);
    setPlayer_name(player_name);
    setCount(count);
    setRow(row);
    setColumn(column);
    setDirection(direction);
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPlayer_id() {
    return player_id;
  }

  public void setPlayer_id(int player_id) {
    this.player_id = player_id;
  }

  public String getPlayer_name() {
    return player_name;
  }

  public void setPlayer_name(String player_name) {
    this.player_name = player_name;
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
  
}

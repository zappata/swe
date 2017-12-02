package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="field")
public class Field {
  @Id
  @Column(name="map_number")
  private int map_number;
  
  @Column(name="field_type")
  private String type;
  
  @Column(name="field_row_number")
  private int row;
  
  @Column(name="field_column_number")
  private int column;
  
  public Field() {}
  
  public Field(int number, String type, int row, int column) {
    setNumber(number);
    setType(type);
    setRow(row);
    setColumn(column);
  } 
  
  public int getNumber() {
    return map_number;
  }
  public void setNumber(int number) {
    this.map_number = number;
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

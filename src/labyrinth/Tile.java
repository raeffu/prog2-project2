package labyrinth;

import java.awt.Color;

public class Tile {

  private int _x;
  private int _y;

  private ETileType _type;
  private Color _color;
  
  public enum ETileType {
    WALL, EMPTY
  }


  public Tile(int x, int y, char c) {
    _x = x;
    _y = y;

    switch (c) {
    case '#':
      _type = ETileType.WALL;
      _color = Color.BLACK;
      break;
    case '.':
      _type = ETileType.EMPTY;
      _color = Color.WHITE;
      break;
    default:
      break;
    }
  }

  public int get_x() {
    return _x;
  }

  public int get_y() {
    return _y;
  }

  public ETileType getType() {
    return _type;
  }
  
  public void switchType() {
    _type = (_type == ETileType.EMPTY) ? ETileType.WALL : ETileType.EMPTY;
  }

  public Color getColor() {
    return _color;
  }
}

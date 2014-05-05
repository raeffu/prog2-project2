package labyrinth;

public class Tile {

  private int _x;
  private int _y;
  
  private boolean _isWall;
  
  public Tile(int x, int y, boolean isWall){
    _x = x;
    _y = y;
    _isWall = isWall;
  }

  public int get_x() {
    return _x;
  }

  public int get_y() {
    return _y;
  }

  public boolean isWall() {
    return _isWall;
  }
  
  
}

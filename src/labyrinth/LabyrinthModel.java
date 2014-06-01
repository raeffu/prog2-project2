package labyrinth;

import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import labyrinth.Tile.ETileType;

public class LabyrinthModel {

  private ArrayList<Tile> _tiles = new ArrayList<Tile>();
  private ArrayList<Tile> _visited = new ArrayList<Tile>();
  private ArrayList<Ellipse2D.Double> _circles = new ArrayList<Ellipse2D.Double>();

  public LabyrinthModel() {
    parseLabyrinth("res/labyrinth.txt");
  }

  private void parseLabyrinth(String file) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      int row = 0;
      String line;

      while ((line = br.readLine()) != null) {
        for (int col = 0; col < line.length(); col++) {
          char c = line.charAt(col);

          Tile tile = new Tile(col, row, c);
          _tiles.add(tile);
        }
        row += 1;
      }
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public ArrayList<Ellipse2D.Double> getCircles() {
    return _circles;
  }

  public ArrayList<Tile> getTiles() {
    return _tiles;
  }

  public Tile getTile(int x, int y) {
    for (Tile tile : _tiles) {
      if (tile.get_x() == x && tile.get_y() == y)
        return tile;
    }
    return null;
  }

  public boolean isAtExit(Tile tile) {

    Tile top = getTile(tile.get_x(), tile.get_y() + 1);
    Tile right = getTile(tile.get_x() + 1, tile.get_y());
    Tile bottom = getTile(tile.get_x(), tile.get_y() - 1);
    Tile left = getTile(tile.get_x() - 1, tile.get_y());

    if (top == null || right == null || bottom == null || left == null) {
      return true;
    }

    return false;
  }

  public boolean findWayOut(Tile tile) {
    if (isAtExit(tile)) {
      return true;
    }
    
    ArrayList<Tile> neighbors = getNeighbors(tile);
    
    for (Tile neighbor : neighbors) {
      if(_visited.contains(neighbor)){
        continue;
      }
      if(neighbor.getType() != ETileType.EMPTY){
        continue;
      }
      
      _visited.add(neighbor);
      
      if(isAtExit(neighbor)){
        return true;
      }
      
      if(!findWayOut(neighbor)){
        _visited.remove(neighbor);
      }
      else{
        return true;
      }
    }
    
    return false;
  }

  private ArrayList<Tile> getNeighbors(Tile tile) {
    Tile top = getTile(tile.get_x(), tile.get_y() - 1);
    Tile right = getTile(tile.get_x() + 1, tile.get_y());
    Tile bottom = getTile(tile.get_x(), tile.get_y() + 1);
    Tile left = getTile(tile.get_x() - 1, tile.get_y());
    
    ArrayList<Tile> neighbors = new ArrayList<Tile>();
    neighbors.add(top);
    neighbors.add(right);
    neighbors.add(bottom);
    neighbors.add(left);
    
    return neighbors;
  }

  public ArrayList<Tile> get_visited() {
    return _visited;
  }
  
  
}

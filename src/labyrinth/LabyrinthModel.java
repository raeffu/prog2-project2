package labyrinth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LabyrinthModel {
  
  private ArrayList<Tile> _tiles = new ArrayList<Tile>();
  
  public LabyrinthModel(){
    parseLabyrinth("res/labyrinth.txt");
  }
  
  private void parseLabyrinth(String file){
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      int row = 0;
      String line;
      
      while((line = br.readLine()) != null){
        for(int col=0; col < line.length(); col++){
          char c = line.charAt(col);
          
          Tile tile = new Tile(col, row, c);
          _tiles.add(tile);
        }
        row += 1;
      }
      br.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public ArrayList<Tile> getTiles(){
    return _tiles;
  }
  
  public Tile getTile(int x, int y){
    for(Tile tile : _tiles){
      if(tile.get_x() == x && tile.get_y() == y)
        return tile;
    }
    return null;
  }
}

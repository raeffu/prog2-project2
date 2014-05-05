package labyrinth;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class LabyrinthPanel extends JPanel {
  
  private int _width;
  private int _height;
  private int _tileSize;
  
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    
    calculateSizes();
    
    Tile tile = new Tile(5, 5, true);
    
    paintBorder(g2, tile.get_x(), tile.get_y());
  }
  
  private void calculateSizes(){
    _width = getSize().width;
    _height = getSize().height;
    
    if(_width > _height)
      _tileSize = _width / 60;
    else
      _tileSize = _height / 60;
    
  }
  
  private void paintBorder(Graphics2D g, int x, int y){
    g.setColor(Color.GRAY);
    g.setStroke(new BasicStroke(1));
    g.drawRect(x, y, _tileSize, _tileSize);
  }
}

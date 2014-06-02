package labyrinth;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import labyrinth.Tile.ETileType;

public class LabyrinthPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private int _width;
  private int _height;
  private int _tileSize;

  private LabyrinthModel _labyrinthModel;

  public LabyrinthPanel(LabyrinthModel labyrinthModel) {
    _labyrinthModel = labyrinthModel;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;

    calculateSizes();

    for (Tile tile : _labyrinthModel.getTiles()) {
      int x = tile.get_x() * _tileSize;
      int y = tile.get_y() * _tileSize;

      paintGrid(g2, x, y);

      if (tile.getType() != ETileType.EMPTY) {
        paintWall(g2, tile, x, y);
      }
    }

    for (Ellipse2D.Double circle : _labyrinthModel.getCircles()) {
      g2.setColor(Color.RED);
      g2.fill(circle);
    }
  }

  private void calculateSizes() {
    _width = getSize().width;
    _height = getSize().height;

    if (_width > _height)
      _tileSize = _width / 20;
    else
      _tileSize = _height / 20;

  }

  private void paintGrid(Graphics2D g, int x, int y) {
    g.setColor(Color.GRAY);
    g.setStroke(new BasicStroke(1));
    g.drawRect(x, y, _tileSize, _tileSize);
  }

  private void paintWall(Graphics2D g, Tile tile, int x, int y) {
    
    g.setStroke(new BasicStroke(3));
    
    ArrayList<Tile> neighbors = _labyrinthModel.getNeighbors(tile);
    Tile top = neighbors.get(0);
    Tile right = neighbors.get(1);
    Tile bottom = neighbors.get(2);
    Tile left = neighbors.get(3);

    if ((top != null) && top.getType() == ETileType.WALL) {

      int ax = tile.get_x() * _tileSize + (_tileSize / 2);
      int ay = tile.get_y() * _tileSize;
      int bx = ax;
      int by = ay + (_tileSize / 2);

      g.setColor(Color.BLACK);
      g.draw(new Line2D.Float(ax, ay, bx, by));
    }
    
    if ((right != null) && right.getType() == ETileType.WALL) {

      int ax = tile.get_x() * _tileSize + (_tileSize / 2);
      int ay = tile.get_y() * _tileSize + (_tileSize / 2);
      int bx = ax + _tileSize /2;
      int by = ay;

      g.setColor(Color.BLACK);
      g.draw(new Line2D.Float(ax, ay, bx, by));
    }
    
    if ((bottom != null) && bottom.getType() == ETileType.WALL) {

      int ax = tile.get_x() * _tileSize + (_tileSize / 2);
      int ay = tile.get_y() * _tileSize + (_tileSize / 2);
      int bx = ax;
      int by = ay + _tileSize / 2;

      g.setColor(Color.BLACK);
      g.draw(new Line2D.Float(ax, ay, bx, by));
    }
    
    if ((left != null) && left.getType() == ETileType.WALL) {

      int ax = tile.get_x() * _tileSize + (_tileSize / 2);
      int ay = tile.get_y() * _tileSize + (_tileSize / 2);
      int bx = ax - _tileSize / 2;
      int by = ay;

      g.setColor(Color.BLACK);
      g.draw(new Line2D.Float(ax, ay, bx, by));
    }
    
    if ((top != null) && top.getType() != ETileType.WALL
        && (right != null) && right.getType() != ETileType.WALL
        && (bottom != null) && bottom.getType() != ETileType.WALL
        && (left != null) && left.getType() != ETileType.WALL) {
      
      int dx = tile.get_x() * _tileSize;
      int dy = tile.get_y() * _tileSize;

      int diameter = 10;
      Ellipse2D.Double middle = new Ellipse2D.Double(dx
          + (0.5 * (_tileSize - diameter)), dy
          + (0.5 * (_tileSize - diameter)), diameter, diameter);
      
      g.setColor(Color.BLACK);
      g.fill(middle);
    }
  }
  
  public void selectTile(Point point) {
    ArrayList<Ellipse2D.Double> circles = _labyrinthModel.getCircles();
    ArrayList<Tile> visited = _labyrinthModel.get_visited();
    circles.clear();
    visited.clear();

    for (Tile tile : _labyrinthModel.getTiles()) {
      if (tile.getType() == ETileType.EMPTY) {

        int x = tile.get_x() * _tileSize;
        int y = tile.get_y() * _tileSize;

        Rectangle frame = new Rectangle(x, y, _tileSize, _tileSize);

        int diameter = 20;
        Ellipse2D.Double circle = new Ellipse2D.Double(x
            + (0.5 * (_tileSize - diameter)), y
            + (0.5 * (_tileSize - diameter)), diameter, diameter);

        if (frame.contains(point)) {
          circles.add(circle);
          visited.add(tile);

          if (_labyrinthModel.findWayOut(tile)) {
            for (Tile t : _labyrinthModel.get_visited()) {
              int a = t.get_x() * _tileSize;
              int b = t.get_y() * _tileSize;

              Ellipse2D.Double path = new Ellipse2D.Double(a
                  + (0.5 * (_tileSize - diameter)), b
                  + (0.5 * (_tileSize - diameter)), diameter, diameter);

              circles.add(path);
            }

          }
        }
      }
    }
    repaint();
  }
}

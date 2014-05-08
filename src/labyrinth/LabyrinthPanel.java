package labyrinth;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

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
        paintTile(g2, tile, x, y);
      }
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

  // private void paintWall(Graphics2D g, int x, int y) {
  // g.setColor(Color.BLACK);
  // g.fillRect(x, y, _tileSize, _tileSize);
  // }

  private void paintTile(Graphics2D g, Tile tile, int x, int y) {
    g.setColor(tile.getColor());
    g.fillRect(x, y, _tileSize, _tileSize);
  }

  // private void paintPath(Graphics2D g, int x, int y) {
  //
  // }

  public void highlightTile(Point point) {
    for (Tile tile : _labyrinthModel.getTiles()) {

      if (tile.getType() == ETileType.EMPTY) {

        int x = tile.get_x() * _tileSize;
        int y = tile.get_y() * _tileSize;

        Rectangle frame = new Rectangle(x, y, _tileSize, _tileSize);

        if (frame.contains(point)) {
          tile.setType(ETileType.HIGHLIGHT);
        }
      }
    }
    repaint();
  }
}

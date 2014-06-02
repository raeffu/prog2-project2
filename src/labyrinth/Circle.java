package labyrinth;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Circle {

  private Ellipse2D.Double _circle;
  private Color _color;
  
  public Circle(Ellipse2D.Double circle, Color color){
    _circle = circle;
    _color = color;
  }

  public Ellipse2D.Double get_circle() {
    return _circle;
  }

  public Color get_color() {
    return _color;
  }
  
}

package labyrinth;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Main extends JFrame {

  private static final long serialVersionUID = 1L;

  private final static int WINDOW_WIDTH = 800;
  private final static int WINDOW_HEIGHT = 800;

  private LabyrinthPanel _labyrinthPanel;

  public Main() {
    setTitle("Labyrinth");
    setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
//    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    createGUI();

    setVisible(true);
  }

  private void createGUI() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    _labyrinthPanel = new LabyrinthPanel(new LabyrinthModel());

    _labyrinthPanel.addMouseMotionListener(new MouseAdapter() {

      @Override
      public void mouseMoved(MouseEvent e) {
        _labyrinthPanel.selectTile(e.getPoint());
      }
    });
    
    _labyrinthPanel.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseReleased(MouseEvent e) {
        _labyrinthPanel.switchTile(e.getPoint());
      }
    });

    mainPanel.add(_labyrinthPanel);

    Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    mainPanel.setBorder(border);

    add(mainPanel);
  }

  public static void main(String[] args) {
    new Main();
  }
}

package labyrinth;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    createGUI();

    setVisible(true);
  }

  private void createGUI() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    _labyrinthPanel = new LabyrinthPanel(new LabyrinthModel());

    _labyrinthPanel.addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseMoved(MouseEvent e) {
         _labyrinthPanel.selectTile(e.getPoint());
      }

      @Override
      public void mouseDragged(MouseEvent e) {
      }
    });

    _labyrinthPanel.addMouseListener(new MouseListener() {

      @Override
      public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mousePressed(MouseEvent arg0) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseClicked(MouseEvent arg0) {
      }
    });

    mainPanel.add(_labyrinthPanel);

//    JButton btnSolve = new JButton("Way out!");
//    btnSolve.addActionListener(new ActionListener() {
//
//      @Override
//      public void actionPerformed(ActionEvent arg0) {
//        // TODO Auto-generated method stub
//
//      }
//    });
//
//    mainPanel.add(btnSolve);

    Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    mainPanel.setBorder(border);

    add(mainPanel);
  }

  public static void main(String[] args) {
    new Main();
  }
}

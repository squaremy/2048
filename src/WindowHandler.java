import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *  Class containing elements needed for the game window that shows up
 *  Responsible for setting aspects of the game window before allowing the game elements to be added into it
 **/
public class WindowHandler extends JFrame {
  private static Dimension screeenResolution = Toolkit.getDefaultToolkit().getScreenSize();
  private Dimension windowSize = new Dimension((int)screeenResolution.getWidth()/2, (int)screeenResolution.getHeight()/2);
  private JPanel contentPane;

  /**
   *  Basic Constructor
   *  @param title - desired window title
   **/
  public WindowHandler(String title) {
    super(title);
    contentPane = new JPanel();
    contentPane.setLayout(null);
    add(contentPane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(windowSize);
    setMaximumSize(screeenResolution);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   *  Custon Constructor
   *  @param title - desired window title
   *  @param windowSize - desired window size
   **/
  public WindowHandler(String title, Dimension windowSize) {
    super(title);
    this.windowSize = windowSize;
    contentPane = new JPanel();
    contentPane.setLayout(null);
    add(contentPane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(windowSize);
    setMaximumSize(screeenResolution);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static Dimension getScreenResolution() { return screeenResolution; } // returns the current computer's screen resolution

  /**
   *  Function to add components to the window
   *  @param toAdd - Component to add to the window
   *  @return the Component to add if not null, NullPointerException if otherwise
   **/
  public Component add(Component toAdd) {
    if(toAdd != null) {
      contentPane.add(toAdd);
      return toAdd;
    } else {
      throw new NullPointerException("Component is null!");
    }
  }

  /**
   *  Function to resize the window
   *  @param width - desired width of the window
   *  @param height - desired height of the window
   *  @return nothing is returned
   **/
  @SuppressWarnings("deprecation")
	public void setSize(int width, int height) {
		resize(width, height);
		setLocationRelativeTo(null);
	}
}

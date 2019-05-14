import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WindowHandler extends JFrame {
  private static Dimension screeenResolution = Toolkit.getDefaultToolkit().getScreenSize();
  private Dimension windowSize = new Dimension((int)screeenResolution.getWidth()/2, (int)screeenResolution.getHeight()/2);
  private JPanel contentPane;

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

  public static Dimension getScreenResolution() { return screeenResolution; }

  public Component add(Component toAdd) {
    if(toAdd != null) {
      contentPane.add(toAdd);
      return toAdd;
    } else {
      throw new NullPointerException("Component is null!");
    }
  }

  @SuppressWarnings("deprecation")
	public void setSize(int width, int height) {
		resize(width, height);
		setLocationRelativeTo(null);
	}
}

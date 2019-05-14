import java.awt.*;
import javax.swing.*;

public class Tile extends JPanel {
  private int value;
  private Location myLoc;
  private JTextField valueDisplay;
  private int tileWidth;

  public Tile(Location toSpawnAt, int val, int tileWidth) {
    this.myLoc = new Location(toSpawnAt);
    this.value = val;
    this.tileWidth = tileWidth;
    setLayout(null);
    setSize(this.tileWidth, this.tileWidth);
    setLocation(new Point(this.myLoc.getX(), this.myLoc.getY()));
    switch(this.value) {
      case 2:
        valueDisplay = new JTextField(Integer.toString(value));
        valueDisplay.setSize(this.tileWidth, this.tileWidth);
        valueDisplay.setEditable(false);
        valueDisplay.setHorizontalAlignment(JTextField.CENTER);
        valueDisplay.setOpaque(false);
        valueDisplay.setVisible(true);
        add(valueDisplay, BorderLayout.CENTER);
        setBackground(Color.WHITE);
        break;
      case 4:
        valueDisplay = new JTextField(Integer.toString(value));
        valueDisplay.setSize(this.tileWidth, this.tileWidth);
        valueDisplay.setEditable(false);
        valueDisplay.setHorizontalAlignment(JTextField.CENTER);
        valueDisplay.setOpaque(false);
        valueDisplay.setVisible(true);
        add(valueDisplay, BorderLayout.CENTER);
        setBackground(Color.YELLOW);
        break;
      default:
        valueDisplay = new JTextField();
        valueDisplay.setSize(this.tileWidth, this.tileWidth);
        valueDisplay.setEditable(false);
        valueDisplay.setHorizontalAlignment(JTextField.CENTER);
        valueDisplay.setOpaque(false);
        valueDisplay.setVisible(false);
        add(valueDisplay, BorderLayout.CENTER);
        setBackground(Color.LIGHT_GRAY);
        break;
    }
    setVisible(true);
    repaint();
  }

  public void updateTile() {
    valueDisplay.setVisible(true);
    valueDisplay.repaint();
    setVisible(true);
    repaint();
  }

  public Location getTileLocation() { return myLoc; }

  public int getValue() { return this.value; }

  public boolean canCombine(Tile toCombineWith) {
    if(toCombineWith.getValue() == this.value) {
      return true;
    }
    return false;
  }
}

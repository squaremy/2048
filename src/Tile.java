import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Tile extends JPanel {
  private int value;
  private Location myLoc;
  public JTextField valueDisplay;
  private int tileWidth;

  public Tile(Location toSpawnAt, int val, int tileWidth) {
    this.myLoc = new Location(toSpawnAt);
    this.value = val;
    this.tileWidth = tileWidth;
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    setSize(this.tileWidth, this.tileWidth);
    setLocation(new Point(this.myLoc.getX(), this.myLoc.getY()));
    updateTile();
  }

  public Tile(Tile toCopy) {
    this.value = toCopy.getValue();
    this.myLoc = toCopy.getTileLocation();
    this.tileWidth = (int)toCopy.getSize().getWidth();
    this.valueDisplay = toCopy.valueDisplay;
    updateTile();
  }

  public void updateTile() {
    determineText();
    setVisible(true);
    revalidate();
    repaint();
  }

  public Location getTileLocation() { return myLoc; }

  public void setTileLocation(Location target) {
    this.myLoc = new Location(target);
  }

  public int getValue() { return this.value; }
  public void setValue(int val) { this.value = val; }

  public boolean canCombine(Tile toCombineWith) {
    if(toCombineWith.getValue() == this.value) {
      return true;
    }
    return false;
  }

  private void determineText() {
    if(valueDisplay != null) remove(valueDisplay);
    if(value != 0) valueDisplay = new JTextField(Integer.toString(value));
    else valueDisplay = new JTextField();
    valueDisplay.setSize(this.getWidth(), this.getHeight());
    // valueDisplay.setEditable(false);
    valueDisplay.setHorizontalAlignment(JTextField.CENTER);
    valueDisplay.setFont(new Font("Futura", Font.BOLD, 32));
    valueDisplay.setBorder(BorderFactory.createEmptyBorder());
    valueDisplay.setOpaque(false);
    valueDisplay.setVisible(true);
    switch(this.value) {
      case 2:
        setBackground(new Color(238, 228, 218));
        break;
      case 4:
        setBackground(new Color(236, 224, 200));
        break;
      case 8:
        setBackground(new Color(242, 177, 121));
        break;
      case 16:
        setBackground(new Color(243, 150, 102));
        break;
      case 32:
        setBackground(new Color(245, 124, 95));
        break;
      case 64:
        setBackground(new Color(250, 93, 53));
        break;
      case 128:
        setBackground(new Color(238, 211, 96));
        break;
      case 256:
        setBackground(new Color(234, 204, 107));
        break;
      case 512:
        setBackground(new Color(233, 204, 63));
        break;
      case 1024:
        setBackground(new Color(234, 198, 69));
        break;
      case 2048:
        setBackground(new Color(239, 194, 43));
        break;
      default:
        setBackground(Color.LIGHT_GRAY);
        break;
    }
    add(valueDisplay);
  }
}

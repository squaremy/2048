import java.awt.*;
import javax.swing.*;

public class Tile extends JPanel {
  private int value;
  private Location myLoc;

  public Tile(Location toSpawnAt, int val) {
    this.myLoc = new Location(toSpawnAt);
    this.value = val;
    switch(value) {
      case 2:
        setBackground(Color.WHITE);
        break;
      case 4:
        setBackground(Color.YELLOW);
        break;
      default:
    }
    setVisible(true);
  }

  public Location getLocation() { return myLoc; }

  public int getValue() { return this.value; }

  public boolean canCombine(Tile toCombineWith) {
    if(toCombineWith.getValue() == this.value) {
      return true;
    }
    return false;
  }
}

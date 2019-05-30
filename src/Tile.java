import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *  Class that contains everything we need for a game tile
 *  Responsible for setting the display aspects of a tile and determining if a tile can combine with other tiles
 **/
public class Tile extends JPanel {
  private int value;
  private Location myLoc;
  public JTextField valueDisplay;
  private int tileWidth;

  /**
   *  Constructor that makes a Tile given all needed aspects
   *  @param toSpawnAt - Location for the tile to spawn into
   *  @param val - value of the tile
   *  @param tileWidth - width of the tile
   **/
  public Tile(Location toSpawnAt, int val, int tileWidth) { //defining the size of the tiles with their locations based off of height and width
    this.myLoc = new Location(toSpawnAt);
    this.value = val;
    this.tileWidth = tileWidth;
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    setSize(this.tileWidth, this.tileWidth);
    setLocation(new Point(this.myLoc.getX(), this.myLoc.getY()));
    updateTile();
  }

  /**
   *  Constructor that copies another tile
   *  @param toCopy - tile to copy from
   **/
  public Tile(Tile toCopy) { //assigning values and retaining values
    this.value = toCopy.getValue();
    this.myLoc = toCopy.getTileLocation();
    this.tileWidth = (int)toCopy.getSize().getWidth();
    this.valueDisplay = toCopy.valueDisplay;
    updateTile();
  }

  /**
   *  Function to update the display of the tile
   *  @return nothing is returned
   **/
  public void updateTile() {
    determineText();
    setVisible(true);
    revalidate();
    repaint();
  }

  public Location getTileLocation() { return myLoc; } // return the tile's location

  /**
   *  Function to set the tile's location
   *  @param target - target Location for the tile
   **/
  public void setTileLocation(Location target) {
    this.myLoc = new Location(target);
  }

  public int getValue() { return this.value; } // return the tile's value
  public void setValue(int val) { this.value = val; } // set the tile's value

  /**
   *  function to determine if a tile can combine with another tile
   *  @param toCombineWith - Tile to try combining with
   *  @return true if the tiles can combine (same values), false if otherwise
   **/
  public boolean canCombine(Tile toCombineWith) {
    if(toCombineWith.getValue() == this.value) {
      return true;
    }
    return false;
  }

  /**
   *  Function to determine the styling of a tile based on the value
   *  @return nothing is returned
   **/
  private void determineText() { //assigns the colors, fonts, and fnt sizes to the tiles
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
        setBackground(new Color(238, 228, 218)); //all of the color values for the following tiles were grabbed from the actual game
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
      case 4096:
        setBackground(new Color(0, 0, 0));
        break;
      default:
        setBackground(Color.LIGHT_GRAY);
        break;
    }
    if (this.value > 4096) setBackground(new Color(0, 0, 0)); //assigns all tiles after after 4096 to black

    add(valueDisplay);
  }
}

/**
 *  Object that stores another object's Location
 *  Responsible for keeping track of an object's location and determining different metrics based off of it and other locations
 **/
public class Location {
  private int x, y;

  /**
   *  Default constructor
   *    initial position of (0, 0)
   **/
  public Location() {
    x = 0;
    y = 0;
  }

  /**
   *  Known location constructor, creates based on x and y
   *  @param x - x position of object
   *  @param y - y position of object
   **/
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   *  Known location constructor, copies target location
   *  @param target - target starting location
   **/
  public Location(Location target) {
    this(target.getX(), target.getY());
  }

  public int getX() { return x; } // returns the x position
  public int getY() { return y; } // returns the y position
  /**
   *  Function that determines if two locations overlap (are the same)
   *  @param toCompare - location to test against
   *  @return true if current location and target are the same, false otherwise
   **/
  public boolean equals(Location toCompare) {
    if(toCompare.getX() == x && toCompare.getY() == y) return true;
    return false;
  }
  /**
   *  Function to determine the distance to some target location using the distance formula
   *  @param target - location to measure distance to
   *  @return the distance to the target
   **/
  public double getDistance(Location target) {
    double dist = Math.sqrt(Math.pow(target.getX() - x, 2) + Math.pow(target.getY() - y, 2));
    return dist;
  }

  /**
   *  Function to compile a location into something printable (for debugging mostly)
   *  @return the string to output
   **/
  public String toString() {
    String toReturn = "X: ";
    toReturn += x;
    toReturn += " Y: ";
    toReturn += y;
    return toReturn;
  }
}

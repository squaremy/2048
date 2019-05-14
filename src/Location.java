public class Location {
  private int x, y;

  public Location() {
    x = 0;
    y = 0;
  }

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Location(Location target) {
    this(target.getX(), target.getY());
  }

  public int getX() { return x; }
  public int getY() { return y; }
  public boolean equals(Location toCompare) {
    if(toCompare.getX() == x && toCompare.getY() == y) return true;
    return false;
  }
  public double getDistance(Location target) {
    double dist = Math.sqrt(Math.pow(target.getX() - x, 2) + Math.pow(target.getY() - y, 2));
    return dist;
  }

  public String toString() {
    String toReturn = "X: ";
    toReturn += x;
    toReturn += " Y: ";
    toReturn += y;
    return toReturn;
  }
}

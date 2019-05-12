import java.awt.*;
import javax.swing.*;

public class main {
  static WindowHandler window;

  public static void main(String args[]) {
    Dimension windowSize = new Dimension((int)(WindowHandler.getScreenResolution().getWidth()/1.5), (int)(WindowHandler.getScreenResolution().getHeight()/1.5));
    window = new WindowHandler("2048", windowSize);


  }
}

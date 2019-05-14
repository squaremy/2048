import java.awt.*;
import javax.swing.*;

public class main {
  static WindowHandler window;
  static Board gameBoard;
  static boolean running = true;

  public static void main(String args[]) {
    Dimension windowSize = new Dimension((int)(WindowHandler.getScreenResolution().getWidth()/1.5), (int)(WindowHandler.getScreenResolution().getHeight()/1.5));
    window = new WindowHandler("2048", windowSize);
    gameBoard = new Board(window.getSize());
    window.add(gameBoard);
    sleep(100);
    while(window.isActive()) {
      gameBoard.requestFocus();
      gameBoard.updateBoard();
      sleep(100);
    }
  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
}

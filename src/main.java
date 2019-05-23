import java.awt.*;
import javax.swing.*;

public class main {
  static WindowHandler window;
  static Board gameBoard;
  static boolean running = true;
  static Dimension windowSize;

  public static void main(String args[]) {
    windowSize = new Dimension((int)(WindowHandler.getScreenResolution().getWidth()/1.5), (int)(WindowHandler.getScreenResolution().getHeight()/1.5));
    window = new WindowHandler("2048", windowSize);
    gameBoard = new Board(window.getSize());
    window.add(gameBoard);
    window.repaint();
    window.revalidate();
    sleep(100);
    while(window.isActive()) {
      gameBoard.requestFocus();
      gameBoard.move();
      window.revalidate();
      window.repaint();
      if(gameBoard.checkForLoss()) {
        sleep(500);
        endGame();
        sleep(3000);
        window.setVisible(false);
        window.dispose();
      }
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

  private static void endGame() {
    JTextField endDisplay = new JTextField("Game Over!");
    endDisplay.setSize((int)windowSize.getWidth()/3, (int)windowSize.getHeight()/3);
    endDisplay.setHorizontalAlignment(JTextField.CENTER);
    endDisplay.setFont(new Font("Futura", Font.BOLD, 64));
    endDisplay.setBorder(BorderFactory.createEmptyBorder());
    endDisplay.setOpaque(false);
    endDisplay.setVisible(true);
    window.removeAll();
    window.add(endDisplay);
    window.revalidate();
    window.repaint();
  }
}

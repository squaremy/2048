import java.awt.*;
import javax.swing.*;

public class main {
  static WindowHandler window;
  static Board gameBoard;
  static boolean running = true;
  static Dimension windowSize;
  static boolean won = false;

  public static void main(String args[]) {
    windowSize = new Dimension((int)(WindowHandler.getScreenResolution().getWidth()/1.5), (int)(WindowHandler.getScreenResolution().getHeight()/1.5));
    window = new WindowHandler("2048", windowSize);
    gameBoard = new Board(window.getSize());
    window.add(gameBoard);
    window.repaint();
    window.revalidate();
    sleep(100);
    while(window.isActive() && !gameBoard.checkForLoss()) {
      gameBoard.requestFocus();
      gameBoard.move();
      window.revalidate();
      window.repaint();
      if(gameBoard.checkForWin() && !won) {
        sleep(250);
        gameWon();
        gameBoard.setVisible(true);
        window.revalidate();
        window.repaint();
      }
      sleep(100);
    }
    sleep(1000);
    endGame();
    sleep(3000);
    window.setVisible(false);
    window.dispose();
  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void endGame() {
    gameBoard.setVisible(false);
    JPanel endScreen = new JPanel();
    endScreen.setBounds(0, 0, (int)window.getSize().getWidth(), (int)window.getSize().getHeight());
    endScreen.setBackground(Color.WHITE);
    endScreen.requestFocus();
    endScreen.setVisible(true);
    JTextField endDisplay = new JTextField("Game Over!");
    endDisplay.setSize((int)windowSize.getWidth()/3, (int)windowSize.getHeight()/3);
    endDisplay.setHorizontalAlignment(JTextField.CENTER);
    endDisplay.setFont(new Font("Futura", Font.BOLD, 64));
    endDisplay.setBorder(BorderFactory.createEmptyBorder());
    endDisplay.setOpaque(false);
    endDisplay.setVisible(true);
    endScreen.add(endDisplay);
    window.add(endScreen);
    window.revalidate();
    window.repaint();
  }

  private static void gameWon() {
    gameBoard.setVisible(false);
    JPanel endScreen = new JPanel();
    endScreen.setBounds(0, 0, (int)window.getSize().getWidth(), (int)window.getSize().getHeight());
    endScreen.setBackground(Color.WHITE);
    endScreen.requestFocus();
    endScreen.setVisible(true);
    JTextField endDisplay = new JTextField("You Win!");
    endDisplay.setSize((int)windowSize.getWidth()/3, (int)windowSize.getHeight()/3);
    endDisplay.setHorizontalAlignment(JTextField.CENTER);
    endDisplay.setFont(new Font("Futura", Font.BOLD, 64));
    endDisplay.setBorder(BorderFactory.createEmptyBorder());
    endDisplay.setOpaque(false);
    endDisplay.setVisible(true);
    endScreen.add(endDisplay);
    window.add(endScreen);
    window.revalidate();
    window.repaint();
    won = true;
    sleep(3000);
    endScreen.setVisible(false);
    endDisplay.setVisible(false);
  }
}

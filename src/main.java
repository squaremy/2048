import java.awt.*;
import javax.swing.*;

/**
 *  Class that contains all the game elements
 *  Responsible for running the game and updating the screen
 **/
public class main {
  static WindowHandler window;
  static Board gameBoard;
  static boolean running = true;
  static Dimension windowSize;
  static boolean won = false;

  /**
   *  Main function that runs on initialization, stats and runs game
   *  @param args - array of arguements to pass in (not needed)
   *  @return nothing is returned
   **/
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
      if(gameBoard.checkForWin() && !won) { //checks to see if game has won (by aquiring the 2048 tile) if it has been acheived display win
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

  /**
   *  Function to delay a desired time in milliseconds
   *  @param millis - time in millis to wait
   *  @return nothing is returned
   **/
  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   *  Function to show the game over screen
   *  @return nothing is returned
   **/
  private static void endGame() {  //sets the determinations after game has lost end game has occured
    gameBoard.setVisible(false);
    JPanel endScreen = new JPanel(); //closes j pannel
    endScreen.setBounds(0, 0, (int)window.getSize().getWidth(), (int)window.getSize().getHeight());
    endScreen.setBackground(Color.WHITE);
    endScreen.requestFocus();
    endScreen.setVisible(true);
    JTextField endDisplay = new JTextField("Game Over!"); //displays Game over with font size, font type
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

  /**
   *  Function to show the you win screen
   *  @return nothing is returned
   **/
  private static void gameWon() { //determinations for game won
    gameBoard.setVisible(false);
    JPanel endScreen = new JPanel(); //closes J pannel for time
    endScreen.setBounds(0, 0, (int)window.getSize().getWidth(), (int)window.getSize().getHeight());
    endScreen.setBackground(Color.WHITE);
    endScreen.requestFocus();
    endScreen.setVisible(true);
    JTextField endDisplay = new JTextField("You Win!"); //displays you won with font size, font, and color
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
    endScreen.setVisible(false); //sets J pannel visibility to false
    endDisplay.setVisible(false);
  }
}

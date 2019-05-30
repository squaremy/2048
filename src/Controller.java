import java.awt.event.*;

/**
 *  Class that contains the "controller" for the game
 *  Responsible for detecting key presses and setting a desired movement direction
 **/
public class Controller implements KeyListener {

  /**
   *  Default constructor
   **/
  public Controller(){}

  /**
   *  Custom Direction object that consists of N, S, W, E, and NONE
   **/
  public enum Direction {
    N, S, W, E, NONE
  };
  public volatile Direction toMove = Direction.NONE;

  @Override
  public void keyTyped(KeyEvent e) {

  }

  /**
   *  Function that is run whenever a key is pressed, determines the direction to be moving
   *  @param e - KeyEvent representing the key that has been pressed
   *  @return nothing is returned
   **/
  @Override
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()) {
      case KeyEvent.VK_W:
        toMove = Direction.N;
        break;
      case KeyEvent.VK_A:
        toMove = Direction.W;
        break;
      case KeyEvent.VK_S:
        toMove = Direction.S;
        break;
      case KeyEvent.VK_D:
        toMove = Direction.E;
        break;
      case KeyEvent.VK_UP:
        toMove = Direction.N;
        break;
      case KeyEvent.VK_LEFT:
        toMove = Direction.W;
        break;
      case KeyEvent.VK_DOWN:
        toMove = Direction.S;
        break;
      case KeyEvent.VK_RIGHT:
        toMove = Direction.E;
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}

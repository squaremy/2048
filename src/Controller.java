import java.awt.event.*;

public class Controller implements KeyListener {

  public Controller(){}

  public enum Direction {
    N, S, W, E, NONE
  };
  public Direction toMove = Direction.NONE;

  @Override
  public void keyTyped(KeyEvent e) {

  }

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

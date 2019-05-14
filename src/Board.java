import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {
  private Tile tiles[][] = new Tile[4][4];
  private int boardWidth;
  private int tileWidth;
  private Controller controller;

  public Board(Dimension frameSize) {
    setFocusable(true);
    controller = new Controller();
    addKeyListener(controller);
    setBackground(Color.GRAY);
    boardWidth = (frameSize.getWidth() > frameSize.getHeight())? (int)frameSize.getHeight()-50:(int)frameSize.getWidth()-30;
    tileWidth = (int)(0.20*boardWidth);
    Location center;
    if(frameSize.getWidth() > frameSize.getHeight()) {
      center = new Location((int)(frameSize.getWidth()/2-boardWidth/2), 5);
    } else {
      center = new Location(5, (int)(frameSize.getHeight()/2-boardWidth/2));
    }
    setBounds(center.getX(), center.getY(), boardWidth, boardWidth);
    GridLayout layout = new GridLayout(4, 4, (int)(0.01*boardWidth), (int)(0.01*boardWidth));
    setLayout(layout);
    setBorder(BorderFactory.createEmptyBorder((int)(0.01*boardWidth), (int)(0.01*boardWidth), (int)(0.01*boardWidth), (int)(0.01*boardWidth)));
    for(int i = 0; i < tiles.length; i++) {
      for(int j = 0; j < tiles[i].length; j++) {
        tiles[i][j] = new Tile(new Location(i, j), 0, tileWidth);
      }
    }
    spawnTile();
    spawnTile();
  }

  public void spawnTile() {
    int x = (int)(Math.random()*4);
    int y = (int)(Math.random()*4);
    while(tiles[x][y].getValue() != 0) {
      x = (int)(Math.random()*4);
      y = (int)(Math.random()*4);
    }
    int value = (int)(Math.random()*100);
    if(value > 66) value = 4;
    else value = 2;
    tiles[x][y] = new Tile(getRandomLocation(), value, tileWidth);
    updateBoard();
  }

  public void move() {
    if(controller.toMove != Direction.NONE) {
      controller.toMove = Direction.NONE;
    }
  }

  public void updateBoard() {
    removeAll();
    for(int i = 0; i < tiles.length; i++) {
      for(int j = 0; j < tiles[i].length; j++) {
        tiles[i][j].updateTile();
        add(tiles[i][j]);
      }
    }
    setVisible(true);
    revalidate();
    repaint();
  }

  private Location getRandomLocation() {
    int x = (int)(Math.random()*4);
    int y = (int)(Math.random()*4);
    Location randomLoc = new Location(x, y);
    for(int i = 0; i < tiles.length; i++) {
      for(int j = 0; j < tiles[i].length; j++) {
        if(tiles[i][j].getValue() != 0 && tiles[i][j].getTileLocation().equals(randomLoc)) return getRandomLocation();
      }
    }
    return randomLoc;
  }

}

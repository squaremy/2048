import java.awt.*;
import javax.swing.*;

/**
 *  Class that contains everything about the game board
 *  Responsible for controlling the gameplay
 **/
public class Board extends JPanel {
  private Tile tiles[][] = new Tile[4][4];
  private int boardWidth;
  private int tileWidth;
  private Controller controller;
  public int score = 0;

  /**
   *  Basic constructor, sets up game board entirely given a game window dimension
   *  @param frameSize - Dimension representing the size of the game window
   **/
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

  /**
   *  Function responsible for spawning a 2 or 4 tile in an open spot on the board
   *  @return nothing is returned
   **/
  public void spawnTile() {
    int value = (int)(Math.random()*100);
    if(value > 66) value = 4;
    else value = 2;
    spawnTile(value);
  }

  /**
   *  Function responsible for spawning a tile of given value in a random open spot on the board
   *  @param value - Desired value for the tile to be spawned
   *  @return nothing is returned
   **/
  public void spawnTile(int value) {
    int x = (int)(Math.random()*4);
    int y = (int)(Math.random()*4);
    while(tiles[x][y].getValue() != 0) {
      x = (int)(Math.random()*4);
      y = (int)(Math.random()*4);
    }
    tiles[x][y] = new Tile(getRandomLocation(), value, tileWidth);
    updateBoard();
  }

  /**
   *  Function responsible for handling everything involved with movement of the tiles
   *    Based on the desired movement direction, tiles are combined if possible and pushed to that side
   *  @return nothing is returned
   **/
  public void move() {
    // MOVE AND COMBINE TILES HERE
    boolean shouldSpawnTile = false;
    switch(controller.toMove) {
      case N://up algorithim
        for(int col = 0; col < tiles[0].length; col++) {
          for(int row = 0; row < tiles.length-1; row++) {
            for(int curRow = row+1; curRow < tiles.length; curRow++) {
              if(tiles[row][col].getValue() != 0) {
                if(tiles[row][col].canCombine(tiles[curRow][col])) {
                  score += tiles[row][col].getValue() + tiles[curRow][col].getValue();
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[row][col].getValue() + tiles[curRow][col].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[curRow][col] = new Tile(tiles[curRow][col].getTileLocation(), 0, (int)tiles[curRow][col].getSize().getWidth());
                  shouldSpawnTile = true;
                  row++;
                  curRow = row;
                  continue;
                } else if(tiles[curRow][col].getValue() != 0) {
                  row++;
                  curRow = row;
                  continue;
                }
              } else {
                if(tiles[curRow][col].getValue() != 0) {
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[curRow][col].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[curRow][col] = new Tile(tiles[curRow][col].getTileLocation(), 0, (int)tiles[curRow][col].getSize().getWidth());
                  shouldSpawnTile = true;
                }
              }
            }
          }
        }
        controller.toMove = Controller.Direction.NONE;
        break;
      case S://down algorithim
        for(int col = 0; col < tiles[0].length; col++) {
          for(int row = tiles.length-1; row > 0; row--) {
            for(int curRow = row-1; curRow >= 0; curRow--) {
              if(tiles[row][col].getValue() != 0) {
                if(tiles[row][col].canCombine(tiles[curRow][col])) {
                  score += tiles[row][col].getValue() + tiles[curRow][col].getValue();
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[row][col].getValue() + tiles[curRow][col].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[curRow][col] = new Tile(tiles[curRow][col].getTileLocation(), 0, (int)tiles[curRow][col].getSize().getWidth());
                  shouldSpawnTile = true;
                  row--;
                  curRow = row;
                  continue;
                } else if(tiles[curRow][col].getValue() != 0) {
                  row--;
                  curRow = row;
                  continue;
                }
              } else {
                if(tiles[curRow][col].getValue() != 0) {
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[curRow][col].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[curRow][col] = new Tile(tiles[curRow][col].getTileLocation(), 0, (int)tiles[curRow][col].getSize().getWidth());
                  shouldSpawnTile = true;
                }
              }
            }
          }
        }
        controller.toMove = Controller.Direction.NONE;
        break;
      case W: //left alg4orithim
        for(int row = 0; row < tiles.length; row++) {
          for(int col = 0; col < tiles[row].length-1; col++) {
            for(int curCol = col+1; curCol < tiles[row].length; curCol++) {
              if(tiles[row][col].getValue() != 0) {
                if(tiles[row][col].canCombine(tiles[row][curCol])) {
                  score += tiles[row][col].getValue() + tiles[row][curCol].getValue();
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[row][col].getValue() + tiles[row][curCol].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[row][curCol] = new Tile(tiles[row][curCol].getTileLocation(), 0, (int)tiles[row][curCol].getSize().getWidth());
                  shouldSpawnTile = true;
                  col++;
                  curCol = col;
                  continue;
                } else if(tiles[row][curCol].getValue() != 0) {
                  col++;
                  curCol = col;
                  continue;
                }
              } else {
                if(tiles[row][curCol].getValue() != 0) {
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[row][curCol].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[row][curCol] = new Tile(tiles[row][curCol].getTileLocation(), 0, (int)tiles[row][curCol].getSize().getWidth());
                  shouldSpawnTile = true;
                }
              }
            }
          }
        }
        controller.toMove = Controller.Direction.NONE;
        break;
      case E: //right algorithim
        for(int row = 0; row < tiles.length; row++) {
          for(int col = tiles[row].length-1; col > 0; col--) {
            for(int curCol = col-1; curCol >= 0; curCol--) {
              if(tiles[row][col].getValue() != 0) {
                if(tiles[row][col].canCombine(tiles[row][curCol])) {
                  score += tiles[row][col].getValue() + tiles[row][curCol].getValue();
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[row][col].getValue() + tiles[row][curCol].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[row][curCol] = new Tile(tiles[row][curCol].getTileLocation(), 0, (int)tiles[row][curCol].getSize().getWidth());
                  shouldSpawnTile = true;
                  col--;
                  curCol = col;
                  continue;
                } else if(tiles[row][curCol].getValue() != 0) {
                  col--;
                  curCol = col;
                  continue;
                }
              } else {
                if(tiles[row][curCol].getValue() != 0) {
                  tiles[row][col] = new Tile(tiles[row][col].getTileLocation(), tiles[row][curCol].getValue(), (int)tiles[row][col].getSize().getWidth());
                  tiles[row][curCol] = new Tile(tiles[row][curCol].getTileLocation(), 0, (int)tiles[row][curCol].getSize().getWidth());
                  shouldSpawnTile = true;
                }
              }
            }
          }
        }
        controller.toMove = Controller.Direction.NONE;
        break;
    }
    updateBoard();
    if(shouldSpawnTile) {
      spawnTile();
      shouldSpawnTile = false;
    }
  }

  /**
   *  Function responsible for updating the display of the game board within the game window
   *  @return nothing is returned
   **/
  public void updateBoard() {
    removeAll();
    for(int i = 0; i < tiles.length; i++) {
      for(int j = 0; j < tiles[i].length; j++) {
        tiles[i][j].updateTile();
        add(tiles[i][j]);
      }
    }
    setVisible(true);
    // repaint();
    // revalidate();
  }

  /**
   *  Function responsible for determining if the player has officially won the game
   *  @return true if the game is won (2048 is present), false if game is not won (2048 is not present)
   **/
  public boolean checkForWin() { //checks for win
    for(int row = 0; row < tiles.length; row++) {
      for(int col = 0; col < tiles[row].length; col++) {
        if(tiles[row][col].getValue() == 2048) return true;
      }
    }
    return false;
  }

  /**
   *  Function responsible for determining if the game has been lost
   *  @return true if the game is lost (no more moves can be made), false if the game is not lost (still playable)
   **/
  public boolean checkForLoss() { //checks for loss, based on the 2D array positioning
    if(tiles[0][0].canCombine(tiles[0][1]) || tiles[0][0].canCombine(tiles[1][0]) || tiles[0][0].getValue() == 0) return false;
    else if(tiles[0][1].canCombine(tiles[0][2]) || tiles[0][1].canCombine(tiles[1][1]) || tiles[0][1].getValue() == 0) return false;
    else if(tiles[0][2].canCombine(tiles[0][3]) || tiles[0][2].canCombine(tiles[1][2]) || tiles[0][2].getValue() == 0) return false;
    else if(tiles[0][3].canCombine(tiles[1][3]) || tiles[0][3].getValue() == 0) return false;
    else if(tiles[1][0].canCombine(tiles[1][1]) || tiles[1][0].canCombine(tiles[2][0]) || tiles[1][0].getValue() == 0) return false;
    else if(tiles[1][1].canCombine(tiles[1][2]) || tiles[1][1].canCombine(tiles[2][1]) || tiles[1][1].getValue() == 0) return false;
    else if(tiles[1][2].canCombine(tiles[1][3]) || tiles[1][2].canCombine(tiles[2][2]) || tiles[1][2].getValue() == 0) return false;
    else if(tiles[1][3].canCombine(tiles[2][3]) || tiles[1][3].getValue() == 0) return false;
    else if(tiles[2][0].canCombine(tiles[2][1]) || tiles[2][0].canCombine(tiles[3][0]) || tiles[2][0].getValue() == 0) return false;
    else if(tiles[2][1].canCombine(tiles[2][2]) || tiles[2][1].canCombine(tiles[3][1]) || tiles[2][1].getValue() == 0) return false;
    else if(tiles[2][2].canCombine(tiles[2][3]) || tiles[2][2].canCombine(tiles[3][2]) || tiles[2][2].getValue() == 0) return false;
    else if(tiles[2][3].canCombine(tiles[3][3]) || tiles[2][3].getValue() == 0) return false;
    else if(tiles[3][0].canCombine(tiles[3][1]) || tiles[3][0].getValue() == 0) return false;
    else if(tiles[3][1].canCombine(tiles[3][2]) || tiles[3][1].getValue() == 0) return false;
    else if(tiles[3][2].canCombine(tiles[3][3]) || tiles[3][2].getValue() == 0) return false;
    else if(tiles[3][3].getValue() == 0) return false;
    else return true;
  }

  /**
   *  Function responsible for finding a random empty tile on the board
   *  @return a Location containing the position of a random empty tile
   **/
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

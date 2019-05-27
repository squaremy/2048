import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {
  private Tile tiles[][] = new Tile[4][4];
  private int boardWidth;
  private int tileWidth;
  private Controller controller;
  public int score = 0;

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
    // MOVE AND COMBINE TILES HERE
    boolean shouldSpawnTile = false;
    switch(controller.toMove) {
      case N:
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
      case S:
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
      case W:
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
      case E:
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

  public boolean checkForWin() {
    for(int row = 0; row < tiles.length; row++) {
      for(int col = 0; col < tiles[row].length; col++) {
        if(tiles[row][col].getValue() == 2048) return true;
      }
    }
    return false;
  }

  public boolean checkForLoss() {
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

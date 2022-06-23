package pt.projeto.batalhadereinos.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board implements IDrawable{
    private Square[][] boardPositions;

    private Castle player1Castle;
    private Castle player2Castle;

    public Board(){
      boardPositions = new Square[4][10];
      for(int i = 0;i<4;i++){
        for(int j = 0;j<10;j++){
          boardPositions[i][j] = new Square("Rectangle", i, j);
        }
      }
      player1Castle = new Castle();
      player2Castle = new Castle();
    }

    public void addTroop(Troop troop,int row, int column){
      boardPositions[row][column].setTroop(troop);
    }

    public Troop getTroop(int row, int column) {
      Troop troop = null;
      if(row >= 0 && row <= 4 && column >= 0 && column <= 9){
        troop = boardPositions[row][column].getTroop();
      }
      return troop;
    }

    public Castle getCastle(int num) {
      return num == 1 ? player1Castle : player2Castle;
    }

    public void removeTroop(int row, int column){
      boardPositions[row][column].removeTroop();
    }

    public void draw(SpriteBatch batch) {
      for(Square[] boardRow : boardPositions){
        for(Square s : boardRow){
          s.draw(batch);
        }
      }
    }
}

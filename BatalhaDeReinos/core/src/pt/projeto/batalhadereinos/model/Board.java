package pt.projeto.batalhadereinos.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board implements IDrawable{
    private Square[][] boardPositions;

    public Troop getTroop(int row, int column) {
      return boardPositions[row][column].getTroop();
    }

    public void draw(SpriteBatch batch) {
      for(Square[] boardRow : boardPositions){
        for(Square s : boardRow){
          s.draw(batch);
        }
      }
    }
}

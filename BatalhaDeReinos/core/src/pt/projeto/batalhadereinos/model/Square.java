package pt.projeto.batalhadereinos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Square implements IDrawable{
    private Texture graphic;
    private int row;
    private int column;
    private Troop troop;

    public Square(String graphicAdress,int row, int column){
        this.graphic = new Texture(Gdx.files.internal(graphicAdress));
        this.row = row;
        this.column = column;
        this.troop = null;
    }

    public Troop getTroop(){
        return this.troop;
    }

    public void draw(SpriteBatch batch){
        int graphicRow = row*64+0;
        int graphicColumn = column*64+0;

        batch.draw(graphic, row, column);

        if(this.troop != null){
            troop.draw(batch);
        }
    }
}

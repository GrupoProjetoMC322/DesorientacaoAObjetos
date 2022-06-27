package pt.projeto.batalhadereinos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Square implements IDrawable{
    private Texture graphic;
    private int row;
    private int column;
    private Troop troop;
    private Buff buff;

    public Square(String graphicAdress,int row, int column){
        this.graphic = new Texture(Gdx.files.internal(graphicAdress+".png"));
        this.row = row;
        this.column = column;
        this.troop = null;
        this.buff = null;
    }

    
    public void setTroop(Troop troop){
        this.troop = troop;
    }

    public Troop getTroop(){
        return this.troop;
    }

    public void removeTroop(){
        this.troop = null;
    }

    public void setBuff(Buff buff){
        this.buff = buff;
    }

    public Buff getBuff(){
        return this.buff;
    }

    public void removeBuff(){
        this.buff = null;
    }
    

    public void draw(SpriteBatch batch){
        int graphicRow = column*100+220;
        int graphicColumn = 644-row*100;

        batch.draw(graphic, graphicRow, graphicColumn);

        if(this.buff != null){
            buff.draw(batch);
        }
        if(this.troop != null){
            troop.draw(batch);
        }
    }
}

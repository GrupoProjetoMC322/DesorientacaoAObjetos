package pt.projeto.batalhadereinos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Buff implements IDrawable{
    private int row;
    private int column;
    protected Texture graphic;
    private String type;

    public Buff(int row, int column, String type){
        this.row = row;
        this.column = column;
        this.type = type;
        this.graphic = new Texture(Gdx.files.internal("icons/"+ type + ".png"));
    }

    public String getType(){
        return this.type;
    }

    public Texture getGraphic(){
        return this.graphic;
    }

    public void draw(SpriteBatch batch){
        
        int graphicRow = column*100+234;
        int graphicColumn = +658-row*100;

        batch.draw(graphic, graphicRow, graphicColumn);
    }
}

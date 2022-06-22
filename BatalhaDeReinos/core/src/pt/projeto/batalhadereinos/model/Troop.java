package pt.projeto.batalhadereinos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Troop implements ITroopObserver, IDrawable{
    private Board board;
    private Texture graphic;
    private int row;
    private int column;
    private int health;
    private int attack;
    private int cost;
    private int range;
    private int speed;
    private String type;

    public Troop(Board board, String graphicAdress, int row, int column, int health, int attack,
    int cost, int range, int speed, String type) {
		this.board = board;
        this.graphic = new Texture(Gdx.files.internal(graphicAdress));
		this.row = row;
		this.column = column;
        this.health = health;
        this.attack = attack;
        this.cost = cost;
        this.range = range;
        this.speed = speed;	
		this.type = type;
	}

    public int getCost(){
        return cost;
    }

    public boolean move(){
        boolean couldMove = true;
        for(int i = this.row;i<=i+this.speed;i++){
            if(this.board.getTroop(i, this.column) == null){
                this.row++;
            }else{
                couldMove = false;
                break;
            }
        }
        return couldMove;
    }

    public void attack(){

    }

    public void draw(SpriteBatch batch){
        
        int graphicRow = row*64+0;
        int graphicColumn = column*64+0;

        batch.draw(graphic, graphicRow, graphicColumn);
    }
    
}

package pt.projeto.batalhadereinos.model;

import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Troop implements ITroopObserver, IDrawable{
    protected Board board;
    protected Texture graphic;
    protected int row;
    protected int column;
    protected int health;
    protected int attack;
    protected int cost;
    protected int range;
    protected int speed;
    protected String type;
    protected int fromWhichPlayer;

    public Troop(Board board, String graphicAdress, int row, int column, int health, int attack,
    int cost, int range, int speed, String type, int fromWhichPlayer) {
		this.board = board;
        this.graphic = new Texture(Gdx.files.internal(graphicAdress + (fromWhichPlayer == 1 ? "Red":"Blue") +".png"));
		this.row = row;
		this.column = column;
        this.health = health;
        this.attack = attack;
        this.cost = cost;
        this.range = range;
        this.speed = speed;	
		this.type = type;
        this.fromWhichPlayer = fromWhichPlayer;
	}

    public int getCost(){
        return this.cost;
    }
    public int getRow(){
        return this.row;
    }
    public int getCollumn(){
        return this.column;
    }
    public int getRange(){
        return this.range;
    }
    public int getHealth(){
        return this.health;
    }

    public void die(){
        System.out.println(this.type+": Morri!");
        board.removeTroop(this.row, this.column);
    }

    public int getFromWhichPlayer(){
        return fromWhichPlayer;
    }

    public ArrayList<Troop> verifyRange(){
        ArrayList<Troop> enemyTroopInRange = new ArrayList<>();

        if(fromWhichPlayer == 1){
            for(int i = 1; i<=range;i++){

                boolean troopNotNull = board.getTroop(row, column+i) != null;
                boolean foundTroopFromOtherPlayer = troopNotNull && (board.getTroop(row, column+i).getFromWhichPlayer() != this.fromWhichPlayer);
                
                if(foundTroopFromOtherPlayer){
                    enemyTroopInRange.add(board.getTroop(row, column+i));
                    break;
                }
            }
        } else{
            for(int i = 1; i<=range;i++){
    
                boolean troopNotNull = board.getTroop(row, column-i) != null;
                boolean foundTroopFromOtherPlayer = troopNotNull && (board.getTroop(row, column-i).getFromWhichPlayer() != this.fromWhichPlayer);
                    
                if(foundTroopFromOtherPlayer){
                    enemyTroopInRange.add(board.getTroop(row, column-i));
                    break;
                }
            }
        }
        
        return enemyTroopInRange;
    }

    public boolean move(){
        
        boolean attacking = false;
        ArrayList<Troop> enemyTroopsFound = new ArrayList<>();

        // Verify before movement
        enemyTroopsFound = verifyRange();
        if(!enemyTroopsFound.isEmpty()){
            attacking = true;
        }
        
        // Movement
        for(int i = 1; i<=this.speed && attacking == false; i++){
            
            if(fromWhichPlayer == 1){
                if(this.column+i >= 0 && this.column+i<= 8){
                    board.removeTroop(this.row, this.column);
                    this.column++;
                    board.addTroop(this, this.row, this.column);
                    
                } else{
                    attacking = true;
                }
            } else{
                
                if(this.column-i >= 1 && this.column-i<= 9){
                    board.removeTroop(this.row, this.column);
                    this.column--;
                    board.addTroop(this, this.row, this.column);
                } else{
                    attacking = true;
                }
            }
                       

            enemyTroopsFound = verifyRange();
            if(!enemyTroopsFound.isEmpty()){
                attacking = true;
                break;
            }
        }

        return attacking;
    }

    public void damageTroop(int damage){
        this.health -= damage;
        if(this.health <= 0){
            this.die();
        }
    }

    public void attack(){
        // Player 1 - ataca castelo com coluna 6
        // Player 2 - ataca castelo com coluna 1
        // Ataque de tropa acontece quando não estão nessas colunas e verificam range
        if(fromWhichPlayer == 1){
            if(column == 8){
                System.out.println("Ataca castelo");
                board.getCastle(2).damageCastle(this.attack);
            } else {
                ArrayList<Troop> enemyTroopsFound = new ArrayList<>();
                enemyTroopsFound = verifyRange();
                System.out.println(enemyTroopsFound.isEmpty());
                for(Troop enemyTroop : enemyTroopsFound){
                    System.out.println("chegou aqui");
                    enemyTroop.damageTroop(this.attack);
                }
            }
        } else {
            if(column == 1){
                System.out.println("Ataca castelo");
                board.getCastle(1).damageCastle(this.attack);
            } else{
                ArrayList<Troop> enemyTroopsFound = new ArrayList<>();
                enemyTroopsFound = verifyRange();
                for(Troop enemyTroop : enemyTroopsFound){
                    enemyTroop.damageTroop(this.attack);
                }
            }
        }
    }

    

    public void draw(SpriteBatch batch){
        
        int graphicRow = column*100+220;
        int graphicColumn = +644-row*100;

        batch.draw(graphic, graphicRow, graphicColumn);
    }
    
}

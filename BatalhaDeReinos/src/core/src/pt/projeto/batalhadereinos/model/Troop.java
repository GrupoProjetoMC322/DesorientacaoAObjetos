package pt.projeto.batalhadereinos.model;

import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Troop implements ITroopObserver{
    protected Board board;

    protected Texture graphic;
    protected TextureRegion textureRegion;
    protected Sprite sprite;

    protected int row;
    protected int column;
    protected int health;
    protected int attack;
    protected int cost;
    protected int range;
    protected int speed;
    protected String type;

    protected int fromWhichPlayer;

    protected String buff = "None";

    public Troop(Board board, String graphicAdress, int row, int column, int health, int attack,
    int cost, int range, int speed, String type, int fromWhichPlayer) {
		this.board = board;
        this.graphic = new Texture(Gdx.files.internal("icons/" + (fromWhichPlayer == 1 ? "red":"blue") + graphicAdress + ".png"));
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


    public abstract void verifyMap();

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
    public String getType(){
        return this.type;
    }
    public String getBuff(){
        return this.buff;
    }
    public Texture getGraphic(){
        return this.graphic;
    }

    public boolean isAlive(){
        boolean alive = true;
        if(this.health <= 0){
            alive = false;
        }
        return alive;
    }

    public void die(){
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


    public void verifyFire(){
        if(board.getFire(this.row, this.column)){
            this.health -= 1;
        }
    }

    public void verifyBuff(){
        if(board.getBuff(this.row, this.column) != null){
            if(this.buff == "None"){
                switch (board.getBuff(this.row, this.column).getType()) {
                    case "HealthPotion":
                        this.health *= 2;
                        this.cost *= 2;
                        this.buff = "Health";
                        break;
                    case "AttackPotion":
                        this.attack *= 2;
                        this.cost *= 2;
                        this.buff = "Attack";
                        break;
                    case "MixedPotion":
                        this.health *= 2;
                        this.attack *= 2;
                        this.cost *= 2;
                        this.buff = "Mixed";
                        break;
                    default:
                        break;
                }
            }
            board.removeBuff(this.row, this.column);
        }
    }

    public boolean move(){

        verifyMap();

        boolean attacking = false;
        ArrayList<Troop> enemyTroopsFound = new ArrayList<>();

        // Verify before movement
        enemyTroopsFound = verifyRange();
        if(!enemyTroopsFound.isEmpty()){
            attacking = true;
        }

        boolean nextSquareTroopNotNull = board.getTroop(row, column+1) != null;

        // Movement
        for(int i = 1; i<=this.speed && attacking == false && !nextSquareTroopNotNull; i++){
            
            if(fromWhichPlayer == 1){
                if(this.column+1 >= 0 && this.column+1<= 8){
                    board.removeTroop(this.row, this.column);
                    this.column++;
                    verifyBuff();
                    verifyFire();
                    board.addTroop(this, this.row, this.column);
                } else{
                    attacking = true;
                }
            } else{
                if(this.column-1 >= 1 && this.column-1<= 9){
                    board.removeTroop(this.row, this.column);
                    this.column--;
                    verifyBuff();
                    verifyFire();
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
                board.getCastle(2).damageCastle(this.attack);
            } else {
                ArrayList<Troop> enemyTroopsFound = new ArrayList<>();
                enemyTroopsFound = verifyRange();
                for(Troop enemyTroop : enemyTroopsFound){
                    enemyTroop.damageTroop(this.attack);
                }
            }
        } else {
            if(column == 1){
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

}

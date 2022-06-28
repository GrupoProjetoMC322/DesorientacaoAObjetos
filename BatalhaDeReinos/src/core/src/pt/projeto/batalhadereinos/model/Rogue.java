package pt.projeto.batalhadereinos.model;

import java.util.ArrayList;

public class Rogue extends Troop{
    public Rogue(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 2, 3, 3, 1, 1, "Rogue", fromWhichPlayer);
    }

    public void verifyMap(){
        if(board.getMap().equals("Field") ||board.getMap().equals("Mist") || board.getMap().equals("Volcano")){
            this.attack = 3;
        } else{
            this.attack = 6;
        }
    }

    public ArrayList<Troop> verifyRange(){
        ArrayList<Troop> enemyTroopInRange = new ArrayList<>();
            for(int i = -1; i<=range;i++){

                boolean troopNotNull = board.getTroop(row, column+i) != null;
                boolean foundTroopFromOtherPlayer = troopNotNull && (board.getTroop(row, column+i).getFromWhichPlayer() != this.fromWhichPlayer);
                
                if(foundTroopFromOtherPlayer){
                    enemyTroopInRange.add(board.getTroop(row, column+i));
                }
            }

        
        return enemyTroopInRange;
    }

    public boolean move(){

        verifyMap();

        boolean attacking = false;
        ArrayList<Troop> enemyTroopsFound = new ArrayList<>();

        // Verify before movement
        enemyTroopsFound = verifyRange();
        if(!enemyTroopsFound.isEmpty()){
            if(this.fromWhichPlayer == 1){
                if(this.column+2 <= 8 && board.getTroop(this.row, this.column+2) == null){
                    board.removeTroop(this.row, this.column);
                    this.column+=2;
                    verifyBuff();
                    verifyFire();
                    board.addTroop(this, this.row, this.column);
                }
            } else {
                if(this.column-2 >= 1 && board.getTroop(this.row, this.column-2) == null){
                    board.removeTroop(this.row, this.column);
                    this.column-=2;
                    verifyBuff();
                    verifyFire();
                    board.addTroop(this, this.row, this.column);
                }
            }
            
            enemyTroopsFound = verifyRange();
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
                if(this.fromWhichPlayer == 1){
                    if(this.column+2 <= 8 && board.getTroop(this.row, this.column+2) == null){
                        board.removeTroop(this.row, this.column);
                        this.column+=2;
                        verifyBuff();
                        verifyFire();
                        board.addTroop(this, this.row, this.column);
                    }
                } else {
                    if(this.column-2 >= 1 && board.getTroop(this.row, this.column-2) == null){
                        board.removeTroop(this.row, this.column);
                        this.column-=2;
                        verifyBuff();
                        verifyFire();
                        board.addTroop(this, this.row, this.column);
                    }
                }
                
                enemyTroopsFound = verifyRange();

                attacking = true;
                break;
            }
        }
        
        return attacking;
    }

    public void attack(){
        // Ataque dobrado pelas costas
        if(fromWhichPlayer == 1){
            if(column == 8){
                board.getCastle(2).damageCastle(this.attack);
            } else {
                ArrayList<Troop> enemyTroopsFound = new ArrayList<>();
                enemyTroopsFound = verifyRange();
                for(Troop enemyTroop : enemyTroopsFound){
                    if(enemyTroop.getCollumn() == this.column - 1){
                        enemyTroop.damageTroop(this.attack * 2);
                    } else {
                        enemyTroop.damageTroop(this.attack);
                    }
                }
            }
        } else {
            if(column == 1){
                board.getCastle(1).damageCastle(this.attack);
            } else{
                ArrayList<Troop> enemyTroopsFound = new ArrayList<>();
                enemyTroopsFound = verifyRange();
                for(Troop enemyTroop : enemyTroopsFound){
                    if(enemyTroop.getCollumn() == this.column + 1){
                        enemyTroop.damageTroop(this.attack * 2);
                    } else {
                        enemyTroop.damageTroop(this.attack);
                    }
                }
            }
        }
    }


}

package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.model.Archer;
import pt.projeto.batalhadereinos.model.Barrier;
import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Knight;
import pt.projeto.batalhadereinos.model.Mage;
import pt.projeto.batalhadereinos.model.Rogue;
import pt.projeto.batalhadereinos.model.Soldier;
import pt.projeto.batalhadereinos.model.Troop;

public class TroopCreatorController {
    private String selectedTroopByPlayer1 = "None";
    private String selectedTroopByPlayer2 = "None";
    private Board board;

    public TroopCreatorController(Board board){
        this.board = board;
    }

    public void setSelectedTroop(String selectedTroop, int numberOfPlayer){
        if(numberOfPlayer == 1){
            this.selectedTroopByPlayer1 = selectedTroop;
        } else{ 
            this.selectedTroopByPlayer2 = selectedTroop;
        }
        
    }

    public String getSelectedTroop(int numberOfPlayer){
        String troop;
        if(numberOfPlayer == 1){
           troop = this.selectedTroopByPlayer1;
        } else{ 
           troop = this.selectedTroopByPlayer2;
        }
        return troop;
    }

    public Troop placeTroop(int row, int column,int currentPlayer, int currentPlayerCoins){
        Troop troop = null;
        String selectedTroop;

        if(currentPlayer == 1){
            selectedTroop = this.selectedTroopByPlayer1;
        } else {
            selectedTroop = this.selectedTroopByPlayer2;
        }

        if(selectedTroop.equals("Mage")){
            troop = new Mage(board,selectedTroop,row,column,currentPlayer);
        } else if(selectedTroop.equals("Barrier")){
            troop = new Barrier(board,selectedTroop,row,column,currentPlayer);
        }else if(selectedTroop.equals("Rogue")){
            troop = new Rogue(board,selectedTroop,row,column,currentPlayer);
        }else if(selectedTroop.equals("Knight")){
            troop = new Knight(board,selectedTroop,row,column,currentPlayer);
        } else if(selectedTroop.equals("Archer")){
            troop = new Archer(board,selectedTroop,row,column,currentPlayer);
        }else if(selectedTroop.equals("Soldier")){
            troop = new Soldier(board,selectedTroop,row,column,currentPlayer);
        }
        
        if((troop != null) && (currentPlayerCoins < troop.getCost() || board.getTroop(row, column) != null)){
            troop = null;
        } else {
            board.addTroop(troop, row, column);
        }

        return troop;
    }
}

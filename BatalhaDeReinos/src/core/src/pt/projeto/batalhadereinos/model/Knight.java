package pt.projeto.batalhadereinos.model;

public class Knight extends Troop{
    public Knight(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 6, 4, 3, 1, 2, "Knight", fromWhichPlayer);
    }

    public void verifyMap(){
        if(board.getMap().equals("Field") ||board.getMap().equals("Mist") || board.getMap().equals("Volcano")){
            this.speed = 2;
        } else{
            this.speed = 1;
        }
    }
}

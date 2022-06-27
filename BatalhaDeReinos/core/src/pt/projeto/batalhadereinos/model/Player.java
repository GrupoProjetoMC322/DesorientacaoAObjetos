package pt.projeto.batalhadereinos.model;

public class Player implements IPlayerObserver{
    private Board board;
    private String name;
    private int coins;
    private Castle base;

    public Player(int num, Board board, String name){
        this.board = board;
        this.name = name;
        this.coins = 5;
        this.base = board.getCastle(num);
    } 

    public String getName(){
        return name;
    }
    public int getCoins(){
        return coins;
    }

    public Castle getCastle(){
        return base;
    }

    public void gainCoins(int number){
        this.coins += number;
        if(this.coins > 20){
            this.coins = 20;
        }
    }
    
    public void payCoins(int number){
        this.coins -= number;
    }
}

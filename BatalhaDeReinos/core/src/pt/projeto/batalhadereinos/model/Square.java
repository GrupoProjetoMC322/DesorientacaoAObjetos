package pt.projeto.batalhadereinos.model;

public class Square{
    private int row;
    private int column;
    private Troop troop;
    private Buff buff;
    private boolean fire;

    public Square(String graphicAdress,int row, int column){
        this.row = row;
        this.column = column;
        this.troop = null;
        this.buff = null;
        this.fire = false;
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
    
    public void setFire(boolean isOnFire){
        this.fire = isOnFire;
        if(isOnFire){
            this.buff = null;
        }   
    }

    public boolean getFire(){
        return this.fire;
    }
}

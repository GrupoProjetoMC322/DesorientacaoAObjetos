package pt.projeto.batalhadereinos.model;

public class Castle {
    private int health;

    public Castle(){
        this.health = 30;
    }

    public int getHealth(){
        return health;
    }

    public void damageCastle(int damage){
        this.health -= damage;
    }
}

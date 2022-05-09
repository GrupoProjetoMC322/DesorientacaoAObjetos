package pt.c40task.l05wumpus;

public class MontadorCaverna {
    private Caverna caverna;

    public MontadorCaverna(){
        caverna = new Caverna(criarSalas());
    }
    
    public Caverna receberCaverna(String[][] salas) {
    	return this.caverna;
    }
    
    public Sala[][] criarSalas(){
    	return new Sala[4][4];
    }
}

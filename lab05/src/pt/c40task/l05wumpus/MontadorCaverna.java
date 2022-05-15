package pt.c40task.l05wumpus;

public class MontadorCaverna {
    private Caverna caverna;
	private int numWumpus = 0;
	private int numBuracos = 0;
	private int numOuro = 0;
	private int numHeroi = 0;

    public MontadorCaverna(){
        caverna = new Caverna(criarSalas());
    }
    
    public Caverna montarCaverna(String[][] cave, ControleJogo controle) {
    	
    	for(int i = 0; i < 16; i++) {
    		int linha = Integer.parseInt(cave[i][0]) - 1;
    		int coluna = Integer.parseInt(cave[i][1]) - 1;
    		String componente = cave[i][2];
    		
    		if(componente.equals("P")) {
    			Heroi heroi = new Heroi(this.caverna, linha, coluna);
				controle.setHeroi(heroi);
    			heroi.incluiNaCaverna();
				numHeroi++;
    		} else if(componente.equals("W")) {
    			Componente wumpus = new Wumpus(this.caverna, linha, coluna);
    			wumpus.incluiNaCaverna();
				numWumpus++;
    		} else if(componente.equals("B")) {
    			Componente buraco = new Buraco(this.caverna, linha, coluna);
    			buraco.incluiNaCaverna();
				numBuracos++;
    		} else if(componente.equals("O")) {
    			Componente ouro = new Ouro(this.caverna, linha, coluna);
    			ouro.incluiNaCaverna();
				numOuro++;
    		}
    	}
    	
    	return this.caverna;
    }
    
    public Sala[][] criarSalas(){
    	Sala[][] salas = new Sala[4][4];
    	for(int i = 0; i < 4; i++) {
    		for(int j = 0; j < 4; j++) {
    			salas[i][j] = new Sala();
    		}
    	}
    	return salas;
    }
}

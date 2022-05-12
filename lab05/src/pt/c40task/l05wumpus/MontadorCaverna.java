package pt.c40task.l05wumpus;

public class MontadorCaverna {
    private Caverna caverna;

    public MontadorCaverna(){
        caverna = new Caverna(criarSalas());
    }
    
    public Caverna montarCaverna(String[][] cave) {
    	
    	for(int i = 0;i<16;i++) {
    		int linha = Integer.parseInt(cave[i][0]) - 1;
    		int coluna = Integer.parseInt(cave[i][1]) - 1;
    		String componente = cave[i][2];
    		
    		if(componente.equals("P")) {
    			Componente heroi = new Heroi(this.caverna,linha,coluna,2);
    			heroi.incluiNaCaverna();
    		} else if(componente.equals("W")) {
    			Componente wumpus = new Heroi(this.caverna,linha,coluna,3);
    			wumpus.incluiNaCaverna();
    		} else if(componente.equals("B")) {
    			Componente buraco = new Heroi(this.caverna,linha,coluna,3);
    			buraco.incluiNaCaverna();
    		} else if(componente.equals("O")) {
    			Componente ouro = new Heroi(this.caverna,linha,coluna,3);
    			ouro.incluiNaCaverna();
    		}
    	}
    	
    	return this.caverna;
    }
    
    public Sala[][] criarSalas(){
    	Sala[][] salas = new Sala[4][4];
    	for(int i = 0;i<4;i++) {
    		for(int j = 0;j<4;j++) {
    			salas[i][j] = new Sala();
    		}
    	}
    	return salas;
    }
}

package pt.c40task.l05wumpus;

public class MontadorCaverna {
    private Caverna caverna;
	private boolean sucesso = true;
	private String errorMessage = "";
	private int numWumpus = 0;
	private int numBuracos = 0;
	private int numOuro = 0;
	private int numHeroi = 0;

    public MontadorCaverna(){
        caverna = new Caverna(criarSalas());
    }
    
    public Caverna montarCaverna(String[][] cave, ControleJogo controle) {
    	
    	for (int i = 0; i < 16; i++) {
    		int linha = Integer.parseInt(cave[i][0]) - 1;
    		int coluna = Integer.parseInt(cave[i][1]) - 1;
    		String componente = cave[i][2];
    		
			if (!errorMessage.equals("")) {
				break;
			} else {
				// Instanciando elementos e adicionando
				if(componente.equals("P")) {
					if(numHeroi > 0 || linha != 0 || coluna != 0){
						errorMessage = "Erro ao montar caverna: Heroi na posicao invalida";
					} else {
						Heroi heroi = new Heroi(this.caverna, linha, coluna);
						controle.setHeroi(heroi);
						errorMessage = heroi.incluiNaCaverna();
						numHeroi++;
					}
				} else if(componente.equals("W")) {
					if(numWumpus > 0){
						errorMessage = "Erro ao montar caverna: Mais de 1 Wumpus";	
					} else{
						Componente wumpus = new Wumpus(this.caverna, linha, coluna);
						errorMessage = wumpus.incluiNaCaverna();
						numWumpus++;
					}
				} else if(componente.equals("B")) {
					if(numBuracos > 3){
						errorMessage = "Erro ao montar caverna: Mais de 3 buracos";
					} else {
						Componente buraco = new Buraco(this.caverna, linha, coluna);
						errorMessage = buraco.incluiNaCaverna();
						numBuracos++;
					}
					
				} else if(componente.equals("O")) {
					if(numOuro > 0){
						errorMessage = "Erro ao montar caverna: Mais de 1 ouro";
					} else {
						Componente ouro = new Ouro(this.caverna, linha, coluna);
						errorMessage = ouro.incluiNaCaverna();
						numOuro++;
					}
				}
			}
			
    	}

		if (errorMessage.equals("")) { // Verificacao Final, apos todo o arquivo ter sido lido
			if(numHeroi < 1){
				errorMessage = "Erro ao montar caverna: Nenhum heroi na caverna";
			}
			if(numBuracos < 2){
				errorMessage = "Erro ao montar caverna: Menos de 2 buracos na caverna";
			}
			if(numWumpus < 1){
				errorMessage = "Erro ao montar caverna: Nenhum Wumpus na caverna";
			}
			if(numOuro < 1){
				errorMessage = "Erro ao montar caverna: Nenhum Ouro na caverna";
			}
		}
		
		if(!errorMessage.equals("")){
			sucesso = false;
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

	public boolean getSucesso(){
		return this.sucesso;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

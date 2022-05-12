package pt.c40task.l05wumpus;

public class Caverna {
    private Sala[][] salas;
    
    public Caverna(Sala[][] salas) {
		this.salas = salas;
	}
	
	public void adicionaComponente(Componente novo) {
		int erro;
		erro = salas[novo.getLinha()][novo.getColuna()].adicionaComponente(novo);
		switch (erro){
		case 1:
			System.out.println("Buraco com Ouro ou Wumpus");
		case 2:
			System.out.println("Ouro com Buraco ou Wumpus");
		case 3:
			System.out.println("Wumpus com Buraco ou Ouro");
		case 4:
			System.out.println("Componente Repetido");
		}
	}
	
	public Sala getSala(int linha, int coluna) {
		return salas[linha][coluna];
	}

	public String toString() {
		String cave = "";
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				cave += salas[i][j].toString();
			}
			if(i != 3){
				cave += "\n";
			}
			
		}
		return cave;
	}
}

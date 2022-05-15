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
			break;
		case 2:
			System.out.println("Ouro com Buraco ou Wumpus");
			break;
		case 3:
			System.out.println("Wumpus com Buraco ou Ouro");
			break;
		case 4:
			System.out.println("Componente Repetido");
			break;
		}
	}

	public void removeComponente(Componente comp) {
		salas[comp.getLinha()][comp.getColuna()].removeComponente(comp);
	}
	
	public Componente[] getComponentes(int linha, int coluna) {
		return salas[linha][coluna].getComponentes();
	}

	public char[][] cavernaChar(){
		char[][] cave = new char[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				cave[i][j] = (salas[i][j].toString()).charAt(0);
			}
		}
		return cave;
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

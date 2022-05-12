package pt.c40task.l05wumpus;

public class Caverna {
    private Sala[][] salas;
    
    public Caverna(Sala[][] salas) {
		this.salas = salas;
	}
	
	public void adicionaComponente(Componente novo) {
		salas[novo.getLinha()][novo.getColuna()].adicionaComponente(novo);
	}

	public String toString() {
		String cave = "";
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				cave += salas[i][j].toString();
			}
			cave += "\n";
		}
		return cave;
	}
}

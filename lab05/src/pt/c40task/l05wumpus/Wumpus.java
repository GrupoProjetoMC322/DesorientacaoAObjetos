package pt.c40task.l05wumpus;

public class Wumpus extends Componente {

	public Wumpus(Caverna caverna, int linha, int coluna) {
		super(caverna, linha, coluna, 4, "W");
		geraFedor();
	}
	
	private void geraFedor() {
		Componente[] fedores = new Componente[4];
		int num_fedores = 0;
		int linha = getLinha();
		int coluna = getColuna();
		Fedor fedor;
		
		if (linha > 0) {
			fedor = new Fedor(caverna, linha-1, coluna);
			caverna.adicionaComponente(fedor);
			fedores[num_fedores] = fedor;
			num_fedores++;
		}
		if (linha < 3) {
			fedor = new Fedor(caverna, linha+1, coluna);
			caverna.adicionaComponente(fedor);
			fedores[num_fedores] = fedor;
			num_fedores++;
		}
		if (coluna > 0) {
			fedor = new Fedor(caverna, linha, coluna-1);
			caverna.adicionaComponente(fedor);
			fedores[num_fedores] = fedor;
			num_fedores++;
		}
		if (coluna < 3) {
			fedor = new Fedor(caverna, linha, coluna+1);
			caverna.adicionaComponente(fedor);
			fedores[num_fedores] = fedor;
			num_fedores++;
		}

		this.setComponentesSecundarios(fedores);
	}

	
}

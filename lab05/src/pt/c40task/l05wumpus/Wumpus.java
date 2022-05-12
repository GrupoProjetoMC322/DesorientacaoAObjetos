package pt.c40task.l05wumpus;

public class Wumpus extends Componente {
	private boolean morto;

	public Wumpus(Caverna caverna, int linha, int coluna) {
		super(caverna, linha, coluna, 4, "W");
		this.morto = false;
		geraFedor();
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		if (!this.morto)
			this.morto = morto;
	}
	
	private void geraFedor() {
		int linha = getLinha();
		int coluna = getColuna();
		Fedor fedor;
		
		if (linha > 0) {
			fedor = new Fedor(caverna, linha-1, coluna);
			caverna.adicionaComponente(fedor);
		}
		if (linha < 3) {
			fedor = new Fedor(caverna, linha+1, coluna);
			caverna.adicionaComponente(fedor);
		}
		if (coluna > 0) {
			fedor = new Fedor(caverna, linha, coluna-1);
			caverna.adicionaComponente(fedor);
		}
		if (coluna < 3) {
			fedor = new Fedor(caverna, linha, coluna+1);
			caverna.adicionaComponente(fedor);
		}
	}

}

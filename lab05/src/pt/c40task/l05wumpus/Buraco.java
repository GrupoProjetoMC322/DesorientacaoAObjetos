package pt.c40task.l05wumpus;

public class Buraco extends Componente {

	public Buraco(Caverna caverna, int linha, int coluna) {
		super(caverna, linha, coluna, 4, "B");
		geraBrisa();
	}
	
	private void geraBrisa() {
		Componente[] brisas = new Componente[4];
		int num_brisas = 0;
		int linha = getLinha();
		int coluna = getColuna();
		Brisa brisa;
		
		if (linha > 0) {
			brisa = new Brisa(caverna, linha-1, coluna);
			caverna.adicionaComponente(brisa);
			brisas[num_brisas] = brisa;
			num_brisas++;
		}
		if (linha < 3) {
			brisa = new Brisa(caverna, linha+1, coluna);
			caverna.adicionaComponente(brisa);
			brisas[num_brisas] = brisa;
			num_brisas++;
		}
		if (coluna > 0) {
			brisa = new Brisa(caverna, linha, coluna-1);
			caverna.adicionaComponente(brisa);
			brisas[num_brisas] = brisa;
			num_brisas++;
		}
		if (coluna < 3) {
			brisa = new Brisa(caverna, linha, coluna+1);
			caverna.adicionaComponente(brisa);
			brisas[num_brisas] = brisa;
			num_brisas++;
		}

		this.setComponentesSecundarios(brisas);
	}

}

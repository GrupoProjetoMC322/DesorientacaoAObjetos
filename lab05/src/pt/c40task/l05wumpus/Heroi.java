package pt.c40task.l05wumpus;

public class Heroi extends Componente {
    private int numFlechas;
    private boolean flechaEquipada;
    private boolean vivo;
	private boolean ouroCapturado;
    
	public Heroi(Caverna caverna, int linha, int coluna) {
		super(caverna, linha, coluna, 3, "P");
		this.numFlechas = 1;
		this.flechaEquipada = false;
		this.vivo = true;
		this.ouroCapturado = false;
	}

	public int getNumFlechas() {
		return numFlechas;
	}

	public void setNumFlechas(int numFlechas) {
		this.numFlechas = numFlechas;
	}

	public boolean getFlechaEquipada() {
		return flechaEquipada;
	}

	public void setFlechaEquipada(boolean flechaEquipada) {
		if (getNumFlechas() > 0)
			this.flechaEquipada = flechaEquipada;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		if (this.vivo)
			this.vivo = vivo;
	}

	public boolean getOuroCapturado() {
		return ouroCapturado;
	}

	public void setOuroCapturado(boolean ouroCapturado) {
		if (!getOuroCapturado())
			this.ouroCapturado = ouroCapturado;
	}
	
	public void capturarOuro() {
		Componente[] comp = caverna.getComponentes(getLinha(), getColuna());
		for (int i = 0; i < 4 && comp[i] != null; i++)
			if (comp[i].getTipo().equals("O")) {  // verifica se o ouro está naquela sala
				caverna.removeComponente(comp[i]);
				ouroCapturado = true;
			}
	}
	
	private int atiraNoWumpus(Componente wumpus) {
		int status;
		double probabilidade = Math.random();

		if (probabilidade > 0.5) {  // mata o Wumpus
			status = 3;
			Componente fedores[] = wumpus.getComponentesSecundarios();

			for (int i = 0; i < 4 && fedores[i] != null ; i++){
				caverna.removeComponente(fedores[i]);
			}
			caverna.removeComponente(wumpus);	

		} else { // morre
			status = 2;
			setVivo(false);
		}
		return status;
	}
	
	public int mover(String comando){
		int status = -1;
		/* STATUS:
		 * 0 - se a movimentação ocorreu com sucesso e o herói não atirou a flecha
		 * 1 - se a movimentação ocorreu com sucesso e o herói atirou a flecha, sem matar o Wumpus
		 * 2 - se o herói morreu sem atirar a flecha
		 * 3 - se o herói morreu e atirou a flecha ou herói mata o Wumpus sem contar o gasto de flecha 
		 * 4 - se a movimentação ocorreu com sucesso e o herói matou o Wumpus
		 * 5 - se o herói sai da caverna sem atirar a flecha
		 * 6 - se o herói sai da caverna atirando a flecha
		 */

		 // Remove o herói da sala onde ele estava antes de se mover
		caverna.removeComponente(this);
		
		// Atualiza a posição do herói
		if (comando.equals("s") && getLinha() < 3){
			setLinha(getLinha() + 1);
			status = 0;
		}
		else if (comando.equals("w") && getLinha() > 0){
			setLinha(getLinha() - 1);
			status = 0;
		}
		else if (comando.equals("a") && getColuna() > 0){
			setColuna(getColuna() - 1);
			status = 0;
		}
		else if (comando.equals("d") && getColuna() < 3){
			setColuna(getColuna() + 1);
			status = 0;
		}
		caverna.adicionaComponente(this);
		
		// verifica se o herói cai em um buraco ou encontra o Wumpus
		Componente comp[] = caverna.getComponentes(getLinha(), getColuna());
		for (int i = 0; i < 4 && comp[i] != null; i++) {
			if (comp[i].getTipo().equals("B")) {	// cai no buraco
				setVivo(false);
				status = 2;
			}
			else if (comp[i].getTipo().equals("W"))  // encontra o Wumpus
				if (getFlechaEquipada())
					status = atiraNoWumpus(comp[i]);
				else {
					status = 2;
					setVivo(false);
				}
		}

		if (getLinha() == 0 && getColuna() == 0 && ouroCapturado == true) {
			// verifica se o herói saiu da caverna com sucesso
			status = 5;
		}

		if (getFlechaEquipada()) {
			// contabiliza o gasto da flecha
			status++;
			setFlechaEquipada(false);
			setNumFlechas(0);
		}

		return status;
	}

}

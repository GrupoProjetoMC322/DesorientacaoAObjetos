package pt.c40task.l05wumpus;

public class Heroi extends Componente {
    private int numFlechas;
    private boolean flechaEquipada;
    private boolean vivo;
    
	public Heroi(Caverna caverna, int linha, int coluna) {
		super(caverna, linha, coluna, 3, "P");
		this.numFlechas = 1;
		this.flechaEquipada = false;
		this.vivo = true;
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
		this.flechaEquipada = flechaEquipada;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		if (this.vivo)
			this.vivo = vivo;
	}
	
	/* TODO
    // capturarOuro()
    */
	
	private void atirarFlecha() {
		
	}
	
	public void mover(String comando){
		// Remove o herói da sala onde ele estava antes de se mover
		Sala salaAtual = caverna.getSala(getLinha(), getColuna());
		salaAtual.removeComponente(this);
		
		// Atualiza a posição do herói
		if (comando.equals("w") && getLinha() > 0)
			setLinha(getLinha() + 1);
		else if (comando.equals("s") && getLinha() < 3)
			setLinha(getLinha() - 1);
		else if (comando.equals("a") && getColuna() > 0)
			setColuna(getColuna() - 1);
		else if (comando.equals("d") && getColuna() < 3)
			setColuna(getColuna() + 1);
		caverna.adicionaComponente(this);
		
		salaAtual = caverna.getSala(getLinha(), getColuna());
		Componente comp[] = salaAtual.getComponentes();
		for (int i = 0; i < 4 && comp[i] != null; i++) {
			if (comp[i].getTipo().equals("B"))	// cai no buraco
				setVivo(false);
			else if (comp[i].getTipo().equals("W"))  // encontra o Wumpus
				if (getFlechaEquipada())
					atirarFlecha();
				else
					setVivo(false);
		}
	}

}

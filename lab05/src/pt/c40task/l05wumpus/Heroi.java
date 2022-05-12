package pt.c40task.l05wumpus;

public class Heroi extends Componente {
    private int numFlechas;
    private boolean flechaEquipada;
    
	public Heroi(Caverna caverna, int linha, int coluna, int prioridade) {
		super(caverna, linha, coluna, prioridade,"P");
		this.numFlechas = 1;
		this.flechaEquipada = false;
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

	/* TODO
    // mover()   remover e adicionar em outra sala
     * caverna[linha-1][posicao-1].getComponentes();
    // equiparFlecha()
    // atirarFlecha()
    // capturarOuro()
    */
	
	public void mover(String comando){
		this.caverna.salas[this.getLinha()][this.getColuna()].removeComponente(this);
		if(comando.equals("d")) {
			if(this.getLinha() == 4) {
				
			} else {
				this.setLinha(this.getLinha()+1);
				this.incluiNaCaverna();
			}
		}
	}
	
	public String toString() {
		return this.getTipo();
	}

}

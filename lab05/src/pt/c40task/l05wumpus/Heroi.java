package pt.c40task.l05wumpus;

public class Heroi extends Componente {
    private int numFlechas;
    private boolean flechaEquipada;
    
	public Heroi(Caverna caverna, int linha, int coluna, int prioridade) {
		super(caverna, linha, coluna, prioridade);
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
    // mover()
     * caverna[linha-1][posicao-1].getComponentes();
    // equiparFlecha()
    // atirarFlecha()
    // capturarOuro()
    */
	
	public String toString() {
		return "P";
	}

}

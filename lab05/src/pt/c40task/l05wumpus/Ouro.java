package pt.c40task.l05wumpus;

public class Ouro extends Componente {
	private boolean coletado;

	public Ouro(Caverna caverna, int linha, int coluna) {
		super(caverna, linha, coluna, 4, "O");
		this.coletado = false;
	}

	public boolean isColetado() {
		return coletado;
	}

	public void setColetado(boolean coletado) {
		if (!this.coletado)
			this.coletado = coletado;
	}

}

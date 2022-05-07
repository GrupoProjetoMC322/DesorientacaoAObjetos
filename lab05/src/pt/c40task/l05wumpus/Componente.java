package pt.c40task.l05wumpus;

public class Componente {
    private Caverna caverna;
    private int linha, coluna;
    private int prioridade;
    
	public Componente(Caverna caverna, int linha, int coluna, int prioridade) {
		this.caverna = caverna;
		incluiNaCaverna();
		this.linha = linha;
		this.coluna = coluna;
		this.prioridade = prioridade;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getPrioridade() {
		return prioridade;
	}
	
    protected void incluiNaCaverna() {
    	caverna.adicionaComponente(this);
    }
    
    public String toString() {
    	return "";
    }
}

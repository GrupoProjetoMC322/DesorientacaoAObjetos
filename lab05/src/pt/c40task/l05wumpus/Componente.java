package pt.c40task.l05wumpus;

public class Componente {
    protected Caverna caverna;
    private int linha, coluna;
    private int prioridade;
    private String tipo;
    
	public Componente(Caverna caverna, int linha, int coluna, int prioridade, String tipo) {
		this.caverna = caverna;
		this.linha = linha;
		this.coluna = coluna;
		this.prioridade = prioridade;
		this.tipo = tipo;
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
	
	public String getTipo() {
		return tipo;
	}
	
    public void incluiNaCaverna() {
    	caverna.adicionaComponente(this);
    }
    
    public String toString() {
    	return this.getTipo();
    }
}

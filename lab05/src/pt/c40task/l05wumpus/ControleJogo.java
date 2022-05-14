package pt.c40task.l05wumpus;

public class ControleJogo {
    private Heroi heroi;
    private int pontuacao;
    
	public ControleJogo() {
		this.heroi = new Heroi(caverna, 1, 1);
		this.pontuacao = 0;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public void executarAcao(String comando) {
		
	}

}

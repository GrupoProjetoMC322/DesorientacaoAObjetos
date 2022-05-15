package pt.c40task.l05wumpus;

public class ControleJogo {
    private Heroi heroi;
	private String jogador;
    private int pontuacao;
	private char status;
    
	public ControleJogo() {
		this.jogador = null;
		this.heroi = null;
		this.pontuacao = 0;
		this.status = 'x';
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	public void setJogador(String jogador) {
		this.jogador = jogador;
	}

	public String getJogador() {
		return jogador;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public void executarAcao(String comando) {
		if (comando.equals("w") || comando.equals("s") || comando.equals("a") || comando.equals("d")) {
			switch (heroi.mover(comando)) {
				case 0:
					pontuacao -= 15;
					break;
				case 1:
					pontuacao -= 115;
					break;
				case 2:	 // se o herói morreu sem atirar a flecha
					pontuacao -= 1015;
					setStatus('n');
					break;
				case 3:
					pontuacao -= 1115;
					setStatus('n');
					break;
				case 4:
					pontuacao += 385;
					break;
				case 5:
					pontuacao += 985;
					setStatus('w');
					break;
				case 6:
					pontuacao += 885;
					setStatus('w');
					break;
			}
		}
		else if (comando.equals("k"))
			heroi.setFlechaEquipada(true);
		else if (comando.equals("c")) {
			heroi.capturarOuro();
		}
		else
			System.out.println("Comando inválido!");
	}

}

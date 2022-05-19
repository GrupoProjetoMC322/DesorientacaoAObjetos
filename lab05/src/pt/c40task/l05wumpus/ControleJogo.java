package pt.c40task.l05wumpus;

import java.util.ArrayList;

public class ControleJogo {
    private Heroi heroi;
	private String jogador;
    private int pontuacao;
	private char status;
	final Resposta[] respostas =  {
		new Resposta(-15, "O heroi se moveu"),
		new Resposta(-100, "atirou a flecha"),
		new Resposta(-1000, "morreu"),
		new Resposta(500, "matou o Wumpus"),
		new Resposta(0, "encontrou o ouro"),
		new Resposta(0, "O heroi capturou o ouro."),
		new Resposta(0, "O heroi preparou a flecha."),
		new Resposta(0, "sentiu uma leve brisa"),
		new Resposta(0, "sentiu fedor"),
		new Resposta(1000, "saiu vitorioso da caverna")
	};
    
	public ControleJogo() {
		this.jogador = null;
		this.heroi = null;
		this.pontuacao = 0;
		this.status = 'P';
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

	public String executarAcao(String comando) {
		String mensagem = "";
		ArrayList<Integer> retorno = new ArrayList<>(); 
		
		if ((comando.equals("w") && heroi.getLinha() > 0)|| (comando.equals("a") && heroi.getColuna() > 0) 
			|| (comando.equals("s") && heroi.getLinha() < 3) || (comando.equals("d") && heroi.getColuna() < 3)) {
			retorno = heroi.mover(comando);
			for (int i = 0; i < retorno.size(); i++) {
				int ret = retorno.get(i);
				pontuacao += respostas[ret].getScore();
				mensagem += respostas[ret].getMensagem();
				if (i < retorno.size() - 2){
					mensagem += ", ";
				} else if(i == retorno.size() - 2){
					mensagem += " e ";
				}else {
					mensagem += ".";
				}

				if (ret == 2)
					this.status = 'L';
				else if (ret == 9)
					this.status = 'W';
			}
		}
		else if (comando.equals("k")) {
			boolean sucesso = heroi.setFlechaEquipada(true);
			if (sucesso)
				mensagem = respostas[6].getMensagem();
			else
				mensagem = "Erro: Nao ha flechas restantes!";
		}
		else if (comando.equals("c")) {
			boolean sucesso = heroi.capturarOuro();
			if (sucesso)
				mensagem = respostas[5].getMensagem();
			else
				mensagem = "Erro: Nao ha ouro nessa sala!";
		}
		else
			mensagem = "Erro: Jogada invalida!";

		return mensagem; 
	}

}

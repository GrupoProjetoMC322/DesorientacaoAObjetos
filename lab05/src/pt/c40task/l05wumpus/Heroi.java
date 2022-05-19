package pt.c40task.l05wumpus;

import java.util.ArrayList;
import java.util.Collections;

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

	public boolean setFlechaEquipada(boolean flechaEquipada) {
		boolean sucesso = false;
		if (getNumFlechas() > 0) {
			this.flechaEquipada = flechaEquipada;
			sucesso = true;
		}
		return sucesso;
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
	
	public boolean capturarOuro() {
		boolean sucesso = false;
		Componente[] comp = caverna.getComponentes(getLinha(), getColuna());
		for (int i = 0; i < 4 && comp[i] != null; i++)
			if (comp[i].getTipo().equals("O")) {  // verifica se o ouro está naquela sala
				caverna.removeComponente(comp[i]);
				ouroCapturado = true;
				sucesso = true;
			}
		return sucesso;
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
	
	public ArrayList<Integer> mover(String comando){
		ArrayList<Integer> resposta = new ArrayList<>();
		/* STATUS:
		 * 0 - o heroi apenas se movimenta
		 * 1 - o heroi atira a flecha
		 * 2 - o heroi morre, seja caindo em um buraco ou morto pelo Wumpus
		 * 3 - o heroi mata o Wumpus
		 * 4 - o heroi encontra o ouro
		 * 5 - o heroi captura o ouro
		 * 6 - o heroi prepara a flecha
		 * 7 - o heroi entra em uma sala onde há brisa
		 * 8 - o heroi entra em uma sala onde há fedor
		 * 9 - o heroi sai da caverna (ganha o jogo)
		 */

		 // Remove o herói da sala onde ele estava antes de se mover
		caverna.removeComponente(this);
		
		// Atualiza a posição do herói
		if (comando.equals("s")){
			setLinha(getLinha() + 1);
			resposta.add(0);
		}
		else if (comando.equals("w")){
			setLinha(getLinha() - 1);
			resposta.add(0);
		}
		else if (comando.equals("a")){
			setColuna(getColuna() - 1);
			resposta.add(0);
		}
		else if (comando.equals("d")){
			setColuna(getColuna() + 1);
			resposta.add(0);
		}
		caverna.adicionaComponente(this);

		// verifica quais componentes o herói encontra na sala
		Componente comp[] = caverna.getComponentes(getLinha(), getColuna());
		for (int i = 0; i < 4 && comp[i] != null; i++) {
			if (comp[i].getTipo().equals("b"))
				resposta.add(7);
			else if (comp[i].getTipo().equals("f"))
				resposta.add(8);
			else if (comp[i].getTipo().equals("O"))
				resposta.add(4);
			else if (comp[i].getTipo().equals("B")) {
				setVivo(false);
				resposta.add(2);
			}
			else if (comp[i].getTipo().equals("W"))
				if (getFlechaEquipada())
					resposta.add(atiraNoWumpus(comp[i]));
				else {
					resposta.add(2);
					setVivo(false);
				}
		}

		if (getLinha() == 0 && getColuna() == 0 && ouroCapturado == true) {
			// verifica se o herói saiu da caverna com sucesso
			resposta.add(9);
		}

		if (getFlechaEquipada()) {  // contabiliza o gasto da flecha
			resposta.add(1);
			setFlechaEquipada(false);
			setNumFlechas(0);
		}

		Collections.sort(resposta);	 // ordena as resposta por código
		return resposta;
	}

}

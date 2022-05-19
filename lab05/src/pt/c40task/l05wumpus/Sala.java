package pt.c40task.l05wumpus;

public class Sala {
    private Componente[] componentes;
    private int numComponentes;
    private boolean visitado;
    
    public Sala() {
		this.componentes = new Componente[4];
		this.visitado = false;
		this.numComponentes = 0;
	}
    
    public String adicionaComponente(Componente componente) {
    	int status = 0;
		String erro = "";
    	for(int i = 0;i<this.numComponentes;i++) {
    		// Buraco com Ouro ou Wumpus
    		if(componente.getTipo().equals("B") && (componentes[i].getTipo().equals("O") || componentes[i].getTipo().equals("W"))) {
    			status = 1;
				erro = "Erro ao montar caverna: Buraco com Ouro ou Wumpus na mesma sala\n";
    		} // Ouro com Buraco ou Wumpus
    		else if(componente.getTipo().equals("O") && (componentes[i].getTipo().equals("B") || componentes[i].getTipo().equals("W"))) {
    			status = 2;
				erro = "Erro ao montar caverna: Ouro com Buraco ou Wumpus na mesma sala\n";
    		} // Wumpus com Buraco ou Ouro
    		else if(componente.getTipo().equals("W") && (componentes[i].getTipo().equals("B") || componentes[i].getTipo().equals("O"))) {
    			status = 3;
				erro = "Erro ao montar caverna: Wumpus com Buraco ou Ouro na mesma sala\n";
    		} // Componente Repetido
    		else if(componente.getTipo().equals(componentes[i].getTipo())) {
    			status = 4;
    		}
    	}
    	if(componente.getTipo() == "P") {
    		this.setVisitado(true);
    	}
    	
    	if(status == 0) {
    		componentes[this.numComponentes] = componente;
        	this.numComponentes++;
    	}
    	
    	return erro;
    }
    
    public void removeComponente(Componente componente) {
    	for(int i = 0;i<4;i++){
			if(componentes[i] == componente){
				componentes[i] = null;
			}
		}
		// REORGANIZA
		int next = 1;
		for(int j = 0;j<3;j++){
			if(componentes[j] == null && componentes[next] != null){
				componentes[j] = componentes[next];
				componentes[next] = null;
			}
			next++;
		}
		this.numComponentes--;
    }
    
    public Componente[] getComponentes() {
    	return componentes;
    }
    
    public void setVisitado(boolean visitado) {
    	this.visitado = visitado;
    }

	public String toString() {
    	String chr = "";
    	if (!visitado)
    		chr = "-";
    	else if (componentes[0] == null) // OBS: Sempre tem que deixar a array com os null no final 
    		chr = "#";
    	else {
    		int maior = 0;
    		for (int i = 0;i<componentes.length && componentes[i] != null;i++) {
    			if (componentes[i].getPrioridade() > maior) {
    				chr = componentes[i].toString();
    				maior = componentes[i].getPrioridade();
    			}
    		}
    	}
    	
    	return chr;
    }

}

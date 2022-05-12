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
    
    public int adicionaComponente(Componente componente) {
    	int status = 0;
    	for(int i = 0;i<this.numComponentes;i++) {
    		// Buraco com Ouro ou Wumpus
    		if(componente.getTipo().equals("B") && (componentes[i].getTipo().equals("O") || componentes[i].getTipo().equals("W"))) {
    			status = 1;
    		} // Ouro com Buraco ou Wumpus
    		else if(componente.getTipo().equals("O") && (componentes[i].getTipo().equals("B") || componentes[i].getTipo().equals("W"))) {
    			status = 2;
    		} // Wumpus com Buraco ou Ouro
    		else if(componente.getTipo().equals("W") && (componentes[i].getTipo().equals("B") || componentes[i].getTipo().equals("O"))) {
    			status = 3;
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
    	
    	return status;
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

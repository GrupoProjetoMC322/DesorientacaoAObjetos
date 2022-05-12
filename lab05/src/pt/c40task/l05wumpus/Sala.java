package pt.c40task.l05wumpus;

public class Sala {
    private Componente[] componentes;
    private int num_componentes;
    private boolean visitado;
    
    public Sala() {
		this.componentes = new Componente[4];
		this.visitado = false;
		this.num_componentes = 0;
	}
    
    public void adicionaComponente(Componente componente) {
    	/* TODO:
    	 * verificação se não haverá ouro, Wumpus e/ou buraco na mesma sala
    	 * verificar se o componente já existe (ex.: duas brisas)
    	 * 
    	 * Adicionar tipos a classe componente (Util para verificacao e remocao)
    	 * 
    	 */
    	
    	if(componente.getTipo() == "P") {
    		this.setVisitado(true);
    	}
    	componentes[this.num_componentes] = componente;
    	this.num_componentes++;
    }
    
    public void removeComponente(Componente componente) {
    	
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

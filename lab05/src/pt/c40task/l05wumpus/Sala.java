package pt.c40task.l05wumpus;

public class Sala {
    private Componente[] componentes;
    private boolean visitado;
    
    public Sala() {
		this.componentes = new Componente[4];
		this.visitado = false;
	}
    
    public void adicionaComponente() {
    	/* TODO:
    	 * verificação se não haverá ouro, Wumpus e/ou buraco na mesma sala
    	 * verificar se o componente já existe (ex.: duas brisas)
    	 */
    	
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
    	else if (componentes.length == 0)
    		chr = "#";
    	else {
    		int maior = 0;
    		for (Componente comp : componentes)
    			if (comp.getPrioridade() > maior) {
    				chr = comp.toString();
    				maior = comp.getPrioridade();
    			}
    	}
    	
    	return chr;
    }

}

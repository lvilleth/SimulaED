package estruturas;

/**
 *
 * @author Leonardo Villeth
 */
public class ABP {
    public class No {
	private int dado;
	private No esq;
	private No dir;
        private int nivel;
        private int index;
	
	public No(){
            esq = null;
            dir = null;
	}
	
	public int getDado() {
            return dado;
	}
	public void setDado(int conteudo) {
            this.dado = conteudo;
	}
	
	public No getEsq() {
            return esq;
	}
	public void setEsq(No esq) {
            this.esq = esq;
	}
	
	public No getDir() {
            return dir;
	}
	
	public void setDir(No dir) {
            this.dir = dir;
	}
        
        public int getNivel(){
            return nivel;
        }
        
        public int getIndex(){
            return index;
        }
    }
    
    private No raiz;

    public ABP(){
        raiz = null;
    }

    /** Verifica se a �rvore est� vazia
     * @return  */
    public boolean vazia (){
        return (raiz == null);
    }
    
    private void atualizaNivel(No T, int nivel){
        if(T != null){
            T.nivel = nivel;
            atualizaNivel(T.getEsq(), nivel + 1);
            atualizaNivel(T.getDir(), nivel + 1);
        }
    }

    /**Buscar recursivamente a partir da raiz.
        Retorna o endere�o se o elemento for
        encontrado, caso contr�rio retorna NULL*/
    private No busca(No T, int valor) {
        if (T == null)
            return null;  // Arvore Vazia

        if(T.getDado() == valor)
            return T; 	// Elem. encontrado na raiz

        if (valor < T.getDado())
            return busca(T.getEsq(), valor);
        else
            return busca(T.getDir(), valor);
    }

    /**Buscar um elemento na ABP
            Retorna o endere�o se o elemento for
            encontrado, caso contr�rio retorna NUL
     * @param valor     
     * @return */
    public No busca(int valor) {          
        if (raiz != null) 
            return busca(raiz, valor);

        return null;
    }
    
    // Retorna o No pai do elemento com o valor dado
    public No buscaPai(int valor){
        if(raiz != null)
            return buscaPai(raiz, valor);
        
        return null;
    }
    
    private No buscaPai(No T, int valor){
        if (T == null)
            return null;
        
        No dir = T.getDir();
        No esq = T.getEsq();
        
        if((dir != null) && (dir.getDado() == valor))
            return T; 
        
        if((esq != null) && (esq.getDado() == valor))
            return T; 
        
        if (valor < T.getDado())
            return buscaPai(esq, valor);
        else
            return buscaPai(dir, valor);
    
    }
    
    public int getAltura(){
        return altura(raiz);
    }
    
    private int altura(No T){
        if(T == null)
            return 0;
        else{
            return 1 + Math.max(altura(T.getEsq()), altura(T.getDir()));
        }
    }


    /**Exibe o conte�do de uma �rvore no formato in-ordem
        (preserva a ordena�?o)*/
    private void exibe (No T){
        if (T != null) {
            exibe(T.getEsq());
            System.out.print(" " + T.getDado());
            exibe(T.getDir());
        }
    }

    public void exibe() {
        if (raiz == null)
            System.out.println("Arvore vazia");
        else
            exibe(raiz);
    }

    /**Insere um n� em uma �rvore ABP
        Retorna 1 se a inser�?o for com sucesso.
        Caso contr�rio retorna
     * @param valor
     * @return */
    public boolean insere(int valor){

        No novoNo = new No();
        novoNo.setDado(valor);
        novoNo.setEsq(null);
        novoNo.setDir(null);

        if (raiz == null){ // Arvore vazia
            raiz = novoNo;
            raiz.index = 1;
            return true;
        }        
        
        if(busca(valor) != null){ // valor ja existe na arvore
            return false;
        }

        // Procura a posicao correta pra inserir o novo no
        No aux = raiz;
        No p = null;
        while (aux != null) {
            p = aux;
            if (valor < aux.getDado())
                aux = aux.getEsq();
            else
                aux = aux.getDir();
        }
        
        if(p == null)
            return false;
        
        // Encontrou um n� folha para inserir
        if (valor < p.getDado()){
            novoNo.index = p.getIndex()*2 - 1;
            p.setEsq(novoNo);
        }else{
            novoNo.index = p.getIndex()*2;
            p.setDir(novoNo);
        }
        atualizaNivel(raiz, 0);
        return true;
    }
}

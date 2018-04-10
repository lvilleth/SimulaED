package estruturas;

/**
 *
 * @author Leonardo Villeth
 */
public class ListaSeq {
    
    private int dados[];
    private int nElementos;     
    
    public ListaSeq(){    		
    		nElementos = 0;
    		dados = new int[10];
    }
    
    public ListaSeq(int tam){    		
    		nElementos = 0;
		dados = new int[tam];
    }
    
    public boolean vazia(){        
        return nElementos == 0;
    }
    
    public boolean cheia(){        
        return nElementos == dados.length;
    }	

    public int tamanho(){
        return nElementos;
    }    
    
    public int elemento(int pos) throws Exception{            	
        
        if ((pos > nElementos) || (pos <= 0))
            throw new Exception("Posiçao Invalida");

        return dados[pos-1];
    }

    public int posicao (int valor){                        
        
        for (int i = 0; i < nElementos; i++){            
            if (dados[i] == valor){
                return (i + 1);
            }
        }        

        return -1;
    }
    
    public boolean insere (int pos, int dado){
        
        if (cheia() || (pos > nElementos+1) || (pos <=0)){            
            return false;
        }

        /* Desloca os elementos após pos, uma posicao a
        direita. Isso serve para abrir espaço para insercao
        do novo elemento */
        for (int i = nElementos; i >= pos; i--){
            dados[i] = dados[i-1];
        }

        /* Insere o dado na posicao correta */
        dados[pos - 1] = dado;

        /* Incrementa o numero de elementos na lista */        
        nElementos++;
        return true;
    }
	
    public int remove(int pos) throws Exception{
        int dado;
        if(vazia())
            throw new Exception("Lista vazia.");
        /* Verifica se a posicao eh valida */
        if ((pos > nElementos) || (pos < 1 ))
            throw new Exception("Posiçao invalida.");        

        /* Armazena o dado a ser removido na var "dado" */
        dado = dados[pos-1];

        /* Desloca todos os elementos após 'pos', uma
        posicao a esquerda */
        for (int i = pos - 1; i < nElementos - 1; i++){
            dados[i] = dados[i+1];
        }

        /* Decrementa o numero de elementos na lista */
        nElementos--;
        return dado;
    }
    
}

package estruturas;

/**
 *
 * @author Leonardo Villeth
 */
public class PilhaSeq {
	
    private int dados[]; // Vetor que cont�m os dados da lista 
    private int topo; 
    
    public PilhaSeq(){    		
        dados = new int[10];    		
        topo  = -1;
    }
    
    public PilhaSeq(int n){    		
        dados = new int[n];    	
        topo  = -1;
    }

    /** Verifica se a Pilha est� vazia */
    public boolean vazia(){        
        return (topo == -1);
    }
	
    /** Verifica se a Pilha est� cheia */
    public boolean cheia(){
        return (topo == (dados.length - 1));		
    }
	
    /** Obt�m o tamanho da Pilha*/
    public int tamanho(){
        return topo + 1;
    }
    
    /** Consulta o elemento do topo da Pilha.
        Retorna -1 se a pilha estiver vazia, 
        caso contr�rio retorna o valor que est� no topo da pilha. */
    public int top() throws Exception {
      if (vazia()) 
         throw new Exception("Pilha vazia."); // pilha vazia
 	  
      return dados[topo];
    }
     
    /** Insere um elemento no topo da pilha.
        Retorna false se a pilha estiver cheia. 
        Caso contr�rio retorna true */
    public boolean push(int valor) throws Exception {
        if (cheia()) 
            throw new Exception("Pilha cheia.");  // err: pilha cheia 
 		
        topo++;
        dados[topo] = valor; 
        return true;
    }   

    /** Retira o elemento do topo da pilha.
        Retorna -1 se a pilha estiver vazia. */
    public int pop() throws Exception{
        if (vazia()) 
            throw new Exception("Pilha vazia."); // Pilha vazia
 		
        int valor = dados[topo]; 
        topo--; 
        return valor;
    }
}

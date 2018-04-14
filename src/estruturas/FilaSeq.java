package estruturas;

/**
 *
 * @author KAMINARI
 */
public class FilaSeq {
    private int dados[];
    private int inicio;
    private int fim;
    
    private int nElementos;
    private int tamMax;
    
    public FilaSeq(){
        inicio = 0;
        fim = -1;
        nElementos = 0;
        tamMax = 100;
        dados = new int[tamMax];
    }
    public FilaSeq(int n){
        inicio = 0;
        fim = -1;
        nElementos = 0;
        tamMax = n;
        dados = new int[tamMax];
    }
    
    //VERIFICA SE A FILA ESTÁ VAZIA
    public boolean vazia(){
        if(nElementos == 0)
            return true;
        else
            return false;
    }
    
    //VERIFICA SE A FILA ESTÁ CHEIA
    public boolean cheia(){
        if(nElementos == tamMax)
            return true;
        else
            return false;
    }
    
    //OBTÉM O TAMANHO DA FILA
    public int tamanho(){
        return nElementos;
    }
    
    //CONSULTA O ELEMENTO NO INÍCIO DA FILA
    public int primeiro(){
        if(vazia())
            return -1;
        return dados[inicio];
    }
    
    //INSERE UM ELEMENTO NO FINAL DA FILA
    public boolean insere(int valor){
        if(cheia())
            return false;
        fim = (fim + 1) % tamMax;
        dados[fim] = valor;
        nElementos++;
        return true;
    }
    
    //REMOVE O ELEMENTO DO INICIO DA FILA E RETORNA O VALOR REMOVIDO
    public int remove(){
        if(vazia())
            return -1;
        
        int res = primeiro();
        inicio = (inicio + 1) % tamMax;
        nElementos--;
        return res;
    }
    public int getFim(){
        return fim;
    }
    public int getInicio(){
        return inicio;
    }
    
}

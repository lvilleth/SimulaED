
package estruturas;

/**
 *
 * @author jayme
 */
public class LSE {
    private class No{
        private int conteudo;
        private No proximo;
        
        public No(){
            proximo = null;
        }

        public int getConteudo() {
            return conteudo;
        }

        public void setConteudo(int conteudo) {
            this.conteudo = conteudo;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No proximo) {
            this.proximo = proximo;
        }
    }
    
    
    private No cabeça;
    private int tamanho;

    public void Lista(){
        cabeça = null;
        tamanho = 0;
    }

    public boolean vazia(){
        return tamanho == 0;
    }

    public int getTamanho(){
        return tamanho;
    }

    /*Retorna o conteudo do No passando a posiçao como parametro.*/
    public int elemento(int pos) throws Exception{
        No aux = cabeça;
        int cont = 1;
        if (vazia()) {
            throw new Exception("Lista vazia.");
        }
        if ((pos < 1) || (pos > tamanho)) {
            throw new Exception("Posiçao invalida.");
        }
        while (cont < pos) {
            aux = aux.getProximo();
            cont++;
        }
        return aux.getConteudo();
    }

    /*Retorna a posiçao de um elemento passando o mesmo como parametro.*/
    public int posiçao(int dado) throws Exception{
        int cont = 1;
        No aux;

        if (vazia()) {
            throw new Exception("Lista vazia.");
        }
        aux = cabeça;
        while (aux != null) {
            if (aux.getConteudo() == dado) {
                return cont;
            }
            aux = aux.getProximo();
            cont++;
        }
        return -1;
    }

    /*Insere um elemento numa determinada posiçao da lista retornando true se conseguir.*/
    public boolean insere(int pos, int dado)throws Exception {
        if (vazia() && (pos != 1) || pos < 1 || pos > tamanho + 1) {
            throw new Exception("Posiçao invalida.");
        }
        if (pos == 1) { //Insere no inicio da lista.
            return insereInicioLista(dado);
        }else if (pos == tamanho + 1) { //Insere no fim da lista.
            return insereFimLista(dado);
        }else{
            return insereMeioLista(pos, dado); //Insere no meio.
        }
    }

    public boolean insereInicioLista(int valor){
        No novoNo = new No();

        novoNo.setConteudo(valor);
        novoNo.setProximo(cabeça);
        cabeça = novoNo;
        tamanho++;

        return true;
    }

    public boolean insereMeioLista(int pos, int dado){
        int cont = 1;
        No novoNo = new No();
        novoNo.setConteudo(dado);
        No aux = cabeça;

        while ((cont < pos - 1) && (aux != null)) {
            aux = aux.getProximo();
            cont++;
        }
        if (aux == null) {
            return false;
        }
        novoNo.setProximo(aux.getProximo());
        aux.setProximo(novoNo);
        tamanho++;

        return true;
    }

    public boolean insereFimLista(int dado){
        No novoNo = new No();
        novoNo.setConteudo(dado);
        No aux = cabeça;

        while (aux.getProximo() != null) {
            aux = aux.getProximo();
        }

        novoNo.setProximo(null);
        aux.setProximo(novoNo);
        tamanho++;

        return true;
    }

    public int remove(int pos) throws Exception{
        if (vazia()) {
            throw new Exception("Lista vazia.");
        }
        if(pos == 1){
            return removeInicioLista();
        }else{
            return removeNaLista(pos);
        }
    }

    public int removeInicioLista(){
        No p = cabeça;
        int dado = p.getConteudo();

        cabeça = p.getProximo();
        tamanho--;
        p = null;

        return dado;
    }

    public int removeNaLista(int pos) throws Exception{
        No atual = null, antecessor = null;
        int dado = -1, cont = 1;

        atual = cabeça;
        while ((cont < pos) && (atual != null)) {
            antecessor = atual;
            atual = atual.getProximo();
            cont++;
        }

        if (atual == null || antecessor == null) {
            throw new Exception("Posiçao invalida.");
        }

        dado = atual.getConteudo();
        antecessor.setProximo(atual.getProximo());
        tamanho--;
        atual = null;

        return dado;
    }
}

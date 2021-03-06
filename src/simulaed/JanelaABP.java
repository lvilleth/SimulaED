package simulaed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Leonardo Villeth
 */
public class JanelaABP extends javax.swing.JDialog {
    
    public static ArrayList<Circulo> circulos;
    
    private final ABPCirculo arvore;
    
    private boolean cor = false;
    private final int DELAY = 500;
    
    private final Circulo visitados[];
    private final Icon cores[];
    private int cont = 0;    
    
    public JanelaABP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configBtns();
        
        super.setTitle("Arvore Binaria de Pesquisa");
        super.setLocationRelativeTo(null);        
    
        circulos = new ArrayList<>();
        
        arvore = new ABPCirculo();
        
        visitados = new Circulo[186];
        cores = new Icon[186];
        
        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if(circulos.isEmpty())
                    return;                
                
                Circulo raiz = circulos.get(0);
                int oldx = raiz.getX();                
                raiz.setLocation(canvas.getWidth()/2, raiz.getY());                
                int dx = oldx - raiz.getX();
                
                Circulo c;
                for (int i = 1; i < circulos.size(); i++){
                    c = circulos.get(i);
                    oldx = c.getX();                    
                    c.setLocation(oldx - dx, c.getY());                    
                }
                revalidate();
                repaint();
            }
            
        });
    }
    
    private void configBtns(){
        btnInserir.setMnemonic(KeyEvent.VK_I);
        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor;
                try{
                    valor = Integer.parseInt(txtValor.getText());
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(rootPane, "Valor invalido");
                    return;
                }
                
                if(cor){
                    removeCor();
                    cor = false;
                }
                
                Circulo c = new Circulo();
                c.setValor(valor);                
                
                boolean ok = arvore.insere(c);                
                
                if(!ok)
                    return;
                
                cor = true;
                
                canvas.add(c);
                circulos.add(c);                
                
                canvas.revalidate();
                canvas.repaint();
            }
        });
        
        btnBuscar.setMnemonic(KeyEvent.VK_B);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor;
                try{
                    valor = Integer.parseInt(txtValor.getText());
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(rootPane, "Valor invalido");
                    return;
                }
                
                if(cor){
                    removeCor();
                    cor = false;
                }
                
                Circulo c = new Circulo();
                c.setValor(valor);
                if(arvore.busca(c) != null){
                    cor = true;
                }
            }
        });
        
        btnIn.setMnemonic(KeyEvent.VK_O);
        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cor){
                    removeCor();
                    cor = false;
                }
                
                cont = 0;
                arvore.exibe();

                lblRetorna.setText("");
                
                int max = cont;                
                for (int i = 0; i < max; i++) {                      
                    Timer t1 = new Timer(DELAY*i, new MeuListener(i));
                    t1.setRepeats(false);
                    t1.start();
                }
                
                
            }
        });
    }
    
    private void removeCor(){
        for (Circulo circulo : circulos) {
            if(circulo.getIcon() != Circulo.BORDA_PRETA){
                circulo.setIcon(Circulo.BORDA_PRETA);
            }
        }    
    }    
    
    
    private class MeuListener implements ActionListener{

        private final int index;

        private MeuListener(int index){
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {             
            if(cores[index] == Circulo.BORDA_VERDE){
                String s = lblRetorna.getText();                
                lblRetorna.setText(s.concat("  ").concat(visitados[index].getText()));                
            }
            visitados[index].setIcon(cores[index]);                        
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInserir = new javax.swing.JButton();
        txtValor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        lblRetorna = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        canvas = new CanvasABP();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnInserir.setText("Inserir");

        jLabel1.setText("Valor:");

        btnBuscar.setText("Buscar");

        lblRetorna.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblRetorna.setText("---");

        btnIn.setText("In-ordem");

        jLabel3.setText("Caminhar");

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3))
                    .addComponent(btnIn))
                .addGap(41, 41, 41)
                .addComponent(lblRetorna)
                .addContainerGap(888, Short.MAX_VALUE))
            .addComponent(canvas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInserir)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar))
                    .addComponent(lblRetorna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnInserir;
    private javax.swing.JPanel canvas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblRetorna;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables



/**
 *
 * @author Leonardo Villeth
 */
    public class ABPCirculo {
        public class No {
            private Circulo dado;
            private No esq;
            private No dir;
            private int nivel;
            private int index;

            public No(){
                esq = null;
                dir = null;            
            }

            public Circulo getDado() {
                return dado;
            }
            public void setDado(Circulo c) {
                this.dado = c;
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

        public ABPCirculo(){
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
        private No busca(No T, Circulo valor) {
            if (T == null)
                return null;  // Arvore Vazia
            
            if(T.getDado().getValor() == valor.getValor()){
                T.getDado().setIcon(Circulo.BORDA_AMARELA);
                return T; 	// Elem. encontrado na raiz
            }

            if (valor.getValor() < T.getDado().getValor())
                return busca(T.getEsq(), valor);
            else
                return busca(T.getDir(), valor);
        }

        /**Buscar um elemento na ABP
                Retorna o endere�o se o elemento for
                encontrado, caso contr�rio retorna NUL
         * @param valor     
         * @return */
        public No busca(Circulo valor) {          
            if (raiz != null) 
                return busca(raiz, valor);

            return null;
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
                visitados[cont] = T.getDado();
                cores[cont] = Circulo.BORDA_AMARELA;
                cont++;
                
                // Apaga
                visitados[cont] = T.getDado();
                cores[cont] = Circulo.BORDA_PRETA;
                cont++;
                /////////
                
                //visita
                exibe(T.getEsq());
                
                visitados[cont] = T.getDado();
                cores[cont] = Circulo.BORDA_VERDE;
                cont++;
                
                // Apaga
                visitados[cont] = T.getDado();
                cores[cont] = Circulo.BORDA_PRETA;
                cont++;
                /////////
                
                //processa
                //System.out.print(" " + T.getDado().getValor());

                exibe(T.getDir());                
                
                visitados[cont] = T.getDado();
                cores[cont] = Circulo.BORDA_AMARELA;
                cont++;
                
                
                // Apaga
                visitados[cont] = T.getDado();
                cores[cont] = Circulo.BORDA_PRETA;
                cont++;
                /////////                
                
            }
        }

        public void exibe() {
            if (raiz == null)
                JOptionPane.showMessageDialog(rootPane, "Arvore vazia");
            else
                exibe(raiz);
        }

        /**Insere um n� em uma �rvore ABP
            Retorna 1 se a inser�?o for com sucesso.
            Caso contr�rio retorna
         * @param valor
         * @return */
        public boolean insere(Circulo valor){

            No novoNo = new No();
            novoNo.setDado(valor);
            novoNo.setEsq(null);
            novoNo.setDir(null);

            if (raiz == null){ // Arvore vazia
                raiz = novoNo;
                raiz.index = 1;
                valor.setLocation(canvas.getWidth()/2, 0);
                valor.setIcon(Circulo.BORDA_VERDE);
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
                if (valor.getValor() < aux.getDado().getValor())
                    aux = aux.getEsq();
                else
                    aux = aux.getDir();
            }

            if(p == null)
                return false;        

            int y = p.getDado().getLocation().y;        
            y+= canvas.getHeight()/6;        

            int x = 0;
            switch(p.getNivel() + 1){
                case 1:
                    x = canvas.getWidth()/3;                
                    break;
                case 2:
                    x = canvas.getWidth()/5;
                    break;
                case 3:
                    x = canvas.getWidth()/9;
                    break;    
                case 4:
                    x = canvas.getWidth()/17;
                    break;
            }        
            
            if(p.getNivel() == 4){
                JOptionPane.showMessageDialog(rootPane, "Altura maxima permitida: 4.");
                return false;
            }
            
            // Encontrou um n� folha para inserir
            if (valor.getValor() < p.getDado().getValor()){
                novoNo.index = p.getIndex()*2 - 1;
                p.setEsq(novoNo);
                x *= novoNo.index;            
                valor.setLocation(x, y);
            }else{
                novoNo.index = p.getIndex()*2;            
                p.setDir(novoNo);
                x *= novoNo.index;            
                valor.setLocation(x, y);
            }
            
            valor.setIcon(Circulo.BORDA_VERDE);
            p.getDado().addConexao(valor);
            
            atualizaNivel(raiz, 0);
            return true;
        }
    }    
}

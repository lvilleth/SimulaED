package simulaed;   

import com.sun.glass.events.KeyEvent;
import estruturas.ListaSeq;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Leonardo Villeth
 */
public final class JanelaListaSeq extends javax.swing.JDialog {
    
    ListaSeq lista;
    ArrayList<Bloco> blocos;
    int tamLista;    
    
    Border bordaVerde    = BorderFactory.createLineBorder(Color.GREEN, 2);
    Border bordaVermelha = BorderFactory.createLineBorder(Color.RED, 2);
    Border bordaAmarela  = BorderFactory.createLineBorder(Color.YELLOW, 2);
    Border bordaVazia    = BorderFactory.createEmptyBorder();
    
    boolean cor;
    final int DELAY = 2000;
    
    public JanelaListaSeq(java.awt.Frame parent, boolean modal, int tamanho){
        super(parent, modal);
        
        initComponents();                        
        configBtns();                
        
        lista = new ListaSeq(tamanho);        
        tamLista = tamanho;        
        blocos = new ArrayList<>(tamanho);
        
        setTitle("Lista Sequencial");
        setLocationRelativeTo(null);    
        lblBusca.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        btnRadioGroup.add(radioPos);
        btnRadioGroup.add(radioValor);
        
        //int x  = canvas.getSize().width;
        int y  = canvas.getSize().height;
        //x/=2;
        y/=2;        
        int x = 20;
        y -= canvas.getY();
                
        // Desenha a lista Inicial na tela
        for (int i = 0; i < tamanho; i++) {
            Bloco b = new Bloco();
            b.setValor(0);
            b.setLocation(x + ((b.getSize().width + 5)*i), y);
            canvas.add(b);  
            blocos.add(b);
        }
    }    
    
    private void configBtns(){
        btnInserir.setMnemonic(KeyEvent.VK_I);        
        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                                
                lblBusca.setText("---");
                
                // Se a borda dos blocos ja estiver colorida, remove a borda
                if(cor){
                    removerBorda();
                }
                
                String v = txtValor.getText();
                String s = txtPosicao.getText();
                
                if(s.equals("") || v.equals(""))                    
                    return;
                
                int valor = Integer.parseInt(v);
                int pos = Integer.parseInt(s);
                                
                boolean ok = lista.insere(pos, valor);
                lblResultado.setText(""+ok);
                
                if(ok){                
                    // Arrasta os valores dos blocos para direita
                    Bloco c;                    
                    for (int i = pos; i < lista.tamanho(); i++) {
                        try {
                            c = blocos.get(i);
                            c.setValor(lista.elemento(i+1));
                            c.setBorder(bordaAmarela);                                    
                        } catch (Exception ex) {

                        }
                    }                    
                    ////////////////////////////////////////////////
                                        
                    // Preenche o valor do bloco depois de determinado DELAY                    
                    Bloco b = blocos.get(pos-1);                                                                                
                    Timer t = new Timer(DELAY, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                            b.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            b.setValor(valor);                                                            
                            cor = true;
                        }
                    });
                    t.setRepeats(false);                    
                    if(lista.tamanho() == 1)
                        t.setInitialDelay(0);
                    t.start();                    
                    ////////////////////////////////////////////////
                }
                lblNElementos.setText(""+lista.tamanho());                
            }
        });
    
        btnRemover.setMnemonic(KeyEvent.VK_R);
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblBusca.setText("---");
                
                // Se a borda dos blocos ja estiver colorida, remove a borda
                if(cor){
                    removerBorda();
                }
                
                int valor;                
                String s = txtPosicao.getText();
                if(s.equals(""))
                    return;                                
                
                int pos = Integer.parseInt(s);                
                
                try {
                    valor = lista.remove(pos);                    
                    
                    final Bloco b = blocos.get(pos-1);
                    b.setBorder(bordaVermelha);                       
                    
                    // Arrasta os valores dos blocos para a esquerda depois de determinado DELAY
                    Timer t = new Timer(DELAY, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            b.setBorder(bordaVazia);
                            Bloco c;
                            for (int i = pos-1; i < lista.tamanho(); i++) {
                                c = blocos.get(i);
                                try {
                                    c.setValor(lista.elemento(i+1));
                                    c.setBorder(bordaAmarela);
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                                }
                            }
                            cor = true;
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                    ////////////////////////////////////////////////
                    
                    lblResultado.setText(""+valor);
                    lblNElementos.setText(""+lista.tamanho());
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }
        });
                
        btnBuscar.setMnemonic(KeyEvent.VK_B);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                // Se a borda dos blocos ja estiver colorida, remove a borda
                if(cor){
                    removerBorda();                    
                }
                
                try {
                    if(radioPos.isSelected())                    
                        buscarPosicao();                
                    else if(radioValor.isSelected())
                        buscarValor();                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());                
                }
            }

            private void buscarPosicao() throws Exception {
                int valor;
                int pos;
                String s = txtPosicao.getText();
                if(s.equals(""))
                    return;                                
                pos = Integer.parseInt(s);                
                valor = lista.elemento(pos);                
                
                lblBusca.setText(String.format("%3d",valor));
                //lblBusca.setText(String.format(""+valor));
            }

            private void buscarValor() throws Exception {
                int valor;
                int pos;
                String s = txtValor.getText();
                if(s.equals(""))
                    return;                                
                valor = Integer.parseInt(s);                
                pos = lista.posicao(valor);                                
                lblBusca.setText(String.format("%3d",pos));
                //lblBusca.setText(String.format(""+pos));
            }
        });
    }
    
    private void removerBorda(){
        for (Bloco b : blocos) {
            b.setBorder(bordaVazia);
        }
        cor = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRadioGroup = new javax.swing.ButtonGroup();
        btnInserir = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        canvas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();
        lblelem = new javax.swing.JLabel();
        lblNElementos = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        lblPosicao = new javax.swing.JLabel();
        txtPosicao = new javax.swing.JTextField();
        radioPos = new javax.swing.JRadioButton();
        radioValor = new javax.swing.JRadioButton();
        lblBusca = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnInserir.setText("Inserir");

        btnRemover.setText("Remover");

        btnBuscar.setText("Buscar");

        jLabel1.setText("Removido:");

        lblResultado.setFont(new java.awt.Font("Arimo", 0, 36)); // NOI18N
        lblResultado.setText("---");

        lblelem.setText("N Elementos:");

        lblNElementos.setFont(new java.awt.Font("Arimo", 0, 28)); // NOI18N
        lblNElementos.setText("0");

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(canvasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResultado)
                .addGap(46, 46, 46)
                .addComponent(lblelem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNElementos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(canvasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblelem)
                        .addComponent(lblNElementos))
                    .addGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblResultado)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblValor.setText("Valor:");

        lblPosicao.setText("Posiçao:");

        radioPos.setText("Buscar por Posiçao");

        radioValor.setText("Buscar por Valor");

        lblBusca.setFont(new java.awt.Font("Arimo", 0, 36)); // NOI18N
        lblBusca.setText("---");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lblValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPosicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioValor)
                        .addGap(49, 49, 49)
                        .addComponent(lblBusca))
                    .addComponent(radioPos))
                .addContainerGap(75, Short.MAX_VALUE))
            .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioPos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioValor))
                    .addComponent(lblBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnInserir)
                    .addComponent(btnRemover)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValor)
                    .addComponent(lblPosicao)
                    .addComponent(txtPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(267, 267, 267))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnInserir;
    private javax.swing.ButtonGroup btnRadioGroup;
    private javax.swing.JButton btnRemover;
    private javax.swing.JPanel canvas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JLabel lblNElementos;
    private javax.swing.JLabel lblPosicao;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblelem;
    private javax.swing.JRadioButton radioPos;
    private javax.swing.JRadioButton radioValor;
    private javax.swing.JTextField txtPosicao;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}

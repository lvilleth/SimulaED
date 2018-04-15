package simulaed;   

import com.sun.glass.events.KeyEvent;
import estruturas.FilaSeq;
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
 * @author KAMINARI
 */
public final class JanelaFilaSeq extends javax.swing.JDialog {
    
    FilaSeq fila;
    ArrayList<Bloco> blocos;
    int tamFila;    
    
    Border bordaVerde    = BorderFactory.createLineBorder(Color.GREEN, 2);
    Border bordaVermelha = BorderFactory.createLineBorder(Color.RED, 2);
    Border bordaAmarela  = BorderFactory.createLineBorder(Color.YELLOW, 2);
    Border bordaVazia    = BorderFactory.createEmptyBorder();
    Border bordaCinza    = BorderFactory.createLineBorder(Color.GRAY, 2);
    
    boolean cor;
    final int DELAY = 1500;
    
    public JanelaFilaSeq(java.awt.Frame parent, boolean modal, int tamanho){
        super(parent, modal);
        
        initComponents();                        
        configBtns();                
        
        fila = new FilaSeq(tamanho);
        tamFila = tamanho;
        blocos = new ArrayList<>(tamanho);
        
        setTitle("Fila Sequencial");
        setLocationRelativeTo(null);
        
        //int x  = canvas.getSize().width;
        int y  = canvas.getSize().height;
        //x/=2;
        y/=2;        
        int x = 20;
        y -= canvas.getY();
                
        // Desenha a lista Inicial na tela
        for (int i = 0; i < tamanho; i++) {
            txtInicio.setText(""+fila.getInicio());
            txtFim.setText(""+fila.getFim());
            Bloco b = new Bloco();
            b.setValor(0);
            b.setLocation(x + ((b.getSize().width + 5)*i), y);
            canvas.add(b);  
            blocos.add(b);
            b.setContentAreaFilled(false);
            b.setBorder(bordaCinza);
            b.setEnabled(false);
        }
    }    
    
    private void configBtns(){
        btnInserir.setMnemonic(KeyEvent.VK_I);        
        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //Se a borda dos blocos ja estiver colorida, remove a borda
                if(cor){
                    removerBorda();
                }
                
                String v = txtValor.getText();
                
                if(v.equals(""))
                    return;
                
                boolean ok = false;
                try{
                    int valor = Integer.parseInt(v);
                    ok = fila.insere(valor);
                    if(ok){                        
                        simulaInsercao(valor, DELAY);
                        //txtValor.setText("");
                        lblNElementos.setText(""+fila.tamanho());
                        txtInicio.setText(""+fila.getInicio());
                        txtFim.setText(""+fila.getFim());
                    }
                    else{
                        JOptionPane.showMessageDialog(rootPane,"Fila cheia.");
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(rootPane, "Numero invalido.");                    
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());                                        
                }
            }
        });
    
        btnRemover.setMnemonic(KeyEvent.VK_R);
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Se a borda dos blocos ja estiver colorida, remove a borda
                if(cor){
                    removerBorda();
                }
                if(fila.vazia()){
                    JOptionPane.showMessageDialog(rootPane, "Fila Vazia.");
                    return;
                }
                
                int valor;                                 
                
                try {
                    valor = fila.remove();                    
                    
                    btnInserir.setEnabled(false);
                    btnRemover.setEnabled(false);
                    
                    simulaRemocao(DELAY);
                    
                    lblResultado.setText(""+valor);
                    txtInicio.setText(""+fila.getInicio());
                    txtFim.setText(""+fila.getFim());
                    lblNElementos.setText(""+fila.tamanho());
                    
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Numero invalido.");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }
        });
    }
    
    
    private void simulaRemocao(int mili){
        final Bloco b;
        if(fila.getInicio() == 0)
            b = blocos.get(tamFila-1);
        else
            b = blocos.get(fila.getInicio()-1);
        b.setBorder(bordaVermelha);                       
        
        // Remove a borda depois de determinado DELAY
        Timer t = new Timer(mili, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                
                b.setBorder(bordaVazia);
                
                cor = true;               
                Bloco ultimo;
                if(fila.getInicio() == 0 )
                    ultimo = blocos.get(tamFila-1);
                else
                    ultimo = blocos.get(fila.getInicio()-1);
                
                ultimo.setBorder(bordaCinza);
                ultimo.setEnabled(false);
                ultimo.setContentAreaFilled(false);
                
                btnInserir.setEnabled(true);
                btnRemover.setEnabled(true);
            }
        });
        t.setRepeats(false);
        t.start();    
    }    
    
    private void simulaInsercao(int valor, int mili){        
        preencheBloco(valor, mili, bordaVerde);
    }
    
    private void preencheBloco(int valor, int mili, Border borda){    
        Bloco b = blocos.get(fila.getFim());                                                                                
        Timer t = new Timer(mili, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                            
                b.setBorder(borda);
                b.setValor(valor);
                b.setContentAreaFilled(true);
                b.setEnabled(true);
                cor = true;
            }
        });
        t.setRepeats(false);                    
        if((fila.tamanho() == 1))
            t.setInitialDelay(0);
        t.start();                        
    }
    
    private void removerBorda(){
        for (Bloco b : blocos) {
            if(b.getBorder() != bordaCinza){
                b.setBorder(bordaVazia);
            }
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
        canvas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();
        lblelem = new javax.swing.JLabel();
        lblNElementos = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        txtInicio = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        lblFim = new javax.swing.JLabel();
        txtFim = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnInserir.setText("Inserir");

        btnRemover.setText("Remover");

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
                .addGap(40, 40, 40)
                .addComponent(lblelem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNElementos)
                .addContainerGap(530, Short.MAX_VALUE))
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(canvasLayout.createSequentialGroup()
                .addGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(canvasLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblelem)))
                    .addGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblResultado)
                        .addComponent(lblNElementos)))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        lblValor.setText("Valor:");

        txtInicio.setFont(new java.awt.Font("Arimo", 0, 36)); // NOI18N
        txtInicio.setText("---");

        lblInicio.setText("Inicio:");

        lblFim.setText("Fim:");

        txtFim.setFont(new java.awt.Font("Arimo", 0, 36)); // NOI18N
        txtFim.setText("---");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover)
                .addGap(18, 18, 18)
                .addComponent(lblValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(lblInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtInicio)
                .addGap(26, 26, 26)
                .addComponent(lblFim)
                .addGap(18, 18, 18)
                .addComponent(txtFim)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtInicio)
                                .addComponent(txtFim))
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInserir)
                            .addComponent(btnRemover)
                            .addComponent(lblValor)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblInicio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblFim)))
                .addGap(11, 11, 11)
                .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInserir;
    private javax.swing.ButtonGroup btnRadioGroup;
    private javax.swing.JButton btnRemover;
    private javax.swing.JPanel canvas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblFim;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblNElementos;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblelem;
    private javax.swing.JLabel txtFim;
    private javax.swing.JLabel txtInicio;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}

package simulaed;

import com.sun.glass.events.KeyEvent;
import estruturas.LSE;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author jayme
 */
public final class JanelaLSE extends javax.swing.JDialog {
    
    LSE lista;
    ArrayList<Bloco> blocos;
    
    Border bordaVerde = BorderFactory.createLineBorder(Color.GREEN, 2);
    Border bordaAmarela = BorderFactory.createLineBorder(Color.YELLOW, 2);
    Border bordaVazia = BorderFactory.createEmptyBorder(); 
    Border bordaVermelha = BorderFactory.createLineBorder(Color.RED, 2);
    
    boolean cor;
    final int DELAY = 1500;
    ImageIcon image = new ImageIcon("seta2.png");    
    ArrayList<JLabel> setas;
    
    int Ybloco;        
    int Yseta;
        
    public JanelaLSE(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        lista = new LSE();
        blocos = new ArrayList<>(10);
        setas = new ArrayList<>(10);
        
        Ybloco  = canvas.getSize().height;
        Ybloco /=2;
        Ybloco -= canvas.getY();
        
        Yseta = image.getIconHeight()/2;
        
        lblElementos.setText("0");
        lblBusca.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        buttonGroup1.add(radioPosicao);
        buttonGroup1.add(radioValor);        
    }
    
    private void moveBlocosDireita(int pos, int dx){        
        Bloco b;
        Point location;
        for (int i = pos; i < blocos.size(); i++) {
            b = blocos.get(i);
            location = b.getLocation();            
            location.translate(dx, 0);
            b.setLocation(location);
        }    
    }
    
    private void insereMeio(Bloco b, int pos){
        int larguraSeta = image.getIconWidth();
        int larguraBloco = b.getWidth();
        int dx =  larguraSeta + larguraBloco;
        
        Point location = blocos.get(pos-1).getLocation();
        
        moveBlocosDireita(pos-1, dx);        
        
        b.setLocation(location);
        
        //insere uma Seta no fim
        Bloco ultimo = blocos.get(blocos.size()-1);
        Point posicaoSeta = new Point(ultimo.getLocation());        
        posicaoSeta.translate(larguraBloco, Yseta);
        insereSeta(posicaoSeta);
    }
    
    private void insereFim(Bloco b){        
        int larguraSeta = image.getIconWidth();
        int larguraBloco = b.getWidth();
        int xUltimoBloco = blocos.get(blocos.size()-1).getX();
        int xFinal = xUltimoBloco + larguraSeta + larguraBloco;
                
        b.setLocation(xFinal, Ybloco);        
        
        //insere seta no fim
        Point posicaoSeta = new Point(b.getLocation());
        posicaoSeta.translate(larguraBloco, Yseta);
        insereSeta(posicaoSeta);
    }
    
    private void insereInicio(Bloco b){
        int larguraSeta = image.getIconWidth();
        int larguraBloco = b.getWidth();
        int dx = larguraSeta + larguraBloco;
        
        moveBlocosDireita(0, dx);        
        
        b.setLocation(5, Ybloco);
        
        //inserir nova seta no fim
        Point posicaoSeta = new Point(b.getLocation());       
        if(lista.getTamanho() == 1)
            posicaoSeta.translate(larguraBloco, Yseta);    
        else{
            dx = larguraSeta * setas.size();
            dx += larguraBloco * lista.getTamanho();
            posicaoSeta.translate(dx, Yseta);
        }
        insereSeta(posicaoSeta);
    }
    
    private void insereSeta(Point posicao){
        JLabel seta = new JLabel(image);
        seta.setLocation(posicao);        
        seta.setSize(image.getIconWidth(), image.getIconHeight());
        
        canvas.add(seta);        
        setas.add(seta);
    }
    
    private void moveSetasDireita(int pos, int dx){
        JLabel seta;
        Point location;
        for (int i = pos; i < setas.size(); i++) {
            seta = setas.get(i);
            location = seta.getLocation();            
            location.translate(dx, 0);
            seta.setLocation(location);            
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        canvas = new javax.swing.JPanel();
        btnInserir = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtPosicao = new javax.swing.JTextField();
        radioValor = new javax.swing.JRadioButton();
        radioPosicao = new javax.swing.JRadioButton();
        btnBuscar = new javax.swing.JButton();
        lblBusca = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblElementos = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista Simplesmente Encadeada");

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        btnInserir.setText("Inserir");
        btnInserir.setMnemonic(KeyEvent.VK_I);
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.setMnemonic(KeyEvent.VK_R);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel1.setText("Valor:");

        jLabel2.setText("Posi�ao:");

        buttonGroup1.add(radioValor);
        radioValor.setText("Pesq. por Valor.");

        buttonGroup1.add(radioPosicao);
        radioPosicao.setText("Pesq. por Posi�ao.");

        btnBuscar.setText("Buscar");
        btnBuscar.setMnemonic(KeyEvent.VK_B);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblBusca.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblBusca.setText("---");

        jLabel3.setText("Removido:");

        jLabel4.setText("N. Elementos:");

        lblElementos.setText("0");

        lblResultado.setText("---");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInserir)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnRemover)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblElementos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblResultado)
                                .addGap(161, 161, 161)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioValor)
                            .addComponent(radioPosicao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBusca)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBuscar, btnInserir, btnRemover});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtPosicao, txtValor});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInserir)
                            .addComponent(jLabel1)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(jLabel4)
                            .addComponent(lblElementos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemover)
                            .addComponent(jLabel2)
                            .addComponent(txtPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(lblResultado)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioPosicao))
                    .addComponent(lblBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        if (cor) {
            removerBorda();
        }
        
        //Erro se estiver vazio os campos.
        if (txtPosicao.getText().equals("") || txtValor.getText().equals("")){            
            JOptionPane.showMessageDialog(null, "Campo(s) vazios", "Erro", HEIGHT);
            return;   
        }  
        
        try {
            Character.isAlphabetic(Integer.parseInt(txtValor.getText()));
            Character.isAlphabetic(Integer.parseInt(txtPosicao.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valor invalido.", "Erro", HEIGHT);
            return;
        }
        
        int valor = Integer.parseInt(txtValor.getText());
        int pos = 1;//ja come�a com posicao 1 para o caso da lista vazia, para nao ser necessario digitar a posicao
        boolean ok = false;        
        
        try{//aqui ele tenta inserir na lista com o valor e a posicao digitados.
            // Se a lista tiver vazia e posicao foi digitada com valor diferente de 1, lan�a uma excecao.
            if(!(txtPosicao.getText().equals("")))
                pos = Integer.parseInt(txtPosicao.getText());
            
            ok = lista.insere(pos, valor); //Insere o valor no n� e a posi�ao.
        
        }catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(rootPane, "Numero invalido.");
        }catch (Exception ex) {
             JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
            
        if (ok) {
            Bloco b = new Bloco(); //Cria um novo bloco.        
            b.setValor(valor); //Coloca o valor que o usuario digitou no bloco.                         
            
            if(pos == 1)
                insereInicio(b);
            else if(pos == lista.getTamanho())            
                insereFim(b);
            else
                insereMeio(b, pos);            
            
            blocos.add(pos-1, b);
            canvas.add(b); //Adiciona ao painel o bloco criado.
            b.setBorder(bordaVerde);
            cor = true;
            
            canvas.repaint();
        }
        lblElementos.setText("" + lista.getTamanho()); //Atualiza o tamanho da lista.
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        
        // Se a borda dos blocos ja estiver colorida, remove a borda
        if(cor){
            removerBorda();
        }
        
        //Erro se estiver vazio os campos.
        if (txtPosicao.getText().equals("") || txtValor.getText().equals("")){        
            JOptionPane.showMessageDialog(null, "Campo(s) vazios", "Erro", HEIGHT);
            return;   
        } 
                
        try {
            Character.isAlphabetic(Integer.parseInt(txtValor.getText()));
            Character.isAlphabetic(Integer.parseInt(txtPosicao.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valor invalido.", "Erro", HEIGHT);
            return;
        }
                
        int valor;                
        String posi�ao = txtPosicao.getText();
        String numero = txtValor.getText();
        if(posi�ao.equals("") || numero.equals("")){
            JOptionPane.showMessageDialog(null, "Campo posi�ao vazia.", "Erro", HEIGHT);
            return;
        }
        int pos = Integer.parseInt(posi�ao);                
                
        try {
            valor = lista.remove(pos);                    
                    
            final Bloco b = blocos.get(pos-1);
            Bloco ultimo = blocos.get(lista.getTamanho());                           
    
            b.setBorder(bordaVermelha);            
            
            Timer t = new Timer(DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    b.setBorder(bordaVazia);
                    
                    Bloco c;
                    for (int i = pos-1; i < lista.getTamanho(); i++) {
                        c = blocos.get(i);
                        try {
                            c.setValor(lista.elemento(i+1));                            
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                        }
                    }
                    
                    cor = true;
                    
                    blocos.remove(ultimo);
                    canvas.remove(ultimo);

                    JLabel ultimaSeta = setas.get(setas.size()-1);
                    setas.remove(ultimaSeta);
                    canvas.remove(ultimaSeta);            
                    
                    canvas.repaint();
                }
            });
            t.setRepeats(false);
            t.start();
                    
            lblResultado.setText(""+valor);
            lblElementos.setText(""+lista.getTamanho());
                    
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Numero invalido.");
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
    }//GEN-LAST:event_btnRemoverActionPerformed

private void moveSetasEsquerda(int pos, int dx){
    JLabel seta;
    Point location;
    for (int i = setas.size()-1; i >= pos; i--) {
        seta = setas.get(i);
        location = seta.getLocation();            
        location.translate(-dx, 0);
        seta.setLocation(location);            
    }
}
    
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        if(cor){
            removerBorda();                    
        }
        
        int valor;
        int pos;
        String s;
        
        try {
            if(radioPosicao.isSelected()) {                   
                s = txtPosicao.getText();
                if(s.equals(""))
                    return;  
                
                pos = Integer.parseInt(s);                
                valor = lista.elemento(pos);     
                
                blocos.get(pos-1).setBorder(bordaAmarela);
                cor = true;
                
                lblBusca.setText(String.format("%3d",valor)); 
            }
            else if(radioValor.isSelected()){
                s = txtValor.getText();
                if(s.equals("")){
                    return; 
                }
                
                valor = Integer.parseInt(s);                
                pos = lista.posi�ao(valor);                                
                
                if(pos != -1){
                    blocos.get(pos-1).setBorder(bordaAmarela);
                    cor = true;
                }
                
                lblBusca.setText(String.format("%3d",pos));
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Numero invalido.");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());                
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void removerBorda(){
        for (Bloco b : blocos) {
            b.setBorder(bordaVazia);
        }
        cor = false;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnRemover;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel canvas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JLabel lblElementos;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JRadioButton radioPosicao;
    private javax.swing.JRadioButton radioValor;
    private javax.swing.JTextField txtPosicao;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}

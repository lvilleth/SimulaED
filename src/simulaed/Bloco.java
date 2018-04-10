package simulaed;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 *
 * @author Leonardo Villeth
 */
public class Bloco extends javax.swing.JButton{
    
    //public Font fonte = new Font("Arial", Font.BOLD, 20);
    private int valor;
    
    public Bloco() {
        super();
        super.removeMouseListener(super.getMouseListeners()[0]);
        super.setUI(new MetalButtonUI());
        super.setSize(70, 70);
        super.setFont(super.getFont().deriveFont(Font.PLAIN, 28));
        //super.setFont(fonte);
        super.setBackground(Color.WHITE);        
        super.setBorderPainted(true);
        super.setFocusPainted(false);
        super.setVisible(true);
        super.setFocusable(false);
        //super.setContentAreaFilled(false);        
    }

    public void setValor(int v){
        valor = v;        
        //setText(String.format("%03d", valor));
        setText(""+ valor);
    }
    public int getValor(){
        return valor;
    }
    
    
}

package simulaed;

import java.awt.Font;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 *
 * @author Leonardo Villeth
 */
public class Circulo extends Bloco{
    
    //private static final File circulo = new File("circulo_preto.png");
    public static final Icon BORDA_PRETA = new ImageIcon("circulo_preto.png");
    public static final Icon BORDA_AMARELA = new ImageIcon("circulo_amarelo.png");
    public static final Icon BORDA_VERDE = new ImageIcon("circulo_verde.png");
    public static final Icon BORDA_VERMELHA = new ImageIcon("circulo_vermelho.png");
    
    public Circulo() {
        super();        
        super.setBackground(null);        
        super.setIcon(BORDA_PRETA);
        super.setVerticalTextPosition(SwingConstants.CENTER);
        super.setHorizontalTextPosition(SwingConstants.CENTER);
    }    
    
}

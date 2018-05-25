package simulaed;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 *
 * @author Leonardo Villeth
 */
public class Circulo extends Bloco{    
    
    public static final Icon BORDA_PRETA = new ImageIcon("circulo_preto.png");
    public static final Icon BORDA_AMARELA = new ImageIcon("circulo_amarelo.png");
    public static final Icon BORDA_VERDE = new ImageIcon("circulo_verde.png");
    public static final Icon BORDA_VERMELHA = new ImageIcon("circulo_vermelho.png");
    
    private final List<Circulo> conexoes;
    
    public Circulo() {
        super();        
        super.setBackground(null);        
        super.setIcon(BORDA_PRETA);
        super.setVerticalTextPosition(SwingConstants.CENTER);
        super.setHorizontalTextPosition(SwingConstants.CENTER);
        
        conexoes = new ArrayList<>();
    }    
    
    public void addConexao(Circulo no){
        conexoes.add(no);
    }
    
    public List<Circulo> getConexoes(){
        return conexoes;
    }
}

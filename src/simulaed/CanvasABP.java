package simulaed;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import static simulaed.JanelaABP.circulos;

/**
 *
 * @author Leonardo Villeth
 */ 
public class CanvasABP extends JPanel{
    
    int xi,xf,yi,yf;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        if(circulos == null)
            return;
                    
        for (Circulo pai : circulos) {            
            for (Circulo filho : pai.getConexoes()) {
                xi = pai.getX();
                yi = pai.getY();
                xf = filho.getX();
                yf = filho.getY();
                
                xi += pai.getWidth()/2;
                yi += pai.getHeight()/2;                
                
                xf += filho.getWidth()/2;
                yf += filho.getHeight()/2;
                
                g2d.drawLine(xi, yi, xf, yf);                            
            }            
        }
        
    }
}

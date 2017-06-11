
package Presentacion;

import Negocio.Reloj;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameReloj extends JFrame{
    
    private JTextField tiempoTextField;
    private long tiempoInicio=0;
    private Reloj reloj;
    private int tiempoActual=0;
    
    public FrameReloj(){
        tiempoTextField = new JTextField("000", 3);
        tiempoTextField.setEditable(false);
        tiempoTextField.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        tiempoTextField.setBackground(Color.BLACK);
        tiempoTextField.setForeground(Color.RED);
        tiempoTextField.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    public void iniciarReloj(){
        reloj=new Reloj(System.currentTimeMillis(),this);
    
    }
    public void pararReloj(){
        reloj.parar();
    }
    public void reiniciar(){
        
        reloj=new Reloj(System.currentTimeMillis()-tiempoActual*1000,this);
    }

    
    public void mostrarTiempo(int tiempo) {
        
        if (tiempo >= 0 && tiempo <= 9) {
            tiempoTextField.setText("00" + tiempo);
        } else if (tiempo > 9 && tiempo <= 99) {
            tiempoTextField.setText("0" + tiempo);
        } else if (tiempo > 99 && tiempo <= 999) {
            tiempoTextField.setText("" + tiempo);
        }
        this.tiempoActual=tiempo;
    } 
    public JTextField getTiempoTextField(){
        return tiempoTextField;
    }
}

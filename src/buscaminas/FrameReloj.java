/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameReloj extends JFrame{
    
    JTextField tf_time;
    long tiempoInicio=0;
    Reloj reloj;
    int tiempoActual=0;
    
    public FrameReloj(){
        tf_time = new JTextField("000", 3);
        tf_time.setEditable(false);
        tf_time.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        tf_time.setBackground(Color.BLACK);
        tf_time.setForeground(Color.RED);
        tf_time.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    public void iniciarReloj(){
        //tiempoInicio=System.currentTimeMillis();
        reloj=new Reloj(System.currentTimeMillis(),this);
    
    }
    public void pararReloj(){
        reloj.parar();
    }
    public void reiniciar(){
        
        reloj=new Reloj(System.currentTimeMillis()-tiempoActual*1000,this);
    }

    
    public void mostrarTiempo(int tiempo) {
        
        if (tiempo >= 0 && tiempo < 9) {
            tf_time.setText("00" + tiempo);
        } else if (tiempo > 9 && tiempo < 99) {
            tf_time.setText("0" + tiempo);
        } else if (tiempo > 99 && tiempo < 999) {
            tf_time.setText("" + tiempo);
        }
        this.tiempoActual=tiempo;
    } 
}


package Negocio;

import Presentacion.FrameReloj;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reloj extends Thread{
    
    private final long tiempoInicial;
    private final FrameReloj fmReloj;
    private boolean relojActivo=true;
    
    public Reloj(long tiempoInicial, FrameReloj fmReloj){
        this.tiempoInicial=tiempoInicial;
        this.fmReloj=fmReloj;
        this.start();
    
    }

    @Override
    public void run() {
        while(relojActivo){
            dormir();
            fmReloj.mostrarTiempo(this.getSegundosTranscuridos());
        }
    } 
    public int getSegundosTranscuridos(){
        return (int) ((System.currentTimeMillis()-this.tiempoInicial) / 1000);
        
    
    }
    public void parar(){
        relojActivo=false;
    }
    
    public void dormir(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

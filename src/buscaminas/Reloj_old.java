/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Reloj_old extends JFrame implements Runnable {

    JTextField tf_time;
    long startTime;
    Thread updater;
    boolean isRunning = false;
    long a = 0;
    
    public Reloj_old(JTextField tf_time){
        this.tf_time=tf_time;
        run();
        
    }
    
    Runnable displayUpdater = new Runnable() {

        public void run() {
            mostrarTiempo(a);
            a++;
        }
    };

    public void stop() {
        long elapsed = a;
        isRunning = false;
        try {
            updater.join();
        } catch (InterruptedException ie) {
             System.err.print(ie.toString());
        }
        mostrarTiempo(elapsed);
        a = 0;
    }

    private void mostrarTiempo(long tiempo) {

        if (tiempo >= 0 && tiempo < 9) {
            tf_time.setText("00" + tiempo);
        } else if (tiempo > 9 && tiempo < 99) {
            tf_time.setText("0" + tiempo);
        } else if (tiempo > 99 && tiempo < 999) {
            tf_time.setText("" + tiempo);
        }
    }

    public void run() {
        try {
            while (isRunning) {
                SwingUtilities.invokeAndWait(displayUpdater);
                Thread.sleep(1000);
            }
        } catch (java.lang.reflect.InvocationTargetException ite) {
            ite.printStackTrace(System.err);
        } catch (InterruptedException ie) {
            System.err.print(ie.toString());
        }
    }

    public void Start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
        updater = new Thread(this);
        updater.start();
    }

}

package buscaminas;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Buscaminas extends JFrame implements ActionListener, ContainerListener {

    int anchoVentana, altoVentana, bloquesFila, bloquesColumna, numeroMinas, banderasRestantes = 0, savedlevel = 1,
            savedbloquesFila, savedbloquesColumna, savednumeroMinas = 10;
    int cantidadCasillas=bloquesFila* bloquesColumna;
    int[] posicionFilasContiguas = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] posicionColumnasContiguas = {-1, 0, 1, 1, 1, 0, -1, -1};
    Icasilla[] casillas;
    ImageIcon[] ic = new ImageIcon[3];
    JPanel panelb = new JPanel();
    JPanel panelmt = new JPanel();
    JTextField tf_mine, tf_time;
    JButton b_reset = new JButton("");
    boolean casillasIniciadas = false, starttime = false;
    Point framelocation;
    Reloj reloj;
    MouseHandler mh;
    Point p;

    public Buscaminas() {
        super("Minesweeper");
        setLocation(400, 300);

        setic();
        setpanel(1, 0, 0, 0);
        setmanue();

        reloj = new Reloj(tf_time);

        b_reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    reloj.stop();
                    setpanel(savedlevel, savedbloquesFila, savedbloquesColumna, savednumeroMinas);
                } catch (Exception ex) {
                    System.err.println(ex.toString());
                    //setpanel(savedlevel, savedbloquesFila, savedbloquesColumna, savednumeroMinas);
                }
                reset();

            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show();
    }

    public void reset() {
        casillasIniciadas = false;
        starttime = false;
        int indice=0;
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                casillas[indice] = new ProxyCasilla(i,j);
                casillas[indice].addMouseListener(mh);
                panelb.add(casillas[indice]);
                indice++;
                
            }
        }
    }

    public void setpanel(int level, int setr, int setc, int setm) {
        if (level == 1) {
            anchoVentana = 200;
            altoVentana = 300;
            bloquesFila = 10;
            bloquesColumna = 10;
            numeroMinas = 10;
        } else if (level == 2) {
            anchoVentana = 320;
            altoVentana = 416;
            bloquesFila = 16;
            bloquesColumna = 16;
            numeroMinas = 70;
        } else if (level == 3) {
            anchoVentana = 400;
            altoVentana = 520;
            bloquesFila = 20;
            bloquesColumna = 20;
            numeroMinas = 150;
        } else if (level == 4) {
            anchoVentana = (20 * setc);
            altoVentana = (24 * setr);
            bloquesFila = setr;
            bloquesColumna = setc;
            numeroMinas = setm;
        }

        savedbloquesFila = bloquesFila;
        savedbloquesColumna = bloquesColumna;
        savednumeroMinas = numeroMinas;

        setSize(anchoVentana, altoVentana);
        setResizable(false);
        banderasRestantes = numeroMinas;
        p = this.getLocation();

        casillas = new ProxyCasilla[bloquesColumna*bloquesFila];
        mh = new MouseHandler(this);

        getContentPane().removeAll();
        panelb.removeAll();

        tf_mine = new JTextField("" + numeroMinas, 3);
        tf_mine.setEditable(false);
        tf_mine.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        tf_mine.setBackground(Color.BLACK);
        tf_mine.setForeground(Color.RED);
        tf_mine.setBorder(BorderFactory.createLoweredBevelBorder());
        
        tf_time = new JTextField("000", 3);
        tf_time.setEditable(false);
        tf_time.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        tf_time.setBackground(Color.BLACK);
        tf_time.setForeground(Color.RED);
        tf_time.setBorder(BorderFactory.createLoweredBevelBorder());
        
        b_reset.setIcon(ic[0]);
        b_reset.setBorder(BorderFactory.createLoweredBevelBorder());

        panelmt.removeAll();
        panelmt.setLayout(new BorderLayout());
        panelmt.add(tf_mine, BorderLayout.WEST);
        panelmt.add(b_reset, BorderLayout.CENTER);
        panelmt.add(tf_time, BorderLayout.EAST);
        panelmt.setBorder(BorderFactory.createLoweredBevelBorder());

        panelb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        panelb.setPreferredSize(new Dimension(anchoVentana, altoVentana));
        panelb.setLayout(new GridLayout(0, bloquesColumna));
        panelb.addContainerListener(this);
        
        reset();

        panelb.revalidate();
        panelb.repaint();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().addContainerListener(this);
        getContentPane().repaint();
        getContentPane().add(panelb, BorderLayout.CENTER);
        getContentPane().add(panelmt, BorderLayout.NORTH);
        setVisible(true);
    }

    public void setmanue() {
        JMenuBar bar = new JMenuBar();

        JMenu game = new JMenu("Opciones");

        JMenuItem menuitem = new JMenuItem("Nueva Partida");
        final JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Principiante");
        final JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermedio");
        final JCheckBoxMenuItem expart = new JCheckBoxMenuItem("Experto");
        final JMenuItem exit = new JMenuItem("Salir");
        final JMenu help = new JMenu("Ayuda");
        final JMenuItem helpitem = new JMenuItem("Ayuda");

        ButtonGroup status = new ButtonGroup();

        menuitem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        setpanel(1, 0, 0, 0);
                    }
                });

        beginner.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(1, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        beginner.setSelected(true);
                        savedlevel = 1;
                    }
                });
        intermediate.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(2, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        intermediate.setSelected(true);
                        savedlevel = 2;
                    }
                });
        expart.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(3, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        expart.setSelected(true);
                        savedlevel = 3;
                    }
                });


        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpitem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "instruction");

            }
        });

        setJMenuBar(bar);

        status.add(beginner);
        status.add(intermediate);
        status.add(expart);

        game.add(menuitem);
        game.addSeparator();
        game.add(beginner);
        game.add(intermediate);
        game.add(expart);
        game.addSeparator();
        game.add(exit);
        help.add(helpitem);

        bar.add(game);
        bar.add(help);

    }

    public void componentAdded(ContainerEvent ce) {
    }

    public void componentRemoved(ContainerEvent ce) {
    }

    public void actionPerformed(ActionEvent ae) {
    }

    public void winner() {
        boolean todasReveladas = true;
        for (int k = 0; k < cantidadCasillas; k++) {
                if (!casillas[k].isRevelado()) {
                    todasReveladas = false;
                }
        }


        if (todasReveladas) {
            for (int k = 0; k < cantidadCasillas; k++){
                    casillas[k].removeMouseListener(mh);
            
            }

            reloj.stop();
            JOptionPane.showMessageDialog(this, "Has ganado!");
        }
    }
//mostrar el valor del bloque
    public void revelarBloque(MouseEvent e) {
        for (int i = 0; i < cantidadCasillas; i++) {

                if (e.getSource() == casillas[i]) {
                    if (e.isMetaDown() == false) {
                        if (casillas[i].tieneBandera()) {
                            if (banderasRestantes < numeroMinas) {
                                banderasRestantes++;
                            }
                            tf_mine.setText("" + banderasRestantes);
                        }

                        if (casillas[i] instanceof CasillaMina) {
                            for (int k = 0; k < cantidadCasillas; k++) {
                                    if (casillas[k] instanceof CasillaMina) {
                                        casillas[k].revelar();
                                        casillas[k].removeMouseListener(mh);
                                    }
                                    casillas[k].removeMouseListener(mh);
                            }
                            reloj.stop();
                            b_reset.setIcon(ic[1]);
                            JOptionPane.showMessageDialog(null, "Has perdido!");
                        } else if (casillas[i] instanceof CasillaVacia) {
                            dfs(casillas[i].getFila(), casillas[i].getColumna());
                            break;
                        } else {
                            casillas[i].revelar();
                            break;
                        }
                    } else {
                        if (banderasRestantes != 0) {
                            if (!casillas[i].isRevelado()) {
                                banderasRestantes--;
                                casillas[i].setIcon(ic[2]);
                            }
                            tf_mine.setText("" + banderasRestantes);
                        }


                    }
                }

            
        }

    }
// Revelar una casilla
    public void calculation() {

        for (int i = 0; i < cantidadCasillas; i++) {
                int valor = 0;
                int valorFila, valorColumna;

                // calcula el numero que hay que descubrir
                if (casillas[i] instanceof CasillaMina) {
                    
                }else {
                for (int k = 0; k < 8; k++) {
                        valorFila = casillas[i].getFila() + posicionFilasContiguas[k];
                        valorColumna = casillas[i].getColumna() + posicionColumnasContiguas[k];

                        if (valorFila >= 0 && valorColumna >= 0 && valorFila < bloquesFila && valorColumna < bloquesColumna) {
                            if (casillas[i] instanceof CasillaMina) {
                                valor++;
                            }

                        }

                    }
                    casillas[i]=casillas[i].setValor(valor);
                
                }
            
        }
    }
    
    public Icasilla buscarCasilla(int fila, int columna){
        for (int i = 0; i < cantidadCasillas; i++) {
            if(casillas[i].getFila()==fila && casillas[i].getColumna()== columna) return casillas[i];       
        }
        
        return null;
    
    }
    
// es un dfs realmente?
    public void dfs(int fila, int columna) {

        int valorFila, valorColumna;
        Icasilla casillaActual=buscarCasilla(fila, columna);
        
        
        casillaActual.revelar();

        for (int k = 0; k < 8; k++) {
            valorFila = fila + posicionFilasContiguas[k];
            valorColumna = columna + posicionColumnasContiguas[k];
            
            Icasilla casillaAdyacente=buscarCasilla(fila, columna);
            
            if (valorFila >= 0 && valorFila < bloquesFila && valorColumna >= 0 && valorColumna < bloquesColumna && !casillaAdyacente.isRevelado()) {
                if (casillaAdyacente instanceof CasillaVacia) {
                    dfs(valorFila, valorColumna);
                } else {
                    casillaAdyacente.revelar();
                }
            }


        }
    }
// pone las minas despues del primer click
    public void setmine() {
        Random casillaAleatoria = new Random();
        Icasilla casillaActual;
        int indiceAleatorio=0;
        
        
        for (int i = 0; i < numeroMinas; i++) {
            indiceAleatorio = casillaAleatoria.nextInt(cantidadCasillas);
            
            if (casillas[indiceAleatorio] instanceof CasillaMina) {
                //chetos
                i--;
                
            } else {
                System.out.println("Mina "+i+":");
                System.out.println("row "+casillas[indiceAleatorio].getFila());
                System.out.println("col "+casillas[indiceAleatorio].getColumna());
                casillas[indiceAleatorio].setValor(-1);
            }
        }
    }

    public void setic() {
        ic[0] = new ImageIcon("./src/img/new game.gif");
        ic[1] = new ImageIcon("./src/img/crape.gif");
        ic[2] = new ImageIcon("./src/img/flag.gif");
         
    }
}

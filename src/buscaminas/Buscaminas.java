package buscaminas;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

public class Buscaminas extends JFrame implements ActionListener, ContainerListener {

    int anchoVentana, altoVentana, bloquesFila, bloquesColumna, numeroMinas, banderasRestantes = 0, savedlevel = 1,
            savedbloquesFila, savedbloquesColumna, savednumeroMinas;
    int cantidadCasillas;
    int[] posicionFilasContiguas = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] posicionColumnasContiguas = {-1, 0, 1, 1, 1, 0, -1, -1};
    Casilla[] casillas;
    ImageIcon[] ic = new ImageIcon[3];
    JPanel panelb = new JPanel();
    JPanel panelmt = new JPanel();
    JTextField tf_mine, tf_time;
    JButton b_reset = new JButton("");
    boolean casillasIniciadas = false, starttime = false;
    Point framelocation;
//    Reloj reloj;
    MouseHandler mh;
    Point p;

    public Buscaminas() {
        super("Buscaminas");
        setLocation(400, 300);

        setic();
        setpanel(1, 0, 0, 0);
        setmanue();

//        reloj = new Reloj(tf_time);
        b_reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
//                    reloj.stop();
                    setpanel(savedlevel, savedbloquesFila, savedbloquesColumna, savednumeroMinas);
                } catch (Exception ex) {
                    System.err.println(ex.toString());
                }

            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        show();
    }

    public void reset() {
        casillasIniciadas = false;
        starttime = false;
        int indice = 0;
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                casillas[indice] = new ProxyCasilla(i, j);
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
            cantidadCasillas = bloquesColumna * bloquesFila;
            numeroMinas = 10;
        } else if (level == 2) {
            anchoVentana = 320;
            altoVentana = 416;
            bloquesFila = 16;
            bloquesColumna = 16;
            numeroMinas = 70;
            cantidadCasillas = bloquesColumna * bloquesFila;
        } else if (level == 3) {
            anchoVentana = 400;
            altoVentana = 520;
            bloquesFila = 20;
            bloquesColumna = 20;
            numeroMinas = 150;
            cantidadCasillas = bloquesColumna * bloquesFila;
        } else if (level == 4) {
            anchoVentana = (20 * setc);
            altoVentana = (24 * setr);
            bloquesFila = setr;
            bloquesColumna = setc;
            numeroMinas = setm;
            cantidadCasillas = bloquesColumna * bloquesFila;
        }

        savedbloquesFila = bloquesFila;
        savedbloquesColumna = bloquesColumna;
        savednumeroMinas = numeroMinas;

        setSize(anchoVentana, altoVentana);
        setResizable(false);
        banderasRestantes = numeroMinas;
        p = this.getLocation();

        casillas = new ProxyCasilla[cantidadCasillas];
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
                JOptionPane.showMessageDialog(null, "instruccion");

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
            if(!casillas[k].esMina()) todasReveladas=todasReveladas && casillas[k].isRevelado();
        }

        if (todasReveladas) {
            for (int k = 0; k < cantidadCasillas; k++) {
                casillas[k].removeMouseListener(mh);
            }

//            reloj.stop();
            JOptionPane.showMessageDialog(this, "Has ganado!");
        }
    }

    public void revelarBloque(int indiceClicado, boolean clickBotonDerecho) {

        if (!clickBotonDerecho) {
            if (casillas[indiceClicado].tieneBandera()) {
                if (banderasRestantes < numeroMinas) {
                    banderasRestantes++;
                }
                tf_mine.setText("" + banderasRestantes);
            }

            if (casillas[indiceClicado].esMina()) {
                for (int k = 0; k < cantidadCasillas; k++) {
                    if (casillas[k].esMina()) {
                        casillas[k].revelar();
                        casillas[k].removeMouseListener(mh);
                    }
                    casillas[k].removeMouseListener(mh);
                }
//                            reloj.stop();
                b_reset.setIcon(ic[1]);
                JOptionPane.showMessageDialog(null, "Has perdido!");
            } else if (casillas[indiceClicado].esVacia()) {
                dfs(casillas[indiceClicado].getFila(), casillas[indiceClicado].getColumna());
            } else {
                casillas[indiceClicado].revelar();
            }
        } else {
            // click derecho
            if (banderasRestantes != 0) {
                if (!casillas[indiceClicado].isRevelado()) {
                    banderasRestantes--;
                    casillas[indiceClicado].setIcon(ic[2]);
                }
                tf_mine.setText("" + banderasRestantes);
            }

        }
    }
// Revelar una casilla

    public void calculation() {

        for (int i = 0; i < cantidadCasillas; i++) {
            int valor = 0;
            int valorFila, valorColumna;

            if (!casillas[i].esMina()) {
                for (int k = 0; k < 8; k++) {
                    valorFila = casillas[i].getFila() + posicionFilasContiguas[k];
                    valorColumna = casillas[i].getColumna() + posicionColumnasContiguas[k];

                    if (valorFila >= 0 && valorColumna >= 0 && valorFila < bloquesFila && valorColumna < bloquesColumna) {
                        if (casillas[buscarIndiceCasilla(valorFila, valorColumna)].esMina()) {
                            valor++;
                        }

                    }
                }
                casillas[i].setValor(valor);
            }

        }
    }

    public int buscarIndiceCasilla(int fila, int columna) {
        for (int i = 0; i < cantidadCasillas; i++) {
            if ((casillas[i].getFila() == fila) && (casillas[i].getColumna() == columna)) {
                return i;
            }
        }

        return -1;

    }

    public void dfs(int fila, int columna) {

        int filaAdyacente, columnaAdyacente;
        casillas[buscarIndiceCasilla(fila, columna)].revelar();

        for (int k = 0; k < 8; k++) {
            filaAdyacente = fila + posicionFilasContiguas[k];
            columnaAdyacente = columna + posicionColumnasContiguas[k];
            int indiceAdyacente = buscarIndiceCasilla(filaAdyacente, columnaAdyacente);
            if ((filaAdyacente >= 0) && (filaAdyacente < bloquesFila) && (columnaAdyacente >= 0) && (columnaAdyacente < bloquesColumna) && (!casillas[indiceAdyacente].isRevelado())) {
                if (casillas[indiceAdyacente].esVacia()) {
                    dfs(filaAdyacente, columnaAdyacente);
                } else if(!casillas[indiceAdyacente].esMina()){
                    casillas[indiceAdyacente].revelar();
                }
            }

        }
    }

    public void setmine(int indiceClicado) {
        int indiceAleatorio;
        Random rand = new Random();

        for (int i = 0; i < numeroMinas; i++) {
            indiceAleatorio = rand.nextInt(cantidadCasillas);

            if (casillas[indiceAleatorio].esMina() || indiceClicado == indiceAleatorio) {

                i--;

            } else {
                //chetos
                System.out.println("Mina " + i + ":");
                System.out.println("indice: " + indiceAleatorio + ":");
                System.out.println("row " + casillas[indiceAleatorio].getFila());
                System.out.println("col " + casillas[indiceAleatorio].getColumna());
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

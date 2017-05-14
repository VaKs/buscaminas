package buscaminas;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
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
    MementoAlmacen almacen = new MementoAlmacen();
    boolean hasPerdido=false;
    boolean hasGanado=false;
    FabricaCasilla fabricaCasilla= new FabricaCasilla();

    public Buscaminas() {
        super("Buscaminas");
        setLocation(400, 300);

        setIcono();
        setPanel(1, 0, 0, 0);
        setMenu();

//        reloj = new Reloj(tf_time);
        b_reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
//                    reloj.stop();
                    setPanel(savedlevel, savedbloquesFila, savedbloquesColumna, savednumeroMinas);
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
                casillas[indice] = fabricaCasilla.getCasilla(i, j);
                casillas[indice].addMouseListener(mh);
                panelb.add(casillas[indice]);
                indice++;

            }
        }
    }

    public void setPanel(int level, int setr, int setc, int setm) {
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

    public void setMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu game = new JMenu("Opciones");

        JMenuItem menuitem = new JMenuItem("Nueva Partida");
        final JCheckBoxMenuItem principiante = new JCheckBoxMenuItem("Principiante");
        final JCheckBoxMenuItem intermedio = new JCheckBoxMenuItem("Intermedio");
        final JCheckBoxMenuItem experto = new JCheckBoxMenuItem("Experto");
        final JMenuItem salir = new JMenuItem("Salir");
        final JMenu help = new JMenu("Ayuda");
        final JMenuItem deshacer = new JMenuItem("Deshacer paso");

        ButtonGroup status = new ButtonGroup();

        menuitem.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setPanel(1, 0, 0, 0);
                    }
                });

        principiante.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setPanel(1, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        principiante.setSelected(true);
                        savedlevel = 1;
                    }
                });
        intermedio.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setPanel(2, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        intermedio.setSelected(true);
                        savedlevel = 2;
                    }
                });
        experto.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setPanel(3, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        experto.setSelected(true);
                        savedlevel = 3;
                    }
                });

        salir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        deshacer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                restaurarMemento();
                if (hasPerdido) {
                    hasPerdido = false;
                    for (int i = 0; i < numeroMinas - 1; i++) {
                        restaurarMemento();
                    }
                    for (int j = 0; j < cantidadCasillas; j++) {
                        casillas[j].addMouseListener(mh);
                    }
                }else if (hasGanado) {
                    hasGanado = false;
                    for (int k = 0; k < cantidadCasillas; k++) {
                        casillas[k].addMouseListener(mh);
                    }
                }
                b_reset.setIcon(ic[0]);

            }
        });

        setJMenuBar(bar);

        status.add(principiante);
        status.add(intermedio);
        status.add(experto);

        game.add(menuitem);
        game.addSeparator();
        game.add(principiante);
        game.add(intermedio);
        game.add(experto);
        game.addSeparator();
        game.add(salir);
        help.add(deshacer);

        bar.add(game);
        bar.add(help);

        show();

    }

    public void componentAdded(ContainerEvent ce) {
    }

    public void componentRemoved(ContainerEvent ce) {
    }

    public void actionPerformed(ActionEvent ae) {
    }

    public void compruebaGanador() {
        boolean todasReveladas=true;
        for (int k = 0; k < cantidadCasillas; k++) {
            
            if (!casillas[k].esMina()) {
                todasReveladas = todasReveladas && casillas[k].isRevelado();
            }
        }
        if (todasReveladas) {
            for (int k = 0; k < cantidadCasillas; k++) {
                casillas[k].removeMouseListener(mh);
            }

//            reloj.stop();
            hasGanado=true;
            JOptionPane.showMessageDialog(this, "Has ganado!");
        }
    }

    public void revelarCasilla(int indiceClicado, boolean clickBotonDerecho) {

        if (clickBotonDerecho) {
            if (banderasRestantes != 0 && !casillas[indiceClicado].isRevelado()) {
                if (casillas[indiceClicado].tieneBandera()) {
                    guardarMemento(indiceClicado, true, false);
                    banderasRestantes++;
                    casillas[indiceClicado].setIcon(null);
                    casillas[indiceClicado].setBandera(false);

                } else {
                    guardarMemento(indiceClicado, false, false);
                    banderasRestantes--;
                    casillas[indiceClicado].setBandera(true);
                    casillas[indiceClicado].setIcon(ic[2]);
                }
//                tf_mine.setText("" + banderasRestantes);
            }
            tf_mine.setText("" + banderasRestantes);
        } else {
            if (casillas[indiceClicado].tieneBandera()) {
                if (banderasRestantes < numeroMinas) {
                    banderasRestantes++;
                    casillas[indiceClicado].setIcon(null);
                    tf_mine.setText("" + banderasRestantes);
                }
            }

            if (casillas[indiceClicado].esMina()) {
                hasPerdido = true;

                for (int k = 0; k < cantidadCasillas; k++) {
                    if (casillas[k].esMina()) {
                        guardarMemento(k, casillas[k].tieneBandera(), false);
                        casillas[k].revelar();
                    }
                    casillas[k].removeMouseListener(mh);
                }
//                            reloj.stop();
                b_reset.setIcon(ic[1]);
                JOptionPane.showMessageDialog(null, "Has perdido!");
            } else if (casillas[indiceClicado].esVacia()) {
                dfs(casillas[indiceClicado].getFila(), casillas[indiceClicado].getColumna());
            } else {
                guardarMemento(indiceClicado, casillas[indiceClicado].tieneBandera(), casillas[indiceClicado].isRevelado());
                casillas[indiceClicado].revelar();
            }

        }
    }
// Revelar una casilla

    public void obtenerValorCasillas() {

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
                } else if (!casillas[indiceAdyacente].esMina()) {
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

    public void setIcono() {
        ic[0] = new ImageIcon("./src/img/new game.gif");
        ic[1] = new ImageIcon("./src/img/crape.gif");
        ic[2] = new ImageIcon("./src/img/flag.gif");

    }

    public void guardarMemento(int indiceCasillaAlterada, boolean bandera, boolean revelado) {
        
        almacen.addMemento(indiceCasillaAlterada, bandera, revelado);
    }

    public void restaurarMemento() {
        Memento memento = almacen.getUltimoMemento();

        if (!memento.getBandera()) {
            casillas[memento.getIndiceCasilla()].setIcon(null);
            casillas[memento.getIndiceCasilla()].setBandera(false);
            tf_mine.setText("" + banderasRestantes);
        }
        if (memento.getBandera()) {
            casillas[memento.getIndiceCasilla()].setIcon(ic[2]);
        } else {
            casillas[memento.getIndiceCasilla()].setIcon(null);
        }

        casillas[memento.getIndiceCasilla()].setRevelado(false);

    }

}

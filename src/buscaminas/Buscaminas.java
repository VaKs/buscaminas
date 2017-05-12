package buscaminas;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Buscaminas extends JFrame implements ActionListener, ContainerListener {

    int anchoVentana, altoVentana, bloquesFila, bloquesColumna, filaBotonClick, columnaBotonClick, numeroMinas, banderasRestantes = 0, savedlevel = 1,
            savedbloquesFila, savedbloquesColumna, savednumeroMinas = 10;
    int[] posicionFilasContiguas = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] posicionColumnasContiguas = {-1, 0, 1, 1, 1, 0, -1, -1};
    JButton[][] bloques;
    int[][] valorBloque;
    boolean[][] bloqueRevelado;
    ImageIcon[] ic = new ImageIcon[14];
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
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                bloqueRevelado[i][j] = false;
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

        bloques = new JButton[bloquesFila][bloquesColumna];
        valorBloque = new int[bloquesFila][bloquesColumna];
        bloqueRevelado = new boolean[bloquesFila][bloquesColumna];
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
        
        b_reset.setIcon(ic[11]);
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

        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                bloques[i][j] = new JButton("");

                bloques[i][j].addMouseListener(mh);

                panelb.add(bloques[i][j]);

            }
        }
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
        int q = 0;
        for (int k = 0; k < bloquesFila; k++) {
            for (int l = 0; l < bloquesColumna; l++) {
                if (bloqueRevelado[k][l] == false) {
                    q = 1;
                }
            }
        }


        if (q == 0) {
            for (int k = 0; k < bloquesFila; k++) {
                for (int l = 0; l < bloquesColumna; l++) {
                    bloques[k][l].removeMouseListener(mh);
                }
            }

            reloj.stop();
            JOptionPane.showMessageDialog(this, "u R a lover");
        }
    }
//mostrar el valor del bloque
    public void revelarBloque(MouseEvent e) {
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {

                if (e.getSource() == bloques[i][j]) {
                    if (e.isMetaDown() == false) {
                        if (bloques[i][j].getIcon() == ic[10]) {
                            if (banderasRestantes < numeroMinas) {
                                banderasRestantes++;
                            }
                            tf_mine.setText("" + banderasRestantes);
                        }

                        if (valorBloque[i][j] == -1) {
                            for (int k = 0; k < bloquesFila; k++) {
                                for (int l = 0; l < bloquesColumna; l++) {
                                    if (valorBloque[k][l] == -1) {
                                        bloques[k][l].setIcon(ic[9]);
                                        bloques[k][l].removeMouseListener(mh);
                                    }
                                    bloques[k][l].removeMouseListener(mh);
                                }
                            }
                            reloj.stop();
                            b_reset.setIcon(ic[12]);
                            JOptionPane.showMessageDialog(null, "Has perdido");
                        } else if (valorBloque[i][j] == 0) {
                            dfs(i, j);
                            break;
                        } else {
                            bloques[i][j].setIcon(ic[valorBloque[i][j]]);
                            bloqueRevelado[i][j] = true;
                            break;
                        }
                    } else {
                        if (banderasRestantes != 0) {
                            if (bloques[i][j].getIcon() == null) {
                                banderasRestantes--;
                                bloques[i][j].setIcon(ic[10]);
                            }
                            tf_mine.setText("" + banderasRestantes);
                        }


                    }
                }

            }
        }

    }
// Revelar una casilla
    public void calculation() {
        int fila, columna;

        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                int valor = 0;
                int valorFila, valorColumna;
                fila = i;
                columna = j;
                // calcula el numero que hay que descubrir
                if (valorBloque[fila][columna] != -1) {
                    for (int k = 0; k < 8; k++) {
                        valorFila = fila + posicionFilasContiguas[k];
                        valorColumna = columna + posicionColumnasContiguas[k];

                        if (valorFila >= 0 && valorColumna >= 0 && valorFila < bloquesFila && valorColumna < bloquesColumna) {
                            if (valorBloque[valorFila][valorColumna] == -1) {
                                valor++;
                            }

                        }

                    }
                    valorBloque[fila][columna] = valor;

                }
            }
        }
    }
// es un dfs realmente?
    public void dfs(int fila, int columna) {

        int valorFila, valorColumna;
        bloqueRevelado[fila][columna] = true;

        bloques[fila][columna].setBackground(Color.GRAY);

        bloques[fila][columna].setIcon(ic[valorBloque[fila][columna]]);

        for (int k = 0; k < 8; k++) {
            valorFila = fila + posicionFilasContiguas[k];
            valorColumna = columna + posicionColumnasContiguas[k];
            if (valorFila >= 0 && valorFila < bloquesFila && valorColumna >= 0 && valorColumna < bloquesColumna && bloqueRevelado[valorFila][valorColumna] == false) {
                if (valorBloque[valorFila][valorColumna] == 0) {
                    dfs(valorFila, valorColumna);
                } else {
                    bloques[valorFila][valorColumna].setIcon(ic[valorBloque[valorFila][valorColumna]]);
                    bloqueRevelado[valorFila][valorColumna] = true;

                }
            }


        }
    }
// pone las minas despues del primer click
    public void setmine() {
        Random filaAleatoria = new Random();
        Random columnaAleatoria = new Random();
        int fila = 0, columna = 0;
        Boolean[][] existeProvabilidadDeBomba = new Boolean[bloquesFila][bloquesColumna];

        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                existeProvabilidadDeBomba[i][j] = true;
                valorBloque[i][j] = 0;
            }
        }

        existeProvabilidadDeBomba[filaBotonClick][columnaBotonClick] = false;
        bloqueRevelado[filaBotonClick][columnaBotonClick] = true;

        for (int i = 0; i < numeroMinas; i++) {
            fila = filaAleatoria.nextInt(bloquesFila);
            columna = columnaAleatoria.nextInt(bloquesColumna);

            if (existeProvabilidadDeBomba[fila][columna] == true) {
                //chetos
                System.out.println("Mina "+i+":");
                System.out.println("row "+fila);
                System.out.println("col "+columna);
                valorBloque[fila][columna] = -1;
                bloqueRevelado[fila][columna] = true;
                existeProvabilidadDeBomba[fila][columna] = false;
            } else {
                i--;
            }
        }
    }

    public void setic() {
        String name;

        for (int i = 0; i <= 8; i++) {
            name ="./src/img/" + i + ".gif";
            ic[i] = new ImageIcon(name);
        }
        ic[9] = new ImageIcon("./src/img/mine.gif");
        ic[10] = new ImageIcon("./src/img/flag.gif");
        ic[11] = new ImageIcon("./src/img/new game.gif");
        ic[12] = new ImageIcon("./src/img/crape.gif"); 
    }
}

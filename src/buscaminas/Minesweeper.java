package buscaminas;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Minesweeper extends JFrame implements ActionListener, ContainerListener {

    int anchoVentana, altoVentana, bloquesFila, bloquesColumna, filaBotonClick, columnaBotonClick, numeroMinas, detectedmine = 0, savedlevel = 1,
            savedbloquesFila, savedbloquesColumna, savednumeroMinas = 10;
    int[] r = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};
    JButton[][] bloques;
    int[][] countmine;
    int[][] colour;
    ImageIcon[] ic = new ImageIcon[14];
    JPanel panelb = new JPanel();
    JPanel panelmt = new JPanel();
    JTextField tf_mine, tf_time;
    JButton b_reset = new JButton("");
    Random ranr = new Random();
    Random ranc = new Random();
    boolean check = true, starttime = false;
    Point framelocation;
    Reloj reloj;
    MouseHandler mh;
    Point p;

    public Minesweeper() {
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
        check = true;
        starttime = false;
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                colour[i][j] = 'w';
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
        detectedmine = numeroMinas;
        p = this.getLocation();

        bloques = new JButton[bloquesFila][bloquesColumna];
        countmine = new int[bloquesFila][bloquesColumna];
        colour = new int[bloquesFila][bloquesColumna];
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

        JMenu game = new JMenu("GAME");

        JMenuItem menuitem = new JMenuItem("new game");
        final JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Begineer");
        final JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
        final JCheckBoxMenuItem expart = new JCheckBoxMenuItem("Expart");
        final JCheckBoxMenuItem custom = new JCheckBoxMenuItem("Custom");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenu help = new JMenu("Help");
        final JMenuItem helpitem = new JMenuItem("Help");

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

        custom.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        reset();
                        panelb.revalidate();
                        panelb.repaint();

                        custom.setSelected(true);
                        savedlevel = 4;
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
        status.add(custom);

        game.add(menuitem);
        game.addSeparator();
        game.add(beginner);
        game.add(intermediate);
        game.add(expart);
        game.add(custom);
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
                if (colour[k][l] == 'w') {
                    q = 1;
                }
            }
        }


        if (q == 0) {
            //panelb.hide();
            for (int k = 0; k < bloquesFila; k++) {
                for (int l = 0; l < bloquesColumna; l++) {
                    bloques[k][l].removeMouseListener(mh);
                }
            }

            reloj.stop();
            JOptionPane.showMessageDialog(this, "u R a lover");
        }
    }

    public void showvalue(MouseEvent e) {
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {

                if (e.getSource() == bloques[i][j]) {
                    if (e.isMetaDown() == false) {
                        if (bloques[i][j].getIcon() == ic[10]) {
                            if (detectedmine < numeroMinas) {
                                detectedmine++;
                            }
                            tf_mine.setText("" + detectedmine);
                        }

                        if (countmine[i][j] == -1) {
                            for (int k = 0; k < bloquesFila; k++) {
                                for (int l = 0; l < bloquesColumna; l++) {
                                    if (countmine[k][l] == -1) {
                                        bloques[k][l].setIcon(ic[9]);
                                        bloques[k][l].removeMouseListener(mh);
                                    }
                                    bloques[k][l].removeMouseListener(mh);
                                }
                            }
                            reloj.stop();
                            b_reset.setIcon(ic[12]);
                            JOptionPane.showMessageDialog(null, "sorry u R  loser");
                        } else if (countmine[i][j] == 0) {
                            dfs(i, j);
                        } else {
                            bloques[i][j].setIcon(ic[countmine[i][j]]);
                            colour[i][j] = 'b';
                            break;
                        }
                    } else {
                        if (detectedmine != 0) {
                            if (bloques[i][j].getIcon() == null) {
                                detectedmine--;
                                bloques[i][j].setIcon(ic[10]);
                            }
                            tf_mine.setText("" + detectedmine);
                        }


                    }
                }

            }
        }

    }
// Revelar una casilla
    public void calculation() {
        int row, column;

        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                int value = 0;
                int R, C;
                row = i;
                column = j;
                // calcula el numero que hay que descubrir
                if (countmine[row][column] != -1) {
                    for (int k = 0; k < 8; k++) {
                        R = row + r[k];
                        C = column + c[k];

                        if (R >= 0 && C >= 0 && R < bloquesFila && C < bloquesColumna) {
                            if (countmine[R][C] == -1) {
                                value++;
                            }

                        }

                    }
                    countmine[row][column] = value;

                }
            }
        }
    }

    public void dfs(int row, int col) {

        int R, C;
        colour[row][col] = 'b';

        bloques[row][col].setBackground(Color.GRAY);

        bloques[row][col].setIcon(ic[countmine[row][col]]);
        //blocks[row][col].setText("");
        for (int i = 0; i < 8; i++) {
            R = row + r[i];
            C = col + c[i];
            if (R >= 0 && R < bloquesFila && C >= 0 && C < bloquesColumna && colour[R][C] == 'w') {
                if (countmine[R][C] == 0) {
                    dfs(R, C);
                } else {
                    bloques[R][C].setIcon(ic[countmine[R][C]]);
                    colour[R][C] = 'b';

                }
            }


        }
    }
// pone las minas despues del primer click
    public void setmine() {
        int row = 0, col = 0;
        Boolean[][] bandera = new Boolean[bloquesFila][bloquesColumna];

        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                bandera[i][j] = true;
                countmine[i][j] = 0;
            }
        }

        bandera[filaBotonClick][columnaBotonClick] = false;
        colour[filaBotonClick][columnaBotonClick] = 'b';

        for (int i = 0; i < numeroMinas; i++) {
            row = ranr.nextInt(bloquesFila);
            col = ranc.nextInt(bloquesColumna);

            if (bandera[row][col] == true) {
                //chetos
                System.out.println("Mina "+i+":");
                System.out.println("row "+row);
                System.out.println("col "+col);
                countmine[row][col] = -1;
                colour[row][col] = 'b';
                bandera[row][col] = false;
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

    class Customizetion extends JFrame implements ActionListener {

        JTextField t1, t2, t3;
        JLabel lb1, lb2, lb3;
        JButton b1, b2;
        int cr, cc, cm, actionc = 0;

        Customizetion() {
            super("CUSTOMIZETION");
            setSize(180, 200);
            setResizable(false);
            setLocation(p);

            t1 = new JTextField();
            t2 = new JTextField();
            t3 = new JTextField();

            b1 = new JButton("OK");
            b2 = new JButton("Cencel");

            b1.addActionListener(this);
            b2.addActionListener(this);

            lb1 = new JLabel("Row");
            lb2 = new JLabel("Column");
            lb3 = new JLabel("mine");

            getContentPane().setLayout(new GridLayout(0, 2));

            getContentPane().add(lb1);
            getContentPane().add(t1);
            getContentPane().add(lb2);
            getContentPane().add(t2);
            getContentPane().add(lb3);
            getContentPane().add(t3);

            getContentPane().add(b1);
            getContentPane().add(b2);

            show();
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                try {
                    cr = Integer.parseInt(t1.getText());
                    cc = Integer.parseInt(t2.getText());
                    cm = Integer.parseInt(t3.getText());
                    setpanel(4, row(), column(), mine());
                    dispose();
                } catch (Exception any) {
                    JOptionPane.showMessageDialog(this, "Wrong");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                }

            }

            if (e.getSource() == b2) {
                dispose();
            }
        }

        public int row() {
            if (cr > 30) {
                return 30;
            } else if (cr < 10) {
                return 10;
            } else {
                return cr;
            }
        }

        public int column() {
            if (cc > 30) {
                return 30;
            } else if (cc < 10) {
                return 10;
            } else {
                return cc;
            }
        }

        public int mine() {
            if (cm > ((row() - 1) * (column() - 1))) {
                return ((row() - 1) * (column() - 1));
            } else if (cm < 10) {
                return 10;
            } else {
                return cm;
            }
        }
    }
}

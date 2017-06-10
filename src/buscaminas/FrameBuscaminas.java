package buscaminas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameBuscaminas extends JFrame implements ActionListener, ContainerListener{
    
    Buscaminas buscaminas;
    
    ImageIcon[] ic = new ImageIcon[3];
    JPanel panelb = new JPanel();
    JPanel panelmt = new JPanel();
    JTextField tf_mine, tf_time;
    JButton b_reset = new JButton("");
    Point framelocation;
    FrameReloj fmReloj;
    Point p;
    
    public FrameBuscaminas(){
        super("Buscaminas");
        
        this.buscaminas=new Buscaminas(this);
        
        this.setLocation(400, 300);
        this.setIcono();
        this.buscaminas.setNivel(Nivel.PRINCIPIANTE);
        this.setMenu();
        

        
    }
    
    public void setPanel(int anchoVentana, int altoVentana, int bloquesFila, int bloquesColumna, int numeroMinas) {
            
        this.setSize(anchoVentana, altoVentana);
        this.setResizable(false);
        buscaminas.banderasRestantes = numeroMinas;
        p = this.getLocation();

        this.getContentPane().removeAll();
        panelb.removeAll();

        tf_mine = new JTextField("" + numeroMinas, 3);
        tf_mine.setEditable(false);
        tf_mine.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        tf_mine.setBackground(Color.BLACK);
        tf_mine.setForeground(Color.RED);
        tf_mine.setBorder(BorderFactory.createLoweredBevelBorder());
        
        fmReloj= new FrameReloj();

        b_reset.setBackground(Color.GRAY);
        b_reset.setIcon(ic[0]);
        b_reset.setBorder(BorderFactory.createLoweredBevelBorder());
        b_reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    setPanel( anchoVentana,altoVentana,bloquesFila, bloquesColumna, numeroMinas);
                } catch (Exception ex) {
                    System.err.println(ex.toString());
                }

            }
        });
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelmt.removeAll();
        panelmt.setLayout(new BorderLayout());
        panelmt.add(tf_mine, BorderLayout.WEST);
        panelmt.add(b_reset, BorderLayout.CENTER);
        panelmt.add(fmReloj.tf_time, BorderLayout.EAST);
        panelmt.setBorder(BorderFactory.createLoweredBevelBorder());

        panelb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        panelb.setPreferredSize(new Dimension(anchoVentana, altoVentana));
        panelb.setLayout(new GridLayout(0, bloquesColumna));
        panelb.addContainerListener(this);

        buscaminas.reset();

        panelb.revalidate();
        panelb.repaint();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().addContainerListener(this);
        getContentPane().repaint();
        getContentPane().add(panelb, BorderLayout.CENTER);
        getContentPane().add(panelmt, BorderLayout.NORTH);
        setVisible(true);
        
    }
    
    public void setIcono() {
        ic[0] = new ImageIcon("./src/img/new game.gif");
        ic[1] = new ImageIcon("./src/img/crape.gif");
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
                        buscaminas.setNivel(Nivel.PRINCIPIANTE);
                    }
                });

        principiante.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        buscaminas.reset();
                        buscaminas.setNivel(Nivel.PRINCIPIANTE);
                        panelb.revalidate();
                        panelb.repaint();
                        principiante.setSelected(true);
                        intermedio.setSelected(false);
                        experto.setSelected(false);
                    }
                });
        intermedio.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        buscaminas.reset();
                        buscaminas.setNivel(Nivel.INTERMEDIO);
                        panelb.revalidate();
                        panelb.repaint();
                        intermedio.setSelected(true);
                        principiante.setSelected(false);
                        experto.setSelected(false);
                    }
                });
        experto.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        buscaminas.reset();
                        buscaminas.setNivel(Nivel.EXPERTO);
                        panelb.revalidate();
                        panelb.repaint();
                        intermedio.setSelected(false);
                        principiante.setSelected(false);
                        experto.setSelected(true);
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
                
                buscaminas.deshacer();

         
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
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void componentAdded(ContainerEvent e) {
    }

    @Override
    public void componentRemoved(ContainerEvent e) {
    }
    
}

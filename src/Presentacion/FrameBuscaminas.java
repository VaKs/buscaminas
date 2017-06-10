package Presentacion;

import Negocio.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameBuscaminas extends JFrame implements ActionListener, ContainerListener{
    
    private Buscaminas buscaminas;
    
    private ImageIcon[] ic = new ImageIcon[3];
    private JPanel panelFondo = new JPanel();
    private JPanel panelCabecera = new JPanel();
    private JTextField banderasRestantesText;
    private JButton reiniciarBoton = new JButton("");
    private FrameReloj fmReloj;
    
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

        this.getContentPane().removeAll();
        panelFondo.removeAll();

        banderasRestantesText = new JTextField("" + numeroMinas, 3);
        banderasRestantesText.setEditable(false);
        banderasRestantesText.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        banderasRestantesText.setBackground(Color.BLACK);
        banderasRestantesText.setForeground(Color.RED);
        banderasRestantesText.setBorder(BorderFactory.createLoweredBevelBorder());
        
        fmReloj= new FrameReloj();

        reiniciarBoton.setBackground(Color.GRAY);
        reiniciarBoton.setIcon(ic[0]);
        reiniciarBoton.setBorder(BorderFactory.createLoweredBevelBorder());
        reiniciarBoton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            try {
                                panelFondo.removeAll();
                                buscaminas.reiniciarNivel();
                                panelFondo.revalidate();
                                panelFondo.repaint();
                            } catch (Exception ex) {
                                System.err.println(ex.toString());
                            }

                        }
                    }
                );
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelCabecera.removeAll();
        panelCabecera.setLayout(new BorderLayout());
        panelCabecera.add(banderasRestantesText, BorderLayout.WEST);
        panelCabecera.add(reiniciarBoton, BorderLayout.CENTER);
        panelCabecera.add(fmReloj.tf_time, BorderLayout.EAST);
        panelCabecera.setBorder(BorderFactory.createLoweredBevelBorder());

        
        
        panelFondo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        panelFondo.setPreferredSize(new Dimension(anchoVentana, altoVentana));
        panelFondo.setLayout(new GridLayout(0, bloquesColumna));
        panelFondo.addContainerListener(this);
        panelFondo.revalidate();
        panelFondo.repaint();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().addContainerListener(this);
        getContentPane().repaint();
        getContentPane().add(panelFondo, BorderLayout.CENTER);
        getContentPane().add(panelCabecera, BorderLayout.NORTH);
        setVisible(true);
        
    }
    
    public void setIcono() {
        ic[0] = new ImageIcon("./src/img/new game.gif");
        ic[1] = new ImageIcon("./src/img/crape.gif");
    }
    
    public void setMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu opcionesMenu = new JMenu("Opciones");
        
        JMenuItem nuevaPartidaBoton = new JMenuItem("Nueva Partida");
        final JCheckBoxMenuItem principianteBoton = new JCheckBoxMenuItem("Principiante");
        final JCheckBoxMenuItem intermedioBoton = new JCheckBoxMenuItem("Intermedio");
        final JCheckBoxMenuItem expertoBoton = new JCheckBoxMenuItem("Experto");
        final JMenuItem salirBoton = new JMenuItem("Salir");
        final JMenu ayudaMenu = new JMenu("Ayuda");
        final JMenuItem deshacerBoton = new JMenuItem("Deshacer paso");

        ButtonGroup status = new ButtonGroup();

        nuevaPartidaBoton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelFondo.removeAll();
                        buscaminas.reiniciarNivel();
                        panelFondo.revalidate();
                        panelFondo.repaint();
                    }
                });

        principianteBoton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelFondo.removeAll();
                        buscaminas.setNivel(Nivel.PRINCIPIANTE);
                        panelFondo.revalidate();
                        panelFondo.repaint();
                        principianteBoton.setSelected(true);
                        intermedioBoton.setSelected(false);
                        expertoBoton.setSelected(false);
                    }
                });
        intermedioBoton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelFondo.removeAll();
                        buscaminas.setNivel(Nivel.INTERMEDIO);
                        panelFondo.revalidate();
                        panelFondo.repaint();
                        intermedioBoton.setSelected(true);
                        principianteBoton.setSelected(false);
                        expertoBoton.setSelected(false);
                    }
                });
        expertoBoton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelFondo.removeAll();
                        buscaminas.setNivel(Nivel.EXPERTO);
                        panelFondo.revalidate();
                        panelFondo.repaint();
                        intermedioBoton.setSelected(false);
                        principianteBoton.setSelected(false);
                        expertoBoton.setSelected(true);
                    }
                });

        salirBoton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        deshacerBoton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                buscaminas.deshacer();

         
            }
        });

        setJMenuBar(bar);

        status.add(principianteBoton);
        status.add(intermedioBoton);
        status.add(expertoBoton);

        opcionesMenu.add(nuevaPartidaBoton);
        opcionesMenu.addSeparator();
        opcionesMenu.add(principianteBoton);
        opcionesMenu.add(intermedioBoton);
        opcionesMenu.add(expertoBoton);
        opcionesMenu.addSeparator();
        opcionesMenu.add(salirBoton);
        ayudaMenu.add(deshacerBoton);

        bar.add(opcionesMenu);
        bar.add(ayudaMenu);

        show();

    }
    public void añadirCasillaAlPanel(Casilla casilla){
        panelFondo.add(casilla);
    }
    public void hasGanado(){
        fmReloj.pararReloj();
        JOptionPane.showMessageDialog(null, "Has ganado!");
    }
    public void actualizarContadorBnderas(int banderasRestantes){
        banderasRestantesText.setText("" + banderasRestantes);
    }
    public void hasPerdido(){
        fmReloj.pararReloj();
        reiniciarBoton.setIcon(ic[1]);
        JOptionPane.showMessageDialog(null, "Has perdido!");
    }
    public void iniciarPartida(){
        fmReloj.iniciarReloj();
        reiniciarBoton.setIcon(ic[0]);
    }
    public void reiniciarPartida(){
        fmReloj.reiniciar();
        reiniciarBoton.setIcon(ic[0]);
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

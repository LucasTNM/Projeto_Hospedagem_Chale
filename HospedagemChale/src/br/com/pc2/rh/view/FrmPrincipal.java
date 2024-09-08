package br.com.pc2.rh.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.pc2.rh.view.Hospedagem.FrmHospedagem;
import br.com.pc2.rh.view.Chale.FrmChale;
import br.com.pc2.rh.view.Cliente.FrmCliente;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmPrincipal frame = new FrmPrincipal();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FrmPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnCadastro = new JMenu("Cadastro");
        menuBar.add(mnCadastro);

        JMenuItem mntmCliente = new JMenuItem("Cliente");
        mntmCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmCliente fc = new FrmCliente();
                fc.setTitle("Cadastro de Cliente");
                fc.setLocationRelativeTo(null);
                fc.setVisible(true);
            }
        });
        mnCadastro.add(mntmCliente);

        JMenuItem mntmChale = new JMenuItem("Chalé");
        mntmChale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmChale fc = new FrmChale();
                fc.setTitle("Cadastro de Chalé");
                fc.setLocationRelativeTo(null);
                fc.setVisible(true);
            }
        });
        mnCadastro.add(mntmChale);

        JMenuItem mntmHospedagem = new JMenuItem("Hospedagem");
        mntmHospedagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmHospedagem fh = new FrmHospedagem();
                fh.setTitle("Cadastro de Hospedagem");
                fh.setLocationRelativeTo(null);
                fh.setVisible(true);
            }
        });
        mnCadastro.add(mntmHospedagem);

        JMenuItem mntmSair = new JMenuItem("Sair");
        mntmSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnCadastro.add(mntmSair);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGap(0, 584, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGap(0, 361, Short.MAX_VALUE)
        );
        contentPane.setLayout(gl_contentPane);
    }
}

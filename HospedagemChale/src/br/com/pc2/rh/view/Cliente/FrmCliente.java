package br.com.pc2.rh.view.Cliente;

import br.com.pc2.rh.controller.ClienteController;
import br.com.pc2.rh.model.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FrmCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblConsulta;
    private JTextField txtNome;
    private JTextField txtRg;
    private JTextField txtEndereco;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtCep;
    private JTextField txtNascimento;
    private JLabel lblMensagem;
    private List<Cliente> listaClientes = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmCliente frame = new FrmCliente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public FrmCliente() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 608, 534);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JPanel panel = new JPanel();
        JPanel panel_1 = new JPanel();
        JPanel panel_2 = new JPanel();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                        .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                        .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );

        JScrollPane scrollPane = new JScrollPane();
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
            gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, gl_panel_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_2.setVerticalGroup(
            gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addContainerGap())
        );

        tblConsulta = new JTable();
        tblConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tblConsulta.getSelectedRow();
                txtNome.setText(tblConsulta.getValueAt(linha, 0).toString());
                txtRg.setText(tblConsulta.getValueAt(linha, 1).toString());
                txtEndereco.setText(tblConsulta.getValueAt(linha, 2).toString());
                txtBairro.setText(tblConsulta.getValueAt(linha, 3).toString());
                txtCidade.setText(tblConsulta.getValueAt(linha, 4).toString());
                txtEstado.setText(tblConsulta.getValueAt(linha, 5).toString());
                txtCep.setText(tblConsulta.getValueAt(linha, 6).toString());
                txtNascimento.setText(tblConsulta.getValueAt(linha, 7).toString());
            }
        });
        tblConsulta.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Nome", "RG", "Endereço", "Bairro", "Cidade", "Estado", "CEP", "Nascimento"
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane.setViewportView(tblConsulta);
        panel_2.setLayout(gl_panel_2);

        JButton btnInserir = new JButton("Inserir");
        btnInserir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cliente cliente = new Cliente();
                ClienteController cc = new ClienteController();
                cliente.setNome(txtNome.getText());
                cliente.setRg(txtRg.getText());
                cliente.setEndereco(txtEndereco.getText());
                cliente.setBairro(txtBairro.getText());
                cliente.setCidade(txtCidade.getText());
                cliente.setEstado(txtEstado.getText());
                cliente.setCep(txtCep.getText());
                cliente.setNascimento(LocalDate.parse(txtNascimento.getText()));
                lblMensagem.setText(cc.inserir(cliente));
            }
        });

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cliente cliente = new Cliente();
                ClienteController cc = new ClienteController();
                cliente.setNome(txtNome.getText());
                cliente.setRg(txtRg.getText());
                cliente.setEndereco(txtEndereco.getText());
                cliente.setBairro(txtBairro.getText());
                cliente.setCidade(txtCidade.getText());
                cliente.setEstado(txtEstado.getText());
                cliente.setCep(txtCep.getText());
                cliente.setNascimento(LocalDate.parse(txtNascimento.getText()));
                lblMensagem.setText(cc.alterar(cliente));
            }
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cliente cliente = new Cliente();
                ClienteController cc = new ClienteController();
                cliente.setRg(txtRg.getText());
                Object[] opcoes = {"Sim", "Não"};
                int i = JOptionPane.showOptionDialog(null,
                    "Deseja excluir o cliente: " + txtNome.getText() + "?", "Exclusão",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    opcoes, opcoes[0]);
                if (JOptionPane.YES_OPTION == i) {
                    lblMensagem.setText(cc.excluir(cliente));
                }
            }
        });

        JButton btnPesquisar = new JButton("Listar");
        btnPesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClienteController cc = new ClienteController();
                listaClientes = cc.listarTodos();
                DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
                tbm.setRowCount(0);
                for (Cliente cliente : listaClientes) {
                    tbm.addRow(new Object[]{
                        cliente.getNome(),
                        cliente.getRg(),
                        cliente.getEndereco(),
                        cliente.getBairro(),
                        cliente.getCidade(),
                        cliente.getEstado(),
                        cliente.getCep(),
                        cliente.getNascimento()
                    });
                }
            }
        });

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtNome.setText("");
                txtRg.setText("");
                txtEndereco.setText("");
                txtBairro.setText("");
                txtCidade.setText("");
                txtEstado.setText("");
                txtCep.setText("");
                txtNascimento.setText("");
                DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
                tbm.setRowCount(0);
            }
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmCliente.this.dispose();
            }
        });

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addComponent(btnInserir)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAlterar)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnExcluir)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnPesquisar)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                    .addComponent(btnLimpar)
                    .addGap(18)
                    .addComponent(btnSair)
                    .addContainerGap())
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInserir)
                        .addComponent(btnAlterar)
                        .addComponent(btnExcluir)
                        .addComponent(btnPesquisar)
                        .addComponent(btnLimpar)
                        .addComponent(btnSair))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        JLabel lblNome = new JLabel("Nome");
        JLabel lblRg = new JLabel("RG");
        JLabel lblEndereco = new JLabel("Endereço");
        JLabel lblBairro = new JLabel("Bairro");
        JLabel lblCidade = new JLabel("Cidade");
        JLabel lblEstado = new JLabel("Estado");
        JLabel lblCep = new JLabel("CEP");
        JLabel lblNascimento = new JLabel("Nascimento");

        txtNome = new JTextField();
        txtNome.setColumns(10);

        txtRg = new JTextField();
        txtRg.setColumns(10);

        txtEndereco = new JTextField();
        txtEndereco.setColumns(10);

        txtBairro = new JTextField();
        txtBairro.setColumns(10);

        txtCidade = new JTextField();
        txtCidade.setColumns(10);

        txtEstado = new JTextField();
        txtEstado.setColumns(10);

        txtCep = new JTextField();
        txtCep.setColumns(10);

        txtNascimento = new JTextField();
        txtNascimento.setColumns(10);

        lblMensagem = new JLabel("Mensagem");
        lblMensagem.setForeground(Color.BLUE);
        lblMensagem.setFont(new Font("Times New Roman", Font.ITALIC, 11));

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblMensagem)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblNome)
                                .addComponent(lblRg)
                                .addComponent(lblEndereco)
                                .addComponent(lblBairro)
                                .addComponent(lblCidade)
                                .addComponent(lblEstado)
                                .addComponent(lblCep)
                                .addComponent(lblNascimento))
                            .addGap(18)
                            .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRg, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCep, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNascimento, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(40, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome)
                        .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRg)
                        .addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEndereco)
                        .addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBairro)
                        .addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCidade)
                        .addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEstado)
                        .addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCep)
                        .addComponent(txtCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNascimento)
                        .addComponent(txtNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblMensagem)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        contentPane.setLayout(gl_contentPane);
    }
}


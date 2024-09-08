package br.com.pc2.rh.view.Chale;

import br.com.pc2.rh.controller.ChaleController;
import br.com.pc2.rh.model.Chale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmChale extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblConsulta;
    private JTextField txtLocalizacao;
    private JTextField txtCapacidade;
    private JTextField txtValorAltaEstacao;
    private JTextField txtValorBaixaEstacao;
    private JLabel lblMensagem;
    private List<Chale> listaChales = new ArrayList<>();
    private ChaleController chaleController = new ChaleController();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmChale frame = new FrmChale();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmChale() {
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
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
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
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addContainerGap())
        );

        tblConsulta = new JTable();
        tblConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tblConsulta.getSelectedRow();
                txtLocalizacao.setText(tblConsulta.getValueAt(linha, 0).toString());
                txtCapacidade.setText(tblConsulta.getValueAt(linha, 1).toString());
                txtValorAltaEstacao.setText(tblConsulta.getValueAt(linha, 2).toString());
                txtValorBaixaEstacao.setText(tblConsulta.getValueAt(linha, 3).toString());
            }
        });
        tblConsulta.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Localização", "Capacidade", "Valor Alta Estação", "Valor Baixa Estação"
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
                String.class, Integer.class, Double.class, Double.class
            };
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false, false, false
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
                Chale chale = new Chale();
                chale.setLocalizacao(txtLocalizacao.getText());
                chale.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
                chale.setValorAltaEstacao(Double.parseDouble(txtValorAltaEstacao.getText()));
                chale.setValorBaixaEstacao(Double.parseDouble(txtValorBaixaEstacao.getText()));
                lblMensagem.setText(chaleController.inserir(chale));
            }
        });

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	    int selectedRow = tblConsulta.getSelectedRow();

        	    if (selectedRow != -1) {
        	        Chale chale = listaChales.get(selectedRow);
        	        if (chale.getCodChale() != null && chale.getCodChale() > 0) {
        	            chale.setLocalizacao(txtLocalizacao.getText());
        	            chale.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
        	            chale.setValorAltaEstacao(Double.parseDouble(txtValorAltaEstacao.getText()));
        	            chale.setValorBaixaEstacao(Double.parseDouble(txtValorBaixaEstacao.getText()));
        	            lblMensagem.setText(chaleController.alterar(chale));
        	        } else {
        	            lblMensagem.setText("Código do chalé inválido.");
        	        }
        	    } else {
        	        lblMensagem.setText("Selecione um chalé para alterar. Caso não tenha chalés aparecendo, utilize o botão pesquisar ou insira um chalé primeiro.");
        	    }
        	}

        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblConsulta.getSelectedRow();
                if (selectedRow != -1) {
                    Chale chale = listaChales.get(selectedRow);
                    Object[] opcoes = {"Sim", "Não"};
                    int i = JOptionPane.showOptionDialog(null,
                        "Deseja excluir o chalé localizado em: " + chale.getLocalizacao() + "?", "Exclusão",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        opcoes, opcoes[0]);
                    if (JOptionPane.YES_OPTION == i) {
                        lblMensagem.setText(chaleController.excluir(chale));
                    }
                } else {
                    lblMensagem.setText("Selecione um chalé para excluir. Caso não tenha chalés aparecendo, utilize o botão pesquisar ou insira um chalé primeiro.");
                }
            }
        });

        JButton btnPesquisar = new JButton("Listar");
        btnPesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listaChales = chaleController.listarTodos();
                DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
                tbm.setRowCount(0); 
                for (Chale chale : listaChales) {
                    tbm.addRow(new Object[]{
                        chale.getLocalizacao(),
                        chale.getCapacidade(),
                        chale.getValorAltaEstacao(),
                        chale.getValorBaixaEstacao()
                    });
                }
            }
        });

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocalizacao.setText("");
                txtCapacidade.setText("");
                txtValorAltaEstacao.setText("");
                txtValorBaixaEstacao.setText("");
                DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
                tbm.setRowCount(0); 
            }
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmChale.this.dispose();
            }
        });

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnInserir)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAlterar)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnExcluir)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnPesquisar)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnLimpar)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnSair)
                    .addContainerGap(106, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
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

        JLabel lblLocalizacao = new JLabel("Localização");
        JLabel lblCapacidade = new JLabel("Capacidade");
        JLabel lblValorAltaEstacao = new JLabel("Valor Alta Estação");
        JLabel lblValorBaixaEstacao = new JLabel("Valor Baixa Estação");

        txtLocalizacao = new JTextField();
        txtLocalizacao.setColumns(10);

        txtCapacidade = new JTextField();
        txtCapacidade.setColumns(10);

        txtValorAltaEstacao = new JTextField();
        txtValorAltaEstacao.setColumns(10);

        txtValorBaixaEstacao = new JTextField();
        txtValorBaixaEstacao.setColumns(10);

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
                                .addComponent(lblLocalizacao)
                                .addComponent(lblCapacidade)
                                .addComponent(lblValorAltaEstacao)
                                .addComponent(lblValorBaixaEstacao))
                            .addGap(18)
                            .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtLocalizacao, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCapacidade, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtValorAltaEstacao, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtValorBaixaEstacao, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(40, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLocalizacao)
                        .addComponent(txtLocalizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCapacidade)
                        .addComponent(txtCapacidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValorAltaEstacao)
                        .addComponent(txtValorAltaEstacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValorBaixaEstacao)
                        .addComponent(txtValorBaixaEstacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblMensagem)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        contentPane.setLayout(gl_contentPane);
    }
}

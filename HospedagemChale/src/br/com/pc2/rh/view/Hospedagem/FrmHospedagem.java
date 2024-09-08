package br.com.pc2.rh.view.Hospedagem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import br.com.pc2.rh.controller.HospedagemController;
import br.com.pc2.rh.model.Hospedagem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class FrmHospedagem extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCodChale, txtEstado, txtDataInicio, txtDataFim, txtQtdPessoas, txtDesconto, txtValorFinal, txtCodCliente;
    private JLabel lblMensagem;
    private JTable tblConsulta;
    private List<Hospedagem> listaHospedagens = new ArrayList<>();
    private HospedagemController hospedagemController = new HospedagemController();
    
    private LocalDate parseDate(String dateText) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
            .toFormatter();

        try {
            return LocalDate.parse(dateText, formatter);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public FrmHospedagem() {
        setTitle("Gestão de Hospedagem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        JPanel panel_1 = new JPanel();

        JLabel lblCodChale = new JLabel("Código do Chalé:");
        txtCodChale = new JTextField();
        txtCodChale.setColumns(10);

        JLabel lblEstado = new JLabel("Estado:");
        txtEstado = new JTextField();
        txtEstado.setColumns(10);

        JLabel lblDataInicio = new JLabel("Data de Início:");
        txtDataInicio = new JTextField();
        txtDataInicio.setColumns(10);

        JLabel lblDataFim = new JLabel("Data de Fim:");
        txtDataFim = new JTextField();
        txtDataFim.setColumns(10);

        JLabel lblQtdPessoas = new JLabel("Quantidade de Pessoas:");
        txtQtdPessoas = new JTextField();
        txtQtdPessoas.setColumns(10);

        JLabel lblDesconto = new JLabel("Desconto:");
        txtDesconto = new JTextField();
        txtDesconto.setColumns(10);

        JLabel lblValorFinal = new JLabel("Valor Final:");
        txtValorFinal = new JTextField();
        txtValorFinal.setColumns(10);

        JLabel lblCodCliente = new JLabel("Código do Cliente:");
        txtCodCliente = new JTextField();
        txtCodCliente.setColumns(10);

        lblMensagem = new JLabel("Mensagem:");

        JButton btnInserir = new JButton("Inserir");
        btnInserir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String dataInicioText = txtDataInicio.getText();
                    String dataFimText = txtDataFim.getText();
                    String estado = txtEstado.getText(); 

                    LocalDate dataInicio = parseDate(dataInicioText);
                    LocalDate dataFim = parseDate(dataFimText);

                    if (dataInicio == null || dataFim == null) {
                        lblMensagem.setText("Formato de data inválido. Por favor, digite no formato: YYYY-MM-DD");
                        return;
                    }

                    if (estado == null || estado.trim().isEmpty()) {
                        lblMensagem.setText("O campo 'Estado' não pode estar vazio.");
                        return;
                    }

                    Hospedagem hospedagem = new Hospedagem();
                    hospedagem.setCodChale(Integer.parseInt(txtCodChale.getText()));
                    hospedagem.setCodCliente(Integer.parseInt(txtCodCliente.getText()));
                    hospedagem.setData_inicio(dataInicio);
                    hospedagem.setData_fim(dataFim);
                    hospedagem.setQtd_pessoas(Integer.parseInt(txtQtdPessoas.getText()));
                    hospedagem.setDesconto(Double.parseDouble(txtDesconto.getText()));
                    hospedagem.setValor_final(Double.parseDouble(txtValorFinal.getText()));
                    hospedagem.setEstado(estado);

                    String result = hospedagemController.inserir(hospedagem);

                    lblMensagem.setText(result);

                } catch (NumberFormatException ex) {
                    lblMensagem.setText("Erro de formato numérico. Verifique os campos de entrada.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    lblMensagem.setText("Erro ao inserir hospedagem: " + ex.getMessage());
                }
            }
        });

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblConsulta.getSelectedRow();
                if (selectedRow != -1) {
                    Hospedagem hospedagem = listaHospedagens.get(selectedRow);
                    if (hospedagem.getCodHospedagem() != null && hospedagem.getCodHospedagem() > 0) {
                        hospedagem.setCodChale(Integer.parseInt(txtCodChale.getText()));
                        hospedagem.setEstado(txtEstado.getText());
                        hospedagem.setData_inicio(parseDate(txtDataInicio.getText()));
                        hospedagem.setData_fim(parseDate(txtDataFim.getText()));
                        hospedagem.setQtd_pessoas(Integer.parseInt(txtQtdPessoas.getText()));
                        hospedagem.setDesconto(Double.parseDouble(txtDesconto.getText()));
                        hospedagem.setValor_final(Double.parseDouble(txtValorFinal.getText()));
                        hospedagem.setCodCliente(Integer.parseInt(txtCodCliente.getText()));

                        lblMensagem.setText(hospedagemController.alterar(hospedagem));
                    } else {
                        lblMensagem.setText("Código da hospedagem inválido.");
                    }
                } else {
                    lblMensagem.setText("Selecione uma hospedagem para alterar.");
                }
            }
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblConsulta.getSelectedRow();
                if (selectedRow != -1) {
                    Hospedagem hospedagem = listaHospedagens.get(selectedRow);
                    Object[] opcoes = {"Sim", "Não"};
                    int i = JOptionPane.showOptionDialog(null,
                        "Deseja excluir a hospedagem do chalé: " + hospedagem.getCodChale() + "?", "Exclusão",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        opcoes, opcoes[0]);
                    if (JOptionPane.YES_OPTION == i) {
                        lblMensagem.setText(hospedagemController.excluir(hospedagem));
                        loadData();
                    }
                } else {
                    lblMensagem.setText("Selecione uma hospedagem para excluir. Caso não tenha hospedagens aparecendo, utilize o botão pesquisar ou insira uma hospedagem primeiro.");
                }
            }
        });

        JButton btnPesquisar = new JButton("Listar");
        btnPesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listaHospedagens = hospedagemController.listarTodos();
                DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
                tbm.setRowCount(0);
                
                for (Hospedagem hospedagem : listaHospedagens) {
                    tbm.addRow(new Object[]{
                        hospedagem.getCodHospedagem(),
                        hospedagem.getCodChale(),
                        hospedagem.getEstado(),
                        hospedagem.getData_inicio(),
                        hospedagem.getData_fim(),
                        hospedagem.getQtd_pessoas(),
                        hospedagem.getDesconto(),
                        hospedagem.getValor_final(),
                        hospedagem.getCodCliente()
                    });
                }
            }
        });


        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtCodChale.setText("");
                txtEstado.setText("");
                txtDataInicio.setText("");
                txtDataFim.setText("");
                txtQtdPessoas.setText("");
                txtDesconto.setText("");
                txtValorFinal.setText("");
                txtCodCliente.setText("");
                DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
                tbm.setRowCount(0);
            }
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmHospedagem.this.dispose();
            }
        });

        tblConsulta = new JTable();
        tblConsulta.setModel(new DefaultTableModel(
        	    new Object[][] {},
        	    new String[] {
        	        "Código da Hospedagem", "Código do Chalé", "Estado", "Data Início", "Data Fim", "Qtd Pessoas", "Desconto", "Valor Final", "Código Cliente"
        	    }
        	) {
        	    private static final long serialVersionUID = 1L;
        	    @SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
        	        Integer.class, Integer.class, String.class, LocalDate.class, LocalDate.class, Integer.class, Double.class, Double.class, Integer.class
        	    };
        	    @SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
        	        return columnTypes[columnIndex];
        	    }
        	    boolean[] columnEditables = new boolean[] {
        	        false, false, false, false, false, false, false, false, false
        	    };
        	    public boolean isCellEditable(int row, int column) {
        	        return columnEditables[column];
        	    }
        	});


        tblConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblConsulta.getSelectedRow();
                if (selectedRow != -1) {
                    Hospedagem hospedagem = listaHospedagens.get(selectedRow);
                    if (hospedagem != null) {
                        txtCodChale.setText(hospedagem.getCodChale() != null ? String.valueOf(hospedagem.getCodChale()) : "");
                        txtEstado.setText(hospedagem.getEstado() != null ? hospedagem.getEstado() : "");
                        txtDataInicio.setText(hospedagem.getData_inicio() != null ? hospedagem.getData_inicio().toString() : "");
                        txtDataFim.setText(hospedagem.getData_fim() != null ? hospedagem.getData_fim().toString() : "");
                        txtQtdPessoas.setText(hospedagem.getQtd_pessoas() != null ? String.valueOf(hospedagem.getQtd_pessoas()) : "");
                        txtDesconto.setText(hospedagem.getDesconto() != null ? String.valueOf(hospedagem.getDesconto()) : "");
                        txtValorFinal.setText(hospedagem.getValor_final() != null ? String.valueOf(hospedagem.getValor_final()) : "");
                        txtCodCliente.setText(hospedagem.getCodCliente() != null ? String.valueOf(hospedagem.getCodCliente()) : "");
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tblConsulta);

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblCodChale)
                        .addComponent(lblEstado)
                        .addComponent(lblDataInicio)
                        .addComponent(lblDataFim)
                        .addComponent(lblQtdPessoas)
                        .addComponent(lblDesconto)
                        .addComponent(lblValorFinal)
                        .addComponent(lblCodCliente))
                    .addGap(18)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtCodChale)
                        .addComponent(txtEstado)
                        .addComponent(txtDataInicio)
                        .addComponent(txtDataFim)
                        .addComponent(txtQtdPessoas)
                        .addComponent(txtDesconto)
                        .addComponent(txtValorFinal)
                        .addComponent(txtCodCliente))
                    .addContainerGap())
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodChale)
                        .addComponent(txtCodChale))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEstado)
                        .addComponent(txtEstado))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataInicio)
                        .addComponent(txtDataInicio))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataFim)
                        .addComponent(txtDataFim))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblQtdPessoas)
                        .addComponent(txtQtdPessoas))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDesconto)
                        .addComponent(txtDesconto))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValorFinal)
                        .addComponent(txtValorFinal))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodCliente)
                        .addComponent(txtCodCliente))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

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
                    .addContainerGap(50, Short.MAX_VALUE))
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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMensagem)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblMensagem)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPane.setLayout(contentPaneLayout);

        loadData();
    }

    private void loadData() {
        listaHospedagens = hospedagemController.listarTodos();
        DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
        tbm.setRowCount(0);
        for (Hospedagem hospedagem : listaHospedagens) {
            tbm.addRow(new Object[]{
            	hospedagem.getCodHospedagem(),
                hospedagem.getCodChale(),
                hospedagem.getEstado(),
                hospedagem.getData_inicio(),
                hospedagem.getData_fim(),
                hospedagem.getQtd_pessoas(),
                hospedagem.getDesconto(),
                hospedagem.getValor_final(),
                hospedagem.getCodCliente()
            });
        }
    }
}

package br.com.pc2.rh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pc2.rh.model.Hospedagem;

public class HospedagemDAOImpl implements HospedagemDAO{

	@Override
	public String inserir(Hospedagem h) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	        con = ConnectionFactory.getConnection();
	        con.setAutoCommit(false);

	        String sql = "INSERT INTO Hospedagem(codChale, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal, codCliente) VALUES(?,?,?,?,?,?,?,?)";
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, h.getCodChale());
	        pst.setString(2, h.getEstado());
	        pst.setDate(3, java.sql.Date.valueOf(h.getData_inicio()));
	        pst.setDate(4, java.sql.Date.valueOf(h.getData_fim()));
	        pst.setInt(5, h.getQtd_pessoas());
	        pst.setDouble(6, h.getDesconto());
	        pst.setDouble(7, h.getValor_final());
	        pst.setInt(8, h.getCodCliente());

	        int res = pst.executeUpdate();
	        con.commit();

	        return res > 0 ? "Inserido com sucesso" : "Erro ao inserir";
	    } catch (SQLException e) {
	        if (con != null) {
	            try {
	                con.rollback();
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        }
	        return "Erro ao inserir dados: " + e.getMessage();
	    } finally {
	        try {
	            if (pst != null) {
	                pst.close();
	            }
	            if (con != null) {
	                ConnectionFactory.close(con);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}




	public String alterar(Hospedagem h) {
	    String sql = "UPDATE Hospedagem SET codChale = ?, estado = ?, dataInicio = ?, dataFim = ?, qtdPessoas = ?, desconto = ?, valorFinal = ?, codCliente = ? WHERE codhospedagem = ?";
	    Connection con = ConnectionFactory.getConnection();
	    PreparedStatement pst = null;

	    try {
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, h.getCodChale());
	        pst.setString(2, h.getEstado());
	        pst.setObject(3, h.getData_inicio(), java.sql.Types.DATE);
	        pst.setObject(4, h.getData_fim(), java.sql.Types.DATE);
	        pst.setInt(5, h.getQtd_pessoas());
	        pst.setDouble(6, h.getDesconto());
	        pst.setDouble(7, h.getValor_final());
	        pst.setInt(8, h.getCodCliente());
	        pst.setInt(9, h.getCodHospedagem());

	        int res = pst.executeUpdate();

	        if (res > 0) {
	            return "Hospedagem atualizada com sucesso!";
	        } else {
	            return "Erro: Não foi possível atualizar a Hospedagem.";
	        }

	    } catch (SQLException e) {
	        return "Erro ao atualizar Hospedagem: " + e.getMessage();

	    } finally {
	        try {
	            if (pst != null) pst.close();
	            ConnectionFactory.close(con);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}




	public String excluir(Hospedagem h) {
	    String sql = "DELETE FROM Hospedagem WHERE codhospedagem = ?";
	    Connection con = ConnectionFactory.getConnection();

	    try {
	        if (h.getCodHospedagem() == null) {
	            return "Erro: Código da hospedagem não foi fornecido.";
	        }

	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, h.getCodHospedagem());

	        int res = pst.executeUpdate();

	        if (res > 0) {
	            return "Hospedagem excluída com sucesso!";
	        } else {
	            return "Erro: Não foi possível excluir a hospedagem. Hospedagem não encontrada.";
	        }

	    } catch (SQLException e) {
	        return "Erro ao excluir hospedagem: " + e.getMessage();
	    } finally {
	        ConnectionFactory.close(con);
	    }
	}





	public List<Hospedagem> listarTodos() {
	    String sql = "SELECT * FROM Hospedagem";
	    List<Hospedagem> lista = new ArrayList<>();
	    Connection con = ConnectionFactory.getConnection();
	    
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            Hospedagem hospedagem = new Hospedagem();
	            hospedagem.setCodHospedagem(rs.getInt("codhospedagem"));
	            hospedagem.setCodChale(rs.getInt("codChale"));
	            hospedagem.setEstado(rs.getString("estado"));
	            hospedagem.setData_inicio(rs.getDate("dataInicio").toLocalDate());
	            hospedagem.setData_fim(rs.getDate("dataFim").toLocalDate());
	            hospedagem.setQtd_pessoas(rs.getInt("qtdPessoas"));
	            hospedagem.setDesconto(rs.getDouble("desconto"));
	            hospedagem.setValor_final(rs.getDouble("valorFinal"));
	            hospedagem.setCodCliente(rs.getInt("codCliente"));
	            
	            lista.add(hospedagem);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.close(con);
	    }
	    
	    return lista;
	}

	
}

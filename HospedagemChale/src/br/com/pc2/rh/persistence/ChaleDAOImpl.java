package br.com.pc2.rh.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pc2.rh.model.Chale;

public class ChaleDAOImpl {
	
	public String inserir(Chale ch) {
	    String sql = "INSERT INTO Chale(localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao) values(?,?,?,?)";
	    Connection con = ConnectionFactory.getConnection();
	    PreparedStatement pst = null;
	    ResultSet generatedKeys = null;

	    try {
	        pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setString(1, ch.getLocalizacao());
	        pst.setInt(2, ch.getCapacidade());
	        pst.setDouble(3, ch.getValorAltaEstacao());
	        pst.setDouble(4, ch.getValorBaixaEstacao());

	        int res = pst.executeUpdate();

	        if (res > 0) {
	            generatedKeys = pst.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int codigoChale = generatedKeys.getInt(1);
	                ch.setCodChale(codigoChale);
	                return "Chalé inserido com sucesso! Código gerado: " + codigoChale;
	            }
	        }
	        return "Erro: Não foi possível inserir Chalé";

	    } catch (SQLException e) {
	        return "Erro ao inserir Chalé: " + e.getMessage();

	    } finally {
	        try {
	            if (generatedKeys != null) generatedKeys.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public String alterar(Chale ch) {

	    String sql = "UPDATE Chale SET localizacao = ?, capacidade = ?, valorAltaEstacao = ?, valorBaixaEstacao = ? WHERE codChale = ?";
	    Connection con = ConnectionFactory.getConnection();
	    PreparedStatement pst = null;

	    try {
	        pst = con.prepareStatement(sql);
	        pst.setString(1, ch.getLocalizacao());
	        pst.setInt(2, ch.getCapacidade());
	        pst.setDouble(3, ch.getValorAltaEstacao());
	        pst.setDouble(4, ch.getValorBaixaEstacao());
	        pst.setInt(5, ch.getCodChale()); 

	        int res = pst.executeUpdate();

	        if (res > 0) {
	            return "Chalé atualizado com sucesso!";
	        } else {
	            return "Erro: Não foi possível atualizar o Chalé.";
	        }

	    } catch (SQLException e) {
	        return "Erro ao atualizar Chalé: " + e.getMessage();

	    } finally {
	        try {
	            if (pst != null) pst.close();
	            ConnectionFactory.close(con);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public String excluir(Chale ch) {
	    String sql = "DELETE FROM Chale WHERE codChale = ?";
	    Connection con = ConnectionFactory.getConnection();
	    
	    try {
	        if (ch.getCodChale() == null) {
	            return "Erro: Código do chalé não foi fornecido.";
	        }
	        
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, ch.getCodChale());

	        int res = pst.executeUpdate();
	        
	        if (res > 0) {
	            return "Chalé excluído com sucesso!";
	        } else {
	            return "Erro: Não foi possível excluir o chalé. Chalé não encontrado.";
	        }
	        
	    } catch (SQLException e) {
	        return "Erro ao excluir chalé: " + e.getMessage();
	    } finally {
	        ConnectionFactory.close(con);
	    }
	}
	
	public List<Chale> listarTodos() {
	    String sql = "SELECT * FROM Chale ORDER BY localizacao";
	    List<Chale> lista = new ArrayList<>();
	    
	    try (Connection con = ConnectionFactory.getConnection();
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        if (!rs.isBeforeFirst()) {
	            System.out.println("Nenhum chalé encontrado no banco de dados.");
	            return lista;
	        }

	        while (rs.next()) {
	            Chale ch = new Chale();
	            ch.setCodChale(rs.getInt("codChale"));
	            ch.setLocalizacao(rs.getString("localizacao"));
	            ch.setCapacidade(rs.getInt("capacidade"));
	            ch.setValorAltaEstacao(rs.getDouble("valorAltaEstacao"));
	            ch.setValorBaixaEstacao(rs.getDouble("valorBaixaEstacao"));

	            lista.add(ch);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}
	
}





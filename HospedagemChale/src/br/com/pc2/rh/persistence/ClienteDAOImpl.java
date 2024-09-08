package br.com.pc2.rh.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pc2.rh.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	@Override
	public String inserir(Cliente cl) {
	    String sql = "INSERT INTO Cliente(nomecliente, rgcliente, endereco, bairro, cidade, estado, cep, datanascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    Connection con = ConnectionFactory.getConnection();
	    try {
	    	PreparedStatement pst = con.prepareStatement(sql);
	        pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setString(1, cl.getNome());
	        pst.setString(2, cl.getRg());
	        pst.setString(3, cl.getEndereco());
	        pst.setString(4, cl.getBairro());
	        pst.setString(5, cl.getCidade());
	        pst.setString(6, cl.getEstado());
	        pst.setString(7, cl.getCep());
	        pst.setObject(8, cl.getNascimento(), java.sql.Types.DATE);  

	        int res = pst.executeUpdate();

	        if (res > 0) {
	            ResultSet generatedKeys = pst.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int codClienteGerado = generatedKeys.getInt(1);
	                return "Inserido com sucesso! Código gerado: " + codClienteGerado;
	            }
	            return "Inserido com sucesso.";
	        } else {
	            return "Erro ao inserir.";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	        return "Erro ao inserir cliente: " + e.getMessage();
	    } finally {
	       ConnectionFactory.close(con);
	    }
	}


	@Override
	public String alterar(Cliente cl) {
	    String sql = "UPDATE Cliente SET nomeCliente = ?, endereco = ?, bairro = ?, cidade = ?, estado = ?, CEP = ?, dataNascimento = ? WHERE rgCliente = ?";
	    Connection con = ConnectionFactory.getConnection();
	    
	    try {
	    	PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, cl.getNome());
	        pst.setString(2, cl.getEndereco());
	        pst.setString(3, cl.getBairro());
	        pst.setString(4, cl.getCidade());
	        pst.setString(5, cl.getEstado());
	        pst.setString(6, cl.getCep());
	        pst.setObject(7, cl.getNascimento(), java.sql.Types.DATE);
	        pst.setString(8, cl.getRg()); 

	        int res = pst.executeUpdate();
	        if (res > 0) {
	            return "Alterado com sucesso.";
	        } else {
	            return "Erro ao alterar.";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	        return "Erro ao alterar cliente: " + e.getMessage();
	    } finally {
	        ConnectionFactory.close(con);
	    }
	}


	@Override
	public String excluir(Cliente cl) {
	    String sql = "DELETE FROM Cliente WHERE rgCliente = ?"; 
	    Connection con = ConnectionFactory.getConnection();
	    PreparedStatement pst = null;
	    try {
	        pst = con.prepareStatement(sql);
	        pst.setString(1, cl.getRg()); 

	        int res = pst.executeUpdate();
	        if (res > 0) {
	            return "Excluído com sucesso.";
	        } else {
	            return "Erro ao excluir.";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erro ao excluir cliente: " + e.getMessage();
	    } finally {
	        ConnectionFactory.close(con);
	    }
	}


	@Override
	public List<Cliente> listarTodos() {
	    String sql = "SELECT * FROM Cliente ORDER BY nomeCliente";
	    Connection con = ConnectionFactory.getConnection();
	    List<Cliente> lista = new ArrayList<>();

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();

	        if (!rs.isBeforeFirst()) {
	            System.out.println("Nenhum cliente encontrado no banco de dados.");
	            return lista;
	        }

	        while (rs.next()) {
	            Cliente cl = new Cliente();
	            cl.setCodCliente(rs.getInt("codCliente"));
	            cl.setNome(rs.getString("nomeCliente"));
	            cl.setRg(rs.getString("rgCliente"));
	            cl.setEndereco(rs.getString("endereco"));
	            cl.setBairro(rs.getString("bairro"));
	            cl.setCidade(rs.getString("cidade"));
	            cl.setEstado(rs.getString("estado"));
	            cl.setCep(rs.getString("CEP"));
	            
	            Date sqlDate = rs.getDate("dataNascimento");
	            if (sqlDate != null) {
	                cl.setNascimento(sqlDate.toLocalDate());
	            }

	            lista.add(cl);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.close(con);
	    }
	    return lista;
	}

}

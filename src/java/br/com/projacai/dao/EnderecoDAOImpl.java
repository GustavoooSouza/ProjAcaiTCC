/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.dao;

import br.com.projacai.model.Endereco;
import br.com.projacai.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuário
 */
public class EnderecoDAOImpl implements GenericDAO {

    private Connection conn;

    public EnderecoDAOImpl() throws Exception {
        //Construtor da Classe;
        try {
            this.conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Endereco oEndereco = (Endereco) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO endereco "
                + "(idCliente, " //Preciso do atributo da classe pai
                + "bairro, "
                + "rua, "
                + "numeroCasa, "
                + "complemento) "
                + "VALUES(?, ?, ?, ?, ?);";

        try {
            stmt = conn.prepareStatement(sql);
            //Como não é herança e sim associação é so isso Ok?
            
            stmt.setInt(1, oEndereco.getCliente().getIdCliente());
            stmt.setString(2, oEndereco.getBairro());
            stmt.setString(3, oEndereco.getRua());
            stmt.setInt(4, oEndereco.getNumeroCasa());
            stmt.setString(5, oEndereco.getComplemento());

            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar Cliente \n Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {        
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "Select * from endereco;";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Endereco oEndereco = new Endereco();
                oEndereco.setIdEndereco(rs.getInt("idEndereco"));
                oEndereco.setBairro(rs.getString("bairro"));
                oEndereco.setRua(rs.getString("rua"));
                oEndereco.setNumeroCasa(rs.getInt("numeroCasa"));
                oEndereco.setComplemento(rs.getString("complemento"));
                resultado.add(oEndereco);
            }
        } catch (SQLException ex){
            System.out.println("Erro ao listar endereco - dao"+ ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch(Exception ex) {
                System.out.println("Erro ao fechar parametros"+ ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override //AJUDA AQUIIII
    public void excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE from endereco "
                + " WHERE idEndereco = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir endereco - dao \n Erro : " +ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro : " +ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Endereco oEndereco = null; 
        
        String sql = "select * from endereco "
                   + "where idEndereco = ?; ";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                oEndereco = new Endereco();
                oEndereco.setBairro(rs.getString("bairro"));
                oEndereco.setRua(rs.getString("rua"));
                oEndereco.setNumeroCasa(rs.getInt("numeroCasa"));
                oEndereco.setComplemento(rs.getString("complemento"));
                oEndereco.setIdEndereco(rs.getInt("idEndereco"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar endereco - dao : "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao DAO \n Erro : "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    return oEndereco;
    }

    @Override
    public Boolean alterar(Object object) {
        Endereco oEndereco = (Endereco) object;
        PreparedStatement stmt = null;
        String sql = "update endereco set "
                + "bairro = ?, "
                + "rua = ?, "
                + "numeroCasa = ?, "
                + "complemento = ? "
                + "where idEndereco = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oEndereco.getBairro());
            stmt.setString(2, oEndereco.getRua());
            stmt.setInt(3, oEndereco.getNumeroCasa());
            stmt.setString(4, oEndereco.getComplemento());
            stmt.setInt(5, oEndereco.getIdEndereco());
            
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao alterar endereco DAO \n Erro : " +ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try{
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao DAO \n Erro : "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.dao;

import br.com.projacai.model.Produto;
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
public class ProdutoDAOImpl implements GenericDAO {
    //Conexão com o BD
    private Connection conn;

    public ProdutoDAOImpl() throws Exception {
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
        Produto oProduto = (Produto) object;
        
        PreparedStatement stmt = null;
        
        String sql="INSERT INTO produto " 
                   + "(nomeProduto, descricao, preco) "
                   + "VALUES(?, ?, ?);";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setString(2, oProduto.getDescricao());
            stmt.setDouble(3, oProduto.getPreco());
            stmt.execute();
            
            return true;
        } catch (Exception ex){
            System.out.println("Erro ao cadastar produto - DAO - \n Erro " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
            ConnectionFactory.closeConnection(conn, stmt);
            } catch(Exception ex) {
                System.out.println("Problemas ao fechar conexao \n Erro " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
                
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "Select * from produto;";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeProduto"));
                oProduto.setDescricao(rs.getString("descricao"));
                oProduto.setPreco(rs.getDouble("preco"));
                resultado.add(oProduto);
            }
        } catch (SQLException ex){
            System.out.println("Erro ao listar Produto"+ ex.getMessage());
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

    @Override
    public void excluir(int idObject) {
                PreparedStatement stmt = null;
        String sql = "DELETE from produto "
                + " WHERE idProduto = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir produto \n Erro : " +ex.getMessage());
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
        Produto oProduto = null; 
        
        String sql = "select * from produto "
                   + "where idProduto = ?; ";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                oProduto = new Produto();
                oProduto.setNomeProduto(rs.getString("nomeProduto"));
                oProduto.setDescricao(rs.getString("descricao"));
                oProduto.setPreco(rs.getDouble("preco"));
                oProduto.setIdProduto(rs.getInt("idProduto"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar produto : "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao DAO \n Erro : "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    return oProduto;
    }

    @Override
    public Boolean alterar(Object object) {
                
        Produto oProduto = (Produto) object;
        PreparedStatement stmt = null;
        String sql = "update produto set "
                + "nomeProduto = ?, "
                + "descricao = ?, "
                + "preco = ? "
                + "where idProduto = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setString(2, oProduto.getDescricao());
            stmt.setDouble(3, oProduto.getPreco());
            stmt.setInt(4, oProduto.getIdProduto());
            
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao alterar produto DAO \n Erro : " +ex.getMessage());
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

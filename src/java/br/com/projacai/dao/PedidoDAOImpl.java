/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.dao;

import br.com.projacai.model.Pedido;
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
public class PedidoDAOImpl implements GenericDAO{
    
    private Connection conn;

    public PedidoDAOImpl() throws Exception {
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
        Pedido oPedido = (Pedido) object;
        
        PreparedStatement stmt = null;
        
        String sql="INSERT INTO pedido " 
                   + "(dataPedido, qtdPedido) "
                   + "VALUES(?, ?);";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(oPedido.getDataPedido().getTime()));
            stmt.setInt(2, oPedido.getQtdPedido());
            stmt.execute();
            
            return true;
        } catch (Exception ex){
            System.out.println("Erro ao cadastar pedido - DAO - \n Erro " + ex.getMessage());
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
        
        String sql = "Select * from pedido;";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pedido oPedido = new Pedido();
                oPedido.setIdPedido(rs.getInt("idPedido"));
                oPedido.setDataPedido(rs.getDate("dataPedido"));
                oPedido.setQtdPedido(rs.getInt("qtdPedido"));
                resultado.add(oPedido);
            }
        } catch (SQLException ex){
            System.out.println("Erro ao listar pedidos - dao"+ ex.getMessage());
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
        String sql = "DELETE from pedido "
                + " WHERE idPedido = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir pedido - dao \n Erro : " +ex.getMessage());
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
        Pedido oPedido = null; 
        
        String sql = "select * from pedido "
                   + "where idPedido = ?; ";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                oPedido = new Pedido();
                oPedido.setDataPedido(rs.getDate("dataPedido"));
                oPedido.setQtdPedido(rs.getInt("qtdPedido"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar pedido - dao : "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao DAO \n Erro : "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    return oPedido;
    }

    @Override
    public Boolean alterar(Object object) {
                
        Pedido oPedido = (Pedido) object;
        PreparedStatement stmt = null;
        String sql = "update pedido set "
                + "dataPedido = ?, "
                + "qtdPedido = ? "
                + "where idPedido = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(oPedido.getDataPedido().getTime()));
            stmt.setInt(2, oPedido.getQtdPedido());
            stmt.setInt(3, oPedido.getIdPedido());
            
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao alterar pedido DAO \n Erro : " +ex.getMessage());
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

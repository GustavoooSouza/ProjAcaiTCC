/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.dao;

import br.com.projacai.model.Cliente;
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
public class ClienteDAOImpl implements GenericDAO{
    
        private Connection conn;

    public ClienteDAOImpl() throws Exception {
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
        Cliente oCliente = (Cliente) object;
        
        PreparedStatement stmt = null;
        
        String sql="INSERT INTO cliente " 
                   + "(cpf, senha, nomeCliente, telefone, dataNasc) "
                   + "VALUES(?, ?, ?, ?, ?);";
        
        try {
            
            //Aqui ele cadastra somente os dados do cliente.....
            //O Endereço vai ser em outro lugar
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oCliente.getCpf());
            stmt.setString(2, oCliente.getSenha());
            stmt.setString(3, oCliente.getNomeCliente());
            stmt.setString(4, oCliente.getTelefone());
            stmt.setDate(5, new java.sql.Date(oCliente.getDataNasc().getTime()));
            stmt.execute();
            
            return true;
        } catch (Exception ex){
            System.out.println("Erro ao cadastar cliente - DAO - \n Erro " + ex.getMessage());
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
        
        String sql = "Select * from cliente;";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente oCliente = new Cliente();
                oCliente.setIdCliente(rs.getInt("idCliente"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setNomeCliente(rs.getString("nomeCliente"));
                oCliente.setTelefone(rs.getString("telefone"));
                oCliente.setDataNasc(rs.getDate("dataNasc"));
                resultado.add(oCliente);
            }
        } catch (SQLException ex){
            System.out.println("Erro ao listar cliente - dao"+ ex.getMessage());
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
        String sql = "DELETE from cliente "
                + " WHERE idCliente = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir cliente - dao \n Erro : " +ex.getMessage());
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
        Cliente oCliente = null; 
        
        String sql = "select * from cliente "
                   + "where idCliente = ?; ";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                oCliente = new Cliente();
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setNomeCliente(rs.getString("nomeCliente"));
                oCliente.setTelefone(rs.getString("telefone"));
                oCliente.setDataNasc(rs.getDate("dataNasc"));
                oCliente.setIdCliente(rs.getInt("idCliente"));
                oCliente.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar cliente - dao : "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao DAO \n Erro : "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    return oCliente;
    }

    @Override
    public Boolean alterar(Object object) {
                
        Cliente oCliente = (Cliente) object;
        PreparedStatement stmt = null;
        String sql = "update cliente set "
                + "cpf = ?, "
                + "nomeCliente = ?, "
                + "telefone = ?, "
                + "dataNasc = ? "
                + "where idCliente = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oCliente.getCpf());
            stmt.setString(2, oCliente.getNomeCliente());
            stmt.setString(3, oCliente.getTelefone());
            stmt.setDate(4, new java.sql.Date(oCliente.getDataNasc().getTime()));
            stmt.setInt(5, oCliente.getIdCliente());
            
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao alterar cliente DAO \n Erro : " +ex.getMessage());
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
    
        public Cliente logarcliente(String cpf, String senha){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente oCliente = null;
        
        String sql = "Select * from cliente "
                + "Where cpf = ? "
                + "AND senha = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
                    
            if(rs.next()) {
                oCliente = new Cliente();
                oCliente.setIdCliente(rs.getInt("idCliente"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setNomeCliente(rs.getString("nomeCliente"));
                oCliente.setDataNasc(rs.getDate("dataNasc"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao permitir login CLIENTE \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oCliente;//Colcoa lá o usuario e a senha correto =)
    }
}

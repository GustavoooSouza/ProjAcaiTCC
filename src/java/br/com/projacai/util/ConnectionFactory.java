/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Usuário
 */
public class ConnectionFactory {
    //A primeira ação a ser feita é adicionar 
    //a biblioteca JDBC para utilizar o Driver
    //>> Botão Direito em cima de Bibliotecas 
    //>> Adicionar Bibliotecas
    //>> Driver JDBC do PostgreSQL
    
    //Método de conexão com BD
    public static Connection conectar() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/projacai";
        String user = "postgres";
        String password = "postdba";
        
        try{
            /*Chamar o Driver que vai fazer a conexão*/
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url,user,password);            
        }catch(Exception ex){
            throw new Exception("Erro ao conectar" + ex.getMessage());
        }        
    }

    //Fechar a Conexão
    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        try{
            if(conn!=null) conn.close();
            if(stmt!=null) stmt.close();
            if(rs!=null) rs.close();
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public static void closeConnection(Connection conn) throws Exception{
        close(conn, null, null);
    }
    
    public static void closeConnection(Connection conn, Statement stmt) throws Exception{
        close(conn, stmt, null);
    }
    
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        close(conn, stmt, rs);
    }
}

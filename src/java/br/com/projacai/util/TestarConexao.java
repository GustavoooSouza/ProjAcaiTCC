/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.util;

import br.com.projacai.util.ConnectionFactory;
import java.sql.Connection;

/**
 *
 * @author Usuário
 */
public class TestarConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Connection conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso");
        }catch(Exception ex){
            System.out.println("Não conectou");
        }
    }
    
}

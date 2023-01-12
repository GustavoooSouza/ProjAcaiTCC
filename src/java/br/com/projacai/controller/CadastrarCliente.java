/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.controller;

import br.com.projacai.dao.ClienteDAOImpl;
import br.com.projacai.dao.GenericDAO;
import br.com.projacai.model.Cliente;
import br.com.projacai.util.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuário
 */
@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastrarCliente"})
public class CadastrarCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Cliente oCliente = new Cliente();
            
            oCliente.setCpf(request.getParameter("cpf"));
            oCliente.setSenha(request.getParameter("senha"));
            oCliente.setNomeCliente(request.getParameter("nomeCliente"));
            oCliente.setTelefone(request.getParameter("telefone"));
            oCliente.setDataNasc(Conversao.converterData(request.getParameter("dataNasc")));
            
            String menssagem = "";
            
            try {
            GenericDAO dao = new ClienteDAOImpl();
            if (request.getParameter("idCliente").equals("")) {
                if (dao.cadastrar(oCliente)) {
                    menssagem = "Cliente" + oCliente.getNomeCliente() + " cadastrado com sucesso!!! Realize Login.";
                } else {
                    menssagem = "Cliente" + oCliente.getNomeCliente() + ", Erro ao cadastrar!!!";
                }
            } else {
                oCliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                if (dao.alterar(oCliente)) {
                    menssagem = "Cliente" + oCliente.getNomeCliente() + "Alteracao feita com sucesso";
                } else {
                    menssagem = "Cliente" + oCliente.getNomeCliente() + "Erro ao alterar";
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar cliente - Servlet \n Erro" + ex.getMessage());
        }
            
            request.setAttribute("menssagem", menssagem);
            request.getRequestDispatcher("ListarCliente").forward(request, response);
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

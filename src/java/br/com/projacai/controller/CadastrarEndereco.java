/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.controller;

import br.com.projacai.dao.EnderecoDAOImpl;
import br.com.projacai.dao.GenericDAO;
import br.com.projacai.model.Cliente;
import br.com.projacai.model.Endereco;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuário
 */
@WebServlet(name = "CadastrarEndereco", urlPatterns = {"/CadastrarEndereco"})
public class CadastrarEndereco extends HttpServlet {

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
            Endereco oEndereco = new Endereco();
            
            oEndereco.setBairro(request.getParameter("bairro"));
            oEndereco.setRua(request.getParameter("rua"));
            oEndereco.setNumeroCasa(Integer.parseInt(request.getParameter("numeroCasa")));
            oEndereco.setComplemento(request.getParameter("complemento"));
            oEndereco.setCliente(new Cliente(Integer.parseInt(request.getParameter("idCliente"))));
            
            String menssagem = "";
            
            try {
            GenericDAO dao = new EnderecoDAOImpl();
            if (request.getParameter("idEndereco").equals("")) {
                if (dao.cadastrar(oEndereco)) {
                    menssagem = "Endereco cadastrado com sucesso!!!";
                } else {
                    menssagem = "Endereco, Erro ao cadastrar!!!";
                }
            } else {
                oEndereco.setIdEndereco(Integer.parseInt(request.getParameter("idEndereco")));
                if (dao.alterar(oEndereco)) {
                    menssagem = "Endereco Alteracao feita com sucesso";
                } else {
                    menssagem = "Endereco, Erro ao alterar";
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar endereco - Servlet \n Erro" + ex.getMessage());
        }
            
            request.setAttribute("menssagem", menssagem);
            request.getRequestDispatcher("ListarEndereco").forward(request, response);
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

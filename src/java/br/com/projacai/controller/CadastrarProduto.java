/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.controller;

import br.com.projacai.dao.GenericDAO;
import br.com.projacai.dao.ProdutoDAOImpl;
import br.com.projacai.model.Produto;
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
@WebServlet(name = "CadastrarProduto", urlPatterns = {"/CadastrarProduto"})
public class CadastrarProduto extends HttpServlet {

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
            
            Produto oProduto = new Produto();
            
            oProduto.setNomeProduto(request.getParameter("nomeProduto"));
            oProduto.setDescricao(request.getParameter("descricao"));
            oProduto.setPreco(Double.parseDouble(request.getParameter("preco")));
            
            String menssagem = "";
            
            try {
            GenericDAO dao = new ProdutoDAOImpl();
            if (request.getParameter("idProduto").equals("")) {
                if (dao.cadastrar(oProduto)) {
                    menssagem = "Produto" + oProduto.getNomeProduto() + " cadastrado com sucesso!!!";
                } else {
                    menssagem = "Produto" + oProduto.getNomeProduto() + ", Erro ao cadastrar!!!";
                }
            } else {
                oProduto.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));
                if (dao.alterar(oProduto)) {
                    menssagem = "Produto" + oProduto.getNomeProduto() + "Alteracao feita com sucesso";
                } else {
                    menssagem = "Produto" + oProduto.getNomeProduto() + "Erro ao alterar";
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar produto - Servlet \n Erro" + ex.getMessage());
        }
            
            request.setAttribute("menssagem", menssagem);
            request.getRequestDispatcher("ListarProduto").forward(request, response);
    
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

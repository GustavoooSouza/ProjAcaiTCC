/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projacai.model;

import java.util.Date;

/**
 *
 * @author Usuário
 */
public class Pedido {
    private Integer idPedido;
    private Date dataPedido;
    private Integer qtdPedido;

    public Pedido(Integer idPedido, Date dataPedido, Integer qtdPedido) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.qtdPedido = qtdPedido;
    }

    public Pedido() {
    }

    /**
     * @return the idPedido
     */
    public Integer getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the dataPedido
     */
    public Date getDataPedido() {
        return dataPedido;
    }

    /**
     * @param dataPedido the dataPedido to set
     */
    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    /**
     * @return the qtdPedido
     */
    public Integer getQtdPedido() {
        return qtdPedido;
    }

    /**
     * @param qtdPedido the qtdPedido to set
     */
    public void setQtdPedido(Integer qtdPedido) {
        this.qtdPedido = qtdPedido;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.negocio;

import java.util.Date;

/**
 *
 * @author Gabriela
 */
public class RelatorioVenda {
    
    private int Pk_Venda;
    private boolean pago;
    private Date data;
    private String nome;
    private Double qtd;
    private Double total;

    public RelatorioVenda(int Pk_Venda, boolean pago, Date data, String nome, Double qtd, Double total) {
        this.Pk_Venda = Pk_Venda;
        this.pago = pago;
        this.data = data;
        this.nome = nome;
        this.qtd = qtd;
        this.total = total;
    }

    public int getPk_Venda() {
        return Pk_Venda;
    }

    public void setPk_Venda(int Pk_Venda) {
        this.Pk_Venda = Pk_Venda;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RelatorioVenda{" + "Pk_Venda=" + Pk_Venda + ", pago=" + pago + ", data=" + data + ", nome=" + nome + ", qtd=" + qtd + ", total=" + total + '}';
    }
    
    
    
    
}

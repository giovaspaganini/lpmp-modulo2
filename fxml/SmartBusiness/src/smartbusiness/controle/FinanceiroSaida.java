/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.controle;

import java.sql.Date;

/**
 *
 * @author administrado
 */
public class FinanceiroSaida {

    private int pk_financeiro;
    private int fk_compra;
    private Date data_emisao;
    private Date data_vencimento;
    private Date data_baixa;
    private double valor;
    private String tipo_documento;
    private String fornecedor;
    private String msg;

    private Fornecedor forne;

    public FinanceiroSaida(int pk_financeiro, int fk_compra, Date data_emisao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento) {
        this.pk_financeiro = pk_financeiro;
        this.fk_compra = fk_compra;
        this.data_emisao = data_emisao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
    }

    public FinanceiroSaida(int pk_financeiro, int fk_compra, Date data_emisao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento, String fornecedores) {
        this.pk_financeiro = pk_financeiro;
        this.fk_compra = fk_compra;
        this.data_emisao = data_emisao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
        this.fornecedor = fornecedores;

    }

    public FinanceiroSaida(int fk_compra, Date data_emisao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento) {

        this.fk_compra = fk_compra;
        this.data_emisao = data_emisao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;

    }

    public FinanceiroSaida(Fornecedor fornecedor, double total, String msg) {
        this.forne = fornecedor;
        this.valor = total;
        this.msg = msg;
    }

    public FinanceiroSaida() {
    }

    public int getPk_financeiro() {
        return pk_financeiro;
    }

    public void setPk_financeiro(int pk_financeiro) {
        this.pk_financeiro = pk_financeiro;
    }

    public int getFk_compra() {
        return fk_compra;
    }

    public void setFk_compra(int fk_compra) {
        this.fk_compra = fk_compra;
    }

    public Date getData_emisao() {
        return data_emisao;
    }

    public void setData_emisao(Date data_emisao) {
        this.data_emisao = data_emisao;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Date getData_baixa() {
        return data_baixa;
    }

    public void setData_baixa(Date data_baixa) {
        this.data_baixa = data_baixa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedor getForne() {
        return forne;
    }

    public void setForne(Fornecedor fornecedor) {
        this.forne = fornecedor;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "FinanceiroSaida{"
                + "pk_financeiro=" + pk_financeiro
                + ", fk_compra=" + fk_compra
                + ", data_emisao=" + data_emisao
                + ", data_vencimento=" + data_vencimento
                + ", data_baixa=" + data_baixa
                + ", valor=" + valor
                + ", tipo_documento="
                + tipo_documento
                + ", fornecedor=" +msg//+ getForne().getNome()
                + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.negocio;

/**
 *
 * @author L
 */
public class Cargo {
    private int pk;
    private String descricao;
    private double gratificacao;

    public Cargo(int pk, String descricao, double gratificacao) {
        this.pk = pk;
        this.descricao = descricao;
        this.gratificacao = gratificacao;
    }

    public Cargo(String descricao, double gratificacao) {
        this.descricao = descricao;
        this.gratificacao = gratificacao;
    }

    
    
    public Cargo() {
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getGratificacao() {
        return gratificacao;
    }

    public void setGratificacao(double gratificacao) {
        this.gratificacao = gratificacao;
    }

    @Override
    public String toString() {
        return "Cargo{" + "pk=" + pk + ", descricao=" + descricao + ", gratificacao=" + gratificacao + '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.controle;

/**
 *
 * @author L
 */
public class EnderecoFuncionario extends Endereco {

    private int fk_funcionario;
    private String pais, cep;

    /**
     *
     * @param pk_endereco recebe a chave primaria do endereço do funcionario
     * @param logradouro recebe o logradouro do endereco
     * @param bairro recebe o bairro do endereço
     * @param cidade recebe a cidade do endereço
     * @param estado recebe o estado do endereço
     * @param pais recebe o pais do endereço
     * @param cep recebe o cep do endereço
     */
    public EnderecoFuncionario(int pk_endereco, int fk_funcionario, String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
        super(pk_endereco, logradouro, bairro, cidade, estado, pais, cep);
        this.fk_funcionario = fk_funcionario;
        this.pais = pais;
        this.cep = cep;
    }

    public EnderecoFuncionario(int fk_funcionario, String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
        super(logradouro, bairro, cidade, estado, pais, cep);
        this.fk_funcionario = fk_funcionario;
        this.pais = pais;
        this.cep = cep;
    }

    public int getFk_funcionario() {
        return fk_funcionario;
    }

    public void setFk_funcionario(int fk_funcionario) {
        this.fk_funcionario = fk_funcionario;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return super.toString() + "EnderecoFuncionario{" + "fk_funcionario=" + fk_funcionario + ", pais=" + pais + ", cep=" + cep + '}';
    }

}

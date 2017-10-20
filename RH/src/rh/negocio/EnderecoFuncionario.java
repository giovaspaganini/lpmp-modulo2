/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.negocio;

/**
 *
 * @author L
 */
public class EnderecoFuncionario extends Endereco{
    
    private int fk_funcionario;

    public EnderecoFuncionario(int fk_funcionario, int pk_endereco, String logradouro, String bairro, String cidade, String estado) {
        super(pk_endereco, logradouro, bairro, cidade, estado);
        this.fk_funcionario = fk_funcionario;
    }

    public int getFk_funcionario() {
        return fk_funcionario;
    }

    public void setFk_funcionario(int fk_funcionario) {
        this.fk_funcionario = fk_funcionario;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + fk_funcionario; //To change body of generated methods, choose Tools | Templates.
    }


    
    
}

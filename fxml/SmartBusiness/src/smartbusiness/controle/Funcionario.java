/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.controle;

import java.util.ArrayList;

public class Funcionario {

    private int pk_funcionario;
    private String nome;
    private String cpf;

    private ArrayList<EnderecoFuncionario> endereco = new ArrayList<>();
    private Cargo cargo;

    /**
     *
     * @param pk_funcionario recebe a chave primaria do funcionario
     * @param nome recebe o nome do funcionario
     * @param cpf recebe o cpf do funcionario
     * @param cargo recebe o cargo do funcionario
     */
    public Funcionario(int pk_funcionario, String nome, String cpf, Cargo cargo) {
        this.pk_funcionario = pk_funcionario;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Funcionario(String nome, String cpf, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public int getPk_funcionario() {
        return pk_funcionario;
    }

    public void setPk_funcionario(int pk_funcionario) {
        this.pk_funcionario = pk_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<EnderecoFuncionario> getEndereco() {
        return endereco;
    }

    public void setEndereco(ArrayList<EnderecoFuncionario> endereco) {
        this.endereco = endereco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "pk_funcionario=" + pk_funcionario + ", nome=" + nome + ", CPF=" + cpf + ", endereco=" + endereco + ", cargo=" + cargo + "}\n";
    }

}

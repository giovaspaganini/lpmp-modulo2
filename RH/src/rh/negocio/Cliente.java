package rh.negocio;

import java.util.ArrayList;

public class Cliente {	
    private int pk_cliente;
    private String nome;
    private String cpf;
    
    private ArrayList<EnderecoCliente> endereco = new ArrayList<>();
    
    public Cliente(){        
    }
    
    public Cliente(int pk_cliente, String nome, String cpf) {
        this.pk_cliente = pk_cliente;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }    
    
    public ArrayList<EnderecoCliente> getEndereco() {
        return endereco;
    }

    public void setEndereco(ArrayList<EnderecoCliente> endereco) {
        this.endereco = endereco;
    }
    
    public int getPk_cliente() {
            return pk_cliente;
    }

    public void setPk_cliente(int pk) {
            this.pk_cliente = pk_cliente;
    }

    public String getNome() {
            return nome;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }

    public String getCpf() {
            return cpf;
    }

    public void setCpf(String cpf) {
            this.cpf = cpf;
    }

     @Override
     public String toString() {
          return "Cliente{" + "pk_cliente=" + pk_cliente + ", nome=" + nome + ", cpf=" + cpf +", endereco=" + endereco + '}';
     }

}

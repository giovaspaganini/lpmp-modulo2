package rh.negocio;

public class Cliente {	
    private int pk_cliente;
    private String nome;
    private String cpf;
    
    public Cliente(int pk_cliente, String nome, String cpf) {
        this.pk_cliente = pk_cliente;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
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
          return "Cliente{" + "pk_cliente=" + pk_cliente + ", nome=" + nome + ", cpf=" + cpf + '}';
     }

}

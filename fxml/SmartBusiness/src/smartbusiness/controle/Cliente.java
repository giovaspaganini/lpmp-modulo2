package smartbusiness.controle;

import java.util.ArrayList;

public class Cliente {	
    private int pk;
    private String nome;
    private String cpf;
    
    private ArrayList<EnderecoCliente> endereco = new ArrayList<>();
    
    public Cliente(){ 
    	
    }
    
    /**
	 * Método construtor de Cliente
	 *
	 * @param pk Chave primária de um cliente
	 * @param nome Nome do cliente
	 * @param cpf CPF do cliente
	 */
    public Cliente(int pk, String nome, String cpf) {
        this.pk = pk;
        this.nome = nome;
        this.cpf = cpf;
    }

    /**
	 * Método construtor de Cliente
	 *
	 * @param nome Nome do cliente
	 * @param cpf CPF do cliente
	 */
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }    
    
    /**
   	 * Método responsável por retornar os endereços do cliente
   	 *
   	 * @return Arraylist de Endereços de Cliente
   	 */
    public ArrayList<EnderecoCliente> getEndereco() {
        return endereco;
    }

    /**
   	 * Método responsável por setar um Arraylist de endereço
   	 *
   	 * @param endereco Arraylist de enderelços
   	 */
    public void setEndereco(ArrayList<EnderecoCliente> endereco) {
        this.endereco = endereco;
    }
    
    /**
   	 * Método responsável por retornar a chave primária do cliente
   	 *
   	 * @return chave primária
   	 */
    public int getPk() {
            return pk;
    }

    /**
   	 * Método responsável por setar uma chave primária
   	 *
   	 * @param pk chave primária do cliente
   	 */
    public void setPk(int pk) {
            this.pk = pk;
    }

    /**
   	 * Método responsável por recuperar o nome do cliente
   	 * @return Nome do usuário
   	 */
    public String getNome() {
            return nome;
    }

    /**
   	 * Método responsável por setar o nome do usuário
   	 *
   	 * @param nome Nome do usuário
   	 */
    public void setNome(String nome) {
            this.nome = nome;
    }

    /**
   	 * Método responsável por recuperar o CPF do usuário
   	 * @return CPF usuário
   	 */
    public String getCpf() {
            return cpf;
    }

    /**
   	 * Método responsável por setar o CPF do usuário
   	 *
   	 * @param cpf CPF do usuário
   	 */
    public void setCpf(String cpf) {
            this.cpf = cpf;
    }

    /**
   	 * Método responsável por transformar o Cliente em String
   	 * @return String Cliente
   	 */
     @Override
     public String toString() {
          return "Cliente{" + "pk_cliente=" + pk + ", nome=" + nome + ", cpf=" + cpf +", endereco=" + endereco + '}';
     }

}

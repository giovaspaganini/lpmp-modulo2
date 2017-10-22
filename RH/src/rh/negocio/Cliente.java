package rh.negocio;

public class Cliente {
	
	private int pk;
    private String nome;
    private String cpf;
    
    public Cliente(int pk, String nome, String cpf) {
        this.pk = pk;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    
	public int getPk() {
		return pk;
	}
	
	public void setPk(int pk) {
		this.pk = pk;
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
	      return "Cliente{" + "pk=" + pk + ", nome=" + nome + ", cpf=" + cpf + '}';
	 }

}

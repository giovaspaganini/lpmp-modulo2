package rh.negocio;

public class ClienteEndereco {
	
	private int pk;
	private int pkCliente;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
	
	public ClienteEndereco(int pk, int pkCliente, String logradouro, String bairro, String cidade, String estado,
			String pais, String cep) {
		super();
		this.pk = pk;
		this.pkCliente = pkCliente;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}	

	public ClienteEndereco(int pkCliente, String logradouro, String bairro, String cidade, String estado, String pais,
			String cep) {
		super();
		this.pkCliente = pkCliente;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}
	
	public ClienteEndereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
		super();
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}

	public int getPk() {
		return pk;
	}
	
	public void setPk(int pk) {
		this.pk = pk;
	}
	
	public int getPkCliente() {
		return pkCliente;
	}
	
	public void setPkCliente(int pkCliente) {
		this.pkCliente = pkCliente;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
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
		return "ClienteEndereco{pk=" + pk + ", pkCliente=" + pkCliente + ", logradouro=" + logradouro + ", bairro="
				+ bairro + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", cep=" + cep + "}";
	}
	
	
	
	
	
	
	
	

}

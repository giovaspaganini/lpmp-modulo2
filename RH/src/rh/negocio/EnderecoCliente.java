package rh.negocio;

public class EnderecoCliente extends Endereco{    
    private int fk_cliente;

    public EnderecoCliente(int fk_cliente, int pk_endereco, String logradouro, String bairro, String cidade, String estado) {
        super(pk_endereco, logradouro, bairro, cidade, estado);
        this.fk_cliente = fk_cliente;
    }

    public EnderecoCliente(int pk_endereco, String logradouro, String bairro, String cidade, String estado) {
    	super(pk_endereco, logradouro, bairro, cidade, estado);
	}

    public EnderecoCliente(String logradouro, String bairro, String cidade, String estado) {
    	super(logradouro, bairro, cidade, estado);
	}

	public int getFk_cliente() {
        return fk_cliente;
    }

    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }
    
    
}

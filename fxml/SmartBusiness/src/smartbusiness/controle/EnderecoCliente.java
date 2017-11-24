package smartbusiness.controle;


/**
 * Classe resposário pelo endereco de um cliente, essa classe se extende da classe Endereco
 *  
 */


public class EnderecoCliente extends Endereco{    
    private int fk_cliente;
    
    /**
   	 * Método contrutor de EnderecoCliente
   	 *
   	 * @param pk_endereco chave primárica de endereco
   	 * @param logradouro logadouro do cliente
   	 * @param bairro bairro do cliente
   	 * @param cidade cidade do cliente
   	 * @param estado estado do cliente
   	 */
    public EnderecoCliente(int pk_endereco, String logradouro, String bairro, String cidade, String estado,  String pais, String cep) {
        super(pk_endereco, logradouro, bairro, cidade, estado, pais, cep);
    }

    /**
   	 * Método contrutor de EnderecoCliente
   	 *
   	 * @param pk_endereco chave primárica de endereco
   	 * @param logradouro logadouro do cliente
   	 * @param bairro bairro do cliente
   	 * @param cidade cidade do cliente
   	 * @param estado estado do cliente
   	 */
    public EnderecoCliente(int pk_endereco, int fk_cliente, String logradouro, String bairro, String cidade, String estado,  String pais, String cep) {
        super(pk_endereco, logradouro, bairro, cidade, estado, pais, cep);
        this.fk_cliente = fk_cliente;
    }

    /**
   	 * Método contrutor de EnderecoCliente
   	 *
   	 * @param logradouro logadouro do cliente
   	 * @param bairro bairro do cliente
   	 * @param cidade cidade do cliente
   	 * @param estado estado do cliente
   	 */
    public EnderecoCliente(String logradouro, String bairro, String cidade, String estado,  String pais, String cep) {
    	super(logradouro, bairro, cidade, estado, pais, cep);
	}

    /**
   	 * Método responsável por recuperar a chave estrangeira do cliente
   	 * @return retorna a chave estrangeira de cliente
   	 */
	public int getFk_cliente() {
        return fk_cliente;
    }

	/**
   	 * Método responsável por
   	 *
   	 * @param fk_cliente seta a chave estrangeira de EnderecoCliente
   	 */
    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }
    
    
}

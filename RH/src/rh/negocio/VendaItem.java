package rh.negocio;


/**
 * Classe modelo de VendaItem
 *  
 */


public class VendaItem {
	
	private int pk;
	private int fkVenda;
	private int fkProduto;
	private int qtd;
	private double valorUnitario;
	
	/**
   	 * Método construtor de VendaItem
   	 *
   	 * @param pk chave primária de VendaItem
   	 * @param qtd quantidade de produtos
   	 * @param fkProduto chave estrangeira de produto
   	 * @param valorUnitario valor unitadio de VendaItem
   	 * @return
   	 */	
	public VendaItem(int pk, int fkVenda, int fkProduto, int qtd, double valorUnitario) {
		super();
		this.pk = pk;
		this.fkVenda = fkVenda;
		this.fkProduto = fkProduto;
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}

	/**
   	 * Método construtor de VendaItem
   	 *
   	 * @param fkVenda chave estrangeira de venda
   	 * @param fkProduto chave estrangeira de produto
   	 * @param valorUnitario valor unitário VendaItem
   	 * @param qtd quantidade de produtos vendidos
   	 */
	public VendaItem(int fkVenda, int fkProduto, int qtd, double valorUnitario) {
		super();
		this.fkVenda = fkVenda;
		this.fkProduto = fkProduto;
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}

	/**
   	 * Método construtor de VendaItem
   	 *
   	 * @param fkProduto chave estrangeira de produto
   	 * @param qtd quantidade de produto vendidos
   	 * @param valorUnitario valor unitário do pruduto
   	 */
	public VendaItem(int fkProduto, int qtd, double valorUnitario) {
		super();
		this.fkProduto = fkProduto;
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}

	/**
   	 * Método construtor de VendaItem
   	 *
   	 * @param qtd quantidade de itens vendidos
   	 * @param valorUnitario valor unitário do produto vendido
   	 */
	public VendaItem(int qtd, double valorUnitario) {
		super();
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}
	
	/**
   	 * Método responsável por retornar a chave primária de VendaItem
   	 * @return Chave primária de VendaItem
   	 */
	public int getPk() {
		return pk;
	}

	/**
   	 * Método responsável por setar a chave primária de VendaItem
   	 *
   	 * @param pk chave primária
   	 */
	public void setPk(int pk) {
		this.pk = pk;
	}

	/**
   	 * Método responsável por recuperar a chave estrangeira de Venda
   	 *  @return chave estrangeira
   	 */
	public int getFkVenda() {
		return fkVenda;
	}

	/**
   	 * Método responsável por setar a chave estrangeira de Venda
   	 *
   	 * @param fkVenda chave estrangeira de Venda
   	 */
	public void setFkVenda(int fkVenda) {
		this.fkVenda = fkVenda;
	}

	/**
   	 * Método responsável por retornar a chave estrangeira de produtos
   	 * @return chave estrangeira de Produto
   	 */
	public int getFkProduto() {
		return fkProduto;
	}

	/**
   	 * Método responsável por setar a chave estrangeira de produto
   	 *
   	 * @param fkProduto chave estrangeira de produto
   	 */
	public void setFkProduto(int fkProduto) {
		this.fkProduto = fkProduto;
	}

	/**
   	 * Método responsável por retorna a quantidade de itens vendidos
   	 *  @return quantidade de itens vendidos
   	 */
	public int getQtd() {
		return qtd;
	}

	/**
   	 * Método responsável por setar a quantidade de itens vendidos
   	 *
   	 * @param qtd quantidade de itens vendidos
   	 */
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	/**
   	 * Método responsável por retornar o valor unitário do item
   	 * @return valor unitário do item vendido
   	 */
	public double getValorUnitario() {
		return valorUnitario;
	}

	/**
   	 * Método responsável por setar o valor unitário do Item
   	 *
   	 * @param valorUnitario valor unitário do Item vendido
   	 */
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
   	 * Método responsável por transpormar VendaItem em String
   	 * @return Objeto VendaItem em String
   	 */
	@Override
	public String toString() {
		return "VendaItem{pk=" + pk + ", fkVenda=" + fkVenda + ", fkProduto=" + fkProduto + ", qtd=" + qtd
				+ ", valorUnitario=" + valorUnitario + "}";
	}
	
}

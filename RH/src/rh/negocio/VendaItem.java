package rh.negocio;

import java.util.Date;

public class VendaItem {
	
	private int pk;
	private int fkVenda;
	private int fkProduto;
	private int qtd;
	private double valorUnitario;	
	
	public VendaItem(int pk, int fkVenda, int fkProduto, int qtd, double valorUnitario) {
		super();
		this.pk = pk;
		this.fkVenda = fkVenda;
		this.fkProduto = fkProduto;
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}

	public VendaItem(int fkVenda, int fkProduto, int qtd, double valorUnitario) {
		super();
		this.fkVenda = fkVenda;
		this.fkProduto = fkProduto;
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}

	public VendaItem(int fkProduto, int qtd, double valorUnitario) {
		super();
		this.fkProduto = fkProduto;
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}

	public VendaItem(int qtd, double valorUnitario) {
		super();
		this.qtd = qtd;
		this.valorUnitario = valorUnitario;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public int getFkVenda() {
		return fkVenda;
	}

	public void setFkVenda(int fkVenda) {
		this.fkVenda = fkVenda;
	}

	public int getFkProduto() {
		return fkProduto;
	}

	public void setFkProduto(int fkProduto) {
		this.fkProduto = fkProduto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public String toString() {
		return "VendaItem{pk=" + pk + ", fkVenda=" + fkVenda + ", fkProduto=" + fkProduto + ", qtd=" + qtd
				+ ", valorUnitario=" + valorUnitario + "}";
	}
	
}

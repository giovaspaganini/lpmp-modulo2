package smartbusiness.controle;

import java.util.ArrayList;
import java.util.Date;

/**
 * Método responsável pela Venda
 */

public class Venda {
    private int pk;
    private int fkCliente;
    private int fkVendedor;
    private int numero;
    private Date data;
    private ArrayList<VendaItem> itens;
    /**
   	 * Método construtor de Venda
   	 *
   	 * @param pk chave primária da venda
   	 * @param fkCliente chave estrangeira de cliente
   	 * @param fkVendedor chave estrangeira do vendedor
   	 * @param numero numero da venda
   	 * @param data data da venda
   	 */
    public Venda(int pk, int fkCliente, int fkVendedor, int numero, Date data, VendaItem itens) {
            super();
            this.pk = pk;
            this.fkCliente = fkCliente;
            this.fkVendedor = fkVendedor;
            this.numero = numero;
            this.data = data;
            getItens().add(itens);
    }
    
    public Venda(int pk, int fkCliente, int fkVendedor, int numero, Date data) {
            super();
            this.pk = pk;
            this.fkCliente = fkCliente;
            this.fkVendedor = fkVendedor;
            this.numero = numero;
            this.data = data;
    }


    /**
   	 * Método construtor de Venda
   	 *
   	 * @param fkCliente chave estrangeira de cliente
   	 * @param fkVendedor chave estrangeira do vendedor
   	 * @param numero numero da venda
   	 * @param data data da venda
   	 */
    public Venda(int fkCliente, int fkVendedor, int numero, Date data, VendaItem itens) {
            super();
            this.fkCliente = fkCliente;
            this.fkVendedor = fkVendedor;
            this.numero = numero;
            this.data = data;
            getItens().add(itens);
    }

    	
    /**
   	 * Método construtor de Venda
   	 *
   	 * @param fkVendedor chave estrangeira do vendedor
   	 * @param numero numero da venda
   	 * @param data data da venda
   	 */
    public Venda(int fkVendedor, int numero, Date data) {
            super();
            this.fkVendedor = fkVendedor;
            this.numero = numero;
            this.data = data;
    }

    /**
   	 * Método construtor de Venda
   	 *
   	 * @param numero numero da venda
   	 * @param data data da venda
   	 */
    public Venda(int numero, Date data) {
            super();
            this.numero = numero;
            this.data = data;
    }

    /**
   	 * Método responsável por retornar a chave primaria de Venda
   	 *
   	 * @return chave primaria
   	 */
    public int getPk() {
            return pk;
    }

    /**
   	 * Método responsável por setar chave primaria de Venda
   	 *
   	 * @param pk chave primaria
   	 */
    public void setPk(int pk) {
            this.pk = pk;
    }

    /**
   	 * Método responsável por retornar a chave estrangeira de Cliente
   	 * @return chave estrangeira de cliente
   	 */
    public int getFkCliente() {
            return fkCliente;
    }

    /**
   	 * Método responsável por setar a chave estrangeira de cliente
   	 *
   	 * @param fkCliente chave estrangeira de cliente
   	 */
    public void setFkCliente(int fkCliente) {
            this.fkCliente = fkCliente;
    }

    /**
   	 * Método responsável por recuperar a chave estrangeira do Vendedor
   	 * @return chave estrangeira do vendedor
   	 */
    public int getFkVendedor() {
            return fkVendedor;
    }

    /**
   	 * Método responsável por setar chave estrangeira do Vendedor
   	 *
   	 * @param fkVendedor chave estrangeira de Vendedor
   	 */
    public void setFkVendedor(int fkVendedor) {
            this.fkVendedor = fkVendedor;
    }

    /**
   	 * Método responsável por recupera número da Venda
   	 *  @return numero da venda
   	 */
    public int getNumero() {
            return numero;
    }

    /**
   	 * Método responsável por seta o búmero da Venda
   	 *
   	 * @param numero numero da venda
   	 */
    public void setNumero(int numero) {
            this.numero = numero;
    }

    /**
   	 * Método responsável por recuperar a data da Venda
   	 * @return Data da venda
   	 */
    public Date getData() {
            return data;
    }	

    /**
   	 * Método responsável por setar a data da venda
   	 *
   	 * @param data Data da venda
   	 */
    public void setData(Date data) {
            this.data = data;
    }

    public ArrayList<VendaItem> getItens() {
        if (itens == null){
            itens = new ArrayList<>();
        }
        return itens;
    }

    public void setItens(ArrayList<VendaItem> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Venda{" + "pk=" + pk + ", fkCliente=" + fkCliente + ", fkVendedor=" + fkVendedor + ", numero=" + numero + ", data=" + data + ", itens=" + getItens() + '}';
    }

    
    
    /**
   	 * Método responsável por transformar a classe Venda em String
   	 * @return Classe Venda em String
   	 */

    

}

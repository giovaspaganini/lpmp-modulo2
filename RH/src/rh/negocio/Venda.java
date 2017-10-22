package rh.negocio;

import java.util.Date;

public class Venda {
    private int pk;
    private int fkCliente;
    private int fkVendedor;
    private int numero;
    private Date data;

    public Venda(int pk, int fkCliente, int fkVendedor, int numero, Date data) {
            super();
            this.pk = pk;
            this.fkCliente = fkCliente;
            this.fkVendedor = fkVendedor;
            this.numero = numero;
            this.data = data;
    }

    public Venda(int fkCliente, int fkVendedor, int numero, Date data) {
            super();
            this.fkCliente = fkCliente;
            this.fkVendedor = fkVendedor;
            this.numero = numero;
            this.data = data;
    }

    public Venda(int fkVendedor, int numero, Date data) {
            super();
            this.fkVendedor = fkVendedor;
            this.numero = numero;
            this.data = data;
    }

    public Venda(int numero, Date data) {
            super();
            this.numero = numero;
            this.data = data;
    }

    public int getPk() {
            return pk;
    }

    public void setPk(int pk) {
            this.pk = pk;
    }

    public int getFkCliente() {
            return fkCliente;
    }

    public void setFkCliente(int fkCliente) {
            this.fkCliente = fkCliente;
    }

    public int getFkVendedor() {
            return fkVendedor;
    }

    public void setFkVendedor(int fkVendedor) {
            this.fkVendedor = fkVendedor;
    }

    public int getNumero() {
            return numero;
    }

    public void setNumero(int numero) {
            this.numero = numero;
    }

    public Date getData() {
            return data;
    }	

    public void setData(Date data) {
            this.data = data;
    }

    @Override
    public String toString() {
            return "Venda{pk=" + pk + ", fkCliente=" + fkCliente + ", fkVendedor=" + fkVendedor + ", numero=" + numero
                            + ", data=" + data + "}";
    }	

}

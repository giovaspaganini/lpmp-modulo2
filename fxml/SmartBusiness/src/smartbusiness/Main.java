
package smartbusiness;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import smartbusiness.modelo.CompraDAO;
import smartbusiness.modelo.CompraItemDAO;
import smartbusiness.modelo.FinanceiroSaidaDAO;
import smartbusiness.modelo.FornecedorDAO;
import smartbusiness.modelo.FornecedorEnderecoDAO;
import smartbusiness.modelo.ProdutoDAO;
import smartbusiness.modelo.VendaDAO;
import smartbusiness.controle.Compra;
import smartbusiness.controle.CompraItem;
import smartbusiness.controle.Fornecedor;
import smartbusiness.controle.FornecedorEndereco;
import smartbusiness.controle.Produto;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        System.out.println(VendaDAO.retrieveAll());   
        System.out.println(FinanceiroSaidaDAO.retrieveAll());
        Compra c = new Compra();
        Fornecedor f = FornecedorDAO.retrieve(2);
        c.setFornecedor(f);
        c.setData(Date.valueOf(LocalDate.of(2017, Month.MARCH, 22)));
        c.setNumero(666);
        
        ArrayList<Produto> prods = ProdutoDAO.retrieveAll();
        
        CompraItem i1 = new CompraItem(prods.get(1), 10, 50);
        CompraItem i2 = new CompraItem(prods.get(2), 10, 50);
        
        c.getItens().add(i1);
        c.getItens().add(i2);
        
        CompraDAO.create(c);
        
    }    
}
   
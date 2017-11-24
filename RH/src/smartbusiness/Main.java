package smartbusiness;

import java.sql.SQLException;
import java.util.Date;
import smartbusiness.modelo.ClienteDAO;
import smartbusiness.negocio.Cliente;
import smartbusiness.modelo.ClienteEnderecoDAO;
import smartbusiness.negocio.EnderecoCliente;
import smartbusiness.modelo.VendaDAO;
import smartbusiness.modelo.VendaItemDAO;
import smartbusiness.negocio.Venda;
import smartbusiness.negocio.VendaItem;

public class Main {

    public static void main(String[] args) throws SQLException  {    
        
        
        System.out.println(VendaDAO.retrieveRelatorioVenda());
        
    }
    
}

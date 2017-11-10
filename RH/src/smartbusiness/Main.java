package smartbusiness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import smartbusiness.modelo.BancoDados;
import smartbusiness.modelo.CargoDAO;
import smartbusiness.modelo.ClienteDAO;
import smartbusiness.modelo.VendaDAO;
import smartbusiness.negocio.Funcionario;
import smartbusiness.negocio.Venda;
import smartbusiness.negocio.VendaItem;
import smartbusiness.negocio.Cargo;
import smartbusiness.negocio.Cliente;
import smartbusiness.negocio.Endereco;
import smartbusiness.negocio.EnderecoCliente;
import smartbusiness.visao.TelaCargo;


public class Main {

    public static void main(String[] args) throws SQLException  {    
        
        System.out.println(ClienteDAO.retrieveByCidade("Morrinhos"));
        
    }
    
}

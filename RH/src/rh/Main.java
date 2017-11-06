package rh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import rh.modelo.BancoDados;
import rh.modelo.CargoDAO;
import rh.modelo.ClienteDAO;
import rh.negocio.Funcionario;
import rh.negocio.Venda;
import rh.negocio.VendaItem;
import rh.negocio.Cargo;
import rh.negocio.Cliente;
import rh.negocio.Endereco;
import rh.negocio.EnderecoCliente;
import rh.visao.TelaCargo;


public class Main {

    public static void main(String[] args) throws SQLException  {
      
        System.out.println(ClienteDAO.retrieveByCidade("Goiania"));    	
    }
    
}

package rh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.modelo.BancoDados;
import rh.modelo.CargoDAO;
import rh.negocio.Funcionario;
import rh.negocio.Cargo;
import rh.negocio.Cliente;
import rh.negocio.ClienteEndereco;
import rh.negocio.Endereco;
import rh.visao.TelaCargo;


public class Main {

    public static void main(String[] args) throws SQLException  {
        
    	Cliente cliente = new Cliente(10, "luiz", "05415751122");
    	System.out.println(cliente);
    	
    	ClienteEndereco clienteEndereco = new ClienteEndereco(10, 10, "Rua 14 13", "Jardim Goiás", "Morrinhos", "Goiás", "Brasil", "75650000");
    	System.out.println(clienteEndereco);

    	
    	
    }
    
}

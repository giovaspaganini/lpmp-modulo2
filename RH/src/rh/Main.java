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
import rh.negocio.Endereco;
import rh.visao.TelaCargo;


public class Main {

    public static void main(String[] args) throws SQLException  {
        
    	Cliente cliente = new Cliente(10, "luiz", "05415751122");
    	System.out.println(cliente);
    	
    	
    	
    	
    	
    }
    
}

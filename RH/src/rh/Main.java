/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.modelo.BancoDados;
import rh.modelo.CargoDAO;
import rh.negocio.Funcionario;
import rh.negocio.Cargo;
import rh.negocio.Endereco;
import rh.visao.TelaCargo;

/**
 *
 * @author L
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
        
         Cargo t = CargoDAO.retrieve(10);
         
         System.out.println(t);
         
         t.setGratificacao(10000);
         
         CargoDAO.update(t);
         
        
//        Cargo a1 =  new Cargo("Analista", 300);
//        CargoDAO.create(a1);
//        
//        Cargo a2 =  new Cargo("DBA", 300);
//        CargoDAO.create(a2);
//        
//        System.out.println(a1);
//        System.out.println(a2);
        
        //new TelaCargo().setVisible(true);
        
/**        Funcionario f = new Funcionario("Pedro", 
                2500, 
                new Dependente("Lucas", "Filho"), 
                new Endereco("Rua Tal", "St Tal", "Mhs", "GO"), 
                new Cargo("Gerente", 400));
        
        Dependente d = new Dependente("Joana", "Esposa");//instaciacao declarativa
        
        f.getDependentes().add(d);//passada por referencia
        f.getDependentes().add(new Dependente("Lia", "Filha"));//instaciacao anonima
        
        f.getEndereco().add(new Endereco("Rua Fulano", "St Ciclano", "Gyn", "GO"));
        
        System.out.println(f);
*/
    }
    
}

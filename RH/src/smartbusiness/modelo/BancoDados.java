/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import smartbusiness.negocio.Cargo;

/**
 *
 * @author L
 */
public class BancoDados {
    
    private static Connection conn;
    
    private BancoDados(){
        
    }
    
    public static Connection createConnection() {
        if (conn != null){
            return conn;
        }
        
                
        try {         
            Class.forName(DBConfig.DRIVER);
            conn = DriverManager.getConnection(
                        DBConfig.URL,
                        DBConfig.USR, 
                        DBConfig.PWD);           
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    
}

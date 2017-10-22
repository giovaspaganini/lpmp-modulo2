package rh.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rh.negocio.Venda;

/**
 *
 * @author Giovani Paganini <giovanipaganini@outlook.com>
 */
public class VendaDAO {
    public int create(Venda v) throws SQLException {
        Connection conn = BancoDados.createConnection();
        
        String sql = "INSERT INTO vendas(fk_cliente, fk_vendedor, numero, data VALUES (?, ?, ?, ? )";
        
        PreparedStatement stm = conn.prepareStatement(sql,
                   PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, v.getFkCliente());
        stm.setInt(2, v.getFkVendedor());
        stm.setInt(3, v.getNumero());
        stm.setDate(4, (Date) v.getData());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        
               
        
        return 0;        
    }
}

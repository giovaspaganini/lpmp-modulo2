package rh.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.Cliente;
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
        
        v.setPk(rs.getInt(1));
        
        stm.close();
        
        return v.getPk();        
    }
    
    public static void delete(Venda v) throws SQLException{
        if (v.getPk()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primaria não configurada");
        }
        
        String sql = "delete from vendas where pk_venda=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, v.getPk());
        stm.execute();
        stm.close();
    } 
    
    public static Venda retrieve(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("select * from vendas where pk_venda=?");
        stm.setInt(1, pk);
        
        stm.execute();
        
        ResultSet rs = stm.getResultSet();
        
        rs.next();
        
        
        return new Venda(rs.getInt("pk_venda"),
                         rs.getInt("fk_cliente"),
                         rs.getInt("fk_vendedor"),
                         rs.getInt("numero"),
                         rs.getDate("data")
        );
        
    }
    
        public static void update(Venda v) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "UPDATE vendas SET fk_cliente=?, fk_vendedor=?, numero=?, data=? WHERE pk_venda=?";

        PreparedStatement stm = conn.prepareCall(sql);

        stm.setInt(1, v.getFkCliente());
        stm.setInt(2, v.getFkVendedor());
        stm.setInt(3, v.getNumero());
        stm.setDate(4, (Date) v.getData());

        stm.execute();

    }
        
    public static void delete(int id) throws SQLException{
        if (id ==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primaria não configurada");
        }
        
        String sql = "delete from vendas where pk_venda=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, id);
        stm.execute();
        stm.close();
    } 

    public static ArrayList<Venda> retrieveAll() throws SQLException{
        
        ArrayList<Venda> aux = new ArrayList<>();
        
        Connection conn = BancoDados.createConnection();
        
        String sql = "select * from vendas";
              
        ResultSet rs = conn.createStatement().executeQuery(sql);
        
        while (rs.next()){
            Venda v = new Venda(rs.getInt("pk_venda"),
                         rs.getInt("fk_cliente"),
                         rs.getInt("fk_vendedor"),
                         rs.getInt("numero"),
                         rs.getDate("data"));
            
            aux.add(v);
        }
        
        return aux;
    }
     
    public static ArrayList<Venda> retriveByCliente(Cliente c) throws SQLException{
        
        
         ArrayList<Venda> aux = new ArrayList<>();
        
        Connection conn = BancoDados.createConnection();
        
        String sql = "SELECT * FROM vendas where fk_cliente = ?";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, c.getPk());
        stm.execute();
              
        ResultSet rs = stm.getGeneratedKeys();
        
        
        while (rs.next()){
            
            Venda v = new Venda(rs.getInt("pk_venda"),
                         rs.getInt("fk_cliente"),
                         rs.getInt("fk_vendedor"),
                         rs.getInt("numero"),
                         rs.getDate("data"));
            
            aux.add(v);
        }
        
        return aux;
        
        
        
    }
}

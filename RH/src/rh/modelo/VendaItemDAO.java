/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.VendaItem;

/**
 *
 * @author Gabriela
 */
public class VendaItemDAO {

    public int create(VendaItem vi) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "INSERT INTO vendas(fk_venda, fk_produto, qtd, valorUnitario VALUES (?, ?, ?, ? )";

        PreparedStatement stm = conn.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, vi.getFkVenda());
        stm.setInt(2, vi.getFkProduto());
        stm.setInt(3, vi.getQtd());
        stm.setDouble(4, vi.getValorUnitario());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();

        rs.next();

        vi.setPk(rs.getInt(1));

        stm.close();

        return vi.getPk();
    }

    public static VendaItem retrieve(int pk_vendaitem) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "select * from vendaItem where pk_vendaitem = ?";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, pk_vendaitem);

        stm.execute();

        ResultSet rs = stm.getResultSet();

        rs.next();

        VendaItem vi = new VendaItem(
                rs.getInt("pk_vendaitem"),
                rs.getInt("fk_venda"),
                rs.getInt("fk_produto"),
                rs.getInt("qtd"),
                rs.getDouble("valorUnitario")
        );
        
        return vi;
    }    

    public static ArrayList<VendaItem> retrieveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "select * from venda_item where fk_venda = ?";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.execute();

        ResultSet rs = stm.getResultSet();

        ArrayList<VendaItem> aux = new ArrayList<>();

        while (rs.next()) {
            VendaItem vi = new VendaItem(
                    rs.getInt("pk_vendaitem"),
                    rs.getInt("fk_venda"),
                    rs.getInt("fk_produto"),
                    rs.getInt("qtd"),
                    rs.getDouble("valorUnitario"));

            aux.add(vi);
        }
        return aux;
    }
    
    public static void update(VendaItem vi) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        String sql = "UPDATE venda_item SET fk_venda=?, fk_produto=?, qtd=?, valorUnitario=? WHERE pk_vendaitem=?";
        
        PreparedStatement stm = conn.prepareCall(sql);
        
        stm.setInt(1, vi.getFkVenda());
        stm.setInt(2, vi.getFkProduto()); 
        stm.setInt(3, vi.getQtd());
        stm.setDouble(4, vi.getValorUnitario()); 
        
        stm.execute();
        
        }
    
     public static void delete(VendaItem vi) throws SQLException{
        if (vi.getPk()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from venda_item where pk_vendaitem =?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, vi.getPk());       
        stm.execute();
        stm.close();        
    }
    
    public static void delete(int id) throws SQLException{
        if (id==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from venda_item where pk_vendaitem =?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, id);       
        stm.execute();
        stm.close();        
    }

}
    
    



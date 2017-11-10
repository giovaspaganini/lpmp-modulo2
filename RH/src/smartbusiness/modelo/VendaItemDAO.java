/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import smartbusiness.negocio.VendaItem;

/**
 * Classe responsável pela persistencia de VendaItem
 *  
 */

public class VendaItemDAO {

	/**
   	 * Método responsável por percistir um VendaItem no banco
   	 *
   	 * @param vi VendaItem que será gravado no banco
   	 * @return chave primária 
   	 */
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

    /**
   	 * Método responsável por recuperar VendaItem pela chave primária
   	 *
   	 * @param pk_vendaitem chave primária que será pesquisada no banco
   	 * @return Objeto VendaItem
   	 */
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

    /**
   	 * Método responsável por recuperar todas as VendasItens salvas no banco
   	 * @return Todas as VendasItens salvas no banco
   	 */
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
    
    /**
   	 * Método responsável por atualizar um VendaItem no banco
   	 *
   	 * @param vi Objeto VendaItem que seŕa atualizado
   	 */
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
    
    /**
   	 * Método responsável por deletar um VendaItem do banco de dados
   	 *
   	 * @param Objecto VendaItem que será deletado do banco de dados
   	 */
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
    
     /**
    	 * Método responsável por deletar um VendaItem do banco passando uma chave primária
    	 *
    	 * @param id chave primária do VendaItem que será deletado
    	 */
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
    
    /**
   	 * Método responsável por recuperar VendaItem por produto
   	 *
   	 * @param vendaPorProduto Nome que produto que será pesquisado
   	 * @return Todas as VendaItem que contem determinado produto
   	 */
    public static ArrayList<VendaItem> retrieveByProduto(String vendaPorProduto) throws SQLException {
        
        ArrayList<VendaItem> aux = new ArrayList<>();
        
        Connection conn = BancoDados.createConnection();
        
        String sql = "SELECT pk_produto, nome," +
            " sum(qtd)," +
            " avg(qtd) media_qtd," +
            " sum(valor_unitario)," +
            " avg(valor_unitario) media_vu" +
            " FROM vendas v JOIN vendas_itens i ON" +
            "	v.pk_venda = i.fk_venda JOIN" +
            "	produtos p ON p.pk_produto = i.fk_produto" +
            " where pk_produto = ?" +
            " group by pk_produto, nome";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, vendaPorProduto);

        stm.execute();

        ResultSet rs = stm.getResultSet();
        
        while( rs.next()){
            aux.add(new VendaItem(0, 0, 0, 0, 0));
        }
        
    
        
        return aux;
    }
    

}
    
    



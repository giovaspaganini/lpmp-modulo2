package smartbusiness.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import smartbusiness.controle.Cliente;
import smartbusiness.controle.Venda;
import smartbusiness.controle.VendaItem;


/**
 * Classe responsável pela percistencia da classe Venda
 *  
 */

public class VendaDAO {
    
	/**
   	 * Método responsável por criar um novo resgistro de Venda
   	 *
   	 * @param v Objeto da Classe Venda
   	 * @return chave primária do registro criado
   	 */
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
        
        int PkVenda = rs.getInt("pk_venda");
        
        v.setPk(PkVenda);
        
         for (VendaItem aux : v.getItens()) {
             
            aux.setFkVenda(PkVenda);

            VendaItemDAO.create(aux);
        
           
    }
         stm.close();
       
        return PkVenda;
    }
    
    /**
   	 * Método responsável por deletar uma venda do banco de dados
   	 *
   	 * @param v Venda que será apagada do banco de dados
   	 */
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
    
    /**
   	 * Método responsável por recuperar uma Venda por sua chave primária
   	 *
   	 * @param pk chave primaria da Venda
   	 * @return Venda recuperada do banco
   	 */
    public static Venda retrieve(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("select * from vendas where pk_venda=?");
        stm.setInt(1, pk);
        
        stm.execute();
        
        ResultSet rs = stm.getResultSet();
        
        rs.next();
        
        
        
        Venda v = new Venda(rs.getInt("pk_venda"), rs.getInt("fk_cliente"), rs.getInt("fk_vendedor"), rs.getInt("numero"), rs.getDate("data"));
        
        return v;
        
    }
   
    /**
   	 * Método responsável por atualizar uma venda no banco de dados
   	 *
   	 * @param v Venda que será atualizada no banco
   	 */
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
    
    /**
   	 * Método responsável por deletar uma Venda por chave primária
   	 */
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

    /**
   	 * Método responsável por recuperar todas as vendas do banco de dados
   	 * @return ArrayList de Venda
   	 */
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
    
    /**
   	 * Método responsável por recuperar todas as vendas por cliente
   	 *
   	 * @param c Cliente que terá suas vendas recuperadas
   	 * @return ArrayList de vendas deu um cliente
   	 */
    public static ArrayList<Venda> retriveByCliente(Cliente c) throws SQLException{ 
        
         ArrayList<Venda> aux = new ArrayList<>();
        
        Connection conn = BancoDados.createConnection();
        
        String sql = "SELECT * FROM vendas where fk_cliente = ?";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, c.getPk());
        stm.execute();
              
        ResultSet rs = stm.getResultSet();
        
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
   
    /**
   	 * Método responsável por retornar todas as vendas entre determinadas datas
   	 *
   	 * @param dataInicial data inicial da pesquisa
   	 * @param dataFinal data final da pesquisa
   	 * @return Vendas realizadas entre o periodo informado
   	 */
    public static ArrayList<Venda> retrieveByData(Date dataInicial, Date dataFinal) throws SQLException{
        
        ArrayList<Venda> aux = new ArrayList<>();
        
        Connection conn = BancoDados.createConnection();
        
        String sql = "SELECT * FROM vendas WHERE DATA >= ? AND DATA <= ?";
        
        System.out.println("retrieveByData entre " + dataInicial + " e " + "dataFinal " + dataFinal);
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setDate(1, dataInicial);
        stm.setDate(2, dataFinal);
        stm.execute();
              
        ResultSet rs = stm.getResultSet();
        
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

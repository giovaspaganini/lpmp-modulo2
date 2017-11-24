package smartbusiness.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import smartbusiness.controle.EnderecoCliente;


/**
 * Classe resposável pela persistencia de EnderecoCliente
 *  
 */

public class ClienteEnderecoDAO {
	
	/**
   	 * Método responsável por criar um novo registro de EnderecoCliente
   	 *
   	 * @param e EnderecoCliente que será gravado no banco de dados
   	 * @return chave primária do item
   	 */
    public static int create(EnderecoCliente e) throws SQLException{

        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = 
                conn.prepareStatement("INSERT INTO clientes_enderecos(fk_cliente, logadouro, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, e.getFk_cliente());
        stm.setString(2, e.getLogradouro());
        stm.setString(3, e.getBairro());
        stm.setString(4, e.getCidade());
        stm.setString(5, e.getEstado());
        //executa o comando no BD
        stm.execute();
        //retorna um conjuto de resultados que contém a chave primária
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();//coloca o resultset em uma posição válida 
        e.setPk_endereco(rs.getInt(1));//recupera o valor da chave na primeira coluna (getInt(1º))
        //fecha a assertiva
        e.setSync(true);
        stm.close();
        
        //retorna a chave primária
        return e.getPk_endereco();
    }
    
    /**
   	 * Método responsável por recuperar um EnderecoCliente pela chave primária
   	 *
   	 * @param pk chave primária que será buscada no banco
   	 * @return retorna EnderecoCliente que bate com a chave passada
   	 */
    public static EnderecoCliente retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("select * from clientes_enderecos where pk_enderenco = ?");
        stm.setInt(1, pk);
        
        stm.execute();

        ResultSet rs = stm.getResultSet();
        
        rs.next();
        return new EnderecoCliente(rs.getInt("pk_enderenco"),
                         rs.getInt("fk_cliente"),
                         rs.getString("logradouro"),
                         rs.getString("bairro"),
                         rs.getString("cidade"),
                         rs.getString("estado"),
                rs.getString("pais"),
                rs.getString("cep")
        );
    }
    
    /**
     * Retorna todos os endereços de um funcionário específico
     * @param fk_funcionario é o inteiro que representa a chave primária do funcionário em questão
     * @return um arraylist com todos os endereços desse funcionário
     * @throws SQLException 
     */
    public static ArrayList<EnderecoCliente> retrieveAll(int fk_endereco) throws SQLException{
        
        ArrayList<EnderecoCliente> aux = new ArrayList<>();
        
        Connection conn = BancoDados.createConnection();
        
        String sql = "select * from clientes_enderecos where fk_cliente = ?";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, fk_endereco);
        
        stm.execute();

        ResultSet rs = stm.getResultSet();
                
        while (rs.next()){
            EnderecoCliente e = new EnderecoCliente(rs.getInt("pk_enderenco"),
                         rs.getInt("fk_cliente"),
                         rs.getString("logradouro"),
                         rs.getString("bairro"),
                         rs.getString("cidade"),
                         rs.getString("estado"),
                         rs.getString("pais"),
                         rs.getString("cep"));

            aux.add(e);
        }        
        return aux;

    }
     
    /**
   	 * Método responsável por atualizar um registro de EnderecoCliente no banco
   	 *
   	 * @param e EnderecoCliente que será atualizado
   	 */
    public static void update(EnderecoCliente e) throws SQLException{
        if (e.getPk_endereco()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "update clientes_enderecos set logradouro=?, bairro=?, cidade=?, estado=? where pk_endereco=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getBairro());
        stm.setString(3, e.getCidade());
        stm.setString(4, e.getEstado());
        stm.setInt(5, e.getPk_endereco());        
        
        stm.execute();
        e.setSync(true);
        stm.close();
    }
   
    /**
   	 * Método responsável por deletar um EnderecoCliente do banco
   	 *
   	 * @param e EnderecoCliente que será delatado do banco de dados
   	 */
    public static void delete(EnderecoCliente e) throws SQLException{
        if (e.getPk_endereco()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from clientes_enderecos where pk_enderenco=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, e.getPk_endereco());       
        stm.execute();
        stm.close();        
    }    
}

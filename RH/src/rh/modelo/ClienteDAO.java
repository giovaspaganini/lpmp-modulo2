/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.Cliente;
import rh.negocio.EnderecoCliente;
import rh.modelo.ClienteEnderecoDAO;

/**
 * Realiza as resposabilidades comportamentais necessárias para a persistencia de um Cliente no banco de dados
 *
 */
public class ClienteDAO {

	
	/**
	 * Método responsável por persistir um objeto da classe Cliente no banco de dados
	 *
	 * @param c Objeto da classe Cliente que será persistido no banco de dados
	 * @return Retorna a chave primária do cliente persistido no banco de dados
	 */
    public static int create(Cliente c) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "INSERT INTO clientes(nome, cpf) VALUES (?, ?)";

        PreparedStatement stm = conn.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);        
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();

        rs.next();

        int pkc = rs.getInt("pk_cliente");

        c.setPk(pkc);

        for (EnderecoCliente aux : c.getEndereco()) {
            aux.setFk_cliente(pkc);

            ClienteEnderecoDAO.create(aux);
        }

        return pkc;
    }

    /**
	 * Método responsável recuperar um usuário do banco de dados passando seu id
	 *
	 * @param pk_cliente id de cliente que será buscado no banco de dados
	 * @return retorna o Cliente pesquisado
	 */
    public static Cliente retrieve(int pk_cliente) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "select * from clientes where pk_cliente = ?";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, pk_cliente);

        stm.execute();

        ResultSet rs = stm.getResultSet();

        rs.next();

        Cliente c = new Cliente(
                pk_cliente,
                rs.getString("nome"),
                rs.getString("cpf"));
                
                c.setEndereco(ClienteEnderecoDAO.retrieveAll(pk_cliente));
        return c;
    }

    
    /**
	 * Método responsável recuperar todos os Clientes do banco de dados
	 *
	 * @return Retorna todos os clientes salvos no banco
	 */
    public static ArrayList<Cliente> retrieveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "select * from clientes";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.execute();

        ResultSet rs = stm.getResultSet();

        ArrayList<Cliente> aux = new ArrayList<>();

        while (rs.next()) {
            Cliente caux = new Cliente(
                    rs.getInt("pk_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"));
            caux.setEndereco(ClienteEnderecoDAO.retrieveAll(caux.getPk()));

            aux.add(caux);
        }
        return aux;
    }
    
    /**
	 * Método responsável por atualizar um registro de Cliente no banco
	 *
	 * @param c Objeto da classe Cliente que será atualizado
	 */
    public static void update(Cliente c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        String sql = "UPDATE clientes SET nome=?, cpf=? WHERE pk_cliente=?";
        
        PreparedStatement stm = conn.prepareCall(sql);
        
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());        
        
        stm.execute();
        
        for (EnderecoCliente aux: c.getEndereco()){
            if (!aux.isSync()){
               ClienteEnderecoDAO.update(aux);
            }
        }
    }
    
    /**
	 * Método responsável por deletar um cliente do banco de dados
	 *
	 * @param c Objeto da classe Cliente que será apagado do banco de dados
	 */
    public static void delete(Cliente c) throws SQLException{
        if (c.getPk()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from clientes where pk_cliente =?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, c.getPk());       
        stm.execute();
        stm.close();        
    }
    
    /**
	 * Método responsável por deletar um cliente do banco de dados passando seu ID
	 *
	 * @param id Id do cliente que será apagado do banco de dados
	 */
    public static void delete(int id) throws SQLException{
        if (id==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from clientes where pk_cliente =?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, id);       
        stm.execute();
        stm.close();        
    }
    
    /**
	 * Método responsável por returnar todos os clientes de uma determinada cidade
	 *
	 * @param nomeCidade Nome da cidade para fazer o filtro de cliente
	 * @return Retorna todos os clientes de uma determinada cidade
	 */
    public static ArrayList<Cliente> retrieveByCidade(String  nomeCidade) throws SQLException {
        
        ArrayList<Cliente> aux = new ArrayList<>();        
        
        Connection conn = BancoDados.createConnection();

        String sql = "select * from clientes c join clientes_enderecos ce on c.pk_cliente = ce.fk_cliente where cidade = ?";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, nomeCidade);

        stm.execute();

        ResultSet rs = stm.getResultSet();
        
        while( rs.next()){
            aux.add( new Cliente( rs.getInt("pk_cliente"),  rs.getString("nome"), rs.getString("cpf")));
        }
         return aux;
    }
    
    /**
	 * Método responsável por retornar todos os clientes de um determinado Estado
	 *
	 * @param nomeEstado Nome do estado que será pesquisado
	 * @return Todos os clientes de um determinado estado
	 */
    public static ArrayList<Cliente> retrieveByEstado(String  nomeEstado) throws SQLException {
        
        ArrayList<Cliente> aux = new ArrayList<>();        
        
        Connection conn = BancoDados.createConnection();

        String sql = "select * from clientes c join clientes_enderecos ce on c.pk_cliente = ce.fk_cliente where estado = ?";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, nomeEstado);

        stm.execute();

        ResultSet rs = stm.getResultSet();
        
        while( rs.next()){
            aux.add( new Cliente( rs.getInt("pk_cliente"),  rs.getString("nome"), rs.getString("cpf")));
        }
         return aux;
    }
}

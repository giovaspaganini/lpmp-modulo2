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
import rh.negocio.Cargo;
import rh.negocio.Endereco;
import rh.negocio.EnderecoFuncionario;

/**
 *
 * @author L
 */
public class FuncionarioEnderecoDAO {
    public static int create(EnderecoFuncionario e) throws SQLException{

        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = 
                conn.prepareStatement("INSERT INTO funcionarios_enderecos(fk_funcionario, logadouro, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, e.getFk_funcionario());
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
     * 
     * @param pk
     * @return
     * @throws SQLException 
     */
    public static EnderecoFuncionario retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("select * from funcionarios_enderecos where pk_endereco = ?");
        stm.setInt(1, pk);
        
        stm.execute();

        ResultSet rs = stm.getResultSet();
        
        rs.next();
        return new EnderecoFuncionario(rs.getInt("pk_endereco"),
                         rs.getInt("fk_funcionario"),
                         rs.getString("logradouro"),
                         rs.getString("bairro"),
                         rs.getString("cidade"),
                         rs.getString("estado")
        );
    }
    
    /**
     * Retorna todos os endereços de um funcionário específico
     * @param fk_funcionario é o inteiro que representa a chave primária do funcionário em questão
     * @return um arraylist com todos os endereços desse funcionário
     * @throws SQLException 
     */
    public static ArrayList<EnderecoFuncionario> retrieveAll(int fk_funcionario) throws SQLException{
        
        ArrayList<EnderecoFuncionario> aux = new ArrayList<>();
        
        Connection conn = BancoDados.createConnection();
        
        String sql = "select * from funcionarios_enderecos where fk_funcionario = ?";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, fk_funcionario);
        
        stm.execute();

        ResultSet rs = stm.getResultSet();
                
        while (rs.next()){
            EnderecoFuncionario e = new EnderecoFuncionario(rs.getInt("pk_endereco"),
                         rs.getInt("fk_funcionario"),
                         rs.getString("logradouro"),
                         rs.getString("bairro"),
                         rs.getString("cidade"),
                         rs.getString("estado"));

            aux.add(e);
        }
        
        return aux;

    }
       
    public static void update(EnderecoFuncionario e) throws SQLException{
        if (e.getPk_endereco()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "update funcionarios_enderecos set logradouro=?, bairro=?, cidade=?, estado=? where pk_endereco=?";
        
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
    
    public static void delete(EnderecoFuncionario e) throws SQLException{
        if (e.getPk_endereco()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from funcionarios_enderecos where pk_endereco=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, e.getPk_endereco());       
        stm.execute();
        stm.close();        
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.modelo;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.EnderecoFuncionario;
import rh.negocio.Funcionario;

/**
 * Realiza as resposabilidades comportamentais necessárias para a persistencia
 * de um funcionário no banco de dados
 *
 * @author L
 */
public class ClienteDAO {

    public static int create(Cliente c) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "INSERT INTO clientes() VALUES ()";

        PreparedStatement stm = conn.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, f.getCargo().getPk());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();

        rs.next();

        int pkc = rs.getInt("pk_cliente");

        c.setPk_cliente(pkc);

        for (EnderecoCliente aux : c.getEndereco()) {
            aux.setFk_cliente(pkc);

            ClienteEnderecoDAO.create(aux);
        }

        return pkc;
    }

    public static Funcionario retrieve(int pk_funcionario) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "select * from funcionarios where pk_funcionario = ?";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, pk_funcionario);

        stm.execute();

        ResultSet rs = stm.getResultSet();

        rs.next();

        Funcionario f = new Funcionario(
                pk_funcionario,
                rs.getString("nome"),
                rs.getString("cpf"),
                CargoDAO.retrieve(rs.getInt("fk_cargo")));

        f.setEndereco(FuncionarioEnderecoDAO.retrieveAll(pk_funcionario));

        return f;
    }

    public static ArrayList<Funcionario> retrieveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "select * from funcionarios";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.execute();

        ResultSet rs = stm.getResultSet();

        ArrayList<Funcionario> aux = new ArrayList<>();

        while (rs.next()) {
            Funcionario faux = new Funcionario(
                    rs.getInt("pk_funcionario"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    CargoDAO.retrieve(rs.getInt("fk_cargo")));

            faux.setEndereco(FuncionarioEnderecoDAO.retrieveAll(faux.getPk_funcionario()));

            aux.add(faux);
        }
        return aux;
    }
    
    public static void update(Funcionario f) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        String sql = "UPDATE funcionarios SET fk_cargo=?, nome=?, cpf=? WHERE pk_funcionario=?";
        
        PreparedStatement stm = conn.prepareCall(sql);
        
        stm.setInt(1, f.getCargo().getPk());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());
        stm.setInt(4, f.getPk_funcionario());
        
        stm.execute();
        
        for (EnderecoFuncionario aux: f.getEndereco()){
            if (!aux.isSync()){
               FuncionarioEnderecoDAO.update(aux);
            }
        }
    }
    
    public static void delete(Funcionario f) throws SQLException{
        if (f.getPk_funcionario()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from funcionarios where pk_funcionario =?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, f.getPk_funcionario());       
        stm.execute();
        stm.close();        
    }

}

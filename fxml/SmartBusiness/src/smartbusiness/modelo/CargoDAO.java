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
import smartbusiness.controle.Cargo;

/**
 * Realiza as resposabilidades comportamentais necessárias para a persistencia
 * de um cargo no banco de dados
 *
 * @author Fernando
 */
public class CargoDAO {

    /**
     * Método responsável por receber um objeto Cargo e restistrá-lo no banco de
     * dados
     *
     * @param c recebe um objeto do tipo Cargo
     * @return retorna a chave primaria para o Cargo registrado no Banco de
     * Dados
     * @throws SQLException
     */
    public static int create(Cargo c) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("insert into cargos(nome,descricao) values(?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, c.getNome());
        stm.setString(2, c.getDescricao());
        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        c.setPk_cargo(rs.getInt("pk_cargo"));

        return c.getPk_cargo();
    }

    /**
     * Método responsável por receber uma chave primária e retornar um objeto
     * Cargo
     *
     * @param pk recebe a chave primaria do Cargo que será resgatado
     * @return retorna o objeto do tipo Cargo referente a chave primária
     * recebida
     * @throws SQLException
     */
    public static Cargo retrieve(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("select * from cargos where pk_cargo=?");
        stm.setInt(1, pk);
        stm.execute();

        ResultSet rs = stm.getResultSet();
        rs.next();
        return new Cargo(rs.getInt("pk_cargo"), rs.getString("nome"), rs.getString("descricao"));
    }

    /**
     * Metodo responsável por retornar uma lista de todos os Cargos no Banco de
     * Dados
     *
     * @return retorna um ArrayList de todos os Cargos do Banco de Dados
     * @throws SQLException
     */
    public static ArrayList<Cargo> retrieveAll() throws SQLException {
        ArrayList<Cargo> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery("select * from cargos");

        while (rs.next()) {
            aux.add(new Cargo(rs.getInt("pk_cargo"),
                    rs.getString("nome"),
                    rs.getString("descricao")));
        }

        return aux;
    }

    /**
     * Método responsável por receber um objeto Cargo e atualizá-lo no Bando de
     * Dados
     *
     * @param c recebe o objeto do tipo Cargo a ser atualizado
     * @throws SQLException
     */
    public static void update(Cargo c) throws SQLException {
        if (c.getPk_cargo() == 0) {
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada!");
        }
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("update cargos set nome=?, descricao=? where pk_cargo=?");
        stm.setString(1, c.getNome());
        stm.setString(2, c.getDescricao());
        stm.setInt(3, c.getPk_cargo());
        stm.execute();
        stm.close();
    }

    /**
     * Método responsável por receber um objeto Cargo e excluí-lo do Banco de
     * Dados
     *
     * @param c recebe o objeto do tipo Cargo a ser excluído
     * @throws SQLException
     */
    public static void delete(Cargo c) throws SQLException {
        if (c.getPk_cargo() == 0) {
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada!");
        }
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("delete from cargos where pk_cargo=?");
        stm.setInt(1, c.getPk_cargo());
        stm.execute();
        stm.close();
    }
}

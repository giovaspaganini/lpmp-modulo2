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
import smartbusiness.modelo.CargoDAO;
import smartbusiness.controle.EnderecoFuncionario;
import smartbusiness.controle.Funcionario;


/**
 * Realiza as resposabilidades comportamentais necessárias para a persistencia
 * de um funcionário no banco de dados
 *
 * @author L
 */
public class FuncionarioDAO {

    /**
     * Método responsável por receber um objeto Funcionario e registrá-lo no
     * Banco de Dados
     *
     * @param f recebe um objeto do tipo Funcionario
     * @return retorna a chave primaria para o Funcionario registrado no Banco
     * de Dados
     * @throws SQLException
     */
    public static int create(Funcionario f) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "INSERT INTO funcionarios(fk_cargo, nome, cpf) VALUES (?, ?, ?)";

        PreparedStatement stm = conn.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, f.getCargo().getPk_cargo());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();

        rs.next();

        int pkf = rs.getInt("pk_funcionario");

        f.setPk_funcionario(pkf);

        for (EnderecoFuncionario aux : f.getEndereco()) {
            aux.setFk_funcionario(pkf);

            FuncionarioEnderecoDAO.create(aux);
        }
        return pkf;
    }

    /**
     * Método responsável por receber uma chave primária e retornar um objeto
     * Funcionario
     *
     * @param pk_funcionario recebe a chave primario do Funcionario a ser
     * resgatado
     * @return retorna o funcionario referente a chave primaria recebida
     * @throws SQLException
     */
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

    /**
     * Método responsável por retornar uma lista com todos os Funcionarios do
     * Banco de Dados
     *
     * @return retorna um ArrayList de todos os Funcionarios do Banco de Dados
     * @throws SQLException
     */
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

    /**
     * Método responsável por receber o nome de uma cidade e retornar uma lista
     * de funcionários com endereço nessa cidade
     *
     * @param nomeCidade recebe o nome da cidade a ser utilizada na consulta
     * @return retorna um ArrayList de Funcionarios pela Cidade
     * @throws SQLException
     */
    public static ArrayList<Funcionario> retrieveByCidade(String nomeCidade) throws SQLException {
        ArrayList<Funcionario> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("select * from funcionarios_enderecos where cidade = ?");
        stm.setString(1, nomeCidade);
        stm.execute();

        ResultSet rs = stm.getResultSet();

        while (rs.next()) {
            boolean teste = false;

            for (Funcionario a : aux) {
                if (a.getPk_funcionario() == rs.getInt("fk_funcionario")) {
                    teste = true;
                }
            }

            if (!teste) {
                aux.add(FuncionarioDAO.retrieve(rs.getInt("fk_funcionario")));
            }
        }

        return aux;
    }

    /**
     * Método responsável por receber o nome de um estado e retornar uma lista
     * de funcionários com endereço nesse estado
     *
     * @param nomeEstado recebe o nome do estado a ser utilizada na consulta
     * @return retorna um ArrayList de Funcionarios pelo estado
     * @throws SQLException
     */
    public static ArrayList<Funcionario> retrieveByEstado(String nomeEstado) throws SQLException {
        ArrayList<Funcionario> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("select * from funcionarios_enderecos where estado = ?");
        stm.setString(1, nomeEstado);
        stm.execute();

        ResultSet rs = stm.getResultSet();

        while (rs.next()) {
            boolean teste = false;

            for (Funcionario a : aux) {
                if (a.getPk_funcionario() == rs.getInt("fk_funcionario")) {
                    teste = true;
                }
            }

            if (!teste) {
                aux.add(FuncionarioDAO.retrieve(rs.getInt("fk_funcionario")));
            }
        }

        return aux;
    }

    /**
     * Método responsável por receber um Funcionario e atualizá-lo no Bando de
     * dados
     *
     * @param f recebe o Funcionario que será atualizado
     * @throws SQLException
     */
    public static void update(Funcionario f) throws SQLException {
        Connection conn = BancoDados.createConnection();

        String sql = "UPDATE funcionarios SET fk_cargo=?, nome=?, cpf=? WHERE pk_funcionario=?";

        PreparedStatement stm = conn.prepareCall(sql);
        stm.setInt(1, f.getCargo().getPk_cargo());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());
        stm.setInt(4, f.getPk_funcionario());

        stm.execute();

        for (EnderecoFuncionario aux : f.getEndereco()) {
            if (!aux.isSync()) {
                FuncionarioEnderecoDAO.update(aux);
            }
        }
    }

    /**
     * Método responsável por receber um Funcionario e excluí-lo do Bando de
     * dados
     *
     * @param f recebe o Funcionario que será deletado
     * @throws SQLException
     */
    public static void delete(Funcionario f) throws SQLException {
        if (f.getPk_funcionario() == 0) {
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

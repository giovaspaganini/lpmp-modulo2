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
import java.util.Date;
import smartbusiness.controle.FinanceiroEntrada;

/**
 *
 * @author Julian Rodrigues
 */
public class FinanceiroEntradaDAO {

    public static int create(FinanceiroEntrada f) throws SQLException {

        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("INSERT INTO financeiros_entradas(  fk_venda, data_emissao, data_vencimento, data_baixa,valor, tipo_documento)\n"
                + "    VALUES ( ?, ?, ?, ?,?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, f.getFk_venda());
        stm.setDate(2, new java.sql.Date(f.getData_emissao().getTime()));
        stm.setDate(3, new java.sql.Date(f.getData_vencimento().getTime()));
        stm.setDate(4, new java.sql.Date(f.getData_baixa().getTime()));
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        f.setPk_entrada(rs.getInt(1));

        return f.getPk_entrada();

    }

    public static FinanceiroEntrada retrieve(int pk_entrada) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("select * from financeiros_entradas where pk_entrada = ?");

        stm.setInt(1, pk_entrada);

        stm.execute();

        ResultSet rs = stm.getResultSet();

        rs.next();
        return new FinanceiroEntrada(rs.getInt("pk_entrada"),
                rs.getInt("fk_venda"),
                rs.getDate("data_emissao"),
                rs.getDate("data_vencimento"),
                rs.getDate("data_baixa"),
                rs.getDouble("valor"),
                rs.getString("tipo_documento")
        );

    }

    public static ArrayList<FinanceiroEntrada> retrieveByData(Date datainicial, Date dataFinal) throws SQLException {

        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        String sql = "SELECT * FROM financeiros_entradas WHERE data_emissao BETWEEN ? AND ? ";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setDate(1, (java.sql.Date) datainicial);
        stm.setDate(2, (java.sql.Date) dataFinal);

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada(rs.getInt("pk_entrada"),
                    rs.getInt("fk_venda"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento")
            );
            aux.add(f);
        }
        return aux;
    }

    public static ArrayList<FinanceiroEntrada> retrieveReceber(Date datainicial, Date dataFinal) throws SQLException {

        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        String sql = " SELECT * FROM financeiros_entradas WHERE  data_baixa  is NULL and\n"
                + " data_emissao BETWEEN  ? AND ?";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setDate(1, (java.sql.Date) datainicial);
        stm.setDate(2, (java.sql.Date) dataFinal);

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada(rs.getInt("pk_entrada"),
                    rs.getInt("fk_venda"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento")
            );
            aux.add(f);
        }
        return aux;

    }

    public static ArrayList<FinanceiroEntrada> retrieveRecebidos(Date datainicial, Date dataFinal) throws SQLException {

        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        String sql = "SELECT * FROM financeiros_entradas WHERE  data_baixa  is not NULL and\n"
                + " data_emissao BETWEEN  ? AND ?";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setDate(1, (java.sql.Date) datainicial);
        stm.setDate(2, (java.sql.Date) dataFinal);

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada(rs.getInt("pk_entrada"),
                    rs.getInt("fk_venda"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento")
            );
            aux.add(f);
        }
        return aux;

    }

    public static ArrayList<FinanceiroEntrada> retrieveVencidos(Date datainicial, Date dataFinal) throws SQLException {

        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        String sql = "SELECT * FROM financeiros_entradas WHERE  data_baixa  is  NULL and\n"
                + " data_vencimento < current_date and data_emissao between ? and ? ";

        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setDate(1, (java.sql.Date) datainicial);
        stm.setDate(2, (java.sql.Date) dataFinal);

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada(rs.getInt("pk_entrada"),
                    rs.getInt("fk_venda"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento")
            );
            aux.add(f);
        }
        return aux;

    }
///////////////////////////////////
    ////////////////////
    /////////////////////

    public static ArrayList<FinanceiroEntrada> retriaveByClientes(Date dataInicial, Date dataFinal) throws SQLException {
        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();
//        PreparedStatement stm = conn.prepareCall("select * from clientes");
//        stm.execute();
//
//        PreparedStatement stmFe = conn.prepareStatement("select v.fk_cliente,fe.data_emissao, fe.data_vencimento, fe.data_baixa, fe.valor from\n"
//                + "vendas v inner join financeiros_entradas fe\n"
//                + "on v.pk_venda=fe.fk_venda and\n"
//                + "fe.data_emissao between ? and ? order by fe.fk_venda");
//
//        stmFe.execute();
//
//        ResultSet rs = stm.getResultSet();
//        ResultSet rsF = stmFe.getResultSet();
//
//        while (rs.next()) {
//            
//        }

        String totalVencidos = "select fk_venda,nome,sum(valor)as totalVencidos from clientes inner join financeiros_entradas on pk_cliente = fk_venda  where (data_emissao >= ? and data_emissao<=?) and data_vencimento<=current_date and data_baixa is  null group by fk_venda,nome";

        String totalReceber = "select fk_venda,nome,sum(valor) as totalReceber from clientes inner join financeiros_entradas on pk_cliente = fk_venda \n"
                + "where data_emissao>= ? and data_emissao <=? and data_baixa  is  null group by fk_venda,nome";
        String totalRecebidos = "select fk_venda,nome,sum(valor)as totalRecebidos from clientes inner join financeiros_entradas on pk_cliente = fk_venda  \n"
                + "                                    where data_emissao>=? and data_emissao <? and data_baixa  is not null group by fk_venda,nome";

        PreparedStatement stmVencidos = conn.prepareStatement(totalVencidos);
        stmVencidos.setDate(1, (java.sql.Date) dataInicial);
        stmVencidos.setDate(2, (java.sql.Date) dataFinal);

        stmVencidos.execute();

        ResultSet rsVencidos = stmVencidos.getResultSet();

        PreparedStatement stmReceber = conn.prepareStatement(totalReceber);
        stmReceber.setDate(1, (java.sql.Date) dataInicial);
        stmReceber.setDate(2, (java.sql.Date) dataFinal);

        stmReceber.execute();
        ResultSet rsReceber = stmReceber.getResultSet();

        PreparedStatement stmRecebidos = conn.prepareStatement(totalRecebidos);
        stmRecebidos.setDate(1, (java.sql.Date) dataInicial);
        stmRecebidos.setDate(2, (java.sql.Date) dataFinal);

        stmRecebidos.execute();

        ResultSet rsRecebidos = stmRecebidos.getResultSet();

        while (rsRecebidos.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada();
            f.setMsg("Recebidos: " + rsRecebidos.getString("nome"));
            f.setValor(rsRecebidos.getDouble("totalRecebidos"));
            aux.add(f);
        }

        while (rsVencidos.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada();
            f.setMsg("Vencidos:" + rsVencidos.getString("nome"));
            f.setValor(rsVencidos.getDouble("totalVencidos"));
            aux.add(f);
        }
        while (rsReceber.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada();
            f.setMsg("A receber:" + rsReceber.getString("nome"));
            f.setValor(rsReceber.getDouble("totalReceber"));
            aux.add(f);
        }
        return aux;

    }

    public static ArrayList<FinanceiroEntrada> retrieveAll() throws SQLException {

        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();

        String sql = "select * form FinanceirosEntradas";

        ResultSet rs = conn.createStatement().executeQuery(sql);

        while (rs.next()) {
            FinanceiroEntrada f = new FinanceiroEntrada(rs.getInt("pk_entrada"),
                    rs.getInt("fk_venda"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento")
            );
            aux.add(f);
        }
        return aux;
    }

    public static void update(FinanceiroEntrada f) throws SQLException {
        if (f.getPk_entrada() == 0) {
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "UPDATE public.financeiros_entradas SET fk_venda=?, data_emissao=?, data_vencimento=?, data_baixa=?, valor=?, tipo_documento=? WHERE pk_entrada=?";
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(1, f.getFk_venda());

        stm.setDate(2, new java.sql.Date(f.getData_emissao().getTime()));
        stm.setDate(3, new java.sql.Date(f.getData_vencimento().getTime()));
        stm.setDate(4, new java.sql.Date(f.getData_baixa().getTime()));
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());
        stm.setInt(7, f.getPk_entrada());

        stm.execute();
        stm.close();
    }

    public static void delete(FinanceiroEntrada f) throws SQLException {
        if (f.getPk_entrada() == 0) {
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = ("DELETE FROM public.financeiros_entradas WHERE pk_entrada=?");

        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(1, f.getPk_entrada());
        stm.execute();
        stm.close();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.modelo;

import smartbusiness.controle.FinanceiroSaida;
import smartbusiness.controle.Fornecedor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import smartbusiness.modelo.BancoDados;

/**
 * Realiza as responsabilidas comportamentais necessárias para a persistencia de
 * controle dos dados de Fornecedores no banco de dados
 *
 * @author Rodrigo julio
 */
public class FinanceiroSaidaDAO {

    /**
     * Metodo responsavel para inserção de dados em financeiros_saidas no BD
     *
     * @param f objeto da classe FinanceiroSaida
     * @return Chave primária do FinanceiroSaida no BD
     * @throws SQLException lança uma exceção
     */
    public static int create(FinanceiroSaida f) throws SQLException {

        Connection conn = BancoDados.createConnection();
        String sql = "insert into financeiros_saidas(fk_compra, data_emissao, data_vencimento, data_baixa, valor, tipo_documento)values(?,?,?,?,?,?)";
        PreparedStatement stm
                = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, f.getFk_compra());
        stm.setDate(2, f.getData_emisao());
        stm.setDate(3, f.getData_vencimento());
        stm.setDate(4, f.getData_baixa());
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        int fpk = rs.getInt("pk_financeiro");
        f.setPk_financeiro(fpk);

        stm.close();
        return fpk;
    }

    /**
     * Metodo responsavel por atualizar os dados da tabela 'financeiros_saidas'
     * no BD
     *
     * @param f Objeto da classe FinanceiroSaidas
     * @throws SQLException lança uma exceção
     */
    public static void update(FinanceiroSaida f) throws SQLException {

        Connection conn = BancoDados.createConnection();

        String sql = "UPDATE financeiros_saidas SET fk_compra=?, data_emissao=?, data_vencimento=?, data_baixa=?, valor=?, tipo_documento=? WHERE pk_financeiro=?";

        PreparedStatement stm = conn.prepareCall(sql);
        stm.setInt(1, f.getFk_compra());
        stm.setDate(2, f.getData_emisao());
        stm.setDate(3, f.getData_vencimento());
        stm.setDate(4, f.getData_baixa());
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());
        stm.setInt(7, f.getPk_financeiro());
        stm.execute();
        stm.close();

    }

    /**
     * Metodo responsavel por excuir um fornecedor do BD
     *
     * @param f retorna Chave Primária da classe FinanceiroSaida
     * @throws SQLException lança uma exceção
     */
    public static void delete(FinanceiroSaida f) throws SQLException {

        if (f.getPk_financeiro() == 0) {

            throw new SQLException("Nao possue uma chave primaria valida");
        }

        Connection conn = BancoDados.createConnection();
        String sql = "delete from financeiros_saidas where pk_financeiro=?";
        PreparedStatement stm
                = conn.prepareStatement(sql);
        stm.setInt(1, f.getPk_financeiro());
        stm.execute();
        stm.close();

    }

    /**
     * Metodo responsavel por fazer a busca de um item na tabela
     * 'financeiros_saidas' no BD
     *
     * @param pk_funcionario Chave primaria fornecia pelo usuario, na qual fara
     * a busca no BD
     * @return f Objeto Fornecedor
     * @throws SQLException lança uma exceção
     */
    public static FinanceiroSaida retrieve(int pk_funcionario) throws SQLException {

        Connection conn = BancoDados.createConnection();
        String sql = "select  * from financeiros_saidas where pk_financeiro=?";
        PreparedStatement stm
                = conn.prepareStatement(sql);
        stm.setInt(1, pk_funcionario);

        stm.execute();
        ResultSet rs = stm.getResultSet();
        rs.next();

        FinanceiroSaida f = new FinanceiroSaida(
                pk_funcionario,
                rs.getInt("fk_compra"),
                rs.getDate("data_emissao"),
                rs.getDate("data_vencimento"),
                rs.getDate("data_baixa"),
                rs.getDouble("valor"),
                rs.getString("tipo_documento"));

        return f;

    }

    /**
     * Metodo reponsavel por retornar todos os dados 'financiros_saidas'
     *
     * @return aux ArrayList do FinanceiroSaida
     * @throws SQLException lança uma exceção
     */
    public static ArrayList<FinanceiroSaida> retrieveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();
        String sql = "select * from financeiros_saidas";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.execute();

        ResultSet rs = stm.getResultSet();
        ArrayList<FinanceiroSaida> aux = new ArrayList<>();

        while (rs.next()) {

            FinanceiroSaida f = new FinanceiroSaida(
                    rs.getInt("pk_financeiro"),
                    rs.getInt("fk_compra"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"));

            aux.add(f);
        }

        return aux;
    }

    /**
     * Metodo responsavel por realizar um busca de financeiros_saidas no BD
     * realizando filtros de datas
     *
     * @param danaInical e e Um Objeto Date fornecido pelo usario, para filtar a
     * bunca do ponto de inicio
     * @param dataFinal e um Objeto Date fornecido pelo usuario,para filtar a
     * busca ate um ponto final
     * @return aux Objeto da busca
     * @throws SQLException lança uma exceção
     */
    public static ArrayList<FinanceiroSaida> retriaveByData(Date dataInicial, Date dataFinal) throws SQLException {
        ArrayList<FinanceiroSaida> aux = new ArrayList<>();
        Connection conn = BancoDados.createConnection();
        String sql
                = "select * from financeiros_saidas where data_emissao>=? and data_emissao<=?";
        PreparedStatement stm
                = conn.prepareStatement(sql);
        stm.setDate(1, dataInicial);
        stm.setDate(2, dataFinal);
        stm.execute();
        ResultSet rs = stm.getResultSet();
        while (rs.next()) {

            FinanceiroSaida f = new FinanceiroSaida(
                    rs.getInt("pk_financeiro"),
                    rs.getInt("fk_compra"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"));

            aux.add(f);
        }
        return aux;
    }

    /**
     * Metodo responsavel por realizar um busca de financeiros_saidas no BD de
     * todos os valores ja pagos, realizando filtros de datas
     *
     * @param danaInical e e Um Objeto Date fornecido pelo usario, para filtar a
     * bunca do ponto de inicio
     * @param dataFinal e um Objeto Date fornecido pelo usuario,para filtar a
     * busca ate um ponto final
     * @return aux Objeto da busca
     * @throws SQLException lança uma exceção
     */
    public static ArrayList<FinanceiroSaida> retriavePagos(Date dataInicial, Date dataFinal) throws SQLException {
        ArrayList<FinanceiroSaida> aux = new ArrayList<>();
        Connection conn = BancoDados.createConnection();
        String sql
                = "select * from financeiros_saidas where data_emissao>=? and data_emissao <=? and data_baixa  is not null";
        PreparedStatement stm
                = conn.prepareStatement(sql);
        stm.setDate(1, dataInicial);
        stm.setDate(2, dataFinal);
        stm.execute();
        ResultSet rs = stm.getResultSet();
        while (rs.next()) {

            FinanceiroSaida f = new FinanceiroSaida(
                    rs.getInt("pk_financeiro"),
                    rs.getInt("fk_compra"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"));

            aux.add(f);
        }
        return aux;
    }

    /**
     * Metodo responsavel por realizar um busca de financeiros_saidas no BD, de
     * todos os a pagar realizando filtros de datas
     *
     * @param danaInical e e Um Objeto Date fornecido pelo usario, para filtar a
     * bunca do ponto de inicio
     * @param dataFinal e um Objeto Date fornecido pelo usuario,para filtar a
     * busca ate um ponto final
     * @return aux Objeto da busca
     * @throws SQLException lança uma exceção
     */
    public static ArrayList<FinanceiroSaida> retriavePagar(Date dataInicial, Date dataFinal) throws SQLException {
        ArrayList<FinanceiroSaida> aux = new ArrayList<>();
        Connection conn = BancoDados.createConnection();
        String sql
                = "select * from financeiros_saidas where data_emissao>=? and data_emissao <=? and data_baixa  is  null";
        PreparedStatement stm
                = conn.prepareStatement(sql);
        stm.setDate(1, dataInicial);
        stm.setDate(2, dataFinal);
        stm.execute();
        ResultSet rs = stm.getResultSet();
        while (rs.next()) {

            FinanceiroSaida f = new FinanceiroSaida(
                    rs.getInt("pk_financeiro"),
                    rs.getInt("fk_compra"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"));

            aux.add(f);
        }
        return aux;
    }

    /**
     * Metodo responsavel por realizar um busca e retornar todos os vencidos de
     * financeiros_saidas no BD realizando filtros de datas
     *
     * @param danaInical e e Um Objeto Date fornecido pelo usario, para filtar a
     * bunca do ponto de inicio
     * @param dataFinal e um Objeto Date fornecido pelo usuario,para filtar a
     * busca ate um ponto final
     * @return aux Objeto da busca
     * @throws SQLException lança uma exceção
     */
    public static ArrayList<FinanceiroSaida> retriaveVencidos(Date dataInicial, Date dataFinal) throws SQLException {
        ArrayList<FinanceiroSaida> aux = new ArrayList<>();
        Connection conn = BancoDados.createConnection();
        String sql
                = "select * from financeiros_saidas  where (data_emissao >= ? and data_vencimento<=?) and data_vencimento<=current_date and data_baixa is  null;";
        PreparedStatement stm
                = conn.prepareStatement(sql);
        stm.setDate(1, dataInicial);
        stm.setDate(2, dataFinal);
        stm.execute();
        ResultSet rs = stm.getResultSet();
        while (rs.next()) {

            FinanceiroSaida f = new FinanceiroSaida(
                    rs.getInt("pk_financeiro"),
                    rs.getInt("fk_compra"),
                    rs.getDate("data_emissao"),
                    rs.getDate("data_vencimento"),
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"));

            aux.add(f);
        }
        return aux;
    }

    ///retorna o total que dos valores pagos,vencidos,e a pagar de cada fornecedor
    /*
    public static ArrayList<FinanceiroSaida>retriaveByFornecedor
        (Date dataInicial,Date dataFinal) throws SQLException
        {
            ArrayList<FinanceiroSaida>aux= new ArrayList<>();
            
            Connection conn = BancoDados.createConnection();
            String totalVencidos  = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where (data_emissao >= ? and data_emissao<=?) and data_vencimento<=current_date and data_baixa is  null group by fk_compra,nome"; 
            String totalPagar = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where data_emissao>=? and data_emissao <=? and data_baixa  is  null group by fk_compra,nome";
            String totalPagos = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where data_emissao>=? and data_emissao <=? and data_baixa  is not null group by fk_compra,nome";
            
            PreparedStatement stmVencidos = conn.prepareStatement(totalVencidos);
            stmVencidos.setDate(1, dataInicial);
            stmVencidos.setDate(2, dataFinal);
            stmVencidos.execute();
            ResultSet rs = stmVencidos.getResultSet();
           
            PreparedStatement stmPagar = conn.prepareStatement(totalPagar);
            stmPagar.setDate(1, dataInicial);
            stmPagar.setDate(2, dataFinal);
            stmPagar.execute();
            ResultSet rs1 = stmPagar.getResultSet();
            
            
            PreparedStatement stmPagos = conn.prepareStatement(totalPagos);
            stmPagos.setDate(1, dataInicial);
            stmPagos.setDate(2, dataFinal);
            stmPagos.execute();
            ResultSet rsPagos = stmPagos.getResultSet();
            
            
            while(rsPagos.next()){
               FinanceiroSaida f = new FinanceiroSaida();
               f.setFornecedor("Recebido: " +rsPagos.getString("nome"));
               f.setValor(rsPagos.getDouble("total"));
               aux.add(f);
            }
            
            
            while(rs.next()){
               FinanceiroSaida f = new FinanceiroSaida();
               f.setFornecedor("Vencidos:" +rs.getString("nome"));
               f.setValor(rs.getDouble("total"));
               aux.add(f);
            }
             while(rs1.next()){
               FinanceiroSaida fs = new FinanceiroSaida();
               fs.setFornecedor("A receber:"+rs1.getString("nome"));
               fs.setValor(rs1.getDouble("total"));
               aux.add(fs);
            }
             
             
  
        
            return aux;
            
        }
     */
    /**
     * Metodo responsavel por realizar um busca de financeiros_saidas no BD
     * realizando filtros e retonar o tatal vencido, pago e a pagar realizando a
     * bunca por filtro de datas
     *
     * @param danaInical e e Um Objeto Date fornecido pelo usario, para filtar a
     * bunca do ponto de inicio
     * @param dataFinal e um Objeto Date fornecido pelo usuario,para filtar a
     * busca ate um ponto final
     * @return aux Objeto da busca
     * @throws SQLException lança uma exceção
     */
    public static ArrayList<FinanceiroSaida> retriaveTotais(Date dataInicial, Date dataFinal) throws SQLException {

        ArrayList<FinanceiroSaida> aux = new ArrayList<>();
        Connection conn = BancoDados.createConnection();
        String sqlPago = "select sum(valor)as total from  financeiros_saidas  where data_emissao>=? and data_emissao <=? and data_baixa  is not null";
        String sqlPagar = "select sum(valor)as total from  financeiros_saidas   where data_emissao>=? and data_emissao <=? and data_baixa  is  null;";
        String sqlVencidos = "select sum(valor)as total from  financeiros_saidas   where (data_emissao >= ? and data_emissao<=?) and data_vencimento<=current_date and data_baixa is  null";

        PreparedStatement stmPago = conn.prepareStatement(sqlPago);
        stmPago.setDate(1, dataInicial);
        stmPago.setDate(2, dataFinal);
        stmPago.execute();
        ResultSet rsTotalPago = stmPago.getResultSet();

        PreparedStatement stmPagar = conn.prepareStatement(sqlPagar);
        stmPagar.setDate(1, dataInicial);
        stmPagar.setDate(2, dataFinal);
        stmPagar.execute();
        ResultSet rsTotalPagar = stmPagar.getResultSet();

        PreparedStatement stmVencidos = conn.prepareStatement(sqlVencidos);
        stmVencidos.setDate(1, dataInicial);
        stmVencidos.setDate(2, dataFinal);
        stmVencidos.execute();
        ResultSet rsTotalVencidos = stmVencidos.getResultSet();

        while (rsTotalPago.next()) {
            FinanceiroSaida f = new FinanceiroSaida();
            f.setMsg("Total Pago:");
            f.setValor(rsTotalPago.getDouble("total"));
            aux.add(f);
        }

        while (rsTotalPagar.next()) {
            FinanceiroSaida f = new FinanceiroSaida();
            f.setMsg("Total Pagar:");
            f.setValor(rsTotalPagar.getDouble("total"));
            aux.add(f);
        }
        while (rsTotalVencidos.next()) {
            FinanceiroSaida f = new FinanceiroSaida();
            f.setMsg("Total Vencidos:");
            f.setValor(rsTotalVencidos.getDouble("total"));
            aux.add(f);
        }
        return aux;

    }

    /**
     * Metodo responsavel por realizar um busca de financeiros_saidas no BD
     * retonarnando o total por fornecedor realizando filtros de datas
     *
     * @param danaInical e e Um Objeto Date fornecido pelo usario, para filtar a
     * bunca do ponto de inicio
     * @param dataFinal e um Objeto Date fornecido pelo usuario,para filtar a
     * busca ate um ponto final
     * @return aux Objeto da busca
     * @throws SQLException lança uma exceção
     */
    /*
        public static ArrayList<FinanceiroSaida>retriaveByFornecedor
        (Date dataInicial,Date dataFinal) throws SQLException
        {
            ArrayList<FinanceiroSaida>aux= new ArrayList<>();
            
            Connection conn = BancoDados.createConnection();
            String totalVencidos  = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where (data_emissao >= ? and data_emissao<=?) and data_vencimento<=current_date and data_baixa is  null group by fk_compra,nome"; 
            String totalPagar = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where data_emissao>=? and data_emissao <=? and data_baixa  is  null group by fk_compra,nome";
            String totalPagos = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where data_emissao>=? and data_emissao <=? and data_baixa  is not null group by fk_compra,pk_fornecedor,nome";
            
            PreparedStatement stmVencidos = conn.prepareStatement(totalVencidos);
            stmVencidos.setDate(1, dataInicial);
            stmVencidos.setDate(2, dataFinal);
            stmVencidos.execute();
            ResultSet rsVencidos = stmVencidos.getResultSet();
           
            PreparedStatement stmPagar = conn.prepareStatement(totalPagar);
            stmPagar.setDate(1, dataInicial);
            stmPagar.setDate(2, dataFinal);
            stmPagar.execute();
            ResultSet rsPagar = stmPagar.getResultSet();
            
            
            PreparedStatement stmPagos = conn.prepareStatement(totalPagos);
            stmPagos.setDate(1, dataInicial);
            stmPagos.setDate(2, dataFinal);
            stmPagos.execute();
            ResultSet rsPagos = stmPagos.getResultSet();
            
            
            FinanceiroSaida fornecedor = new FinanceiroSaida();
             
           // f.setEnderecos(FornecedorEnderecoDAO.retrieveAll(f.getPk_fornecedor()));
            
            while(rsPagos.next()){
              
            
                
               FinanceiroSaida f = new FinanceiroSaida();
               f.setFornecedor("Recebido: " +FornecedorDAO.retrieve(rsPagos.getInt("fk_compra")).getNome());
               f.setValor(rsPagos.getDouble("total"));
               aux.add(f);
            
            
            }
            
            
            while(rsVencidos.next()){
               FinanceiroSaida f = new FinanceiroSaida();
               f.setFornecedor("Vencidos: " +FornecedorDAO.retrieve(rsVencidos.getInt("fk_compra")).getNome());
               f.setValor(rsVencidos.getDouble("total"));
               aux.add(f);
            }
             while(rsPagar.next()){
               FinanceiroSaida f = new FinanceiroSaida();
               f.setFornecedor("A receber: " +FornecedorDAO.retrieve(rsPagar.getInt("fk_compra")).getNome());
               f.setValor(rsPagar.getDouble("total"));
               aux.add(f);
            }
             
             
  
        
            return aux;
            
        }
     */
    /**
     * Metodo responsavel por realizar um busca de financeiros_saidas no BD
     * retonarnando o total por fornecedor realizando filtros de datas
     *
     * @param danaInical e e Um Objeto Date fornecido pelo usario, para filtar a
     * bunca do ponto de inicio
     * @param dataFinal e um Objeto Date fornecido pelo usuario,para filtar a
     * busca ate um ponto final
     * @return aux Objeto da busca
     * @throws SQLException lança uma exceção
     */
    public static ArrayList<FinanceiroSaida> retriaveByFornecedor(Date dataInicial, Date dataFinal) throws SQLException {
        ArrayList<FinanceiroSaida> aux = new ArrayList<>();

        Connection conn = BancoDados.createConnection();
        String totalVencidos = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where (data_emissao >= ? and data_emissao<=?) and data_vencimento<=current_date and data_baixa is  null group by fk_compra,nome";
        String totalPagar = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where data_emissao>=? and data_emissao <=? and data_baixa  is  null group by fk_compra,nome";
        String totalPagos = "select fk_compra,nome,sum(valor)as total from fornecedores inner join financeiros_saidas on pk_fornecedor = fk_compra  where data_emissao>=? and data_emissao <=? and data_baixa  is not null group by fk_compra,pk_fornecedor,nome";

        PreparedStatement stmVencidos = conn.prepareStatement(totalVencidos);
        stmVencidos.setDate(1, dataInicial);
        stmVencidos.setDate(2, dataFinal);
        stmVencidos.execute();
        ResultSet rsVencidos = stmVencidos.getResultSet();

        PreparedStatement stmPagar = conn.prepareStatement(totalPagar);
        stmPagar.setDate(1, dataInicial);
        stmPagar.setDate(2, dataFinal);
        stmPagar.execute();
        ResultSet rsPagar = stmPagar.getResultSet();

        PreparedStatement stmPagos = conn.prepareStatement(totalPagos);
        stmPagos.setDate(1, dataInicial);
        stmPagos.setDate(2, dataFinal);
        stmPagos.execute();
        ResultSet rsPagos = stmPagos.getResultSet();

        while (rsPagos.next()) {

            Fornecedor fonecedor = new Fornecedor();
            fonecedor.setNome(rsPagos.getString("nome"));
            FinanceiroSaida f = new FinanceiroSaida(fonecedor,
                    rsPagos.getDouble("total"), "Pagos:"
            );
            aux.add(f);

        }

        while (rsVencidos.next()) {

            Fornecedor fonecedor = new Fornecedor();
            fonecedor.setNome(rsVencidos.getString("nome"));
            FinanceiroSaida f = new FinanceiroSaida(fonecedor,
                    rsVencidos.getDouble("total"), "Vencidos:"
            );
            aux.add(f);

        }
        while (rsPagar.next()) {

            Fornecedor fonecedor = new Fornecedor();
            fonecedor.setNome(rsPagar.getString("nome"));
            FinanceiroSaida f = new FinanceiroSaida(fonecedor,
                    rsPagar.getDouble("total"), "A pagar:"
            );
            aux.add(f);

        }

        return aux;

    }

}

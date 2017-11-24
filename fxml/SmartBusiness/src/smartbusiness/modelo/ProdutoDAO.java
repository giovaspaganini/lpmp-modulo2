package smartbusiness.modelo;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import smartbusiness.controle.Produto;

/**
 * Realiza as resposabilidades comportamentais necessárias para a persistencia
 * de um Produto no banco de dados
 *
 * @author Fernando
 */
public class ProdutoDAO {

    /**
     * Método responsável por receber um produto e registrá-lo no Banco de Dados
     *
     * @param p recebe o objeto Produto vindo como parâmetro
     * @return retorna a chave primária gerada para o produto criado
     * @throws SQLException
     */
    public static int create(Produto p) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm
                = conn.prepareStatement("insert into produtos (nome, estoque_minimo,qtd_estoque) values (?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoque_minimo());
        stm.setInt(3, p.getQtd_estoque());
        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        p.setPk_produto(rs.getInt("pk_produto"));

        stm.close();

        return p.getPk_produto();
    }

    /**
     * Método responsável por receber uma chave primaria e retornar um produto
     *
     * @param pk recebe a chave primaria
     * @return retorna o produto baseado na chave primária
     * @throws SQLException
     */
    public static Produto retrieve(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("select * from produtos where pk_produto = ?");

        stm.setInt(1, pk);
        stm.execute();

        ResultSet rs = stm.getResultSet();
        rs.next();

        return new Produto(rs.getInt("pk_produto"),
                rs.getString("nome"),
                rs.getInt("estoque_minimo"),
                rs.getInt("qtd_estoque"));

    }

    /**
     * Método responsável por retornar uma lista de todos os produtos no banco
     * de dados
     *
     * @return @throws SQLException
     */
    public static ArrayList<Produto> retrieveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        ArrayList<Produto> aux = new ArrayList<>();

        ResultSet rs = conn.createStatement().executeQuery("select * from produtos");

        while (rs.next()) {

            aux.add(new Produto(rs.getInt("pk_produto"),
                    rs.getString("nome"),
                    rs.getInt("estoque_minimo"),
                    rs.getInt("qtd_estoque"))
            );

        }
        return aux;

    }

    /**
     * Método responsável por retonar um relatório de Estoque
     *
     * @return retorna uma String com o relátorio do Estoque
     * @throws SQLException
     */
    public static String retrieveRelatorioEstoque() throws SQLException {
        String relatorio = "Nome do produto, QTD_Estoque, Estoque_mínimo, Estoque dinâmico\n";
        Connection conn = BancoDados.createConnection();

        ResultSet rsP = conn.createStatement().executeQuery("select * from produtos");
        PreparedStatement stmC = conn.prepareStatement("select * from compras_itens where fk_produto=?");
        PreparedStatement stmV = conn.prepareStatement("select * from vendas_itens where fk_produto=?");
        ResultSet rs;

        while (rsP.next()) {
            int compras = 0, vendas = 0;
            relatorio += rsP.getString("nome") + ", ";
            relatorio += rsP.getString("qtd_estoque") + ", ";
            relatorio += rsP.getString("estoque_minimo") + ", ";

            stmC.setInt(1, rsP.getInt("pk_produto"));
            stmC.execute();
            rs = stmC.getResultSet();
            while (rs.next()) {
                compras += rs.getInt("qtd");
            }

            stmV.setInt(1, rsP.getInt("pk_produto"));
            stmV.execute();
            rs = stmV.getResultSet();
            while (rs.next()) {
                vendas += rs.getInt("qtd");
            }

            relatorio += compras - vendas + "\n";
        }
        stmC.close();
        stmV.close();
        return relatorio;
    }

    /**
     * Método responsável por retornar um relatório de lucro bruto dentro de um
     * período
     *
     * @param dataInicial recebe a dada inicial do período
     * @param dataFinal recebe a data final do período
     * @return retornar uma string com o relatório de lucro bruto
     * @throws SQLException
     */
    public static String retrieveRelatorioLucroBruto(Date dataInicial, Date dataFinal) throws SQLException {
        String relatorio = "Período apurado: " + new java.sql.Date(dataInicial.getTime())
                + " a " + new java.sql.Date(dataFinal.getTime()) + "\n";
        relatorio += "Nome do produto, Total Compras R$,Total Vendas R$, Lucro Bruto R$\n";
        Connection conn = BancoDados.createConnection();

        ResultSet rsP = conn.createStatement().executeQuery("select * from produtos");

        PreparedStatement stmC
                = conn.prepareStatement("select ci.fk_produto, ci.qtd, ci.valor_unitario\n"
                        + "from compras com inner join compras_itens ci\n"
                        + "on com.pk_compra = ci.fk_compra and com.datas between ? and ?");
        stmC.setDate(1, new java.sql.Date(dataInicial.getTime()));
        stmC.setDate(2, new java.sql.Date(dataFinal.getTime()));

        PreparedStatement stmV
                = conn.prepareStatement("select vi.fk_produto, vi.qtd, vi.valor_unitario\n"
                        + "from vendas v inner join vendas_itens vi\n"
                        + "on v.pk_venda = vi.fk_venda and v.data between ? and ?");
        stmV.setDate(1, new java.sql.Date(dataInicial.getTime()));
        stmV.setDate(2, new java.sql.Date(dataFinal.getTime()));

        ResultSet rsCV;

        while (rsP.next()) {
            double compras = 0, vendas = 0;
            relatorio += rsP.getString("nome") + ": ";

            //calculando o valor das compras do produto atual
            stmC.execute();
            rsCV = stmC.getResultSet();
            while (rsCV.next()) {
                if (rsP.getInt("pk_produto") == rsCV.getInt("fk_produto")) {
                    compras += rsCV.getInt("qtd") * rsCV.getDouble("valor_unitario");
                }
            }
            relatorio += compras + ", ";

            //calculando o valor das vendas do produto atual
            stmV.execute();
            rsCV = stmV.getResultSet();
            while (rsCV.next()) {
                if (rsP.getInt("pk_produto") == rsCV.getInt("fk_produto")) {
                    vendas += rsCV.getInt("qtd") * rsCV.getDouble("valor_unitario");
                }
            }
            relatorio += vendas + ", ";

            relatorio += vendas - compras + "\n";
        }
        return relatorio;
    }

    /**
     * Método responsável por receber um produto e atualizá-lo no banco de Dados
     *
     * @param p recebe o produto
     * @throws SQLException
     */
    public static void update(Produto p) throws SQLException {
        if (p.getPk_produto() == 0) {
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("update produtos set nome=?,estoque_minimo=?,qtd_estoque=? where pk_produto = ?");

        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoque_minimo());
        stm.setInt(3, p.getQtd_estoque());
        stm.setInt(4, p.getPk_produto());
        stm.execute();

        stm.close();

    }

    /**
     * Método responsáel por receber um produto e excluí-lo do Banco de dados
     *
     * @param p recebe o produto a ser excuí-do
     * @throws SQLException
     */
    public static void delete(Produto p) throws SQLException {
        if (p.getPk_produto() == 0) {
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("delete from produtos where pk_produto=?");
        stm.setInt(1, p.getPk_produto());
        stm.execute();
        stm.close();
    }
}

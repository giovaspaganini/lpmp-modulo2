package smartbusiness.controle;

/**
 *
 * @author Fernando
 */

public class Produto {

    private int pk_produto;
    private String nome;
    private int estoque_minimo, qtd_estoque;

    /**
     *
     * @param pk_produto recebe a chave primaria do produto
     * @param nome recebe o nome do produto
     * @param estoque_minimo recebe o valor do estoque minimo definido para
     * produto
     * @param qtd_estoque recebe a quantidade em estoque do produto
     */
    public Produto(int pk_produto, String nome, int estoque_minimo, int qtd_estoque) {
        this.pk_produto = pk_produto;
        this.nome = nome;
        this.estoque_minimo = estoque_minimo;
        this.qtd_estoque = qtd_estoque;
    }

    public Produto(String nome, int estoque_minimo, int qtd_estoque) {
        this.nome = nome;
        this.estoque_minimo = estoque_minimo;
        this.qtd_estoque = qtd_estoque;
    }

    public int getPk_produto() {
        return pk_produto;
    }

    public void setPk_produto(int pk_produto) {
        this.pk_produto = pk_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(int estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    @Override
    public String toString() {
        return "Produto{" + "pk_produto=" + pk_produto + ", nome=" + nome + ", estoque_minimo=" + estoque_minimo + ", qtd_estoque=" + qtd_estoque +"}\n";
    }

}

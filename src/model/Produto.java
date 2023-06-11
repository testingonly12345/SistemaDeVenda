package model;

public class Produto implements IMostrarDados {
    private int idProduto;
    private String descricao;
    private double precoUnitario;
    private int quantidadeEstoque;

    @Override
    public void mostrarDados() {
        System.out.println("\nDescricao do Produto: " + this.getDescricao() + "\nPreco Unitario: R$ " + this.getPrecoUnitario() +
                "\nQuantidade em Estoque: " + this.getQuantidadeEstoque());
    }

    //atualizado para dar baixa em cada venda feita
    public void atualizarEstoque(int quantidade) {

        this.quantidadeEstoque = quantidadeEstoque - quantidade;
    }

    public Produto(int idProduto, String descricao, double precoUnitario, int quantidadeEstoque) {
        this.setIdProduto(idProduto);
        this.setDescricao(descricao);
        this.setPrecoUnitario(precoUnitario);
        this.setQuantidadeEstoque(quantidadeEstoque);
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}

package model;

public class ItemVenda implements IMostrarDados {
    private int idItemVenda;
    private int quantidadeVendida;
    private double valorTotal; // Este atributo não vai como parâmetro para o CONSTRUTOR, pois ele é calculado AUTOMATICAMENTE.
    private Produto produto;
    private Venda venda;

    @Override
    public void mostrarDados() {
        System.out.println("\nDescricao do Produto: " + this.getProduto().getDescricao() + "\nQuantidade em Estoque: "
                + this.getProduto().getQuantidadeEstoque() + "\nQuantidade Vendida: " + this.quantidadeVendida +
                 "\nValor Total: " + this.valorTotal);
    }

    public void calcularValorTotal() {
        valorTotal = this.quantidadeVendida * this.getProduto().getPrecoUnitario();
    }

    // ATENÇÃO NESTE CONSTRUTOR
    // Ele recebe apenas QUATRO PARÂMETROS DIRETOS: idItemVenda, quantidadeVendida, produto, venda.
    // O valorTotal, vai como PARÂMETRO MAS EM FORMA DO MÉTODO CRIADO: calcularValorTotal().

    public ItemVenda(int idItemVenda, int quantidadeVendida, Produto produto, Venda venda) {
        this.setIdItemVenda(idItemVenda);
        this.setQuantidadeVendida(quantidadeVendida);
        this.setProduto(produto);
        this.setVenda(venda);
        calcularValorTotal();
    }

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    //ATENÇÃO: Não teremos o setValorTotal, pois não queremos
    // que o valor deste atributo seja modificado fora desta classe, o que poderia
    // gerar inconsistências
    /*
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    */
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}

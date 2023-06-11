package model;

import java.util.ArrayList;
import java.util.List;

public class Venda implements IMostrarDados {
    private int idVenda;
    private String dataVenda;
    private double valorTotalVenda; // Este atributo NÃO vai como Parâmetro para o CONSTRUTOR, pois ele será calculado
                            // AUTOMATICAMENTE
                            private List<ItemVenda> itemVendaList = new ArrayList<>(); // Este atributo NÃO vai como Parâmetro para o CONSTRUTOR,
                                                      // porque será adicionado o item venda através do método
                                                      // addItemVenda

    //ATENÇÃO NESTE CONSTRUTOR
    // Ele recebe apenas 2 parâmetros diretamente: idVenda e dataVenda.
    // O valor total da venda é calculado automaticamente.
    // Já o List<ItemVenda> não vai como Parâmentro porque é adicionado depois pelo método addItemVenda
    // Portanto, não passamos no construtor


    @Override
    public void mostrarDados() {
        System.out.println("\n Numero da Venda: " + this.getIdVenda() + "\n Data da Venda: " + this.getDataVenda()
                + "\n Valor Total: R$ " + this.getValorTotalVenda());
        System.out.println(" Itens da Venda: ");
        for (ItemVenda i : getItemVendaList())
            System.out.println(" Produto: " + i.getProduto().getDescricao()
                               + "\n Quantidade: " + i.getQuantidadeVendida());
    }

    public Venda(int idVenda, String dataVenda) {
        this.setIdVenda(idVenda);
        this.setDataVenda(dataVenda);
    }

    public void calcularValorTotalVenda() {
        double total = 0;
        for (ItemVenda i : getItemVendaList()) {
            total = total + i.getValorTotal();
        }
        valorTotalVenda = total;
    }

    public void addItemVenda(ItemVenda...itemVenda) {
        getItemVendaList().addAll(List.of(itemVenda));
        // Ou assim: Collections.addAll(itemVendaList, itemVenda);
        this.calcularValorTotalVenda();
    }

    public void removerItemVenda(ItemVenda itemVenda) {
        getItemVendaList().remove(itemVenda);
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotalVenda() {
        return valorTotalVenda;
    }
    //ATENÇÃO: Não teremos o setValorTotalVenda, pois não queremos
    // que o valor deste atributo seja modificado fora desta classe, o que poderia
    // gerar inconsistências
    /*
    public void setValorTotalVenda(double valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }
    */
    public List<ItemVenda> getItemVendaList() {
        return itemVendaList;
    }

    public void setItemVendaList(List<ItemVenda> itemVendaList) {
        this.itemVendaList = itemVendaList;
    }
}

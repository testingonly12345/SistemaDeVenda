import data.ItemVendaSQLiteDAO;
import data.ProdutoSQLiteDAO;
import data.VendaSQLiteDAO;
import model.ItemVenda;
import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Produto p1 = new Produto(1, "chenelo", 20,12);
        Produto p2 = new Produto(2, "Sapato Social", 50, 23);
        Produto p3 = new Produto(3, "Sapato Esport_Social", 90, 15);
        Produto p4 = new Produto(4, "Tenis", 120, 44);
        Produto p5 = new Produto(5, "Sandalha", 85, 25);

        ProdutoSQLiteDAO produtoSQLiteDAO = new ProdutoSQLiteDAO();
        produtoSQLiteDAO.salvar(p1);
        produtoSQLiteDAO.salvar(p2);
        produtoSQLiteDAO.salvar(p3);
        produtoSQLiteDAO.salvar(p4);
        produtoSQLiteDAO.salvar(p5);


        Venda v1 = new Venda(1, "04/03/2023");
        Venda v2 = new Venda(2, "04/04/2023");
        Venda v3 = new Venda(3, "18/05/2023");
        Venda v4 = new Venda(4, "29/05/2023");
        Venda v5 = new Venda(5, "30/05/2023");

        VendaSQLiteDAO vendaSQLiteDAO = new VendaSQLiteDAO();
        vendaSQLiteDAO.salvar(v1);
        vendaSQLiteDAO.salvar(v2);
        vendaSQLiteDAO.salvar(v3);
        vendaSQLiteDAO.salvar(v4);
        vendaSQLiteDAO.salvar(v5);


        ItemVenda i1 = new ItemVenda(6, 2, p1, v1);
        ItemVenda i2 = new ItemVenda(7, 3, p2, v2);
        ItemVenda i3 = new ItemVenda(8, 5, p3, v3);
        ItemVenda i4 = new ItemVenda(9, 6, p4, v4);
        ItemVenda i5 = new ItemVenda(10, 1, p5, v5);

        ItemVendaSQLiteDAO itemVendaSQLiteDAO = new ItemVendaSQLiteDAO();
        itemVendaSQLiteDAO.salvar(i1);
        itemVendaSQLiteDAO.salvar(i2);
        itemVendaSQLiteDAO.salvar(i3);
        itemVendaSQLiteDAO.salvar(i4);
        itemVendaSQLiteDAO.salvar(i5);


        //adicionar itens e update onde fizer isso:
        i1.setProduto(p1);
        p1.atualizarEstoque(1);
        produtoSQLiteDAO.atualizar(p1); //atualiza p1 com qtdd nova no estoque
        itemVendaSQLiteDAO.atualizar(i1);

        i2.setProduto(p2);
        itemVendaSQLiteDAO.atualizar(i2);

        //cria lista de itemvenda para adicionar na Venda
        List<ItemVenda> itemVendaList = new ArrayList<>();
        itemVendaList.add(i1);
        itemVendaList.add(i2);

        //salva a lista na venda e atualiza no db
        v1.setItemVendaList(itemVendaList);
        v1.calcularValorTotalVenda(); //atualiza tambem o valor total de venda de v1, que esta como 0 no db
        vendaSQLiteDAO.atualizar(v1);

        for (Produto produto: produtoSQLiteDAO.buscarTodos()) {
            produto.mostrarDados();
        }

        for (Venda venda: vendaSQLiteDAO.buscarTodos()) {
            venda.mostrarDados();
        }

        for (ItemVenda itemVenda: itemVendaSQLiteDAO.buscarTodos()) {
            itemVenda.mostrarDados();
        }

        System.out.println("\n Buscar itens Venda: ");
        System.out.println("__________________________________");
        List<ItemVenda> itemVendaList2 = itemVendaSQLiteDAO.buscarItemVenda(v1);
        for (ItemVenda i : itemVendaList2)
            i.mostrarDados();
        System.out.println("__________________________________");


//        List<Produto> produtoList = produtoSQLiteDAO.buscar();
//        for (Produto p : produtoList)
//            p.mostrarDados();
//
//        List<Venda> vendaList = vendaSQLiteDAO.buscar();
//        for (Venda v : vendaList)
//            v.mostrarDados();


    }
}

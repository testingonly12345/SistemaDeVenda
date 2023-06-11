package data;

import model.ItemVenda;
import model.Produto;
import model.Venda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaSQLiteDAO implements ItemVendaDAO {

    @Override
    public  void salvar(ItemVenda itemVenda) {
        // estava como INSERT INTO itemVenda(idItemVenda, quantidadeVendida, valorTotal, Produto, Venda) VALUES (?,?,?,?,?)
        String sql = "INSERT INTO itemVenda(idItemVenda, quantidadeVendida, valorTotal, idProduto, idvenda) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, itemVenda.getIdItemVenda());
            stmt.setInt(2, itemVenda.getQuantidadeVendida());
            stmt.setDouble(3, itemVenda.getValorTotal());
            stmt.setInt(4, itemVenda.getProduto().getIdProduto());
            stmt.setInt(5, itemVenda.getVenda().getIdVenda());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void atualizar(ItemVenda itemVenda) {
        // "UPDATE itemVenda SET quantidadeVendida=?, valorTotal=?, Produto=?, Venda=?"
        String sql = "UPDATE itemVenda SET quantidadeVendida=?, valorTotal=?, idProduto=?, idVenda=?" +
                "WHERE idItemVenda = ?";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, itemVenda.getQuantidadeVendida());
            stmt.setDouble(2, itemVenda.getValorTotal());
            stmt.setInt(3, itemVenda.getProduto().getIdProduto());
            stmt.setInt(4, itemVenda.getVenda().getIdVenda());
            stmt.setInt(5, itemVenda.getIdItemVenda());
            stmt.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    @Override
    public void apagar(ItemVenda itemVenda) {
        String sql = "DELETE FROM itemVenda WHERE idVenda=?";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, itemVenda.getIdItemVenda());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ItemVenda buscar(int id) {
        // aqui estava como id, mas é idItemVenda
        String sql = "SELECT * FROM itemVenda WHERE idItemVenda=?";
        ItemVenda itemVenda = null;
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                // aqui estava como rs.getInt("produto"), mas é idProduto
                Produto produto = new ProdutoSQLiteDAO().buscar(rs.getInt("idProduto"));
                // aqui estava como rs.getInt("venda"), mas é idVenda
                Venda venda = new VendaSQLiteDAO().buscar(rs.getInt("idVenda"));
                itemVenda = new ItemVenda(rs.getInt("idItemVenda"),rs.getInt("quantidadeVendida"),
                            produto, venda);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return itemVenda;
    }

    @Override
    public List<ItemVenda> buscarTodos() {
        String sql = "SELECT * FROM itemVenda";
        List<ItemVenda> ListaItemVendas = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                // aqui estava como rs.getInt("produto"), mas é idProduto
                Produto produto = new ProdutoSQLiteDAO().buscar(rs.getInt("idProduto"));
                // aqui estava como rs.getInt("venda"), mas é idVenda
                Venda venda = new VendaSQLiteDAO().buscar(rs.getInt("idVenda"));
                ItemVenda itemVenda = new ItemVenda(rs.getInt("idItemVenda"),rs.getInt("quantidadeVendida"),
                        produto, venda);
                ListaItemVendas.add(itemVenda);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ListaItemVendas;
    }

    @Override
    //Aqui tinha um problema pois estava sem parâmetro (Venda venda)
    //O nome da classe deveria ser buscar>Itens<Venda, não parece mas quando vc bate o olho isso te diz muito sobre a função dela
    //foi o que me levou a confusão lá no wpp kkkkkk.
    public List<ItemVenda> buscarItemVenda(Venda venda) {
        // aqui estava como id, mas é idItemVenda
        String sql = "SELECT * FROM itemVenda WHERE idItemVenda=?";

        //declaro a lista fora do for pra não ter problemas de acesso ao escopo (o que é criado DENTRO do for só existe lá dentro)
        List<ItemVenda> ListaItemVendas = new ArrayList<>();

        //pra cada itemVenda dentro da Venda que passou como parametro
        for (ItemVenda item: venda.getItemVendaList()) {
            try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
                //pega o id do item da vez e seta como o idItemVenda lá na query SQL
                stmt.setInt(1, item.getIdItemVenda());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    // aqui estava como rs.getInt("produto"), mas é idProduto
                    Produto produto = new ProdutoSQLiteDAO().buscar(rs.getInt("idProduto"));
                    //como a venda já é passada como parâmetro, apenas coloco-a direto no construtor, sem precisar buscar com SQLiteDAO
                    ItemVenda itemVenda = new ItemVenda(rs.getInt("idItemVenda"), rs.getInt("quantidadeVendida"),
                            produto, venda);
                    //adiciona na lista
                    ListaItemVendas.add(itemVenda);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ListaItemVendas;
    }
}

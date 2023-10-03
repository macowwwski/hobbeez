package DAOs;

import Entidades.Produtos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOProdutos extends DAOGenerico<Produtos> {

    private List<Produtos> lista = new ArrayList<>();

    public DAOProdutos() {
        super(Produtos.class);
    }

    public int autoIdProdutos() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Produtos e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Produtos> listByidProduto(String idProduto) {
       return em.createQuery("SELECT e FROM Produtos e WHERE e.idProduto) LIKE :idProduto").setParameter("idProduto", "%" + idProduto + "%").getResultList();
}

    public List<Produtos> listBynomeProduto(String nomeProduto) {
       return em.createQuery("SELECT e FROM Produtos e WHERE e.nomeProduto) LIKE :nomeProduto").setParameter("nomeProduto", "%" + nomeProduto + "%").getResultList();
}

    public List<Produtos> listByquantidadeProduto(String quantidadeProduto) {
       return em.createQuery("SELECT e FROM Produtos e WHERE e.quantidadeProduto) LIKE :quantidadeProduto").setParameter("quantidadeProduto", "%" + quantidadeProduto + "%").getResultList();
}

public List<Produtos> listInOrderidProduto() {
        return em.createQuery("SELECT e FROM Produtose ORDER BY e.idProduto").getResultList();
    }public List<Produtos> listInOrdernomeProduto() {
        return em.createQuery("SELECT e FROM Produtose ORDER BY e.nomeProduto").getResultList();
    }public List<Produtos> listInOrderquantidadeProduto() {
        return em.createQuery("SELECT e FROM Produtose ORDER BY e.quantidadeProduto").getResultList();
    }public static void main(String[] args) {
        DAOProdutos daoProdutos = new DAOProdutos();
        List<Produtos> listaProdutos = daoProdutos.list();
        for (Produtos produtos : listaProdutos) {
System.out.println(produtos.getIdProduto() + "-" + produtos.getNomeProduto() + "-" + produtos.getQuantidadeProduto() + "-" + "");
        }
    }
}

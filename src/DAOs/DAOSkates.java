package DAOs;

import Entidades.Skates;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOSkates extends DAOGenerico<Skates> {

    private List<Skates> lista = new ArrayList<>();

    public DAOSkates() {
        super(Skates.class);
    }

    public int autoIdSkates() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Skates e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Skates> listByprodutos_idProduto(String produtos_idProduto) {
       return em.createQuery("SELECT e FROM Skates e WHERE e.produtos_idProduto) LIKE :produtos_idProduto").setParameter("produtos_idProduto", "%" + produtos_idProduto + "%").getResultList();
}

    public List<Skates> listBymarca_idMarca(String marca_idMarca) {
       return em.createQuery("SELECT e FROM Skates e WHERE e.marca_idMarca) LIKE :marca_idMarca").setParameter("marca_idMarca", "%" + marca_idMarca + "%").getResultList();
}

    public List<Skates> listBynivelSkt(String nivelSkt) {
       return em.createQuery("SELECT e FROM Skates e WHERE e.nivelSkt) LIKE :nivelSkt").setParameter("nivelSkt", "%" + nivelSkt + "%").getResultList();
}

    public List<Skates> listByprecoSkt(String precoSkt) {
       return em.createQuery("SELECT e FROM Skates e WHERE e.precoSkt) LIKE :precoSkt").setParameter("precoSkt", "%" + precoSkt + "%").getResultList();
}

public List<Skates> listInOrderprodutos_idProduto() {
        return em.createQuery("SELECT e FROM Skatese ORDER BY e.produtos_idProduto").getResultList();
    }public List<Skates> listInOrdermarca_idMarca() {
        return em.createQuery("SELECT e FROM Skatese ORDER BY e.marca_idMarca").getResultList();
    }public List<Skates> listInOrdernivelSkt() {
        return em.createQuery("SELECT e FROM Skatese ORDER BY e.nivelSkt").getResultList();
    }public List<Skates> listInOrderprecoSkt() {
        return em.createQuery("SELECT e FROM Skatese ORDER BY e.precoSkt").getResultList();
    }public static void main(String[] args) {
        DAOSkates daoSkates = new DAOSkates();
        List<Skates> listaSkates = daoSkates.list();
        for (Skates skates : listaSkates) {
System.out.println(skates.getProdutosidProduto() + "-" + skates.getMarcaidMarca() + "-" + skates.getNivelSkt() + "-" + skates.getPrecoSkt() + "-" + "");
        }
    }
}

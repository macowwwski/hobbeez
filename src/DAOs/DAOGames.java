package DAOs;

import Entidades.Games;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOGames extends DAOGenerico<Games> {

    private List<Games> lista = new ArrayList<>();

    public DAOGames() {
        super(Games.class);
    }

    public int autoIdGames() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Games e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Games> listByprodutos_idProduto(String produtos_idProduto) {
       return em.createQuery("SELECT e FROM Games e WHERE e.produtos_idProduto) LIKE :produtos_idProduto").setParameter("produtos_idProduto", "%" + produtos_idProduto + "%").getResultList();
}

    public List<Games> listByidPlataforma(String idPlataforma) {
       return em.createQuery("SELECT e FROM Games e WHERE e.idPlataforma) LIKE :idPlataforma").setParameter("idPlataforma", "%" + idPlataforma + "%").getResultList();
}

    public List<Games> listBydesenvolvedorGm(String desenvolvedorGm) {
       return em.createQuery("SELECT e FROM Games e WHERE e.desenvolvedorGm) LIKE :desenvolvedorGm").setParameter("desenvolvedorGm", "%" + desenvolvedorGm + "%").getResultList();
}

    public List<Games> listByanoGm(String anoGm) {
       return em.createQuery("SELECT e FROM Games e WHERE e.anoGm) LIKE :anoGm").setParameter("anoGm", "%" + anoGm + "%").getResultList();
}

    public List<Games> listByprecoGm(String precoGm) {
       return em.createQuery("SELECT e FROM Games e WHERE e.precoGm) LIKE :precoGm").setParameter("precoGm", "%" + precoGm + "%").getResultList();
}

public List<Games> listInOrderprodutos_idProduto() {
        return em.createQuery("SELECT e FROM Gamese ORDER BY e.produtos_idProduto").getResultList();
    }public List<Games> listInOrderidPlataforma() {
        return em.createQuery("SELECT e FROM Gamese ORDER BY e.idPlataforma").getResultList();
    }public List<Games> listInOrderdesenvolvedorGm() {
        return em.createQuery("SELECT e FROM Gamese ORDER BY e.desenvolvedorGm").getResultList();
    }public List<Games> listInOrderanoGm() {
        return em.createQuery("SELECT e FROM Gamese ORDER BY e.anoGm").getResultList();
    }public List<Games> listInOrderprecoGm() {
        return em.createQuery("SELECT e FROM Gamese ORDER BY e.precoGm").getResultList();
    }public static void main(String[] args) {
        DAOGames daoGames = new DAOGames();
        List<Games> listaGames = daoGames.list();
        for (Games games : listaGames) {
System.out.println(games.getProdutosidProduto() + "-" + games.getIdPlataforma() + "-" + games.getDesenvolvedorGm() + "-" + games.getAnoGm() + "-" + games.getPrecoGm() + "-" + "");
        }
    }
}

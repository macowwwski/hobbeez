package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.GamesHasPlataformasgames;
import Entidades.GamesHasPlataformasgamesPK;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOGamesHasPlataformasgames extends DAOGenerico<GamesHasPlataformasgames> {

    private List<GamesHasPlataformasgames> lista = new ArrayList<>();

    public DAOGamesHasPlataformasgames() {
        super(GamesHasPlataformasgames.class);
    }

    public GamesHasPlataformasgames obter(GamesHasPlataformasgamesPK gamesHasPlataformasgamesPK) {
        return em.find(GamesHasPlataformasgames.class, gamesHasPlataformasgamesPK);
    }
    
    public int autoIdGamesHasPlataformasgames() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM GamesHasPlataformasgames e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<GamesHasPlataformasgames> listBygames_produtos_idProduto(String games_produtos_idProduto) {
        return em.createQuery("SELECT e FROM GamesHasPlataformasgames e WHERE e.games_produtos_idProduto) LIKE :games_produtos_idProduto").setParameter("games_produtos_idProduto", "%" + games_produtos_idProduto + "%").getResultList();
    }

    public List<GamesHasPlataformasgames> listByplataformasgames_idPlataforma(String plataformasgames_idPlataforma) {
        return em.createQuery("SELECT e FROM GamesHasPlataformasgames e WHERE e.plataformasgames_idPlataforma) LIKE :plataformasgames_idPlataforma").setParameter("plataformasgames_idPlataforma", "%" + plataformasgames_idPlataforma + "%").getResultList();
    }

    public List<GamesHasPlataformasgames> listBystatus(String status) {
        return em.createQuery("SELECT e FROM GamesHasPlataformasgames e WHERE e.status) LIKE :status").setParameter("status", "%" + status + "%").getResultList();
    }

    public List<GamesHasPlataformasgames> listInOrdergames_produtos_idProduto() {
        return em.createQuery("SELECT e FROM GamesHasPlataformasgamese ORDER BY e.games_produtos_idProduto").getResultList();
    }

    public List<GamesHasPlataformasgames> listInOrderplataformasgames_idPlataforma() {
        return em.createQuery("SELECT e FROM GamesHasPlataformasgamese ORDER BY e.plataformasgames_idPlataforma").getResultList();
    }

    public List<GamesHasPlataformasgames> listInOrderstatus() {
        return em.createQuery("SELECT e FROM GamesHasPlataformasgamese ORDER BY e.status").getResultList();
    }

    public static void main(String[] args) {
        DAOGamesHasPlataformasgames daoGamesHasPlataformasgames = new DAOGamesHasPlataformasgames();
        List<GamesHasPlataformasgames> listaGamesHasPlataformasgames = daoGamesHasPlataformasgames.list();
        for (GamesHasPlataformasgames gamesHasPlataformasgames : listaGamesHasPlataformasgames) {
            System.out.println(gamesHasPlataformasgames.getGames()+ "-" + gamesHasPlataformasgames.getStatus()+ "-" + gamesHasPlataformasgames.getGamesHasPlataformasgamesPK()+ "-" + gamesHasPlataformasgames.getPlataformasgames()+ "");
        }
    }
}

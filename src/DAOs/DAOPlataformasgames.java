package DAOs;

import Entidades.Plataformasgames;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOPlataformasgames extends DAOGenerico<Plataformasgames> {

    private List<Plataformasgames> lista = new ArrayList<>();

    public DAOPlataformasgames() {
        super(Plataformasgames.class);
    }

    public int autoIdPlataformasgames() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Plataformasgames e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Plataformasgames> listByidPlataforma(String idPlataforma) {
        return em.createQuery("SELECT e FROM Plataformasgames e WHERE e.idPlataforma) LIKE :idPlataforma").setParameter("idPlataforma", "%" + idPlataforma + "%").getResultList();
    }

    public List<Plataformasgames> listBynomePlataforma(String nomePlataforma) {
        return em.createQuery("SELECT e FROM Plataformasgames e WHERE e.nomePlataforma) LIKE :nomePlataforma").setParameter("nomePlataforma", "%" + nomePlataforma + "%").getResultList();
    }

    public List<Plataformasgames> listInOrderidPlataforma() {
        return em.createQuery("SELECT e FROM Plataformasgames e ORDER BY e.idPlataforma").getResultList();
    }

    public List<Plataformasgames> listInOrdernomePlataforma() {
        return em.createQuery("SELECT e FROM Plataformasgames e ORDER BY e.nomePlataforma").getResultList();
    }
    
    public String[] listInOrderNomeStringsArray() {
        List<Plataformasgames> lf = listInOrderidPlataforma();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getIdPlataforma()+ "-" + lf.get(i).getNomePlataforma());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPlataformasgames daoPlataformasgames = new DAOPlataformasgames();
        List<Plataformasgames> listaPlataformasgames = daoPlataformasgames.list();
        for (Plataformasgames plataformasgames : listaPlataformasgames) {
            System.out.println(plataformasgames.getIdPlataforma() + "-" + plataformasgames.getNomePlataforma() + "-" + "");
        }
    }
}

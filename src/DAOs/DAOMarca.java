package DAOs;

import Entidades.Marca;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOMarca extends DAOGenerico<Marca> {

    private List<Marca> lista = new ArrayList<>();

    public DAOMarca() {
        super(Marca.class);
    }

    public int autoIdMarca() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Marca e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Marca> listByidMarca(String idMarca) {
        return em.createQuery("SELECT e FROM Marca e WHERE e.idMarca) LIKE :idMarca").setParameter("idMarca", "%" + idMarca + "%").getResultList();
    }

    public List<Marca> listBynomeMarca(String nomeMarca) {
        return em.createQuery("SELECT e FROM Marca e WHERE e.nomeMarca) LIKE :nomeMarca").setParameter("nomeMarca", "%" + nomeMarca + "%").getResultList();
    }

    public List<Marca> listInOrderidMarca() {
        return em.createQuery("SELECT e FROM Marca e ORDER BY e.idMarca").getResultList();
    }

    public List<Marca> listInOrdernomeMarca() {
        return em.createQuery("SELECT e FROM Marca e ORDER BY e.nomeMarca").getResultList();
    }

    public String[] listInOrderNomeStringsArray() {
        List<Marca> lf = listInOrderidMarca();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getIdMarca()+ "-" + lf.get(i).getNomeMarca());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOMarca daoMarca = new DAOMarca();
        List<Marca> listaMarca = daoMarca.list();
        for (Marca marca : listaMarca) {
            System.out.println(marca.getIdMarca() + "-" + marca.getNomeMarca() + "-" + "");
        }
    }
}

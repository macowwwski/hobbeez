package DAOs;

import Entidades.Cds;
import Entidades.Produtos;
import Entidades.Marca;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOCds extends DAOGenerico<Cds> {

    private List<Cds> lista = new ArrayList<>();

    public DAOCds() {
        super(Cds.class);
    }

    public int autoIdCds() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Cds e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cds> listByprodutos_idProduto(String produtos_idProduto) {
        return em.createQuery("SELECT e FROM Cds e WHERE e.produtos_idProduto) LIKE :produtos_idProduto").setParameter("produtos_idProduto", "%" + produtos_idProduto + "%").getResultList();
    }

    public List<Cds> listBymarca_idMarca(String marca_idMarca) {
        return em.createQuery("SELECT e FROM Cds e WHERE e.marca_idMarca) LIKE :marca_idMarca").setParameter("marca_idMarca", "%" + marca_idMarca + "%").getResultList();
    }


    public List<Cds> listBygravadoraCd(String gravadoraCd) {
        return em.createQuery("SELECT e FROM Cds e WHERE e.gravadoraCd) LIKE :gravadoraCd").setParameter("gravadoraCd", "%" + gravadoraCd + "%").getResultList();
    }

    public List<Cds> listByanoCd(String anoCd) {
        return em.createQuery("SELECT e FROM Cds e WHERE e.anoCd) LIKE :anoCd").setParameter("anoCd", "%" + anoCd + "%").getResultList();
    }

    public List<Cds> listByprecoCd(String precoCd) {
        return em.createQuery("SELECT e FROM Cds e WHERE e.precoCd) LIKE :precoCd").setParameter("precoCd", "%" + precoCd + "%").getResultList();
    }

    public List<Cds> listInOrderprodutos_idProduto() {
        return em.createQuery("SELECT e FROM Cdse ORDER BY e.produtos_idProduto").getResultList();
    }

    public List<Cds> listInOrdermarca_idMarca() {
        return em.createQuery("SELECT e FROM Cdse ORDER BY e.marca_idMarca").getResultList();
    }


    public List<Cds> listInOrdergravadoraCd() {
        return em.createQuery("SELECT e FROM Cdse ORDER BY e.gravadoraCd").getResultList();
    }

    public List<Cds> listInOrderanoCd() {
        return em.createQuery("SELECT e FROM Cdse ORDER BY e.anoCd").getResultList();
    }

    public List<Cds> listInOrderprecoCd() {
        return em.createQuery("SELECT e FROM Cdse ORDER BY e.precoCd").getResultList();
    }

    public static void main(String[] args) {
        DAOCds daoCds = new DAOCds();
        List<Cds> listaCds = daoCds.list();
        for (Cds cds : listaCds) {
            System.out.println(cds.getProdutosidProduto() + "-" + cds.getMarcaidMarca()+ "-" + cds.getGravadoraCd() + "-" + cds.getAnoCd() + "-" + cds.getPrecoCd() + "-" + "");
        }
    }
}

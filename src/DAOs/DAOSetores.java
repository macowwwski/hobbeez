package DAOs;

import Entidades.Setores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOSetores extends DAOGenerico<Setores> {

    private List<Setores> lista = new ArrayList<>();

    public DAOSetores() {
        super(Setores.class);
    }

    public int autoIdSetores() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Setores e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Setores> listByidSetor(String idSetor) {
       return em.createQuery("SELECT e FROM Setores e WHERE e.idSetor) LIKE :idSetor").setParameter("idSetor", "%" + idSetor + "%").getResultList();
}

    public List<Setores> listBynomeSetor(String nomeSetor) {
       return em.createQuery("SELECT e FROM Setores e WHERE e.nomeSetor) LIKE :nomeSetor").setParameter("nomeSetor", "%" + nomeSetor + "%").getResultList();
}

    public List<Setores> listBysalarioSetor(String salarioSetor) {
       return em.createQuery("SELECT e FROM Setores e WHERE e.salarioSetor) LIKE :salarioSetor").setParameter("salarioSetor", "%" + salarioSetor + "%").getResultList();
}

public List<Setores> listInOrderidSetor() {
        return em.createQuery("SELECT e FROM Setorese ORDER BY e.idSetor").getResultList();
    }public List<Setores> listInOrdernomeSetor() {
        return em.createQuery("SELECT e FROM Setorese ORDER BY e.nomeSetor").getResultList();
    }public List<Setores> listInOrdersalarioSetor() {
        return em.createQuery("SELECT e FROM Setorese ORDER BY e.salarioSetor").getResultList();
    }public static void main(String[] args) {
        DAOSetores daoSetores = new DAOSetores();
        List<Setores> listaSetores = daoSetores.list();
        for (Setores setores : listaSetores) {
System.out.println(setores.getIdSetor() + "-" + setores.getNomeSetor() + "-" + setores.getSalarioSetor() + "-" + "");
        }
    }
}

package DAOs;

import Entidades.Funcionarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOFuncionarios extends DAOGenerico<Funcionarios> {

    private List<Funcionarios> lista = new ArrayList<>();

    public DAOFuncionarios() {
        super(Funcionarios.class);
    }

    public int autoIdFuncionarios() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Funcionarios e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Funcionarios> listByidFuncionario(String idFuncionario) {
       return em.createQuery("SELECT e FROM Funcionarios e WHERE e.idFuncionario) LIKE :idFuncionario").setParameter("idFuncionario", "%" + idFuncionario + "%").getResultList();
}

    public List<Funcionarios> listBynomeFuncionario(String nomeFuncionario) {
       return em.createQuery("SELECT e FROM Funcionarios e WHERE e.nomeFuncionario) LIKE :nomeFuncionario").setParameter("nomeFuncionario", "%" + nomeFuncionario + "%").getResultList();
}

    public List<Funcionarios> listByidSetor(String idSetor) {
       return em.createQuery("SELECT e FROM Funcionarios e WHERE e.idSetor) LIKE :idSetor").setParameter("idSetor", "%" + idSetor + "%").getResultList();
}

public List<Funcionarios> listInOrderidFuncionario() {
        return em.createQuery("SELECT e FROM Funcionariose ORDER BY e.idFuncionario").getResultList();
    }public List<Funcionarios> listInOrdernomeFuncionario() {
        return em.createQuery("SELECT e FROM Funcionariose ORDER BY e.nomeFuncionario").getResultList();
    }public List<Funcionarios> listInOrderidSetor() {
        return em.createQuery("SELECT e FROM Funcionariose ORDER BY e.idSetor").getResultList();
    }public static void main(String[] args) {
        DAOFuncionarios daoFuncionarios = new DAOFuncionarios();
        List<Funcionarios> listaFuncionarios = daoFuncionarios.list();
        for (Funcionarios funcionarios : listaFuncionarios) {
System.out.println(funcionarios.getIdFuncionario() + "-" + funcionarios.getNomeFuncionario() + "-" + funcionarios.getIdSetor() + "-" + "");
        }
    }
}

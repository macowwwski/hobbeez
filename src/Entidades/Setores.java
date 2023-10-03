/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author aninh
 */
@Entity
@Table(name = "setores")
@NamedQueries({
    @NamedQuery(name = "Setores.findAll", query = "SELECT s FROM Setores s")})
public class Setores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idSetor")
    private Integer idSetor;
    @Basic(optional = false)
    @Column(name = "nomeSetor")
    private String nomeSetor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salarioSetor")
    private Double salarioSetor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSetor")
    private List<Funcionarios> funcionariosList;

    public Setores() {
    }

    public Setores(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public Setores(Integer idSetor, String nomeSetor) {
        this.idSetor = idSetor;
        this.nomeSetor = nomeSetor;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public Double getSalarioSetor() {
        return salarioSetor;
    }

    public void setSalarioSetor(Double salarioSetor) {
        this.salarioSetor = salarioSetor;
    }

    public List<Funcionarios> getFuncionariosList() {
        return funcionariosList;
    }

    public void setFuncionariosList(List<Funcionarios> funcionariosList) {
        this.funcionariosList = funcionariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSetor != null ? idSetor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setores)) {
            return false;
        }
        Setores other = (Setores) object;
        if ((this.idSetor == null && other.idSetor != null) || (this.idSetor != null && !this.idSetor.equals(other.idSetor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Setores[ idSetor=" + idSetor + " ]";
    }
    
}

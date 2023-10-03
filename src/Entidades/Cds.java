/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aninh
 */
@Entity
@Table(name = "cds")
@NamedQueries({
    @NamedQuery(name = "Cds.findAll", query = "SELECT c FROM Cds c")})
public class Cds implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "gravadoraCd")
    private String gravadoraCd;
    @Basic(optional = false)
    @Column(name = "anoCd")
    @Temporal(TemporalType.DATE)
    private Date anoCd;
    @Basic(optional = false)
    @Column(name = "precoCd")
    private double precoCd;
    @Id
    @Basic(optional = false)
    @Column(name = "produtos_idProduto")
    private Integer produtosidProduto;
    @JoinColumn(name = "marca_idMarca", referencedColumnName = "idMarca")
    @ManyToOne(optional = false)
    private Marca marcaidMarca;
    @JoinColumn(name = "produtos_idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Produtos produtos;

    public Cds() {
    }

    public Cds(Integer produtosidProduto) {
        this.produtosidProduto = produtosidProduto;
    }

    public Cds(Integer produtosidProduto, String gravadoraCd, Date anoCd, double precoCd) {
        this.produtosidProduto = produtosidProduto;
        this.gravadoraCd = gravadoraCd;
        this.anoCd = anoCd;
        this.precoCd = precoCd;
    }

    public String getGravadoraCd() {
        return gravadoraCd;
    }

    public void setGravadoraCd(String gravadoraCd) {
        this.gravadoraCd = gravadoraCd;
    }

    public Date getAnoCd() {
        return anoCd;
    }

    public void setAnoCd(Date anoCd) {
        this.anoCd = anoCd;
    }

    public double getPrecoCd() {
        return precoCd;
    }

    public void setPrecoCd(double precoCd) {
        this.precoCd = precoCd;
    }

    public Integer getProdutosidProduto() {
        return produtosidProduto;
    }

    public void setProdutosidProduto(Integer produtosidProduto) {
        this.produtosidProduto = produtosidProduto;
    }

    public Marca getMarcaidMarca() {
        return marcaidMarca;
    }

    public void setMarcaidMarca(Marca marcaidMarca) {
        this.marcaidMarca = marcaidMarca;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produtosidProduto != null ? produtosidProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cds)) {
            return false;
        }
        Cds other = (Cds) object;
        if ((this.produtosidProduto == null && other.produtosidProduto != null) || (this.produtosidProduto != null && !this.produtosidProduto.equals(other.produtosidProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cds[ produtosidProduto=" + produtosidProduto + " ]";
    }
    
}

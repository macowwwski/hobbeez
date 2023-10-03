/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
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

/**
 *
 * @author aninh
 */
@Entity
@Table(name = "skates")
@NamedQueries({
    @NamedQuery(name = "Skates.findAll", query = "SELECT s FROM Skates s")})
public class Skates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nivelSkt")
    private String nivelSkt;
    @Basic(optional = false)
    @Column(name = "precoSkt")
    private double precoSkt;
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

    public Skates() {
    }

    public Skates(Integer produtosidProduto) {
        this.produtosidProduto = produtosidProduto;
    }

    public Skates(Integer produtosidProduto, String nivelSkt, double precoSkt) {
        this.produtosidProduto = produtosidProduto;
        this.nivelSkt = nivelSkt;
        this.precoSkt = precoSkt;
    }

    public String getNivelSkt() {
        return nivelSkt;
    }

    public void setNivelSkt(String nivelSkt) {
        this.nivelSkt = nivelSkt;
    }

    public double getPrecoSkt() {
        return precoSkt;
    }

    public void setPrecoSkt(double precoSkt) {
        this.precoSkt = precoSkt;
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
        if (!(object instanceof Skates)) {
            return false;
        }
        Skates other = (Skates) object;
        if ((this.produtosidProduto == null && other.produtosidProduto != null) || (this.produtosidProduto != null && !this.produtosidProduto.equals(other.produtosidProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Skates[ produtosidProduto=" + produtosidProduto + " ]";
    }
    
}

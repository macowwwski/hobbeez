/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aninh
 */
@Entity
@Table(name = "games")
@NamedQueries({
    @NamedQuery(name = "Games.findAll", query = "SELECT g FROM Games g")})
public class Games implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "games")
    private List<GamesHasPlataformasgames> gamesHasPlataformasgamesList;
   
    //RELACIONAMENTO N:M AQUI
    @JoinTable(name = "games_has_plataformasgames", joinColumns = {
        @JoinColumn(name = "games_produtos_idProduto", referencedColumnName = "produtos_idProduto")}, inverseJoinColumns = {
        @JoinColumn(name = "plataformasgames_idPlataforma", referencedColumnName = "idPlataforma")})
    @ManyToMany
    
    private List<Plataformasgames> plataformasgamesList;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "desenvolvedorGm")
    private String desenvolvedorGm;
    @Basic(optional = false)
    @Column(name = "anoGm")
    @Temporal(TemporalType.DATE)
    private Date anoGm;
    @Basic(optional = false)
    @Column(name = "precoGm")
    private double precoGm;
    @Id
    @Basic(optional = false)
    @Column(name = "produtos_idProduto")
    private Integer produtosidProduto;
    @JoinColumn(name = "idPlataforma", referencedColumnName = "idPlataforma")
    @ManyToOne(optional = false)
    private Plataformasgames idPlataforma;
    @JoinColumn(name = "produtos_idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Produtos produtos;

    public Games() {
    }

    public Games(Integer produtosidProduto) {
        this.produtosidProduto = produtosidProduto;
    }

    public Games(Integer produtosidProduto, String desenvolvedorGm, Date anoGm, double precoGm) {
        this.produtosidProduto = produtosidProduto;
        this.desenvolvedorGm = desenvolvedorGm;
        this.anoGm = anoGm;
        this.precoGm = precoGm;
    }

    public String getDesenvolvedorGm() {
        return desenvolvedorGm;
    }

    public void setDesenvolvedorGm(String desenvolvedorGm) {
        this.desenvolvedorGm = desenvolvedorGm;
    }

    public Date getAnoGm() {
        return anoGm;
    }

    public void setAnoGm(Date anoGm) {
        this.anoGm = anoGm;
    }

    public double getPrecoGm() {
        return precoGm;
    }

    public void setPrecoGm(double precoGm) {
        this.precoGm = precoGm;
    }

    public Integer getProdutosidProduto() {
        return produtosidProduto;
    }

    public void setProdutosidProduto(Integer produtosidProduto) {
        this.produtosidProduto = produtosidProduto;
    }

    public Plataformasgames getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(Plataformasgames idPlataforma) {
        this.idPlataforma = idPlataforma;
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
        if (!(object instanceof Games)) {
            return false;
        }
        Games other = (Games) object;
        if ((this.produtosidProduto == null && other.produtosidProduto != null) || (this.produtosidProduto != null && !this.produtosidProduto.equals(other.produtosidProduto))) {
            return false;
        }
        return true;
    }


    public List<Plataformasgames> getPlataformasgamesList() {
        return plataformasgamesList;
    }

    public void setPlataformasgamesList(List<Plataformasgames> plataformasgamesList) {
        this.plataformasgamesList = plataformasgamesList;
    }

    public List<GamesHasPlataformasgames> getGamesHasPlataformasgamesList() {
        return gamesHasPlataformasgamesList;
    }

    public void setGamesHasPlataformasgamesList(List<GamesHasPlataformasgames> gamesHasPlataformasgamesList) {
        this.gamesHasPlataformasgamesList = gamesHasPlataformasgamesList;
    }
    
    
    @Override
    public String toString() {
        return produtosidProduto + ";" + getProdutos().getNomeProduto() + getIdPlataforma();
    }
    
}

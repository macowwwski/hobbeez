/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author aninh
 */
@Embeddable
public class GamesHasPlataformasgamesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "games_produtos_idProduto")
    private int gamesprodutosidProduto;
    @Basic(optional = false)
    @Column(name = "plataformasgames_idPlataforma")
    private int plataformasgamesidPlataforma;

    public GamesHasPlataformasgamesPK() {
    }

    public GamesHasPlataformasgamesPK(int gamesprodutosidProduto, int plataformasgamesidPlataforma) {
        this.gamesprodutosidProduto = gamesprodutosidProduto;
        this.plataformasgamesidPlataforma = plataformasgamesidPlataforma;
    }

    public int getGamesprodutosidProduto() {
        return gamesprodutosidProduto;
    }

    public void setGamesprodutosidProduto(int gamesprodutosidProduto) {
        this.gamesprodutosidProduto = gamesprodutosidProduto;
    }

    public int getPlataformasgamesidPlataforma() {
        return plataformasgamesidPlataforma;
    }

    public void setPlataformasgamesidPlataforma(int plataformasgamesidPlataforma) {
        this.plataformasgamesidPlataforma = plataformasgamesidPlataforma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) gamesprodutosidProduto;
        hash += (int) plataformasgamesidPlataforma;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GamesHasPlataformasgamesPK)) {
            return false;
        }
        GamesHasPlataformasgamesPK other = (GamesHasPlataformasgamesPK) object;
        if (this.gamesprodutosidProduto != other.gamesprodutosidProduto) {
            return false;
        }
        if (this.plataformasgamesidPlataforma != other.plataformasgamesidPlataforma) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.GamesHasPlataformasgamesPK[ gamesprodutosidProduto=" + gamesprodutosidProduto + ", plataformasgamesidPlataforma=" + plataformasgamesidPlataforma + " ]";
    }
    
}

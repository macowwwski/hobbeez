/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aninh
 */
@Entity
@Table(name = "games_has_plataformasgames")
@NamedQueries({
    @NamedQuery(name = "GamesHasPlataformasgames.findAll", query = "SELECT g FROM GamesHasPlataformasgames g")})
public class GamesHasPlataformasgames implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GamesHasPlataformasgamesPK gamesHasPlataformasgamesPK;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "games_produtos_idProduto", referencedColumnName = "produtos_idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Games games;
    @JoinColumn(name = "plataformasgames_idPlataforma", referencedColumnName = "idPlataforma", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Plataformasgames plataformasgames;

    public GamesHasPlataformasgames() {
    }

    public GamesHasPlataformasgames(GamesHasPlataformasgamesPK gamesHasPlataformasgamesPK) {
        this.gamesHasPlataformasgamesPK = gamesHasPlataformasgamesPK;
    }

    public GamesHasPlataformasgames(int gamesprodutosidProduto, int plataformasgamesidPlataforma) {
        this.gamesHasPlataformasgamesPK = new GamesHasPlataformasgamesPK(gamesprodutosidProduto, plataformasgamesidPlataforma);
    }

    public GamesHasPlataformasgamesPK getGamesHasPlataformasgamesPK() {
        return gamesHasPlataformasgamesPK;
    }

    public void setGamesHasPlataformasgamesPK(GamesHasPlataformasgamesPK gamesHasPlataformasgamesPK) {
        this.gamesHasPlataformasgamesPK = gamesHasPlataformasgamesPK;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
    }

    public Plataformasgames getPlataformasgames() {
        return plataformasgames;
    }

    public void setPlataformasgames(Plataformasgames plataformasgames) {
        this.plataformasgames = plataformasgames;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gamesHasPlataformasgamesPK != null ? gamesHasPlataformasgamesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GamesHasPlataformasgames)) {
            return false;
        }
        GamesHasPlataformasgames other = (GamesHasPlataformasgames) object;
        if ((this.gamesHasPlataformasgamesPK == null && other.gamesHasPlataformasgamesPK != null) || (this.gamesHasPlataformasgamesPK != null && !this.gamesHasPlataformasgamesPK.equals(other.gamesHasPlataformasgamesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.GamesHasPlataformasgames[ gamesHasPlataformasgamesPK=" + gamesHasPlataformasgamesPK + " ]";
    }

}

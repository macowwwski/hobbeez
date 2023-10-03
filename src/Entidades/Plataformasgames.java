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
@Table(name = "plataformasgames")
@NamedQueries({
    @NamedQuery(name = "Plataformasgames.findAll", query = "SELECT p FROM Plataformasgames p")})
public class Plataformasgames implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plataformasgames")
    private List<GamesHasPlataformasgames> gamesHasPlataformasgamesList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPlataforma")
    private Integer idPlataforma;
    @Basic(optional = false)
    @Column(name = "nomePlataforma")
    private String nomePlataforma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlataforma")
    private List<Games> gamesList;

    public Plataformasgames() {
    }

    public Plataformasgames(Integer idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public Plataformasgames(Integer idPlataforma, String nomePlataforma) {
        this.idPlataforma = idPlataforma;
        this.nomePlataforma = nomePlataforma;
    }

    public Integer getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(Integer idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getNomePlataforma() {
        return nomePlataforma;
    }

    public void setNomePlataforma(String nomePlataforma) {
        this.nomePlataforma = nomePlataforma;
    }

    public List<Games> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Games> gamesList) {
        this.gamesList = gamesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlataforma != null ? idPlataforma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plataformasgames)) {
            return false;
        }
        Plataformasgames other = (Plataformasgames) object;
        if ((this.idPlataforma == null && other.idPlataforma != null) || (this.idPlataforma != null && !this.idPlataforma.equals(other.idPlataforma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPlataforma + ";" + nomePlataforma;
    }

    public List<GamesHasPlataformasgames> getGamesHasPlataformasgamesList() {
        return gamesHasPlataformasgamesList;
    }

    public void setGamesHasPlataformasgamesList(List<GamesHasPlataformasgames> gamesHasPlataformasgamesList) {
        this.gamesHasPlataformasgamesList = gamesHasPlataformasgamesList;
    }
    
}

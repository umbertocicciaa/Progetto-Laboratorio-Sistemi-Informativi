/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Time;

/**
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "ListaFermate")
@NamedQueries({
        @NamedQuery(name = "ListaFermate.findAll", query = "SELECT l FROM ListaFermate l"),
        @NamedQuery(name = "ListaFermate.findByIDCorsa", query = "SELECT l FROM ListaFermate l WHERE l.listaFermatePK.iDCorsa = :iDCorsa"),
        @NamedQuery(name = "ListaFermate.findByTargaAutobus", query = "SELECT l FROM ListaFermate l WHERE l.listaFermatePK.targaAutobus = :targaAutobus"),
        @NamedQuery(name = "ListaFermate.findByViaFermata", query = "SELECT l FROM ListaFermate l WHERE l.listaFermatePK.viaFermata = :viaFermata"),
        @NamedQuery(name = "ListaFermate.findByCittaFermata", query = "SELECT l FROM ListaFermate l WHERE l.listaFermatePK.cittaFermata = :cittaFermata"),
        @NamedQuery(name = "ListaFermate.findByLocalitaArrivo", query = "SELECT l FROM ListaFermate l WHERE l.localitaArrivo = :localitaArrivo"),
        @NamedQuery(name = "ListaFermate.findByLocalitaPartenza", query = "SELECT l FROM ListaFermate l WHERE l.localitaPartenza = :localitaPartenza")})
public class ListaFermate implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ListaFermatePK listaFermatePK;
    @Column(name = "localitaArrivo")
    private String localitaArrivo;
    @Column(name = "localitaPartenza")
    private String localitaPartenza;

    private Time orarioArrivo;
    @JoinColumns({
            @JoinColumn(name = "IDCorsa", referencedColumnName = "IDCorsa", insertable = false, updatable = false),
            @JoinColumn(name = "TargaAutobus", referencedColumnName = "Autobus", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Corsa corsa;
    @JoinColumns({
            @JoinColumn(name = "ViaFermata", referencedColumnName = "via", insertable = false, updatable = false),
            @JoinColumn(name = "CittaFermata", referencedColumnName = "citta", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Fermata fermata;

    public ListaFermate() {
    }

    public ListaFermate(ListaFermatePK listaFermatePK) {
        this.listaFermatePK = listaFermatePK;
    }

    public ListaFermate(int iDCorsa, String targaAutobus, String viaFermata, String cittaFermata) {
        this.listaFermatePK = new ListaFermatePK(iDCorsa, targaAutobus, viaFermata, cittaFermata);
    }

    public ListaFermate(int iDCorsa, String targaAutobus, String viaFermata, String cittaFermata, String localitaPartenza, String localitaArivo, Time orarioArrivo) {
        this.listaFermatePK = new ListaFermatePK(iDCorsa, targaAutobus, viaFermata, cittaFermata);
        this.orarioArrivo = orarioArrivo;
        this.localitaArrivo = localitaArivo;
        this.localitaPartenza = localitaPartenza;
    }

    public ListaFermatePK getListaFermatePK() {
        return listaFermatePK;
    }

    public void setListaFermatePK(ListaFermatePK listaFermatePK) {
        this.listaFermatePK = listaFermatePK;
    }

    public String getLocalitaArrivo() {
        return localitaArrivo;
    }

    public void setLocalitaArrivo(String localitaArrivo) {
        this.localitaArrivo = localitaArrivo;
    }

    public String getLocalitaPartenza() {
        return localitaPartenza;
    }

    public void setLocalitaPartenza(String localitaPartenza) {
        this.localitaPartenza = localitaPartenza;
    }

    public Corsa getCorsa() {
        return corsa;
    }

    public void setCorsa(Corsa corsa) {
        this.corsa = corsa;
    }

    public Fermata getFermata() {
        return fermata;
    }

    public void setFermata(Fermata fermata) {
        this.fermata = fermata;
    }

    public Time getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(Time orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listaFermatePK != null ? listaFermatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaFermate)) {
            return false;
        }
        ListaFermate other = (ListaFermate) object;
        return (this.listaFermatePK != null || other.listaFermatePK == null) && (this.listaFermatePK == null || this.listaFermatePK.equals(other.listaFermatePK));
    }

    @Override
    public String toString() {
        return "data.ListaFermate[ listaFermatePK=" + listaFermatePK + " ]";
    }

}

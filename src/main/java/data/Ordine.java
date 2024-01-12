/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Ordine")
@NamedQueries({
    @NamedQuery(name = "Ordine.findAll", query = "SELECT o FROM Ordine o"),
    @NamedQuery(name = "Ordine.findByNumero", query = "SELECT o FROM Ordine o WHERE o.numero = :numero"),
    @NamedQuery(name = "Ordine.findByStato", query = "SELECT o FROM Ordine o WHERE o.stato = :stato"),
    @NamedQuery(name = "Ordine.findByData", query = "SELECT o FROM Ordine o WHERE o.data = :data"),
    @NamedQuery(name = "Ordine.findByQuantita", query = "SELECT o FROM Ordine o WHERE o.quantita = :quantita")})
public class Ordine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Numero")
    private Integer numero;
    @Column(name = "Stato")
    private String stato;
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "Quantita")
    private Integer quantita;
    @JoinColumn(name = "AutomezzoDaOrdinare", referencedColumnName = "targa")
    @ManyToOne
    private Automezzo automezzoDaOrdinare;
    @JoinColumn(name = "ProdottoDaOrdinare", referencedColumnName = "CodProdotto")
    @ManyToOne
    private Prodotto prodottoDaOrdinare;

    public Ordine() {
    }

    public Ordine(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Automezzo getAutomezzoDaOrdinare() {
        return automezzoDaOrdinare;
    }

    public void setAutomezzoDaOrdinare(Automezzo automezzoDaOrdinare) {
        this.automezzoDaOrdinare = automezzoDaOrdinare;
    }

    public Prodotto getProdottoDaOrdinare() {
        return prodottoDaOrdinare;
    }

    public void setProdottoDaOrdinare(Prodotto prodottoDaOrdinare) {
        this.prodottoDaOrdinare = prodottoDaOrdinare;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordine)) {
            return false;
        }
        Ordine other = (Ordine) object;
        return (this.numero != null || other.numero == null) && (this.numero == null || this.numero.equals(other.numero));
    }

    @Override
    public String toString() {
        return "data.Ordine[ numero=" + numero + " ]";
    }
    
}

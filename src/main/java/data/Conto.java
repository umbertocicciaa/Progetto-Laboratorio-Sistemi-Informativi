/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Conto")
@NamedQueries({
    @NamedQuery(name = "Conto.findAll", query = "SELECT c FROM Conto c"),
    @NamedQuery(name = "Conto.findByIban", query = "SELECT c FROM Conto c WHERE c.iban = :iban"),
    @NamedQuery(name = "Conto.findBySaldo", query = "SELECT c FROM Conto c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Conto.findByTitolare", query = "SELECT c FROM Conto c WHERE c.titolare = :titolare")})
public class Conto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IBAN")
    private String iban;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Column(name = "titolare")
    private String titolare;
    @OneToMany(mappedBy = "conto")
    private Collection<TransazioneFinanziaria> transazioneFinanziariaCollection;

    public Conto() {
    }

    public Conto(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTitolare() {
        return titolare;
    }

    public void setTitolare(String titolare) {
        this.titolare = titolare;
    }

    public Collection<TransazioneFinanziaria> getTransazioneFinanziariaCollection() {
        return transazioneFinanziariaCollection;
    }

    public void setTransazioneFinanziariaCollection(Collection<TransazioneFinanziaria> transazioneFinanziariaCollection) {
        this.transazioneFinanziariaCollection = transazioneFinanziariaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iban != null ? iban.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conto)) {
            return false;
        }
        Conto other = (Conto) object;
        return (this.iban != null || other.iban == null) && (this.iban == null || this.iban.equals(other.iban));
    }

    @Override
    public String toString() {
        return "data.Conto[ iban=" + iban + " ]";
    }
    
}

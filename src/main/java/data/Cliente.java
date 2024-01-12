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
import java.util.Collection;

/**
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Cliente")
@NamedQueries({
        @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
        @NamedQuery(name = "Cliente.findByCf", query = "SELECT c FROM Cliente c WHERE c.cf = :cf"),
        @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
        @NamedQuery(name = "Cliente.findByCognome", query = "SELECT c FROM Cliente c WHERE c.cognome = :cognome"),
        @NamedQuery(name = "Cliente.findByMail", query = "SELECT c FROM Cliente c WHERE c.mail = :mail"),
        @NamedQuery(name = "Cliente.findByNumerotelefono", query = "SELECT c FROM Cliente c WHERE c.numerotelefono = :numerotelefono")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CF")
    private String cf;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "mail")
    private String mail;
    @Column(name = "numerotelefono")
    private String numerotelefono;
    @OneToMany(mappedBy = "cFCliente")
    private Collection<RichiestaCliente> richiestaClienteCollection;

    public Cliente() {
    }

    public Cliente(String cf) {
        this.cf = cf;
    }

    public Cliente(String cf, String nome, String cognome, String mail, String numerotelefono) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.numerotelefono = numerotelefono;
        this.mail = mail;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumerotelefono() {
        return numerotelefono;
    }

    public void setNumerotelefono(String numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public Collection<RichiestaCliente> getRichiestaClienteCollection() {
        return richiestaClienteCollection;
    }

    public void setRichiestaClienteCollection(Collection<RichiestaCliente> richiestaClienteCollection) {
        this.richiestaClienteCollection = richiestaClienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cf != null ? cf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        return (this.cf != null || other.cf == null) && (this.cf == null || this.cf.equals(other.cf));
    }

    @Override
    public String toString() {
        return "data.Cliente[ cf=" + cf + " ]";
    }

}

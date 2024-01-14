package logic.business;

import data.*;
import logic.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author umbertodomenicociccia
 */
public interface GestioneAcquisti {
    SessionFactory sessionFactory = HibernateFactory.ISTANCE.getSessionFactory();

    default void addFornitore(@NotNull String piva, @NotNull String nome, @NotNull String citta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Fornitore(piva, nome, citta));
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void removeFornitore(@NotNull String piva) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Fornitore fornitore = session.get(Fornitore.class, piva);
            session.delete(fornitore);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default Fornitore getFornitore(@NotNull String piva) {
        Fornitore fornitore = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            fornitore = session.get(Fornitore.class, piva);
            session.getTransaction().commit();
            return fornitore;
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return fornitore;
        }
    }

    default void updateFornitore(@NotNull String piva, String nome, String citta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Fornitore fornitore = session.get(Fornitore.class, piva);
            if (nome != null)
                fornitore.setNome(nome);
            if (citta != null)
                fornitore.setCitta(citta);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void addProdotto(int codice, @NotNull String tipo, int quantitaNecessaria) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Prodotto(codice, tipo, quantitaNecessaria));
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void removeProdotto(int codice) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Prodotto prodotto = session.get(Prodotto.class, codice);
            session.delete(prodotto);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default Prodotto getProdotto(int codice) {
        Prodotto prodotto = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            prodotto = session.get(Prodotto.class, codice);
            session.getTransaction().commit();
            return prodotto;
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return prodotto;
        }
    }

    default void updateProdotto(int codice, String tipo, Integer quantita) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Prodotto prodotto = session.get(Prodotto.class, codice);
            if (tipo != null)
                prodotto.setTipo(tipo);
            if (quantita != null)
                prodotto.setQuantitaNecessaria(quantita);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void addAutomezzo(@NotNull String targa, @NotNull String marca, @NotNull String assicurazione, @NotNull BigDecimal prezzo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Automezzo(targa, marca, assicurazione, prezzo));
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void removeAutomezzo(@NotNull String targa) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Automezzo automezzo = session.get(Automezzo.class, targa);
            session.delete(automezzo);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default Automezzo getAutomezzo(@NotNull String targa) {
        Automezzo automezzo = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            automezzo = session.get(Automezzo.class, targa);
            session.getTransaction().commit();
            return automezzo;
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return automezzo;
        }
    }

    default void updateAutomezzo(@NotNull String targa, String marca, String assicurazione, BigDecimal prezzo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Automezzo automezzo = session.get(Automezzo.class, targa);
            if (marca != null)
                automezzo.setMarca(marca);
            if (assicurazione != null)
                automezzo.setAssicurazione(assicurazione);
            if (prezzo != null)
                automezzo.setPrezzo(prezzo);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void addPreventivo(@NotNull String piva, int prodotto, @NotNull String automezzo, @NotNull BigDecimal prezzo, @NotNull Date scadenza, @NotNull Date scrittura) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Preventivo(new PreventivoPK(piva, prodotto, automezzo), prezzo, scadenza, scrittura));
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void removePreventivo(@NotNull String piva, int prodotto, @NotNull String automezzo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Preventivo preventivo = session.get(Preventivo.class, new PreventivoPK(piva, prodotto, automezzo));
            session.delete(preventivo);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void updatePreventivo(@NotNull String piva, int prodotto, @NotNull String automezzo, BigDecimal prezzo, Date scadenza, Date scrittura) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Preventivo preventivo = session.get(Preventivo.class, new PreventivoPK(piva, prodotto, automezzo));
            if (prezzo != null)
                preventivo.setPrezzo(prezzo);
            if (scadenza != null)
                preventivo.setDataScadenza(scadenza);
            if (scrittura != null)
                preventivo.setDataScrittura(scrittura);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default Preventivo getPreventivo(@NotNull String piva, int prodotto, @NotNull String automezzo) {
        Preventivo preventivo = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            preventivo = session.get(Preventivo.class, new PreventivoPK(piva, prodotto, automezzo));
            session.getTransaction().commit();
            return preventivo;
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return preventivo;
        }
    }

    default void addOrdine(int codice, @NotNull String stato, @NotNull Date data, int quantita, int prodotto, String automezzo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Ordine ordine = null;
            if (automezzo == null)
                ordine = new Ordine(codice, stato, data, quantita, null, session.get(Prodotto.class, prodotto));
            else if (prodotto < 0) {
                ordine = new Ordine(codice, stato, data, quantita, session.get(Automezzo.class, automezzo), null);
            } else
                ordine = new Ordine(codice, stato, data, quantita, session.get(Automezzo.class, automezzo), session.get(Prodotto.class, prodotto));
            session.persist(ordine);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default Ordine getOrdine(int codice) {
        Ordine ordine = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ordine = session.get(Ordine.class, codice);
            session.getTransaction().commit();
            return ordine;
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return ordine;
        }
    }

    default void removeOrdine(int codice) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(session.get(Ordine.class, codice));
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void updateOrdine(int codice, String stato, Date data, int quantita) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Ordine ordine = session.get(Ordine.class, codice);
            if (stato != null)
                ordine.setStato(stato);
            if (data != null)
                ordine.setData(data);
            if (quantita > 0)
                ordine.setQuantita(quantita);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

}

package logic.business;

import data.*;
import logic.HibernateSingleton;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ui.UIUtil.*;

/**
 * @author umbertodomenicociccia
 */
@SuppressWarnings("CallToPrintStackTrace")
public interface GestioneAcquisti {
    SessionFactory sessionFactory = HibernateSingleton.ISTANCE.getSessionFactory();

    default void addFornitore(@NotNull String piva, @NotNull String nome, @NotNull String citta) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(new Fornitore(piva, nome, citta));
            session.getTransaction().commit();
        } catch (RuntimeException exception) {
            messaggioErroreInserimento("fornitore");
        }
    }

    default void removeFornitore(@NotNull String piva) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Fornitore fornitore = session.get(Fornitore.class, piva);
            session.remove(fornitore);
            session.getTransaction().commit();
        } catch (RuntimeException exception) {
            messaggioErroreCancellazione("fornitore");
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

    default List<Fornitore> getFornitori() {
        List<Fornitore> fornitori = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Fornitore> query = session.createNamedQuery("Fornitore.findAll", Fornitore.class);
            fornitori.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return fornitori;
    }

    default void addProdotto(@NotNull String tipo, int quantitaNecessaria) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Prodotto(tipo, quantitaNecessaria));
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    default void removeProdotto(int codice) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Prodotto prodotto = session.get(Prodotto.class, codice);
            session.remove(prodotto);
            session.getTransaction().commit();
            session.beginTransaction();
            Query<Prodotto> query = session.createNativeQuery("select count(*) from Prodotto", Prodotto.class);
            int numeriProdotti = query.getFirstResult();
            System.out.println(numeriProdotti);
            if (numeriProdotti == 0) {
                Query<Prodotto> reset = session.createNativeQuery("ALTER TABLE Prodotto auto_increment = 1", Prodotto.class);
                reset.executeUpdate();
            }
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
            messaggioErroreCancellazione("Prodotto");
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

    default List<Prodotto> getProdotti() {
        List<Prodotto> res = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Prodotto> query = session.createNamedQuery("Prodotto.findAll", Prodotto.class);
            res.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return res;
    }

    default void addAutomezzo(@NotNull String targa, @NotNull String marca, @NotNull String assicurazione, @NotNull BigDecimal prezzo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Automezzo(targa, marca, assicurazione, prezzo));
            session.getTransaction().commit();
        } catch (RuntimeException exception) {
            messaggioErroreInserimento("automezzo");
        }
    }

    default void removeAutomezzo(@NotNull String targa) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Automezzo automezzo = session.get(Automezzo.class, targa);
            session.remove(automezzo);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
            messaggioErroreCancellazione("automezzo");
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
            messaggioErroreAggiornamento("automezzo");
        }
    }

    default List<Automezzo> getAutomezzi() {
        List<Automezzo> res = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Automezzo> query = session.createNamedQuery("Automezzo.findAll", Automezzo.class);
            res.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return res;
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
            session.remove(preventivo);
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
            Ordine ordine;
            if (automezzo == null)
                ordine = new Ordine(codice, stato, data, quantita, null, session.get(Prodotto.class, prodotto));
            else if (prodotto < 0) {
                ordine = new Ordine(codice, stato, data, quantita, session.get(Automezzo.class, automezzo), null);
            } else
                ordine = new Ordine(codice, stato, data, quantita, session.get(Automezzo.class, automezzo), session.get(Prodotto.class, prodotto));
            session.persist(ordine);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            messaggioErroreInserimento("ordine");
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
            session.remove(session.get(Ordine.class, codice));
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

    default List<Ordine> getOrdine() {
        List<Ordine> res = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Ordine> query = session.createNamedQuery("Ordine.findAll", Ordine.class);
            res.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return res;
    }


    default List<Ordine> getOrdineByNumero(int numero) {
        List<Ordine> res = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Ordine> query = session.createNamedQuery("Ordine.findByNumero", Ordine.class);
            query.setParameter("numero", numero);
            res.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return res;
    }

    default List<Ordine> getOrdineByStato(String stato) {
        List<Ordine> res = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Ordine> query = session.createNamedQuery("Ordine.findByStato", Ordine.class);
            query.setParameter("stato", stato);
            res.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return res;
    }

    default List<Ordine> getOrdineByData(Date data) {
        List<Ordine> res = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Ordine> query = session.createNamedQuery("Ordine.findByData", Ordine.class);
            query.setParameter("data", data);
            res.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return res;
    }

    default List<Ordine> getOrdineByQuantita(int quantita) {
        List<Ordine> res = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Ordine> query = session.createNamedQuery("Ordine.findByQuantita", Ordine.class);
            query.setParameter("quantita", quantita);
            res.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return res;
    }

    default List<Fornitore> fornitoriDiUnaCitta(@NotNull String citta) {
        List<Fornitore> fornitori = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Fornitore> query = session.createNamedQuery("Fornitore.findByCitta", Fornitore.class);
            query.setParameter("citta", citta);
            fornitori.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return fornitori;
    }

    default List<Fornitore> fornitoriDiUnaPartitaIva(@NotNull String piva) {
        List<Fornitore> fornitori = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Fornitore> query = session.createNamedQuery("Fornitore.findByPiva", Fornitore.class);
            query.setParameter("piva", piva);
            fornitori.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return fornitori;
    }

    default List<Fornitore> fornitoriDiUnNome(@NotNull String nome) {
        List<Fornitore> fornitori = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Fornitore> query = session.createNamedQuery("Fornitore.findByNome", Fornitore.class);
            query.setParameter("nome", nome);
            fornitori.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return fornitori;
    }

    default List<Automezzo> automezziDiUnaTarga(@NotNull String targa) {
        List<Automezzo> automezzo = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Automezzo> query = session.createNamedQuery("Automezzo.findByTarga", Automezzo.class);
            query.setParameter("targa", targa);
            automezzo.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return automezzo;
    }

    default List<Automezzo> automezziDiUnaMarca(@NotNull String marca) {
        List<Automezzo> automezzo = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Automezzo> query = session.createNamedQuery("Automezzo.findByMarca", Automezzo.class);
            query.setParameter("marca", marca);
            automezzo.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return automezzo;
    }

    default List<Automezzo> automezziDiUnaAssicurazione(@NotNull String assicurazione) {
        List<Automezzo> automezzo = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Automezzo> query = session.createNamedQuery("Automezzo.findByAssicurazione", Automezzo.class);
            query.setParameter("assicurazione", assicurazione);
            automezzo.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return automezzo;
    }

    default List<Automezzo> automezziDiUnPrezzo(@NotNull BigDecimal prezzo) {
        List<Automezzo> automezzo = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Automezzo> query = session.createNamedQuery("Automezzo.findByPrezzo", Automezzo.class);
            query.setParameter("prezzo", prezzo);
            automezzo.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return automezzo;
    }

    default List<Prodotto> prodottiDiUnCodice(int codice) {
        List<Prodotto> prodotti = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Prodotto> query = session.createNamedQuery("Prodotto.findByCodProdotto", Prodotto.class);
            query.setParameter("codProdotto", codice);
            prodotti.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return prodotti;
    }

    default List<Prodotto> prodottiDiUnTipo(@NotNull String tipo) {
        List<Prodotto> prodotti = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Prodotto> query = session.createNamedQuery("Prodotto.findByTipo", Prodotto.class);
            query.setParameter("tipo", tipo);
            prodotti.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return prodotti;
    }

    default List<Prodotto> prodottiDiUnaNecessita(int quantitaNecessaria) {
        List<Prodotto> prodotti = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Prodotto> query = session.createNamedQuery("Prodotto.findByQuantitaNecessaria", Prodotto.class);
            query.setParameter("quantitaNecessaria", quantitaNecessaria);
            prodotti.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return prodotti;
    }

    default List<Prodotto> prodottoNecessitaMAggiore() {
        List<Prodotto> prodotti = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Prodotto> query = session.createQuery(
                    "FROM Prodotto p WHERE p.quantitaNecessaria = (SELECT max(p2.quantitaNecessaria) FROM Prodotto p2)",
                    Prodotto.class
            );
            prodotti.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return prodotti;
    }

    /*
     * Filtra prodotto necessita maggiore
     * */


}

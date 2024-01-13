package logic.business;

import data.Fornitore;
import data.Prodotto;
import logic.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;

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

}

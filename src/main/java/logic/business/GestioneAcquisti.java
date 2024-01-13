package logic.business;

import data.Fornitore;
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

}

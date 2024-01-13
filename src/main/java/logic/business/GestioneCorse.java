package logic.business;

import data.*;
import logic.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author umbertodomenicociccia
 */
public interface GestioneCorse {

    SessionFactory sessionFactory = HibernateFactory.ISTANCE.getSessionFactory();

    default void addCorsa(int idCorsa, @NotNull Date dataCorsa, @NotNull String targaAutobus) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Corsa(new CorsaPK(idCorsa, targaAutobus), dataCorsa));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void removeCorsa(int idCorsa, @NotNull String targaAutobus) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Corsa corsa = session.get(Corsa.class, new CorsaPK(idCorsa, targaAutobus));
            session.delete(corsa);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void updateCorsa(int idCorsa, @NotNull String targaAutobus, @NotNull Date nuovaData) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Corsa corsa = session.get(Corsa.class, new CorsaPK(idCorsa, targaAutobus));
            corsa.setDataCorsa(nuovaData);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default Corsa getCorsa(int idCorsa, @NotNull String targaAutobus) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Corsa> query = session.createQuery("FROM Corsa c WHERE  c.id = :id", Corsa.class);
            CorsaPK pk = new CorsaPK(idCorsa, targaAutobus);
            query.setParameter("id", pk);
            Corsa corsa = query.getSingleResult();
            session.getTransaction().commit();
            return corsa;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default void addFermata(@NotNull String via, @NotNull String citta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Fermata(via, citta));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void removeFermata(@NotNull String via, @NotNull String citta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(session.get(Fermata.class, new FermataPK(via, citta)));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default Fermata getFermata(@NotNull String via, @NotNull String citta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Fermata fermata = session.get(Fermata.class, new FermataPK(via, citta));
            session.getTransaction().commit();
            return fermata;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default void addAutobus(@NotNull String targa) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Autobus autobus = new Autobus(targa);
            session.persist(autobus);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void removeAutobus(@NotNull String targa) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Autobus autobus = session.get(Autobus.class, targa);
            session.delete(autobus);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default Autobus getAutobus(@NotNull String targa) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Autobus autobus = session.get(Autobus.class, targa);
            session.getTransaction().commit();
            return autobus;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default void addListaFermate(int idCorsa,
                                 @NotNull String targaAutobus,
                                 @NotNull String viaFermata,
                                 @NotNull String cittaFermata,
                                 @NotNull Time orarioArrivo,
                                 @NotNull String localitaPartenza,
                                 @NotNull String localitaArrivo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ListaFermate fermate = new ListaFermate(idCorsa, targaAutobus, viaFermata, cittaFermata, localitaPartenza, localitaArrivo, orarioArrivo);
            session.persist(fermate);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void removeListaFermate(int idCorsa,
                                    @NotNull String targaAutobus,
                                    @NotNull String viaFermata,
                                    @NotNull String cittaFermata) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ListaFermate fermate = session.get(ListaFermate.class, new ListaFermatePK(idCorsa, targaAutobus, viaFermata, cittaFermata));
            session.delete(fermate);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default ListaFermate getListaFermate(int idCorsa,
                                         @NotNull String targaAutobus,
                                         @NotNull String viaFermata,
                                         @NotNull String cittaFermata) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ListaFermate fermate = session.get(ListaFermate.class, new ListaFermatePK(idCorsa, targaAutobus, viaFermata, cittaFermata));
            session.getTransaction().commit();
            return fermate;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default void updateListaFermate(int idCorsa,
                                    @NotNull String targaAutobus,
                                    @NotNull String viaFermata,
                                    @NotNull String cittaFermata,
                                    Time orarioArrivo,
                                    String localitaPartenza,
                                    String localitaArrivo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ListaFermate fermate = session.get(ListaFermate.class, new ListaFermatePK(idCorsa, targaAutobus, viaFermata, cittaFermata));
            if (orarioArrivo != null)
                fermate.setOrarioArrivo(orarioArrivo);
            if (localitaPartenza != null)
                fermate.setLocalitaPartenza(localitaPartenza);
            if (localitaArrivo != null)
                fermate.setLocalitaArrivo(localitaArrivo);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default List<ListaFermate> corseArrivonoOrario(Time ora) {
        List<ListaFermate> corse = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<ListaFermate> query = session.createQuery("SELECT f.corsa FROM ListaFermate f WHERE  f.orarioArrivo=:ora", ListaFermate.class);
            query.setParameter("ora", ora);
            corse.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return corse;
    }

    default List<ListaFermate> corsePartonoStazione(@NotNull String stazione) {
        List<ListaFermate> corse = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<ListaFermate> query = session.createQuery("SELECT f.corsa FROM ListaFermate f where f.localitaPartenza=:stazione", ListaFermate.class);
            query.setParameter("stazione", stazione);
            corse.addAll(query.getResultList());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return corse;
    }

}

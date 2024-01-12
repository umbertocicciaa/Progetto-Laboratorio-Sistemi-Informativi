package logic.business;

import data.Cliente;
import data.RichiestaCliente;
import logic.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

/**
 * @author umbertodomenicociccia
 */
public interface GestioneReclamo {

    SessionFactory sessionFactory = HibernateFactory.ISTANCE.getSessionFactory();

    default void createCliente(String @NotNull ... values) {
        if (values.length < 5) {
            throw new RuntimeException("Non hai inserito tutti i campi");
        }
        Cliente cliente = new Cliente(values[0], values[1], values[2], values[3], values[4]);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(cliente);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void updateCliente(@NotNull String cf, String newMail, String newNumero) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, cf);
            if (cliente != null) {
                if (newMail != null && !newMail.isEmpty())
                    cliente.setMail(newMail);
                if (newNumero != null && !newNumero.isEmpty()) {
                    cliente.setNumerotelefono(newNumero);
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void deleteCliente(@NotNull String cf) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, cf);
            session.delete(cliente);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default Cliente readCliente(@NotNull String cf) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Cliente> query = session.createQuery("FROM Cliente WHERE cf = :cf", Cliente.class);
            query.setParameter("cf", cf);
            Cliente cliente = query.uniqueResult();
            session.getTransaction().commit();
            return cliente;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default void createRichiestaCliente(@NotNull Integer iDRichiesta,
                                        @NotNull String contenuto,
                                        @NotNull Date data,
                                        @NotNull String stato,
                                        @NotNull String tipo,
                                        @NotNull String cFCliente) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Cliente cliente = readCliente(cFCliente);
            RichiestaCliente richiestaCliente = new RichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, cliente);
            session.persist(richiestaCliente);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void updateRichiestaCliente(@NotNull Integer iDRichiesta, @NotNull String stato) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            RichiestaCliente richiestaCliente = session.get(RichiestaCliente.class, iDRichiesta);
            if (richiestaCliente != null) {
                richiestaCliente.setStato(stato);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void deleteRichiestaCliente(@NotNull Integer iDRichiesta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            RichiestaCliente richiestaCliente = session.get(RichiestaCliente.class, iDRichiesta);
            session.delete(richiestaCliente);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default RichiestaCliente readRichiestaCliente(@NotNull Integer iDRichiesta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<RichiestaCliente> query = session.createQuery("FROM RichiestaCliente WHERE iDRichiesta = :iDRichiesta", RichiestaCliente.class);
            query.setParameter("iDRichiesta", iDRichiesta);
            RichiestaCliente richiestaCliente = query.uniqueResult();
            session.getTransaction().commit();
            return richiestaCliente;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<RichiestaCliente> reclamiRisolti() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<RichiestaCliente> query = session.createNativeQuery(
                    " SELECT * FROM RichiestaCliente r WHERE r.stato = 'chiuso' AND r.tipo = 'reclamo'", RichiestaCliente.class
            );
            List<RichiestaCliente> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<RichiestaCliente> reclamiNonRisolti() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<RichiestaCliente> query = session.createNativeQuery(
                    " SELECT * FROM RichiestaCliente r WHERE r.stato != 'chiuso' AND r.tipo = 'reclamo'", RichiestaCliente.class
            );
            List<RichiestaCliente> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<Cliente> clientiReclamiRisolti() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Cliente> query = session.createNativeQuery(
                    "SELECT c.CF, c.nome, c.cognome, c.mail, c.numerotelefono FROM RichiestaCliente r, Cliente c WHERE r.stato = 'chiuso' AND r.tipo = 'reclamo' AND r.CFCliente = c.CF"
                    , Cliente.class
            );
            List<Cliente> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<Cliente> clientiReclamiNonRisolti() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Cliente> query = session.createNativeQuery(
                    "SELECT c.CF, c.nome, c.cognome, c.mail, c.numerotelefono FROM RichiestaCliente r, Cliente c WHERE r.stato != 'chiuso' AND r.tipo = 'reclamo' AND r.CFCliente = c.CF"
                    , Cliente.class
            );
            List<Cliente> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<Cliente> clientiReclamiLavorazione() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Cliente> query = session.createNativeQuery(
                    "SELECT c.CF, c.nome, c.cognome, c.mail, c.numerotelefono FROM RichiestaCliente r, Cliente c WHERE r.stato = 'lavorazione' AND r.tipo = 'reclamo' AND r.CFCliente = c.CF"
                    , Cliente.class
            );
            List<Cliente> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<String> cognomeClientiReclamo() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<String> query = session.createNativeQuery(
                    "SELECT c.cognome FROM RichiestaCliente r, Cliente c WHERE  r.tipo = 'reclamo' and c.CF=r.CFCliente"
                    , String.class
            );
            List<String> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<String> nomeClientiReclamo() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<String> query = session.createNativeQuery(
                    "SELECT c.nome FROM RichiestaCliente r, Cliente c WHERE  r.tipo = 'reclamo' AND r.CFCliente = c.CF"
                    , String.class
            );
            List<String> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<String> cfClientiReclamo() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<String> query = session.createNativeQuery(
                    "SELECT c.CF FROM RichiestaCliente r, Cliente c WHERE  r.tipo = 'reclamo' AND r.CFCliente = c.CF"
                    , String.class
            );
            List<String> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<String> mailClientiReclamo() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<String> query = session.createNativeQuery(
                    "SELECT c.mail FROM RichiestaCliente r, Cliente c WHERE  r.tipo = 'reclamo' AND r.CFCliente = c.CF"
                    , String.class
            );
            List<String> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default List<Cliente> clienteEffettuatoReclamo() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Cliente> query = session.createNativeQuery(
                    "SELECT c.CF, c.nome, c.cognome, c.mail, c.numerotelefono FROM RichiestaCliente r, Cliente c  WHERE  c.CF=r.CFCliente and  r.tipo = 'reclamo' ORDER BY r.IDRichiesta"
                    , Cliente.class
            );
            List<Cliente> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    default String statoReclamoCliente(@NotNull Cliente cliente) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<String> query = session.createNativeQuery(
                    "SELECT  r.Stato  FROM RichiestaCliente r, Cliente c WHERE c.CF = :cf and r.CFCliente=c.CF and r.Tipo='reclamo' ORDER BY r.IDRichiesta"
                    , String.class
            );
            query.setParameter("cf", cliente.getCf());
            String result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    default float percentualeReclamiRisolti(){

    }

    default float percentualeReclamiNonRisolti(){

    }*/
}



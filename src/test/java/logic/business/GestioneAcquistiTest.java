package logic.business;

import data.Fornitore;
import data.Prodotto;
import logic.HibernateFactory;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author umbertodomenicociccia
 */
public class GestioneAcquistiTest {
    private static SessionFactory sessionFactory;
    private static GestioneAcquisti gestioneAcquisti;

    @BeforeAll
    public static void setUp() {
        sessionFactory = HibernateFactory.ISTANCE.getSessionFactory();
        gestioneAcquisti = new GestioneAcquisti() {
        };
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null && !sessionFactory.isClosed())
            sessionFactory.close();
    }

    @Test
    public void testAddFornitore() {
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";

        gestioneAcquisti.addFornitore(piva, nome, citta);
        Fornitore fornitore = gestioneAcquisti.getFornitore(piva);
        Assertions.assertEquals(fornitore, new Fornitore(piva, nome, citta));
        gestioneAcquisti.removeFornitore(piva);
    }

    @Test
    public void testRemoveFornitore() {
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";

        gestioneAcquisti.addFornitore(piva, nome, citta);
        gestioneAcquisti.removeFornitore(piva);
        Assertions.assertNull(gestioneAcquisti.getFornitore(piva));

    }

    @Test
    public void testGetFornitore() {
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";

        gestioneAcquisti.addFornitore(piva, nome, citta);
        Fornitore fornitore = gestioneAcquisti.getFornitore(piva);
        Assertions.assertEquals(fornitore, new Fornitore(piva, nome, citta));
        gestioneAcquisti.removeFornitore(piva);
    }

    @Test
    public void testUpdateFornitore() {
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";
        String nuovoNome = "pippo";
        gestioneAcquisti.addFornitore(piva, nome, citta);
        gestioneAcquisti.updateFornitore(piva, nuovoNome, null);
        Fornitore fornitore = gestioneAcquisti.getFornitore(piva);
        Assertions.assertEquals(fornitore.getNome(), nuovoNome);
        gestioneAcquisti.removeFornitore(piva);
    }

    @Test
    public void testAddProdotto() {
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;

        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        Prodotto prodotto = gestioneAcquisti.getProdotto(codice);
        Assertions.assertEquals(prodotto.getCodProdotto(), codice);
        gestioneAcquisti.removeProdotto(codice);
    }

    @Test
    public void testRemoveProdotto() {
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;

        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        gestioneAcquisti.removeProdotto(codice);
        Assertions.assertNull(gestioneAcquisti.getProdotto(codice));
    }

    @Test
    public void testGetProdotto() {
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;

        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        Prodotto prodotto = gestioneAcquisti.getProdotto(codice);
        Assertions.assertEquals(prodotto.getCodProdotto(), codice);
        gestioneAcquisti.removeProdotto(codice);
    }

    @Test
    public void testUpdateProdotto() {
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;
        String tipoNuovo = "ciao";
        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        gestioneAcquisti.updateProdotto(codice, tipoNuovo, null);
        Prodotto prodotto = gestioneAcquisti.getProdotto(codice);
        Assertions.assertEquals(prodotto.getTipo(), tipoNuovo);
        gestioneAcquisti.removeProdotto(codice);
    }

}

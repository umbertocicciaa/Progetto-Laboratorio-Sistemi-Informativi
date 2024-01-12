import data.*;
import jakarta.persistence.NoResultException;
import logic.HibernateFactory;
import logic.business.GestioneCorse;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

/**
 * @author umbertodomenicociccia
 */
public class GestioneCorseTest {

    private static SessionFactory sessionFactory;
    private static GestioneCorse gestioneCorse;

    @BeforeAll
    public static void setUp() {
        sessionFactory = HibernateFactory.ISTANCE.getSessionFactory();
        gestioneCorse = new GestioneCorse() {
        };
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null && !sessionFactory.isClosed())
            sessionFactory.close();
    }

    @Test
    public void testAddCOrsa() {
        int idCorsa = 1;
        Date data = new Date();
        String targa = "AAA2024";
        gestioneCorse.addAutobus(targa);
        gestioneCorse.addCorsa(idCorsa, data, targa);
        Corsa corsa = gestioneCorse.getCorsa(idCorsa, targa);
        Assertions.assertEquals(corsa.getCorsaPK(), new CorsaPK(idCorsa, targa));
        gestioneCorse.removeCorsa(idCorsa, targa);
        gestioneCorse.removeAutobus(targa);
    }

    @Test
    public void testRemoveCorsa() {
        int idCorsa = 1;
        Date data = new Date();
        String targa = "AAA2024";
        gestioneCorse.addAutobus(targa);
        gestioneCorse.addCorsa(idCorsa, data, targa);

        gestioneCorse.removeCorsa(idCorsa, targa);
        gestioneCorse.removeAutobus(targa);

        Assertions.assertThrowsExactly(NoResultException.class, () -> gestioneCorse.getCorsa(idCorsa, targa));
    }

    @Test
    public void testGetCOrsa() {
        int idCorsa = 1;
        Date data = new Date();
        String targa = "AAA2024";
        gestioneCorse.addAutobus(targa);
        gestioneCorse.addCorsa(idCorsa, data, targa);

        Corsa corsa = gestioneCorse.getCorsa(idCorsa, targa);
        Assertions.assertEquals(corsa.getCorsaPK(), new CorsaPK(idCorsa, targa));

        gestioneCorse.removeCorsa(idCorsa, targa);
        gestioneCorse.removeAutobus(targa);
    }

    @Test
    public void testUpdateCOrsa() {
        int idCorsa = 1;
        Date data = new Date();
        String targa = "AAA2024";
        Date nuova = new Date(2002, 7, 7);

        gestioneCorse.addAutobus(targa);
        gestioneCorse.addCorsa(idCorsa, data, targa);

        gestioneCorse.updateCorsa(idCorsa, targa, nuova);
        Corsa corsa = gestioneCorse.getCorsa(idCorsa, targa);
        Assertions.assertEquals(corsa.getDataCorsa(), nuova);

        gestioneCorse.removeCorsa(idCorsa, targa);
        gestioneCorse.removeAutobus(targa);

    }

    @Test
    public void testAddFermata() {
        String via = "Via umbo";
        String citta = "locri";
        gestioneCorse.addFermata(via, citta);
        Fermata fermata = gestioneCorse.getFermata(via, citta);
        Assertions.assertEquals(fermata.getFermataPK().getVia(), via);
        Assertions.assertEquals(fermata.getFermataPK().getCitta(), citta);
        gestioneCorse.removeFermata(via, citta);
    }

    @Test
    public void testRemoveFermata() {
        String via = "Via umbo";
        String citta = "locri";
        gestioneCorse.addFermata(via, citta);
        gestioneCorse.removeFermata(via, citta);
        Assertions.assertNull(gestioneCorse.getFermata(via, citta));

    }

    @Test
    public void testGetFermata() {
        String via = "Via umbo";
        String citta = "locri";
        gestioneCorse.addFermata(via, citta);
        Fermata fermata = gestioneCorse.getFermata(via, citta);
        Assertions.assertEquals(fermata.getFermataPK().getVia(), via);
        Assertions.assertEquals(fermata.getFermataPK().getCitta(), citta);
        gestioneCorse.removeFermata(via, citta);
    }

    @Test
    public void testAddAutobus() {
        String targa = "AA2024";
        gestioneCorse.addAutobus(targa);
        Autobus autobus = gestioneCorse.getAutobus(targa);
        Assertions.assertEquals(autobus.getTarga(), targa);
        gestioneCorse.removeAutobus(targa);
    }

    @Test
    public void testRemoveAutobus() {
        String targa = "AA2024";
        gestioneCorse.addAutobus(targa);
        gestioneCorse.removeAutobus(targa);
        Assertions.assertNull(gestioneCorse.getAutobus(targa));

    }

    @Test
    public void testGetAutobus() {
        String targa = "AA2024";
        gestioneCorse.addAutobus(targa);
        Autobus autobus = gestioneCorse.getAutobus(targa);
        Assertions.assertEquals(autobus.getTarga(), targa);
        gestioneCorse.removeAutobus(targa);
    }

    @Test
    public void testAddListaFermate() {
        int idCorsa = 1;
        Date data = new Date();
        String via = "SalvatoreFurfaro", citta = "locri", autobus = "AA2024", localitaPartenza = "locri", localitaArrivo = "rende";
        Time orarioArrivo = new Time(15, 10, 0);

        gestioneCorse.addAutobus(autobus);
        gestioneCorse.addCorsa(idCorsa, data, autobus);
        gestioneCorse.addFermata(via, citta);
        gestioneCorse.addListaFermate(idCorsa, autobus, via, citta, orarioArrivo, localitaPartenza, localitaArrivo);

        ListaFermate fermata = gestioneCorse.getListaFermate(idCorsa, autobus, via, citta);
        Assertions.assertEquals(fermata.getListaFermatePK(), new ListaFermatePK(idCorsa, autobus, via, citta));

        gestioneCorse.removeListaFermate(idCorsa, autobus, via, citta);
        gestioneCorse.removeFermata(via, citta);
        gestioneCorse.removeCorsa(idCorsa, autobus);
        gestioneCorse.removeAutobus(autobus);
    }

    @Test
    public void testRemoveListaFermate() {
        int idCorsa = 1;
        Date data = new Date();
        String via = "SalvatoreFurfaro", citta = "locri", autobus = "AA2024", localitaPartenza = "locri", localitaArrivo = "rende";
        Time orarioArrivo = new Time(15, 10, 0);

        gestioneCorse.addAutobus(autobus);
        gestioneCorse.addCorsa(idCorsa, data, autobus);
        gestioneCorse.addFermata(via, citta);
        gestioneCorse.addListaFermate(idCorsa, autobus, via, citta, orarioArrivo, localitaPartenza, localitaArrivo);


        gestioneCorse.removeListaFermate(idCorsa, autobus, via, citta);
        Assertions.assertNull(gestioneCorse.getListaFermate(idCorsa,autobus,via,citta));
        gestioneCorse.removeFermata(via, citta);
        gestioneCorse.removeCorsa(idCorsa, autobus);
        gestioneCorse.removeAutobus(autobus);

    }

    @Test
    public void testUpdateListaFermate() {
        int idCorsa = 1;
        Date data = new Date();
        String via = "SalvatoreFurfaro", citta = "locri", autobus = "AA2024", localitaPartenza = "locri", localitaArrivo = "rende";
        Time orarioArrivo = new Time(15, 10, 0);

        gestioneCorse.addAutobus(autobus);
        gestioneCorse.addCorsa(idCorsa, data, autobus);
        gestioneCorse.addFermata(via, citta);
        gestioneCorse.addListaFermate(idCorsa, autobus, via, citta, orarioArrivo, localitaPartenza, localitaArrivo);

        Time orarioNuovo=new Time(15,15,16);
        String nuovoArrivo="catanzaro";
        gestioneCorse.updateListaFermate(idCorsa,autobus,via,citta,orarioNuovo,null,nuovoArrivo);

        ListaFermate fermata = gestioneCorse.getListaFermate(idCorsa, autobus, via, citta);
        Assertions.assertEquals(fermata.getListaFermatePK(), new ListaFermatePK(idCorsa, autobus, via, citta));
        Assertions.assertEquals(fermata.getLocalitaArrivo(),nuovoArrivo);
        Assertions.assertEquals(fermata.getOrarioArrivo(),orarioNuovo);

        gestioneCorse.removeListaFermate(idCorsa, autobus, via, citta);
        gestioneCorse.removeFermata(via, citta);
        gestioneCorse.removeCorsa(idCorsa, autobus);
        gestioneCorse.removeAutobus(autobus);
    }

    @Test
    public void testGetListaFermate() {
        int idCorsa = 1;
        Date data = new Date();
        String via = "SalvatoreFurfaro", citta = "locri", autobus = "AA2024", localitaPartenza = "locri", localitaArrivo = "rende";
        Time orarioArrivo = new Time(15, 10, 0);

        gestioneCorse.addAutobus(autobus);
        gestioneCorse.addCorsa(idCorsa, data, autobus);
        gestioneCorse.addFermata(via, citta);
        gestioneCorse.addListaFermate(idCorsa, autobus, via, citta, orarioArrivo, localitaPartenza, localitaArrivo);

        ListaFermate fermata = gestioneCorse.getListaFermate(idCorsa, autobus, via, citta);
        Assertions.assertEquals(fermata.getListaFermatePK(), new ListaFermatePK(idCorsa, autobus, via, citta));

        gestioneCorse.removeListaFermate(idCorsa, autobus, via, citta);
        gestioneCorse.removeFermata(via, citta);
        gestioneCorse.removeCorsa(idCorsa, autobus);
        gestioneCorse.removeAutobus(autobus);
    }

}

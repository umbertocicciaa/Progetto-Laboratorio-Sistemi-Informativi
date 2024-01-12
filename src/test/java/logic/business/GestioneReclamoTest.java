import data.Cliente;
import data.RichiestaCliente;
import logic.HibernateFactory;
import logic.business.GestioneReclamo;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author umbertodomenicociccia
 */
public class GestioneReclamoTest {

    private static SessionFactory sessionFactory;
    private static GestioneReclamo gestioneReclamo;

    @BeforeAll
    public static void setUp() {
        sessionFactory = HibernateFactory.ISTANCE.getSessionFactory();
        gestioneReclamo = new GestioneReclamo() {
        };
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    @Test
    public void testCreateCliente() {
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        Cliente createdCliente = gestioneReclamo.readCliente("CF123");
        assertEquals("CF123", createdCliente.getCf());
        assertEquals("test@mail.com", createdCliente.getMail());
        gestioneReclamo.deleteCliente("CF123");
    }

    @Test
    public void testUpdateCliente() {
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.updateCliente("CF123", "updated@mail.com", "987654321");
        Cliente updatedCliente = gestioneReclamo.readCliente("CF123");
        assertEquals("updated@mail.com", updatedCliente.getMail());
        assertEquals("987654321", updatedCliente.getNumerotelefono());
        gestioneReclamo.deleteCliente("CF123");
    }

    @Test
    public void testDeleteCliente() {
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.deleteCliente("CF123");
        Cliente deletedCliente = gestioneReclamo.readCliente("CF123");
        assertNull(deletedCliente);
    }

    @Test
    public void testCreateRichiestaCliente() {
        Integer iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "aperto";
        String tipo = "feedback";

        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");

        RichiestaCliente createdResult = gestioneReclamo.readRichiestaCliente(iDRichiesta);
        assertNotNull(createdResult);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteCliente("CF123");
    }

    @Test
    public void testUpdateRichiestaCliente() {
        Integer iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "aperto";
        String tipo = "feedback";

        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");

        gestioneReclamo.updateRichiestaCliente(iDRichiesta, "chiuso");

        // Verify the update
        RichiestaCliente updatedResult = gestioneReclamo.readRichiestaCliente(iDRichiesta);
        assertNotNull(updatedResult);
        assertEquals("chiuso", updatedResult.getStato());

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteCliente("CF123");
    }

    @Test
    public void testDeleteRichiestaCliente() {
        Integer iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "aperto";
        String tipo = "feedback";

        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");

        // Test deleteRichiestaCliente
        gestioneReclamo.deleteRichiestaCliente(iDRichiesta);
        gestioneReclamo.deleteCliente("CF123");

        // Verify the deletion
        RichiestaCliente deletedResult = gestioneReclamo.readRichiestaCliente(iDRichiesta);
        assertNull(deletedResult);

    }

    @Test
    public void testReclamiRisolti() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "chiuso";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "chiuso";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<RichiestaCliente> reclamiRisolti = gestioneReclamo.reclamiRisolti();
        assertNotNull(reclamiRisolti);
        assertEquals(reclamiRisolti.get(0), new RichiestaCliente(1));
        assertEquals(reclamiRisolti.get(1), new RichiestaCliente(2));
        System.out.println(reclamiRisolti);


        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");

    }

    @Test
    public void testReclamiNonRisolti() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "chiuso";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "chiuso";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<RichiestaCliente> reclamiNonRisolti = gestioneReclamo.reclamiNonRisolti();
        assertNotNull(reclamiNonRisolti);
        assertEquals(reclamiNonRisolti.get(0), new RichiestaCliente(3));
        System.out.println(reclamiNonRisolti);


        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testClientiReclamiRisolti() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "chiuso";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "chiuso";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<Cliente> clientiReclamiRisolti = gestioneReclamo.clientiReclamiRisolti();
        assertNotNull(clientiReclamiRisolti);
        assertEquals(clientiReclamiRisolti.get(0), new Cliente("CF123"));
        assertEquals(clientiReclamiRisolti.get(1), new Cliente("CF345"));
        System.out.println(clientiReclamiRisolti);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testClientiReclamiNonRisolti() {

        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "chiuso";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "chiuso";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<Cliente> clientiReclamiNonRisolti = gestioneReclamo.clientiReclamiNonRisolti();
        assertNotNull(clientiReclamiNonRisolti);
        assertEquals(clientiReclamiNonRisolti.get(0), new Cliente("CF456"));
        System.out.println(clientiReclamiNonRisolti);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testClientiReclamiLavorazione() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "lavorazione";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "lavorazione";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<Cliente> clientiReclamiLavorazioni = gestioneReclamo.clientiReclamiLavorazione();
        assertNotNull(clientiReclamiLavorazioni);
        assertEquals(clientiReclamiLavorazioni.get(0), new Cliente("CF123"));
        assertEquals(clientiReclamiLavorazioni.get(1), new Cliente("CF345"));
        System.out.println(clientiReclamiLavorazioni);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testCognomeClientiReclamo() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "lavorazione";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Name", "Ciccia", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "lavorazione";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Name", "Chiricosta", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<String> cognomeClientiReclamo = gestioneReclamo.cognomeClientiReclamo();
        assertNotNull(cognomeClientiReclamo);
        assertEquals(cognomeClientiReclamo.get(0), "Ciccia");
        assertEquals(cognomeClientiReclamo.get(1), "Chiricosta");
        System.out.println(cognomeClientiReclamo);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testNomeClientiReclamo() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "lavorazione";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Umberto Domenico", "Ciccia", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "lavorazione";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Salvatore", "Chiricosta", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<String> nomeClientiReclamo = gestioneReclamo.nomeClientiReclamo();
        assertNotNull(nomeClientiReclamo);
        assertEquals(nomeClientiReclamo.get(0), "Umberto Domenico");
        assertEquals(nomeClientiReclamo.get(1), "Salvatore");
        System.out.println(nomeClientiReclamo);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testCfClientiReclamo() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "lavorazione";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Umberto Domenico", "Ciccia", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "lavorazione";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF345", "Salvatore", "Chiricosta", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<String> cfClientiReclamo = gestioneReclamo.cfClientiReclamo();
        assertNotNull(cfClientiReclamo);
        assertEquals(cfClientiReclamo.get(0), "CF123");
        System.out.println(cfClientiReclamo);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testMailClientiReclamo() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "lavorazione";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Umberto Domenico", "Ciccia", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "lavorazione";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF345", "Salvatore", "Chiricosta", "1@11", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "23@m2", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<String> mailClientiReclamo = gestioneReclamo.mailClientiReclamo();
        assertNotNull(mailClientiReclamo);
        assertEquals(mailClientiReclamo.get(0), "test@mail.com");
        System.out.println(mailClientiReclamo);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testClienteEffettuatoReclamo() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "lavorazione";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Umberto Domenico", "Ciccia", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "lavorazione";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Salvatore", "Chiricosta", "1@11", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "23@m2", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        List<Cliente> clientiEffettuatoReclamo = gestioneReclamo.clienteEffettuatoReclamo();
        assertNotNull(clientiEffettuatoReclamo);
        assertEquals(clientiEffettuatoReclamo.get(0), new Cliente("CF123"));
        assertEquals(clientiEffettuatoReclamo.get(1), new Cliente("CF345"));
        System.out.println(clientiEffettuatoReclamo);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

    @Test
    public void testStatoReclamoCliente() {
        int iDRichiesta = 1;
        String contenuto = "Test Contenuto";
        Date data = new Date();
        String stato = "lavorazione";
        String tipo = "reclamo";
        gestioneReclamo.createCliente("CF123", "Umberto Domenico", "Ciccia", "test@mail.com", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF123");


        iDRichiesta = 2;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "lavorazione";
        tipo = "reclamo";
        gestioneReclamo.createCliente("CF345", "Salvatore", "Chiricosta", "1@11", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF345");


        iDRichiesta = 3;
        contenuto = "Test Contenuto";
        data = new Date();
        stato = "aperto";
        tipo = "feedback";
        gestioneReclamo.createCliente("CF456", "Name", "Surname", "23@m2", "123456789");
        gestioneReclamo.createRichiestaCliente(iDRichiesta, contenuto, data, stato, tipo, "CF456");

        String statoRichiesta = gestioneReclamo.statoReclamoCliente(new Cliente("CF123"));
        assertEquals(statoRichiesta, "lavorazione");
        System.out.println(statoRichiesta);

        gestioneReclamo.deleteRichiestaCliente(1);
        gestioneReclamo.deleteRichiestaCliente(2);
        gestioneReclamo.deleteRichiestaCliente(3);

        gestioneReclamo.deleteCliente("CF123");
        gestioneReclamo.deleteCliente("CF345");
        gestioneReclamo.deleteCliente("CF456");
    }

}

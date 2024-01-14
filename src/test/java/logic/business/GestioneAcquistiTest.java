package logic.business;

import data.*;
import logic.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void testAddAutomezzo() {
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        Automezzo automezzo = gestioneAcquisti.getAutomezzo(targa);
        Assertions.assertEquals(automezzo.getTarga(), targa);
        gestioneAcquisti.removeAutomezzo(targa);
    }

    @Test
    public void testRemoveAutomezzo() {
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.removeAutomezzo(targa);
        Assertions.assertNull(gestioneAcquisti.getAutomezzo(targa));
    }

    @Test
    public void testGetAutomezzo() {
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        Automezzo automezzo = gestioneAcquisti.getAutomezzo(targa);
        Assertions.assertEquals(automezzo.getTarga(), targa);
        gestioneAcquisti.removeAutomezzo(targa);
    }

    @Test
    public void testUpdateAutomezzo() {
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");
        String nuova = "ciccio";
        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.updateAutomezzo(targa, nuova, null, null);
        Automezzo automezzo = gestioneAcquisti.getAutomezzo(targa);
        Assertions.assertEquals(automezzo.getMarca(), nuova);
        gestioneAcquisti.removeAutomezzo(targa);
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

    @Test
    public void testAddPreventivo() {
        //prodotto
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;

        //fornitore
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";

        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //preventivo
        BigDecimal costo = new BigDecimal("10.2");
        Date scadenza = new Date(2002, 2, 2), scrittura = new Date(2002, 2, 3);

        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        gestioneAcquisti.addFornitore(piva, nome, citta);
        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addPreventivo(piva, codice, targa, costo, scadenza, scrittura);

        Preventivo preventivo = gestioneAcquisti.getPreventivo(piva, codice, targa);
        Assertions.assertEquals(preventivo.getPreventivoPK(), new PreventivoPK(piva, codice, targa));

        gestioneAcquisti.removePreventivo(piva, codice, targa);
        gestioneAcquisti.removeAutomezzo(targa);
        gestioneAcquisti.removeProdotto(codice);
        gestioneAcquisti.removeFornitore(piva);

    }

    @Test
    public void testGetPreventivo() {
        //prodotto
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;

        //fornitore
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";

        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //preventivo
        BigDecimal costo = new BigDecimal("10.2");
        Date scadenza = new Date(2002, 2, 2), scrittura = new Date(2002, 2, 3);

        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        gestioneAcquisti.addFornitore(piva, nome, citta);
        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addPreventivo(piva, codice, targa, costo, scadenza, scrittura);

        Preventivo preventivo = gestioneAcquisti.getPreventivo(piva, codice, targa);
        Assertions.assertEquals(preventivo.getPreventivoPK(), new PreventivoPK(piva, codice, targa));

        gestioneAcquisti.removePreventivo(piva, codice, targa);
        gestioneAcquisti.removeAutomezzo(targa);
        gestioneAcquisti.removeProdotto(codice);
        gestioneAcquisti.removeFornitore(piva);

    }

    @Test
    public void testRemovePreventivo() {
        //prodotto
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;

        //fornitore
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";

        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //preventivo
        BigDecimal costo = new BigDecimal("10.2");
        Date scadenza = new Date(2002, 2, 2), scrittura = new Date(2002, 2, 3);

        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        gestioneAcquisti.addFornitore(piva, nome, citta);
        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addPreventivo(piva, codice, targa, costo, scadenza, scrittura);

        gestioneAcquisti.removePreventivo(piva, codice, targa);
        Assertions.assertNull(gestioneAcquisti.getPreventivo(piva, codice, targa));
        gestioneAcquisti.removeAutomezzo(targa);
        gestioneAcquisti.removeProdotto(codice);
        gestioneAcquisti.removeFornitore(piva);

    }

    @Test
    public void testUpdatePreventivo() {
        //prodotto
        int codice = 1;
        String tipo = "tipo";
        int quantita = 10;

        //fornitore
        String piva = "F1";
        String nome = "A";
        String citta = "Locri";

        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //preventivo
        BigDecimal costo = new BigDecimal("10.2"), nuovoCosto = new BigDecimal("10.90");
        Date scadenza = new Date(2002, 2, 2), scrittura = new Date(2002, 2, 3);

        gestioneAcquisti.addProdotto(codice, tipo, quantita);
        gestioneAcquisti.addFornitore(piva, nome, citta);
        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addPreventivo(piva, codice, targa, costo, scadenza, scrittura);

        gestioneAcquisti.updatePreventivo(piva, codice, targa, nuovoCosto, null, null);
        Preventivo preventivo = gestioneAcquisti.getPreventivo(piva, codice, targa);
        Assertions.assertEquals(preventivo.getPrezzo(), nuovoCosto);

        gestioneAcquisti.removePreventivo(piva, codice, targa);
        gestioneAcquisti.removeAutomezzo(targa);
        gestioneAcquisti.removeProdotto(codice);
        gestioneAcquisti.removeFornitore(piva);

    }

    @Test
    public void testAddOrdine() {
        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //Ordine
        int ordine = 1;
        String stato = "consegna";
        Date date = new Date();
        int quantitaOrdine = 10;

        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addOrdine(ordine, stato, date, quantitaOrdine, -1, targa);
        Ordine ord = gestioneAcquisti.getOrdine(ordine);
        Assertions.assertEquals(ord.getNumero(), ordine);
        gestioneAcquisti.removeOrdine(ordine);
        gestioneAcquisti.removeAutomezzo(targa);
    }

    @Test
    public void testRemoveOrdine() {
        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //Ordine
        int ordine = 1;
        String stato = "consegna";
        Date date = new Date();
        int quantitaOrdine = 10;
        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addOrdine(ordine, stato, date, quantitaOrdine, -1, targa);
        gestioneAcquisti.removeOrdine(ordine);
        Assertions.assertNull(gestioneAcquisti.getOrdine(ordine));
        gestioneAcquisti.removeAutomezzo(targa);
    }

    @Test
    public void testGetOrdine() {
        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //Ordine
        int ordine = 1;
        String stato = "consegna";
        Date date = new Date();
        int quantitaOrdine = 10;

        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addOrdine(ordine, stato, date, quantitaOrdine, -1, targa);
        Ordine ord = gestioneAcquisti.getOrdine(ordine);
        Assertions.assertEquals(ord.getNumero(), ordine);
        gestioneAcquisti.removeOrdine(ordine);
        gestioneAcquisti.removeAutomezzo(targa);
    }

    @Test
    public void testUpdateOrdine() {
        //automezzo
        String targa = "ABCDEFG1111";
        String marca = "alpha";
        String assicurazione = "Unipol";
        BigDecimal prezzo = new BigDecimal("20.5");

        //Ordine
        int ordine = 1;
        String stato = "consegna";
        Date date = new Date();
        int quantitaOrdine = 10, nuova = 12;

        gestioneAcquisti.addAutomezzo(targa, marca, assicurazione, prezzo);
        gestioneAcquisti.addOrdine(ordine, stato, date, quantitaOrdine, -1, targa);
        gestioneAcquisti.updateOrdine(ordine, stato, date, nuova);
        Ordine ord = gestioneAcquisti.getOrdine(ordine);
        Assertions.assertEquals(ord.getQuantita(), nuova);
        gestioneAcquisti.removeOrdine(ordine);
        gestioneAcquisti.removeAutomezzo(targa);
    }

    @Test
    public void testFornitoriDiUnaCitta() {
        String piva = "F1",piva2="F2";
        String nome = "A",nome2="B";
        String citta2="Cosenza";

        gestioneAcquisti.addFornitore(piva, nome, citta2);
        gestioneAcquisti.addFornitore(piva2, nome2, citta2);

        List<Fornitore>fornitori=gestioneAcquisti.fornitoriDiUnaCitta(citta2);
        Assertions.assertEquals(fornitori.get(0).getPiva(),piva);
        Assertions.assertEquals(fornitori.get(1).getPiva(),piva2);

        gestioneAcquisti.removeFornitore(piva);
        gestioneAcquisti.removeFornitore(piva2);
    }

    @Test
    public void testFornitoriDiUnaPartitaIva() {
        String piva = "F1",piva2="F2";
        String nome = "A",nome2="B";
        String citta2="Cosenza";

        gestioneAcquisti.addFornitore(piva, nome, citta2);
        gestioneAcquisti.addFornitore(piva2, nome2, citta2);

        List<Fornitore>fornitori=gestioneAcquisti.fornitoriDiUnaPartitaIva(piva);
        Assertions.assertEquals(fornitori.get(0).getPiva(),piva);

        gestioneAcquisti.removeFornitore(piva);
        gestioneAcquisti.removeFornitore(piva2);
    }

}

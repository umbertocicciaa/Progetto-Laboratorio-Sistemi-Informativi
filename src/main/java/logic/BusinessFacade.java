package logic;

import logic.business.GestioneAcquisti;

public final class BusinessFacade {
    private static final GestioneAcquisti gestioneAcquisti = new GestioneAcquisti() {
    };

    public static GestioneAcquisti getGestioneAcquisti() {
        return gestioneAcquisti;
    }
}

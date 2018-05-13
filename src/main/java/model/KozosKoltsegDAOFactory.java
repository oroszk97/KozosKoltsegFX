package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * Adatbáziskapcsolat létrehozására szükséges osztály.
 */
public class KozosKoltsegDAOFactory implements AutoCloseable {
    /**
     * Az adatbázishoz szükséges adattag.
     */
    private static KozosKoltsegDAOFactory instance;
    /**
     * Az adatbázishoz szükséges adattag.
     * @see EntityManager
     */
    private static EntityManager em;
    /**
     * Az adatbázishoz szükséges adattag.
     * @see EntityManagerFactory
     */
    private static EntityManagerFactory f;

    static {

        instance = new KozosKoltsegDAOFactory();
        f = Persistence.createEntityManagerFactory("KozosKoltseg");
        em = f.createEntityManager();

    }
    /**
     * Egy {@code KozosKoltsegDAOFactory} objektum létrehozáshoz szükséges konstruktora.
     */
    private KozosKoltsegDAOFactory() {
    }

    /**
     * Visszaadja az adatbáziskapcsolatot.
     * @return az adatbáziskapcsolatot
     */
    public static KozosKoltsegDAOFactory getInstance() {

        return instance;
    }

    /**
     * Létrehoz egy új adatbáziskapcsolatot.
     * @return egy új adatbáziskapcsolatot
     */
    public KozosKoltsegDAO createKozosKoltsegDAO() {
        return new KozosKoltsegDAOImpl(em);
    }

    /**
     * Bezárja az adatbáziskapcsolatot.
     * @throws Exception Sikertelen bezárás esetén, vagy nem található kapcsolat esetén.
     */
    @Override
    public void close() throws Exception {
        f.close();
        em.close();
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author f4ke
 */
public class KozosKoltsegDAOImpl implements KozosKoltsegDAO{
    /**
     * Az adatbáziskapcsolathoz szükséges adattag.
     */
    private EntityManager em;

    /**
     * Visszaadja a jelenlegi adatbáziskapcsolatot.
     * @param em egy adatbáziskapcsolat
     */
    public KozosKoltsegDAOImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void createKepviselo(String azonosito, String jelszo) {
        
        em.getTransaction().begin();
        
        Kepviselo k = new Kepviselo(azonosito,jelszo);
        
        em.persist(k);
    
        em.getTransaction().commit();
    }

    @Override
    public Kepviselo readKepviselo(int id) {
        
        return em.find(Kepviselo.class, id);
    
    }

    @Override
    public void updateKepviselo(Kepviselo k, String azonosito, String jelszo, String nev) {
        
        em.getTransaction().begin();
        
        k.setAzonosito(azonosito);
        k.setJelszo(jelszo);
        k.setNev(nev);
        
        em.getTransaction().commit();
    }

    @Override
    public void deleteKepviselo(Kepviselo k) {
        
        em.getTransaction().begin();
        
        em.remove(k);
        
        em.getTransaction().commit();
    
    }

    @Override
    public List<Kepviselo> findKepviselo(String azonosito) {
       
        TypedQuery<Kepviselo> q = em.createQuery("SELECT k FROM Kepviselok k WHERE k.azonosito='"
                                            +azonosito+ "'",Kepviselo.class);
        
        return q.getResultList();
    
    }
    
    @Override
    public void createLako(int emelet, int lakas, String jelszo) {
        
        em.getTransaction().begin();
        
        Lako l = new Lako(emelet,lakas,jelszo);
        
        em.persist(l);
    
        em.getTransaction().commit();
    }

    @Override
    public Lako readLako(int id) {
        
        return em.find(Lako.class, id);
    
    }

    @Override
    public void updateLako(Lako l, int emelet, int lakas , String jelszo, int befizetett, int telefonszam) {
        
        em.getTransaction().begin();
        
        l.setEmelet(emelet);
        l.setLakas(lakas);
        l.setJelszo(jelszo);
        l.setTelefonszam(telefonszam);
        l.setBefizetett(befizetett);
        
        em.getTransaction().commit();
    }

    @Override
    public void deleteLako(Lako l) {
        
        em.getTransaction().begin();
        
        em.remove(l);
        
        em.getTransaction().commit();
    
    }

    @Override
    public List<Lako> findLako(int emelet, int lakas) {
       
        TypedQuery<Lako> q = em.createQuery("SELECT l FROM Lakok l WHERE l.emelet='"
                                            + emelet + "' and l.lakas='" + lakas + "'",Lako.class);
        
        return q.getResultList();
    
    }
    
    @Override
    public List<Lako> getOsszLako(){
        
        TypedQuery<Lako> q = em.createQuery("SELECT l from Lakok l",Lako.class);
        
        return q.getResultList();
    }
    @Override
    public Tarsashaz readTarsashaz(int id){
        
        return em.find(Tarsashaz.class, id);
    
    }
    
    @Override
    public void updateTarsashaz(Tarsashaz t, String cim, String uzenet, int kozoskoltseg){
        em.getTransaction().begin();
        
        t.setCim(cim);
        t.setKozoskoltseg(kozoskoltseg);
        t.setUzenet(uzenet);
        
        em.getTransaction().commit();
    }
}

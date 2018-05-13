/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Egy társasházat reprezentáló osztály.
 */
    @Entity(name="Tarsashaz")
    @Table(name="Tarsashaz")
public class Tarsashaz {
    
    /**
     * Egy id adattag amit az adatbázisban használunk egyedi azonosítóként.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    /**
     * Cim adattag ami a társasház címét reprezentálja.
     */
    @Column(name="cim")
    private String cim;
    
    /**
     * Üzenet adattag amit egy {@code Kepviselo} tud beállítani.
     */
    @Column(name="uzenet")
    private String uzenet;
    
    /**
     * Közösköltség adattag ami egy {@code Lako}-nak kell kifizetnie egy hónapra.
     */
    @Column(name="kozoskoltseg")
    private int kozoskoltseg;
    
    /**
     * Konstruktor egy {@code Tarsashaz} objektum létrehozására.
     */
    public Tarsashaz(){}

    /**
     * Konstruktor egy {code Tarsashaz} objektum létrehozására.
     * 
     * @param cim a társasház címe
     * @param uzenet egy {@code Kepviselo} által megadott üzenet az összes {@code Lako} számára
     * @param kozoskoltseg a társasházban a közösköltség egy {@code Lako} számára egy hónapra
     */
    public Tarsashaz(String cim, String uzenet, int kozoskoltseg) {
        this.cim = cim;
        this.uzenet = uzenet;
        this.kozoskoltseg = kozoskoltseg;
    }

    /**
     * Visszaadja a társasház címét.
     * 
     * @return a társasház címét
     */
    public String getCim() {
        return cim;
    }

    /**
     * Beállítja a társasház címét.
     * 
     * @param cim a társasház címe
     */
    public void setCim(String cim) {
        this.cim = cim;
    }

    /**
     * Visszaadja egy {@code Kepviselo} által megadott üzenetet.
     * 
     * @return egy {@code Kepviselo} által megadott üzenetet
     */
    public String getUzenet() {
        return uzenet;
    }

    /**
     * Beállítja egy {@code Kepviselo} által megadott üzenetet.
     * 
     * @param uzenet egy {@code Kepviselo} által megadott üzenet
     */
    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }

    /**
     * Visszaadja a társasházban egy {@code Lako}-ra értetődő közösköltséget egy hónapra.
     * 
     * @return a társasházban egy {@code Lako}-ra értetődő közösköltséget egy hónapra
     */
    public int getKozoskoltseg() {
        return kozoskoltseg;
    }

    /**
     * Beállítja a társasházban egy {@code Lako}-ra értetődő közösköltséget egy hónapra.
     * 
     * @param kozoskoltseg a társasházban egy {@code Lako}-ra értetődő közösköltség egy hónapra
     */
    public void setKozoskoltseg(int kozoskoltseg) {
        this.kozoskoltseg = kozoskoltseg;
    }
}

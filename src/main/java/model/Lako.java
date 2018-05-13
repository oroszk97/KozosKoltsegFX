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
 * Egy lakót reprezentáló osztály.
 */

@Entity(name = "Lakok")
@Table(name = "Lakok")
public class Lako {
    
    /**
     * Egy id adattag ami az adatbázisban egy egyedi azonosítót szolgál.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    /**
     * Emelet adattag ami egy lakó lakásának emelete.
     */
    @Column(name = "emelet")
    private int emelet;
    
    /**
     * Lakás adattag ami egy lakó lakásának házszáma.
     */
    @Column(name = "lakas")
    private int lakas;
    
    /**
     * Jelszó adattag ami egy lakó belépési jelszava.
     */
    @Column(name = "jelszo")
    private String jelszo;
    
    /**
     * Telefonszám adattag, egy lakó telefonszáma.
     */
    @Column(name = "telefonszam")
    private int telefonszam;
    
    /**
     * Befizetett adattag, megadja hogy egy lakó mennyit közösköltséget fizetett be eddig.
     */
    @Column(name = "befizetett")
    private int befizetett;

    /**
     * Konstruktor egy {@code Lako} objektum létrehozására.
     */
    public Lako() {
    }

    /**
     * Konstuktor egy {code Lako} objemtum létrehozására.
     * 
     * @param emelet egy lakó lakásának az emelete
     * @param lakas egy lakó lakásának a házszáma
     * @param jelszo egy lakó jelszava
     */
    public Lako(int emelet,int lakas , String jelszo) {
        this.emelet = emelet;
        this.lakas = lakas;
        this.jelszo = jelszo;
        this.telefonszam = telefonszam;
    }

    /**
     * Visszaadja a lakó összes közösköltség befizetésnek az összegét.
     * 
     * @return a lakó összes eddig befizetett közösköltségét
     */
    public int getBefizetett() {
        return befizetett;
    }

    /**
     * Beállítja a lakó összes közösköltség befizetésének az összegét.
     * 
     * @param befizetett a lakó összes eddig befizetett közösköltsége
     */
    public void setBefizetett(int befizetett) {
        this.befizetett = befizetett;
    }
    
    /**
     * Visszaadja a lakó lakásának az emeletét.
     * 
     * @return a lakó lakásának az emeletét
     */
    public int getEmelet() {
        return emelet;
    }
    
    /**
     * Visszaadja a lakó lakásának a házszámát.
     * 
     * @return a lakó lakásának a házszámát
     */
    public int getLakas() {
        return lakas;
    }

    /**
     * Beállítja a lakó lakásának az emeletét.
     * 
     * @param emelet a lakó lakásának az emelete
     */
    public void setEmelet(int emelet) {
        this.emelet = emelet;
    }
    
    /**
     * Beállítja a lakó lakásának a házszámát.
     * 
     * @param lakas a lakó lakásának a házszáma
     */
    public void setLakas(int lakas) {
        this.lakas = lakas;
    }

    /**
     * Visszaadja a lakó jelszavát.
     * 
     * @return a lakó jelszavát
     */
    public String getJelszo() {
        return jelszo;
    }

    /**
     * Beállítja a lakó jelszavát.
     * 
     * @param jelszo a lakó jelszava
     */
    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }
    
    /**
     * Visszaadja a lakó telefonszámát.
     * 
     * @return a lakó telefonszámát
     */
    public int getTelefonszam() {
        return telefonszam;
    }

    /**
     * Beállítja a lakó telefonszámát.
     * 
     * @param tel a lakó telefonszáma
     */
    public void setTelefonszam(int tel) {
        this.telefonszam = tel;
    }
    
}

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
 * Egy képviselőt reprezentáló osztály.
 */

@Entity(name = "Kepviselok")
@Table(name = "Kepviselok")
public class Kepviselo {
    /**
     * Egy id adattag ami az adatbázisban egy egyedi azonosítóként szolgál.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    /**
     * Egy azonosító adattag, ami a belépéshez szükséges azonosítót tartalmazza.
     */
    @Column(name = "azonosito")
    private String azonosito;
    
    /**
     * Egy jelszó adattag, ami a belépéshez szükséges jelszót tartalmazza.
     */
    @Column(name = "jelszo")
    private String jelszo;
    
    /**
     * Egy név adattag, ami a képviselő nevét tartalmazza.
     */
    @Column(name = "nev")
    private String nev;

    /**
     * Konstruktor egy {@code Képviselő} objektum létrehozására.
     */
    public Kepviselo() {
    }

    /**
     * Konstruktor egy Képviselő objektum létrehozására.
     * 
     * @param azonosito a {@code Képviselő} belépési azonosítója 
     * @param jelszo a {@code Képviselő} belépéshez szükséges jelszava
     */
    public Kepviselo(String azonosito, String jelszo) {
        this.azonosito = azonosito;
        this.jelszo = jelszo;
    }

    /**
     * Visszaadja a képviselő nevét.
     * 
     * @return a képviselő neve 
     */
    public String getNev() {
        return nev;
    }

    /**
     * Beállítja a {@code Képviselő} nevét.
     * 
     * @param nev a Képviselő neve
     */
    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * Visszaadja a {@code Képviselő} belépési azonosítóját.
     * 
     * @return a {@code Képviselő} azonosítóját
     */
    
    public String getAzonosito() {
        return azonosito;
    }

    /**
     * Beállítja a {@code Képviselő} azonosítóját.
     * 
     * @param azonosito a {@code Képviselő} azonosítója
     */
    public void setAzonosito(String azonosito) {
        this.azonosito = azonosito;
    }

    /**
     * Visszaadja a {@code Képviselő} belépési jelszavát.
     * 
     * @return a {@code Képviselő} jelszavát
     */
    public String getJelszo() {
        return jelszo;
    }

    /**
     * Beállítja a {@code Képviselő} belépési jelszavát.
     * 
     * @param jelszo a {@code Képviselő} belépési jelszava
     */
    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }
}

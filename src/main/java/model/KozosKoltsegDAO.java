/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * 
 * Ez az interfész olvassa le, módosítja, vagy törli az adatokat az adatbázisból. 
 * 
 */
public interface KozosKoltsegDAO {
    
    /**
     * Egy új {@code Kepviselo} objektum létrehozására használt függvény. 
     * @param azonosito {@code Kepviselo} azonosítója
     * @param jelszo {@code Kepviselo} jelszava
     */
    public void createKepviselo(String azonosito ,String jelszo);
    
    /**
     * Egy létező {@code Kepviselo} olvasására használt függvény.
     * @param id egy képviselő adatbázisban használt egyedi azonosítója
     * @return egy {@code Kepviselo} objektumot
     */
    public Kepviselo readKepviselo(int id);
    
    /**
     * Egy létező {@code Kepviselo} adatai módosításához használt függvény.
     * 
     * @param k egy {@code Kepviselo} objektum
     * @param azonosito a képviselő azonosítója
     * @param jelszo a képviselő jelszava
     * @param nev a képviselő neve
     */
    public void updateKepviselo(Kepviselo k, String azonosito, String jelszo, String nev);
    
    /**
     * Egy létező {@code Kepviselo} objektum törlésére használt függvény.
     * 
     * @param k egy {@code Kepviselo} objektum
     */
    public void deleteKepviselo(Kepviselo k);
    
    /**
     * Egy létező {@code Kepviselo} keresésére használt függvény.
     * 
     * @param azonosito képviselő azonosítója
     * @return egy listát a találatokról
     */
    public List<Kepviselo> findKepviselo(String azonosito);
    
    /**
     * Egy {@code Lako} objektum létrehozására használt függvény.
     * 
     * @param emelet a lakó lakásának emelete
     * @param lakas a lakó lakásának száma
     * @param jelszo a lakó jelszava
     */
    public void createLako(int emelet, int lakas ,String jelszo);
    
    /**
     * Egy {@code Lako} olvasására szolgáló függvény.
     * 
     * @param id a lakó adatbázisban használt egyedi azonosítója
     * @return egy {@code Lako} objektumot
     */
    public Lako readLako(int id);
    
    /**
     * Egy {@code Lako} adatai módosításához használt függvény.
     * 
     * @param l egy {@code Lako} objektum
     * @param emelet a lakó lakásának emelete
     * @param lakas a lakó lakásának a házszáma
     * @param jelszo a lakó jelszava
     * @param befizetett a lakó által összesen befizetett összeg
     * @param telefonszam a lakó telefonszáma
     */
    public void updateLako(Lako l, int emelet,int lakas, String jelszo, int befizetett, int telefonszam);
    
    /**
     * Egy {@code Lako} objektum törlésére használt függvény.
     * 
     * @param l 
     */
    public void deleteLako(Lako l);
    
    /**
     * Egy létező {@code Lako} keresésére használt függvény.
     * 
     * @param emelet a lakó lakásának emelete
     * @param lakas a lakó lakásának házszáma
     * @return a találatok listáját
     */
    public List<Lako> findLako(int emelet,int lakas);
    
    /**
     * Az összes {@code Lako} objektum listájának visszaadására szolgáló függvény.
     * 
     * @return az összes {@code Lako} objektum listáját az adatbázisból
     */
    public List<Lako> getOsszLako();
    
    /**
     * Egy létező {@code Tarsashaz} objektum olvasására szolgáló függvény.
     * 
     * @param id a társasház adatbázisban használt egyedi azonosítója
     * @return egy {@code Tarsashaz} objektumot
     */
    public Tarsashaz readTarsashaz(int id);
    
    /**
     * Egy létező {@code Tarsashaz} adatainak módosítására szolgáló függvény.
     * 
     * @param t egy {@code Tarsashaz} objektum
     * @param cim a társasház címe
     * @param uzenet egy {@code Kepviselo} által megadott üzenet az összes {@code Lako} -nak
     * @param kozoskoltseg a társasházban a közösköltség egy {@code Lako} számára egy hónapra
     */
    public void updateTarsashaz(Tarsashaz t, String cim, String uzenet, int kozoskoltseg);
    
}

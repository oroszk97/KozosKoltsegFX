/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * 
 * {@code Kepviselo} belépéséhez, és regisztrációjához szükséges osztály.
 *
 */
public class KepviseloValidator {
    /**
     * Az adatbáziskapcsolathoz szükséges adattag.
     */
    private KozosKoltsegDAO ud;

    /**
     * Konstruktor egy {@code KepviseloValidator} objektum létrehozására..
     * 
     * @param ud Az adatbázis kapcsolat
     *
     */
    public KepviseloValidator(KozosKoltsegDAO ud) {
        this.ud = ud;
    }
    
    
    /**
     * Visszaadja hogy a {@code Kepviselo} belépéshez az azonosítás sikeres volt-e vagy sem.
     * 
     * @param azonosito {@code Kepviselo} azonosítója
     * @param jelszo {@code Kepviselo} jelszava
     * @return a belépés sikerességét
     */
    public boolean loginValidate(String azonosito, String jelszo) {
        
        List<Kepviselo> kepviseloLista = ud.findKepviselo(azonosito);
        
        return kepviseloLista.size()>0 && kepviseloLista.get(0).getJelszo().equals(jelszo);
        
    }
    
    /**
     * Visszaadja egy új {@code Kepviselo} kreálásának sikerességét, vagyis hogy nem-e foglalt az adott azonosító.
     * 
     * @param azonosito {@code Kepviselo} azonosítója
     * @return a regisztráció sikerességét
     */
     public boolean regValidate(String azonosito){
        List<Kepviselo> kepviseloLista = ud.findKepviselo(azonosito);
        
        return kepviseloLista.isEmpty() && !azonosito.isEmpty() ;
    }
    
}

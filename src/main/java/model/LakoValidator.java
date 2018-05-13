/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * {@code Lako} belépéséhez, és regisztrációjához szükséges osztály.
 * 
 */
public class LakoValidator {
    
    /**
     * Egy {@code KozosKoltsegDAO} objektum ami az adatbáziskapcsolathoz szükséges.
     */
    private KozosKoltsegDAO ud;
    
    /**
     * Konstruktor egy {@code LakoValidator} objektum létrehozására..
     * 
     * @param ud Az adatbázis kapcsolat
     *
     */

    public LakoValidator(KozosKoltsegDAO ud) {
        this.ud = ud;
    }
    
    
    /**
     * Visszaadja hogy a {@code Lako} belépéshez az azonosítás sikeres volt-e vagy sem.
     * 
     * @param emelet {@code Lako} lakásának emelete
     * @param lakas {@code Lako} lakásának házszáma
     * @param jelszo {@code Lako} jelszava
     * @return a belépés sikerességét
     */
    public boolean loginValidate(int emelet,int lakas, String jelszo) {
        
        List<Lako> lakoLista = ud.findLako(emelet, lakas);
        
        return lakoLista.size()>0 && lakoLista.get(0).getJelszo().equals(jelszo);
        
    }
    /**
     * Visszaadja egy új {@code Lako} kreálásának sikerességét, vagyis hogy nincs-e már regisztrálva az a lakás.
     * 
     * @param emelet {@code Lako} lakásának emelete
     * @param lakas {@code Lako} lakásának házszáma
     * @return a regisztráció sikerességét
     */   
    public boolean regValidate(int emelet, int lakas){
        List<Lako> lakoLista = ud.findLako(emelet,lakas);
        
        return lakoLista.isEmpty() ;
    }
    
}

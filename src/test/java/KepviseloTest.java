/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import model.Kepviselo;


public class KepviseloTest {
    
    public KepviseloTest() {
    }
    
    @Test
    public void KepviseloAzonTeszt(){
      String azonosito = "teszt";
      String jelszo = "teszt";
      Kepviselo k = new Kepviselo(azonosito,jelszo);
      
        k.setAzonosito("valami");
        assertEquals("valami", k.getAzonosito());
    }
    
    @Test
    public void KepviseloJelszoTeszt(){
      String azonosito = "teszt";
      String jelszo = "teszt";
      Kepviselo k = new Kepviselo(azonosito,jelszo);
      
        k.setJelszo("valami");
        assertEquals("valami", k.getJelszo());
    }
    
    @Test
    public void KepviseloNevTeszt(){
      String azonosito = "teszt";
      String jelszo = "teszt";
      Kepviselo k = new Kepviselo(azonosito,jelszo);
      
        k.setNev("valami");
        assertEquals("valami", k.getNev());
    }
}

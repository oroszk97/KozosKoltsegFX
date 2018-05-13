/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import model.Tarsashaz;


public class TarsashazTest {
    
    public TarsashazTest() {
    }
    
    @Test
    public void TarsashazCimTeszt(){
      String cim = "valami";
      String uzenet = "valami";
      int kozoskoltseg = 2;
      
      Tarsashaz t = new Tarsashaz(cim, uzenet, kozoskoltseg);
      
        t.setCim("valami2");
        assertEquals("valami2", t.getCim());
    }
    
    @Test
    public void TarsashazUzenetTeszt(){
      String cim = "valami";
      String uzenet = "valami";
      int kozoskoltseg = 2;
      
      Tarsashaz t = new Tarsashaz(cim, uzenet, kozoskoltseg);
      
        t.setUzenet("ujuzenet");
        assertEquals("ujuzenet", t.getUzenet());
    }
    
    @Test
    public void TarsashazKoltsegTeszt(){
      String cim = "valami";
      String uzenet = "valami";
      int kozoskoltseg = 2;
      
      Tarsashaz t = new Tarsashaz(cim, uzenet, kozoskoltseg);
      
        t.setKozoskoltseg(3000);
        assertEquals(3000, t.getKozoskoltseg());
    }
}

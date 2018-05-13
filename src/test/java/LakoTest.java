/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import model.Lako;


public class LakoTest {
    
    public LakoTest() {
    }
    
    @Test
    public void LakoEmeletTeszt(){
      int emelet = 1;
      int lakas = 1;
      String jelszo = "valami";
      Lako l = new Lako(emelet,lakas,jelszo);
      
        l.setEmelet(2);
        assertEquals(2, l.getEmelet());
    }
    
    @Test
    public void LakoLakasTeszt(){
      int emelet = 1;
      int lakas = 1;
      String jelszo = "valami";
      Lako l = new Lako(emelet,lakas,jelszo);
      
        l.setLakas(2);
        assertEquals(2, l.getLakas());
    }
    
    @Test
    public void LakoJelszoTeszt(){
      int emelet = 1;
      int lakas = 1;
      String jelszo = "valami";
      Lako l = new Lako(emelet,lakas,jelszo);
      
        l.setJelszo("valami2");
        assertEquals("valami2", l.getJelszo());
    }
    
    @Test
    public void LakoTelTeszt(){
      int emelet = 1;
      int lakas = 1;
      String jelszo = "valami";
      Lako l = new Lako(emelet,lakas,jelszo);
      
        l.setTelefonszam(22);
        assertEquals(22, l.getTelefonszam());
    }

    @Test
    public void LakoBefizetettTeszt(){
      int emelet = 1;
      int lakas = 1;
      String jelszo = "valami";
      Lako l = new Lako(emelet,lakas,jelszo);
      
        l.setBefizetett(123);
        assertEquals(123, l.getBefizetett());
    }    
}

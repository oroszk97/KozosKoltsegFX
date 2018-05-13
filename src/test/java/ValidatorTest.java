/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kepviselo;
import model.KepviseloValidator;
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;
import model.Lako;
import model.LakoValidator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author f4ke
 */
public class ValidatorTest {
    
    public KozosKoltsegDAO ud;
    
    public ValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
     ud = KozosKoltsegDAOFactory.getInstance().createKozosKoltsegDAO();
    }
    
    @After
    public void tearDown() {
            //KozosKoltsegDAOFactory.getInstance().close();
        }
    
    @Test
    public void LakoRegTest(){
        
        LakoValidator v = new LakoValidator(ud);
        
        assertEquals(0,ud.findLako(124, 123).size());
        assertEquals(true,v.regValidate(124, 123));
        ud.createLako(222, 222, "v");
        assertEquals(false,v.regValidate(222, 222));
        Lako l = ud.findLako(222, 222).get(0);
        ud.deleteLako(l);
    }
    
    @Test
    public void LakoLogTest(){
    
        LakoValidator v = new LakoValidator(ud);
        
        ud.createLako(123, 456, "teszt");
	Lako l = ud.findLako(123,456).get(0);        

        assertEquals(true, v.loginValidate(123, 456, "teszt"));
	ud.updateLako(l,123,456,"jelszo",2,3);
        assertEquals(false, v.loginValidate(123, 456, "teszt"));
        
        l = ud.findLako(123, 456).get(0);
        ud.deleteLako(l);
        
    }
    
    @Test
    public void KepviseloRegTest(){
        KepviseloValidator v = new KepviseloValidator(ud);
        
        assertEquals(0,ud.findKepviselo("teszthezazon").size());
        assertEquals(true,v.regValidate("teszthezazon"));
        
        ud.createKepviselo("teszthezazon", "jelszo");
        assertEquals(1,ud.findKepviselo("teszthezazon").size());
        assertEquals(false, v.regValidate("teszthezazon"));
        
        Kepviselo k = ud.findKepviselo("teszthezazon").get(0);
        ud.deleteKepviselo(k);
    }
    
    @Test
    public void KepviseloLogTest(){
        KepviseloValidator v = new KepviseloValidator(ud);
        ud.createKepviselo("teszthezazon", "jelszo");
        Kepviselo k = ud.findKepviselo("teszthezazon").get(0);       
        assertEquals(true,v.loginValidate("teszthezazon", "jelszo"));
	ud.updateKepviselo(k,"teszthezazon1","jelszo","a");
        assertEquals(0,ud.findKepviselo("teszthezazon").size());
        assertEquals(false,v.loginValidate("teszthezazon", "jelszo"));
        k = ud.findKepviselo("teszthezazon1").get(0);
        ud.deleteKepviselo(k);
    }
    
}

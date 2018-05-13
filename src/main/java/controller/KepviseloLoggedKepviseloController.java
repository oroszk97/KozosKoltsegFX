package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Kepviselo;
import model.KepviseloValidator;
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;
import model.Tarsashaz;


/**
 * A bejelentkezett képviselő, képviselő menü grafikus megjelenítésére szolgáló vezérlőosztály.
 */
public class KepviseloLoggedKepviseloController implements Initializable{
	/**
	 * A naplózáshoz szükséges {@code Logger}.
	 */
	private static Logger logger = LoggerFactory.getLogger(KepviseloLoggedLakoController.class);

    /**
     * Kilépés gomb.
     */
    @FXML
    private Button kilep;

    /**
     * Kereséshez szükséges szövegdoboz.
     */
    @FXML
    private TextField azonk;

    /**
     * Visszatérési üzenethez szükséges szöveg.
     */
    @FXML
    private Label msg;

    /**
     * Találatnál az azonosító szövegdoboz.
     */
    @FXML
    private TextField azont;

    /**
     * Találatnál a jelszó szövegdoboz.
     */
    @FXML
    private PasswordField jelszot;

    /**
     * Találatnál a név szövegdoboz.
     */
    @FXML
    private TextField nevt;
    
    /**
     * Találatnál a cím szövegdoboz.
     */
    @FXML
    private TextField tcim;
    
    /**
     * Közösköltség szövegdoboza.
     */
    @FXML
    private TextField kozoskolts;
    
    /**
     * Bejelentkezett képviselő azonosítója.
     */
    private String kepviseloazon;
    
    /**
     * Adatbáziskapcsolat.
     */
    private KozosKoltsegDAO ud;
    
    /**
     * Módosításhoz egy {@code Kepviselo} objektum.
     */
    private Kepviselo current;
    
    /**
     * Segédmetódus ami visszaadja egy Stringről hogy csak számokat tartalmaz-e.
     * @param s egy {@code String} objektum
     * @return hogy a {@code String} csak számokat tartalmaz-e
     */
    public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");}
            
/**
 * Képvselő keresés.
 * @param event gombnyomás 
 */
    @FXML
    void keres(ActionEvent event) {
        
        if(azonk.getText().isEmpty()){
            msg.setText("A keresési mező nem lehet üres!");
        }
        else if(ud.findKepviselo(azonk.getText()).size() == 0){
            msg.setText("Nincs találat!");
    }   else{
        current = ud.findKepviselo(azonk.getText()).get(0);
        azont.setText(current.getAzonosito());
        jelszot.setText(current.getJelszo());
        nevt.setText(current.getNev());
        msg.setText("Sikeres keresés!");
        }

    }

    /**
     * Kilépés a programból.
     * @param event gombnyomás
     */
    @FXML
    void kilepes(ActionEvent event) {
        
        Stage stage = (Stage) kilep.getScene().getWindow();
        try {
            KozosKoltsegDAOFactory.getInstance().close();
        } catch (Exception ex) {
           logger.error("Hiba a kilepes kozben:" + ex.getMessage());
        }
       stage.close();

    }

    /**
     * Képviselő módosítása.
     * @param event gombnyomás
     */
    @FXML
    void modositas(ActionEvent event) {
        
        if(azont.getText().isEmpty() || jelszot.getText().isEmpty() || nevt.getText().isEmpty() ){
            msg.setText("Módosításnál nem lehetnek üres mezők!");
            logger.warn("Kepviselo Modositasnal ures mezok! Kepviselo:"+kepviseloazon);
        }
        else if (ud.findKepviselo(azont.getText()).size() > 0){
            current = ud.findKepviselo(azont.getText()).get(0);
            if(current.getAzonosito().equals(kepviseloazon)) {
            	kepviseloazon = azont.getText();
            }
            ud.updateKepviselo(current, azont.getText(), jelszot.getText(), nevt.getText());
            msg.setText("Sikeres módosítás!");
            logger.info("Sikeres kepviselo modositas! Kepviselo:"+ kepviseloazon);
        }

    }
    
    /**
     * Társasház módosítása.
     * @param event gombnyomás
     */
    @FXML
    void tarsasmodositas(ActionEvent event){
        
        if(tcim.getText().isEmpty() || kozoskolts.getText().isEmpty() || !isNumeric(kozoskolts.getText()) )
        {
            msg.setText("Hibás vagy üres mezők a módosításhoz!");
            logger.warn("Hibas vagy ures mezok a tarsashaz modositasnal! Kepviselo:"+kepviseloazon);
        }else{
            Tarsashaz t = ud.readTarsashaz(1);
            ud.updateTarsashaz(t,tcim.getText(), t.getUzenet(), Integer.parseInt(kozoskolts.getText()));
            msg.setText("Sikeres módosítás!");
            logger.info("Sikeres tarsashazmodositas! Kepviselo:"+kepviseloazon);
        }
        
    }
    
    /**
     * Új képvselő létrehozása.
     * @param event gombnyomás
     */
    @FXML
    void ujkepviselo(ActionEvent event){
        
        KepviseloValidator v = new KepviseloValidator(ud);
        if(!azont.getText().isEmpty()){
        if(v.regValidate(azont.getText())){
            ud.createKepviselo(azont.getText(), jelszot.getText());
            msg.setText("Sikeres létrehozás!");
            logger.info("Sikeres kepviselo letrehozas! Kepviselo:"+kepviseloazon);
            }else{
            msg.setText("Már van ilyen Képviselő!");
            logger.warn("Kepviselo letrehozasnal mar letezo kepviselo! Kepviselo:" + kepviseloazon);
        }
        }else{
            msg.setText("Az azonosító nem lehet üres!");
            logger.warn("Kepviselo letrehozasnal ures mezo(k)! Kepviselo:"+kepviseloazon);
        }
    }
    
    /**
     * Képviselő törlése.
     * @param event gombnyomás
     */
    @FXML
    void kepviselotorol(ActionEvent event){
        if(!azont.getText().isEmpty()){
        if(ud.findKepviselo(azont.getText()).size() > 0){
        Kepviselo k = ud.findKepviselo(azont.getText()).get(0);
        ud.deleteKepviselo(k);
        msg.setText("Sikeres törlés!");
        logger.info("Sikeres kepviselo torles! Kepviselo:" + kepviseloazon);
        }else{
        msg.setText("Nem létező azonosító!");
        logger.warn("Nem letezo azonosito kepviselo torlesnel! Kepviselo:" + kepviseloazon);
        }
        }else{
        msg.setText("Az azonosító nem lehet üres!");
        logger.warn("Kepviselo torlesnel ures mezo(k)! Kepviselo:" + kepviseloazon);
        }
    }

    /**
     * Visszalépés a képviselő főmenübe.
     * @param event gombnyomás
     */
    @FXML
    void vissza(ActionEvent event) {
        
         try {
                Stage stage = (Stage) msg.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/kepviseloLogged.fxml"));
                Parent root = fl.load();
                fl.<KepviseloLoggedController>getController().initData(kepviseloazon,ud);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                stage.setTitle("Kepviseloi felulet");
                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            logger.error("Hiba a kepviseloi felulet visszalepeshez! Kepviselo:" + kepviseloazon);
            }

    }
    
    /**
     * Az ablak megnyitása előtt szükséges adatok.
     * @param azon bejelentkezett Képviselő azonosítója
     * @param db adatbáziskapcsolat
     */
    public void initData(String azon, KozosKoltsegDAO db){
        kepviseloazon = azon;
        ud = db;
    }
    
    /**
     * Az ablak megnyitása előtt az adatbáziskapcsolat felállítása.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        kozoskolts.setText(Integer.toString(ud.readTarsashaz(1).getKozoskoltseg()));
        
        tcim.setText(ud.readTarsashaz(1).getCim());

    }


}

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
import model.LakoValidator;
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;
import model.Lako;

/**
 * A bejelentkezett képviselő lakó menüjének grafikus megjelenítésére szolgáló vezérlőosztály.
 */
public class KepviseloLoggedLakoController{
	
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
     * Emelet kereséséhez szükséges szövegdoboz.
     */
    @FXML
    private TextField emk;

    /**
     * Lakás kereséséhez szükséges szövegdoboz.
     */
    @FXML
    private TextField lakk;

    /**
     * Visszatérési üzenet szöveg.
     */
    @FXML
    private Label msg;

    /**
     * Talált emelethez szövegdoboz.
     */
    @FXML
    private TextField emt;

    /**
     * Talált lakáshoz szövegdoboz.
     */
    @FXML
    private TextField lakt;

    /**
     * Talált telefonszámhoz szövegdoboz.
     */
    @FXML
    private TextField telt;

    /**
     * Talált befizetéshez szövegdoboz.
     */
    @FXML
    private TextField beft;

    /**
     * Talált jelszóhoz szövegdoboz.
     */
    @FXML
    private PasswordField jelszt;
    
    /**
     * Új jelszóhoz szövegdoboz.
     */
    @FXML
    private PasswordField jelszr;
    
    /**
     * Új lakáshoz szövegdoboz.
     */
    @FXML
    private TextField lakr;
    
    /**
     * Új emelethez szövegdoboz.
     */
    @FXML
    private TextField emr;
    
    /**
     * Bejelentkezett képviselő azonosítója.
     */
    private String kepviseloazon;
    
    /**
     * Adatbáziskapcsolat.
     */
    private KozosKoltsegDAO ud;
    
    /**
     * Talált {@code Lako} objektum.
     */
    private Lako current;

    /**
     * Segédmetódus visszaadja hogy egy {@code String} csak számokat tartalmaz-e.
     * @param s egy {@code String} objektum
     * @return hogy a {@code String} csak számokat tartalmaz-e
     */
    public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");}
    
    /**
     * Visszalépés a fő képviselői menübe.
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
            logger.error("Hiba a Kepviselo menube valo visszalepesnel:" + ex.getMessage());
            }
        }
    
    /**
     * Egy {@code Lako} törléséhez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void torol(ActionEvent event){
        if(emt.getText().isEmpty() || !isNumeric(emt.getText()) || lakt.getText().isEmpty() || !isNumeric(lakt.getText()))
        {
            msg.setText("Az emelet és/vagy a lakás mező nem lehet üres!");
            logger.warn("Lakas torlesnel ures mezo(k)! Kepviselo:" + kepviseloazon);
        } else if(ud.findLako(Integer.parseInt(emt.getText()), Integer.parseInt(lakt.getText())).isEmpty()){
            msg.setText("Nem létező lakó!");
            logger.warn("Lako torlesnel nem letezo lako! Kepviselo:" + kepviseloazon);
        } else{
            Lako l = ud.findLako(Integer.parseInt(emt.getText()), Integer.parseInt(lakt.getText())).get(0);
            ud.deleteLako(l);
            msg.setText("Sikeres törlés!");
            logger.info("Sikeres lako torles! Kepviselo:" + kepviseloazon);
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
            logger.error("Hiba a kilepesnel:" + ex.getMessage());
        }
       stage.close();

    }
    
    /**
     * Egy {@code Lako} módosításához szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void modositas(ActionEvent event){
        
        if(emt.getText().isEmpty() || !isNumeric(emt.getText()) || lakt.getText().isEmpty() || !isNumeric(lakt.getText()) ||
           telt.getText().isEmpty() || !isNumeric(telt.getText()) || beft.getText().isEmpty() || !isNumeric(beft.getText()) ||
            jelszt.getText().isEmpty() || jelszt.getText().length() > 20){
            
            msg.setText("Hibás vagy üres mezők!");
            logger.warn("Lako modositasnal hibas vagy ures mezok! Kepviselo:" + kepviseloazon);
        }else if(!ud.findLako(Integer.parseInt(emt.getText()), Integer.parseInt(lakt.getText())).isEmpty()){
           current = ud.findLako(Integer.parseInt(emt.getText()), Integer.parseInt(lakt.getText())).get(0);
           ud.updateLako(current, Integer.parseInt(emt.getText()), Integer.parseInt(lakt.getText()), jelszt.getText(),
                   Integer.parseInt(beft.getText()), Integer.parseInt((telt.getText())));
           msg.setText("Sikeres módosítás!");
           logger.info("Sikeres lako modositas! Kepviselo:"+kepviseloazon);
        }else{
            msg.setText("Nincs ilyen Lakó!");
            logger.warn("Lako modositasnal nem letezo lako! Kepviselo:" + kepviseloazon);
        }
    }
    
    /**
     * Egy {@code Lako} hozzáadásához szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void hozzaad(ActionEvent event){
        if(emr.getText().isEmpty() || !isNumeric(emr.getText()) || lakr.getText().isEmpty() || !isNumeric(lakr.getText())
        || jelszr.getText().isEmpty() || jelszr.getText().length() > 20){
            logger.warn("Lako hozzaadasnal hibas mezok! Kepviselo:" + kepviseloazon);
        	msg.setText("A hozzáadásnál a mezők hibásak/üresek vagy a jelszó túl hosszú! (max 20 karakter)");
        }else{
            LakoValidator v = new LakoValidator(ud);
            if(v.regValidate(Integer.parseInt(emr.getText()),Integer.parseInt(lakr.getText())) ){
                ud.createLako(Integer.parseInt(emr.getText()), Integer.parseInt(lakr.getText()), jelszr.getText());
                msg.setText("Sikeres hozzáadás!");
                logger.info("Sikeres lako hozzaadas! Kepviselo:"+kepviseloazon);
            } else{
                msg.setText("Már létező Lakó!");
                logger.warn("Lako hozzaadasnal mar letezo lako! Kepviselo:"+kepviseloazon);
            }
        }
    }
    
    /**
     * Egy {@code Lako} kereséséhez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void keres(ActionEvent event){
        if(lakk.getText().isEmpty() || emk.getText().isEmpty() || !isNumeric(lakk.getText()) || !isNumeric(emk.getText()) ){
            
            msg.setText("Üres vagy hibásan megadott mezők a kereséshez!");
            logger.warn("Lako keresesnel ures vagy hibas mezok! Kepviselo: "+kepviseloazon);

        }
        else if(ud.findLako(Integer.parseInt(emk.getText()), Integer.parseInt(lakk.getText())).size() == 0){
            
            msg.setText("Nincs találat!");
            logger.warn("Lako keresesnel nincs talalat! Kepviselo:"+kepviseloazon);
            
        }else{
            
            current = ud.findLako(Integer.parseInt(emk.getText()), Integer.parseInt(lakk.getText())).get(0);
            
            emt.setText(Integer.toString(current.getEmelet()));
            lakt.setText(Integer.toString(current.getLakas()));
            telt.setText(Integer.toString(current.getTelefonszam()));
            beft.setText(Integer.toString(current.getBefizetett()));
            jelszt.setText(current.getJelszo());
            
            msg.setText("Sikeres keresés!");
            logger.info("Sikeres lako kereses! Kepviselo:"+kepviseloazon);
            
        }
    }
    
    /**
     * Az ablak megnyitása előtt az adatok átadásához szükséges metódus.
     * @param azon képviselő azonosítója
     * @param db adatbáziskapcsolat
     */
    public void initData(String azon, KozosKoltsegDAO db){
        kepviseloazon = azon;
        ud = db;
    }

}

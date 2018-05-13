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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;
import model.Lako;

/**
 * A bejelentkezett lakó grafikus megjelenítésére szolgáló vezérlőosztály.
 */
public class LakoLoggedController{
	/**
	 * A naplózáshoz szükséges {@code Logger}.
	 */
	private static Logger logger = LoggerFactory.getLogger(LakoLoggedController.class);

	/**
	 * Az üdvözlő üzenethez szükséges szöveg.
	 */
    @FXML
    private Label welcome;

    /**
     * A jelenlegi üzenethez szükséges szöveg.
     */
    @FXML
    private Label jelenlegiuzenet;

    /**
     * A jelszó módosításhoz való gomb.
     */
    @FXML
    private Button jelszomodosit;

    /**
     * A befizetéshez való gomb.
     */
    @FXML
    private Button befizet;

    /**
     * A befizetett összeghez való szöveg.
     */
    @FXML
    private Label befizetett;

    /**
     * A tartozáshoz való szöveg.
     */
    @FXML
    private Label tartozas;
    
    /**
     * A közösköltséghez való szöveg.
     */
    @FXML
    private Label kozoskoltseg;
    
    /**
     * Az összeghez való szövegdoboz.
     */
    @FXML
    private TextField osszeg;
    
    /**
     * Az új jelszóhoz való szövegdoboz.
     */
    @FXML
    private TextField ujjelszo;
    
    /**
     * A kilépéshez való gomb.
     */
    @FXML
    private Button kilep;
    
    /**
     * A kijelentkezéshez való gomb.
     */
    @FXML
    private Button kijelentkez;
    
    /**
     * A jelenlegi emelethez való szöveg.
     */
    @FXML
    private Label jemelet;

    /**
     * A jelenlegi lakáshoz való szöveg.
     */
    @FXML
    private Label jlakas;

    /**
     * A jelenlegi telefonszámhoz való szöveg.
     */
    @FXML
    private Label jtelszam;
    
    /**
     * Az új telefonszámhoz szükséges szövegdoboz.
     */
    @FXML
    private TextField ujtel;
    
    /**
     * A jelenlegi emelet.
     */
    private int emelet;
    
    /**
     * A jelenlegi lakás.
     */
    private int lakas;
    
    /**
     * Adatbáziskapcsolat.
     */
    private KozosKoltsegDAO ud;
    
    /**
     * Segédmetódus ami visszaadja hogy egy {@code String} csak számokat tartalmaz-e.
     * @param s egy {@code String} objektum
     * @return hogy egy {@code String} csak számokat tartalmaz-e
     */
    public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");}

    /**
     * A befizetéshez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void befizetes(ActionEvent event) {
    if(osszeg.getText().isEmpty()){
        osszeg.setText("A mező nem lehet üres!");
        logger.warn("Ures mezok a befizetesnel! Lako:"+ emelet+","+ lakas);}
    else if(!isNumeric(osszeg.getText())){
        osszeg.setText("Csak számok!");
        logger.warn("Befizetesnel nem szam karakterek! Lako:"+emelet+","+lakas);
        }
    else{
       Lako l = ud.findLako(emelet, lakas).get(0);
       int befizetett = Integer.parseInt(osszeg.getText());
       ud.updateLako(l, emelet, lakas, l.getJelszo(), l.getBefizetett()+befizetett, l.getTelefonszam());
       osszeg.setText("Sikeres befizetés!");
       logger.info("Sikeres befizetes! Lako:"+emelet+","+lakas);
       initData(emelet, lakas,ud);
    }
    }

    /**
     * A jelszómódosításhoz szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void jelszomodositas(ActionEvent event) {
        if(ujjelszo.getText().length() > 20){
        ujjelszo.setText("Maximum 20 karakter!");
        logger.warn("Jelszo modositasnal tobb mint 20 karakter! Lako:"+emelet+","+lakas);
        }
        else if(ujjelszo.getText().isEmpty()){
        ujjelszo.setText("A mező nem lehet üres!");
        logger.warn("Jelszo modositasnal ures szovegdoboz! Lako:"+emelet+","+lakas);
        }else if(ujjelszo.getText().contentEquals("Új jelszó") || ujjelszo.getText().contentEquals("Maximum 20 karakter!") || ujjelszo.getText().contentEquals("A mező nem lehet üres!")){
        ujjelszo.setText("");
        }
        else{
         Lako l = ud.findLako(emelet, lakas).get(0);
        ud.updateLako(l, emelet, lakas, ujjelszo.getText(), l.getBefizetett(), l.getTelefonszam());
        ujjelszo.setText("Sikeres változtatás!");
        logger.info("Sikeres jelszo valtoztatas! Lako:"+emelet+","+lakas);
        }
        
        
    }
    
    /**
     * Az ablak megnyitása előtt adatok átadásához szükséges metódus.
     * @param em bejelentkezett lakó lakásának emelete
     * @param lak bejelentkezett lakó lakásának házszáma
     * @param db adatbáziskapcsolat
     */
    public void initData(int em, int lak, KozosKoltsegDAO db) {
       emelet = em;
       lakas = lak;
       ud = db;
       Lako l = ud.findLako(emelet, lakas).get(0);
       int k = ud.readTarsashaz(1).getKozoskoltseg();
       befizetett.setText(Integer.toString(l.getBefizetett()) + "Ft");
       tartozas.setText(Integer.toString((k*12)-l.getBefizetett()) + "Ft");
       kozoskoltseg.setText(Integer.toString(k) + "Ft");
       jelenlegiuzenet.setText(ud.readTarsashaz(1).getUzenet());
       jemelet.setText("Emelet: " + l.getEmelet());
       jlakas.setText("Lakas: " + l.getLakas());
       jtelszam.setText("Tel. szám: " + l.getTelefonszam());
    }
    
    /**
     * A telefonszám módosításához szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void telszammod(ActionEvent event){
    
        if(ujtel.getText().isEmpty()){
            ujtel.setText("A mező nem lehet üres");
            logger.warn("Tel. szam modositasnal ures mezok! Lako:"+lakas+","+emelet);
        }
        else if(!isNumeric(ujtel.getText())){
            ujtel.setText("Csak számok!");
            logger.warn("Tel. szam modositasnal nem csak szam karakterek! Lako:"+emelet+","+lakas);
        }
        else{
            Lako l = ud.findLako(emelet, lakas).get(0);
            ud.updateLako(l, emelet, lakas, l.getJelszo(), l.getBefizetett(), Integer.parseInt(ujtel.getText()));
            ujtel.setText("Sikeres változtatás!");
            logger.info("Sikeres tel. szam modositas! Lako:"+emelet+","+lakas);
            initData(emelet, lakas,ud);
        }
    
    }
    
    /**
     * A kijelentkezéshez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void kijelentkezes(ActionEvent event){
    Stage stage = (Stage) kijelentkez.getScene().getWindow();
    try {
   	 	FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
        Parent root = fl.load();
        fl.<MainScreenController>getController().initData(ud);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("KozosKoltseg fomenu");
        stage.setScene(scene);
        stage.show();
        }
        catch (Exception ex) {
            logger.error("Hiba a kijelentkezes kozben:" + ex.getMessage());

        }
    }
    
    /**
     * A kilépéshez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void kilepes(ActionEvent event){
           Stage stage = (Stage) kilep.getScene().getWindow();
        try {
            KozosKoltsegDAOFactory.getInstance().close();
        } catch (Exception ex) {
            logger.error("Hiba a kilepes kozben:"+ ex.getMessage());
        }
       stage.close();
        }
    }


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
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;
import model.LakoValidator;

/**
 * A lakó bejelentkezés grafikus megjelenítésére szolgáló vezérlőosztály.
 */
public class LakoLoginController{
	/**
	 * A naplózáshoz szükséges {@code Logger}.
	 */
	private static Logger logger = LoggerFactory.getLogger(LakoLoginController.class);

	/**
	 * Az emelethez szükséges szövegdoboz.
	 */
    @FXML
    private TextField emelet;

    /**
     * A vissza gomb.
     */
    @FXML
    private Button vissza;

    /**
     * A kilépés gomb.
     */
    @FXML
    private Button exit;

    /**
     * A visszatérési üzenethez szöveg.
     */
    @FXML
    private Label msg;

    /**
     * A lakáshoz szövegdoboz.
     */
    @FXML
    private TextField lakas;

    /**
     * A jelszóhoz szövegdoboz.
     */
    @FXML
    private PasswordField jelszo;
    
    /**
     * Adatbáziskapcsolat.
     */
    private KozosKoltsegDAO ud;
    
    /**
     * A belépéshez való gomb.
     */
    @FXML
    private Button belep;

    /**
     * A belépéshez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void belepes(ActionEvent event) {
        
        if(!emelet.getText().isEmpty() && !lakas.getText().isEmpty() && !jelszo.getText().isEmpty()){
        try {
            
            LakoValidator v = new LakoValidator(ud);   
            
            if (v.loginValidate(Integer.parseInt(emelet.getText()),Integer.parseInt(lakas.getText()), jelszo.getText())) {
                Stage stage = (Stage) msg.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/lakoLogged.fxml"));
                Parent root = fl.load();
                fl.<LakoLoggedController>getController().initData(Integer.parseInt(emelet.getText()),Integer.parseInt(lakas.getText()),ud);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                stage.setTitle("Kepviseloi felulet");
                stage.setScene(scene);
                stage.show();
            } else {
                msg.setText("Sikertelen belépés!");
                logger.warn("Sikertelen lako bejelentkezes!");
            }

        } catch (IOException ex) {
            logger.error("Hiba a lako bejelentkezes kozben:" + ex.getMessage());
        }}else{
         msg.setText("Egyik mező sem maradhat üresen!");
         logger.warn("Ures mezok a lako bejelentkezesnel!");
        }
    }
    
    /**
     * A kilépéshez való metódus.
     * @param event gombnyomás
     */
    @FXML
    void exit(ActionEvent event) {
       Stage stage = (Stage) exit.getScene().getWindow();
        try {
            KozosKoltsegDAOFactory.getInstance().close();
        } catch (Exception ex) {
            logger.error("Hiba a kilepes kozben:"+ex.getMessage());
        }
       stage.close();
    }

    /**
     * A visszalépéshez való metódus.
     * @param event gombnyomás
     */
    @FXML
    void vissza(ActionEvent event) {
    	try {
            Stage stage = (Stage) exit.getScene().getWindow();
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
                logger.error("Hiba a fomenube visszalepes soran:" + ex.getMessage());

            }
    }
    
    /**
     * Az ablak megnyitása előtt adatok átadásához való metódus.
     * @param db adatbáziskapcsolat
     */
    public void initData(KozosKoltsegDAO db) {
    	ud = db;
    	
    }


}

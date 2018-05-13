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
import model.KepviseloValidator;
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;

/**
 * A képviselő bejelentkezés grafikus megjelenítésére szolgáló vezérlőosztály.
 */
public class KepviseloLoginController{
	/**
	 * A naplózáshoz szükséges {@code Logger}.
	 */
	private static Logger logger = LoggerFactory.getLogger(KepviseloLoginController.class);

	/**
	 * Az azonosítóhoz szövegdoboz.
	 */
    @FXML
    private TextField azon;

    /**
     * A visszalépéshez való gomb.
     */
    @FXML
    private Button vissza;

    /**
     * A kilépéshez való gomb.
     */
    @FXML
    private Button exit;

    /**
     * A visszatérési üzenethez való szöveg.
     */
    @FXML
    private Label msg;

    /**
     * A jelszóhoz való szövegdoboz.
     */
    @FXML
    private PasswordField jelszo;

    /**
     * A belépéshez való gomb.
     */
    @FXML
    private Button belep;
    
    /**
     * Adatbáziskapcsolat.
     */
    private KozosKoltsegDAO ud;

    /**
     * Belépéshez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void belep(ActionEvent event) {
        if(!azon.getText().isEmpty() && !jelszo.getText().isEmpty()){
        try {
            
            KepviseloValidator v = new KepviseloValidator(ud);   
            System.out.println("Azonosito: " + azon.getText());
            System.out.println("Jelszo: " + jelszo.getText());
            
            if (v.loginValidate(azon.getText(), jelszo.getText())) {
                Stage stage = (Stage) msg.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/kepviseloLogged.fxml"));
                Parent root = fl.load();
                fl.<KepviseloLoggedController>getController().initData(azon.getText(),ud);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                stage.setTitle("Kepviseloi felulet");
                stage.setScene(scene);
                stage.show();
            } else {
                msg.setText("Sikertelen belépés!");
            }

        } catch (IOException ex) {
            logger.error("Hiba a Kepviselo belepes ablak nyitasanal:"+ex.getMessage());
        }}else{
            msg.setText("Egyik mező sem maradhat üresen!");
            logger.warn("Kepviselo belepesnel ures mezok!");
        }
    }

    /**
     * Kilépéshez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void exit(ActionEvent event) {
       Stage stage = (Stage) exit.getScene().getWindow();
        try {
            KozosKoltsegDAOFactory.getInstance().close();
        } catch (Exception ex) {
            logger.error("Hiba a kilepesnel:"+ex.getMessage());
        }
       stage.close();
    }

    /**
     * Visszalépéshez szükséges metódus.
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
                logger.error("Hiba a visszalepesnel:"+ex.getMessage());
            }
    }
    
    /**
     * Az ablak megnyitása előtt adatok átadásához szükséges metódus.
     * @param db adatbáziskapcsolat
     */
    public void initData(KozosKoltsegDAO db) {
    	ud = db;
    	
    }
    

}

package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Kepviselo;
import model.KepviseloValidator;
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;
import model.Tarsashaz;

/**
 * A bejelentkezett képviselő grafikus megjelenítésére szolgáló vezérlőosztály.
 */
public class KepviseloLoggedController {

    /**
     * Az üdvözlő szöveg.
     */
    @FXML
    private Label welcome;

    /**
     * Az üzenet módosításhoz szükséges gomb.
     */
    @FXML
    private Button uzenetm;

    /**
     * A tábla megnézéséhez szükséges gomb. 
     */
    @FXML
    private Button tablanez;

    /**
     * Lakó kereséséhez szükséges gomb.
     */
    @FXML
    private Button lakokeres;

    /**
     * Az üzenet módosításához szükséges szövegdoboz.
     */
    @FXML
    private TextField uzenetbox;
    
    /**
     * Kijelentkezéshez szükséges gomb.
     */
    @FXML
    private Button kijelentkez;
    
    /**
     * Lakó menühöz szükséges gomb.
     */
    @FXML
    private Button lakom;
    
    /**
     * Képviselő menühöz szükséges gomb.
     */
    @FXML
    private Button kepviselom;

    /**
     * Kilépéshez szükséges homb.
     */
    @FXML
    private Button kilep;
    
    /**
     * Visszatérési üzenetekhez szükséges szöveg.
     */
    @FXML
    private Label msg;
    
    /**
     * Jelenlegi bejelentkezess képviselő azonosítója.
     */
    private String kepviseloazon;
    /**
     * Adatbáziskapcsolat.
     */
    private KozosKoltsegDAO ud;
    /**
     * Jelenlegi bejelentkezett képviselő.
     */
    private Kepviselo kepviselo;

    /**
     * A naplózáshoz szükséges {@code Logger}.
     *
     */
	private static Logger logger = LoggerFactory.getLogger(KepviseloLoggedController.class);
    /**
     * A közösköltség tábla megtekintése.
     * @param event gombnyomás
     */
	@FXML
    void tablamegnez(ActionEvent event) {
        
                try {
                Stage stage = (Stage) msg.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/kepviseloLoggedTabla.fxml"));
                Parent root = fl.load();
                fl.<KepviseloLoggedTablaController>getController().initData(kepviseloazon,ud);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                stage.setTitle("Kepviseloi Tabla nezet");
                stage.setScene(scene);
                stage.show();

        } catch (IOException ex) {
            logger.error("Hiba a tablamegnezes kozben:" + ex.getMessage());
        }

    }

    /**
     * Üzenet módosítása.
     * @param event gombnyomás
     */
    @FXML
    void uzenetmodositas(ActionEvent event) {
        
        String ujUzenet = uzenetbox.getText();
        Tarsashaz t = ud.readTarsashaz(1);
        try{
        ud.updateTarsashaz(t, t.getCim(), ujUzenet, t.getKozoskoltseg());
        }catch(Exception e){
        msg.setText(e.getMessage());
        logger.error("Hiba uzenetmodositas kozben:" + e.getMessage());
        }
        
        msg.setText("Az üzenet sikeresen módosítva!");
        logger.info("A képviselői üzenet sikeresen módosítva! Képviselő:"+ kepviseloazon );
        
    }

    /**
     * Képviselői menü megnyitása.
     * @param event gombnyomás
     */
    @FXML
    void kepviselomenu(ActionEvent event) {
        
        try {
                Stage stage = (Stage) msg.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/kepviseloLoggedKepviselo.fxml"));
                Parent root = fl.load();
                fl.<KepviseloLoggedKepviseloController>getController().initData(kepviseloazon,ud);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                stage.setTitle("Kepviseloi Kepviselo felulet");
                stage.setScene(scene);
                stage.show();

        } catch (IOException ex) {
            logger.error("Hiba a kepviseloi menu megnyitasa kozben: ",ex.getMessage());
        }
    }

    /**
     * Kijelentkezés az ablakból.
     * @param event gombnyomás
     */
    @FXML
    void kijelentkezes(ActionEvent event) {
        try {
        Stage stage = (Stage) kijelentkez.getScene().getWindow();
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
            logger.error("Hiba a kijelentkezes kozben:"+ ex.getMessage());

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
            logger.error("Hiba a kilepes kozben:"+ ex.getMessage());
        }
       stage.close();
    }

    /**
     * A lakó menübe átlépés.
     * @param event gombnyomás
     */
    @FXML
    void lakomenu(ActionEvent event) {
        
        try {
                Stage stage = (Stage) msg.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/kepviseloLoggedLako.fxml"));
                Parent root = fl.load();
                fl.<KepviseloLoggedLakoController>getController().initData(kepviseloazon,ud);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                stage.setTitle("Kepviseloi Lako felulet");
                stage.setScene(scene);
                stage.show();

        } catch (IOException ex) {
            logger.error("Hiba a lako menu megnyitasa kozben:" + ex.getMessage());
        }
    }

    /**
     * Adatok átadása az ablakba való átlépés előtt.
     * @param azon képviselő azonosítója
     * @param db adatbáziskapcsolat
     */
    public void initData(String azon, KozosKoltsegDAO db) {
        kepviseloazon = azon;
        ud = db;
        welcome.setText("Bejelentkezett: " + ud.findKepviselo(azon).get(0).getNev());
        uzenetbox.setText(ud.readTarsashaz(1).getUzenet());
        
    }
}
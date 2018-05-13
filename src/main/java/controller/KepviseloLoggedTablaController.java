package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.KozosKoltsegDAO;
import model.KozosKoltsegDAOFactory;
import model.Lako;

/**
 * A bejelentkezett képviselő közösköltségtábla grafikus megjelenítésére szolgáló vezérlőosztály.
 */
public class KepviseloLoggedTablaController {
	/**
	 * A naplózáshoz szükséges {@code Logger}.
	 */
	private static Logger logger = LoggerFactory.getLogger(KepviseloLoggedTablaController.class);

	/**
	 * A táblázat.
	 */
    @FXML
    private TableView<Lako> tabla;
    
    /**
     * A táblázat emelet oszlopa.
     */
    @FXML
    private TableColumn<Lako, Integer> em;

    /**
     * A táblázat lakás oszlopa.
     */
    @FXML
    private TableColumn<Lako, Integer> lak;

    /**
     * A táblázat befizetett oszlopa.
     */
    @FXML
    private TableColumn<Lako, Integer> bef;

    /**
     * A táblázat tartozás oszlopa.
     */
    @FXML
    private TableColumn<Lako, Integer> tart;

    /**
     * Kilépés gomb.
     */
    @FXML
    private Button kilep;
    
    /**
     * Adatbáziskapcsolat.
     */
    private KozosKoltsegDAO ud;
    
    /**
     * Bejelentkezett {@code Kepviselo} azonosítója.
     */
    private String kepviseloazon;

    /**
     * A táblázat frissítéséhez szükséges metódus.
     * @param event gombnyomás
     */
    @FXML
    void frissites(ActionEvent event) {
    tabla.setItems(lakok());
    }

    /**
     * Kilépés az alkalmazásból.
     * @param event gombnyomás.
     */
    @FXML
    void kilepes(ActionEvent event) {
        
    Stage stage = (Stage) kilep.getScene().getWindow();
        try {
            KozosKoltsegDAOFactory.getInstance().close();
        } catch (Exception ex) {
            logger.error("Hiba a kilepesnel:"+ex.getMessage());
        }
       stage.close();

    }

    /**
     * Visszalépés a képviselői főmenübe.
     * @param event gombnyomás
     */
    @FXML
    void vissza(ActionEvent event) {
        
         try {
                Stage stage = (Stage) kilep.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/kepviseloLogged.fxml"));
                Parent root = fl.load();
                fl.<KepviseloLoggedController>getController().initData(kepviseloazon,ud);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                stage.setTitle("Kepviseloi felulet");
                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            logger.error("Hiba a kepviseloi visszalepeshez:"+ex.getMessage());
            }

    }
    /**
     * A táblázathoz szükséges {@code ObservableList} objektum ami a lakók listáját tartalmazza.
     * @return lakók listája
     */
    public ObservableList<Lako> lakok(){
        List<Lako> lakok = ud.getOsszLako();
       ObservableList<Lako> l = FXCollections.observableArrayList(lakok);
       return l;
    }
    
    /**
     * Az ablak megnyitás előtt adatok átadására szolgáló metódus.
     * @param azon a bejelentkezett {@code Kepviselo} azonosítója
     * @param db adatbáziskapcsolat
     */
    public void initData(String azon, KozosKoltsegDAO db){
        kepviseloazon = azon;
        ud = db;
        int osszkozoskoltseg = ud.readTarsashaz(1).getKozoskoltseg() * 12;
        
        em.setCellValueFactory(new PropertyValueFactory<Lako, Integer> ("emelet"));
        lak.setCellValueFactory(new PropertyValueFactory<Lako, Integer> ("lakas"));
        bef.setCellValueFactory(new PropertyValueFactory<Lako, Integer> ("befizetett"));
        tart.setCellValueFactory(c -> new SimpleIntegerProperty(osszkozoskoltseg - c.getValue().getBefizetett()).asObject());
        
        tabla.setItems(lakok());
    }

}

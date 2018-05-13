package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import model.KozosKoltsegDAOFactory;
import model.KozosKoltsegDAO;

/**
 * A főmenü grafikus megjelenítéséhez szükséges vezérlőosztály.
 */
public class MainScreenController implements Initializable {
	/**
	 * A naplózáshoz szükségeg {@code Logger}.
	 */
	private static Logger logger = LoggerFactory.getLogger(MainScreenController.class);

	/**
	 * A lakó választáshoz való gomb.
	 */
	@FXML
	private Button lako;

	/**
	 * A képviselő választáshoz való gomb.
	 */
	@FXML
	private Button kepviselo;

	/**
	 * A kilépéshez való gomb.
	 */
	@FXML
	private Button exitbutton;

	/**
	 * Adatbáziskapcsolat.
	 */
	private KozosKoltsegDAO ud;

	/**
	 * A kilépéshez való metódus.
	 * @param event gombnyomás
	 */
	@FXML
	void exit(ActionEvent event) {
		try {
			KozosKoltsegDAOFactory.getInstance().close();
		} catch (Exception e) {
			logger.error("Hiba a kilepesnel:"+e.getMessage());
		}
		Stage stage = (Stage) exitbutton.getScene().getWindow();
		stage.close();
	}

	/**
	 * A képviselő belépés átirányításához való gomb.
	 * @param event gombnyomás
	 */
	@FXML
	void kepviseloBelep(ActionEvent event) {
		try {
			Stage stage = (Stage) kepviselo.getScene().getWindow();
			FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/kepviseloLogin.fxml"));
			Parent root = fl.load();
			fl.<KepviseloLoginController>getController().initData(ud);

			Scene scene = new Scene(root);
			scene.getStylesheets().add("/styles/Styles.css");

			stage.setTitle("Kepviselo belepes");
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			logger.error("Hiba a kepviselo belepesnel:"+ex.getMessage());
		}
	}

	/**
	 * A lakó belépés átirányításához való gomb.
	 * @param event gombnyomás
	 */
	@FXML
	void lakoBelep(ActionEvent event) {

		try {
			Stage stage = (Stage) lako.getScene().getWindow();
			FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/lakoLogin.fxml"));
			Parent root = fl.load();
			fl.<LakoLoginController>getController().initData(ud);

			Scene scene = new Scene(root);
			scene.getStylesheets().add("/styles/Styles.css");

			stage.setTitle("Lako belepes");
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			logger.error("Hiba a lako belepesenel:"+ex.getMessage());
		}

	}

	/**
	 * Az ablak megnyitása előtt adatok átadásához való metódus.
	 * @param db adatbáziskapcsolat
	 */
	public void initData(KozosKoltsegDAO db) {

		ud = db;
	}

	/**
	 * Az inicializálás a program megnyitásakor.
	 * @param url url
	 * @param rb rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ud = KozosKoltsegDAOFactory.getInstance().createKozosKoltsegDAO();
	}

}

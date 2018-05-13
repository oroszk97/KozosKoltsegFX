package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.KozosKoltsegDAOFactory;

/**
 * 
 * A program megnyitására szolgáló osztály.
 *
 */
public class MainApp extends Application {
	/**
	 * A naplózáshoz szükséges {@code Logger}.
	 */
	private static Logger logger = LoggerFactory.getLogger(MainApp.class);

    /**
     * Ezzel a metódussal indul el a programunk grafikus felülete.
     * 
     * @param stage egy {@code JavaFX} ablak
     * @throws Exception Olvasási hibakor
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Kozoskoltseg fofelulet");
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(e->closeApp());
    }

    /**
     * Az alkalmazás indítása.
     * 
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * A program bezárása után bezárja az adatbáziskapcsolatot.
     */
     private void closeApp() {
        try {
            KozosKoltsegDAOFactory.getInstance().close();
        } catch (Exception ex) {
            logger.error("Hiba az alkalmazas bezarasakor:"+ex.getMessage());
        }
    }

}

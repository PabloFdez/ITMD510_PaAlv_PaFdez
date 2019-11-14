package models;

import java.io.IOException;
//import java.util.logging.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.StartPage;

public class ToolKit {	
	public void newWindow(String PanelNuevo) {
		//application.Login.LOGGER.log(Level.INFO, PanelNuevo);
		AnchorPane root = null;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/ventanaAyuda/"+ PanelNuevo));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage secondaryStage = new Stage();
		Scene scene = new Scene(root, 640, 480);
		secondaryStage.setResizable(false);
		secondaryStage.setTitle("Help");
		secondaryStage.setScene(scene);
		secondaryStage.show();
	}

	public static void TKClose() {
		//close program
		javafx.application.Platform.exit();
		System.exit(0);
	}

	public static void TKSession() {
		//close session
		login.StartPage m = new StartPage();
		m.relaunch();
	}

	public static void TKHelp() {
		//general help
	}

	public static void TKSpecificHelp(String path) {

	}

	public static void TKAboutUs() {

	}
}
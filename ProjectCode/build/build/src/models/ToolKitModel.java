package models;

import java.io.IOException;

import application.StartPage;
//import java.util.logging.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Pablo Angel Alvarez Fernandez
 * @author Pablo Fernandez Diaz
 * ToolKitModel class
 * Methods for the top nav bar
 */
public class ToolKitModel {
	String newPanel="";
	public void newWindow() {
		//application.Login.LOGGER.log(Level.INFO, PanelNuevo);
		AnchorPane root = null;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/help/"+ newPanel));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage secondaryStage = new Stage();
		Scene scene = new Scene(root);
		secondaryStage.setResizable(false);
		secondaryStage.setTitle(newPanel.substring(0, newPanel.length()-5));
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
		application.StartPage m = new StartPage();
		m.relaunch();
	}

	public void TKHelp() {
		//general help
		newPanel = "GeneralHelp.fxml";
		newWindow();
	}

	public void TKSpecificHelp() {
		int type = controllers.LoginController.AClog.getMemberType();
		if(type == 0){
			//help std
			newPanel = "StudentHelp.fxml";
			newWindow();
		} else if(type == 1) {
			//help prof
			newPanel = "ProfessorHelp.fxml";
			newWindow();
		}
	}

	public void TKAboutUs() {
		newPanel = "AboutUs.fxml";
		newWindow();
	}
}

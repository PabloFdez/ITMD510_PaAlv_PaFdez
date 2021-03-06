package controllers;


import Dao.DaoModel;
import application.Professor;
import application.Student;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.DataModel;
import models.ToolKitModel;

/**
 * @author Pablo Angel Alvarez Fernandez
 * @author Pablo Fernandez Diaz
 * IndexAdminController class
 * Iniciates the admin page
 */
public class IndexAdminController extends Application {
	@FXML private TextField eFN;
	@FXML private TextField eLN;
	@FXML private TextField eUs;
	@FXML private TextField eMaj;
	@FXML private TextField ePass;
	@FXML private TextField pFN;
	@FXML private TextField pLN;
	@FXML private TextField pUs;
	@FXML private TextField pDep;
	@FXML private TextField pOf;
	@FXML private TextField pPass;
	
	@Override
	public void start(Stage secondaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/views/admin/IndexAdmin.fxml"));
			Scene scene = new Scene(root,640, 480);
			secondaryStage.setTitle("Admin Site");
			secondaryStage.setScene(scene);
			secondaryStage.setMaximized(true);
			secondaryStage.show();
		} catch(Exception e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
	}
	
	// register a student
	public void regStudent() {
		if ((eFN.getText().length() <= 0) || (eLN.getText().length() <= 0) || 
				(eUs.getText().length()) <= 0 || (ePass.getText().length()) <= 0 || eMaj.getText().length()<=0) {
			DataModel.sendAlert("Save error", "Please review the data");
		} else {
			Student s1 = new Student(eFN.getText(), eLN.getText(), eUs.getText(), eMaj.getText());
			String hashPass = models.HashingModel.hash(ePass.getText());
			String sql = "Update papf_students SET ePassword = '"+hashPass+"'  WHERE eEMail = '"+s1.getEmail()+"';";
			DaoModel.insertStudent(s1);
			DaoModel.QueryUpd(sql);
		}
	}
	
	// register a professor
	public void regProfessor() {
		if ((pFN.getText().length() <= 0) || (pLN.getText().length() <= 0) || 
				(pUs.getText().length()) <= 0 || (pPass.getText().length()) <= 0 || pDep.getText().length()<=0 || pOf.getText().length()<=0 || !DataModel.isNumeric(pOf.getText())) {
			DataModel.sendAlert("Save error", "Please review the data");
		} else {
			Professor p1 = new Professor(pFN.getText(), pLN.getText(), pUs.getText(), pDep.getText(), Integer.parseInt(pOf.getText()));
			String hashPass = models.HashingModel.hash(pPass.getText());
			String sql = "Update papf_professors SET pPassword = '"+hashPass+"'  WHERE pEMail = '"+p1.getEmail()+"';";
			DaoModel.insertProfessor(p1);
			DaoModel.QueryUpd(sql);
		}
	}
	
	// toolbar methods
	public void TKClose() {
		ToolKitModel.TKClose();
	}
	
	public void TKSession() {
		ToolKitModel.TKSession();
	}
	
	public void TKHelp() {
		new ToolKitModel().TKHelp();
	}
	
	public void TKAboutUs(){
		new ToolKitModel().TKAboutUs();
	}
}


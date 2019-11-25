package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Professor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import models.DaoModel;

public class DataProfController implements Initializable{
	private Professor professor = (Professor) controllers.LoginController.AClog;
	@FXML
	private Pane ChangingPane;
	@FXML
	private Label fName;
	@FXML
	private Label lName;
	@FXML
	private Label email;
	@FXML
	private Label department;
	@FXML
	private Label office;
	@FXML
	private TextField TfName;
	@FXML
	private TextField TlName;
	@FXML
	private TextField Tdepartment;
	@FXML
	private TextField Toffice;
	@FXML
	private Button BUboton;

	public void setChangingPane(String PanelNuevo) {
		// cambiar panel pasando el nombre de la venta a la que se quiere ir
		try {
			ChangingPane.getChildren().clear();
			ChangingPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource(PanelNuevo)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EditUserData() {
		// pasar a vantana editar
		setChangingPane("../professor/EditPane.fxml");
	}

	public static boolean isNumeric(String str) {
		return str.matches("[+-]?\\d*(\\d+)?");
	}

	public void SaveEdit() {
		// mandar datos correctos a la bbdd

		if ((TfName.getText().length() <= 0) || (TlName.getText().length() <= 0) || 
				(Tdepartment.getText().length()) <= 0 || !isNumeric(Toffice.getText()) || Toffice.getText().length()<=0) {
			Alert dialogoAlerta = new Alert(AlertType.WARNING);
			dialogoAlerta.setTitle("Warning");
			dialogoAlerta.setHeaderText("Save error");
			dialogoAlerta.setContentText("Please review the data");
			dialogoAlerta.showAndWait();
		} else {
			// Updating DB
			DaoModel.updateProfessor(professor.getId(), TfName.getText(), TlName.getText(), Tdepartment.getText(),Integer.parseInt(Toffice.getText()));
			
			// Updating App
			professor.setFirstName(TfName.getText());
			professor.setLastName(TlName.getText());
			professor.setDept(Tdepartment.getText());
			professor.setOfficeNo(Integer.parseInt(Toffice.getText()));
			setChangingPane("../professor/DataPane.fxml");
		}
	}

	public void BackUpGeneral() {
		// mandar a la bbdd que clone la bd
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (location.toString().contains("DataPane.fxml")) {
			fName.setText(professor.getFirstName());
			lName.setText(professor.getLastName());
			email.setText(professor.getEmail());
			department.setText(professor.getDept());
			office.setText(Integer.toString(professor.getOfficeNo()));
		} else if (location.toString().contains("EditPane.fxml")) {
			TfName.setText(professor.getFirstName());
			TlName.setText(professor.getLastName());
			Tdepartment.setText(professor.getDept());
			Toffice.setText(Integer.toString(professor.getOfficeNo()));
		}
	}
}
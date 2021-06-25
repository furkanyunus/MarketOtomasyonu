package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class yrdm {



	@FXML 
	private Button yardimKpt;
	
	

	@FXML
	void yardimKpt_Click (ActionEvent event) {
		
		Node node = (Node)event.getSource();
		Stage stage = (Stage)node.getScene().getWindow();
		stage.close();
		
	}
}

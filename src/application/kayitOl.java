package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import veritabani.veritabaniUtil;

public class kayitOl {

	 @FXML
	    private Button btn_Kayit;

	    @FXML
	    private Button btn_Kapat;

	    @FXML
	    private TextField txt_sifreKydt;

	    @FXML
	    private TextField txt_kullaniciKydt;

	    @FXML
	    private TextField txt_emailKydt;

	    @FXML
	    void btn_Kapat_Click(ActionEvent event) {
	    	Node node = (Node)event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();
			
	    	
	    }
		Connection sqlbaglanti = null;
		PreparedStatement sorguIfadesi = null;
		String sql;
		
		int sqlidm = 0;

		public kayitOl() {
			sqlbaglanti = veritabaniUtil.Baglan();
		}
	
	    @FXML
	    void btn_Kayit_Click(ActionEvent event) {

	    	sql="insert into kullanicikayit(kullanici,Sifre,email) values (?,?,?)";
	    	try {
	    		sorguIfadesi=sqlbaglanti.prepareStatement(sql);
	    		sorguIfadesi.setString(1, txt_kullaniciKydt.getText());
	    		sorguIfadesi.setString(2, txt_sifreKydt.getText());
	    		sorguIfadesi.setString(3, txt_emailKydt.getText());
	    		sorguIfadesi.execute();
	    		JOptionPane.showMessageDialog(null, "saved...");
	    	}catch(Exception e) {
	    		
	    		JOptionPane.showMessageDialog(null, e);
	    	}
	    }
	
	
	 

		@FXML
		void initialize() {
			
		}
	
}

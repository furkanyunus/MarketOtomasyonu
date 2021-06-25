package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import veritabani.veritabaniUtil;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField kullaniciAdi;

    @FXML
    private Button girisButonu;

    @FXML
    private Label hataliGiris;

    @FXML
    private PasswordField sifre;

    @FXML
    private Button kapatButonu;
    @FXML
    private Button kayıt_Ol;
    
    @FXML
    private TextField txt_Abone;

    @FXML
    private TextField txt_AboneSifre;
    @FXML
    private Button btn_KullanıcıGris;
    
    
    @FXML
    private AnchorPane anchor1;
    Connection sqlbaglanti=null;
    PreparedStatement sorguIfadesi=null;
    PreparedStatement sorguIfadesi1=null;
    ResultSet getirilen=null;
    String sql;
    
    public SampleController() {
    	sqlbaglanti=veritabaniUtil.Baglan();
	}

    @FXML
    void btnGirisYap_clicked(ActionEvent event) {
    	
    	sql = "select * from login where kul_ad=? and sifre=?";
    	try {
    		sorguIfadesi=sqlbaglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, kullaniciAdi.getText().trim());
    		sorguIfadesi.setString(2, sifre.getText().trim());
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		
    	
    		if(!getirilen.next())
    		{
    			  String a="";
		    	if (kullaniciAdi.getText().equals("")) 
		    	{
		    		a+="Kullanıcı adı Boş veya Hatalı !!\n";
		    		kullaniciAdi.setStyle("-fx-border-color:red");
		    				
				}else {
					a+="Kullanıcı Adı Dogru \n";
					sifre.setStyle("-fx-border-color:red");
					
				} 
		    	 if (sifre.getText().equals("")) 
		    	{
		    		a+="Sifre  Boş veya Hatalı !!\n";
		    		sifre.setStyle("-fx-border-color:red");
		    				
				}else {
					a+="Sifre Dogru \n";
					sifre.setStyle("-fx-border-color:red");
					
				}
		    	
		    	/*if (sifre.getText().equals("")) 
		    	{
		    		m+="Şifre  Boş Girdiniz veya Hatalı!\n";
		    		sifre.setStyle("-fx-border-color:red");
		    				
				}*/
		    	
		    	/*
		    		else 
		    		{
		    			sifre.setStyle("-fx-border-color:blue");
		    		}*/
		    	hataliGiris.setText(a);

    		}
    		else {
    			
    			try {
    		    	Node node = (Node)event.getSource();
    				Stage stage = (Stage)node.getScene().getWindow();
    				stage.close();
    				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Anasayfa.fxml"));
    				Parent root1;
    				root1 = (Parent) fxmlLoader.load();
    				Stage stage1 = new Stage();
    				stage1.setScene(new Scene(root1));  
    				stage1.show();
    	    	
    	    	} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}

    	    	}
			
		} 
    	catch (Exception e) 
    	{
			
		}

    }
    
    @FXML
    void kayıt_Ol_Click(ActionEvent event) {

    	try {
    		Stage stage1=new Stage();
			AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("kayitOl.fxml"));
			Scene scene1=new Scene(pane1);
			stage1.setScene(scene1);
			stage1.show();
			
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void btnKapat_clicked(ActionEvent event) {	
    	Node node = (Node)event.getSource();
		Stage stage = (Stage)node.getScene().getWindow();
		stage.close();
    	
    }

    @FXML
    void btn_KullanıcıGris_Click(ActionEvent event) {

    	sql = "select * from kullanicikayit where kullanici=? and Sifre=?";
    	try {
    		sorguIfadesi=sqlbaglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_Abone.getText().trim());
    		sorguIfadesi.setString(2, txt_AboneSifre.getText().trim());
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		
    		

    		if(!getirilen.next())
    		{
    			  String b="";
		    	if (txt_Abone.getText().equals("")) 
		    	{
		    		b+="Kullanıcı adı Boş veya Hatalı !!\n";
		    		txt_Abone.setStyle("-fx-border-color:red");
		    				
				}else {
					b+="Kullanıcı Adı Dogru \n";
					txt_AboneSifre.setStyle("-fx-border-color:red");
					
				} 
		    	 if (txt_AboneSifre.getText().equals("")) 
		    	{
		    		b+="Sifre  Boş veya Hatalı !!\n";
		    		txt_AboneSifre.setStyle("-fx-border-color:red");
		    				
				}else {
					b+="Sifre Dogru \n";
					txt_AboneSifre.setStyle("-fx-border-color:red");
					
				}hataliGiris.setText(b);

    		}
    		else {
    			
    			try {
    		    	Node node = (Node)event.getSource();
    				Stage stage = (Stage)node.getScene().getWindow();
    				stage.close();
    				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Anasayfa.fxml"));
    				Parent root1;
    				root1 = (Parent) fxmlLoader.load();
    				Stage stage1 = new Stage();
    				stage1.setScene(new Scene(root1));  
    				stage1.show();
    	    	
    	    	} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}

    	    	}
			
		} 
    	catch (Exception e) 
    	{
			
		}

    	
    	
    }

    
    
    @FXML
    void initialize() {


    }
    
    
    
    
}

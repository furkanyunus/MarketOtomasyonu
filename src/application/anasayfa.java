package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import veritabani.veritabaniUtil;


public class anasayfa {
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private AnchorPane anasayfa;

	    @FXML
	    private MenuItem yardimMenuitem;
	    @FXML
	    private MenuItem tumUrunler_Menuitm;
	    @FXML
	    private MenuItem ekleMenuitem; 
	    @FXML
	    private TextField txt_Barkod;

	    @FXML
	    private TableView<ekleKayit> kayitlarTablosu;

	    @FXML
	    private TableColumn<ekleKayit, String> barkod_No;

	    @FXML
	    private TableColumn<ekleKayit, String> urun_Turu;

	    @FXML
	    private TableColumn<ekleKayit, String> urun_Ad;

	    @FXML
	    private TableColumn<ekleKayit, String> fiyat;

	    @FXML
	    private TableColumn<ekleKayit, String> tarih_Giris;

	    @FXML
	    private Button btn_Satıldı;
	    
	    @FXML
	    private Label lbl_Toplam;

	    @FXML
	    private Label lbl_Sonurun;

	    private String urunlerim = "";
	    private float ToplamFiyat = 0;
	    
	    Connection sqlbaglanti = null;
		PreparedStatement sorguIfadesi = null;
		String sql;
		

		public anasayfa() {
			sqlbaglanti = veritabaniUtil.Baglan();
			
		}

	    @FXML
	     void yardimMenuitm_Click() {
	    	
	    	try {
	    		Stage stage1=new Stage();
				AnchorPane pane1= (AnchorPane) FXMLLoader.load(getClass().getResource("Yrdm.fxml"));
				Scene scene1=new Scene(pane1);
				stage1.setScene(scene1);
				stage1.show();
				
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	catch (Exception e) 
	    	{
				
			}
	    }
	    
	    
	    @FXML
	    void tumUrunler_Menuitm_Click(ActionEvent event) {

	    	
	    	try {
	    		Stage stage2=new Stage();
				AnchorPane pane2= (AnchorPane) FXMLLoader.load(getClass().getResource("tumUrunlerGrfk.fxml"));
				Scene scene2=new Scene(pane2);
				stage2.setScene(scene2);
				stage2.show();
				
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	catch (Exception e) 
	    	{

			}	
	    }
	    
	    @FXML
	    void ekleMenuitm_Click (ActionEvent event) {
	    	
	    	try {
	    		Stage stage2=new Stage();
				AnchorPane pane2= (AnchorPane) FXMLLoader.load(getClass().getResource("Ekle.fxml"));
				Scene scene2=new Scene(pane2);
				stage2.setScene(scene2);
				stage2.show();
				
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	catch (Exception e) 
	    	{

			}	
	    	
	    	
	    }
	    
	    @FXML
	    
	    void ara_Clicked(ActionEvent event) {
	    	String barkod = txt_Barkod.getText();	
	    	SqlDegeriGtrme(kayitlarTablosu,barkod);
	    }
	    
	    
	    @FXML
	    void kayitlarTablosu_Clicked(MouseEvent event) {
	    	ekleKayit kayit = new ekleKayit();
			kayit = (ekleKayit) kayitlarTablosu.getItems().get(kayitlarTablosu.getSelectionModel().getSelectedIndex());
			/*
			sqlidm=kayit.getBarkod_No();
			txt_Barkod.setText(String.valueOf(kayit.getBarkod_No()));
			combo_UrnTuru.setValue(kayit.getUrun_Turu());
			txt_Urun.setText(kayit.getUrun_Ad());
			txt_Fiyat.setText(String.valueOf(kayit.getFiyat()));
			datep_tarih.setValue(LocalDate.parse(kayit.getTarih_Giris()));
			*/
			urunlerim += kayit.getUrun_Ad() + ",";
			ToplamFiyat += kayit.getFiyat();

			lbl_Sonurun.setText(""+urunlerim);
			lbl_Toplam.setText(""+ToplamFiyat);
			
	    }
	    
	    public void SqlDegeriGtrme(TableView<ekleKayit> table,String barkod) {
			sql = "SELECT * FROM `ekleme` WHERE `barkod_No` LIKE '%"+barkod+"%'";
			try {
				sorguIfadesi = sqlbaglanti.prepareStatement(sql);
				ObservableList<ekleKayit> kayitlarListe = FXCollections.observableArrayList();
				ResultSet getirilen = sorguIfadesi.executeQuery();
				while (getirilen.next()) {
					kayitlarListe.add(new ekleKayit(getirilen.getInt("barkod_No"), getirilen.getString("urun_Turu"),
							getirilen.getString("urun_Ad"), getirilen.getInt("fiyat"), getirilen.getString("tarih_Giris")));
				}
				barkod_No.setCellValueFactory(new PropertyValueFactory<>("barkod_No"));
				urun_Turu.setCellValueFactory(new PropertyValueFactory<>("urun_Turu"));
				urun_Ad.setCellValueFactory(new PropertyValueFactory<>("urun_Ad"));
				fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
				tarih_Giris.setCellValueFactory(new PropertyValueFactory<>("tarih_Giris"));

				table.setItems(kayitlarListe);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	    
	    
	    @FXML
	    void temizle_Clicked(ActionEvent event) {
	    	urunlerim = "";
			ToplamFiyat = 0;

			lbl_Sonurun.setText(""+urunlerim);
			lbl_Toplam.setText(""+ToplamFiyat);
	    }

	    @FXML
	    void btn_Satıldı_Click(ActionEvent event) {
	    	sqlbaglanti=veritabaniUtil.Baglan();
	    	Alert alert=new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Ürün Bilgileri");
	    	alert.setHeaderText("Satılı Uyarısı");
	    	alert.setContentText("Silmek istediğinize emin misiniz?");
	    	Optional<ButtonType> result=alert.showAndWait();
	    	if(result.get()==ButtonType.OK) {
	        	ekleKayit bilgiler=new ekleKayit();
	          	 bilgiler=kayitlarTablosu.getItems().get(kayitlarTablosu.getSelectionModel().getFocusedIndex());
	          	 int id=bilgiler.getBarkod_No();
	          	 sql="delete from ekleme where barkod_No=?";
	   	    	try {
	   					sorguIfadesi=sqlbaglanti.prepareStatement(sql);
	   					sorguIfadesi.setInt(1, id);
	   					sorguIfadesi.executeUpdate();
	   				} 
	   	    	catch (SQLException e) {e.printStackTrace();}  
	    	}
	    }
	    
	    
	    @FXML
	    void initialize() {	
	    
	    }
	    
	    
}

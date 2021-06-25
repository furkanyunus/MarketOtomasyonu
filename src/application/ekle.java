package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import veritabani.veritabaniUtil;

public class ekle {

	@FXML
	private Button btn_Ekle;
	@FXML
	private Button btn_Sil;
	@FXML
	private Button btn_Guncelle;
	@FXML
	private Button btn_Kapat;

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
	private TextField txt_Barkod;
	@FXML
	private TextField txt_Urun;
	@FXML
	private TextField txt_Fiyat;
	@FXML
	private ComboBox<String> combo_UrnTuru;
	@FXML
	private DatePicker datep_tarih;

	ObservableList<ekleKayit> tumveriler;

	@FXML
	void btn_Kapat_Click(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		Stage stage1 = (Stage) node.getScene().getWindow();
		stage1.setMaximized(true);
		stage1.close();
		/*
		 * FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource("Ekle.fxml"));
		 * Parent root1; root1 = (Parent) fxmlLoader.load(); Stage stage = new Stage();
		 * stage.setScene(new Scene(root1)); stage.show();
		 */
	}

	Connection sqlbaglanti = null;
	PreparedStatement sorguIfadesi = null;
	String sql;
	//PreparedStatement sorguIfadesi1 = null;
	//ResultSet getirilen = null;
//	String ytk;

	int sqlidm = 0;

	public ekle() {
		sqlbaglanti = veritabaniUtil.Baglan();
	}

	@FXML
	void btn_Ekle_clicked(ActionEvent event) {
		sql = "insert into ekleme(barkod_No,urun_Turu,urun_Ad,fiyat,tarih_Giris) values(?,?,?,?,?)";
		try {
			sorguIfadesi = sqlbaglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txt_Barkod.getText().trim());
			sorguIfadesi.setString(2, combo_UrnTuru.getValue().trim());
			sorguIfadesi.setString(3, txt_Urun.getText().trim());
			sorguIfadesi.setString(4, txt_Fiyat.getText().trim());
			sorguIfadesi.setString(5, String.valueOf(datep_tarih.getValue()));
			sorguIfadesi.executeUpdate();
			SqlDegeriGtrme(kayitlarTablosu);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

	}

	@FXML
	void btn_Guncelle_clicked(ActionEvent event) {
		sql="update ekleme set barkod_No= ? , urun_Turu= ? , urun_Ad= ? , fiyat= ? , tarih_Giris= ? where barkod_No=?";
		try {
			sorguIfadesi = sqlbaglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txt_Barkod.getText().trim());
			sorguIfadesi.setString(2, combo_UrnTuru.getValue().trim());
			sorguIfadesi.setString(3, txt_Urun.getText().trim());
			sorguIfadesi.setString(4, txt_Fiyat.getText().trim());
			sorguIfadesi.setString(5, String.valueOf(datep_tarih.getValue()));
			sorguIfadesi.setInt(6, sqlidm);
			sorguIfadesi.executeUpdate();
			SqlDegeriGtrme(kayitlarTablosu);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}

	@FXML
	void btn_Sil_clicked(ActionEvent event) {
		sql = "delete from ekleme where barkod_No=?";
		try {
			sorguIfadesi = sqlbaglanti.prepareStatement(sql);
			sorguIfadesi.setInt(1, sqlidm);
			sorguIfadesi.executeUpdate();
			SqlDegeriGtrme(kayitlarTablosu);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}

	@FXML
	void kayitlarTablosu_Clicked(MouseEvent event) {

		ekleKayit kayit = new ekleKayit();
		kayit = (ekleKayit) kayitlarTablosu.getItems().get(kayitlarTablosu.getSelectionModel().getSelectedIndex());
		sqlidm=kayit.getBarkod_No();
		txt_Barkod.setText(String.valueOf(kayit.getBarkod_No()));
		combo_UrnTuru.setValue(kayit.getUrun_Turu());
		txt_Urun.setText(kayit.getUrun_Ad());
		txt_Fiyat.setText(String.valueOf(kayit.getFiyat()));
		datep_tarih.setValue(LocalDate.parse(kayit.getTarih_Giris()));

	}

	/*
	 * @FXML void combo_sehir_Clicked(ActionEvent event) { System.out.println(
	 * combo_UrnTuru.getSelectionModel().getSelectedItem());
	 * 
	 * }
	 */
	public void SqlDegeriGtrme(TableView<ekleKayit> table) {
		sql = "select * from ekleme";
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
	void initialize() {
		ObservableList<String> list = FXCollections.observableArrayList("Temizlik Mazemesi", "Gıda", "Bakım Ürünü",
				"İçecek");
		combo_UrnTuru.getItems().addAll(list);

		SqlDegeriGtrme(kayitlarTablosu);
	}

}

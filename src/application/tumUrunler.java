package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class tumUrunler {

	
	
	@FXML
    private PieChart pichrt_Grafik;

    @FXML
    private Button Kapat_btn;
    @FXML
    private Button btn_Piehatrt;
  
    
    @FXML
    void Kapat_btn_click(ActionEvent event) {
    	 Node node = (Node)event.getSource();
 		Stage stage = (Stage)node.getScene().getWindow();
 		stage.close();
 		
    }
@FXML
void btnpichart_Göster (ActionEvent event) {
    	ObservableList<Data> list = FXCollections.observableArrayList(
    			new PieChart.Data("Temizlik Mazemesi",30),
    			new PieChart.Data("Gıda",50),
    			new PieChart.Data("Bakım Ürünü",40),
    			new PieChart.Data("İçecek",60)
    			);

    			pichrt_Grafik.setData(list);
    	
    	
    	
    }
 
    
}


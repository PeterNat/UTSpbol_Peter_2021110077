/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_peter_2021110077;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Penath
 */
public class FXMLDataBukuController implements Initializable {

    @FXML
    private TableView<BukuModel> tbvbuku;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnquit;
    @FXML
    private TextField searchbuku;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    public void showdata(){
        ObservableList<BukuModel> data=FXMLDocumentController.dtbuku.Load();
        if(data!=null){            
            tbvbuku.getColumns().clear();            
            tbvbuku.getItems().clear();
            TableColumn col=new TableColumn("ID Buku");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idbuku"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Judul");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("judul"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Kategori");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("kategori"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Pengarang");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("pengarang"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Penerbit");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("penerbit"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Tahun");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, Integer>("tahun"));
            tbvbuku.getColumns().addAll(col);
                   
            tbvbuku.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbuku.getScene().getWindow().hide();;
        }                
    }

    

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvbuku.getSelectionModel().selectBelowCell();        
        tbvbuku.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvbuku.getSelectionModel().selectAboveCell();       
        tbvbuku.requestFocus(); 
    }

   

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBuku.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();      
    }

    @FXML
    private void updateklik(ActionEvent event) {
         BukuModel s= new BukuModel();
        s=tbvbuku.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBuku.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputBukuController isidt=(FXMLInputBukuController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  

    }

    @FXML
    private void hapusklik(ActionEvent event) {
        BukuModel s= new BukuModel();       
        s=tbvbuku.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Apakah anda yakin?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtbuku.delete(s.getIdbuku())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();              
        }    
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void cariData(KeyEvent event) {
        BukuModel s = new BukuModel();
        String key = searchbuku.getText();
        if(key!=""){
        ObservableList<BukuModel> data=FXMLDocumentController.dtbuku.CariBuku(key,key);
        if(data!=null){            
            tbvbuku.getColumns().clear();
            tbvbuku.getItems().clear();
            TableColumn col=new TableColumn("ID Buku");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idbuku"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Judul");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("judul"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Kategori");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("kategori"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Pengarang");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("pengarang"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Penerbit");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("penerbit"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("Tahun");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, Integer>("tahun"));
            tbvbuku.getColumns().addAll(col);      
            
            tbvbuku.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbuku.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }        
    }
    
}

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
public class FXMLDataAnggotaController implements Initializable {
    
    @FXML
    private TableView<AnggotaModel> tbvanggota;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private TextField searchanggota;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showdata();
    }    

    public void showdata(){
        ObservableList<AnggotaModel> data=FXMLDocumentController.dtanggota.Load();
        if(data!=null){            
            tbvanggota.getColumns().clear();            
            tbvanggota.getItems().clear();
            TableColumn col=new TableColumn("idanggota");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("idanggota"));
            tbvanggota.getColumns().addAll(col);
            col=new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("nama"));
            tbvanggota.getColumns().addAll(col);
            col=new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("alamat"));
            tbvanggota.getColumns().addAll(col);
            col=new TableColumn("telepon");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, Integer>("telepon"));
            tbvanggota.getColumns().addAll(col);
            

            
            tbvanggota.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvanggota.getScene().getWindow().hide();;
        }                
    }
    
    @FXML
    private void cariData(KeyEvent event) {
        AnggotaModel s = new AnggotaModel();
        String key = searchanggota.getText();
        if(key!=""){
        ObservableList<AnggotaModel> data=FXMLDocumentController.dtanggota.CariAnggota(key,key);
        if(data!=null){            
            tbvanggota.getColumns().clear();
            tbvanggota.getItems().clear();
            TableColumn col=new TableColumn("id Anggota");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("idanggota"));
            tbvanggota.getColumns().addAll(col);
            col=new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("nama"));
            tbvanggota.getColumns().addAll(col);
            col=new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("alamat"));
            tbvanggota.getColumns().addAll(col);
            col=new TableColumn("telepon");
            col.setCellValueFactory(new PropertyValueFactory<AnggotaModel, String>("telepon"));
            tbvanggota.getColumns().addAll(col);       
            
            tbvanggota.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvanggota.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }        
    
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
         AnggotaModel s= new AnggotaModel();       
        s=tbvanggota.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtanggota.delete(s.getIdanggota())){
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
    private void updateklik(ActionEvent event) {
        AnggotaModel s= new AnggotaModel();
        s=tbvanggota.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputAnggota.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputAnggotaController isidt=(FXMLInputAnggotaController)loader.getController();
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
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputAnggota.fxml"));    
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
    private void sebelumklik(ActionEvent event) {
        tbvanggota.getSelectionModel().selectAboveCell();       
        tbvanggota.requestFocus();  
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvanggota.getSelectionModel().selectBelowCell();        
        tbvanggota.requestFocus();
    }

    
}

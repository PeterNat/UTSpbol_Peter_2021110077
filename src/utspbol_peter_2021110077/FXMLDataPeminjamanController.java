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
public class FXMLDataPeminjamanController implements Initializable {

    @FXML
    private TextField searchpinjam;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnawal;
    @FXML
    private TableView<PinjamModel> tbvpinjam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
public void showdata(){
        ObservableList<PinjamModel> data=FXMLDocumentController.dtpinjam.Load();
        if(data!=null){            
            tbvpinjam.getColumns().clear();            
            tbvpinjam.getItems().clear();
            TableColumn col=new TableColumn("idpinjam");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idpinjam"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("idanggota");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idanggota"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("idbuku");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idbuku"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("judul");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("judul"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("tgl_pinjam");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("tgl_pinjam"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("tgl_kembali");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("tgl_kembali"));
            tbvpinjam.getColumns().addAll(col);
                   
            tbvpinjam.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvpinjam.getScene().getWindow().hide();;
        }                
    }
    @FXML
    private void cariData(KeyEvent event) {
        PinjamModel s = new PinjamModel();
        String key = searchpinjam.getText();
        if(key!=""){
        ObservableList<PinjamModel> data=FXMLDocumentController.dtpinjam.CariPinjam(key,key);
        if(data!=null){            
            tbvpinjam.getColumns().clear();
            tbvpinjam.getItems().clear();
            TableColumn col=new TableColumn("idpinjam");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idpinjam"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("idanggota");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idanggota"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("idbuku");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idbuku"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("judul");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("judul"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("tgl_pinjam");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("tgl_pinjam"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("tgl_kembali");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("tgl_kembali"));
            tbvpinjam.getColumns().addAll(col);    
            
            tbvpinjam.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvpinjam.getScene().getWindow().hide();;
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
        PinjamModel s= new PinjamModel();       
        s=tbvpinjam.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtpinjam.delete(s.getIdpinjam())){
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
        PinjamModel s= new PinjamModel();
        s=tbvpinjam.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPeminjaman.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputPeminjamanController isidt=(FXMLInputPeminjamanController)loader.getController();
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPeminjaman.fxml"));    
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
        tbvpinjam.getSelectionModel().selectAboveCell();       
        tbvpinjam.requestFocus(); 
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvpinjam.getSelectionModel().selectBelowCell();        
        tbvpinjam.requestFocus();
    }

    
    
}

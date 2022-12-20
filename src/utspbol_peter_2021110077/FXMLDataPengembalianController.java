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
public class FXMLDataPengembalianController implements Initializable {

    @FXML
    private TableView<KembaliModel> tbvkembali;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnquit;
    @FXML
    private TextField searchkembali;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
public void showdata(){
        ObservableList<KembaliModel> data=FXMLDocumentController.dtkembali.Load();
        if(data!=null){            
            tbvkembali.getColumns().clear();            
            tbvkembali.getItems().clear();
            TableColumn
            col=new TableColumn("ID Kembali");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idkembali"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("ID Pinjam");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idpinjam"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("tanggal Kembali Buku");
            col.setCellValueFactory(new FormattedDateValueFactory<BukuModel>("tgl_kembalibuku", "dd-MMM-yyyy"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("ID Buku");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idbuku"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("Judul");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("judul"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("ID Anggota");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idanggota"));
            tbvkembali.getColumns().addAll(col);
                   
            tbvkembali.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvkembali.getScene().getWindow().hide();
        }                
    }
    

    @FXML
    private void sesudahklik(ActionEvent event) {
         tbvkembali.getSelectionModel().selectBelowCell();        
        tbvkembali.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvkembali.getSelectionModel().selectAboveCell();       
        tbvkembali.requestFocus(); 
    }


    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPengembalian.fxml"));    
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
        KembaliModel s= new KembaliModel();
        s=tbvkembali.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPengembalian.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputPengembalianController isidt=(FXMLInputPengembalianController)loader.getController();
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
        KembaliModel s= new KembaliModel();       
        s=tbvkembali.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtkembali.delete(s.getIdkembali())){
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
        KembaliModel s = new KembaliModel();
        String key = searchkembali.getText();
        if(key!=""){
        ObservableList<KembaliModel> data=FXMLDocumentController.dtkembali.CariKembali(key,key);
        if(data!=null){            
            tbvkembali.getColumns().clear();            
            tbvkembali.getItems().clear();
            TableColumn
            col=new TableColumn("ID Kembali");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idkembali"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("ID Pinjam");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idpinjam"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("tanggal Kembali Buku");
            col.setCellValueFactory(new FormattedDateValueFactory<BukuModel>("tgl_kembalibuku", "dd-MMM-yyyy"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("ID Buku");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idbuku"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("Judul");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("judul"));
            tbvkembali.getColumns().addAll(col);
            col=new TableColumn("ID Anggota");
            col.setCellValueFactory(new PropertyValueFactory<KembaliModel, String>("idanggota"));
            tbvkembali.getColumns().addAll(col);   
            
            tbvkembali.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvkembali.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }        
    }
    
}

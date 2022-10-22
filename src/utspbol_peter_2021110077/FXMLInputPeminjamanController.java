/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_peter_2021110077;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Penath
 */
public class FXMLInputPeminjamanController implements Initializable {
boolean editdata=false;
    @FXML
    private TextField txtidbuku;
    @FXML
    private TextField txtidanggota;
    @FXML
    private TextField txtidpinjam;
    @FXML
    private TextField searchanggota;
    @FXML
    private TableView<AnggotaModel> tbvanggota;
    @FXML
    private TextField searchbuku;
    @FXML
    private TableView<BukuModel> tbvbuku;
    @FXML
    private TextField txtjudul;
    @FXML
    private TextField txttglpinjam;
    @FXML
    private TextField txttglkembali;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdataanggota();
        showdatabuku();
        tbvanggota.getSelectionModel().selectFirst(); //ini untuk inisialisasi baris pertama
    }    
    
    //input
    public void execute(PinjamModel d){
        if(!d.getIdpinjam().isEmpty()){
          editdata=true;
          txtidpinjam.setText(d.getIdpinjam());
          txtidanggota.setText(d.getIdanggota());
          txtidbuku.setText(d.getIdbuku());
          txtjudul.setText(d.getJudul());          
          txttglpinjam.setText(d.getTgl_pinjam());          
          txttglkembali.setText(d.getTgl_kembali());          
          txtidpinjam.setEditable(false);
          txtidpinjam.requestFocus();         
          
        }
    }
        
public void showdataanggota(){
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

    public void showdatabuku(){
        ObservableList<BukuModel> data=FXMLDocumentController.dtbuku.Load();
        if(data!=null){            
            tbvbuku.getColumns().clear();            
            tbvbuku.getItems().clear();
            TableColumn col=new TableColumn("idbuku");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idbuku"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("judul");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("judul"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("kategori");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("kategori"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("pengarang");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("pengarang"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("penerbit");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("penerbit"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("tahun");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, Integer>("tahun"));
            tbvbuku.getColumns().addAll(col);
                   
            tbvbuku.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbuku.getScene().getWindow().hide();;
        }                
    }
    
    @FXML
    public void setdata(){
        txtidanggota.setText(tbvanggota.getSelectionModel().getSelectedItem().getIdanggota());
        txtidbuku.setText(tbvbuku.getSelectionModel().getSelectedItem().getIdbuku());
        txtjudul.setText(tbvbuku.getSelectionModel().getSelectedItem().getJudul());
      }
    
    private void tambahklik(ActionEvent event) {
    }

    @FXML
    private void cariDataAnggota(KeyEvent event) {
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
               showdataanggota();
            }        
    }

    @FXML
    private void cariDataBuku(KeyEvent event) {
        BukuModel s = new BukuModel();
        String key = searchbuku.getText();
        if(key!=""){
        ObservableList<BukuModel> data=FXMLDocumentController.dtbuku.CariBuku(key,key);
        if(data!=null){            
            tbvbuku.getColumns().clear();
            tbvbuku.getItems().clear();
            TableColumn col=new TableColumn("idbuku");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("idbuku"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("judul");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("judul"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("kategori");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("kategori"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("pengarang");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("pengarang"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("penerbit");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, String>("penerbit"));
            tbvbuku.getColumns().addAll(col);
            col=new TableColumn("tahun");
            col.setCellValueFactory(new PropertyValueFactory<BukuModel, Integer>("tahun"));
            tbvbuku.getColumns().addAll(col);      
            
            tbvbuku.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbuku.getScene().getWindow().hide();;
        }            
            } else{
               showdatabuku();
            }        
    
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtidpinjam.setText("");        
        txtidanggota.setText("");
        txtidbuku.setText("");
        txtjudul.setText("");       
        txttglpinjam.setText("");       
        txttglkembali.setText("");    
        txtidpinjam.requestFocus();
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        PinjamModel n=new PinjamModel();        
        n.setIdpinjam(txtidpinjam.getText());
        n.setIdanggota(txtidanggota.getText());
        n.setIdbuku(txtidbuku.getText());
        n.setJudul(txtjudul.getText());     
        n.setTgl_pinjam(txttglpinjam.getText());  
        n.setTgl_kembali(txttglkembali.getText());  
        
        FXMLDocumentController.dtpinjam.setPinjamModel(n);
        if(editdata){
            if(FXMLDocumentController.dtpinjam.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtidpinjam.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtpinjam.validasi(n.getIdpinjam())<=0){
            if(FXMLDocumentController.dtpinjam.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidpinjam.requestFocus();
        }
    
    }


    
}

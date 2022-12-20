/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_peter_2021110077;

import java.net.URL;
import java.sql.Date;
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
public class FXMLInputPengembalianController implements Initializable {
boolean editdata=false;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnquit;
    @FXML
    private TextField txtidanggota;
    @FXML
    private DatePicker txttglkembalibuku;
    @FXML
    private TextField txtjudul;
    private TableView<BukuModel> tbvbuku;
    @FXML
    private TableView<PinjamModel> tbvpinjam;
    @FXML
    private TextField searchpinjam;
    @FXML
    private TextField txtidkembali;
    @FXML
    private TextField txtidbuku;
    @FXML
    private TextField txtidpinjam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdatapinjam();
        tbvpinjam.getSelectionModel().selectFirst(); //ini untuk inisialisasi baris pertama
    }    
//input
    public void execute(KembaliModel d){
        if(!d.getIdkembali().isEmpty()){
          editdata=true;
          txtidkembali.setText(d.getIdkembali());
          txtidpinjam.setText(d.getIdpinjam());
//          txttglkembalibuku.setText(d.getTgl_kembalibuku());
          txtidbuku.setText(d.getIdbuku());
          txtjudul.setText(d.getJudul());          
          txtidanggota.setText(d.getIdanggota());              
          txtidkembali.setEditable(false);
          txtidkembali.requestFocus();         
          
        }
    }
    public void showdatapinjam(){
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
            col.setCellValueFactory(new FormattedDateValueFactory<BukuModel>("tgl_pinjam", "dd-MMM-yyyy"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("tgl_kembali");
            col.setCellValueFactory(new FormattedDateValueFactory<BukuModel>("tgl_kembali", "dd-MMM-yyyy"));
            tbvpinjam.getColumns().addAll(col);
                   
            tbvpinjam.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvpinjam.getScene().getWindow().hide();;
        }                
    
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        KembaliModel n=new KembaliModel();        
        n.setIdkembali(txtidkembali.getText());
        n.setIdpinjam(txtidpinjam.getText());
        n.setTgl_kembalibuku(Date.valueOf(txttglkembalibuku.getValue())); 
        n.setIdbuku(txtidbuku.getText());
        n.setJudul(txtjudul.getText());     
        n.setIdanggota(txtidanggota.getText());   
        
        FXMLDocumentController.dtkembali.setKembaliModel(n);
        if(editdata){
            if(FXMLDocumentController.dtkembali.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtidkembali.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtkembali.validasi(n.getIdpinjam())<=0){
            if(FXMLDocumentController.dtkembali.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidkembali.requestFocus();
        }
    
    
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtidkembali.setText("");        
        txtidpinjam.setText("");        
        txtidanggota.setText("");
        txtidbuku.setText("");
        txtjudul.setText("");       
        txttglkembalibuku.getEditor().clear();   
        txtidkembali.requestFocus();
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void setdata(MouseEvent event) {
        txtidpinjam.setText(tbvpinjam.getSelectionModel().getSelectedItem().getIdpinjam());
        txtidbuku.setText(tbvpinjam.getSelectionModel().getSelectedItem().getIdbuku());
        txtjudul.setText(tbvpinjam.getSelectionModel().getSelectedItem().getJudul());
        txtidanggota.setText(tbvpinjam.getSelectionModel().getSelectedItem().getIdanggota());
    }


    @FXML
    private void cariDataPinjam(KeyEvent event) {
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
            col.setCellValueFactory(new FormattedDateValueFactory<BukuModel>("tgl_pinjam", "dd-MMM-yyyy"));
            tbvpinjam.getColumns().addAll(col);
            col=new TableColumn("tgl_kembali");
            col.setCellValueFactory(new FormattedDateValueFactory<BukuModel>("tgl_kembali", "dd-MMM-yyyy"));
            tbvpinjam.getColumns().addAll(col);    
            
            tbvpinjam.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvpinjam.getScene().getWindow().hide();;
        }            
            } else{
               showdatapinjam();
            }     
    }
    
}

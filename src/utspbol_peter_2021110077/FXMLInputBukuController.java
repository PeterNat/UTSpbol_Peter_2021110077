/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_peter_2021110077;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Penath
 */
public class FXMLInputBukuController implements Initializable {
boolean editdata=false;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtjudul;
    @FXML
    private TextField txtkategori;
    @FXML
    private TextField txtpengarang;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnquit;
    @FXML
    private TextField txtpenerbit;
    @FXML
    private TextField txttahun;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void execute(BukuModel d){
        if(!d.getIdbuku().isEmpty()){
          editdata=true;
          txtid.setText(d.getIdbuku());
          txtjudul.setText(d.getJudul());
          txtkategori.setText(d.getKategori());
          txtpengarang.setText(d.getPengarang());
          txtpenerbit.setText(d.getPenerbit());
          txttahun.setText(String.valueOf(d.getTahun()));          
          txtid.setEditable(false);
          txtjudul.requestFocus();         
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        BukuModel n=new BukuModel();        
        n.setIdbuku(txtid.getText());
        n.setJudul(txtjudul.getText());     
        n.setKategori(txtkategori.getText());  
        n.setPengarang(txtpengarang.getText());  
        n.setPenerbit(txtpenerbit.getText());  
        n.setTahun(Integer.parseInt(txttahun.getText()));
        
        FXMLDocumentController.dtbuku.setBukuModel(n);
        if(editdata){
            if(FXMLDocumentController.dtbuku.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtid.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtbuku.validasi(n.getIdbuku())<=0){
            if(FXMLDocumentController.dtbuku.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtid.requestFocus();
        }
    
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtid.setText("");        
        txtjudul.setText("");
        txtkategori.setText("");       
        txtpengarang.setText("");       
        txtpenerbit.setText("");       
        txttahun.setText("");  
        txtid.requestFocus();
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }
    
}

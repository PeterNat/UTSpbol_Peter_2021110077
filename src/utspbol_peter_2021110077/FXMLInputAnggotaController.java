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
public class FXMLInputAnggotaController implements Initializable {
boolean editdata=false;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txttelepon;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(AnggotaModel d){
        if(!d.getIdanggota().isEmpty()){
          editdata=true;
          txtid.setText(d.getIdanggota());
          txtnama.setText(d.getNama());
          txtalamat.setText(d.getAlamat());
          txttelepon.setText(String.valueOf(d.getTelepon()));          
          txtid.setEditable(false);
          txtnama.requestFocus();         
        }
    }
    
    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtid.setText("");        
        txtnama.setText("");
        txtalamat.setText("");       
        txttelepon.setText("");  
        txtid.requestFocus();
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        AnggotaModel n=new AnggotaModel();        
        n.setIdanggota(txtid.getText());
        n.setNama(txtnama.getText());     
        n.setAlamat(txtalamat.getText());  
        n.setTelepon(Integer.parseInt(txttelepon.getText()));
        
        FXMLDocumentController.dtanggota.setAnggotaModel(n);
        if(editdata){
            if(FXMLDocumentController.dtanggota.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtid.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtanggota.validasi(n.getIdanggota())<=0){
            if(FXMLDocumentController.dtanggota.insert()){
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
    
}

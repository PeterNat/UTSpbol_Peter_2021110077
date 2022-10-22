/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package utspbol_peter_2021110077;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Penath
 */
public class FXMLDocumentController implements Initializable {
    //database
    public static DBAnggota dtanggota = new DBAnggota();    
    public static DBBuku dtbuku = new DBBuku();
    public static DBPinjam dtpinjam = new DBPinjam();
    public static DBKembali dtkembali = new DBKembali();
    
    @FXML
    private MenuItem inputanggota;
    @FXML
    private MenuItem inputbuku;
    @FXML
    private MenuItem displayagg;
    @FXML
    private MenuItem displaybuku;
    
    
    @FXML
    private MenuItem displaypinjam;
    @FXML
    private MenuItem inputpinjam;
    @FXML
    private MenuItem displaykembali;
    @FXML
    private MenuItem inputkembali;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inputaggklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputAnggota.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void inputbukuklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBuku.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void displayaggklik(ActionEvent event) {
    try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDataAnggota.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }
    

    @FXML
    private void displaybukuklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDataBuku.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }    
    
    }

    @FXML
    private void displaypinjamklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDataPeminjaman.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void inputpinjamklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPeminjaman.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }    
    }

    @FXML
    private void displaykembaliklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDataPengembalian.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void inputkembaliklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPengembalian.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }    
    }
    
}

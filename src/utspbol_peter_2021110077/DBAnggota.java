/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol_peter_2021110077;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Penath
 */
public class DBAnggota {
    private AnggotaModel dt=new AnggotaModel();    
    public AnggotaModel getAnggotaModel(){ return(dt);}
    public void setAnggotaModel(AnggotaModel s){ dt=s;}
    
    public ObservableList<AnggotaModel>  Load() {
        try {
            ObservableList<AnggotaModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idanggota,nama,alamat,telepon from anggota");
            int i = 1;
            while (rs.next()) {
                AnggotaModel d=new AnggotaModel();
                d.setIdanggota(rs.getString("idanggota"));                
                d.setNama(rs.getString("nama"));            
                d.setAlamat(rs.getString("alamat"));               
                d.setTelepon(rs.getInt("telepon"));                
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from anggota where idanggota = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
   
 public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into anggota (idanggota,nama, alamat, telepon) values (?,?,?,?)");
            con.preparedStatement.setString(1, getAnggotaModel().getIdanggota());           
            con.preparedStatement.setString(2, getAnggotaModel().getNama());
            con.preparedStatement.setString(3, getAnggotaModel().getAlamat());           
            con.preparedStatement.setInt(4, getAnggotaModel().getTelepon());        
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
   
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from anggota where idanggota  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }

public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update anggota set nama = ?, alamat = ?, telepon= ?,idanggota=?  where  idanggota = ? ");
            con.preparedStatement.setString(1, getAnggotaModel().getNama());
            con.preparedStatement.setString(2, getAnggotaModel().getAlamat());
            con.preparedStatement.setDouble(3, getAnggotaModel().getTelepon());
            con.preparedStatement.setString(4, getAnggotaModel().getIdanggota());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
        }

public ObservableList<AnggotaModel>  CariAnggota(String kode, String nama) {
        try {    
            ObservableList<AnggotaModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from anggota WHERE idanggota LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            AnggotaModel d = new AnggotaModel();
            d.setIdanggota(rs.getString("idanggota"));
            d.setNama(rs.getString("nama"));
            d.setAlamat(rs.getString("alamat"));
            d.setTelepon(rs.getInt("telepon"));
            
            tableData.add(d);
            i++;
        }
        con.tutupKoneksi();
        return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

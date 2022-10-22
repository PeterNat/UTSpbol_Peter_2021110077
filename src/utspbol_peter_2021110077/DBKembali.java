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
public class DBKembali {
    private KembaliModel dt=new KembaliModel();    
    public KembaliModel getKembaliModel(){ return(dt);}
    public void setKembaliModel(KembaliModel s){ dt=s;}
    
    public ObservableList<KembaliModel>  Load() {
        try {
            ObservableList<KembaliModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idkembali,idpinjam, tgl_kembalibuku,idbuku,judul,idanggota from pengembalian");
            int i = 1;
            while (rs.next()) {
                KembaliModel d=new KembaliModel();
                d.setIdkembali(rs.getString("idkembali"));                
                d.setIdpinjam(rs.getString("idpinjam"));                
                d.setTgl_kembalibuku(rs.getString("tgl_kembalibuku"));                  
                d.setIdbuku(rs.getString("idbuku"));            
                d.setJudul(rs.getString("judul"));            
                d.setIdanggota(rs.getString("idanggota"));            
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from pengembalian where idkembali = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pengembalian (idkembali,idpinjam, tgl_kembalibuku,idbuku,judul,idanggota) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getKembaliModel().getIdkembali());           
            con.preparedStatement.setString(2, getKembaliModel().getIdpinjam());           
            con.preparedStatement.setString(3, getKembaliModel().getTgl_kembalibuku());
            con.preparedStatement.setString(4, getKembaliModel().getIdbuku());           
            con.preparedStatement.setString(5, getKembaliModel().getJudul());        
            con.preparedStatement.setString(6, getKembaliModel().getIdanggota());          
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pengembalian where idkembali = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pengembalian set idanggota= ?,judul= ? , idbuku= ?,tgl_kembalibuku= ? ,idpinjam= ?   where  idkembali= ? ");
            con.preparedStatement.setString(1, getKembaliModel().getIdanggota());          
            con.preparedStatement.setString(2, getKembaliModel().getJudul());        
            con.preparedStatement.setString(3, getKembaliModel().getIdbuku());           
            con.preparedStatement.setString(4, getKembaliModel().getTgl_kembalibuku());           
            con.preparedStatement.setString(5, getKembaliModel().getIdpinjam());           
            con.preparedStatement.setString(6, getKembaliModel().getIdkembali());
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

public ObservableList<KembaliModel>  CariKembali(String kode, String idpinjam) {
        try {    
            ObservableList<KembaliModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from pengembalian WHERE idkembali LIKE '" + kode + "%' OR idpinjam LIKE '" + idpinjam + "%'");
        int i = 1;
        while(rs.next()){
            KembaliModel d = new KembaliModel();
            d.setIdkembali(rs.getString("idkembali"));                
            d.setIdpinjam(rs.getString("idpinjam"));                
            d.setTgl_kembalibuku(rs.getString("tgl_kembalibuku"));     
            d.setIdbuku(rs.getString("idbuku"));            
            d.setJudul(rs.getString("judul"));            
            d.setIdanggota(rs.getString("idanggota"));            
            
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
public ObservableList<PinjamModel>  CariPinjam(String kode, String idanggota) {
        try {    
            ObservableList<PinjamModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from peminjaman WHERE idpinjam LIKE '" + kode + "%' OR idanggota LIKE '" + idanggota + "%'");
        int i = 1;
        while(rs.next()){
            PinjamModel d = new PinjamModel();
            d.setIdpinjam(rs.getString("idpinjam"));
            d.setIdanggota(rs.getString("idanggota"));
            d.setIdbuku(rs.getString("idbuku"));
            d.setJudul(rs.getString("judul"));
            d.setTgl_pinjam(rs.getString("tgl_pinjam"));
            d.setTgl_kembali(rs.getString("tgl_kembali"));
            
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

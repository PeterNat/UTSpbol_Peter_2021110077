/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol_peter_2021110077;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Penath
 */
public class DBPinjam {
    private PinjamModel dt=new PinjamModel();    
    public PinjamModel getPinjamModel(){ return(dt);}
    public void setPinjamModel(PinjamModel s){ dt=s;}
    
    public ObservableList<PinjamModel>  Load() {
        try {
            ObservableList<PinjamModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idpinjam, idanggota, idbuku,judul,tgl_pinjam,tgl_kembali from peminjaman");
            int i = 1;
            while (rs.next()) {
                PinjamModel d=new PinjamModel();
                d.setIdpinjam(rs.getString("idpinjam"));                
                d.setIdanggota(rs.getString("idanggota"));            
                d.setIdbuku(rs.getString("idbuku"));            
                d.setJudul(rs.getString("judul"));            
                d.setTgl_pinjam(rs.getDate("tgl_pinjam"));            
                d.setTgl_kembali(rs.getDate("tgl_kembali"));               
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from peminjaman where idpinjam = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into peminjaman (idpinjam,idanggota,idbuku,judul,tgl_pinjam,tgl_kembali) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getPinjamModel().getIdpinjam());           
            con.preparedStatement.setString(2, getPinjamModel().getIdanggota());
            con.preparedStatement.setString(3, getPinjamModel().getIdbuku());           
            con.preparedStatement.setString(4, getPinjamModel().getJudul());        
            con.preparedStatement.setDate(5, getPinjamModel().getTgl_pinjam());        
            con.preparedStatement.setDate(6, getPinjamModel().getTgl_kembali());        
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from peminjaman where idpinjam = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update peminjaman set tgl_kembali= ?,tgl_pinjam= ? , judul= ?,idbuku= ? ,idanggota= ?   where  idpinjam= ? ");
            con.preparedStatement.setDate(1, getPinjamModel().getTgl_kembali());         
            con.preparedStatement.setDate(2, getPinjamModel().getTgl_pinjam());        
            con.preparedStatement.setString(3, getPinjamModel().getJudul());        
            con.preparedStatement.setString(4, getPinjamModel().getIdbuku());           
            con.preparedStatement.setString(5, getPinjamModel().getIdanggota());
            con.preparedStatement.setString(6, getPinjamModel().getIdpinjam());           
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
            d.setTgl_pinjam(rs.getDate("tgl_pinjam"));
            d.setTgl_kembali(rs.getDate("tgl_kembali"));
            
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
public void print(){
        Koneksi con = new Koneksi(); 
        String is = "./src/utspbol_peter_2021110077/ReportPeminjaman.jasper";
        Map map = new HashMap();
        map.put("p_periode", "Desember");
        con.bukaKoneksi();
        try { 
            JasperPrint jasperPrint =
                JasperFillManager.fillReport(is, map,  con.dbKoneksi);
            JasperViewer.viewReport(jasperPrint, false);
        } 
        catch (Exception ex) { ex.printStackTrace();  }   
        con.tutupKoneksi();         
    }
}

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
public class DBBuku {
    private BukuModel dt=new BukuModel();    
    public BukuModel getBukuModel(){ return(dt);}
    public void setBukuModel(BukuModel s){ dt=s;}
    
    public ObservableList<BukuModel>  Load() {
        try {
            ObservableList<BukuModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idbuku, judul, kategori,pengarang,penerbit,tahun from buku");
            int i = 1;
            while (rs.next()) {
                BukuModel d=new BukuModel();
                d.setIdbuku(rs.getString("idbuku"));                
                d.setJudul(rs.getString("judul"));            
                d.setKategori(rs.getString("kategori"));               
                d.setPengarang(rs.getString("pengarang"));               
                d.setPenerbit(rs.getString("penerbit"));               
                d.setTahun(rs.getInt("tahun"));               
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from buku where idbuku = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into buku (idbuku, judul, kategori,pengarang,penerbit,tahun) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getBukuModel().getIdbuku());           
            con.preparedStatement.setString(2, getBukuModel().getJudul());
            con.preparedStatement.setString(3, getBukuModel().getKategori());           
            con.preparedStatement.setString(4, getBukuModel().getPengarang());        
            con.preparedStatement.setString(5, getBukuModel().getPenerbit());        
            con.preparedStatement.setInt(6, getBukuModel().getTahun());        
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from buku where idbuku = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update buku set judul= ?,kategori= ? , pengarang= ?,penerbit= ? ,tahun = ?   where  idbuku= ? ");
            con.preparedStatement.setString(1, getBukuModel().getJudul());
            con.preparedStatement.setString(2, getBukuModel().getKategori());           
            con.preparedStatement.setString(3, getBukuModel().getPengarang());        
            con.preparedStatement.setString(4, getBukuModel().getPenerbit());        
            con.preparedStatement.setInt(5, getBukuModel().getTahun());       
            con.preparedStatement.setString(6, getBukuModel().getIdbuku());           
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

public ObservableList<BukuModel>  CariBuku(String kode, String judul) {
        try {    
            ObservableList<BukuModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from buku WHERE idbuku LIKE '" + kode + "%' OR judul LIKE '" + judul + "%'");
        int i = 1;
        while(rs.next()){
            BukuModel d = new BukuModel();
            d.setIdbuku(rs.getString("idbuku"));                
            d.setJudul(rs.getString("judul"));            
            d.setKategori(rs.getString("kategori"));               
            d.setPengarang(rs.getString("pengarang"));               
            d.setPenerbit(rs.getString("penerbit"));               
            d.setTahun(rs.getInt("tahun"));     
            
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

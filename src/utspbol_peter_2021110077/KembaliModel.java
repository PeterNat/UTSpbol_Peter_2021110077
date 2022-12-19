/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol_peter_2021110077;

import java.sql.Date;

/**
 *
 * @author Penath
 */
public class KembaliModel {
    private String idkembali,idpinjam,idanggota, idbuku,judul;
    Date tgl_kembalibuku;

    public String getIdkembali() {
        return idkembali;
    }

    public void setIdkembali(String idkembali) {
        this.idkembali = idkembali;
    }

    public String getIdpinjam() {
        return idpinjam;
    }

    public void setIdpinjam(String idpinjam) {
        this.idpinjam = idpinjam;
    }
    public String getIdanggota() {
        return idanggota;
    }

    public void setIdanggota(String idanggota) {
        this.idanggota = idanggota;
    }

    public String getIdbuku() {
        return idbuku;
    }

    public void setIdbuku(String idbuku) {
        this.idbuku = idbuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Date getTgl_kembalibuku() {
        return tgl_kembalibuku;
    }

    public void setTgl_kembalibuku(Date tgl_kembalibuku) {
        this.tgl_kembalibuku = tgl_kembalibuku;
    }
    
}


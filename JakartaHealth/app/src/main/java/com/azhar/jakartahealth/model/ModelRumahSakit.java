package com.azhar.jakartahealth.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class ModelRumahSakit implements Serializable {

    private String nama_rs;
    private String jenis_rs;
    private String kode_pos;
    private String location;
    private String telepon;
    private String email;
    private String website;
    private String faximile;
    private double latitude;
    private double longitude;

    public String getFaximile() {
        return faximile;
    }

    public void setFaximile(String faximile) {
        this.faximile = faximile;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNamaRs() {
        return nama_rs;
    }

    public void setNamaRs(String nama_rs) {
        this.nama_rs = nama_rs;
    }

    public String getJenisRs() {
        return jenis_rs;
    }

    public void setJenisRs(String jenis_rs) {
        this.jenis_rs = jenis_rs;
    }

    public String getKodePos() {
        return kode_pos;
    }

    public void setKodePos(String kode_pos) {
        this.kode_pos = kode_pos;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}

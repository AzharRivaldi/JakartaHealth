package com.azhar.jakartahealth.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class ModelPuskesmas implements Serializable {

    private String namaPuskesmas;
    private String location;
    private String telepon;
    private String faximile;
    private String email;
    private String kepalaPuskesmas;
    private double latitude;
    private double longitude;

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

    public String getNamaPuskesmas() {
        return namaPuskesmas;
    }

    public void setNamaPuskesmas(String namaPuskesmas) {
        this.namaPuskesmas = namaPuskesmas;
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

    public String getFaximile() {
        return faximile;
    }

    public void setFaximile(String faximile) {
        this.faximile = faximile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKepalaPuskesmas() {
        return kepalaPuskesmas;
    }

    public void setKepalaPuskesmas(String kepalaPuskesmas) {
        this.kepalaPuskesmas = kepalaPuskesmas;
    }
}

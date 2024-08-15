package com.bigcorp.booking.TP;

public class Voiture {

    private Long id;
    private String numeroImmatriculation;
    private boolean actif = true;
    private String dateImmatriculation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getDateImmatriculation() {
        return dateImmatriculation;
    }

    public void setDateImmatriculation(String dateImmatriculation) {
        this.dateImmatriculation = dateImmatriculation;
    }
}

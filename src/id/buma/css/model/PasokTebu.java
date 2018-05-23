/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.model;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public class PasokTebu {
    private String afdeling;
    private String rayon;
    private String tstr;
    private Double tonase;
    private int ritMasuk;
    private int ritCaneyard;
    private int ritAntrian;
    
    public PasokTebu(String afdeling,String rayon, String tstr, Double tonase,
            int ritMasuk,int ritCaneyard, int ritAntrian){
        this.afdeling = afdeling;
        this.rayon = rayon;
        this.tstr = tstr;
        this.tonase = tonase;
        this.ritMasuk = ritMasuk;
        this.ritCaneyard = ritCaneyard;
        this.ritAntrian = ritAntrian;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getTstr() {
        return tstr;
    }

    public void setTstr(String tstr) {
        this.tstr = tstr;
    }

    public Double getTonase() {
        return tonase;
    }

    public void setTonase(Double tonase) {
        this.tonase = tonase;
    }

    public int getRitMasuk() {
        return ritMasuk;
    }

    public void setRitMasuk(int ritMasuk) {
        this.ritMasuk = ritMasuk;
    }

    public int getRitAntrian() {
        return ritAntrian;
    }

    public void setRitAntrian(int ritAntrian) {
        this.ritAntrian = ritAntrian;
    }

    public int getRitCaneyard() {
        return ritCaneyard;
    }

    public void setRitCaneyard(int ritCaneyard) {
        this.ritCaneyard = ritCaneyard;
    }
            
}

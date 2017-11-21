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
    private String wilayah;
    private Double tonase;
    private int ritMasuk;
    private int ritBruto;
    private int ritNetto;
    private int ritJalur;
    
    public PasokTebu(String afdeling,String wilayah, Double tonase,
            int ritMasuk,int ritBruto, int ritNetto){
        this.afdeling = afdeling;
        this.wilayah = wilayah;
        this.tonase = tonase;
        this.ritMasuk = ritMasuk;
        this.ritBruto = ritBruto;
        this.ritNetto = ritNetto;
        this.ritJalur = ritMasuk - (ritNetto + ritBruto); 
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }
    
    public String getWilayah(){
        return wilayah;
    }
    
    public void setWilayah(String wilayah){
        this.wilayah = wilayah;
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

    public int getRitBruto() {
        return ritBruto;
    }

    public void setRitBruto(int ritBruto) {
        this.ritBruto = ritBruto;
    }

    public int getRitNetto() {
        return ritNetto;
    }

    public void setRitNetto(int ritNetto) {
        this.ritNetto = ritNetto;
    }

    public int getRitJalur() {
        return ritJalur;
    }

    public void setRitJalur(int ritJalur) {
        this.ritJalur = ritJalur;
    }
            
}

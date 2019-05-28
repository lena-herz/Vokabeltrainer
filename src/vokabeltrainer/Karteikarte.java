/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;

/**
 *
 * @author Lena
 */
public class Karteikarte {

    private String vokA;
    private String vokZ;
    private String hilfssatz;
    private boolean gelernt;
    private int status;
    private boolean favorit;

    public static void main(String[] args) {
        
    }

    public Karteikarte() {
        setVokA();
        setVokZ();
        menuHS();

    }
    
    private void setVokA(){
        System.out.println("Vokabel Ausgangssprache?");
        String pVokA = SystemInReader.readString();
        this.vokA = pVokA;
    }

    public String getVokA() {
        return vokA;
    }
    
    private void setVokZ(){
        System.out.println("Vokabel Zielsprache?");
        String pVokZ = SystemInReader.readString();
        vokZ = pVokZ;
    }

    public String getVokZ() {
        return vokZ;
    }

//    public void setHilfssatz() {
//        System.out.println("Wie soll der Hilfssatz lauten?");
//        String satz = SystemInReader.readString();
//        hilfssatz = satz;
//    }
    
    private void menuHS() {
        System.out.println("Hilfssatz hinzufügen?");
        System.out.println("1: ja");
        System.out.println("2: nein");
        int eingHS = SystemInReader.readInt();
        if (eingHS == 1) {
            System.out.println("Hilfssatz?");
            String pHS = SystemInReader.readString();
            this.hilfssatz = pHS;
        }
    }

    public String getHilfssatz() {
        return hilfssatz;
    }

    public void setStatus(int pWert) {
        status = pWert;
    }

    public int getStatus() {
        return status;
    }

    public void setGelernt() {
        gelernt = true;
    }

    public boolean getGelernt() {
        return gelernt;
    }

    public void setFavorit(boolean pFavorit) {
        favorit = pFavorit;
    }

    public boolean getFavorit() {
        return favorit;
    }

}

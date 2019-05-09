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
        //System.out.println("Karteikarte");
    }
    
    public Karteikarte(String pVokA, String pVokZ){
        vokA = pVokA;
        vokZ = pVokZ;
    }
    
    public String getVokA(){
        return vokA;
    }
    
    public String getVokZ(){
        return vokZ;
    }
    
    public void setHilfssatz(){
        System.out.println("Wie soll der Hilfssatz lauten?");
        String satz = SystemInReader.readString();
        hilfssatz = satz;
    }
    
}

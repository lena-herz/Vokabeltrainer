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

    private final String VOKA;
    private final String VOKZ;
    private final String HILFSSATZ;
    private boolean gelernt;
    private int status; //status von 0-3 für die vier Lampen, 0 entspricht der roten Lampe, ab da aufsteigend
    private boolean favorit; //aus Zeitmangel nicht geschafft, wäre aber prinzipiell möglich, das Programm durch eine Favoritenliste zu erweitern
    private final Lektion MEINELEKTION;


    public Karteikarte(Lektion pMeineLektion, String pVokA, String pVokZ, String pHS) { //Konstruktor für wenn neue Karteiakarten erstellt werden
        VOKA = pVokA;
        VOKZ = pVokZ;
        HILFSSATZ = pHS; 
        MEINELEKTION = pMeineLektion;
        
        //sind eigentlich Standardwerte, aber zur Sicherheit:
        gelernt = false;
        status = 0;
        favorit = false;
    }
    
    //Konstruktor für wenn Vokabeln eingelesen werden
    public Karteikarte(String pVokA, String pVokZ, String pHS, boolean pGel, int pStatus, boolean pFav, Lektion pMeineLektion){
        VOKA = pVokA;
        VOKZ = pVokZ;
        HILFSSATZ = pHS;
        gelernt = pGel;
        status = pStatus;
        favorit = pFav;
        MEINELEKTION = pMeineLektion;
    }

    public String getVokA() {
        return VOKA;
    }

    public String getVokZ() {
        return VOKZ;
    }

    public String getHilfssatz() {
        return HILFSSATZ;
    }

    public void setStatus(int pWert) {//übernimmt zusätzlich Funktion von setGelernt, wenn erforderlich, daher keine extra setGelernt()
        status = pWert;
        if(pWert == 3){
            gelernt = true;
        }
        MEINELEKTION.listeSpeichern(); //damit Status der Lampe beim nächsten Programmstart wieder übernommen wird
    }

    public int getStatus() {
        return status;
    }

    public boolean getGelernt() {
        return gelernt;
    }

    public void setFavorit(boolean pFavorit) { //nicht verwendet, provisorisch angelegt
        favorit = pFavorit;
        MEINELEKTION.listeSpeichern();
    }

    public boolean getFavorit() {
        return favorit;
    }
    
    public Lektion getMeineLektion(){
        return MEINELEKTION;
    }

}

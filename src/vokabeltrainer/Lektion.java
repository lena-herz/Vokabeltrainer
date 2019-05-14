/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;
import java.util.ArrayList;
/**
 *
 * @author Lena
 */
public class Lektion {
    private final String lName;
    private boolean vollGelernt;
    private int score;
    private boolean zielsprGefr;
    private Karteikarte aktKarte;
    private ArrayList<Karteikarte> lektListe = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("Lektion");
    
    }
    
    public Lektion(String pName){
        lName = pName;
    }
    
    public String getName(){
        return lName;
    }
    
    public void setVollstaendigGelernt(){
        vollGelernt = true;
    }
    
    public boolean getVollstaendigGelernt(){
        return vollGelernt;
    }
    
    public void setScore(int pScore){
        score = pScore;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setZielsprGefragt(boolean pZgefragt){
        zielsprGefr = pZgefragt;
    }
    
    public boolean getZielsprGefragt(){
        return zielsprGefr;
    }
    
    public void setAktKarte(Karteikarte pAktuell){
        aktKarte = pAktuell;
    }
    
    public Karteikarte getAktKarte(){
        return aktKarte;
    }
    
    public void vokHinzufuegen(Karteikarte pKarte){                
        lektListe.add(pKarte);
        
        System.out.println();
        System.out.println("Länge der Liste: " + lektListe.size());
        System.out.println("Liste der Lektion:");
        System.out.println();
        int i = 1;
        for (Karteikarte karte : lektListe){
            System.out.println(i++ + "." + karte.getVokA()+ " - " + karte.getVokZ());
        }
    }
}
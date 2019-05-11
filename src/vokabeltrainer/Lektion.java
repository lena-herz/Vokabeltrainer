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
public class Lektion {
    private String lName;
    private boolean vollGelernt;
    private int score;
    private boolean zielsprGefr;
    private Karteikarte aktKarte;
    
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
    
    public void setZielsprGefragt(){
        zielsprGefr = true;
    }
    
    public boolean getZielsprGefragt(){
        return zielsprGefr;
    }
    
    public void setAktKarte(Karteikarte pAktuell){
        aktKarte = pAktuell;
    }
    
    public Karteikarte getaktKarte(){
        return aktKarte;
    }
}
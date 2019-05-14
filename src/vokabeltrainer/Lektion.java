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
    
    public Karteikarte getaktKarte(){
        return aktKarte;
    }
    
    public void speicherInListe(Karteikarte pKarte){
        ArrayList<Karteikarte> list = new ArrayList<>();
        
        list.add(pKarte);

        
        System.out.println("Liste der Lektion:");
        System.out.println();
        int i = 1;
        for (Karteikarte str : list){
            System.out.println(i++ + "." + str.getVokA()+ " - " + str.getVokZ());
        }
    }
}
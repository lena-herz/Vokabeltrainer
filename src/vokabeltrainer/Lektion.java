/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;

/**
 *
 * @author Lena
 */
public class Lektion {

    private final String lName;
    private boolean vollGelernt;
    private int score;
    private boolean zielsprGefr; //zielsprGefr == true bedeutet, dass der Nutzer die Vokabelbedeutung in der Zielsprache eingeben muss
    private Karteikarte aktKarte;
    private static ArrayList<Karteikarte> vokListe = new ArrayList<>();
    private static File lektFile;
    
    //erstellt schonmal den Writer, damit in mehreren Methodenabschnitten aufrufbar unabhängig von Schleifen etc.,
    //aber übergibt noch keine Datei, weil noch keine definiert
    private static BufferedWriter lektOut;  

    public static void main(String[] args) throws IOException {
        //Lektion lekt = new Lektion();
        //vokListe.add(new Karteikarte());    
        //listeSpeichern();
    }

    public Lektion() throws IOException {
        System.out.println("Lektionsname?");
        String pName = SystemInReader.readString();
        lName = pName;
        
        //erstellt im Ordner "Vokabellisten" eine csv-Datei, die nach dem Lektionsnamen benannt wird und in der die Inhalte aller Karteikarten gespeichert werden 
        //sollen, die zu dieser Lektion gehören 
        lektFile = new File("Vokabellisten\\" + pName + ".csv"); 
        
        lektOut = new BufferedWriter(new FileWriter(lektFile)); //übergibt dem Writer jetzt die Datei, auf die er schreiben soll
        
        //bei einer neuen Lektion soll als default-case nach der Zielsprache gefragt werden, wenn der Nutzer es umstellt, soll es dann aber gespeichert werden
        zielsprGefr = true; 
    }
    
    //Neue Karteikarte wird über "vokListe.add(new Karteikarte())" zur vokListe hinzugefügt, beliebig oft, dann muss am Ende "listeSpeichern()" aufgerufen
    //werden, weil immer die ganze Datei überschrieben wird. Hier werden dann die Informationen von jedem Element der vokListe in die Datei geschrieben. Bei 
    //Programmaufruf muss dann die Datei wieder in die vokListe eingelesen werden, damit alle Elemente wieder vorhanden sind und beim nächsten Speichern 
    //wieder mitgeschrieben werden.
    public static void listeSpeichern() throws IOException {
        for (Karteikarte krt : vokListe) {            
            lektOut.write(krt.getVokA() + ";" + krt.getVokZ() + ";");
            
            //Hilfssatz soll nur gespeichert werden, wenn einer eingegeben wurde, sonst soll der Speicherplatz an dieser Stelle leer bleiben. Ohne diese if-Abfrage
            //würde dort "null" als String abgespeichert werden.
            if(krt.getHilfssatz() != null){
                lektOut.write(krt.getHilfssatz() + ";");
            }else{
                lektOut.write(";");
            }
            
            lektOut.newLine();
        }
        lektOut.close();
    }

    public String getName() {
        return lName;
    }
    
    public File getFile(){
        return lektFile;
    }

    public void setVollstaendigGelernt() {
        vollGelernt = true;
    }

    public boolean getVollstaendigGelernt() {
        return vollGelernt;
    }

    public void setScore(int pScore) {
        score = pScore;
    }

    public int getScore() {
        return score;
    }

    public void setZielsprGefragt(boolean pZgefragt) {
        zielsprGefr = pZgefragt;
    }

    public boolean getZielsprGefragt() {
        return zielsprGefr;
    }

    public void setAktKarte(Karteikarte pAktuell) {
        aktKarte = pAktuell;
    }

    public Karteikarte getAktKarte() {
        return aktKarte;
    }
    
}
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
public class Lektion { //Problem: Lektionen verschiedener Sprachen dürfen nicht gleich heißen, evtl. lösen über Unterordner?

    private final String lName;
    private boolean vollGelernt;
    private int score; //score gibt an, wie viele Vokabeln bereits "gelernt" sind, also auf der grünen Lampe stehen
    private boolean zielsprGefr; //zielsprGefr == true bedeutet, dass der Nutzer die Vokabelbedeutung in der Zielsprache eingeben muss
    private Karteikarte aktKarte;
    private static ArrayList<Karteikarte> vokListe = new ArrayList<>();
    private static File lektFile;
    private String meinKurs;

    //erstellt schonmal den Writer, damit in mehreren Methodenabschnitten aufrufbar unabhängig von Schleifen etc.,
    //aber übergibt noch keine Datei, weil noch keine definiert
    private static BufferedWriter lektOut;
    private static BufferedReader lektIn;

    public static void main(String[] args) {
        //Lektion lekt = new Lektion();
        //vokListe.add(new Karteikarte());    
        //listeSpeichern();
    }

    public Lektion(String pMeinKurs) { //Konstruktor für wenn eine Lektion neu erstellt wird
        System.out.println("Lektionsname?");
        String pName = SystemInReader.readString();
        lName = pName;
        meinKurs = pMeinKurs;

        //erstellt im Ordner "Vokabellisten" eine csv-Datei, die nach dem Lektionsnamen benannt wird und in der die Inhalte aller Karteikarten gespeichert werden 
        //sollen, die zu dieser Lektion gehören 
        lektFile = new File("Vokabellisten\\" + pMeinKurs + "_" + pName + ".csv");

        try {
            lektOut = new BufferedWriter(new FileWriter(lektFile)); //übergibt dem Writer jetzt die Datei, auf die er schreiben soll
            lektIn = new BufferedReader(new FileReader(lektFile));
        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen der Lektion.");
        }

        //bei einer neuen Lektion soll als default-case nach der Zielsprache gefragt werden, wenn der Nutzer es umstellt, soll es dann aber gespeichert werden
        zielsprGefr = true;        
    }

    public Lektion(String pName, int pScore, boolean pVollGel, boolean pZielsprGefr, String pFile, String pMeinKurs) { //Konstruktor für wenn die gespeicherten Lektionen eingelesen werden
        lName = pName;
        score = pScore;
        vollGelernt = pVollGel;
        zielsprGefr = pZielsprGefr;
        lektFile = new File(pFile);
        meinKurs = pMeinKurs;
        try {            
            lektIn = new BufferedReader(new FileReader(lektFile));
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der gespeicherten Vokabeln (FileReader).");
        }
        listeEinlesen();
        
        try{
            lektOut = new BufferedWriter(new FileWriter(lektFile));
        }catch (IOException e){
            System.out.println("Fehler beim Einlesen der gespeicherten Vokabeln (FileWriter).");
        }
    }

    //Neue Karteikarte wird über "vokListe.add(new Karteikarte())" zur vokListe hinzugefügt, beliebig oft, dann muss am Ende "listeSpeichern()" aufgerufen
    //werden, weil immer die ganze Datei überschrieben wird. Hier werden dann die Informationen von jedem Element der vokListe in die Datei geschrieben. Bei 
    //Programmaufruf muss dann die Datei wieder in die vokListe eingelesen werden, damit alle Elemente wieder vorhanden sind und beim nächsten Speichern 
    //wieder mitgeschrieben werden.
    public static void listeSpeichern() { //ArrayOutOfBounds weil in csv-Dateien die ganzen neuen Felder nicht berücksichtigt; am besten morgen die ganzen Vokabeln einmal übers Programm eingeben
        try {
            for (Karteikarte krt : vokListe) {
                lektOut.write(krt.getVokA() + ";" + krt.getVokZ() + ";" + krt.getHilfssatz() + ";" + krt.getGelernt() + ";" + krt.getStatus() + ";" + krt.getFavorit());
                lektOut.newLine();
            }
            lektOut.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Vokabelliste.");
        }
    }

    private void listeEinlesen() {
        try {
            String zeile = lektIn.readLine();
            if (zeile != null) {
                while(!zeile.equals("endOfList")){
                    String[] split = zeile.split(";");
                    
                    //Parameter typecasten zu dem, was sie im Konstruktor sein müssen
                    String pVokA = split[0];
                    String pVokZ = split[1];
                    String pHS = split[2];
                    boolean pGel = Boolean.parseBoolean(split[3]);
                    int pStatus = Integer.parseInt(split[4]);
                    boolean pFav = Boolean.parseBoolean(split[5]);
                    
                    vokListe.add(new Karteikarte(pVokA, pVokZ, pHS, pGel, pStatus, pFav));
                    zeile = lektIn.readLine();
                }                  
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Vokabelliste.");
        }
    }
    
    public Karteikarte getVokAt(int pIndex){
        return vokListe.get(pIndex);
    }

    public String getName() {
        return lName;
    }

    public File getFile() {
        return lektFile;
    }

    public void setVollGelernt() {
        vollGelernt = true;
    }

    public boolean getVollGelernt() {
        return vollGelernt;
    }

    public void setScore(int pScore) {
        score = pScore;
    }

    public int getScore() {
        return score;
    }

    public void setZielsprGefr(boolean pZgefragt) {
        zielsprGefr = pZgefragt;
    }

    public boolean getZielsprGefr() {
        return zielsprGefr;
    }

    public void setAktKarte(Karteikarte pAktuell) {
        aktKarte = pAktuell;
    }

    public Karteikarte getAktKarte() {
        return aktKarte;
    }
    
    public void setMeinKurs(String pMeinKurs){
        meinKurs = pMeinKurs;
    }
    
    public String getMeinKurs(){
        return meinKurs;
    }

}

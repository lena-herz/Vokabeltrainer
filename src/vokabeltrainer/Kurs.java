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
public class Kurs {

    private final String kName;
    private ArrayList<Lektion> lekListe = new ArrayList<>();
    private File kursFile;

    //erstellt schonmal Writer und Reader, damit in mehreren Methodenabschnitten aufrufbar unabhängig von Schleifen etc.,
    //aber übergibt noch keine Datei, weil noch keine definiert
    private static BufferedWriter kursOut;
    private static BufferedReader kursIn;

    public static void main(String[] args) {

    }

    public Kurs() { //Konstruktor für wenn ein Kurs neu erstellt wird
        System.out.println("Kursname?");
        String pName = SystemInReader.readString();
        kName = pName;

        //erstellt im Ordner "Kurslisten" eine csv-Datei, die nach dem Kursnamen benannt ist und in der die Namen aller Lektionen gespeichert werden 
        //sollen, die zu diesem Kurs gehören
        kursFile = new File("Lektionslisten\\" + pName + ".csv");
        try {
            kursOut = new BufferedWriter(new FileWriter(kursFile));
            kursIn = new BufferedReader(new FileReader(kursFile));
        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen des Kurses.");
        }

        lekListe.add(new Lektion(this.kName)); //wenn ich einen Kurs erstelle, soll auch direkt eine Lektion hinzugefügt werden, sonst brauch ich den Kurs nicht
        listeSpeichern();
    }

    public Kurs(String pName, String pFile) { //Konstruktor für wenn die gespeicherten Kurse eingelesen werden
        kName = pName;
        kursFile = new File(pFile);
        try {
            kursIn = new BufferedReader(new FileReader(kursFile));
            listeEinlesen();
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Lektionsliste (FileReader).");
        }

        try { //getrennt vom Reader, weil der Writer sonst die Datei leert, bevor der Reader lesen kann
            kursOut = new BufferedWriter(new FileWriter(kursFile));
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der lektionsliste (FileWriter).");
        }
    }

    //Neue Lektion wird über "lekListe.add(new Lektion())" zur lekListe hinzugefügt, beliebig oft, dann muss am Ende listeSpeichern() aufgerufen werden, 
    //weil immer die ganze Datei überschrieben wird. Hier werden dann die Informationen von jedem Element der lekListe in die Datei geschrieben. Bei 
    //Programmaufruf muss dann die Datei wieder in die lekListe eingelesen werden, damit alle Elemente wieder vorhanden sind und beim nächsten Speichern 
    //wieder mitgeschrieben werden.
    private void listeSpeichern() {
        try {
            for (Lektion lek : lekListe) {
                kursOut.write(lek.getName() + ";" + lek.getScore() + ";" + lek.getVollGelernt() + ";" + lek.getZielsprGefr() + ";" + lek.getFile() + ";" + lek.getMeinKurs());
                kursOut.newLine();
            }
            kursOut.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Lektionsliste.");
        }
    }

    private void listeEinlesen() {
        try {
            String zeile = kursIn.readLine();
            if (zeile != null) {
                while (!zeile.equals("endOfList")) {
                    String[] split = zeile.split(";"); //teilt am ";"

                    //Parameter typecasten zu dem, was sie im Konstruktor sein müssen:
                    String pName = split[0];
                    int pScore = Integer.parseInt(split[1]);
                    boolean pVollGel = Boolean.parseBoolean(split[2]);
                    boolean pZielsprGefr = Boolean.parseBoolean(split[3]);
                    String pFile = split[4];
                    String pMeinKurs = split[5];

                    lekListe.add(new Lektion(pName, pScore, pVollGel, pZielsprGefr, pFile, pMeinKurs));//fügt abgespeicherte Kurse wieder zur kursListe hinzu mit den in "split" gespeicherten Informationen
                    zeile = kursIn.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Lektionsliste.");
        }
    }

    public Lektion getLektionAt(int pIndex) {
        return lekListe.get(pIndex);
    }

    public String getName() {
        return kName;
    }

    public File getFile() {
        return this.kursFile;
    }

}

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
    private static ArrayList<Lektion> lekListe = new ArrayList<>();
    private static File kursFile;
    
    //erstellt schonmal den Writer, damit in mehreren Methodenabschnitten aufrufbar unabhängig von Schleifen etc.,
    //aber übergibt noch keine Datei, weil noch keine definiert
    private static BufferedWriter kursOut;
    
    public static void main(String[] args) throws IOException {
//        Kurs kurs = new Kurs();
//        lekListe.add(new Lektion());
//        listeSpeichern();
    }
    
    public Kurs () throws IOException{
        System.out.println("Kursname?");
        String pName = SystemInReader.readString();
        kName = pName;
        
        //erstellt im Ordner "Kurslisten" eine csv-Datei, die nach dem Kursnamen benannt ist und in der die Namen aller Lektionen gespeichert werden 
        //sollen, die zu diesem Kurs gehören
        kursFile = new File("Lektionslisten\\" + pName + ".csv"); 
        kursOut = new BufferedWriter(new FileWriter(kursFile));
        Vokabeltrainer.addKurs(this);
    }
    
    //Neue Lektion wird über "lekListe.add(new Lektion())" zur lekListe hinzugefügt, beliebig oft, dann muss am Ende listeSpeichern() aufgerufen werden, 
    //weil immer die ganze Datei überschrieben wird. Hier werden dann die Informationen von jedem Element der lekListe in die Datei geschrieben. Bei 
    //Programmaufruf muss dann die Datei wieder in die lekListe eingelesen werden, damit alle Elemente wieder vorhanden sind und beim nächsten Speichern 
    //wieder mitgeschrieben werden.
    public static void listeSpeichern() throws IOException {
        for (Lektion lek : lekListe) {            
            kursOut.write(lek.getName() + ";" + lek.getScore() + ";" + lek.getVollstaendigGelernt() + ";" + lek.getZielsprGefragt() + ";" + lek.getFile() + ";");            
            kursOut.newLine();
        }
        kursOut.close();
    }
    
    public String getName(){
        return kName;
    }
    
    public File getFile(){
        return kursFile;
    }
    
}

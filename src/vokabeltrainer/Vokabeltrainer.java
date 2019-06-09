/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.ArrayList;

public class Vokabeltrainer {
    
    //komische NullPointerExceptions beim Speichern...

    private static final ArrayList<Kurs> kursListe = new ArrayList<>();

    //erstellt schonmal den Writer, damit in mehreren Methodenabschnitten aufrufbar unabhängig von Schleifen etc.,
    //aber übergibt noch keine Datei, weil hier die IOException nicht gethrowt werden kann
    private static BufferedWriter trainOut;
    private static BufferedReader trainIn;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try { //hier muss das Programm rein            
            //erstmal alles Gespeicherte einlesen:
            trainIn = new BufferedReader(new FileReader("Kursliste.csv"));
            listeEinlesen(); //liest alle Kurse ein, die lesen ihre Lektion ein und die wiederum ihre Vokabeln

            trainOut = new BufferedWriter(new FileWriter("Kursliste.csv"));
            printMenu();
//            Kurs tempKurs = kursListe.get(0);
//            Lektion tempLekt = tempKurs.getLektionAt(0);
//            Karteikarte tempKarte = tempLekt.getVokAt(0);
//            System.out.println(tempKarte.getVokA() + " - " + tempKarte.getVokZ());
        } catch (IOException e) { //hier fangen wir Fehler auf, die ganz zum Schluss noch übrig sind und sonst nirgendwo behandelt werden
            System.out.println("Upsi. Irgendwo ist ein Input/Output schief gelaufen, aber ich weiß nicht genau wo. Sorry... ");
        }
    }

    public static void printMenu() {

        System.out.println("Welche Aktion möchten Sie ausführen?");
        System.out.println("1: neue Lektion hinzufügen");
        System.out.println("2: Programm beenden"); //wenn Änderung, dann auch in while-Schleife
        int menu = SystemInReader.readInt();
        while (menu != 2) {
            while (menu != 1) {
                System.out.println("Keine Option. Bitte geben Sie 1 oder 2 ein.");
                menu = SystemInReader.readInt();
            }
            switch (menu) {
                case 1:
                    System.out.println("Zu welchem Kurs gehört die Lektion?");
                    String eingKursname = SystemInReader.readString();
//                    System.out.println("Alles richtig geschrieben? : " + eingKursname); //die Sache mit den Tippfehlern
//                    System.out.println("1: nein");
//                    System.out.println("2: ja");
//                    int janein = SystemInReader.readInt();
//                    if(janein == 1){
//                        eingKursname = SystemInReader.readString();
//                    }                            

                    boolean vorhanden = false;
                    for (Kurs kurs : kursListe) { //wenn schon ein Kurs mit dem eingegebenen Namen existiert, soll die Lektion zu diesem Kur hinzugefügt werden
                        if (kurs.getName().equals(eingKursname)) {
                            System.out.println("vorhanden");
                            kurs.addLektion();
                            vorhanden = true;
                        }
                    }

                    //wenn das Programm hier angekommen ist und vorhanden nicht auf true gestellt wurde, existiert noch kein Kurs mit dem eingegebenen Namen 
                    //also wird ein neuer erstellt und dort eine Lektion hinzugefügt
                    if (vorhanden == false) {
                        System.out.println("nicht vorhanden");
                        kursListe.add(new Kurs(eingKursname));
                    }

                    //hier gehts direkt wieder ins Hauptmenü
                    System.out.println();
                    printMenu();
                    menu = SystemInReader.readInt();
                    break;
            }
        }
        //wenn ~2~ eingegeben wurde, um das Programm zu beenden wird alles gespeichert und das Programm beendet; hier könnte man noch eine Nachfrage einfügen, 
        //ob wirklich beendet werden soll
        listeSpeichern();
        System.exit(0);

    }

    public static void listeSpeichern() { //überschreibt vorhandene Datei "Kursliste.csv" mit allen Elementen, die jetzt in der kursListe sind        
        try {
            for (Kurs kurs : kursListe) {
                trainOut.write(kurs.getName() + ";" + "Lektionslisten\\"+kurs.getName()+".csv" + ";");
                trainOut.newLine();
            }
            trainOut.write("endOfList");
            trainOut.close();
            System.out.println("Kursliste gespeichert");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Kursliste.");
        }
    }

    //liest Zeile für Zeile die Datei "Kursliste.csv" ein, teilt am ";" und speichert entsprechend Kursnamen und Dateinamen der Lektionsliste in der kursListe ab
    //Problem mit Umlauten und wahrscheinlich auch nicht-lateinischen Schriftsätzen
    private static void listeEinlesen() {
        try {
            String zeile = trainIn.readLine();
            if (zeile != null) { //wenn Datei nicht leer
                while (!zeile.equals("endOfList")) { //"endOfList" markiert das Ende der Datei, wird bei listeSpeichern() immer ans Ende gesetzt
                    System.out.println(zeile);
                    String[] split = zeile.split(";"); //teilt am ";"
                    kursListe.add(new Kurs(split[0], split[1])); //fügt abgespeicherte Kurse wieder zur kursListe hinzu mit den in "split" gespeicherten Informationen
                    zeile = trainIn.readLine();
                }
            }
            trainIn.close(); //immer schließen, sonst gehts nicht
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Kursliste.");
        }
    }

}

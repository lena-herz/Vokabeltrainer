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

    private static ArrayList<Kurs> kursListe = new ArrayList<>();

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
            System.out.println("Upsi. Irgendwo ist etwas schief gelaufen, aber ich weiß nicht genau wo. Sorry... ");
        }
    }

    public static void printMenu() {

        System.out.println("Welche Aktion möchten Sie ausführen?");
        System.out.println("1: neuen Kurs anlegen");
        System.out.println("2: Programm beenden"); //wenn Änderung, dann auch in while-Schleife
        int menu = SystemInReader.readInt();
        while (menu != 2) {
            while (menu != 1) {
                System.out.println("Keine Option. Bitte geben Sie 1 oder 2 ein.");
                menu = SystemInReader.readInt();
            }
            switch (menu) {
                case 1:
                    kursListe.add(new Kurs());
                    listeSpeichern();

                    System.out.println();
                    printMenu();
                    menu = SystemInReader.readInt();
                    break;
            }
        }

    }

    public static void listeSpeichern() { //überschreibt vorhandene Datei "Kursliste.csv" mit allen Elementen, die jetzt in der kursListe sind        
        try {
            for (Kurs kurs : kursListe) {
                trainOut.write(kurs.getName() + ";" + kurs.getFile());
                trainOut.newLine();
            }
            trainOut.write("endOfList");
            trainOut.close();
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
                while (!zeile.equals("endOfList")) { //"endOfList markiert das Ende der Datei, wird bei listeSpeichern() immer ans Ende gesetzt
                    String[] split = zeile.split(";"); //teilt am ";"
                    kursListe.add(new Kurs(split[0], split[1])); //fügt abgespeicherte Kurse wieder zur kursListe hinzu mit den in "split" gespeicherten Informationen
                    zeile = trainIn.readLine();
                }
            }
            trainIn.close(); //immer schließen, sonst gehts nicht
//        for(Kurs kurs : kursListe){
//            System.out.println(kurs.getName() + " - " + kurs.getFile());
//        }
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Kursliste.");
        }
    }

}

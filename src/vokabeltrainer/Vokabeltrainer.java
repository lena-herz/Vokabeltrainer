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
    private static File trainFile = new File("Kursliste.csv");

    //erstellt schonmal den Writer, damit in mehreren Methodenabschnitten aufrufbar unabhängig von Schleifen etc.,
    //aber übergibt noch keine Datei, weil noch keine definiert
    //private static BufferedWriter trainOut = new BufferedWriter(new FileWriter(trainFile));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
    }

    public static void printVokmenu() {
        System.out.println("Welche Aktion möchten Sie ausführen?");
        System.out.println("1: neue Karteikarte anlegen");
        System.out.println("2: Programm beenden"); //wenn Änderung, dann auch in while-Schleife oben
        int menu = SystemInReader.readInt();
        while (menu != 2) {
            while (menu != 1) {
                System.out.println("Keine Option. Bitte geben Sie 1 oder 2 ein.");
                menu = SystemInReader.readInt();
            }
            switch (menu) {
                case 1:
                    Karteikarte kartetmp = new Karteikarte();
                    System.out.println("hinzufügen:" + kartetmp.getVokA() + " - " + kartetmp.getVokZ());
                    //ersteLektion.vokHinzufuegen(kartetmp);

                    System.out.println();
                    printVokmenu();
                    menu = SystemInReader.readInt();
                    break;
            }
        }
    }

    public static void addKurs(Kurs pKurs) {
        kursListe.add(pKurs);
    }

//    public static void listeSpeichern() throws IOException {
//        for (Kurs kurs : kursListe) {
//            trainOut.write(kurs.getName() + ";" + kurs.getFile() + ";");
//            trainOut.newLine();
//        }
//        trainOut.close();
//    }

}

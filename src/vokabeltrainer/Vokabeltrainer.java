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
import java.text.*;

public class Vokabeltrainer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Lektionen in Excel eingeben, als .csv speichern, einlesen mit bufferedReader(new FileReader("Datei")), schreiben mit bufferedWriter
        Kurs englisch = new Kurs("Englisch"); //muss noch nutzerbestimmt gemacht werden bzw bei Eingabe der Vokabel
        Lektion ersteLektion = new Lektion("ersteLektion"); //s.o. 
        englisch.lekHinzufuegen(ersteLektion);
        
        printMenu();
        int menu = SystemInReader.readInt();
        while(menu!=2){
            while(menu!=1){
                System.out.println("Keine Option. Bitte geben Sie 1 oder 2 ein.");
                menu = SystemInReader.readInt();
            }
            switch(menu){
                case 1:
                    //überlegen, ob wir diese Abfragen mit in die vokHinzufuegen() packen und dann ohne Parameter; dann wäre hier die main Methode übersichtlicher
                    System.out.println("Bedeutung der Vokabel in der Ausgangssprache?");
                    String ausgspr = SystemInReader.readString();
                    System.out.println("Bedeutung der Vokabel in der Zielsprache?");
                    String zielspr = SystemInReader.readString();
                    Karteikarte kartetmp = new Karteikarte(ausgspr, zielspr);
                    ersteLektion.vokHinzufuegen(kartetmp);
                    
                    System.out.println();
                    printMenu();
                    menu = SystemInReader.readInt();
                    break;
            }
        }
        System.out.println("Vielen Dank! Programm wird beendet.");
    }
    
    public static void printMenu(){
        System.out.println("Welche Aktion möchten Sie ausführen?");
        System.out.println("1: neue Karteikarte anlegen");
        System.out.println("2: Programm beenden"); //wenn Änderung, dann auch in while-Schleife oben
    }
    
}

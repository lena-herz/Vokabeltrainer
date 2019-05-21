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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vokabeltrainer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Lektionen in Excel eingeben, als .csv speichern, einlesen mit bufferedReader(new FileReader("Datei")), schreiben mit bufferedWriter
        User user1 = new User("Username", "Kennwort");
//        try {
//            user1 = new User("Username", "Kennwort");
//        } catch (IOException ex) {
//            Logger.getLogger(Vokabeltrainer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Kurs englisch = new Kurs("Englisch"); //muss noch nutzerbestimmt gemacht werden bzw bei Eingabe der Vokabel
        Lektion ersteLektion = new Lektion("ersteLektion"); //s.o. 
        englisch.lekHinzufuegen(ersteLektion);

//        if(user1.getUname().equals("Username") && user1.getKennwort().equals("Kennwort")){
//            user1.setUname();
//            user1.setKennwort();
//        }
        while (user1.getUexistent()==false) {
            System.out.println("Username?");
            String eingName = SystemInReader.readString();

            System.out.println("Kennwort?");
            String eingKennwort = SystemInReader.readString();
            try {
                user1 = new User(eingName, eingKennwort);
            } catch (IOException ex) {
                Logger.getLogger(Vokabeltrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            user1.setUexistent(true);
        }
        
        printUsermenu();
        int auswahlU = SystemInReader.readInt();
        while (auswahlU != 2) {
            while (auswahlU != 1) {
                System.out.println("Keine Option. Bitte geben Sie 1 oder 2 ein.");
                auswahlU = SystemInReader.readInt();
            }
            switch (auswahlU) {
                case 1:
                    //System.out.println("Username eingeben:");
                    //String eingUname = SystemInReader.readString();
                    System.out.println("Kennwort eingeben:");
                    String eingKennw = SystemInReader.readString();

                    if (eingKennw.equals(user1.getKennwort())) {
                        printVokmenu();
                        int menu = SystemInReader.readInt();
                        while (menu != 2) {
                            while (menu != 1) {
                                System.out.println("Keine Option. Bitte geben Sie 1 oder 2 ein.");
                                menu = SystemInReader.readInt();
                            }
                            switch (menu) {
                                case 1:
                                    //überlegen, ob wir diese Abfragen mit in die vokHinzufuegen() packen und dann ohne Parameter; dann wäre hier die main Methode übersichtlicher
                                    System.out.println("Bedeutung der Vokabel in der Ausgangssprache?");
                                    String ausgspr = SystemInReader.readString();
                                    System.out.println("Bedeutung der Vokabel in der Zielsprache?");
                                    String zielspr = SystemInReader.readString();
                                    Karteikarte kartetmp = new Karteikarte(ausgspr, zielspr);
                                    ersteLektion.vokHinzufuegen(kartetmp);

                                    System.out.println();
                                    printVokmenu();
                                    menu = SystemInReader.readInt();
                                    break;
                            }
                        }
                        System.out.println("Vielen Dank! Programm wird beendet.");
                        auswahlU = 2;
                    } else {
                        System.out.println("falsches Kennwort");
                        printUsermenu();
                        auswahlU = SystemInReader.readInt();
                    }
                    break;
            }
        }

    }

    public static void printVokmenu() {
        System.out.println("Welche Aktion möchten Sie ausführen?");
        System.out.println("1: neue Karteikarte anlegen");
        System.out.println("2: Programm beenden"); //wenn Änderung, dann auch in while-Schleife oben
    }

    public static void printUsermenu() {
        System.out.println("Welche Aktion möchten Sie ausführen?");
        System.out.println("1: Anmelden");
        //System.out.println("2: Neues Nutzerkonto anlegen");
        System.out.println("2: Programm beenden");
    }
}

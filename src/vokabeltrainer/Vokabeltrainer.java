/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;

public class Vokabeltrainer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Lektionen in Excel eingeben, als .csv speichern, einlesen mit bufferedReader, schreiben mit bufferedWriter
        Kurs englisch = new Kurs("Englisch");
        Lektion ersteLektion = new Lektion("ersteLektion");
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
        System.out.println("2: Programm beenden");
    }
    
}

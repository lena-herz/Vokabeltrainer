/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;

/**
 *
 * @author Lena
 */
public class Vokabeltrainer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("Start");
        
        printMenu();
        int menu = SystemInReader.readInt();
        while(menu!=2){
            while(menu!=1){
                System.out.println("Keine Option. Bitte geben Sie 1 oder 2");
                menu = SystemInReader.readInt();
            }
            switch(menu){
                case 1:
                    System.out.println("Bedeutung der Vokabel in der Ausgangssprache?");
                    String ausgspr = SystemInReader.readString();
                    System.out.println("Bedeutung der Vokabel in der Zielsprache?");
                    String zielspr = SystemInReader.readString();
                    Karteikarte karte1 = new Karteikarte(ausgspr, zielspr);
                    System.out.println("Deutsche Bedeutung: " + karte1.getVokA());
                    System.out.println("Spanische BEdeutung: " + karte1.getVokZ());
                    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;
import java.util.ArrayList;

/**
 *
 * @author Lena
 */
public class Kurs {
    private String kName;
    private ArrayList<Lektion> kursListe = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("Kurs");
    }
    
    public Kurs (String pName){
        kName = pName;
    }
    
    public void lekHinzufuegen(Lektion pLektion){                
        kursListe.add(pLektion);
        
//        System.out.println();
//        int i = 1;
//        for (Lektion lektion : kursListe){
//            System.out.println(i++ + "." + lektion.getName());
//        }
    }
}

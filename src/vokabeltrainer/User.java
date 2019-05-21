/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lena
 */
public class User {

    private String uName;
    private String kennwort;
    private File uDaten;
    private boolean uExistent = false;

    public static void main(String[] args) {
        System.out.println("User");
    }

    public User(String pName, String pKennwort) throws IOException {
        uName = pName;
        kennwort = pKennwort;
        uDaten = new File("user1_Daten.csv");  
        try (BufferedWriter outDaten = new BufferedWriter(new FileWriter(uDaten))) {
            outDaten.write(uName + ";" + kennwort + ";" + uExistent);
        }        
    }
    
    

    public String getUname() {
        return uName;
    }

//    public void setUname() {
//        System.out.println("Username?");
//        String eingUname = SystemInReader.readString();
//
//        uname = eingUname;
//    }

    public String getKennwort() {
        return kennwort;
    }

//    public void setKennwort() {
//        System.out.println("Kennwort?");
//        String eingKennw = SystemInReader.readString();
//
//        kennwort = eingKennw;
//    }
    
    public BufferedReader getUdaten(){
        try {
                BufferedReader inDaten = new BufferedReader(new FileReader(uDaten));
                return inDaten;
            } catch (IOException ex) {
                Logger.getLogger(Vokabeltrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    
    public boolean getUexistent(){
        try {
                BufferedReader inExist = new BufferedReader(new FileReader(uDaten));
                String zeile = inExist.readLine();
                while(zeile!= null){
                    String[] split = zeile.split(";");
                    System.out.println(split[2]);
                    boolean tempExist = Boolean.parseBoolean(split[2]);
                    return tempExist;
                }
            } catch (IOException ex) {
                Logger.getLogger(Vokabeltrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
    
    public void setUexistent(boolean pEx){
        uExistent = pEx;
        try (BufferedWriter outExist = new BufferedWriter(new FileWriter(uDaten))) {
            outExist.flush();
            outExist.write(uName + ";" + kennwort + ";" + uExistent);
        }catch (IOException ex) {
                Logger.getLogger(Vokabeltrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

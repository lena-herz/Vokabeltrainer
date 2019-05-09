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
public class Karteikarte {
    private static String vokA;
    private static String vokZ;
    private static String hilfssatz;
    private static boolean gelernt;
    private static int status;
    private static boolean favorit;
    
    public Karteikarte(String pVokA, String pVokZ){
        pVokA = vokA;
        pVokZ = vokZ;
    }
    
    public static String getVokA(){
        return vokA;
    }
    
}

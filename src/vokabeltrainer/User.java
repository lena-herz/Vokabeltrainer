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
public class User {
    private String uname;
    private String kennwort;
    
    public static void main(String[] args) {
        System.out.println("User");
    }
    
    public User(String pName, String pKennwort){
        uname = pName;
        kennwort = pKennwort;
    }
    
    public String getUname(){
        return uname;
    }
    
    public void setUname(){
        System.out.println("Username?");
        String eingUname = SystemInReader.readString();
        
        uname = eingUname;
    }
    
    public String getKennwort(){
        return kennwort;
    }
    
    public void setKennwort(){
        System.out.println("Kennwort?");
        String eingKennw = SystemInReader.readString();
        
        kennwort = eingKennw;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeltrainer.src.gui; //muss immer korrekten Namen mit den Verzeichnissen durch Punkte getrennt erhalten

//Import-Anweisungen
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
//import javax.swing.JDialog; wäre einfaches Fenster ohne Menü
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Event;
import java.awt.Image;
import java.util.EventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lara Meyer
 */
public class GUI extends JFrame {

    //Konstruktor erstellen
    public GUI() {
        //super Konstruktor mit Fenstertitel aufrufen
        super("Digitaler Vokabeltrainer");
        //Größe und weitere Details zum JFrame angeben 
        setSize(500, 500);
        setLayout(new BorderLayout()); //Layout muss festgelegt werden
        add(createMenuPanel(), BorderLayout.WEST); //sorgt dafür, dass das Panel auch dem Frame zugefügt wird
        add(createKartenPanel(), BorderLayout.CENTER);
        add(createEingabePanel(), BorderLayout.SOUTH);
        add(createStatusPanel(), BorderLayout.NORTH);
        add(createDoPanel(), BorderLayout.EAST);
        //add(createScorePanel(), BorderLayout.PAGE_END);
        //pageend und north überschreiben sich leider ....muss evtl noch ein panel eingebaut werden vorher mit borderlayout im Norden, dazu textarea und score dann adden?
        //pack(); //würde Größe an Inhalt anpassen. Habe ich aber zunächst händisch eingestellt
        setLocationRelativeTo(null); //das Fenster in die Mitte vom Bildschirm setzen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);//JFrame sichtbar machen
    }

    private JPanel createMenuPanel() {
        JPanel menupanel = new JPanel();
        //Layout festlegen
        menupanel.setLayout(new BoxLayout(menupanel, BoxLayout.Y_AXIS));
        //Farbe festlegen
        menupanel.setBackground(new java.awt.Color(0, 145, 153));
        

        //Buttons erstellen
        //hier Schleife, damit immer so viele Buttons erstellt werden, wie Kurse vorhanden
        JButton add = new JButton("Vokabellisten erstellen");
        JButton kurs1 = new JButton("Englisch"); //im Orangenen die Verbindung zu dem vom Nutzer gewählten Kursnamen legen
        JButton kurs2 = new JButton("Spanisch");
        JButton kurs3 = new JButton("Französisch");
        //Die Buttons dem Panel hinzufügen
        menupanel.add(kurs1);
        menupanel.add(kurs2);
        menupanel.add(kurs3);
        menupanel.add(add);
        return menupanel;
    }
    // ****Statt Menu mal mit Buttons und Popup.Menüs bauen****

    /* private JPanel createMenuPanel(){ 
            
        JPanel menupanel = new JPanel();
        // Erstellen einer Menüleiste
        JMenuBar bar = new JMenuBar();
        menupanel.add(bar);
        JMenu menu = new JMenu("Vokabeln lernen");
        // Menü wird der Menüleiste hinzugefügt
        bar.add(menu);
        JMenu menu2 = new JMenu ("Vokabellisten erstellen");
        bar.add(menu2);
        JMenuItem item1 = new JMenuItem("");
        // fügen das JMenuItem dem JMenu hinzu
        menu.add(item1);
        JMenuItem item2 = new JMenuItem("Lektion 1.2");
        menu.add(item2);
        JSeparator sep = new JSeparator();
        // JSeparator wird JMenu hinzugefügt         
        menu.add(sep);
        return menupanel;
    }*/

    private JPanel createKartenPanel() {
        JPanel kartenpanel = new JPanel();
        JLabel vokAbfrage = new JLabel("die Umwelt"); //hier Verbindung zu den Vokabeln, die angezeigt werden sollen
        kartenpanel.add(vokAbfrage);
        // Legt eine schwarze einfache Linie als Border um das JPanel
        kartenpanel.setBorder(BorderFactory.createLineBorder(Color.white));
        return kartenpanel;

    }

    private JPanel createEingabePanel() {
        JPanel eingabepanel = new JPanel();
        //1-zeiliges und 50-spaltiges Textfeld wird 
        JTextArea eingabefeld = new JTextArea(3, 20);
        //Text für das Textfeld wird gesetzt
        eingabefeld.setText("environment"); //hier den Bezug zu den Vokabeln herstellen, die angezeigt werden sollen
        //Zeilenumbruch wird eingeschaltet
        eingabefeld.setLineWrap(true);
        //Zeilenumbrüche nur nach ganzen Wörtern
        eingabefeld.setWrapStyleWord(true);
        eingabepanel.add(eingabefeld);
        return eingabepanel;
    }
        
        //Icon-buttons Häkchen, Kreuz und ? hinzufügen
    private JPanel createDoPanel(){
        JPanel dopanel = new JPanel();
        JButton häkchen = new JButton(
            new ImageIcon("./Tick.png"));
        dopanel.add(häkchen);
        JButton kreuz = new JButton(
            new ImageIcon("./Kreuz.png"));
        dopanel.add(kreuz);
        JButton hilfssatz = new JButton(
            new ImageIcon("./Message.png"));
        dopanel.add(hilfssatz);
        return dopanel;
    }
   
    private JPanel createStatusPanel(){
        JPanel statuspanel = new JPanel();
        //an diese Labels die Bedingung anknüpfen, dass sie nur angezeigt werden, wenn der entsprechdne Integer dafür besteht.
        JLabel statuslabelrot = new JLabel(
                    new ImageIcon ("./Lampe_rot.png")); //verwenden absoluten Pfad ggf. wenn nihct funktional?
                //relativer Pfad wäre es nicht notwendig die exakte Lage des Bildes zu kennen. ./Lampe_rot.png wobei der Punkt aktuellen Standort angibt
        JLabel statuslabelgelb = new JLabel(
                    new ImageIcon ("./Lampe_gelb.png"));
        JLabel statuslabelgrün = new JLabel(
                    new ImageIcon ("./Lampe_gruen.png"));
        JLabel statuslabelausgeschaltet = new JLabel(
                    new ImageIcon ("./Lampe_ausgeschaltet.png"));
        statuspanel.add(statuslabelrot);
        statuspanel.add(statuslabelgelb);
        statuspanel.add(statuslabelgrün);
        statuspanel.add(statuslabelausgeschaltet);
        return statuspanel;
    }

    private JPanel createScorePanel(){ //wird iwie nihct angezeigt...
        JPanel scorepanel = new JPanel();
        JLabel scorelabel = new JLabel("Score: 13/20 gelernt; noch: 7"); //hier entsprechend Verbindung zu dem errechneten Score aus Code. Ggf. für Zahlen halt Variablne einsetzen, sodass es veränderkich wird?
        scorepanel.add(scorelabel);
        return scorepanel;
    }
    public static void main(String[] args) {
        new GUI();

    }
}

    


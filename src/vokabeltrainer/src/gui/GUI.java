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
import javax.swing.JToggleButton;

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
        add(createStatusPanel(), BorderLayout.NORTH);
        add(createSouthPanel(), BorderLayout.SOUTH);
        add(createRichtungPanel(), BorderLayout.EAST);
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

    private JPanel createKartenPanel() {
        JPanel kartenpanel = new JPanel();
        JLabel vokAbfrage = new JLabel("die Umwelt"); //hier Verbindung zu den Vokabeln, die angezeigt werden sollen
        kartenpanel.add(vokAbfrage);
        // Legt eine schwarze einfache Linie als Border um das JPanel
        kartenpanel.setBorder(BorderFactory.createLineBorder(Color.white));
        return kartenpanel;

    }
    
    private JPanel createRichtungPanel(){
        JPanel richtungpanel = new JPanel();
        JToggleButton ausgsprgefr = new JToggleButton("Ausgangsprache gefragt");
        richtungpanel.add(ausgsprgefr);
        JToggleButton zielsprgefr = new JToggleButton("Zielsprache gefragt");
        richtungpanel.add(zielsprgefr);
        return richtungpanel;
    }
    private JPanel createSouthPanel(){
        JPanel southpanel = new JPanel();
        
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
        southpanel.add(eingabepanel);
        
        JPanel dopanel = new JPanel();
        JButton häkchen = new JButton();
        ImageIcon ihäkchen = new ImageIcon(new ImageIcon("./Tick.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        häkchen.setIcon(ihäkchen);
        dopanel.add(häkchen);
        
        JButton kreuz = new JButton();
        ImageIcon ikreuz = new ImageIcon(new ImageIcon("./Kreuz.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        kreuz.setIcon(ikreuz);
        dopanel.add(kreuz);
        
        JButton hilfssatz = new JButton();
        ImageIcon ihilfssatz = new ImageIcon(new ImageIcon("./Message.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        hilfssatz.setIcon(ihilfssatz);
        dopanel.add(hilfssatz);
        southpanel.add(dopanel);
        
        JPanel scorepanel = new JPanel();
        JLabel scorelabel = new JLabel("Score: 13/20 gelernt; noch: 7"); //hier entsprechend Verbindung zu dem errechneten Score aus Code. Ggf. für Zahlen halt Variablne einsetzen, sodass es veränderkich wird?
        scorepanel.add(scorelabel);    
        southpanel.add(scorepanel);
        return southpanel;
    }

    private JPanel createStatusPanel(){
        JPanel statuspanel = new JPanel();
        //an diese Labels die Bedingung anknüpfen, dass sie nur angezeigt werden, wenn der entsprechdne Integer dafür besteht.
        JLabel statuslabelrot = new JLabel();
        ImageIcon statusrot = new ImageIcon(new ImageIcon("./Lampe_rot.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelrot.setIcon(statusrot);
                   
        //verwenden absoluten Pfad ggf. wenn nihct funktional?
        //relativer Pfad wäre es nicht notwendig die exakte Lage des Bildes zu kennen. ./Lampe_rot.png wobei der Punkt aktuellen Standort angibt
        JLabel statuslabelgelb = new JLabel();
        ImageIcon statusgelb = new ImageIcon(new ImageIcon("./Lampe_gelb.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelgelb.setIcon(statusgelb);
                   
        JLabel statuslabelgrün = new JLabel();
        ImageIcon statusgrün = new ImageIcon(new ImageIcon("./Lampe_grün.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelgrün.setIcon(statusgrün);
        
        JLabel statuslabelausgeschaltet = new JLabel();
        ImageIcon statusausgeschaltet = new ImageIcon(new ImageIcon("./Lampe_ausgeschaltet.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelausgeschaltet.setIcon(statusausgeschaltet);
        
        statuspanel.add(statuslabelrot);
        statuspanel.add(statuslabelgelb);
        statuspanel.add(statuslabelgrün);
        statuspanel.add(statuslabelausgeschaltet);
        return statuspanel;
    }

    public static void main(String[] args) {
        new GUI();

    }
}

    


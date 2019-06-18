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
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
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
//        //Layout festlegen
        menupanel.setLayout(new BoxLayout(menupanel, BoxLayout.Y_AXIS));
//        //Farbe festlegen
        menupanel.setBackground(new java.awt.Color(0, 145, 153)); 
        
//            /*  Die Button-Struktur mal ersetzt durch JComboBoxen. Dort kann man dann aus auklappender Liste auswählen. Aktuell sich die Arrays hier noch händisch eingegeben, 
//                aber Ziel ist es, dass hier die Lektionsnamen geladen werden und in einer Schleife entstprechend bestimmen, wann eine ComboBox mit Kurs erstellt wird
//                allerdings müsste, wollte man den Kursnamen mit anzeigen jeweils ein Panel über die CB gelegt werden mit dme entsprechdnen Label...
//                Was ist nun besser? Das mit den Buttons?
//                Noch eine Alternative wäre es vielleicht mit einem FileChooser zu arbeiten, wo dann die Lektionsliste ausgewählt wird, die direkt geladen wird? (vgl. https://www.java-tutorial.org/jfilechooser.html)*/
//        // Array für JComboBox
//        String comboBoxListeEng[] = {"Science and Technology", "Shopping",
//            "Sustainability", "History of GB", "Thomas Tallis School",
//            "A day in the city", "Slavery in the US", "Colonism",
//            "Democracy and Politics"};
//        String comboBoxListeSpa[] = {"En la tienda", "El mar",
//            "Las fiestas", "Cristobál Cólon"};
//        String comboBoxListeFr[] = {"Zahlen bis 20", "Zahlen ab 20"};
//        
//        //JComboBox mit den Einträgen erstellen
//        JComboBox lektionsauswahlEng = new JComboBox(comboBoxListeEng);
//        JComboBox lektionsauswahlSpa = new JComboBox (comboBoxListeSpa);
//        JComboBox lektionsauswahlFr = new JComboBox (comboBoxListeFr);
//        
//        //JComboBox wird Panel hinzugefügt
//        menupanel.add(lektionsauswahlEng);
//        menupanel.add(lektionsauswahlSpa);
//        menupanel.add(lektionsauswahlFr);

    //Buttons erstellen
        //hier Schleife, damit immer so viele Buttons erstellt werden, wie Kurse vorhanden
        JButton kurs1 = new JButton("Englisch - Science and technology"); 
//        kurs1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //Methode, was der depp machen soll dann...abfrgane ();
//            }
        kurs1.setFont(new Font("Dialog", 0, 20));
        JButton kurs2 = new JButton("Spanisch - En la tienda");
        kurs2.setFont(new Font("Dialog", 0, 20));
        JButton kurs3 = new JButton("Sprache - Lektion");
        kurs3.setFont(new Font("Dialog", 0, 20));
        JButton kurs4 = new JButton("Sprache - Lektion");
        kurs4.setFont(new Font("Dialog", 0, 20));
        JButton kurs5 = new JButton("Sprache - Lektion");
        kurs5.setFont(new Font("Dialog", 0, 20));
        JButton kurs6 = new JButton("Sprache - Lektion");
        kurs6.setFont(new Font("Dialog", 0, 20));
        JButton kurs7 = new JButton("Sprache - Lektion");
        kurs7.setFont(new Font("Dialog", 0, 20));
        JButton kurs8 = new JButton("Sprache - Lektion");
        kurs8.setFont(new Font("Dialog", 0, 20));
        JButton kurs9 = new JButton("Sprache - Lektion");
        kurs9.setFont(new Font("Dialog", 0, 20));
        JButton kurs10 = new JButton("Sprache - Lektion");
        kurs10.setFont(new Font("Dialog", 0, 20));
        
        //Die Buttons dem Panel hinzufügen
        menupanel.add(kurs1);
        menupanel.add(kurs2);
        menupanel.add(kurs3);
        menupanel.add(kurs4);
        menupanel.add(kurs5);
        menupanel.add(kurs6);
        menupanel.add(kurs7);
        menupanel.add(kurs8);
        menupanel.add(kurs9);
        menupanel.add(kurs10);
        return menupanel;
    }

    private JPanel createKartenPanel() {
        JPanel kartenpanel = new JPanel();
        JLabel vokAbfrage = new JLabel("die Umwelt"); //hier Verbindung zu den Vokabeln, die angezeigt werden sollen
        vokAbfrage.setFont(new Font("Dialog", 0, 20));  
        kartenpanel.add(vokAbfrage);
        // Legt eine weiße einfache Linie als Border um das JPanel
        kartenpanel.setBorder(BorderFactory.createLineBorder(Color.white));
        return kartenpanel;
    }
    
    private JPanel createRichtungPanel(){
        JPanel richtungpanel = new JPanel();
        richtungpanel.setLayout(new BoxLayout(richtungpanel, BoxLayout.Y_AXIS));
        ButtonGroup richtungBG = new ButtonGroup(); 
        JRadioButton zielsprgefr = new JRadioButton("Zielsprache gefragt");
        zielsprgefr.setFont(new Font("Dialog", 0, 20));
        richtungBG.add(zielsprgefr); 
        richtungpanel.add(zielsprgefr); 
        JRadioButton ausgsprgefr = new JRadioButton("Ausgangssprache gefragt"); 
        ausgsprgefr.setFont(new Font("Dialog", 0, 20));
        richtungBG.add(ausgsprgefr); 
        richtungpanel.add(ausgsprgefr); 
        
        JPanel scorepanel = new JPanel();
        JLabel scorelabel = new JLabel("Score: 13/20 gelernt; noch: 7"); //hier entsprechend Verbindung zu dem errechneten Score aus Code. Ggf. für Zahlen halt Variablne einsetzen, sodass es veränderkich wird?
        scorelabel.setFont(new Font("Dialog", 0, 20));
        scorepanel.add(scorelabel);    
        richtungpanel.add(scorepanel);
        
        return richtungpanel;
    }
    
    private JPanel createSouthPanel(){
        JPanel southpanel = new JPanel();
        
        JPanel eingabepanel = new JPanel();
       //1-zeiliges und 50-spaltiges Textfeld wird 
        JTextArea eingabefeld = new JTextArea(3, 20);
        //Text für das Textfeld wird gesetzt
        eingabefeld.setText("environment"); //hier den Bezug zu den Vokabeln herstellen, die angezeigt werden sollen
        eingabefeld.setFont(new Font("Dialog", 0, 20));
        //Zeilenumbruch wird eingeschaltet
        eingabefeld.setLineWrap(true);
        //Zeilenumbrüche nur nach ganzen Wörtern
        eingabefeld.setWrapStyleWord(true);
        eingabepanel.add(eingabefeld);
        southpanel.add(eingabepanel);
        
        JPanel dopanel = new JPanel();
        JButton tick = new JButton();
        ImageIcon itick = new ImageIcon(new ImageIcon("./Tick.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        tick.setIcon(itick);
        dopanel.add(tick);
        
        JButton kreuz = new JButton();
        ImageIcon ikreuz = new ImageIcon(new ImageIcon("./Kreuz.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        kreuz.setIcon(ikreuz);
        dopanel.add(kreuz);
        
        JButton hilfssatz = new JButton();
        ImageIcon ihilfssatz = new ImageIcon(new ImageIcon("./Message.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        hilfssatz.setIcon(ihilfssatz);
        dopanel.add(hilfssatz);
        southpanel.add(dopanel);
        
        return southpanel;
    }

    private JPanel createStatusPanel(){
        JPanel statuspanel = new JPanel();
        //an diese Labels die Bedingung anknüpfen, dass sie nur angezeigt werden, wenn der entsprechende Integer dafür besteht.
        JLabel statuslabelrot = new JLabel();
        ImageIcon statusrot = new ImageIcon(new ImageIcon("./Lampe_rot.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelrot.setIcon(statusrot);
                   
        JLabel statuslabelgelb = new JLabel();
        ImageIcon statusgelb = new ImageIcon(new ImageIcon("./Lampe_gelb.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelgelb.setIcon(statusgelb);
                           
        JLabel statuslabelgruen = new JLabel();
        ImageIcon statusgruen = new ImageIcon(new ImageIcon("./Lampe_gruen.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelgruen.setIcon(statusgruen);
        
        JLabel statuslabelausgeschaltet = new JLabel();
        ImageIcon statusausgeschaltet = new ImageIcon(new ImageIcon("./Lampe_ausgeschaltet.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabelausgeschaltet.setIcon(statusausgeschaltet);
        //Keine Ahnung, warum denn der grüne nicht angezeigt wird...auch FlowLayout hat nicht geholfen
        statuspanel.add(statuslabelrot);
        statuspanel.add(statuslabelgelb);
        statuspanel.add(statuslabelgruen);
        statuspanel.add(statuslabelausgeschaltet);
        return statuspanel;
    }

}

    


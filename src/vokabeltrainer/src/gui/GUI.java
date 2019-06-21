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
import vokabeltrainer.Karteikarte;
import vokabeltrainer.Kurs;
import vokabeltrainer.Lektion;
import vokabeltrainer.SystemInReader;
import static vokabeltrainer.Vokabeltrainer.alleLektionen;
import static vokabeltrainer.Vokabeltrainer.kursListe;

/**
 *
 * @author Lara Meyer
 */
public class GUI extends JFrame {

    public JPanel kartenPanel;
    public JPanel menuPanel;
    public JPanel statusPanel;
    public JPanel southPanel;
    public JPanel richtungPanel;
    public JLabel vokAbfrage;
    public Lektion[] alleLektionen;
    public JButton button0;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JTextArea eingabefeld;
    public JButton kreuz;
    public JButton tick;
    public JButton hilfssatz;
    public JLabel statuslabel;
    public Karteikarte aktKarte;
    public String antwort;
    public Lektion aktLektion;
    public int abfrageIndex;
    public boolean fZielsprGefr = true; //fZielsprGefr == true bedeutet, dass der Nutzer die Vokabelbedeutung in der Zielsprache eingeben muss
    public JLabel scorelabel;

    //Konstruktor erstellen
    public GUI() {
        //super Konstruktor mit Fenstertitel aufrufen
        super("Digitaler Vokabeltrainer");
        //Größe und weitere Details zum JFrame angeben 
        setSize(1000, 500);
        setLayout(new BorderLayout()); //Layout muss festgelegt werden
        add(menuPanel = createMenuPanel(), BorderLayout.WEST); //sorgt dafür, dass das Panel auch dem Frame zugefügt wird
        add(kartenPanel = createKartenPanel(), BorderLayout.CENTER);
        add(statusPanel = createStatusPanel(), BorderLayout.NORTH);
        add(southPanel = createSouthPanel(this), BorderLayout.SOUTH);
        add(richtungPanel = createRichtungPanel(), BorderLayout.EAST);
        //pack(); //würde Größe an Inhalt anpassen. Habe ich aber zunächst händisch eingestellt
        setLocationRelativeTo(null); //das Fenster in die Mitte vom Bildschirm setzen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);//JFrame sichtbar machen

    }

    //muss für Konstruktor erstmal leere Buttons erstellen, weil das sost mit der Reihenfolge der Übergabewerte beim Aufrufen in main nicht passt, weil die
    //Lektionen zu dem Zeitpunkt noch nicht eingelesen sein können
    private JPanel createMenuPanel() {
        JPanel menupanel = new JPanel();
//        //Layout festlegen
        menupanel.setLayout(new BoxLayout(menupanel, BoxLayout.Y_AXIS));
//        //Farbe festlegen
        menupanel.setBackground(new java.awt.Color(0, 145, 153));

        button0 = new JButton();
        button0.setFont(new Font("Dialog", 0, 20));

        button1 = new JButton();
        button1.setFont(new Font("Dialog", 0, 20));

        button2 = new JButton();
        button2.setFont(new Font("Dialog", 0, 20));

        button3 = new JButton();
        button3.setFont(new Font("Dialog", 0, 20));

        button4 = new JButton();
        button4.setFont(new Font("Dialog", 0, 20));

        //Die Buttons dem Panel hinzufügen
        menupanel.add(button0);
        menupanel.add(button1);
        menupanel.add(button2);
        menupanel.add(button3);
        menupanel.add(button4);
        return menupanel;
    }

    //hier werden die leeren Buttons dann entfernt und durch die neuen mit richtiger Aufschrift ersetzt
    public JPanel updateMenuPanel(GUI pGui) {

//        JPanel menupanel = new JPanel();
//          //Layout festlegen
//        menupanel.setLayout(new BoxLayout(menupanel, BoxLayout.Y_AXIS));
//          //Farbe festlegen
//        menupanel.setBackground(new java.awt.Color(0, 145, 153));
//            /*  Die Button-Struktur mal ersetzt durch JComboBoxen. Dort kann man dann aus auklappender Liste auswählen. Aktuell sind die Arrays hier noch händisch eingegeben, 
//                aber Ziel ist es, dass hier die Lektionsnamen geladen werden und in einer Schleife entstprechend bestimmen, wann eine ComboBox mit Kurs erstellt wird
//                allerdings müsste, wollte man den Kursnamen mit anzeigen jeweils ein Panel über die CB gelegt werden mit dem entsprechenden Label...
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
        menuPanel.remove(button0);
        menuPanel.remove(button1);
        menuPanel.remove(button2);
        menuPanel.remove(button3);
        menuPanel.remove(button4);

        //hier jeweils im ActionListener die Lektion zuweisen, die in lekAuflisten() an entsprechender Stelle steht
        Lektion lektion0 = alleLektionen[0];
        button0 = new JButton(lektion0.getMeinKurs() + " - " + lektion0.getName());
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alleLektionen[0] != null) {
                    aktLektion = alleLektionen[0];
                    abfrageIndex = 0;
                    updateScore();
                    alleLektionen[0].abfrage(pGui, abfrageIndex);
                    eingabefeld.setText("");
                }
            }
        });
        button0.setFont(new Font("Dialog", 0, 20));

        button1 = new JButton(alleLektionen[1].getMeinKurs() + " - " + alleLektionen[1].getName());
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alleLektionen[1] != null) {
                    aktLektion = alleLektionen[1];
                    abfrageIndex = 0;
                    updateScore();
                    alleLektionen[1].abfrage(pGui, abfrageIndex);
                    eingabefeld.setText("");
                }
            }
        });
        button1.setFont(new Font("Dialog", 0, 20));

        button2 = new JButton(alleLektionen[2].getMeinKurs() + " - " + alleLektionen[2].getName());
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alleLektionen[2] != null) {
                    aktLektion = alleLektionen[2];
                    abfrageIndex = 0;
                    updateScore();
                    alleLektionen[2].abfrage(pGui, abfrageIndex);
                    eingabefeld.setText("");
                }
            }
        });
        button2.setFont(new Font("Dialog", 0, 20));

        button3 = new JButton(alleLektionen[3].getMeinKurs() + " - " + alleLektionen[3].getName());
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alleLektionen[3] != null) {
                    aktLektion = alleLektionen[3];
                    abfrageIndex = 0;
                    updateScore();
                    alleLektionen[3].abfrage(pGui, abfrageIndex);
                    eingabefeld.setText("");
                }
            }
        });
        button3.setFont(new Font("Dialog", 0, 20));

        button4 = new JButton(alleLektionen[4].getMeinKurs() + " - " + alleLektionen[4].getName());
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alleLektionen[4] != null) {
                    aktLektion = alleLektionen[4];
                    abfrageIndex = 0;
                    updateScore();
                    alleLektionen[4].abfrage(pGui, abfrageIndex);
                    eingabefeld.setText("");
                }
            }
        });
        button4.setFont(new Font("Dialog", 0, 20));

        //Die Buttons dem Panel hinzufügen
        menuPanel.add(button0);
        menuPanel.add(button1);
        menuPanel.add(button2);
        menuPanel.add(button3);
        menuPanel.add(button4);
        return menuPanel;
    }

    private JPanel createKartenPanel() {
        kartenPanel = new JPanel();
        vokAbfrage = new JLabel("Vokabel Ausgangssprache"); //hier Verbindung zu den Vokabeln, die angezeigt werden sollen
        vokAbfrage.setFont(new Font("Dialog", 0, 20));
        kartenPanel.add(vokAbfrage);
        // Legt eine weiße einfache Linie als Border um das JPanel
        kartenPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        return kartenPanel;
    }

    public JLabel setAbfrage(String pAbfrage) {
        kartenPanel.remove(vokAbfrage);
        vokAbfrage = new JLabel(pAbfrage);
        vokAbfrage.setFont(new Font("Dialog", 0, 20));
        kartenPanel.add(vokAbfrage);
        // Legt eine weiße einfache Linie als Border um das JPanel
        kartenPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        return vokAbfrage;
    }

    private JPanel createRichtungPanel() {
        JPanel richtungpanel = new JPanel();
        richtungpanel.setLayout(new BoxLayout(richtungpanel, BoxLayout.Y_AXIS));
        ButtonGroup richtungBG = new ButtonGroup();

        JRadioButton bZielsprGefr = new JRadioButton("Zielsprache gefragt");
        bZielsprGefr.setFont(new Font("Dialog", 0, 20));
        bZielsprGefr.setSelected(true); //damit am Anfang auch ausgewählt, da fZielsprGefr bei Programmstart auf true steht, dementsprechend auch die Anzeige
        bZielsprGefr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fZielsprGefr = true;
                if (aktKarte != null) {
                    setAbfrage(aktKarte.getVokA());
                    kartenPanel.updateUI();
                }
            }
        });
        richtungBG.add(bZielsprGefr);
        richtungpanel.add(bZielsprGefr);

        JRadioButton bAusgsprGefr = new JRadioButton("Ausgangssprache gefragt");
        bAusgsprGefr.setFont(new Font("Dialog", 0, 20));
        bAusgsprGefr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fZielsprGefr = false;
                if (aktKarte != null) {
                    setAbfrage(aktKarte.getVokZ());
                    kartenPanel.updateUI();
                }
            }
        });
        richtungBG.add(bAusgsprGefr);
        richtungpanel.add(bAusgsprGefr);

        JPanel scorepanel = new JPanel();
        scorelabel = new JLabel("Score: 0/0 gelernt - noch: 0"); //hier entsprechend Verbindung zu dem errechneten Score aus Code. Ggf. für Zahlen halt Variablne einsetzen, sodass es veränderkich wird?
        scorelabel.setFont(new Font("Dialog", 0, 20));
        scorepanel.add(scorelabel);
        richtungpanel.add(scorepanel);

        return richtungpanel;
    }

    private JPanel createSouthPanel(GUI pGui) {
        JPanel southpanel = new JPanel();

        JPanel eingabepanel = new JPanel();
        //1-zeiliges und 50-spaltiges Textfeld wird         
        eingabefeld = new JTextArea(3, 20);
        //Text für das Textfeld wird gesetzt
        eingabefeld.setText("Übersetzung eingeben..."); //hier den Bezug zu den Vokabeln herstellen, die angezeigt werden sollen
        eingabefeld.setFont(new Font("Dialog", 0, 20));
        //Zeilenumbruch wird eingeschaltet
        eingabefeld.setLineWrap(true);
        //Zeilenumbrüche nur nach ganzen Wörtern
        eingabefeld.setWrapStyleWord(true);
        eingabepanel.add(eingabefeld);
        southpanel.add(eingabepanel);

        JPanel checkpanel = new JPanel();
        tick = new JButton();
        ImageIcon itick = new ImageIcon(new ImageIcon("./Tick.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        tick.setIcon(itick);
        tick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                antwort = eingabefeld.getText();
                useTick();
                eingabefeld.setText("");
                abfrageIndex++;
                if (abfrageIndex < aktLektion.getAnzahlVok()) {//ruft Abfrage für nächste Vokabel in der Liste auf
                    aktLektion.abfrage(pGui, abfrageIndex);
                } else {//fängt nach letzter Vokabel wieder bei erster an
                    abfrageIndex = 0;
                    aktLektion.abfrage(pGui, abfrageIndex);
                }
            }
        });
        checkpanel.add(tick);

        kreuz = new JButton();
        ImageIcon ikreuz = new ImageIcon(new ImageIcon("./Kreuz.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        kreuz.setIcon(ikreuz);
        checkpanel.add(kreuz);

        hilfssatz = new JButton();
        ImageIcon ihilfssatz = new ImageIcon(new ImageIcon("./Message.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        hilfssatz.setIcon(ihilfssatz);
        checkpanel.add(hilfssatz);
        southpanel.add(checkpanel);

        return southpanel;
    }

    private JPanel createStatusPanel() {
        JPanel statuspanel = new JPanel();
        //an diese Labels die Bedingung anknüpfen, dass sie nur angezeigt werden, wenn der entsprechende Integer dafür besteht.

        statuslabel = new JLabel();
        ImageIcon statusausgeschaltet = new ImageIcon(new ImageIcon("./Lampe_ausgeschaltet.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabel.setIcon(statusausgeschaltet);

//        JLabel statuslabelrot = new JLabel();
//        ImageIcon statusrot = new ImageIcon(new ImageIcon("./Lampe_rot.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//        statuslabelrot.setIcon(statusrot);
//
//        JLabel statuslabelgelb = new JLabel();
//        ImageIcon statusgelb = new ImageIcon(new ImageIcon("./Lampe_gelb.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//        statuslabelgelb.setIcon(statusgelb);
//
//        JLabel statuslabelgruen = new JLabel();
//        ImageIcon statusgruen = new ImageIcon(new ImageIcon("./Lampe_gruen.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//        statuslabelgruen.setIcon(statusgruen);
//
//        JLabel statLabAus = new JLabel();
//        ImageIcon statusausgeschaltet = new ImageIcon(new ImageIcon("./Lampe_ausgeschaltet.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//        statLabAus.setIcon(statusausgeschaltet);
//        statuspanel.add(statuslabelrot);
//        statuspanel.add(statuslabelgelb);
//        statuspanel.add(statuslabelgruen);
        statuspanel.add(statuslabel);
        return statuspanel;
    }

    public void setAlleLektionen(Lektion[] pArray) {
        alleLektionen = pArray;
    }

    public void useTick() {
        if (fZielsprGefr == true) {//gucken, in welchem Modus gerade abgefragt wird; hier muss Zielsprache eingegeben werden
            if (antwort.equals(aktKarte.getVokZ())) {//prüfen, ob Eingabe mit gespeicherter Übersetzung übereinstimmt
                //erhöht Status der aktuell abgefragten Karteikarte um 1; da aufgrund der Überprufung in abfrage() eine Karte mit grüner Lampe gar nicht
                //angezeigt wird, muss dieser Fall hier nicht mehr berücksichtigt werden
                int statZ = aktKarte.getStatus() + 1;
                aktKarte.setStatus(statZ);
                System.out.println(aktKarte.getStatus());//raus
                updateStatusPanel(aktKarte.getStatus());
                updateScore();
            } else {//wenn falsche Antwort gegeben, wird Lampe auf rot gesetzt
                aktKarte.setStatus(0);
                updateStatusPanel(aktKarte.getStatus());
                updateScore();
            }
        } else {//anderer Abfragemodus, hier muss Ausgangssprache eingegeben werden
            if (antwort.equals(aktKarte.getVokA())) {//prüfen, ob Eingabe mit gespeicherter Übersetzung übereinstimmt
                //s. oben
                System.out.println(aktKarte.getVokA() + " - richtig");
                int statA = aktKarte.getStatus() + 1;
                aktKarte.setStatus(statA);
                System.out.println(aktKarte.getStatus());//raus
                updateStatusPanel(aktKarte.getStatus());
                updateScore();
            } else {//wenn falsche Antwort gegeben, wird Lampe auf rot gesetzt
                System.out.println(aktKarte.getVokA() + " - falsch");
                aktKarte.setStatus(0);
                updateStatusPanel(aktKarte.getStatus());
                updateScore();
            }
        }
    }

    public void updateStatusPanel(int pStatus) {
        switch (pStatus) {
            case 0:
                ImageIcon statusRot = new ImageIcon(new ImageIcon("./Lampe_rot.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                statuslabel.setIcon(statusRot);
                statusPanel.updateUI();
                break;

            case 1:
                ImageIcon statusOrange = new ImageIcon(new ImageIcon("./Lampe_orange.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                statuslabel.setIcon(statusOrange);
                statusPanel.updateUI();
                break;

            case 2:
                ImageIcon statusGelb = new ImageIcon(new ImageIcon("./Lampe_gelb.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                statuslabel.setIcon(statusGelb);
                statusPanel.updateUI();
                break;

            case 3:
                ImageIcon statusGruen = new ImageIcon(new ImageIcon("./Lampe_gruen.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                statuslabel.setIcon(statusGruen);
                statusPanel.updateUI();
                break;
        }
    }

    public void updateScore() {
        int fullScore = aktLektion.getAnzahlVok();
        int anzGelernt = aktLektion.getAnzahlGel();
        int anzRest = fullScore - anzGelernt;
        scorelabel.setText("Score: " + anzGelernt + "/" + fullScore + " - noch:" + anzRest);
        richtungPanel.updateUI();
    }

}

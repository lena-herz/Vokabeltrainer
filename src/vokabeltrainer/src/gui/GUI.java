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
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import vokabeltrainer.Karteikarte;
import vokabeltrainer.Kurs;
import vokabeltrainer.Lektion;
import vokabeltrainer.Vokabeltrainer;

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
    public JLabel anzeigeLoesung; //hier soll korrekte Übersetzung angezeigt werden, wenn auf Kreuz geklickt wird
    public JLabel anzeigeHS; //hier soll Hilfssatz angezeigt werden, wenn auf Spreechblase geklickt wird
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
    public JButton weiter;
    public String eingKurs;
    private ArrayList<Kurs> alleKurse;
    private Kurs aktKurs; //für Eingabe einer neuen Letkion

    //Konstruktor erstellen
    public GUI() {
        //super Konstruktor mit Fenstertitel aufrufen
        super("Digitaler Vokabeltrainer");
        //Größe und weitere Details zum JFrame angeben 
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout()); //Layout muss festgelegt werden
        add(menuPanel = createMenuPanel(this), BorderLayout.WEST); //sorgt dafür, dass das Panel auch dem Frame zugefügt wird
        add(kartenPanel = createKartenPanel(), BorderLayout.CENTER);
        add(statusPanel = createStatusPanel(), BorderLayout.NORTH);
        add(southPanel = createSouthPanel(this), BorderLayout.SOUTH);
        add(richtungPanel = createRichtungPanel(), BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);//JFrame sichtbar machen

    }

    //muss für Konstruktor erstmal leere Buttons erstellen, weil das sonst mit der Reihenfolge der Übergabewerte beim Aufrufen in main nicht passt, weil die
    //Lektionen zu dem Zeitpunkt noch nicht eingelesen sein können
    private JPanel createMenuPanel(GUI pGui) {
        menuPanel = new JPanel();
        //Layout festlegen
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        //Farbe festlegen
        menuPanel.setBackground(new java.awt.Color(0, 145, 153));

        JButton neueLektion = new JButton("neue Lektion");
        neueLektion.setFont(new Font("Dialog", 0, 20));
        neueLektion.setForeground(Color.red);
        neueLektion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kartenPanel.removeAll();
                eingabeKurs(pGui);
            }
        });
        menuPanel.add(neueLektion);
        return menuPanel;
    }

    public void eingabeKurs(GUI pGui) {
        JLabel labEingKurs = new JLabel("Zu welchem Kurs gehört die Lektion?");
        labEingKurs.setFont(new Font("Dialog", 0, 20));
        labEingKurs.setForeground(Color.gray);
        labEingKurs.setOpaque(true);
        labEingKurs.setSize(400, 50);
        labEingKurs.setLocation(200, 210);
        kartenPanel.add(labEingKurs);

        JTextArea eingabeKurs = new JTextArea(1, 20);
        eingabeKurs.setFont(new Font("Dialog", 0, 20));
        eingabeKurs.setLineWrap(true);
        eingabeKurs.setWrapStyleWord(true);
        eingabeKurs.setOpaque(true);
        eingabeKurs.setSize(400, 50);
        eingabeKurs.setLocation(200, 250);
        kartenPanel.add(eingabeKurs);

        JLabel labNeueLektion = new JLabel("Wie heißt die Lektion?");
        labNeueLektion.setFont(new Font("Dialog", 0, 20));
        labNeueLektion.setForeground(Color.gray);
        labNeueLektion.setOpaque(true);
        labNeueLektion.setSize(400, 50);
        labNeueLektion.setLocation(200, 310);
        kartenPanel.add(labNeueLektion);

        JTextArea neueLektion = new JTextArea(1, 20);
        neueLektion.setFont(new Font("Dialog", 0, 20));
        neueLektion.setLineWrap(true);
        neueLektion.setWrapStyleWord(true);
        neueLektion.setOpaque(true);
        neueLektion.setSize(400, 50);
        neueLektion.setLocation(200, 350);
        kartenPanel.add(neueLektion);

        JButton kursBest = new JButton("bestätigen");
        kursBest.setFont(new Font("Dialog", 0, 15));
        kursBest.setOpaque(true);
        kursBest.setSize(150, 50);
        kursBest.setLocation(700, 370);
        kursBest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eingKurs = eingabeKurs.getText();
                String neueLektName = neueLektion.getText();
                boolean vorhanden = false;
                for (Kurs kurs : alleKurse) { //wenn schon ein Kurs mit dem eingegebenen Namen existiert, soll die Lektion zu diesem Kurs hinzugefügt werden
                    if (kurs.getName().equals(eingKurs)) {
                        aktKurs = kurs;
                        aktKurs.addLektion(neueLektName);
                        vorhanden = true;
                    }
                }
                //wenn hier vorhanden immer noch auf false steht, existiert noch kein Kurs mit dem eingegebenen Namen, also wird einer erstellt
                if (vorhanden == false) {
                    Kurs neuerKurs = new Kurs(eingKurs, pGui, neueLektName);
                    alleKurse.add(neuerKurs);
                    Vokabeltrainer.listeSpeichern();
                    aktKurs = neuerKurs;
                }
                kartenPanel.removeAll();
                eingabeLektion();
                eingKurs = null;
            }
        });
        kartenPanel.add(kursBest);

        kartenPanel.updateUI();
    }

    public void eingabeLektion() {

        JLabel labEingZielspr = new JLabel("Vokabelbedeutung in der Zielsprache?");
        labEingZielspr.setFont(new Font("Dialog", 0, 20));
        labEingZielspr.setForeground(Color.gray);
        labEingZielspr.setOpaque(true);
        labEingZielspr.setSize(400, 50);
        labEingZielspr.setLocation(200, 100);
        kartenPanel.add(labEingZielspr);

        JTextArea eingabeZielspr = new JTextArea(1, 20);
        eingabeZielspr.setFont(new Font("Dialog", 0, 20));
        eingabeZielspr.setLineWrap(true);
        eingabeZielspr.setWrapStyleWord(true);
        eingabeZielspr.setOpaque(true);
        eingabeZielspr.setSize(400, 50);
        eingabeZielspr.setLocation(200, 140);
        kartenPanel.add(eingabeZielspr);

        JLabel labEingAusgspr = new JLabel("Vokabelbedeutung in der Ausgangssprache?");
        labEingAusgspr.setFont(new Font("Dialog", 0, 20));
        labEingAusgspr.setForeground(Color.gray);
        labEingAusgspr.setOpaque(true);
        labEingAusgspr.setSize(400, 50);
        labEingAusgspr.setLocation(200, 200);
        kartenPanel.add(labEingAusgspr);

        JTextArea eingabeAusgspr = new JTextArea(1, 20);
        eingabeAusgspr.setFont(new Font("Dialog", 0, 20));
        eingabeAusgspr.setLineWrap(true);
        eingabeAusgspr.setWrapStyleWord(true);
        eingabeAusgspr.setOpaque(true);
        eingabeAusgspr.setSize(400, 50);
        eingabeAusgspr.setLocation(200, 240);
        kartenPanel.add(eingabeAusgspr);
        
        JLabel labEingHS = new JLabel("Hilfssatz?");
        labEingHS.setFont(new Font("Dialog", 0, 20));
        labEingHS.setForeground(Color.gray);
        labEingHS.setOpaque(true);
        labEingHS.setSize(400, 50);
        labEingHS.setLocation(200, 300);
        kartenPanel.add(labEingHS);

        JTextArea eingabeHS = new JTextArea("Es wurde kein Hilfssatz eingegeben.", 3, 20);
        eingabeHS.setFont(new Font("Dialog", 0, 20));
        eingabeHS.setLineWrap(true);
        eingabeHS.setWrapStyleWord(true);
        eingabeHS.setOpaque(true);
        eingabeHS.setSize(400, 100);
        eingabeHS.setLocation(200, 340);
        kartenPanel.add(eingabeHS);

        JButton nextVok = new JButton("nächste Vokabel");
        nextVok.setFont(new Font("Dialog", 0, 15));
        nextVok.setOpaque(true);
        nextVok.setSize(150, 50);
        nextVok.setLocation(700, 300);
        nextVok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String neueVokA = eingabeAusgspr.getText();
                String neueVokZ = eingabeZielspr.getText();
                String neueVokHS = eingabeHS.getText();
                aktLektion.addVokabel(neueVokA, neueVokZ, neueVokHS);
                eingabeAusgspr.setText("");
                eingabeZielspr.setText("");
                eingabeHS.setText("Es wurde kein Hilfssatz eingegeben.");
            }
        });
        kartenPanel.add(nextVok);

        JButton lektSpeichern = new JButton("Lektion speichern");
        lektSpeichern.setFont(new Font("Dialog", 0, 15));
        lektSpeichern.setOpaque(true);
        lektSpeichern.setSize(150, 50);
        lektSpeichern.setLocation(700, 400);
        lektSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String neueVokA = eingabeAusgspr.getText();
                String neueVokZ = eingabeZielspr.getText();
                String neueVokHS = eingabeHS.getText();
                aktLektion.addVokabel(neueVokA, neueVokZ, neueVokHS);
                kartenPanel.removeAll();
                buildKartenPanelAbfrage();
                aktKurs = null;
                aktLektion = null;
            }
        });
        kartenPanel.add(lektSpeichern);

        kartenPanel.updateUI();
    }

    public JPanel updateMenuPanel(GUI pGui, ArrayList<Lektion> pAlleLektionen) {
        //Buttons erstellen
        for (Lektion lektion : pAlleLektionen) {
            JButton lektButton = new JButton(lektion.getMeinKurs() + " - " + lektion.getName());
            lektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    kartenPanel.removeAll();
                    buildKartenPanelAbfrage();
                    aktLektion = lektion;
                    abfrageIndex = 0;
                    updateScore();
                    aktLektion.abfrage(pGui, abfrageIndex);
                    eingabefeld.setText("");
                    if (anzeigeLoesung.getText() != null) {
                        anzeigeLoesung.setText("");
                    }
                    kartenPanel.updateUI();
                }
            });
            lektButton.setFont(new Font("Dialog", 0, 20));
            menuPanel.add(lektButton);
        }

        return menuPanel;
    }

    private JPanel createKartenPanel() {
        kartenPanel = new JPanel();
        kartenPanel.setLayout(null);
        buildKartenPanelAbfrage();

        // Legt eine weiße einfache Linie als Border um das JPanel
        kartenPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        return kartenPanel;
    }

    //erstellt die Felder, die bei der Abfrage gebraucht werden, da beim Eingeben einer neuen Lektion das KartenPanel anders genutzt wird
    public void buildKartenPanelAbfrage() {
        vokAbfrage = new JLabel("Bitte Lektion wählen");
        vokAbfrage.setFont(new Font("Dialog", 0, 30));
        vokAbfrage.setOpaque(true);
        vokAbfrage.setSize(400, 50);
        vokAbfrage.setLocation(380, 30);
        kartenPanel.add(vokAbfrage);

        anzeigeLoesung = new JLabel();
        anzeigeLoesung.setFont(new Font("Dialog", 0, 30));
        anzeigeLoesung.setOpaque(true);
        anzeigeLoesung.setSize(400, 50);
        anzeigeLoesung.setLocation(380, 100);
        kartenPanel.add(anzeigeLoesung);

        anzeigeHS = new JLabel();
        anzeigeHS.setFont(new Font("Dialog", 0, 30));
        anzeigeHS.setOpaque(true);
        anzeigeHS.setSize(800, 100);
        anzeigeHS.setLocation(50, 500);
        kartenPanel.add(anzeigeHS);
        
        kartenPanel.updateUI();
    }

    public void setAbfrage(String pAbfrage) {
        vokAbfrage.setText(pAbfrage);
        kartenPanel.updateUI();
    }

    public void showLoesung(String pLoesung) {
        anzeigeLoesung.setText(pLoesung);
        kartenPanel.updateUI();
    }

    public void showHS(String pHS) {
        anzeigeHS.setText("<html>" + pHS + "</html>");
        kartenPanel.updateUI();
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
                    if (anzeigeLoesung.getText() != null) {
                        anzeigeLoesung.setText("");
                    }
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
                    if (anzeigeLoesung.getText() != null) {//wenn noch Übersetzung vom Kreuz angezeigt, soll die weggenommen werden
                        anzeigeLoesung.setText("");
                    }
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
        southPanel = new JPanel();

        JPanel eingabepanel = new JPanel();

        eingabefeld = new JTextArea(3, 20);
        eingabefeld.setText("Übersetzung eingeben...");
        eingabefeld.setFont(new Font("Dialog", 0, 20));
        eingabefeld.setLineWrap(true);
        eingabefeld.setWrapStyleWord(true);
        eingabepanel.add(eingabefeld);
        southPanel.add(eingabepanel);

        JPanel checkpanel = new JPanel();
        tick = new JButton();
        ImageIcon iTick = new ImageIcon(new ImageIcon("./Tick.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        tick.setIcon(iTick);
        tick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                antwort = eingabefeld.getText();
                useTick();
                eingabefeld.setText("");
                if (anzeigeHS.getText() != null) {
                    anzeigeHS.setText("");
                }
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
        ImageIcon iKreuz = new ImageIcon(new ImageIcon("./Kreuz.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        kreuz.setIcon(iKreuz);
        kreuz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aktKarte != null) {
                    if (fZielsprGefr == true) {
                        showLoesung(aktKarte.getVokZ());
                    } else {
                        showLoesung(aktKarte.getVokA());
                    }
                    weiter = new JButton("weiter");
                    weiter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            aktKarte.setStatus(0);
                            eingabefeld.setText("");
                            anzeigeLoesung.setText("");
                            if (anzeigeHS.getText() != null) {
                                anzeigeHS.setText("");
                            }
                            abfrageIndex++;
                            if (abfrageIndex < aktLektion.getAnzahlVok()) {//ruft Abfrage für nächste Vokabel in der Liste auf
                                aktLektion.abfrage(pGui, abfrageIndex);
                            } else {//fängt nach letzter Vokabel wieder bei erster an
                                abfrageIndex = 0;
                                aktLektion.abfrage(pGui, abfrageIndex);
                            }
                            southPanel.remove(weiter);
                            southPanel.updateUI();
                        }
                    });
                    southPanel.add(weiter);
                }
            }
        });
        checkpanel.add(kreuz);

        hilfssatz = new JButton();
        ImageIcon iHilfssatz = new ImageIcon(new ImageIcon("./Message.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        hilfssatz.setIcon(iHilfssatz);
        hilfssatz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHS(aktKarte.getHilfssatz());
            }
        });
        checkpanel.add(hilfssatz);

        southPanel.add(checkpanel);
        return southPanel;
    }

    private JPanel createStatusPanel() {
        JPanel statuspanel = new JPanel();
        //an diese Labels die Bedingung anknüpfen, dass sie nur angezeigt werden, wenn der entsprechende Integer dafür besteht.

        statuslabel = new JLabel();
        ImageIcon statusausgeschaltet = new ImageIcon(new ImageIcon("./Lampe_ausgeschaltet.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        statuslabel.setIcon(statusausgeschaltet);

        statuspanel.add(statuslabel);
        return statuspanel;
    }

    public void useTick() {//hier findet die eigentliche Überprüfung der Eingabe statt
        if (fZielsprGefr == true) {//gucken, in welchem Modus gerade abgefragt wird; hier muss Zielsprache eingegeben werden
            if (antwort.equals(aktKarte.getVokZ())) {//prüfen, ob Eingabe mit gespeicherter Übersetzung übereinstimmt
                //erhöht Status der aktuell abgefragten Karteikarte um 1; da aufgrund der Überprufung in abfrage() eine Karte mit grüner Lampe gar nicht
                //angezeigt wird, muss dieser Fall hier nicht mehr berücksichtigt werden
                int statZ = aktKarte.getStatus() + 1;
                aktKarte.setStatus(statZ);
                new java.util.Timer().schedule(new java.util.TimerTask() {//macht noch nicht, was es soll
                    @Override
                    public void run() {
                        updateStatusPanel(aktKarte.getStatus());
                    }
                }, 10000);
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
                updateStatusPanel(aktKarte.getStatus());
                updateScore();
            } else {//wenn falsche Antwort gegeben, wird Lampe auf rot gesetzt
                System.out.println(antwort);
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
        statusPanel.updateUI();
    }

    public void updateScore() {
        int fullScore = aktLektion.getAnzahlVok();
        int anzGelernt = aktLektion.getAnzahlGel();
        int anzRest = fullScore - anzGelernt;
        scorelabel.setText("Score: " + anzGelernt + "/" + fullScore + " - noch:" + anzRest);
        richtungPanel.updateUI();
    }

    public void setAlleKurse(ArrayList<Kurs> pKurse) {
        alleKurse = pKurse;
    }

}

package vue;

import vue.FenetreNewEvent;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.ControleurCourse;
import controleur.ControleurEvenement;
import controleur.ControleurPilote;
import controleur.ControleurSeanceEssai;
import controleur.ControleurTop;
import controleur.ControleurTrainPneu;
import controleur.ControleurVoiture;

public class WelcomeWindow extends JFrame implements ActionListener{
	
	
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	// Charge les différents noms de langue dans un tableau pour la comboBox des langues
	String[] languagesString = { BUNDLE.getString("FrenchLanguageString"), BUNDLE.getString("EnglishLanguageString") };

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	// Declarations des controleurs
	
	private ControleurEvenement controlEve;
	private ControleurCourse controlC;
	private ControleurSeanceEssai controlSE;
	private ControleurVoiture controlV;
	private ControleurPilote controlP;
	private ControleurTrainPneu controlTP ;
	private ControleurTop controlT;
	
	
	// Déclarations des éléments de la fenêtre
	private JFrame frmAccueil;
	public JComboBox boxLanguages;
	public JLabel labelLanguage;
	public JButton buttonOpenEvent;
	public JButton buttonNewEvent;

	/**
	 * Create the application.
	 */
	public WelcomeWindow(ControleurEvenement controlEve, ControleurCourse controlC, ControleurSeanceEssai controlSE, 
			ControleurVoiture controlV, ControleurPilote controlP, ControleurTrainPneu controlTP, ControleurTop controlT) {
		
		this.controlEve = controlEve;
		this.controlC = controlC;
		this.controlSE = controlSE;
		this.controlV = controlV;
		this.controlP = controlP;
		this.controlTP = controlTP;
		this.controlT = controlT;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAccueil = new JFrame();
		frmAccueil.setResizable(false);
		frmAccueil.setSize(500, 200);
		frmAccueil.setLocation((screenSize.width - frmAccueil.getWidth()) / 2, (screenSize.height - frmAccueil.getHeight()) / 3);
		frmAccueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAccueil.setTitle(BUNDLE.getString("test.frmChronoProject.title")); //$NON-NLS-1$
		frmAccueil.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panelAccueil = new JPanel();
		frmAccueil.getContentPane().add(panelAccueil);
		SpringLayout layoutAccueil = new SpringLayout();
		panelAccueil.setLayout(layoutAccueil);

		//Bouton nouvel événement
		buttonNewEvent = new JButton(BUNDLE.getString("NewEventString")); //$NON-NLS-1$
		layoutAccueil.putConstraint(SpringLayout.NORTH, buttonNewEvent, 55, SpringLayout.NORTH, panelAccueil);
		layoutAccueil.putConstraint(SpringLayout.WEST, buttonNewEvent, 80, SpringLayout.WEST, panelAccueil);
		buttonNewEvent.setPreferredSize(new Dimension(150,30));		
		panelAccueil.add(buttonNewEvent);
		buttonNewEvent.addActionListener(this);

		//Bouton Ouverture d'un événement existant
		buttonOpenEvent = new JButton(BUNDLE.getString("OpenEventString")); //$NON-NLS-1$
		layoutAccueil.putConstraint(SpringLayout.NORTH, buttonOpenEvent, 55, SpringLayout.NORTH, panelAccueil);
		layoutAccueil.putConstraint(SpringLayout.EAST, buttonOpenEvent, -80, SpringLayout.EAST, panelAccueil);
		buttonOpenEvent.setPreferredSize(new Dimension(150,30));
		panelAccueil.add(buttonOpenEvent);
		buttonOpenEvent.addActionListener(this);

		//Label de la ComboBox des langues
		labelLanguage = new JLabel(BUNDLE.getString("LanguageString")+" :");
		panelAccueil.add(labelLanguage);

		//ComboBox des langues
		boxLanguages = new JComboBox(languagesString);
		layoutAccueil.putConstraint(SpringLayout.WEST, boxLanguages, 398, SpringLayout.WEST, panelAccueil);
		layoutAccueil.putConstraint(SpringLayout.SOUTH, boxLanguages, -10, SpringLayout.SOUTH, panelAccueil);
		layoutAccueil.putConstraint(SpringLayout.NORTH, labelLanguage, 3, SpringLayout.NORTH, boxLanguages);
		layoutAccueil.putConstraint(SpringLayout.EAST, labelLanguage, -6, SpringLayout.WEST, boxLanguages);

		//Evénement de la comboBox
		boxLanguages.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent eventLanguage) {
				if (eventLanguage.getStateChange() == ItemEvent.SELECTED) {
					Object language = eventLanguage.getItem();
					if (language.toString().equals("Anglais")) {
						Locale.setDefault(Locale.ENGLISH);
						BUNDLE = ResourceBundle.getBundle("vue.messages", Locale.getDefault());
						updateLanguage();
						boxLanguages.setSelectedIndex(1);
					} else if (language.toString().equals("French")) {
						Locale.setDefault(Locale.FRENCH);
						BUNDLE = ResourceBundle.getBundle("vue.messages", Locale.getDefault());
						updateLanguage();
					}
				}
			}
		});
		panelAccueil.add(boxLanguages);
		frmAccueil.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==buttonOpenEvent) {
			//Traduit la fenêtre de sélection de fichier
		    JFileChooser.setDefaultLocale(Locale.getDefault());
		    JFileChooser chooser = new JFileChooser();			    
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		    }
		}
		else {
			new FenetreNewEvent(controlEve, controlC, controlSE, controlV, controlP, controlTP, controlT);
			frmAccueil.dispose();
			}
		
		
	}
	
	//Met à jour les différents textes qu'il faut traduire lorsque l'on change de langue
	private void updateLanguage() {
		buttonNewEvent.setText(BUNDLE.getString("NewEventString"));
		buttonOpenEvent.setText(BUNDLE.getString("OpenEventString"));
		labelLanguage.setText(BUNDLE.getString("LanguageString"));
		languagesString = new String[] { BUNDLE.getString("FrenchLanguageString"), BUNDLE.getString("EnglishLanguageString") };
		boxLanguages.setModel(new JComboBox(languagesString).getModel());
	}

}

package vue;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import controleur.ControleurCourse;
import controleur.ControleurEvenement;
import controleur.ControleurPilote;
import controleur.ControleurSeanceEssai;
import controleur.ControleurTop;
import controleur.ControleurTrainPneu;
import controleur.ControleurVoiture;

public class FenetreNewEvent extends JFrame implements ActionListener, Observer {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	//Variables pour manipuler les index de course/ seance d'essai/voiture

	private int idCourse;
	private int idSeance;
	private int idVoiture; 

	//Déclaration des différents controleurs:
	private ControleurEvenement controlEve;
	private ControleurCourse controlC;
	private ControleurSeanceEssai controlSE;
	private ControleurVoiture controlV;
	private ControleurPilote controlP;
	private ControleurTrainPneu controlTP ;
	private ControleurTop controlT;

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	// Déclarations des éléments de la fenêtre
	private JFrame frmNewEvent;
	private JLabel labelEventName;
	private JLabel lblTitleEvent;
	private JLabel labelTrackName;
	private JLabel labelM;
	private JLabel labelTrackLength;
	private JLabel labelPracticeSessions;
	private JLabel labelRaces;
	private JLabel labelCars;
	private JButton buttonStart;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JMenuBar menuBarEvent;
	private JMenu mnMenuBarEvent;
	private JTextField textFieldTrackName;
	private JTextField textFieldEventName;
	private JTextField textFieldTrackLength;
	private List listPracticeSessions;
	private List listRaces;
	private List listCars;
	private JMenu mnMenuBarEventLanguage;
	private JRadioButtonMenuItem mnMenuBarEventLanguageEnglish;
	private JRadioButtonMenuItem mnMenuBarEventLanguageFrench;

	ImageIcon startIcon = new ImageIcon(this.getClass().getResource("/resources/startIcon.png"));
	ImageIcon startIconPressed = new ImageIcon(this.getClass().getResource("/resources/startIconPressed.png"));
	ImageIcon addIcon = new ImageIcon(this.getClass().getResource("/resources/addIcon.png"));

	/*
	 * NOUVEAU
	 */
	ImageIcon editIcon = new ImageIcon(this.getClass().getResource("/resources/editIcon.png"));
	ImageIcon delIcon = new ImageIcon(this.getClass().getResource("/resources/delIcon.png"));



	private JButton buttonAddPracticeSession;
	private JButton buttonAddRace;

	private JButton buttonAddCar;

	private JButton buttonEditPracticeSession;

	private AbstractButton buttonDelPracticeSession;

	private AbstractButton buttonEditRace;

	private AbstractButton buttonDelRace;

	private AbstractButton buttonEditCar;

	private AbstractButton buttonDelCar;

	public FenetreNewEvent(ControleurEvenement controlEve, ControleurCourse controlC, ControleurSeanceEssai controlSE, 
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
	@SuppressWarnings("deprecation")
	private void initialize() {
		frmNewEvent = new JFrame();
		frmNewEvent.setResizable(false);
		frmNewEvent.setSize(1000, 700);
		frmNewEvent.setLocation((screenSize.width - frmNewEvent.getWidth()) / 2, (screenSize.height - frmNewEvent.getHeight()) / 3);
		frmNewEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewEvent.setTitle(BUNDLE.getString("CustomizationEventString")); //$NON-NLS-1$
		SpringLayout springLayout = new SpringLayout();
		frmNewEvent.getContentPane().setLayout(springLayout);

		// //BARRE DES MENUS////

		menuBarEvent = new JMenuBar();
		springLayout.putConstraint(SpringLayout.WEST, menuBarEvent, 0, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBarEvent, 0, SpringLayout.EAST, frmNewEvent.getContentPane());
		frmNewEvent.getContentPane().add(menuBarEvent);

		mnMenuBarEventLanguage = new JMenu(BUNDLE.getString("LanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguage.setHorizontalAlignment(SwingConstants.LEFT);
		menuBarEvent.add(mnMenuBarEventLanguage);
		// ANGLAIS//
		mnMenuBarEventLanguageEnglish = new JRadioButtonMenuItem(BUNDLE.getString("EnglishLanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventLanguageEnglish) {
				Locale.setDefault(Locale.ENGLISH);
				BUNDLE = ResourceBundle.getBundle("vue.messages", Locale.getDefault());
				updateLanguage();
			}
		});
		mnMenuBarEventLanguage.add(mnMenuBarEventLanguageEnglish);
		// FRANCAIS//
		mnMenuBarEventLanguageFrench = new JRadioButtonMenuItem(BUNDLE.getString("FrenchLanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageFrench.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventLanguageFrench) {
				Locale.setDefault(Locale.FRENCH);
				BUNDLE = ResourceBundle.getBundle("vue.messages", Locale.getDefault());
				updateLanguage();
			}
		});
		mnMenuBarEventLanguage.add(mnMenuBarEventLanguageFrench);

		ButtonGroup group = new ButtonGroup();
		group.add(mnMenuBarEventLanguageEnglish);
		group.add(mnMenuBarEventLanguageFrench);

		// //TITRE////

		lblTitleEvent = new JLabel(BUNDLE.getString("CustomizationEventString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, lblTitleEvent, 0, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTitleEvent, 1000, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTitleEvent, 16, SpringLayout.SOUTH, menuBarEvent);
		lblTitleEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleEvent.setFont(Police.getFontTitle());
		frmNewEvent.getContentPane().add(lblTitleEvent);

		// //NOM DE L'EVENT////

		labelEventName = new JLabel(BUNDLE.getString("NameString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelEventName, 15, SpringLayout.SOUTH, lblTitleEvent);
		springLayout.putConstraint(SpringLayout.WEST, labelEventName, 252, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelEventName, 308, SpringLayout.WEST, frmNewEvent.getContentPane());
		labelEventName.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewEvent.getContentPane().add(labelEventName);

		textFieldEventName = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldEventName, 3, SpringLayout.SOUTH, labelEventName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldEventName, 10, SpringLayout.EAST, labelEventName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldEventName, 400, SpringLayout.EAST, labelEventName);
		textFieldEventName.setText("");
		textFieldEventName.setPreferredSize(new Dimension(200, 20));
		frmNewEvent.getContentPane().add(textFieldEventName);
		textFieldEventName.addActionListener(this);

		// //NOM DU CIRCUIT////

		labelTrackName = new JLabel(BUNDLE.getString("TrackString"));
		springLayout.putConstraint(SpringLayout.NORTH, labelTrackName, 10, SpringLayout.SOUTH, textFieldEventName);
		springLayout.putConstraint(SpringLayout.EAST, labelTrackName, 0, SpringLayout.EAST, labelEventName);
		labelTrackName.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewEvent.getContentPane().add(labelTrackName);

		textFieldTrackName = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldTrackName, 3, SpringLayout.SOUTH, labelTrackName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldTrackName, 10, SpringLayout.EAST, labelTrackName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldTrackName, 0, SpringLayout.EAST, textFieldEventName);
		textFieldTrackName.setText("");
		textFieldTrackName.setColumns(10);
		frmNewEvent.getContentPane().add(textFieldTrackName);
		textFieldTrackName.addActionListener(this);

		// //LONGUEUR DU CIRCUIT////

		labelTrackLength = new JLabel(BUNDLE.getString("TrackLengthString"));
		springLayout.putConstraint(SpringLayout.NORTH, labelTrackLength, 10, SpringLayout.SOUTH, textFieldTrackName);
		springLayout.putConstraint(SpringLayout.EAST, labelTrackLength, 500, SpringLayout.WEST, frmNewEvent.getContentPane());
		labelTrackLength.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewEvent.getContentPane().add(labelTrackLength);

		textFieldTrackLength = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldTrackLength, 3, SpringLayout.SOUTH, labelTrackLength);
		springLayout.putConstraint(SpringLayout.WEST, textFieldTrackLength, 10, SpringLayout.EAST, labelTrackLength);
		springLayout.putConstraint(SpringLayout.EAST, textFieldTrackLength, -424, SpringLayout.EAST, frmNewEvent.getContentPane());
		textFieldTrackLength.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTrackLength.setText("");
		textFieldTrackLength.setColumns(10);
		frmNewEvent.getContentPane().add(textFieldTrackLength);
		textFieldTrackLength.addActionListener(this);

		labelM = new JLabel(BUNDLE.getString("FenetreNewEvent.lblM.text"));
		springLayout.putConstraint(SpringLayout.NORTH, labelM, 0, SpringLayout.NORTH, labelTrackLength);
		springLayout.putConstraint(SpringLayout.WEST, labelM, 10, SpringLayout.EAST, textFieldTrackLength);
		labelM.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewEvent.getContentPane().add(labelM);

		// //LISTE DES COURSES D'ESSAI////

		labelPracticeSessions = new JLabel(BUNDLE.getString("PracticeSessionsString")); //$NON-NLS-1$
		labelPracticeSessions.setFont(Police.getFontSubtitle());
		labelPracticeSessions.setHorizontalAlignment(SwingConstants.CENTER);
		labelPracticeSessions.setPreferredSize(new Dimension(200, 40));
		springLayout.putConstraint(SpringLayout.NORTH, labelPracticeSessions, 178, SpringLayout.SOUTH, lblTitleEvent);
		springLayout.putConstraint(SpringLayout.WEST, labelPracticeSessions,
				(frmNewEvent.getSize().width / 6) - (labelPracticeSessions.getPreferredSize().width / 2), SpringLayout.WEST,
				frmNewEvent.getContentPane());
		frmNewEvent.getContentPane().add(labelPracticeSessions);

		listPracticeSessions = new List();
		springLayout.putConstraint(SpringLayout.NORTH, listPracticeSessions, 5, SpringLayout.SOUTH, labelPracticeSessions);
		springLayout.putConstraint(SpringLayout.WEST, listPracticeSessions, 0, SpringLayout.WEST, labelPracticeSessions);
		springLayout.putConstraint(SpringLayout.SOUTH, listPracticeSessions, 240, SpringLayout.SOUTH, labelPracticeSessions);
		springLayout.putConstraint(SpringLayout.EAST, listPracticeSessions, 0, SpringLayout.EAST, labelPracticeSessions);
		frmNewEvent.getContentPane().add(listPracticeSessions);
		listPracticeSessions.setMultipleMode(false);

		buttonAddPracticeSession = new JButton(""); //$NON-NLS-1$
		buttonAddPracticeSession.setIcon(addIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonAddPracticeSession, 0, SpringLayout.SOUTH, listPracticeSessions);
		springLayout.putConstraint(SpringLayout.WEST, buttonAddPracticeSession, 0, SpringLayout.WEST, listPracticeSessions);
		buttonAddPracticeSession.setOpaque(false);
		buttonAddPracticeSession.setContentAreaFilled(false);
		buttonAddPracticeSession.setBorder(null);
		buttonAddPracticeSession.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonAddPracticeSession);
		buttonAddPracticeSession.addActionListener(this);

		/*
		 * NOUVEAU
		 */

		buttonEditPracticeSession = new JButton(""); //$NON-NLS-1$
		buttonEditPracticeSession.setIcon(editIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonEditPracticeSession, 0, SpringLayout.NORTH, buttonAddPracticeSession);
		springLayout.putConstraint(SpringLayout.WEST, buttonEditPracticeSession, 5, SpringLayout.EAST, buttonAddPracticeSession);
		buttonEditPracticeSession.setOpaque(false);
		buttonEditPracticeSession.setContentAreaFilled(false);
		buttonEditPracticeSession.setBorder(null);
		buttonEditPracticeSession.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonEditPracticeSession);
		buttonEditPracticeSession.addActionListener(this);

		buttonDelPracticeSession = new JButton(""); //$NON-NLS-1$
		buttonDelPracticeSession.setIcon(delIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonDelPracticeSession, 0, SpringLayout.NORTH, buttonEditPracticeSession);
		springLayout.putConstraint(SpringLayout.WEST, buttonDelPracticeSession, 5, SpringLayout.EAST, buttonEditPracticeSession);
		buttonDelPracticeSession.setOpaque(false);
		buttonDelPracticeSession.setContentAreaFilled(false);
		buttonDelPracticeSession.setBorder(null);
		buttonDelPracticeSession.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonDelPracticeSession);
		buttonDelPracticeSession.addActionListener(this);


		// //LISTE DES COURSES////

		labelRaces = new JLabel(BUNDLE.getString("RacesString")); //$NON-NLS-1$
		labelRaces.setFont(Police.getFontSubtitle());
		labelRaces.setHorizontalAlignment(SwingConstants.CENTER);
		labelRaces.setPreferredSize(new Dimension(200, 40));
		springLayout.putConstraint(SpringLayout.NORTH, labelRaces, 0, SpringLayout.NORTH, labelPracticeSessions);
		springLayout.putConstraint(SpringLayout.WEST, labelRaces, (frmNewEvent.getSize().width / 2) - (labelRaces.getPreferredSize().width / 2),
				SpringLayout.WEST, frmNewEvent.getContentPane());
		frmNewEvent.getContentPane().add(labelRaces);

		listRaces = new List();
		springLayout.putConstraint(SpringLayout.NORTH, listRaces, 5, SpringLayout.SOUTH, labelRaces);
		springLayout.putConstraint(SpringLayout.WEST, listRaces, 0, SpringLayout.WEST, labelRaces);
		springLayout.putConstraint(SpringLayout.SOUTH, listRaces, 240, SpringLayout.SOUTH, labelRaces);
		springLayout.putConstraint(SpringLayout.EAST, listRaces, 0, SpringLayout.EAST, labelRaces);
		frmNewEvent.getContentPane().add(listRaces);
		listRaces.setMultipleMode(false);

		buttonAddRace = new JButton("");
		buttonAddRace.setIcon(addIcon);
		springLayout.putConstraint(SpringLayout.WEST, buttonAddRace, 0, SpringLayout.WEST, listRaces);
		springLayout.putConstraint(SpringLayout.NORTH, buttonAddRace, 0, SpringLayout.SOUTH, listRaces);
		buttonAddRace.setOpaque(false);
		buttonAddRace.setFocusPainted(false);
		buttonAddRace.setContentAreaFilled(false);
		buttonAddRace.setBorder(null);
		frmNewEvent.getContentPane().add(buttonAddRace);
		buttonAddRace.addActionListener(this);


		buttonEditRace = new JButton(""); //$NON-NLS-1$
		buttonEditRace.setIcon(editIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonEditRace, 0, SpringLayout.NORTH, buttonAddRace);
		springLayout.putConstraint(SpringLayout.WEST, buttonEditRace, 5, SpringLayout.EAST, buttonAddRace);
		buttonEditRace.setOpaque(false);
		buttonEditRace.setContentAreaFilled(false);
		buttonEditRace.setBorder(null);
		buttonEditRace.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonEditRace);
		buttonEditRace.addActionListener(this);


		buttonDelRace = new JButton(""); //$NON-NLS-1$
		buttonDelRace.setIcon(delIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonDelRace, 0, SpringLayout.NORTH, buttonEditRace);
		springLayout.putConstraint(SpringLayout.WEST, buttonDelRace, 5, SpringLayout.EAST, buttonEditRace);
		buttonDelRace.setOpaque(false);
		buttonDelRace.setContentAreaFilled(false);
		buttonDelRace.setBorder(null);
		buttonDelRace.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonDelRace);
		buttonDelRace.addActionListener(this);


		// //LISTE DES VOITURES////

		labelCars = new JLabel(BUNDLE.getString("CarsString")); //$NON-NLS-1$
		labelCars.setFont(Police.getFontSubtitle());
		labelCars.setHorizontalAlignment(SwingConstants.CENTER);
		labelCars.setPreferredSize(new Dimension(200, 40));
		springLayout.putConstraint(SpringLayout.NORTH, labelCars, 0, SpringLayout.NORTH, labelPracticeSessions);
		springLayout.putConstraint(SpringLayout.WEST, labelCars, (frmNewEvent.getSize().width * 5 / 6) - (labelCars.getPreferredSize().width / 2),
				SpringLayout.WEST, frmNewEvent.getContentPane());
		frmNewEvent.getContentPane().add(labelCars);

		listCars = new List();
		springLayout.putConstraint(SpringLayout.NORTH, listCars, 5, SpringLayout.SOUTH, labelCars);
		springLayout.putConstraint(SpringLayout.WEST, listCars, 0, SpringLayout.WEST, labelCars);
		springLayout.putConstraint(SpringLayout.SOUTH, listCars, 240, SpringLayout.SOUTH, labelCars);
		springLayout.putConstraint(SpringLayout.EAST, listCars, 0, SpringLayout.EAST, labelCars);
		frmNewEvent.getContentPane().add(listCars);
		listCars.setMultipleMode(true);

		buttonAddCar = new JButton(""); //$NON-NLS-1$
		buttonAddCar.setIcon(addIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonAddCar, 0, SpringLayout.SOUTH, listCars);
		springLayout.putConstraint(SpringLayout.WEST, buttonAddCar, 0, SpringLayout.WEST, listCars);
		buttonAddCar.setOpaque(false);
		buttonAddCar.setContentAreaFilled(false);
		buttonAddCar.setBorder(null);
		buttonAddCar.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonAddCar);
		buttonAddCar.addActionListener(this);

		buttonEditCar = new JButton(""); //$NON-NLS-1$
		buttonEditCar.setIcon(editIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonEditCar, 0, SpringLayout.NORTH, buttonAddCar);
		springLayout.putConstraint(SpringLayout.WEST, buttonEditCar, 5, SpringLayout.EAST, buttonAddCar);
		buttonEditCar.setOpaque(false);
		buttonEditCar.setContentAreaFilled(false);
		buttonEditCar.setBorder(null);
		buttonEditCar.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonEditCar);
		buttonEditCar.addActionListener(this);

		buttonDelCar = new JButton(""); //$NON-NLS-1$
		buttonDelCar.setIcon(delIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonDelCar, 0, SpringLayout.NORTH, buttonEditCar);
		springLayout.putConstraint(SpringLayout.WEST, buttonDelCar, 5, SpringLayout.EAST, buttonEditCar);
		buttonDelCar.setOpaque(false);
		buttonDelCar.setContentAreaFilled(false);
		buttonDelCar.setBorder(null);
		buttonDelCar.setFocusPainted(false);
		frmNewEvent.getContentPane().add(buttonDelCar);
		buttonDelCar.addActionListener(this);


		// //BOUTON START////

		buttonStart = new JButton(); //$NON-NLS-1$
		buttonStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				buttonStart.setIcon(startIconPressed);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				buttonStart.setIcon(startIcon);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				buttonStart.setIcon(startIconPressed);
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonStart.setIcon(startIcon);
				setCursor(Cursor.getDefaultCursor());
			}
		});
		buttonStart.setIcon(startIcon);
		buttonStart.setOpaque(false);
		buttonStart.setContentAreaFilled(false);
		buttonStart.setBorder(null);
		buttonStart.setFocusPainted(false);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonStart, -10, SpringLayout.SOUTH, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonStart, -10, SpringLayout.EAST, frmNewEvent.getContentPane());
		buttonStart.setPreferredSize(new Dimension(85, 85));
		frmNewEvent.getContentPane().add(buttonStart);
		buttonStart.addActionListener(this);

		// //BOUTON ENREGISTRER////

		buttonSave = new JButton(BUNDLE.getString("SaveString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.SOUTH, buttonSave, 0, SpringLayout.SOUTH, buttonStart);
		springLayout.putConstraint(SpringLayout.EAST, buttonSave, -50, SpringLayout.EAST, labelTrackLength);
		buttonSave.setPreferredSize(new Dimension(120, 30));
		frmNewEvent.getContentPane().add(buttonSave);
		buttonSave.addActionListener(this);

		// //BOUTON ANNULER////

		buttonCancel = new JButton(BUNDLE.getString("CancelString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCancel, 0, SpringLayout.SOUTH, buttonStart);
		springLayout.putConstraint(SpringLayout.WEST, buttonCancel, 50, SpringLayout.EAST, labelTrackLength);
		buttonCancel.setPreferredSize(new Dimension(120, 30));
		frmNewEvent.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(this);


		if (Locale.getDefault().toString().equals("English"))
			mnMenuBarEventLanguageEnglish.setSelected(true);
		else
			mnMenuBarEventLanguageFrench.setSelected(true);
		frmNewEvent.setVisible(true);
	}


	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == buttonSave) {

			String nameEvent = textFieldEventName.getText();
			String nameCircuit = textFieldTrackName.getText();
			String lenght = this.textFieldTrackLength.getText();

			if (nameEvent.isEmpty()) {  
				this.popUpErreur("nom de l'evenement");
			}
			else if (nameCircuit.isEmpty()) {  
				this.popUpErreur("nom du circuit");
			}
			else if (lenght.isEmpty()) {  
				this.popUpErreur("longueur du circuit");
			}
			else {controlEve.enregistrerEve(nameEvent, nameCircuit, lenght);}
		}

		else if (arg0.getSource() == buttonAddPracticeSession) {
			controlEve.ajouterCourseSE();
			idSeance = listPracticeSessions.getItemCount();
			listPracticeSessions.add("Seance en cours de creation", idSeance);
			FenetreNewRace fenetre = new FenetreNewRace(controlSE, idSeance);
			fenetre.setFenetreNewEvent(this);
			controlSE.ajouterObserver(this);
		}
		else if (arg0.getSource() == buttonEditPracticeSession) {
			try {
				idSeance = listPracticeSessions.getSelectedIndex();
				FenetreNewRace fenetre = new FenetreNewRace(controlSE, idSeance);
				controlSE.modifierCourse(fenetre);
				controlSE.ajouterObserver(this);
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
		else if (arg0.getSource() == buttonDelPracticeSession) {
			idSeance = listPracticeSessions.getSelectedIndex();
			listPracticeSessions.remove(idSeance);
			controlEve.supprimerSceanceEssai(idSeance);
		}
		else if (arg0.getSource() == buttonAddRace) {
			controlEve.ajouterCourseC();
			idCourse = listRaces.getItemCount();
			listRaces.add("Course en cours de creation", idCourse);
			FenetreNewRace fenetre = new FenetreNewRace(controlC, idCourse);
			fenetre.setFenetreNewEvent(this);
			controlC.ajouterObserver(this);
		}
		else if (arg0.getSource() == buttonEditRace) {
			try {
				idCourse = listRaces.getSelectedIndex();
				FenetreNewRace fenetre = new FenetreNewRace(controlC, idCourse);
				controlC.modifierCourse(fenetre);
				controlC.ajouterObserver(this);
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
		else if (arg0.getSource() == buttonDelRace) {
			idCourse = listRaces.getSelectedIndex();
			listRaces.remove(idCourse);
			controlEve.supprimerCourse(idCourse);
		}
		else if (arg0.getSource() == buttonAddCar) {
			controlEve.ajouterV();
			idVoiture = listCars.getItemCount();
			listCars.add("Voiture en cours de creation", idVoiture);
			FenetreNewCar fenetre = new FenetreNewCar(controlV, controlP, controlTP, idVoiture);
			fenetre.setFenetreNewEvent(this);
			controlV.ajouterObserver(this);
		}
		else if (arg0.getSource() == buttonDelCar) {
			idVoiture = listCars.getSelectedIndex();
			listCars.remove(idVoiture);
			controlEve.supprimerCourse(idVoiture);
		}
		else if (arg0.getSource() == buttonEditCar) {
			try{
				idVoiture = listCars.getSelectedIndex();
				FenetreNewCar fenetre = new FenetreNewCar(controlV, controlP, controlTP, idVoiture);
				fenetre.setIsEdit(true);
				controlV.modifierCourse(fenetre);
				controlV.ajouterObserver(this);
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
		else if (arg0.getSource() == buttonCancel) {
			frmNewEvent.dispose();
		}
		else if (arg0.getSource()== buttonStart) {
			/*if ((listPracticeSessions.getSelectedIndexes().length + listRaces.getSelectedIndexes().length) != 1) {
				popUpErreurCourse();
			}

			else if (listCars.getSelectedIndexes().length==0) {
				popUpErreurVoiture();
			}
			else*/ 
			if (listRaces.getSelectedIndexes().length == 1) {
				FenetreNewTop fenetre = new FenetreNewTop(controlT);
				controlT.setCourse("Course Standard", listRaces.getSelectedIndex());
				controlT.setVoitures(listCars.getSelectedItems(), listCars.getItems());
				controlT.miseEnPlaceCourse(fenetre);
			}
			else {
				FenetreNewTop fenetre = new FenetreNewTop(controlT);
				controlT.setCourse("Seance D'essai", listPracticeSessions.getSelectedIndex());
				controlT.setVoitures(listCars.getSelectedItems(), listCars.getItems());
				controlT.miseEnPlaceCourse(fenetre);
			}
		}

	}




	public void update(Observable o, Object arg) {

		String[] res = ((String) arg).split("-");

		if(res[0].equals("CourseStandard")) {
			listRaces.replaceItem(res[1], idCourse);
		}
		else if(res[0].equals("SeanceEssai")) {
			listPracticeSessions.replaceItem(res[1], idSeance);
		}
		else {
			listCars.replaceItem(res[1], idVoiture);
		}
	}


	public void popUpErreur(String texte){
		JOptionPane.showMessageDialog(this, "Veillez remplir le champ : " + texte , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpErreurSelection(){
		JOptionPane.showMessageDialog(this, "Veuillez selectionner un et un seul item", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpErreurCourse(){
		JOptionPane.showMessageDialog(this, "Veuillez selectionner une et une seule course", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpErreurVoiture(){
		JOptionPane.showMessageDialog(this, "Veuillez selectionner une voiture", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void supprimerCourseEnCoursCreation(int id) {
		listRaces.remove(id);
	}

	public void supprimerSeanceEnCoursCreation(int id) {
		listPracticeSessions.remove(id);
	}

	public void supprimerVoitureEnCoursCreation(int id) {
		listCars.remove(id);
	}

	// Met à jour les différents textes qu'il faut traduire lorsque l'on change de langue
	private void updateLanguage() {
		frmNewEvent.setTitle(BUNDLE.getString("CustomizationEventString"));
		labelEventName.setText(BUNDLE.getString("NameString"));
		lblTitleEvent.setText(BUNDLE.getString("CustomizationEventString"));
		labelTrackName.setText(BUNDLE.getString("TrackString"));
		labelTrackLength.setText(BUNDLE.getString("TrackLengthString"));
		labelPracticeSessions.setText(BUNDLE.getString("PracticeSessionsString"));
		labelRaces.setText(BUNDLE.getString("RacesString"));
		labelCars.setText(BUNDLE.getString("CarsString"));
		buttonSave.setText(BUNDLE.getString("SaveString"));
		buttonCancel.setText(BUNDLE.getString("CancelString"));
		mnMenuBarEventLanguage.setText(BUNDLE.getString("LanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageEnglish.setText(BUNDLE.getString("EnglishLanguageString"));
		mnMenuBarEventLanguageFrench.setText(BUNDLE.getString("FrenchLanguageString"));
	}


}

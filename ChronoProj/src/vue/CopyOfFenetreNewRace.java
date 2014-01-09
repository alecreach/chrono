package vue;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.List;
import java.io.InputStream;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class CopyOfFenetreNewRace extends JFrame {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	// Charge les différents noms de langue dans un tableau pour la comboBox des langues
	String[] languagesString = { BUNDLE.getString("FrenchLanguageString"), BUNDLE.getString("EnglishLanguageString") };

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	// Déclarations des éléments de la fenêtre
	private JFrame frmNewEvent;
	private JLabel labelRaceName;
	private JLabel lblTitleEvent;
	private JLabel labelHourStart;
	private JLabel labelMaxTotal;
	private JLabel labelPracticeSessions;
	private JLabel labelRaces;
	private JLabel labelCars;
	private JButton buttonStart;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JMenuBar menuBarEvent;
	private JMenu mnMenuBarEvent;
	private JTextField textFieldRaceName;
	private JTextField textFieldMaxTotal;
	private List listPracticeSessions;
	private List listRaces;
	private List listCars;
	private JMenu mnMenuBarEventLanguage;
	private JRadioButtonMenuItem mnMenuBarEventLanguageEnglish;
	private JRadioButtonMenuItem mnMenuBarEventLanguageFrench;
	
	ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/startIcon.png"));
	ImageIcon startIconPressed = new ImageIcon(this.getClass().getResource("/resources/startIconPressed.png"));
	private JTextField textFieldHourStart;
	private JLabel labelMaxConsec;
	private JTextField textFieldMaxConsec;



	

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// FenetreNewEvent window = new FenetreNewEvent();
	// window.frmChronoProject.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public CopyOfFenetreNewRace() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewEvent = new JFrame();
		frmNewEvent.setResizable(false);
		frmNewEvent.setSize(1000, 700);
		frmNewEvent.setLocation((screenSize.width - frmNewEvent.getWidth()) / 2, (screenSize.height - frmNewEvent.getHeight()) / 3);
		frmNewEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewEvent.setTitle(BUNDLE.getString("CustomizationEventString"));
		SpringLayout springLayout = new SpringLayout();
		frmNewEvent.getContentPane().setLayout(springLayout);

		// //BARRE DES MENUS////

		menuBarEvent = new JMenuBar();
		springLayout.putConstraint(SpringLayout.WEST, menuBarEvent, 0, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBarEvent, 0, SpringLayout.EAST, frmNewEvent.getContentPane());
		frmNewEvent.getContentPane().add(menuBarEvent);

		mnMenuBarEvent = new JMenu(BUNDLE.getString("FenetreNewEvent.mnNewMenu.text_2")); //$NON-NLS-1$
		mnMenuBarEvent.setHorizontalAlignment(SwingConstants.LEFT);
		menuBarEvent.add(mnMenuBarEvent);
		
		mnMenuBarEventLanguage = new JMenu(BUNDLE.getString("LanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguage.setHorizontalAlignment(SwingConstants.LEFT);
		menuBarEvent.add(mnMenuBarEventLanguage);
		//ANGLAIS//
		mnMenuBarEventLanguageEnglish = new JRadioButtonMenuItem(BUNDLE.getString("EnglishLanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventLanguageEnglish) {
				Locale.setDefault(Locale.ENGLISH);
				BUNDLE = ResourceBundle.getBundle("vue.messages", Locale.getDefault());
				updateLanguage();
			}
		});
		mnMenuBarEventLanguage.add(mnMenuBarEventLanguageEnglish);
		//FRANCAIS//
		mnMenuBarEventLanguageFrench = new JRadioButtonMenuItem(BUNDLE.getString("FrenchLanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageFrench.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventLanguageFrench) {
				Locale.setDefault(Locale.FRENCH);
				BUNDLE = ResourceBundle.getBundle("vue.messages", Locale.getDefault());
				updateLanguage();
			}
		});
		mnMenuBarEventLanguage.add(mnMenuBarEventLanguageFrench);
		
		ButtonGroup group = new ButtonGroup(  );
		group.add(mnMenuBarEventLanguageEnglish);
		group.add(mnMenuBarEventLanguageFrench);
		
		// //TITRE////

		lblTitleEvent = new JLabel(BUNDLE.getString("CharacterizationRaceString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, lblTitleEvent, 0, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTitleEvent, 1000, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTitleEvent, 16, SpringLayout.SOUTH, menuBarEvent);
		lblTitleEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleEvent.setFont(Police.getFontTitle());
		frmNewEvent.getContentPane().add(lblTitleEvent);

		// //NOM DE L'EVENT////

		labelRaceName = new JLabel(BUNDLE.getString("NameString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelRaceName, 15, SpringLayout.SOUTH, lblTitleEvent);
		springLayout.putConstraint(SpringLayout.WEST, labelRaceName, 252, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelRaceName, 308, SpringLayout.WEST, frmNewEvent.getContentPane());
		labelRaceName.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewEvent.getContentPane().add(labelRaceName);

		textFieldRaceName = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldRaceName, 3, SpringLayout.SOUTH, labelRaceName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldRaceName, 10, SpringLayout.EAST, labelRaceName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldRaceName, 400, SpringLayout.EAST, labelRaceName);
		textFieldRaceName.setText("");
		textFieldRaceName.setPreferredSize(new Dimension(200, 20));
		frmNewEvent.getContentPane().add(textFieldRaceName);

		// //HEURE DE DEBUT////
		
		labelHourStart = new JLabel(BUNDLE.getString("HourStartString")+" :"); //$NON-NLS-1$	
		springLayout.putConstraint(SpringLayout.NORTH, labelHourStart, 10, SpringLayout.SOUTH, textFieldRaceName);
		springLayout.putConstraint(SpringLayout.EAST, labelHourStart, 500, SpringLayout.WEST, frmNewEvent.getContentPane());
		labelHourStart.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewEvent.getContentPane().add(labelHourStart);
		
		textFieldHourStart = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textFieldHourStart, 460, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldHourStart, -524, SpringLayout.SOUTH, frmNewEvent.getContentPane());
		textFieldHourStart.setText("");
		textFieldHourStart.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldHourStart.setColumns(5);
		frmNewEvent.getContentPane().add(textFieldHourStart);		
		
		// //DUREE MAX TOTALE/PILOTE////

		labelMaxTotal = new JLabel(BUNDLE.getString("MaxTotalDurationString")+" :"); //$NON-NLS-1$		
		springLayout.putConstraint(SpringLayout.NORTH, labelMaxTotal, 153, SpringLayout.NORTH, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelMaxTotal, 500, SpringLayout.WEST, frmNewEvent.getContentPane());
		labelMaxTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewEvent.getContentPane().add(labelMaxTotal);

		textFieldMaxTotal = new JTextField();		
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldMaxTotal, -495, SpringLayout.SOUTH, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldMaxTotal, 10, SpringLayout.EAST, labelMaxTotal);		
		textFieldMaxTotal.setText("");
		textFieldMaxTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMaxTotal.setColumns(5);
		frmNewEvent.getContentPane().add(textFieldMaxTotal);	

		// //DUREE MAX CONSEC/PILOTE////		

		labelMaxConsec = new JLabel(BUNDLE.getString("MaxConsecutiveDurationString")+" :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelMaxConsec, 10, SpringLayout.SOUTH, textFieldMaxTotal);
		springLayout.putConstraint(SpringLayout.EAST, labelMaxConsec, 500, SpringLayout.WEST, frmNewEvent.getContentPane());
		labelMaxConsec.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewEvent.getContentPane().add(labelMaxConsec);
		
		textFieldMaxConsec = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldMaxConsec, 3, SpringLayout.SOUTH, labelMaxConsec);
		springLayout.putConstraint(SpringLayout.WEST, textFieldMaxConsec, 10, SpringLayout.EAST, labelMaxConsec);		
		textFieldMaxConsec.setText("");
		textFieldMaxConsec.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMaxConsec.setColumns(5);
		frmNewEvent.getContentPane().add(textFieldMaxConsec);
		
		
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

		// //BOUTON START////

		buttonStart = new JButton(); //$NON-NLS-1$
		buttonStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				buttonStart.setIcon(startIconPressed);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				buttonStart.setIcon(icon);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				buttonStart.setIcon(startIconPressed);
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonStart.setIcon(icon);
				setCursor(Cursor.getDefaultCursor());
			}
		});
		buttonStart.setIcon(icon);
		buttonStart.setOpaque(false);
		buttonStart.setContentAreaFilled(false);
		buttonStart.setBorder(null);
		buttonStart.setFocusPainted(false);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonStart, -10, SpringLayout.SOUTH, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonStart, -10, SpringLayout.EAST, frmNewEvent.getContentPane());
		buttonStart.setPreferredSize(new Dimension(85, 85));
		frmNewEvent.getContentPane().add(buttonStart);

		// //BOUTON ENREGISTRER////

		buttonSave = new JButton(BUNDLE.getString("SaveString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.EAST, labelHourStart, 0, SpringLayout.EAST, buttonSave);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonSave, 0, SpringLayout.SOUTH, buttonStart);
		springLayout.putConstraint(SpringLayout.EAST, buttonSave, -50, SpringLayout.EAST, labelMaxTotal);
		buttonSave.setPreferredSize(new Dimension(120, 30));
		frmNewEvent.getContentPane().add(buttonSave);
		
		

		// //BOUTON ANNULER////

		buttonCancel = new JButton(BUNDLE.getString("CancelString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCancel, 0, SpringLayout.SOUTH, buttonStart);
		springLayout.putConstraint(SpringLayout.WEST, buttonCancel, 50, SpringLayout.EAST, labelMaxTotal);
		buttonCancel.setPreferredSize(new Dimension(120, 30));
		frmNewEvent.getContentPane().add(buttonCancel);
		
		JButton btnNewButton = new JButton(BUNDLE.getString("FenetreNewRace.btnNewButton.text"));
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 490, SpringLayout.WEST, frmNewEvent.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -2, SpringLayout.NORTH, labelRaces);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, labelMaxTotal);
		frmNewEvent.getContentPane().add(btnNewButton);
		

				
		if(Locale.getDefault().toString().equals("English")) mnMenuBarEventLanguageEnglish.setSelected(true);
		else mnMenuBarEventLanguageFrench.setSelected(true);
		frmNewEvent.setVisible(true);
	}

	// Met à jour les différents textes qu'il faut traduire lorsque l'on change de langue
	private void updateLanguage() {
		frmNewEvent.setTitle(BUNDLE.getString("NewEventString"));
		labelRaceName.setText(BUNDLE.getString("NameString"));
		lblTitleEvent.setText(BUNDLE.getString("NewEventString"));
		labelHourStart.setText(BUNDLE.getString("TrackString"));
		labelMaxTotal.setText(BUNDLE.getString("TrackLengthString"));
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

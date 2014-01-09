package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import controleur.ControleurTop;

public class FenetreNewTop extends JFrame implements ActionListener {
	private static ResourceBundle BUNDLE = ResourceBundle
	.getBundle("vue.messages"); //$NON-NLS-1$

	// R�cup�re les dimensions de l'�cran pour centrer la fen�tre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
	.getScreenSize();

	// Formats de date utilis�

	private DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


	// Declaration du controleur

	ControleurTop controlT;

	//Variable int qui indique le type de fin, 0 = fin � l'heure, 1 = fin hybride, 2 = fin au nombre de tours
	
	private int typeCourse;
	
	// Composants pour la manipulation du chrono

	int delais = 100;
	private long startTime;
	private long timeElapsed = 1;
	private long heureDeb = 0;
	private long heureFin = 0; 
	private int nbMaxDeTours = 0;
	private boolean isTimerOn = false;
	
	ActionListener tacheTimerChrono = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			timeElapsed = System.currentTimeMillis() - startTime;

			if (typeCourse<2 && heureFin < timeElapsed) {
				timerChrono.stop();
			}
			
			labelElapsedTimeValue.setText(longEnString(timeElapsed)); // rafraichir le label
		}
	};

	ActionListener tacheTimerCountdown = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if (timeElapsed > 0) {
			timeElapsed = heureDeb - stringEnLong(dateFormat.format(new Date()));
			labelElapsedTimeValue.setText(longEnString(timeElapsed)); // rafraichir le label
			}
			else {
				startTime = System.currentTimeMillis();
				timerChrono.start();
				timerCountdown.stop();
				isTimerOn = true;
				popUpDepartCourse();
			}
		}
	};
	
	ActionListener updateClock = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			labelCurrentTimeValue.setText(dateFormat.format(new Date()));
		}
	};
	
	// Instanciation des timer
	final Timer timerChrono = new Timer(delais, tacheTimerChrono);
	final Timer timerCountdown = new Timer(delais, tacheTimerCountdown);
	final Timer t = new Timer(delais, updateClock);

	// D�clarations des �l�ments de la fen�tre
	private JFrame frmTop;
	private JMenuBar menuBarEvent;
	private JMenu mnMenuBarEventLanguage;
	private JRadioButtonMenuItem mnMenuBarEventLanguageEnglish;
	private JRadioButtonMenuItem mnMenuBarEventLanguageFrench;
	private JLabel labelCurrentTime;
	private JLabel labelCurrentTimeValue;
	private JLabel labelElapsedTime;
	private JLabel labelRemainingTime;
	private JLabel labelElapsedTimeValue;
	private JLabel labelRemainingTimeValue;
	private JButton buttonTop;
	private JLabel labelNextPassages;
	private List listNextPassages;
	private JLabel labelCarNumber;
	public String numeroVoiture = "--";
	private JLabel labelCurrentDriver;
	private JComboBox comboBoxCurrentDriver;
	private JLabel labelCurrentTyres;
	private JComboBox comboBoxCurrentTyres;
	private JLabel labelLaps;
	private JLabel labelRemainingLaps;
	private JTextField textFieldLaps;
	private JTextField textFieldRemainingLaps;
	private JLabel labelCurrentRelay;
	private JTextField textFieldCurrentRelay;
	private JLabel labelLapsSinceLastRelay;
	private JTextField textFieldLapsSinceLastRelay;
	private JLabel labelLapsBeforeRelay;
	private JTextField textFieldLapsBeforeRelay;
	private JLabel labelEstimatedLapTime;
	private JTextField textFieldEstimatedLapTime;
	private JLabel labelNextPassage;
	private JTextField textFieldNextPassage;
	private JProgressBar progressBarMainCar;
	private JLabel labelDate;
	private JLabel labelRemark;
	private JLabel labelCircuitName;
	private JLabel labelRaceName;
	private JLabel labelMainCar;
	private JComboBox comboBox;
	private JLabel labelNewRemark;
	private JTextArea textAreaNewRemark;
	private JButton buttonOK;
	private JLabel labelAvailableCars;
	private List listCars;
	private JButton buttonAddCar;
	private JButton buttonSave;
	private JProgressBar progressBarRace;
	private JPanel panelTopsTab;
	private JTable tableTops;
	private JPanel panelRankingTab;
	private JTable tableRanking;
	private int numTop = 0;
	ImageIcon chronoIcon = new ImageIcon(this.getClass().getResource(
	"/resources/chronoIcon.png"));
	ImageIcon chronoIconPressed = new ImageIcon(this.getClass().getResource(
	"/resources/chronoIconPressed.png"));
	ImageIcon addIcon = new ImageIcon(this.getClass().getResource(
	"/resources/addIcon.png"));
	ImageIcon saveIcon = new ImageIcon(this.getClass().getResource(
	"/resources/saveIcon.png"));
	ImageIcon topIcon = new ImageIcon(FenetreNewCar.class
			.getResource("/resources/topIcon.png"));
	ImageIcon bottomIcon = new ImageIcon(FenetreNewCar.class
			.getResource("/resources/bottomIcon.png"));
	ImageIcon editIcon = new ImageIcon(this.getClass().getResource(
	"/resources/editIcon.png"));
	ImageIcon delIcon = new ImageIcon(this.getClass().getResource(
	"/resources/delIcon.png"));

	private JTabbedPane panelInformation;
	String[] entetesRanking;
	private String[] entetesTops = new String[] {
			BUNDLE.getString("TopNumberString"), BUNDLE.getString("CarString"),
			BUNDLE.getString("DriverString"),
			BUNDLE.getString("LapTimeString"), BUNDLE.getString("LapsString"),
			BUNDLE.getString("StateString"), BUNDLE.getString("TopDateString"),
			BUNDLE.getString("LapsRelayString"),
			BUNDLE.getString("RelayString"), BUNDLE.getString("RemarkString") };

	private JTabbedPane panelResults;

	private JButton buttonTopPassage;

	private JButton buttonBottomPassage;

	private JButton buttonEditCar;

	private AbstractButton buttonDelCar;

	private Object[][] donnees = new Object[100][10];

	private JPanel panelTime;

	private JPanel panelBarreProgress;

	private SpringLayout sl_panelRankingTab;

	private SpringLayout sl_panelTopsTab;

	/**
	 * Create the application.
	 */
	public FenetreNewTop(ControleurTop controlT) {
		this.controlT = controlT;
		initialize();
		t.start();
		timerCountdown.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTop = new JFrame();
		frmTop.setResizable(false);
		frmTop.setSize(screenSize.width, screenSize.height * 9 / 10);
		frmTop.setLocation((screenSize.width - frmTop.getWidth()) / 2,
				(screenSize.height - frmTop.getHeight()) / 3);
		frmTop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTop.setTitle(BUNDLE.getString("RaceInterfaceString")); //$NON-NLS-1$
		SpringLayout springLayout = new SpringLayout();
		frmTop.getContentPane().setLayout(springLayout);

		// //BARRE DES MENUS////

		menuBarEvent = new JMenuBar();
		menuBarEvent.setSize(new Dimension(50, 150));
		springLayout.putConstraint(SpringLayout.WEST, menuBarEvent, 0,
				SpringLayout.WEST, frmTop.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBarEvent, 0,
				SpringLayout.EAST, frmTop.getContentPane());
		frmTop.getContentPane().add(menuBarEvent);

		mnMenuBarEventLanguage = new JMenu(BUNDLE.getString("LanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguage.setHorizontalAlignment(SwingConstants.LEFT);
		menuBarEvent.add(mnMenuBarEventLanguage);
		// ANGLAIS//
		mnMenuBarEventLanguageEnglish = new JRadioButtonMenuItem(BUNDLE
				.getString("EnglishLanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventLanguageEnglish) {
				Locale.setDefault(Locale.ENGLISH);
				BUNDLE = ResourceBundle.getBundle("vue.messages", Locale
						.getDefault());
				updateLanguage();
			}
		});
		mnMenuBarEventLanguage.add(mnMenuBarEventLanguageEnglish);
		// FRANCAIS//
		mnMenuBarEventLanguageFrench = new JRadioButtonMenuItem(BUNDLE
				.getString("FrenchLanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageFrench.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventLanguageFrench) {
				Locale.setDefault(Locale.FRENCH);
				BUNDLE = ResourceBundle.getBundle("vue.messages", Locale
						.getDefault());
				updateLanguage();
			}
		});
		mnMenuBarEventLanguage.add(mnMenuBarEventLanguageFrench);

		ButtonGroup group = new ButtonGroup();
		group.add(mnMenuBarEventLanguageEnglish);
		group.add(mnMenuBarEventLanguageFrench);

		if (Locale.getDefault().toString().equals("English"))
			mnMenuBarEventLanguageEnglish.setSelected(true);
		else
			mnMenuBarEventLanguageFrench.setSelected(true);

		frmTop.setVisible(true);

		// ///PANELS////

		// /TEMPS

		panelTime = new JPanel();
		panelTime.setPreferredSize(new Dimension(frmTop.getWidth() / 3, frmTop
				.getHeight()
				* 2 / 3 - menuBarEvent.getHeight()));
		panelTime.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panelTime, 0,
				SpringLayout.SOUTH, menuBarEvent);
		springLayout.putConstraint(SpringLayout.WEST, panelTime, 0,
				SpringLayout.WEST, frmTop.getContentPane());
		frmTop.getContentPane().add(panelTime);
		SpringLayout sl_panelTime = new SpringLayout();
		panelTime.setLayout(sl_panelTime);

		// HEURE
		labelCurrentTime = new JLabel(BUNDLE.getString("CurrentTimeString")); //$NON-NLS-1$
		labelCurrentTime.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelCurrentTime, 0,
				SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelCurrentTime, 0,
				SpringLayout.EAST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelCurrentTime, 10,
				SpringLayout.NORTH, panelTime);
		labelCurrentTime.setFont(Police.getFontSubtitle());
		panelTime.add(labelCurrentTime);

		/*
		 * Mise � jour de l'heure
		 */
		labelCurrentTimeValue = new JLabel(""); //$NON-NLS-1$=
		labelCurrentTimeValue.setText(dateFormat.format(new Date()));

		sl_panelTime.putConstraint(SpringLayout.NORTH, labelCurrentTimeValue,
				5, SpringLayout.SOUTH, labelCurrentTime);
		labelCurrentTimeValue.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelCurrentTimeValue, 0,
				SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelCurrentTimeValue, 0,
				SpringLayout.EAST, panelTime);
		labelCurrentTimeValue.setFont(Police.getFontTitle());
		panelTime.add(labelCurrentTimeValue);

		// TEMPS ECOULE
		labelElapsedTime = new JLabel(BUNDLE.getString("ElapsedTimeString")); //$NON-NLS-1$
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelElapsedTime, 15,
				SpringLayout.SOUTH, labelCurrentTimeValue);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelElapsedTime, 0,
				SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelElapsedTime,
				-panelTime.getPreferredSize().width / 2 - 120,
				SpringLayout.EAST, panelTime);
		labelElapsedTime.setHorizontalAlignment(SwingConstants.RIGHT);
		labelElapsedTime.setFont(Police.getFontSubtitle());
		panelTime.add(labelElapsedTime);

		// Jlabel contenant le chrono

		labelElapsedTimeValue = new JLabel("00:00:00");
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelElapsedTimeValue,
				3, SpringLayout.SOUTH, labelElapsedTime);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelElapsedTimeValue, 0,
				SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelElapsedTimeValue,
				-panelTime.getPreferredSize().width / 2 - 120,
				SpringLayout.EAST, panelTime);
		labelElapsedTimeValue.setHorizontalAlignment(SwingConstants.RIGHT);
		labelElapsedTimeValue.setFont(Police.getFontTitle());
		panelTime.add(labelElapsedTimeValue);

		// TEMPS RESTANT
		labelRemainingTime = new JLabel(BUNDLE.getString("TimeRemainingString"));
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelRemainingTime, 15,
				SpringLayout.SOUTH, labelCurrentTimeValue);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelRemainingTime,
				-panelTime.getPreferredSize().width / 2 + 120,
				SpringLayout.EAST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelRemainingTime, 0,
				SpringLayout.EAST, panelTime);
		labelRemainingTime.setHorizontalAlignment(SwingConstants.LEFT);
		labelRemainingTime.setFont(Police.getFontSubtitle());
		panelTime.add(labelRemainingTime);

		labelRemainingTimeValue = new JLabel("12:53:45");
		sl_panelTime.putConstraint(SpringLayout.EAST, labelRemainingTimeValue,
				-160, SpringLayout.WEST, labelRemainingTimeValue);
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelRemainingTimeValue,
				5, SpringLayout.SOUTH, labelRemainingTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelRemainingTimeValue,
				0, SpringLayout.EAST, labelRemainingTime);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelRemainingTimeValue,
				0, SpringLayout.WEST, labelRemainingTime);
		labelRemainingTimeValue.setHorizontalAlignment(SwingConstants.LEFT);
		labelRemainingTimeValue.setFont(Police.getFontTitle());
		panelTime.add(labelRemainingTimeValue);

		// BOUTON TOP
		buttonTop = new JButton(chronoIcon); //$NON-NLS-1$		
		sl_panelTime.putConstraint(SpringLayout.NORTH, buttonTop, 5,
				SpringLayout.SOUTH, labelCurrentTimeValue);
		sl_panelTime.putConstraint(SpringLayout.EAST, buttonTop, -10,
				SpringLayout.WEST, labelRemainingTime);
		sl_panelTime.putConstraint(SpringLayout.WEST, buttonTop, 20,
				SpringLayout.EAST, labelElapsedTime);

		buttonTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				buttonTop.setIcon(chronoIconPressed);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				buttonTop.setIcon(chronoIcon);
			}
		});
		buttonTop.setFocusPainted(false);
		panelTime.add(buttonTop);
		buttonTop.addActionListener(this);

		// PROCHAINS PASSAGES
		listNextPassages = new List();
		sl_panelTime.putConstraint(SpringLayout.NORTH, listNextPassages, 115,
				SpringLayout.NORTH, labelElapsedTimeValue);
		sl_panelTime.putConstraint(SpringLayout.WEST, listNextPassages, 0,
				SpringLayout.WEST, buttonTop);
		listNextPassages.setPreferredSize(new Dimension(400, 200));
		sl_panelTime.putConstraint(SpringLayout.EAST, listNextPassages, -100,
				SpringLayout.EAST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.SOUTH, listNextPassages, -115,
				SpringLayout.SOUTH, panelTime);
		panelTime.add(listNextPassages);


		labelNextPassages = new JLabel(BUNDLE.getString("NextPassagesString")
				+ " :");
		labelNextPassages.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNextPassages.setFont(Police.getFontDefault());
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelNextPassages, 0,
				SpringLayout.NORTH, listNextPassages);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelNextPassages, 0,
				SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelNextPassages, -10,
				SpringLayout.WEST, listNextPassages);
		panelTime.add(labelNextPassages);
		listNextPassages.setMultipleMode(false);

		buttonTopPassage = new JButton(""); //$NON-NLS-1$
		buttonTopPassage.setIcon(topIcon);
		buttonTopPassage.setOpaque(false);
		buttonTopPassage.setFocusPainted(false);
		buttonTopPassage.setContentAreaFilled(false);
		buttonTopPassage.setBorder(null);
		sl_panelTime.putConstraint(SpringLayout.NORTH, buttonTopPassage, 20,
				SpringLayout.NORTH, listNextPassages);
		sl_panelTime.putConstraint(SpringLayout.WEST, buttonTopPassage, 8,
				SpringLayout.EAST, listNextPassages);
		panelTime.add(buttonTopPassage);
		buttonTopPassage.addActionListener(this);

		buttonBottomPassage = new JButton(""); //$NON-NLS-1$
		buttonBottomPassage.setIcon(bottomIcon);
		buttonBottomPassage.setOpaque(false);
		buttonBottomPassage.setFocusPainted(false);
		buttonBottomPassage.setContentAreaFilled(false);
		buttonBottomPassage.setBorder(null);
		sl_panelTime.putConstraint(SpringLayout.NORTH, buttonBottomPassage, 2,
				SpringLayout.SOUTH, buttonTopPassage);
		sl_panelTime.putConstraint(SpringLayout.WEST, buttonBottomPassage, 8,
				SpringLayout.EAST, listNextPassages);
		panelTime.add(buttonBottomPassage);
		buttonBottomPassage.addActionListener(this);

		// /VOITURE PRINCIPALE
		JPanel panelMainCar = new JPanel();
		panelMainCar.setPreferredSize(new Dimension(frmTop.getWidth() / 3 + 80,
				frmTop.getHeight() * 2 / 3 - menuBarEvent.getHeight()));
		panelMainCar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		springLayout.putConstraint(SpringLayout.NORTH, panelMainCar, 0,
				SpringLayout.SOUTH, menuBarEvent);
		springLayout.putConstraint(SpringLayout.WEST, panelMainCar, 0,
				SpringLayout.EAST, panelTime);
		SpringLayout sl_panelMainCar = new SpringLayout();
		panelMainCar.setLayout(sl_panelMainCar);
		frmTop.getContentPane().add(panelMainCar);

		labelCarNumber = new JLabel(
				BUNDLE.getString("CarNumberString") + numeroVoiture); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCarNumber, 10,
				SpringLayout.NORTH, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelCarNumber, 0,
				SpringLayout.WEST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelCarNumber, 0,
				SpringLayout.EAST, panelMainCar);
		labelCarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		labelCarNumber.setFont(Police.getFontSubtitle());
		panelMainCar.add(labelCarNumber);

		// LISTE DES PILOTES DE LA VOITURE
		labelCurrentDriver = new JLabel(
				BUNDLE.getString("CurrentDriverString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCurrentDriver,
				60, SpringLayout.SOUTH, labelCarNumber);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelCurrentDriver,
				25, SpringLayout.WEST, panelMainCar);
		labelCurrentDriver.setFont((Police.getFontDefault()));
		panelMainCar.add(labelCurrentDriver);

		comboBoxCurrentDriver = new JComboBox();
		sl_panelMainCar.putConstraint(SpringLayout.WEST, comboBoxCurrentDriver,
				10, SpringLayout.EAST, labelCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH,
				comboBoxCurrentDriver, -4, SpringLayout.NORTH,
				labelCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, comboBoxCurrentDriver,
				panelMainCar.getPreferredSize().width / 2, SpringLayout.WEST,
				panelMainCar);
		comboBoxCurrentDriver.setFont((Police.getFontDefault()));
		panelMainCar.add(comboBoxCurrentDriver);
		comboBoxCurrentDriver.addActionListener(this);

		// LISTE DES TRAINS DE PNEUS
		labelCurrentTyres = new JLabel(
				BUNDLE.getString("TyresSetString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCurrentTyres,
				60, SpringLayout.SOUTH, labelCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelCurrentTyres, 0,
				SpringLayout.EAST, labelCurrentDriver);
		labelCurrentTyres.setFont((Police.getFontDefault()));
		panelMainCar.add(labelCurrentTyres);

		comboBoxCurrentTyres = new JComboBox();
		sl_panelMainCar.putConstraint(SpringLayout.WEST, comboBoxCurrentTyres,
				10, SpringLayout.EAST, labelCurrentTyres);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, comboBoxCurrentTyres,
				-4, SpringLayout.NORTH, labelCurrentTyres);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, comboBoxCurrentTyres,
				panelMainCar.getPreferredSize().width / 2, SpringLayout.WEST,
				panelMainCar);
		comboBoxCurrentTyres.setFont((Police.getFontDefault()));
		panelMainCar.add(comboBoxCurrentTyres);

		// TOURS
		labelLaps = new JLabel(BUNDLE.getString("LapsString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelLaps, 15,
				SpringLayout.SOUTH, labelCarNumber);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelLaps,
				panelMainCar.getPreferredSize().width / 2 - 150,
				SpringLayout.WEST, panelMainCar);
		labelLaps.setFont(new Font("Century Gothic", Font.BOLD, 16));
		panelMainCar.add(labelLaps);

		textFieldLaps = new JTextField();
		textFieldLaps.setText(""); //$NON-NLS-1$
		textFieldLaps.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldLaps, 0,
				SpringLayout.NORTH, labelLaps);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, textFieldLaps, 10,
				SpringLayout.EAST, labelLaps);
		textFieldLaps.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldLaps);

		// TOURS RESTANTS
		textFieldRemainingLaps = new JTextField();
		textFieldRemainingLaps.setText(""); //$NON-NLS-1$
		textFieldRemainingLaps.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH,
				textFieldRemainingLaps, 0, SpringLayout.NORTH, labelLaps);
		sl_panelMainCar.putConstraint(SpringLayout.EAST,
				textFieldRemainingLaps,
				panelMainCar.getPreferredSize().width / 2 + 150,
				SpringLayout.WEST, panelMainCar);
		textFieldRemainingLaps.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldRemainingLaps);

		labelRemainingLaps = new JLabel(
				BUNDLE.getString("RemainingString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelRemainingLaps,
				-10, SpringLayout.WEST, textFieldRemainingLaps);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelRemainingLaps,
				0, SpringLayout.NORTH, labelLaps);
		labelRemainingLaps.setFont(new Font("Century Gothic", Font.BOLD, 16));
		panelMainCar.add(labelRemainingLaps);

		// RELAIS EN COURS
		labelCurrentRelay = new JLabel(
				BUNDLE.getString("CurrentRelayString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelCurrentRelay,
				100, SpringLayout.EAST, comboBoxCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCurrentRelay, 0,
				SpringLayout.NORTH, comboBoxCurrentDriver);
		labelCurrentRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(labelCurrentRelay);

		textFieldCurrentRelay = new JTextField();
		textFieldCurrentRelay.setText("0");
		textFieldCurrentRelay.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar
		.putConstraint(SpringLayout.NORTH, textFieldCurrentRelay, 0,
				SpringLayout.NORTH, labelCurrentRelay);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, textFieldCurrentRelay,
				10, SpringLayout.EAST, labelCurrentRelay);
		textFieldCurrentRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldCurrentRelay);

		// TOUR DEPUIS DERNIER RELAIS
		textFieldLapsSinceLastRelay = new JTextField();
		textFieldLapsSinceLastRelay.setText(""); //$NON-NLS-1$
		textFieldLapsSinceLastRelay.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH,
				textFieldLapsSinceLastRelay, 21, SpringLayout.SOUTH,
				textFieldCurrentRelay);
		sl_panelMainCar.putConstraint(SpringLayout.EAST,
				textFieldLapsSinceLastRelay, 0, SpringLayout.EAST,
				textFieldCurrentRelay);
		textFieldLapsSinceLastRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldLapsSinceLastRelay);

		labelLapsSinceLastRelay = new JLabel(BUNDLE
				.getString("LapsSinceLastRelayString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.EAST,
				labelLapsSinceLastRelay, -10, SpringLayout.WEST,
				textFieldLapsSinceLastRelay);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH,
				labelLapsSinceLastRelay, 0, SpringLayout.NORTH,
				textFieldLapsSinceLastRelay);
		labelLapsSinceLastRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(labelLapsSinceLastRelay);

		// TOURS AVANT RELAIS
		textFieldLapsBeforeRelay = new JTextField();
		textFieldLapsBeforeRelay.setText(""); //$NON-NLS-1$
		textFieldLapsBeforeRelay.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH,
				textFieldLapsBeforeRelay, 0, SpringLayout.NORTH,
				comboBoxCurrentTyres);
		sl_panelMainCar.putConstraint(SpringLayout.EAST,
				textFieldLapsBeforeRelay, 0, SpringLayout.EAST,
				textFieldLapsSinceLastRelay);
		textFieldLapsBeforeRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldLapsBeforeRelay);

		labelLapsBeforeRelay = new JLabel(BUNDLE
				.getString("LapsBeforeRelayString") + " :"); //$NON-NLS-1$ //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelLapsBeforeRelay,
				-10, SpringLayout.WEST, textFieldLapsBeforeRelay);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelLapsBeforeRelay,
				0, SpringLayout.NORTH, textFieldLapsBeforeRelay);
		labelLapsBeforeRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(labelLapsBeforeRelay);

		// TEMPS ESTIM�
		labelEstimatedLapTime = new JLabel(BUNDLE
				.getString("EstimatedLapTimeString")); //$NON-NLS-1$
		labelEstimatedLapTime.setPreferredSize(new Dimension(panelMainCar
				.getPreferredSize().width / 2, 30));
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelEstimatedLapTime,
				0, SpringLayout.WEST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH,
				labelEstimatedLapTime, 40, SpringLayout.SOUTH,
				labelLapsBeforeRelay);
		labelEstimatedLapTime.setFont((Police.getFontSubtitle()));
		labelEstimatedLapTime.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(labelEstimatedLapTime);

		textFieldEstimatedLapTime = new JTextField();
		textFieldEstimatedLapTime.setText(""); //$NON-NLS-1$
		textFieldEstimatedLapTime.setPreferredSize(new Dimension(120, 30));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH,
				textFieldEstimatedLapTime, 5, SpringLayout.SOUTH,
				labelEstimatedLapTime);
		sl_panelMainCar.putConstraint(SpringLayout.WEST,
				textFieldEstimatedLapTime,
				panelMainCar.getPreferredSize().width / 4
				- textFieldEstimatedLapTime.getPreferredSize().width
				/ 2, SpringLayout.WEST, panelMainCar);
		textFieldEstimatedLapTime.setFont((Police.getFontDefault()));
		textFieldEstimatedLapTime.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(textFieldEstimatedLapTime);

		// PROCHAIN PASSAGE
		labelNextPassage = new JLabel(BUNDLE.getString("NextPassageString")); //$NON-NLS-1$
		labelNextPassage.setPreferredSize(new Dimension(panelMainCar
				.getPreferredSize().width / 2, 30));
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelNextPassage, 0,
				SpringLayout.EAST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelNextPassage, 0,
				SpringLayout.NORTH, labelEstimatedLapTime);
		labelNextPassage.setFont((Police.getFontSubtitle()));
		labelNextPassage.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(labelNextPassage);

		textFieldNextPassage = new JTextField();
		textFieldNextPassage.setText(""); //$NON-NLS-1$
		textFieldNextPassage.setPreferredSize(new Dimension(120, 30));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldNextPassage,
				5, SpringLayout.SOUTH, labelNextPassage);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, textFieldNextPassage,
				panelMainCar.getPreferredSize().width * 3 / 4
				- textFieldNextPassage.getPreferredSize().width / 2,
				SpringLayout.WEST, panelMainCar);
		textFieldNextPassage.setFont((Police.getFontDefault()));
		textFieldNextPassage.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(textFieldNextPassage);

		// BARRE DE PROGRESSION VOITURE PRINCIPALE
		progressBarMainCar = new JProgressBar();
		progressBarMainCar.setPreferredSize(new Dimension(146, 30));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, progressBarMainCar,
				30, SpringLayout.SOUTH, textFieldNextPassage);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, progressBarMainCar,
				70, SpringLayout.WEST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, progressBarMainCar,
				-70, SpringLayout.EAST, panelMainCar);
		progressBarMainCar.setValue(35);
		panelMainCar.add(progressBarMainCar);

		// /INFOS
		panelInformation = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, panelInformation, 0,
				SpringLayout.SOUTH, menuBarEvent);
		springLayout.putConstraint(SpringLayout.WEST, panelInformation, 0,
				SpringLayout.EAST, panelMainCar);
		panelInformation.setPreferredSize(new Dimension(
				frmTop.getWidth() / 3 - 80, frmTop.getHeight() * 2 / 3
				- menuBarEvent.getHeight()));
		panelInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		frmTop.getContentPane().add(panelInformation);

		// panelGeneralInformationTab
		JPanel panelGeneralInformationTab = new JPanel();
		SpringLayout sl_panelGeneralInformationTab = new SpringLayout();
		panelGeneralInformationTab.setLayout(sl_panelGeneralInformationTab);

		panelInformation.addTab(BUNDLE.getString("GeneralString"),
				panelGeneralInformationTab);

		final DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		labelDate = new JLabel("");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				labelDate, 10, SpringLayout.NORTH, panelGeneralInformationTab);
		labelDate.setText(dateFormat2.format(new Date()));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				labelDate, 0, SpringLayout.WEST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST,
				labelDate, 0, SpringLayout.EAST, panelGeneralInformationTab);
		labelDate.setFont(Police.getFontDefault());
		labelDate.setHorizontalAlignment(SwingConstants.CENTER);
		panelGeneralInformationTab.add(labelDate);

		labelCircuitName = new JLabel("Nom du circuit...");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				labelCircuitName, 10, SpringLayout.SOUTH, labelDate);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST,
				labelCircuitName, 0, SpringLayout.EAST,
				panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				labelCircuitName, 0, SpringLayout.WEST,
				panelGeneralInformationTab);
		labelCircuitName.setHorizontalAlignment(SwingConstants.CENTER);
		labelCircuitName.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelCircuitName);

		labelRaceName = new JLabel("Nom de la course...");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				labelRaceName, 10, SpringLayout.SOUTH, labelCircuitName);
		sl_panelGeneralInformationTab
		.putConstraint(SpringLayout.EAST, labelRaceName, 0,
				SpringLayout.EAST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab
		.putConstraint(SpringLayout.WEST, labelRaceName, 0,
				SpringLayout.WEST, panelGeneralInformationTab);
		labelRaceName.setHorizontalAlignment(SwingConstants.CENTER);
		labelRaceName.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelRaceName);

		labelRemark = new JLabel("Remarques...");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				labelRemark, 10, SpringLayout.SOUTH, labelRaceName);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST,
				labelRemark, 0, SpringLayout.EAST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				labelRemark, 0, SpringLayout.WEST, panelGeneralInformationTab);
		labelRemark.setHorizontalAlignment(SwingConstants.CENTER);
		labelRemark.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelRemark);

		// Choix voiture principale
		labelMainCar = new JLabel(BUNDLE.getString("MainCarString") + " :"); //$NON-NLS-1$
		labelMainCar.setPreferredSize(new Dimension(150, 20));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				labelMainCar, 40, SpringLayout.SOUTH, labelRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				labelMainCar, panelInformation.getPreferredSize().width / 2
				- labelMainCar.getPreferredSize().width,
				SpringLayout.WEST, panelGeneralInformationTab);
		labelMainCar.setHorizontalAlignment(SwingConstants.CENTER);
		labelMainCar.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelMainCar);

		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(150, 30));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				comboBox, 10, SpringLayout.EAST, labelMainCar);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				comboBox, -4, SpringLayout.NORTH, labelMainCar);
		comboBox.setFont((Police.getFontDefault()));
		panelGeneralInformationTab.add(comboBox);
		comboBox.addActionListener(this);

		// Commentaires
		labelNewRemark = new JLabel(BUNDLE.getString("RemarkString") + " :"); //$NON-NLS-1$
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				labelNewRemark, 10, SpringLayout.WEST,
				panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				labelNewRemark, 65, SpringLayout.SOUTH, labelMainCar);
		labelNewRemark.setPreferredSize(new Dimension(150, 20));
		labelNewRemark.setHorizontalAlignment(SwingConstants.CENTER);
		labelNewRemark.setFont((Police.getFontDefault()));
		panelGeneralInformationTab.add(labelNewRemark);

		textAreaNewRemark = new JTextArea();
		textAreaNewRemark.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaNewRemark.setText(""); //$NON-NLS-1$
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				textAreaNewRemark, 10, SpringLayout.EAST, labelNewRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST,
				textAreaNewRemark, -20, SpringLayout.EAST,
				panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				textAreaNewRemark, -30, SpringLayout.NORTH, labelNewRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.SOUTH,
				textAreaNewRemark, 30, SpringLayout.SOUTH, labelNewRemark);
		panelGeneralInformationTab.add(textAreaNewRemark);

		buttonOK = new JButton("OK"); //$NON-NLS-1$		
		buttonOK.setPreferredSize(new Dimension(60, 30));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH,
				buttonOK, 10, SpringLayout.SOUTH, textAreaNewRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST,
				buttonOK, panelInformation.getPreferredSize().width / 2
				- buttonOK.getPreferredSize().width / 2,
				SpringLayout.WEST, panelGeneralInformationTab);
		panelGeneralInformationTab.add(buttonOK);

		// panelCarsTab
		JPanel panelCarsTab = new JPanel();
		SpringLayout sl_panelCarsTab = new SpringLayout();
		panelCarsTab.setLayout(sl_panelCarsTab);
		panelInformation.addTab(BUNDLE.getString("CarsString"), panelCarsTab);

		labelAvailableCars = new JLabel(BUNDLE.getString("AvailableCarsString"));
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, labelAvailableCars,
				10, SpringLayout.NORTH, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, labelAvailableCars, 0,
				SpringLayout.WEST, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.EAST, labelAvailableCars, 0,
				SpringLayout.EAST, panelCarsTab);
		labelAvailableCars.setHorizontalAlignment(SwingConstants.CENTER);
		labelAvailableCars.setFont(Police.getFontDefault());
		panelCarsTab.add(labelAvailableCars);

		listCars = new List();
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, listCars, 10,
				SpringLayout.SOUTH, labelAvailableCars);
		sl_panelCarsTab.putConstraint(SpringLayout.SOUTH, listCars, -100,
				SpringLayout.SOUTH, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, listCars, 120,
				SpringLayout.WEST, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.EAST, listCars, -120,
				SpringLayout.EAST, panelCarsTab);
		panelCarsTab.add(listCars);

		buttonAddCar = new JButton(""); //$NON-NLS-1$
		buttonAddCar.setIcon(addIcon);
		buttonAddCar.setOpaque(false);
		buttonAddCar.setFocusPainted(false);
		buttonAddCar.setContentAreaFilled(false);
		buttonAddCar.setBorder(null);
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, buttonAddCar, 0,
				SpringLayout.SOUTH, listCars);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, buttonAddCar, 0,
				SpringLayout.WEST, listCars);
		panelCarsTab.add(buttonAddCar);

		buttonEditCar = new JButton(""); //$NON-NLS-1$
		buttonEditCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent NewDriverEvent) {
			}
		});
		buttonEditCar.setIcon(editIcon);
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, buttonEditCar, 0,
				SpringLayout.NORTH, buttonAddCar);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, buttonEditCar, 5,
				SpringLayout.EAST, buttonAddCar);
		buttonEditCar.setOpaque(false);
		buttonEditCar.setContentAreaFilled(false);
		buttonEditCar.setBorder(null);
		buttonEditCar.setFocusPainted(false);
		panelCarsTab.add(buttonEditCar);

		buttonDelCar = new JButton(""); //$NON-NLS-1$
		buttonDelCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent NewCar) {
			}
		});
		buttonDelCar.setIcon(delIcon);
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, buttonDelCar, 0,
				SpringLayout.NORTH, buttonEditCar);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, buttonDelCar, 5,
				SpringLayout.EAST, buttonEditCar);
		buttonDelCar.setOpaque(false);
		buttonDelCar.setContentAreaFilled(false);
		buttonDelCar.setBorder(null);
		buttonDelCar.setFocusPainted(false);
		panelCarsTab.add(buttonDelCar);

		buttonSave = new JButton(""); //$NON-NLS-1$
		buttonSave.setIcon(saveIcon);
		buttonSave.setFocusPainted(false);
		sl_panelCarsTab.putConstraint(SpringLayout.SOUTH, buttonSave, -10,
				SpringLayout.SOUTH, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.EAST, buttonSave, -10,
				SpringLayout.EAST, panelCarsTab);
		panelCarsTab.add(buttonSave);

		// /BARRE DE PROGRESSION
		panelBarreProgress = new JPanel();
		panelBarreProgress.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		panelBarreProgress
		.setPreferredSize(new Dimension(frmTop.getWidth(), 45));
		springLayout.putConstraint(SpringLayout.NORTH, panelBarreProgress, 0,
				SpringLayout.SOUTH, panelTime);
		springLayout.putConstraint(SpringLayout.WEST, panelBarreProgress, 0,
				SpringLayout.WEST, frmTop.getContentPane());
		frmTop.getContentPane().add(panelBarreProgress);
		SpringLayout sl_panelBarreProgress = new SpringLayout();
		panelBarreProgress.setLayout(sl_panelBarreProgress);

		progressBarRace = new JProgressBar();
		sl_panelBarreProgress.putConstraint(SpringLayout.NORTH,
				progressBarRace, 0, SpringLayout.NORTH, panelBarreProgress);
		sl_panelBarreProgress.putConstraint(SpringLayout.SOUTH,
				progressBarRace, 0, SpringLayout.SOUTH, panelBarreProgress);
		sl_panelBarreProgress.putConstraint(SpringLayout.WEST, progressBarRace,
				0, SpringLayout.WEST, panelBarreProgress);
		sl_panelBarreProgress.putConstraint(SpringLayout.EAST, progressBarRace,
				0, SpringLayout.EAST, panelBarreProgress);
		progressBarRace.setValue(50);
		panelBarreProgress.add(progressBarRace);

		// /RESULTATS
		panelResults = new JTabbedPane(JTabbedPane.TOP);
		panelResults.setPreferredSize(new Dimension(frmTop.getWidth(), frmTop
				.getHeight()
				- panelTime.getPreferredSize().height
				- panelBarreProgress.getPreferredSize().height));
		springLayout.putConstraint(SpringLayout.NORTH, panelResults, 0,
				SpringLayout.SOUTH, panelBarreProgress);
		springLayout.putConstraint(SpringLayout.WEST, panelResults, 0,
				SpringLayout.WEST, frmTop.getContentPane());
		frmTop.getContentPane().add(panelResults);

		// panelTopsTab
		panelTopsTab = new JPanel();
		sl_panelTopsTab = new SpringLayout();
		panelTopsTab.setLayout(sl_panelTopsTab);
		panelResults.addTab("Tops", panelTopsTab);

		// panelRankingTab
		panelRankingTab = new JPanel();
		sl_panelRankingTab = new SpringLayout();
		panelRankingTab.setLayout(sl_panelRankingTab);
		panelResults.addTab(BUNDLE.getString("RankingString"), panelRankingTab);

		//TABLEAU DE TOPS

		tableTops = new JTable(donnees, entetesTops);
		tableTops.setFillsViewportHeight(true);
		JScrollPane JScrollPaneTops = new JScrollPane(tableTops);
		JScrollPaneTops.setPreferredSize(new Dimension(frmTop.getWidth(),
				frmTop.getHeight() - panelTime.getPreferredSize().height
				- panelBarreProgress.getPreferredSize().height));
		sl_panelTopsTab.putConstraint(SpringLayout.NORTH, JScrollPaneTops, 0,
				SpringLayout.NORTH, panelTopsTab);
		sl_panelTopsTab.putConstraint(SpringLayout.WEST, JScrollPaneTops, 0,
				SpringLayout.WEST, panelTopsTab);

		panelTopsTab.add(JScrollPaneTops, BorderLayout.CENTER);

		frmTop.setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == comboBox) {
			controlT.miseEnPlacePilotesTrain((String) comboBox.getSelectedItem(), this);
			labelCarNumber.setText((String) comboBox.getSelectedItem());
		}
		if (arg0.getSource() == buttonTop && isTimerOn) {
			String num = listNextPassages.getItem(0);
			listNextPassages.add(num);
			listNextPassages.remove(0);
			String pil = (String) comboBoxCurrentDriver.getSelectedItem();
			String remarque = textAreaNewRemark.getText();
			controlT.topPresse(this, num, pil, remarque);
			textAreaNewRemark.setText("");
			numTop++;
		}
		if (arg0.getSource() == comboBoxCurrentDriver) {
			int relai = Integer.parseInt(textFieldCurrentRelay.getText());
			relai++;
			textFieldCurrentRelay.setText(String.valueOf(relai));

		}
		if(arg0.getSource() == buttonBottomPassage) {

			try {
				String voitureCourante = listNextPassages.getSelectedItem();
				int idVoitureCourante = listNextPassages.getSelectedIndex();
				if (idVoitureCourante < listNextPassages.getItemCount()-1) {
					listNextPassages.replaceItem(listNextPassages.getItem(idVoitureCourante+1), idVoitureCourante);
					listNextPassages.replaceItem(voitureCourante, idVoitureCourante+1);
				}
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
		if(arg0.getSource() == buttonTopPassage) {

			try {
				String voitureCourante = listNextPassages.getSelectedItem();
				int idVoitureCourante = listNextPassages.getSelectedIndex();
				if (idVoitureCourante != 0) {
					listNextPassages.replaceItem(listNextPassages.getItem(idVoitureCourante-1), idVoitureCourante);
					listNextPassages.replaceItem(voitureCourante, idVoitureCourante-1);
				}
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}

	}

	public void miseEnPlaceEvenement(String nomCourse, String nomCircuit, String remarque) {

		labelDate.setText(date.format(new Date()));
		labelRemark.setText(remarque);
		labelCircuitName.setText(nomCircuit);
		labelRaceName.setText(nomCourse);
	}

	public long tempsCourant() {
		return timeElapsed;
	}

	public void miseEnPlaceVoitures(String[] listCar, String[] listCarSel) {
		for (int i = 0; i < listCarSel.length; i++) {
			comboBox.addItem(listCarSel[i]);
			listNextPassages.add(listCarSel[i]);
		}
		for (int j = 0; j < listCar.length; j++) {
			listCars.add(listCar[j]);
		}
	}

	public void miseEnPlaceApresTop(long tempsEstimTour, long tempsProchPass, int relaiEnCours) {

		textFieldNextPassage.setText(String.valueOf(tempsProchPass));
		textFieldEstimatedLapTime.setText(String.valueOf(tempsEstimTour));
		textFieldCurrentRelay.setText(String.valueOf(relaiEnCours));

	}

	public long stringEnLong(String dateString) {

		String[] res = dateString.split(":");
		return Long.parseLong(res[0])*3600000 + Long.parseLong(res[1])*60000 + Long.parseLong(res[2])*1000;
	}

	public String longEnString(long dateLong) {

		long seconde = (dateLong / 1000) % 60;
		long minute = (dateLong / 60000) % 60;
		long heure = (dateLong / 3600000) % 60;
		String secondeTexte="";
		String minuteTexte="";
		String heureTexte="";

		if (seconde < 10) {
			secondeTexte = "0";
		}

		if (minute < 10) {
			minuteTexte = "0";
		}

		if (heure < 10) {
			heureTexte = "0";
		}

		return heureTexte + heure + ":" + minuteTexte + minute + ":" + secondeTexte +seconde;
	}

	public void miseEnPlaceTop(String numPilote, String nomPilote,
			long tempsTour, int numTour, long tempsTotal,
			String commentaire) {

		Object[] donneesAjouter = { numTop, numPilote, nomPilote, longEnString(tempsTour),
				numTour, 0, longEnString(tempsTotal), 0, textFieldCurrentRelay.getText(), commentaire };

		for(int i =0;i<donneesAjouter.length;i++) {
			donnees[numTop][i] = donneesAjouter[i];
		}
		
		if (typeCourse>0 && numTour >= nbMaxDeTours) {
			timerChrono.stop();
		}
		
		tableTops.repaint();

	}

	public void miseEnPlaceVoiture(int nbTour) {
		textFieldLaps.setText(String.valueOf(nbTour));
	}

	public void miseEnPlaceTrain(java.util.List<String> listTrainsPneu) {

		Iterator<String> it = listTrainsPneu.iterator();
		comboBoxCurrentTyres.removeAllItems();
		while(it.hasNext()) {
			comboBoxCurrentTyres.addItem(it.next());
		}
	}

	public void miseEnPlacePilotes(java.util.List<String> listPilotes) {

		Iterator<String> it = listPilotes.iterator();
		comboBoxCurrentDriver.removeAllItems();
		while(it.hasNext()) {
			comboBoxCurrentDriver.addItem(it.next());
		}
	}

	public void miseEnPlaceHeuresTours(Date heuredebut, Date heurefin, int nbMaxDeTours) {

		heureDeb = stringEnLong(dateFormat.format(heuredebut));
		heureFin = stringEnLong(dateFormat.format(heurefin));
		this.nbMaxDeTours = nbMaxDeTours;
		
		if (nbMaxDeTours == 0) {
			typeCourse = 0;
		}
		else if(dateFormat.format(heurefin).equals("00:00:00")) {
			typeCourse = 2;
		}
		else {
			typeCourse = 1;
		}
		
	}


	public void popUpErreurSelection(){
		JOptionPane.showMessageDialog(this, "Veuillez selectionner un item", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpDepartCourse() {
		JOptionPane.showMessageDialog(this, "La course commence !", "Depart", JOptionPane.WARNING_MESSAGE);
	}
	

	// Met � jour les diff�rents textes qu'il faut traduire lorsque l'on change
	// de langue
	private void updateLanguage() {
		frmTop.setTitle(BUNDLE.getString("CustomizationEventString"));
		mnMenuBarEventLanguage.setText(BUNDLE.getString("LanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageEnglish.setText(BUNDLE
				.getString("EnglishLanguageString"));
		mnMenuBarEventLanguageFrench.setText(BUNDLE
				.getString("FrenchLanguageString"));
		frmTop.setTitle(BUNDLE.getString("RaceInterfaceString"));
		labelCurrentTime.setText(BUNDLE.getString("CurrentTimeString"));
		labelElapsedTime.setText(BUNDLE.getString("ElapsedTimeString"));
		labelRemainingTime.setText(BUNDLE.getString("TimeRemainingString"));
		labelNextPassages
		.setText(BUNDLE.getString("NextPassagesString") + " :");
		labelCarNumber.setText(BUNDLE.getString("CarNumberString"));
		labelCurrentDriver.setText(BUNDLE.getString("CurrentDriverString"));
		labelCurrentTyres.setText(BUNDLE.getString("TyresSetString"));
		labelLaps.setText(BUNDLE.getString("LapsString") + " :");
		labelRemainingLaps.setText(BUNDLE.getString("RemainingString") + " :");
		labelCurrentRelay
		.setText(BUNDLE.getString("CurrentRelayString") + " :");
		labelLapsSinceLastRelay.setText(BUNDLE
				.getString("LapsSinceLastRelayString")
				+ " :");
		labelLapsBeforeRelay.setText(BUNDLE.getString("LapsBeforeRelayString")
				+ " :");
		labelEstimatedLapTime.setText(BUNDLE
				.getString("EstimatedLapTimeString"));
		labelNextPassage.setText(BUNDLE.getString("NextPassageString"));
		panelInformation.setTitleAt(0, BUNDLE.getString("GeneralString"));
		labelMainCar.setText(BUNDLE.getString("MainCarString"));
		labelNewRemark.setText(BUNDLE.getString("RemarkString") + " :");
		panelInformation.setTitleAt(1, BUNDLE.getString("CarsString"));
		labelAvailableCars.setText(BUNDLE.getString("AvailableCarsString"));
		updateTableHeaderColumnNames(tableTops, entetesTops);
		panelResults.setTitleAt(1, BUNDLE.getString("RankingString"));
		updateTableHeaderColumnNames(tableRanking, entetesRanking);

	}

	public static void updateTableHeaderColumnNames(JTable table,
			String[] headers) {
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int k = 0; k <= th.getComponentCount(); k++) {
			tcm.getColumn(k).setHeaderValue(headers[k]);
		}
		th.repaint();
	}


}
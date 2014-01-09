package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FenetreTop3 extends JFrame {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	// Déclarations des éléments de la fenêtre
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
	private JList listNextPassages;
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

	ImageIcon chronoIcon = new ImageIcon(this.getClass().getResource("/resources/chronoIcon.png"));
	ImageIcon chronoIconPressed = new ImageIcon(this.getClass().getResource("/resources/chronoIconPressed.png"));
	ImageIcon addIcon = new ImageIcon(this.getClass().getResource("/resources/addIcon.png"));
	ImageIcon saveIcon = new ImageIcon(this.getClass().getResource("/resources/saveIcon.png"));
	ImageIcon topIcon = new ImageIcon(FenetreNewCar.class.getResource("/resources/topIcon.png"));
	ImageIcon bottomIcon = new ImageIcon(FenetreNewCar.class.getResource("/resources/bottomIcon.png"));
	ImageIcon editIcon = new ImageIcon(this.getClass().getResource("/resources/editIcon.png"));
	ImageIcon delIcon = new ImageIcon(this.getClass().getResource("/resources/delIcon.png"));
	
	
	private JTabbedPane panelInformation;
	private String[] entetesTops;
	String[] entetesRanking;

	private JTabbedPane panelResults;

	private JButton buttonTopPassage;

	private AbstractButton buttonBottomPassage;

	private JButton buttonEditCar;

	private AbstractButton buttonDelCar;

	/**
	 * / * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreTop3 window = new FenetreTop3();
					window.frmTop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetreTop3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTop = new JFrame();
		frmTop.setResizable(false);
		frmTop.setSize(screenSize.width, screenSize.height*9/10);
		frmTop.setLocation((screenSize.width - frmTop.getWidth()) / 2, (screenSize.height - frmTop.getHeight()) / 3);
		frmTop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTop.setTitle(BUNDLE.getString("RaceInterfaceString")); //$NON-NLS-1$
		SpringLayout springLayout = new SpringLayout();
		frmTop.getContentPane().setLayout(springLayout);

		// //BARRE DES MENUS////

		menuBarEvent = new JMenuBar();
		menuBarEvent.setSize(new Dimension(50, 150));
		springLayout.putConstraint(SpringLayout.WEST, menuBarEvent, 0, SpringLayout.WEST, frmTop.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBarEvent, 0, SpringLayout.EAST, frmTop.getContentPane());
		frmTop.getContentPane().add(menuBarEvent);

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

		if (Locale.getDefault().toString().equals("English"))
			mnMenuBarEventLanguageEnglish.setSelected(true);
		else
			mnMenuBarEventLanguageFrench.setSelected(true);

		frmTop.setVisible(true);

		// ///PANELS////

		// /TEMPS

		JPanel panelTime = new JPanel();
		panelTime.setPreferredSize(new Dimension(frmTop.getWidth() / 3, frmTop.getHeight() *2/3 - menuBarEvent.getHeight()));
		panelTime.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panelTime, 0, SpringLayout.SOUTH, menuBarEvent);
		springLayout.putConstraint(SpringLayout.WEST, panelTime, 0, SpringLayout.WEST, frmTop.getContentPane());
		frmTop.getContentPane().add(panelTime);
		SpringLayout sl_panelTime = new SpringLayout();
		panelTime.setLayout(sl_panelTime);

		// HEURE
		labelCurrentTime = new JLabel(BUNDLE.getString("CurrentTimeString")); //$NON-NLS-1$
		labelCurrentTime.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelCurrentTime, 0, SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelCurrentTime, 0, SpringLayout.EAST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelCurrentTime, 10, SpringLayout.NORTH, panelTime);
		labelCurrentTime.setFont(Police.getFontSubtitle());
		panelTime.add(labelCurrentTime);

		/*
		 * Mise à jour de l'heure
		 */
		final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		labelCurrentTimeValue = new JLabel(""); //$NON-NLS-1$=
		labelCurrentTimeValue.setText(dateFormat.format(new Date()));
		ActionListener updateClock = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelCurrentTimeValue.setText(dateFormat.format(new Date()));
			}
		};
		Timer t = new Timer(1000, updateClock);
		t.start();

		sl_panelTime.putConstraint(SpringLayout.NORTH, labelCurrentTimeValue, 5, SpringLayout.SOUTH, labelCurrentTime);
		labelCurrentTimeValue.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelCurrentTimeValue, 0, SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelCurrentTimeValue, 0, SpringLayout.EAST, panelTime);
		labelCurrentTimeValue.setFont(Police.getFontTitle());
		panelTime.add(labelCurrentTimeValue);

		// TEMPS ECOULE
		labelElapsedTime = new JLabel(BUNDLE.getString("ElapsedTimeString")); //$NON-NLS-1$
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelElapsedTime, 15, SpringLayout.SOUTH, labelCurrentTimeValue);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelElapsedTime, 0, SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelElapsedTime, -panelTime.getPreferredSize().width / 2 - 80, SpringLayout.EAST, panelTime);
		labelElapsedTime.setHorizontalAlignment(SwingConstants.CENTER);
		labelElapsedTime.setFont(Police.getFontSubtitle());
		panelTime.add(labelElapsedTime);

		labelElapsedTimeValue = new JLabel("12:53:45");
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelElapsedTimeValue, 5, SpringLayout.SOUTH, labelElapsedTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelElapsedTimeValue, 0, SpringLayout.EAST, labelElapsedTime);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelElapsedTimeValue, 0, SpringLayout.WEST, labelElapsedTime);
		labelElapsedTimeValue.setHorizontalAlignment(SwingConstants.CENTER);
		labelElapsedTimeValue.setFont(Police.getFontTitle());
		panelTime.add(labelElapsedTimeValue);

		// TEMPS RESTANT
		labelRemainingTime = new JLabel(BUNDLE.getString("TimeRemainingString")); //$NON-NLS-1$
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelRemainingTime, 0, SpringLayout.NORTH, labelElapsedTime);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelRemainingTime, -panelTime.getPreferredSize().width / 2 + 80, SpringLayout.EAST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelRemainingTime, 0, SpringLayout.EAST, panelTime);
		labelRemainingTime.setHorizontalAlignment(SwingConstants.CENTER);
		labelRemainingTime.setFont(Police.getFontSubtitle());
		panelTime.add(labelRemainingTime);

		labelRemainingTimeValue = new JLabel("12:53:45");
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelRemainingTimeValue, 5, SpringLayout.SOUTH, labelRemainingTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelRemainingTimeValue, 0, SpringLayout.EAST, labelRemainingTime);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelRemainingTimeValue, 0, SpringLayout.WEST, labelRemainingTime);
		labelRemainingTimeValue.setHorizontalAlignment(SwingConstants.CENTER);
		labelRemainingTimeValue.setFont(Police.getFontTitle());
		panelTime.add(labelRemainingTimeValue);

		// BOUTON TOP
		buttonTop = new JButton(chronoIcon); //$NON-NLS-1$		
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
		sl_panelTime.putConstraint(SpringLayout.NORTH, buttonTop, -10, SpringLayout.NORTH, labelElapsedTime);
		sl_panelTime.putConstraint(SpringLayout.WEST, buttonTop, 180, SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, buttonTop, -180, SpringLayout.EAST, panelTime);
		panelTime.add(buttonTop);

		// PROCHAINS PASSAGES
		listNextPassages = new JList();
		sl_panelTime.putConstraint(SpringLayout.NORTH, listNextPassages, 95, SpringLayout.NORTH, labelElapsedTimeValue);
		sl_panelTime.putConstraint(SpringLayout.WEST, listNextPassages, 0, SpringLayout.WEST, buttonTop);
		listNextPassages.setPreferredSize(new Dimension(400,200));
		sl_panelTime.putConstraint(SpringLayout.EAST, listNextPassages, 0, SpringLayout.EAST, buttonTop);
		listNextPassages.setBorder(new LineBorder(new Color(0, 0, 0)));
		listNextPassages.setSelectedIndex(0);
		panelTime.add(listNextPassages);

		labelNextPassages = new JLabel(BUNDLE.getString("NextPassagesString") + " :");
		labelNextPassages.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNextPassages.setFont(Police.getFontDefault());
		sl_panelTime.putConstraint(SpringLayout.NORTH, labelNextPassages, 0, SpringLayout.NORTH, listNextPassages);
		sl_panelTime.putConstraint(SpringLayout.WEST, labelNextPassages, 0, SpringLayout.WEST, panelTime);
		sl_panelTime.putConstraint(SpringLayout.EAST, labelNextPassages, -10, SpringLayout.WEST, listNextPassages);
		panelTime.add(labelNextPassages);		
		
		buttonTopPassage = new JButton(""); //$NON-NLS-1$
		buttonTopPassage.setIcon(topIcon);
		buttonTopPassage.setOpaque(false);
		buttonTopPassage.setFocusPainted(false);
		buttonTopPassage.setContentAreaFilled(false);
		buttonTopPassage.setBorder(null);
		sl_panelTime.putConstraint(SpringLayout.NORTH,buttonTopPassage,20,SpringLayout.NORTH, listNextPassages);
		sl_panelTime.putConstraint(SpringLayout.WEST,buttonTopPassage,8,SpringLayout.EAST, listNextPassages);
		panelTime.add(buttonTopPassage);
		
		buttonBottomPassage = new JButton(""); //$NON-NLS-1$
		buttonBottomPassage.setIcon(bottomIcon);
		buttonBottomPassage.setOpaque(false);
		buttonBottomPassage.setFocusPainted(false);
		buttonBottomPassage.setContentAreaFilled(false);
		buttonBottomPassage.setBorder(null);
		sl_panelTime.putConstraint(SpringLayout.NORTH,buttonBottomPassage,2,SpringLayout.SOUTH, buttonTopPassage);
		sl_panelTime.putConstraint(SpringLayout.WEST,buttonBottomPassage,8,SpringLayout.EAST, listNextPassages);
		panelTime.add(buttonBottomPassage);

		// /VOITURE PRINCIPALE
		JPanel panelMainCar = new JPanel();
		panelMainCar.setPreferredSize(new Dimension(frmTop.getWidth() / 3 + 80, frmTop.getHeight() *2/3- menuBarEvent.getHeight()));
		panelMainCar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panelMainCar, 0, SpringLayout.SOUTH, menuBarEvent);
		springLayout.putConstraint(SpringLayout.WEST, panelMainCar, 0, SpringLayout.EAST, panelTime);
		SpringLayout sl_panelMainCar = new SpringLayout();
		panelMainCar.setLayout(sl_panelMainCar);
		frmTop.getContentPane().add(panelMainCar);

		labelCarNumber = new JLabel(BUNDLE.getString("CarNumberString") + numeroVoiture); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCarNumber, 10, SpringLayout.NORTH, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelCarNumber, 0, SpringLayout.WEST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelCarNumber, 0, SpringLayout.EAST, panelMainCar);
		labelCarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		labelCarNumber.setFont(Police.getFontSubtitle());
		panelMainCar.add(labelCarNumber);

		// LISTE DES PILOTES DE LA VOITURE
		labelCurrentDriver = new JLabel(BUNDLE.getString("CurrentDriverString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCurrentDriver, 60, SpringLayout.SOUTH, labelCarNumber);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelCurrentDriver, 25, SpringLayout.WEST, panelMainCar);
		labelCurrentDriver.setFont((Police.getFontDefault()));
		panelMainCar.add(labelCurrentDriver);

		comboBoxCurrentDriver = new JComboBox();
		sl_panelMainCar.putConstraint(SpringLayout.WEST, comboBoxCurrentDriver, 10, SpringLayout.EAST, labelCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, comboBoxCurrentDriver, -4, SpringLayout.NORTH, labelCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, comboBoxCurrentDriver, panelMainCar.getPreferredSize().width / 2, SpringLayout.WEST,
				panelMainCar);
		comboBoxCurrentDriver.setFont((Police.getFontDefault()));
		panelMainCar.add(comboBoxCurrentDriver);

		// LISTE DES TRAINS DE PNEUS
		labelCurrentTyres = new JLabel(BUNDLE.getString("TyresSetString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCurrentTyres, 60, SpringLayout.SOUTH, labelCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelCurrentTyres, 0, SpringLayout.EAST, labelCurrentDriver);
		labelCurrentTyres.setFont((Police.getFontDefault()));
		panelMainCar.add(labelCurrentTyres);

		comboBoxCurrentTyres = new JComboBox();
		sl_panelMainCar.putConstraint(SpringLayout.WEST, comboBoxCurrentTyres, 10, SpringLayout.EAST, labelCurrentTyres);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, comboBoxCurrentTyres, -4, SpringLayout.NORTH, labelCurrentTyres);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, comboBoxCurrentTyres, panelMainCar.getPreferredSize().width / 2, SpringLayout.WEST,
				panelMainCar);
		comboBoxCurrentTyres.setFont((Police.getFontDefault()));
		panelMainCar.add(comboBoxCurrentTyres);

		// TOURS
		labelLaps = new JLabel(BUNDLE.getString("LapsString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelLaps, 15, SpringLayout.SOUTH, labelCarNumber);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelLaps, panelMainCar.getPreferredSize().width / 2 - 150, SpringLayout.WEST, panelMainCar);
		labelLaps.setFont(new Font("Century Gothic", Font.BOLD, 16));
		panelMainCar.add(labelLaps);

		textFieldLaps = new JTextField();
		textFieldLaps.setText(""); //$NON-NLS-1$
		textFieldLaps.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldLaps, 0, SpringLayout.NORTH, labelLaps);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, textFieldLaps, 10, SpringLayout.EAST, labelLaps);
		textFieldLaps.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldLaps);

		// TOURS RESTANTS
		textFieldRemainingLaps = new JTextField();
		textFieldRemainingLaps.setText(""); //$NON-NLS-1$
		textFieldRemainingLaps.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldRemainingLaps, 0, SpringLayout.NORTH, labelLaps);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, textFieldRemainingLaps, panelMainCar.getPreferredSize().width / 2 + 150, SpringLayout.WEST,
				panelMainCar);
		textFieldRemainingLaps.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldRemainingLaps);

		labelRemainingLaps = new JLabel(BUNDLE.getString("RemainingString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelRemainingLaps, -10, SpringLayout.WEST, textFieldRemainingLaps);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelRemainingLaps, 0, SpringLayout.NORTH, labelLaps);
		labelRemainingLaps.setFont(new Font("Century Gothic", Font.BOLD, 16));
		panelMainCar.add(labelRemainingLaps);

		// RELAIS EN COURS
		labelCurrentRelay = new JLabel(BUNDLE.getString("CurrentRelayString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelCurrentRelay, 100, SpringLayout.EAST, comboBoxCurrentDriver);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelCurrentRelay, 0, SpringLayout.NORTH, comboBoxCurrentDriver);
		labelCurrentRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(labelCurrentRelay);

		textFieldCurrentRelay = new JTextField();
		textFieldCurrentRelay.setText(""); //$NON-NLS-1$
		textFieldCurrentRelay.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldCurrentRelay, 0, SpringLayout.NORTH, labelCurrentRelay);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, textFieldCurrentRelay, 10, SpringLayout.EAST, labelCurrentRelay);
		textFieldCurrentRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldCurrentRelay);

		// TOUR DEPUIS DERNIER RELAIS
		textFieldLapsSinceLastRelay = new JTextField();
		textFieldLapsSinceLastRelay.setText(""); //$NON-NLS-1$
		textFieldLapsSinceLastRelay.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldLapsSinceLastRelay, 21, SpringLayout.SOUTH, textFieldCurrentRelay);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, textFieldLapsSinceLastRelay, 0, SpringLayout.EAST, textFieldCurrentRelay);
		textFieldLapsSinceLastRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldLapsSinceLastRelay);

		labelLapsSinceLastRelay = new JLabel(BUNDLE.getString("LapsSinceLastRelayString") + " :"); //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelLapsSinceLastRelay, -10, SpringLayout.WEST, textFieldLapsSinceLastRelay);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelLapsSinceLastRelay, 0, SpringLayout.NORTH, textFieldLapsSinceLastRelay);
		labelLapsSinceLastRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(labelLapsSinceLastRelay);

		// TOURS AVANT RELAIS
		textFieldLapsBeforeRelay = new JTextField();
		textFieldLapsBeforeRelay.setText(""); //$NON-NLS-1$
		textFieldLapsBeforeRelay.setPreferredSize(new Dimension(40, 20));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldLapsBeforeRelay, 0, SpringLayout.NORTH, comboBoxCurrentTyres);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, textFieldLapsBeforeRelay, 0, SpringLayout.EAST, textFieldLapsSinceLastRelay);
		textFieldLapsBeforeRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(textFieldLapsBeforeRelay);

		labelLapsBeforeRelay = new JLabel(BUNDLE.getString("LapsBeforeRelayString") + " :"); //$NON-NLS-1$ //$NON-NLS-1$
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelLapsBeforeRelay, -10, SpringLayout.WEST, textFieldLapsBeforeRelay);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelLapsBeforeRelay, 0, SpringLayout.NORTH, textFieldLapsBeforeRelay);
		labelLapsBeforeRelay.setFont((Police.getFontDefault()));
		panelMainCar.add(labelLapsBeforeRelay);

		// TEMPS ESTIMÉ
		labelEstimatedLapTime = new JLabel(BUNDLE.getString("EstimatedLapTimeString")); //$NON-NLS-1$
		labelEstimatedLapTime.setPreferredSize(new Dimension(panelMainCar.getPreferredSize().width / 2, 30));
		sl_panelMainCar.putConstraint(SpringLayout.WEST, labelEstimatedLapTime, 0, SpringLayout.WEST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelEstimatedLapTime, 40, SpringLayout.SOUTH, labelLapsBeforeRelay);
		labelEstimatedLapTime.setFont((Police.getFontSubtitle()));
		labelEstimatedLapTime.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(labelEstimatedLapTime);

		textFieldEstimatedLapTime = new JTextField();
		textFieldEstimatedLapTime.setText(""); //$NON-NLS-1$
		textFieldEstimatedLapTime.setPreferredSize(new Dimension(120, 30));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldEstimatedLapTime, 5, SpringLayout.SOUTH, labelEstimatedLapTime);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, textFieldEstimatedLapTime, panelMainCar.getPreferredSize().width / 4
				- textFieldEstimatedLapTime.getPreferredSize().width / 2, SpringLayout.WEST, panelMainCar);
		textFieldEstimatedLapTime.setFont((Police.getFontDefault()));
		textFieldEstimatedLapTime.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(textFieldEstimatedLapTime);

		// PROCHAIN PASSAGE
		labelNextPassage = new JLabel(BUNDLE.getString("NextPassageString")); //$NON-NLS-1$
		labelNextPassage.setPreferredSize(new Dimension(panelMainCar.getPreferredSize().width / 2, 30));
		sl_panelMainCar.putConstraint(SpringLayout.EAST, labelNextPassage, 0, SpringLayout.EAST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, labelNextPassage, 0, SpringLayout.NORTH, labelEstimatedLapTime);
		labelNextPassage.setFont((Police.getFontSubtitle()));
		labelNextPassage.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(labelNextPassage);

		textFieldNextPassage = new JTextField();
		textFieldNextPassage.setText(""); //$NON-NLS-1$
		textFieldNextPassage.setPreferredSize(new Dimension(120, 30));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, textFieldNextPassage, 5, SpringLayout.SOUTH, labelNextPassage);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, textFieldNextPassage,
				panelMainCar.getPreferredSize().width * 3 / 4 - textFieldNextPassage.getPreferredSize().width / 2, SpringLayout.WEST, panelMainCar);
		textFieldNextPassage.setFont((Police.getFontDefault()));
		textFieldNextPassage.setHorizontalAlignment(SwingConstants.CENTER);
		panelMainCar.add(textFieldNextPassage);

		// BARRE DE PROGRESSION VOITURE PRINCIPALE
		progressBarMainCar = new JProgressBar();
		progressBarMainCar.setPreferredSize(new Dimension(146, 30));
		sl_panelMainCar.putConstraint(SpringLayout.NORTH, progressBarMainCar, 30, SpringLayout.SOUTH, textFieldNextPassage);
		sl_panelMainCar.putConstraint(SpringLayout.WEST, progressBarMainCar, 70, SpringLayout.WEST, panelMainCar);
		sl_panelMainCar.putConstraint(SpringLayout.EAST, progressBarMainCar, -70, SpringLayout.EAST, panelMainCar);
		progressBarMainCar.setValue(35);
		panelMainCar.add(progressBarMainCar);

		// /INFOS
		panelInformation = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, panelInformation, 0, SpringLayout.SOUTH, menuBarEvent);
		springLayout.putConstraint(SpringLayout.WEST, panelInformation, 0, SpringLayout.EAST, panelMainCar);
		panelInformation.setPreferredSize(new Dimension(frmTop.getWidth() / 3 - 80, frmTop.getHeight() *2/3 - menuBarEvent.getHeight()));
		panelInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmTop.getContentPane().add(panelInformation);

		// panelGeneralInformationTab
		JPanel panelGeneralInformationTab = new JPanel();
		SpringLayout sl_panelGeneralInformationTab = new SpringLayout();
		panelGeneralInformationTab.setLayout(sl_panelGeneralInformationTab);

		panelInformation.addTab(BUNDLE.getString("GeneralString"), panelGeneralInformationTab);

		final DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		labelDate = new JLabel("");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, labelDate, 10, SpringLayout.NORTH, panelGeneralInformationTab);
		labelDate.setText(dateFormat2.format(new Date()));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, labelDate, 0, SpringLayout.WEST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST, labelDate, 0, SpringLayout.EAST, panelGeneralInformationTab);
		labelDate.setFont(Police.getFontDefault());
		labelDate.setHorizontalAlignment(SwingConstants.CENTER);
		panelGeneralInformationTab.add(labelDate);

		labelCircuitName = new JLabel("Nom du circuit...");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, labelCircuitName, 10, SpringLayout.SOUTH, labelDate);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST, labelCircuitName, 0, SpringLayout.EAST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, labelCircuitName, 0, SpringLayout.WEST, panelGeneralInformationTab);
		labelCircuitName.setHorizontalAlignment(SwingConstants.CENTER);
		labelCircuitName.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelCircuitName);

		labelRaceName = new JLabel("Nom de la course...");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, labelRaceName, 10, SpringLayout.SOUTH, labelCircuitName);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST, labelRaceName, 0, SpringLayout.EAST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, labelRaceName, 0, SpringLayout.WEST, panelGeneralInformationTab);
		labelRaceName.setHorizontalAlignment(SwingConstants.CENTER);
		labelRaceName.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelRaceName);

		labelRemark = new JLabel("Remarques...");
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, labelRemark, 10, SpringLayout.SOUTH, labelRaceName);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST, labelRemark, 0, SpringLayout.EAST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, labelRemark, 0, SpringLayout.WEST, panelGeneralInformationTab);
		labelRemark.setHorizontalAlignment(SwingConstants.CENTER);
		labelRemark.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelRemark);

		// Choix voiture principale
		labelMainCar = new JLabel(BUNDLE.getString("MainCarString") + " :"); //$NON-NLS-1$
		labelMainCar.setPreferredSize(new Dimension(150, 20));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, labelMainCar, 40, SpringLayout.SOUTH, labelRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, labelMainCar,
				panelInformation.getPreferredSize().width / 2 - labelMainCar.getPreferredSize().width, SpringLayout.WEST, panelGeneralInformationTab);
		labelMainCar.setHorizontalAlignment(SwingConstants.CENTER);
		labelMainCar.setFont(Police.getFontDefault());
		panelGeneralInformationTab.add(labelMainCar);

		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(150, 30));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.EAST, labelMainCar);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, comboBox, -4, SpringLayout.NORTH, labelMainCar);
		comboBox.setFont((Police.getFontDefault()));
		panelGeneralInformationTab.add(comboBox);

		// Commentaires
		labelNewRemark = new JLabel(BUNDLE.getString("RemarkString") + " :"); //$NON-NLS-1$
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, labelNewRemark, 10, SpringLayout.WEST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, labelNewRemark, 65, SpringLayout.SOUTH, labelMainCar);
		labelNewRemark.setPreferredSize(new Dimension(150, 20));
		labelNewRemark.setHorizontalAlignment(SwingConstants.CENTER);
		labelNewRemark.setFont((Police.getFontDefault()));
		panelGeneralInformationTab.add(labelNewRemark);

		textAreaNewRemark = new JTextArea();
		textAreaNewRemark.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaNewRemark.setText(""); //$NON-NLS-1$
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, textAreaNewRemark, 10, SpringLayout.EAST, labelNewRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.EAST, textAreaNewRemark, -20, SpringLayout.EAST, panelGeneralInformationTab);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, textAreaNewRemark, -30, SpringLayout.NORTH, labelNewRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.SOUTH, textAreaNewRemark, 30, SpringLayout.SOUTH, labelNewRemark);
		panelGeneralInformationTab.add(textAreaNewRemark);

		buttonOK = new JButton("OK"); //$NON-NLS-1$		
		buttonOK.setPreferredSize(new Dimension(60, 30));
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.NORTH, buttonOK, 10, SpringLayout.SOUTH, textAreaNewRemark);
		sl_panelGeneralInformationTab.putConstraint(SpringLayout.WEST, buttonOK,
				panelInformation.getPreferredSize().width / 2 - buttonOK.getPreferredSize().width / 2, SpringLayout.WEST, panelGeneralInformationTab);
		panelGeneralInformationTab.add(buttonOK);

		// panelCarsTab
		JPanel panelCarsTab = new JPanel();
		SpringLayout sl_panelCarsTab = new SpringLayout();
		panelCarsTab.setLayout(sl_panelCarsTab);
		panelInformation.addTab(BUNDLE.getString("CarsString"), panelCarsTab);

		labelAvailableCars = new JLabel(BUNDLE.getString("AvailableCarsString"));
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, labelAvailableCars, 10, SpringLayout.NORTH, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, labelAvailableCars, 0, SpringLayout.WEST, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.EAST, labelAvailableCars, 0, SpringLayout.EAST, panelCarsTab);
		labelAvailableCars.setHorizontalAlignment(SwingConstants.CENTER);
		labelAvailableCars.setFont(Police.getFontDefault());
		panelCarsTab.add(labelAvailableCars);

		listCars = new List();
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, listCars, 10, SpringLayout.SOUTH, labelAvailableCars);
		sl_panelCarsTab.putConstraint(SpringLayout.SOUTH, listCars, -100, SpringLayout.SOUTH, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, listCars, 120, SpringLayout.WEST, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.EAST, listCars, -120, SpringLayout.EAST, panelCarsTab);
		panelCarsTab.add(listCars);

		buttonAddCar = new JButton(""); //$NON-NLS-1$
		buttonAddCar.setIcon(addIcon);
		buttonAddCar.setOpaque(false);
		buttonAddCar.setFocusPainted(false);
		buttonAddCar.setContentAreaFilled(false);
		buttonAddCar.setBorder(null);
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, buttonAddCar, 0, SpringLayout.SOUTH, listCars);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, buttonAddCar, 0, SpringLayout.WEST, listCars);
		panelCarsTab.add(buttonAddCar);
		
		buttonEditCar = new JButton(""); //$NON-NLS-1$
		buttonEditCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent NewDriverEvent) {
				new FenetreNewCar();
			}
		});
		buttonEditCar.setIcon(editIcon);
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, buttonEditCar, 0, SpringLayout.NORTH, buttonAddCar);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, buttonEditCar, 5, SpringLayout.EAST, buttonAddCar);
		buttonEditCar.setOpaque(false);
		buttonEditCar.setContentAreaFilled(false);
		buttonEditCar.setBorder(null);
		buttonEditCar.setFocusPainted(false);
		panelCarsTab.add(buttonEditCar);
		
		buttonDelCar = new JButton(""); //$NON-NLS-1$
		buttonDelCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent NewCar) {
				new FenetreNewCar();
			}
		});
		buttonDelCar.setIcon(delIcon);
		sl_panelCarsTab.putConstraint(SpringLayout.NORTH, buttonDelCar, 0, SpringLayout.NORTH, buttonEditCar);
		sl_panelCarsTab.putConstraint(SpringLayout.WEST, buttonDelCar, 5, SpringLayout.EAST, buttonEditCar);
		buttonDelCar.setOpaque(false);
		buttonDelCar.setContentAreaFilled(false);
		buttonDelCar.setBorder(null);
		buttonDelCar.setFocusPainted(false);
		panelCarsTab.add(buttonDelCar);

		buttonSave = new JButton(""); //$NON-NLS-1$
		buttonSave.setIcon(saveIcon);
		buttonSave.setFocusPainted(false);
		sl_panelCarsTab.putConstraint(SpringLayout.SOUTH, buttonSave, -10, SpringLayout.SOUTH, panelCarsTab);
		sl_panelCarsTab.putConstraint(SpringLayout.EAST, buttonSave, -10, SpringLayout.EAST, panelCarsTab);
		panelCarsTab.add(buttonSave);

		// /BARRE DE PROGRESSION
		JPanel panelBarreProgress = new JPanel();
		panelBarreProgress.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBarreProgress.setPreferredSize(new Dimension(frmTop.getWidth(), 45));
		springLayout.putConstraint(SpringLayout.NORTH, panelBarreProgress, 0, SpringLayout.SOUTH, panelTime);
		springLayout.putConstraint(SpringLayout.WEST, panelBarreProgress, 0, SpringLayout.WEST, frmTop.getContentPane());
		frmTop.getContentPane().add(panelBarreProgress);
		SpringLayout sl_panelBarreProgress = new SpringLayout();
		panelBarreProgress.setLayout(sl_panelBarreProgress);

		progressBarRace = new JProgressBar();
		sl_panelBarreProgress.putConstraint(SpringLayout.NORTH, progressBarRace, 0, SpringLayout.NORTH, panelBarreProgress);
		sl_panelBarreProgress.putConstraint(SpringLayout.SOUTH, progressBarRace, 0, SpringLayout.SOUTH, panelBarreProgress);
		sl_panelBarreProgress.putConstraint(SpringLayout.WEST, progressBarRace, 0, SpringLayout.WEST, panelBarreProgress);
		sl_panelBarreProgress.putConstraint(SpringLayout.EAST, progressBarRace, 0, SpringLayout.EAST, panelBarreProgress);
		progressBarRace.setValue(50);
		panelBarreProgress.add(progressBarRace);

		// /RESULTATS
		panelResults = new JTabbedPane(JTabbedPane.TOP);
		panelResults.setPreferredSize(new Dimension(frmTop.getWidth(), frmTop.getHeight() - panelTime.getPreferredSize().height
				- panelBarreProgress.getPreferredSize().height));
		springLayout.putConstraint(SpringLayout.NORTH, panelResults, 0, SpringLayout.SOUTH, panelBarreProgress);
		springLayout.putConstraint(SpringLayout.WEST, panelResults, 0, SpringLayout.WEST, frmTop.getContentPane());
		frmTop.getContentPane().add(panelResults);

		// panelTopsTab
		panelTopsTab = new JPanel();
		SpringLayout sl_panelTopsTab = new SpringLayout();
		panelTopsTab.setLayout(sl_panelTopsTab);
		panelResults.addTab("Tops", panelTopsTab);

		entetesTops = new String[] { BUNDLE.getString("TopNumberString"), BUNDLE.getString("CarString"), BUNDLE.getString("DriverString"),
				BUNDLE.getString("LapTimeString"), BUNDLE.getString("LapsString"), BUNDLE.getString("StateString"),
				BUNDLE.getString("TopDateString"), BUNDLE.getString("LapsRelayString"), BUNDLE.getString("RelayString"),
				BUNDLE.getString("RemarkString") };

		Object[][] donneesTops = { { 1, "005", "PROST", "00:02:43:42", 1, 0, "12:48:45:42", 1, 1, "Sortie" },
				{ 2, "007", "SENNA", "00:02:43:42", 1, 0, "12:48:45:42", 1, 1, "Sortie" }, };

		tableTops = new JTable(donneesTops, entetesTops);
		tableTops.setFillsViewportHeight(true);
		JScrollPane JScrollPaneTops = new JScrollPane(tableTops);
		JScrollPaneTops.setPreferredSize(new Dimension(frmTop.getWidth(), frmTop.getHeight() - panelTime.getPreferredSize().height
				- panelBarreProgress.getPreferredSize().height));
		sl_panelTopsTab.putConstraint(SpringLayout.NORTH, JScrollPaneTops, 0, SpringLayout.NORTH, panelTopsTab);
		sl_panelTopsTab.putConstraint(SpringLayout.WEST, JScrollPaneTops, 0, SpringLayout.WEST, panelTopsTab);

		panelTopsTab.add(JScrollPaneTops, BorderLayout.CENTER);

		// panelRankingTab
		panelRankingTab = new JPanel();
		SpringLayout sl_panelRankingTab = new SpringLayout();
		panelRankingTab.setLayout(sl_panelRankingTab);
		panelResults.addTab(BUNDLE.getString("RankingString"), panelRankingTab);

		entetesRanking = new String[] { BUNDLE.getString("RankingString"), BUNDLE.getString("CarString"), BUNDLE.getString("DriverString"),
				BUNDLE.getString("TimeString") };
		Object[][] donneesRanking = { { 1, "005", "PROST", "00:22:43:42" }, { 2, "007", "SENNA", "00:22:43:44" }, };

		tableRanking = new JTable(donneesRanking, entetesRanking);
		tableRanking.setFillsViewportHeight(true);
		JScrollPane JScrollPaneRanking = new JScrollPane(tableRanking);
		JScrollPaneRanking.setPreferredSize(new Dimension(frmTop.getWidth(), frmTop.getHeight() - panelTime.getPreferredSize().height
				- panelBarreProgress.getPreferredSize().height));
		sl_panelRankingTab.putConstraint(SpringLayout.NORTH, JScrollPaneRanking, 0, SpringLayout.NORTH, panelRankingTab);
		sl_panelRankingTab.putConstraint(SpringLayout.WEST, JScrollPaneRanking, 0, SpringLayout.WEST, panelRankingTab);
		panelRankingTab.add(JScrollPaneRanking, BorderLayout.CENTER);

		frmTop.setVisible(true);
	}

	// Met à jour les différents textes qu'il faut traduire lorsque l'on change de langue
	private void updateLanguage() {
		frmTop.setTitle(BUNDLE.getString("CustomizationEventString"));
		mnMenuBarEventLanguage.setText(BUNDLE.getString("LanguageString")); //$NON-NLS-1$
		mnMenuBarEventLanguageEnglish.setText(BUNDLE.getString("EnglishLanguageString"));
		mnMenuBarEventLanguageFrench.setText(BUNDLE.getString("FrenchLanguageString"));
		frmTop.setTitle(BUNDLE.getString("RaceInterfaceString"));
		labelCurrentTime.setText(BUNDLE.getString("CurrentTimeString"));
		labelElapsedTime.setText(BUNDLE.getString("ElapsedTimeString"));
		labelRemainingTime.setText(BUNDLE.getString("TimeRemainingString"));
		labelNextPassages.setText(BUNDLE.getString("NextPassagesString")+" :");		
		labelCarNumber.setText(BUNDLE.getString("CarNumberString"));
		labelCurrentDriver.setText(BUNDLE.getString("CurrentDriverString"));
		labelCurrentTyres.setText(BUNDLE.getString("TyresSetString"));
		labelLaps.setText(BUNDLE.getString("LapsString")+" :");
		labelRemainingLaps.setText(BUNDLE.getString("RemainingString")+" :");
		labelCurrentRelay.setText(BUNDLE.getString("CurrentRelayString")+" :");
		labelLapsSinceLastRelay.setText(BUNDLE.getString("LapsSinceLastRelayString")+" :");
		labelLapsBeforeRelay.setText(BUNDLE.getString("LapsBeforeRelayString")+" :");
		labelEstimatedLapTime.setText(BUNDLE.getString("EstimatedLapTimeString"));
		labelNextPassage.setText(BUNDLE.getString("NextPassageString"));
		panelInformation.setTitleAt(0, BUNDLE.getString("GeneralString"));
		labelMainCar.setText(BUNDLE.getString("MainCarString"));
		labelNewRemark.setText(BUNDLE.getString("RemarkString")+" :");
		panelInformation.setTitleAt(1, BUNDLE.getString("CarsString"));
		labelAvailableCars.setText(BUNDLE.getString("AvailableCarsString"));
		updateTableHeaderColumnNames(tableTops, entetesTops);
		panelResults.setTitleAt(1, BUNDLE.getString("RankingString"));
		updateTableHeaderColumnNames(tableRanking, entetesRanking);

	}

	public static void updateTableHeaderColumnNames(JTable table, String[] headers) {
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for (int k = 0; k <= th.getComponentCount(); k++) {
			tcm.getColumn(k).setHeaderValue(headers[k]);
		}
		th.repaint();
	}

}
package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import model.Pneu;


import controleur.ControleurTrainPneu;

public class FenetreNewTyres extends JFrame implements ActionListener, Observer {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	// Charge les différents noms des types de pneus
	String[] tyreTypes = { "Slick", BUNDLE.getString("RainTyreString") };

	// fenetre de la voiture courante :
	
	FenetreNewCar fenetreCar;
	
	// Controleur :

	private ControleurTrainPneu tp;

	// id de la voiture courante :

	private int idVoiture;
	private int idTrain; 

	// Charge les différentes duretés des pneus
	String[] tyreHardness = { BUNDLE.getString("SoftTyreString"), BUNDLE.getString("HardTyreString"), BUNDLE.getString("SemihardTyreString") };

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	// Déclarations des éléments de la fenêtre
	private JFrame frmNewTyres;
	private JLabel labelSetNumber;
	private JLabel lblTitleTyres;
	private JLabel labelTyresType;
	private JLabel labelTyreHardness;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JTextField textFieldSetNumber;

	private JComboBox comboBoxTyresType;

	private JComboBox comboBoxTyreHardness;
	private JSeparator separatorVerticalH;

	private JSeparator separatorHorizontalG;

	private JSeparator separatorVerticalB;

	private JSeparator separatorHorizontalD;
	private JLabel label;
	private JLabel labelLF;

	private JLabel labelRF;

	private JLabel labelLR;

	private JLabel labelRR;

	private JLabel labelPAVG;

	private JTextField textFieldPAVG;

	private JLabel labelTIntAVG;

	private JTextField textFieldTIntAVG;

	private JLabel labelTMedAVG;

	private JTextField textFieldTMedAVG;

	private JTextField textFieldTExtAVG;

	private JLabel labelTExtAVG;

	private JLabel labelPARG;

	private JTextField textFieldPARG;

	private JLabel labelTIntARG;

	private JTextField textFieldTIntARG;

	private JLabel labelTMedARG;

	private JTextField textFieldTMedARG;

	private JLabel labelTExtARG;

	private JTextField textFieldTExtARG;

	private JLabel labelPAVD;

	private JTextField textFieldPAVD;

	private JLabel labelTIntAVD;

	private JTextField textFieldTIntAVD;

	private JLabel labelTMedAVD;

	private JTextField textFieldTMedAVD;

	private JTextField textFieldTExtAVD;

	private JLabel labelTExtAVD;

	private JTextField textFieldPARD;

	private JLabel labelPARD;

	private JLabel labelTIntARD;

	private JTextField textFieldTIntARD;

	private JLabel labelTMedARD;

	private JTextField textFieldTMedARD;

	private JLabel labelTExtARD;

	private JTextField textFieldTExtARD;

	/**
	 * Create the application.
	 */
	public FenetreNewTyres(ControleurTrainPneu tp, int idVoiture, int idTrain) {
		this.tp = tp;
		this.idTrain = idTrain;
		this.idVoiture = idVoiture;
		tp.setTrainPneu(idTrain, idVoiture);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewTyres = new JFrame();
		frmNewTyres.setResizable(false);
		frmNewTyres.setSize(800, 700);
		frmNewTyres.setLocation((screenSize.width - frmNewTyres.getWidth()) / 2 + 50, (screenSize.height - frmNewTyres.getHeight()) / 3 + 50);
		frmNewTyres.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmNewTyres.setTitle(BUNDLE.getString("CustomizationTyresString")); //$NON-NLS-1$
		SpringLayout springLayout = new SpringLayout();
		frmNewTyres.getContentPane().setLayout(springLayout);

		// //TITRE////
		lblTitleTyres = new JLabel(BUNDLE.getString("CustomizationTyresString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, lblTitleTyres, 0, SpringLayout.WEST, frmNewTyres.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTitleTyres, 16, SpringLayout.NORTH, frmNewTyres.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTitleTyres, frmNewTyres.getWidth(), SpringLayout.WEST, frmNewTyres.getContentPane());
		lblTitleTyres.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleTyres.setFont(Police.getFontTitle());
		frmNewTyres.getContentPane().add(lblTitleTyres);

		// //NUMERO DU TRAIN////
		textFieldSetNumber = new JTextField();
		textFieldSetNumber.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldSetNumber, 15, SpringLayout.SOUTH, lblTitleTyres);
		springLayout.putConstraint(SpringLayout.EAST, textFieldSetNumber, frmNewTyres.getWidth() / 2 + textFieldSetNumber.getPreferredSize().width
				/ 2, SpringLayout.WEST, frmNewTyres.getContentPane());
		textFieldSetNumber.setText("");
		textFieldSetNumber.setHorizontalAlignment(SwingConstants.LEFT);
		frmNewTyres.getContentPane().add(textFieldSetNumber);
		textFieldSetNumber.addActionListener(this);

		labelSetNumber = new JLabel(BUNDLE.getString("SetNumberString") + " :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelSetNumber, 0, SpringLayout.NORTH, textFieldSetNumber);
		springLayout.putConstraint(SpringLayout.EAST, labelSetNumber, -10, SpringLayout.WEST, textFieldSetNumber);
		labelSetNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewTyres.getContentPane().add(labelSetNumber);


		// //TYPE DES PNEUS////
		labelTyresType = new JLabel(BUNDLE.getString("TyresTypeString") + " :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelTyresType, 15, SpringLayout.SOUTH, labelSetNumber);
		springLayout.putConstraint(SpringLayout.EAST, labelTyresType, 0, SpringLayout.EAST, labelSetNumber);
		labelTyresType.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewTyres.getContentPane().add(labelTyresType);

		comboBoxTyresType = new JComboBox(tyreTypes);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxTyresType, -2, SpringLayout.NORTH, labelTyresType);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxTyresType, 10, SpringLayout.EAST, labelTyresType);
		comboBoxTyresType.setEditable(true);
		frmNewTyres.getContentPane().add(comboBoxTyresType);
		comboBoxTyresType.addActionListener(this);


		// //DURETE DES PNEUS////
		labelTyreHardness = new JLabel(BUNDLE.getString("TyreHardnessString") + " :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelTyreHardness, 15, SpringLayout.SOUTH, labelTyresType);
		springLayout.putConstraint(SpringLayout.EAST, labelTyreHardness, 0, SpringLayout.EAST, labelSetNumber);
		labelTyreHardness.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewTyres.getContentPane().add(labelTyreHardness);

		comboBoxTyreHardness = new JComboBox(tyreHardness);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxTyreHardness, -2, SpringLayout.NORTH, labelTyreHardness);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxTyreHardness, 10, SpringLayout.EAST, labelTyreHardness);
		comboBoxTyreHardness.setEditable(true);
		frmNewTyres.getContentPane().add(comboBoxTyreHardness);
		comboBoxTyreHardness.addActionListener(this);


		// //BOUTON ENREGISTRER////
		buttonSave = new JButton(BUNDLE.getString("SaveString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonSave, -10, SpringLayout.SOUTH, frmNewTyres.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonSave, frmNewTyres.getWidth() / 2 - 50, SpringLayout.WEST, frmNewTyres.getContentPane());
		buttonSave.setPreferredSize(new Dimension(120, 30));
		frmNewTyres.getContentPane().add(buttonSave);
		buttonSave.addActionListener(this);


		// //BOUTON ANNULER////
		buttonCancel = new JButton(BUNDLE.getString("CancelString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCancel, -10, SpringLayout.SOUTH, frmNewTyres.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonCancel, frmNewTyres.getWidth() / 2 + 50, SpringLayout.WEST, frmNewTyres.getContentPane());
		buttonCancel.setPreferredSize(new Dimension(120, 30));
		frmNewTyres.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(this);


		// //SEPARATEUR VERTICAL HAUT////
		separatorVerticalH = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separatorVerticalH, 50, SpringLayout.SOUTH, comboBoxTyreHardness);
		springLayout
		.putConstraint(SpringLayout.WEST, separatorVerticalH, frmNewTyres.getWidth() / 2, SpringLayout.WEST, frmNewTyres.getContentPane());
		separatorVerticalH.setPreferredSize(new Dimension(1, 200));
		separatorVerticalH.setOrientation(SwingConstants.VERTICAL);
		frmNewTyres.getContentPane().add(separatorVerticalH);

		// //SEPARATEUR VERTICAL BAS////
		separatorVerticalB = new JSeparator();
		springLayout.putConstraint(SpringLayout.SOUTH, separatorVerticalB, -50, SpringLayout.NORTH, buttonCancel);
		springLayout
		.putConstraint(SpringLayout.WEST, separatorVerticalB, frmNewTyres.getWidth() / 2, SpringLayout.WEST, frmNewTyres.getContentPane());
		separatorVerticalB.setPreferredSize(new Dimension(1, 200));
		separatorVerticalB.setOrientation(SwingConstants.VERTICAL);
		frmNewTyres.getContentPane().add(separatorVerticalB);

		// //SEPARATEUR HORIZONTAL GAUCHE////
		separatorHorizontalG = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separatorHorizontalG, 175, SpringLayout.NORTH, separatorVerticalH);
		springLayout.putConstraint(SpringLayout.WEST, separatorHorizontalG, 100, SpringLayout.WEST, frmNewTyres.getContentPane());
		separatorHorizontalG.setPreferredSize(new Dimension(400, 1));
		separatorHorizontalG.setOrientation(SwingConstants.HORIZONTAL);
		frmNewTyres.getContentPane().add(separatorHorizontalG);

		// //SEPARATEUR HORIZONTAL DROIT////

		separatorHorizontalD = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separatorHorizontalD, 175, SpringLayout.NORTH, separatorVerticalH);
		springLayout.putConstraint(SpringLayout.EAST, separatorHorizontalD, -100, SpringLayout.EAST, frmNewTyres.getContentPane());
		separatorHorizontalD.setPreferredSize(new Dimension(400, 1));
		separatorHorizontalD.setOrientation(SwingConstants.HORIZONTAL);

		frmNewTyres.getContentPane().add(separatorHorizontalD);

		// PNEU AVANT GAUCHE
		labelLF = new JLabel(BUNDLE.getString("LeftFrontString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.SOUTH, labelLF, -6, SpringLayout.NORTH, separatorHorizontalG);
		springLayout.putConstraint(SpringLayout.EAST, labelLF, -6, SpringLayout.WEST, separatorVerticalH);
		frmNewTyres.getContentPane().add(labelLF);

		labelPAVG = new JLabel("P :");
		springLayout.putConstraint(SpringLayout.WEST, labelPAVG, -150, SpringLayout.EAST, separatorVerticalH);
		springLayout.putConstraint(SpringLayout.NORTH, labelPAVG, 30, SpringLayout.NORTH, separatorVerticalH);
		frmNewTyres.getContentPane().add(labelPAVG);

		textFieldPAVG = new JTextField();
		textFieldPAVG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldPAVG, 10, SpringLayout.EAST, labelPAVG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPAVG, 0, SpringLayout.NORTH, labelPAVG);
		frmNewTyres.getContentPane().add(textFieldPAVG);
		textFieldPAVG.addActionListener(this);


		labelTIntAVG = new JLabel("T° int. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTIntAVG, 0, SpringLayout.EAST, labelPAVG);
		springLayout.putConstraint(SpringLayout.NORTH, labelTIntAVG, 15, SpringLayout.SOUTH, labelPAVG);
		frmNewTyres.getContentPane().add(labelTIntAVG);

		textFieldTIntAVG = new JTextField();
		textFieldTIntAVG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTIntAVG, 10, SpringLayout.EAST, labelTIntAVG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTIntAVG, 0, SpringLayout.NORTH, labelTIntAVG);
		frmNewTyres.getContentPane().add(textFieldTIntAVG);
		textFieldTIntAVG.addActionListener(this);

		labelTMedAVG = new JLabel("T° med. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTMedAVG, 0, SpringLayout.EAST, labelTIntAVG);
		springLayout.putConstraint(SpringLayout.NORTH, labelTMedAVG, 15, SpringLayout.SOUTH, labelTIntAVG);
		frmNewTyres.getContentPane().add(labelTMedAVG);

		textFieldTMedAVG = new JTextField();
		textFieldTMedAVG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTMedAVG, 10, SpringLayout.EAST, labelTMedAVG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTMedAVG, 0, SpringLayout.NORTH, labelTMedAVG);
		frmNewTyres.getContentPane().add(textFieldTMedAVG);
		textFieldTMedAVG.addActionListener(this);


		labelTExtAVG = new JLabel("T° ext. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTExtAVG, 0, SpringLayout.EAST, labelTMedAVG);
		springLayout.putConstraint(SpringLayout.NORTH, labelTExtAVG, 15, SpringLayout.SOUTH, labelTMedAVG);
		frmNewTyres.getContentPane().add(labelTExtAVG);

		textFieldTExtAVG = new JTextField();
		textFieldTExtAVG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTExtAVG, 10, SpringLayout.EAST, labelTExtAVG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTExtAVG, 0, SpringLayout.NORTH, labelTExtAVG);
		frmNewTyres.getContentPane().add(textFieldTExtAVG);
		textFieldTExtAVG.addActionListener(this);


		// PNEU ARRIERE GAUCHE
		labelLR = new JLabel(BUNDLE.getString("LeftRearString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelLR, 6, SpringLayout.NORTH, separatorHorizontalG);
		springLayout.putConstraint(SpringLayout.EAST, labelLR, -6, SpringLayout.WEST, separatorVerticalH);
		frmNewTyres.getContentPane().add(labelLR);

		labelPARG = new JLabel("P :");
		springLayout.putConstraint(SpringLayout.WEST, labelPARG, -150, SpringLayout.EAST, separatorVerticalH);
		springLayout.putConstraint(SpringLayout.NORTH, labelPARG, 30, SpringLayout.NORTH, separatorHorizontalG);
		frmNewTyres.getContentPane().add(labelPARG);

		textFieldPARG = new JTextField();
		textFieldPARG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldPARG, 10, SpringLayout.EAST, labelPARG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPARG, 0, SpringLayout.NORTH, labelPARG);
		frmNewTyres.getContentPane().add(textFieldPARG);
		textFieldPARG.addActionListener(this);

		labelTIntARG = new JLabel("T° int. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTIntARG, 0, SpringLayout.EAST, labelPARG);
		springLayout.putConstraint(SpringLayout.NORTH, labelTIntARG, 15, SpringLayout.SOUTH, labelPARG);
		frmNewTyres.getContentPane().add(labelTIntARG);

		textFieldTIntARG = new JTextField();
		textFieldTIntARG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTIntARG, 10, SpringLayout.EAST, labelTIntARG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTIntARG, 0, SpringLayout.NORTH, labelTIntARG);
		frmNewTyres.getContentPane().add(textFieldTIntARG);
		textFieldTIntARG.addActionListener(this);


		labelTMedARG = new JLabel("T° med. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTMedARG, 0, SpringLayout.EAST, labelTIntARG);
		springLayout.putConstraint(SpringLayout.NORTH, labelTMedARG, 15, SpringLayout.SOUTH, labelTIntARG);
		frmNewTyres.getContentPane().add(labelTMedARG);

		textFieldTMedARG = new JTextField();
		textFieldTMedARG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTMedARG, 10, SpringLayout.EAST, labelTMedARG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTMedARG, 0, SpringLayout.NORTH, labelTMedARG);
		frmNewTyres.getContentPane().add(textFieldTMedARG);
		textFieldTMedARG.addActionListener(this);


		labelTExtARG = new JLabel("T° ext. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTExtARG, 0, SpringLayout.EAST, labelTMedARG);
		springLayout.putConstraint(SpringLayout.NORTH, labelTExtARG, 15, SpringLayout.SOUTH, labelTMedARG);
		frmNewTyres.getContentPane().add(labelTExtARG);

		textFieldTExtARG = new JTextField();
		textFieldTExtARG.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTExtARG, 10, SpringLayout.EAST, labelTExtARG);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTExtARG, 0, SpringLayout.NORTH, labelTExtARG);
		frmNewTyres.getContentPane().add(textFieldTExtARG);
		textFieldTExtARG.addActionListener(this);


		// PNEU AVANT DROIT
		labelRF = new JLabel(BUNDLE.getString("RightFrontString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.SOUTH, labelRF, -6, SpringLayout.NORTH, separatorHorizontalG);
		springLayout.putConstraint(SpringLayout.WEST, labelRF, 6, SpringLayout.WEST, separatorVerticalH);
		frmNewTyres.getContentPane().add(labelRF);

		textFieldPAVD = new JTextField();
		textFieldPAVD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.EAST, textFieldPAVD, 150, SpringLayout.EAST, separatorVerticalH);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPAVD, 30, SpringLayout.NORTH, separatorVerticalH);
		frmNewTyres.getContentPane().add(textFieldPAVD);
		textFieldPAVD.addActionListener(this);


		labelPAVD = new JLabel("P :");
		springLayout.putConstraint(SpringLayout.EAST, labelPAVD, -10, SpringLayout.WEST, textFieldPAVD);
		springLayout.putConstraint(SpringLayout.NORTH, labelPAVD, 0, SpringLayout.NORTH, textFieldPAVD);
		frmNewTyres.getContentPane().add(labelPAVD);

		labelTIntAVD = new JLabel("T° int. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTIntAVD, 0, SpringLayout.EAST, labelPAVD);
		springLayout.putConstraint(SpringLayout.NORTH, labelTIntAVD, 15, SpringLayout.SOUTH, labelPAVD);
		frmNewTyres.getContentPane().add(labelTIntAVD);

		textFieldTIntAVD = new JTextField();
		textFieldTIntAVD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTIntAVD, 10, SpringLayout.EAST, labelTIntAVD);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTIntAVD, 0, SpringLayout.NORTH, labelTIntAVD);
		frmNewTyres.getContentPane().add(textFieldTIntAVD);
		textFieldTIntAVD.addActionListener(this);


		labelTMedAVD = new JLabel("T° med. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTMedAVD, 0, SpringLayout.EAST, labelTIntAVD);
		springLayout.putConstraint(SpringLayout.NORTH, labelTMedAVD, 15, SpringLayout.SOUTH, labelTIntAVD);
		frmNewTyres.getContentPane().add(labelTMedAVD);

		textFieldTMedAVD = new JTextField();
		textFieldTMedAVD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTMedAVD, 10, SpringLayout.EAST, labelTMedAVD);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTMedAVD, 0, SpringLayout.NORTH, labelTMedAVD);
		frmNewTyres.getContentPane().add(textFieldTMedAVD);
		textFieldTMedAVD.addActionListener(this);


		labelTExtAVD = new JLabel("T° ext. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTExtAVD, 0, SpringLayout.EAST, labelTMedAVD);
		springLayout.putConstraint(SpringLayout.NORTH, labelTExtAVD, 15, SpringLayout.SOUTH, labelTMedAVD);
		frmNewTyres.getContentPane().add(labelTExtAVD);

		textFieldTExtAVD = new JTextField();
		textFieldTExtAVD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTExtAVD, 10, SpringLayout.EAST, labelTExtAVD);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTExtAVD, 0, SpringLayout.NORTH, labelTExtAVD);
		frmNewTyres.getContentPane().add(textFieldTExtAVD);
		textFieldTExtAVD.addActionListener(this);


		// PNEU ARRIERE DROIT
		labelRR = new JLabel(BUNDLE.getString("RightRearString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelRR, 6, SpringLayout.NORTH, separatorHorizontalG);
		springLayout.putConstraint(SpringLayout.WEST, labelRR, 6, SpringLayout.WEST, separatorVerticalH);
		frmNewTyres.getContentPane().add(labelRR);

		labelRF = new JLabel(BUNDLE.getString("RightFrontString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.SOUTH, labelRF, -6, SpringLayout.NORTH, separatorHorizontalG);
		springLayout.putConstraint(SpringLayout.WEST, labelRF, 6, SpringLayout.WEST, separatorVerticalH);
		frmNewTyres.getContentPane().add(labelRF);

		textFieldPARD = new JTextField();
		textFieldPARD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.EAST, textFieldPARD, 150, SpringLayout.EAST, separatorVerticalH);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPARD, 30, SpringLayout.NORTH, separatorHorizontalG);
		frmNewTyres.getContentPane().add(textFieldPARD);
		textFieldTExtAVD.addActionListener(this);


		labelPARD = new JLabel("P :");
		springLayout.putConstraint(SpringLayout.EAST, labelPARD, -10, SpringLayout.WEST, textFieldPARD);
		springLayout.putConstraint(SpringLayout.NORTH, labelPARD, 0, SpringLayout.NORTH, textFieldPARD);
		frmNewTyres.getContentPane().add(labelPARD);

		labelTIntARD = new JLabel("T° int. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTIntARD, 0, SpringLayout.EAST, labelPARD);
		springLayout.putConstraint(SpringLayout.NORTH, labelTIntARD, 15, SpringLayout.SOUTH, labelPARD);
		frmNewTyres.getContentPane().add(labelTIntARD);

		textFieldTIntARD = new JTextField();
		textFieldTIntARD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTIntARD, 10, SpringLayout.EAST, labelTIntARD);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTIntARD, 0, SpringLayout.NORTH, labelTIntARD);
		frmNewTyres.getContentPane().add(textFieldTIntARD);
		textFieldTIntARD.addActionListener(this);


		labelTMedARD = new JLabel("T° med. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTMedARD, 0, SpringLayout.EAST, labelTIntARD);
		springLayout.putConstraint(SpringLayout.NORTH, labelTMedARD, 15, SpringLayout.SOUTH, labelTIntARD);
		frmNewTyres.getContentPane().add(labelTMedARD);

		textFieldTMedARD = new JTextField();
		textFieldTMedARD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTMedARD, 10, SpringLayout.EAST, labelTMedARD);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTMedARD, 0, SpringLayout.NORTH, labelTMedARD);
		frmNewTyres.getContentPane().add(textFieldTMedARD);
		textFieldTMedARD.addActionListener(this);


		labelTExtARD = new JLabel("T° ext. :");
		springLayout.putConstraint(SpringLayout.EAST, labelTExtARD, 0, SpringLayout.EAST, labelTMedARD);
		springLayout.putConstraint(SpringLayout.NORTH, labelTExtARD, 15, SpringLayout.SOUTH, labelTMedARD);
		frmNewTyres.getContentPane().add(labelTExtARD);

		textFieldTExtARD = new JTextField();
		textFieldTExtARD.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldTExtARD, 10, SpringLayout.EAST, labelTExtARD);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldTExtARD, 0, SpringLayout.NORTH, labelTExtARD);
		frmNewTyres.getContentPane().add(textFieldTExtARD);
		textFieldTExtARD.addActionListener(this);


		frmNewTyres.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == buttonSave) {
			String numero = textFieldSetNumber.getText();
			String durete = (String) comboBoxTyreHardness.getSelectedItem();
			String type = (String) comboBoxTyresType.getSelectedItem();
			String pressionARD = textFieldPARD.getText();
			String tempExtARD = textFieldTExtARD.getText();
			String tempIntARD = textFieldTIntARD.getText();
			String tempsMedARD =  textFieldTMedARD.getText();
			String pressionAVD = textFieldPAVD.getText();
			String tempExtAVD = textFieldTExtAVD.getText();
			String tempIntAVD = textFieldTIntAVD.getText();
			String tempsMedAVD =  textFieldTMedAVD.getText();
			String pressionARG = textFieldPARG.getText();
			String tempExtARG = textFieldTExtARG.getText();
			String tempIntARG = textFieldTIntARG.getText();
			String tempsMedARG =  textFieldTMedARG.getText();
			String pressionAVG = textFieldPAVG.getText();
			String tempExtAVG = textFieldTExtAVG.getText();
			String tempIntAVG = textFieldTIntAVG.getText();
			String tempsMedAVG =  textFieldTMedAVG.getText();

			if (numero.isEmpty()) {
				this.popUpErreur("numero du type de train");
			}
			if (durete.isEmpty()) {
				this.popUpErreur("durete du type de train");
			}
			if (type.isEmpty()) {
				this.popUpErreur("type de pneu");
			}
			else if (pressionARD.isEmpty()) {
				this.popUpErreur("pression du pneu ARD");
			}
			else if (tempExtARD.isEmpty()) {
				this.popUpErreur("temperature exterieure du pneu ARD");
			}
			else if (tempIntARD.isEmpty()) {
				this.popUpErreur("temperature interieure du pneu ARD");
			}
			else if (tempsMedARD.isEmpty()) {
				this.popUpErreur("temperature centrale (med) du pneu ARD");
			}
			else if (pressionAVD.isEmpty()) {
				this.popUpErreur("pression du pneu AVD");
			}
			else if (tempExtAVD.isEmpty()) {
				this.popUpErreur("temperature exterieure du pneu AVD");
			}
			else if (tempIntAVD.isEmpty()) {
				this.popUpErreur("temperature interieure du pneu AVD");
			}
			else if (tempsMedAVD.isEmpty()) {
				this.popUpErreur("temperature centrale (med) du pneu AVD");
			}
			else if (pressionARG.isEmpty()) {
				this.popUpErreur("pression du pneu ARG");
			}
			else if (tempExtARG.isEmpty()) {
				this.popUpErreur("temperature exterieure du pneu ARG");
			}
			else if (tempIntARG.isEmpty()) {
				this.popUpErreur("temperature interieure du pneu ARG");
			}
			else if (tempsMedARG.isEmpty()) {
				this.popUpErreur("temperature centrale (med) du pneu ARG");
			}
			else if (pressionAVG.isEmpty()) {
				this.popUpErreur("pression du pneu AVG");
			}
			else if (tempExtAVG.isEmpty()) {
				this.popUpErreur("temperature exterieure du pneu AVG");
			}
			else if (tempIntAVG.isEmpty()) {
				this.popUpErreur("temperature interieure du pneu AVG");
			}
			else if (tempsMedAVG.isEmpty()) {
				this.popUpErreur("temperature centrale (med) du pneu AVG");
			}
			else {
				tp.enregistrerPneuARD(pressionARD, tempExtARD, tempIntARD, tempsMedARD);
				tp.enregistrerPneuAVD( pressionAVD, tempExtAVD,  tempIntAVD, tempsMedAVD);
				tp.enregistrerPneuARG( pressionARG, tempExtARG,  tempIntARG, tempsMedARG);
				tp.enregistrerPneuAVG( pressionAVG, tempExtAVG, tempIntAVG, tempsMedAVG);
				tp.enregistrerTrainPneu(numero, type, durete);
				frmNewTyres.dispose();
			}
		}
		if (arg0.getSource() == buttonCancel) {
			try {
				fenetreCar.supprimerTrainEnCoursCreation(idVoiture);
			}
			catch(Exception e){
			}
			frmNewTyres.dispose();
		}
	}

	public void modifierTrainPneu(String numero, String type, String durete) {
		textFieldSetNumber.setText(numero);
		comboBoxTyreHardness.setSelectedItem(durete);
		comboBoxTyresType.setSelectedItem(type);
	}

	public void modifierPneuARD(String pressionARD, String tempExtARD, String tempIntARD, String tempsMedARD) {
		textFieldPARD.setText(pressionARD);
		textFieldTExtARD.setText(tempExtARD);
		textFieldTIntARD.setText(tempIntARD);
		textFieldTMedARD.setText(tempsMedARD);
	}

	public void modifierPneuAVD(String pressionAVD, String tempExtAVD, String tempIntAVD, String tempsMedAVD) {
		textFieldPAVD.setText(pressionAVD);
		textFieldTExtAVD.setText(tempExtAVD);
		textFieldTIntAVD.setText(tempIntAVD);
		textFieldTMedAVD.setText(tempsMedAVD);
	}

	public void modifierPneuARG(String pressionARG, String tempExtARG, String tempIntARG, String tempsMedARG) {
		textFieldPARG.setText(pressionARG);
		textFieldTExtARG.setText(tempExtARG);
		textFieldTIntARG.setText(tempIntARG);
		textFieldTMedARG.setText(tempsMedARG);
	}

	public void modifierPneuAVG(String pressionAVG, String tempExtAVG, String tempIntAVG, String tempsMedAVG) {
		textFieldPAVG.setText(pressionAVG);
		textFieldTExtAVG.setText(tempExtAVG);
		textFieldTIntAVG.setText(tempIntAVG);
		textFieldTMedAVG.setText(tempsMedAVG);
	}

	public void popUpErreur(String texte){
		JOptionPane.showMessageDialog(this, "Veillez remplir le champ : " + texte , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public void setFenetreCar(FenetreNewCar fenetreNewCar) {
		this.fenetreCar = fenetreNewCar;
	}
}

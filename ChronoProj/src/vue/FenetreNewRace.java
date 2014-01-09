package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import controleur.ControleurCourse;
import controleur.ControleurSeanceEssai;

public class FenetreNewRace extends JFrame implements ActionListener, Observer{
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	//format de date
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // fenetre evenement, dans le cas où la creation de la course est annulée, cela permet de supprimer la course ajoutée dans la liste de la fenetre evenement 
	
	FenetreNewEvent fenetreEvent;
	
	//Id de la course 
	
	private int idRace; 
	
	//Declaration du controleur

	ControleurSeanceEssai controlSE;
	ControleurCourse controlC;

	// Déclarations des éléments de la fenêtre
	private JFrame frmNewRace;
	private JLabel labelRaceName;
	private JLabel lblTitleRace;
	private JLabel labelHourStart;
	private JLabel labelMaxTotal;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JTextField textFieldRaceName;
	private JTextField textFieldMaxTotal;

	ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/startIcon.png"));
	ImageIcon startIconPressed = new ImageIcon(this.getClass().getResource("/resources/startIconPressed.png"));

	private JTextField textFieldHourStart;
	private JLabel labelMaxConsec;
	private JTextField textFieldMaxConsec;
	private JLabel labelEndRace;
	private JTextField textFieldHourEnd;
	private JLabel labelHourEnd;
	private JTextField textFieldMaxLaps;

	private JLabel labelMaxLaps;
	private JLabel labelRemark;

	private JTextArea textAreaRemark;

	private String typeCourse;

	/**
	 * Create the application.
	 */
	public FenetreNewRace(ControleurCourse controlC, int idRace) {
		this.controlC = controlC;
		controlC.setCourseStandard(idRace);
		typeCourse = "Course standard";
		this.idRace= idRace;
		initialize();
	}

	public FenetreNewRace(ControleurSeanceEssai controlSE, int idRace) {
		this.controlSE = controlSE;
		controlSE.setSeanceEssai(idRace);
		typeCourse = "Seance d'essai";
		this.idRace = idRace;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewRace = new JFrame();
		frmNewRace.setResizable(false);
		frmNewRace.setSize(800, 550);
		frmNewRace.setLocation((screenSize.width - frmNewRace.getWidth()) / 2 + 50, (screenSize.height - frmNewRace.getHeight()) / 3 + 50);
		frmNewRace.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmNewRace.setTitle(BUNDLE.getString("CustomizationRaceString")); //$NON-NLS-1$
		SpringLayout springLayout = new SpringLayout();
		frmNewRace.getContentPane().setLayout(springLayout);

		// //TITRE////
		lblTitleRace = new JLabel(BUNDLE.getString("CustomizationRaceString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, lblTitleRace, 0, SpringLayout.WEST, frmNewRace.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTitleRace, 16, SpringLayout.NORTH, frmNewRace.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTitleRace, frmNewRace.getWidth(), SpringLayout.WEST, frmNewRace.getContentPane());
		lblTitleRace.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleRace.setFont(Police.getFontTitle());
		frmNewRace.getContentPane().add(lblTitleRace);

		// //NOM DE LA COURSE////		
		textFieldRaceName = new JTextField();
		textFieldRaceName.setPreferredSize(new Dimension(300, 20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldRaceName, 15, SpringLayout.SOUTH, lblTitleRace);
		springLayout.putConstraint(SpringLayout.EAST, textFieldRaceName, frmNewRace.getWidth()/2 + textFieldRaceName.getPreferredSize().width/2, SpringLayout.WEST, frmNewRace.getContentPane());
		textFieldRaceName.setText("");
		textFieldRaceName.setHorizontalAlignment(SwingConstants.LEFT);
		frmNewRace.getContentPane().add(textFieldRaceName);
		textFieldRaceName.addActionListener(this);


		labelRaceName = new JLabel(BUNDLE.getString("NameString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.EAST, labelRaceName, -10, SpringLayout.WEST, textFieldRaceName);
		springLayout.putConstraint(SpringLayout.NORTH, labelRaceName, 0, SpringLayout.NORTH, textFieldRaceName);
		labelRaceName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelRaceName.setPreferredSize(new Dimension(120, 20));
		frmNewRace.getContentPane().add(labelRaceName);

		// //HEURE DE DEBUT////		
		textFieldHourStart = new JTextField();
		textFieldHourStart.setPreferredSize(new Dimension(50,20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldHourStart, 10, SpringLayout.SOUTH, textFieldRaceName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldHourStart, frmNewRace.getWidth()/2 + textFieldHourStart.getPreferredSize().width/2, SpringLayout.WEST, frmNewRace.getContentPane());
		textFieldHourStart.setText("");
		textFieldHourStart.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewRace.getContentPane().add(textFieldHourStart);
		textFieldHourStart.addActionListener(this);

		labelHourStart = new JLabel(BUNDLE.getString("HourStartString") + " :"); //$NON-NLS-1$	
		springLayout.putConstraint(SpringLayout.EAST, labelHourStart, -10, SpringLayout.WEST, textFieldHourStart);
		springLayout.putConstraint(SpringLayout.NORTH, labelHourStart, 0, SpringLayout.NORTH, textFieldHourStart);
		labelHourStart.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewRace.getContentPane().add(labelHourStart);

		// //DUREE MAX TOTALE/PILOTE////		
		textFieldMaxTotal = new JTextField();
		textFieldMaxTotal.setPreferredSize(new Dimension(50,20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldMaxTotal, 10, SpringLayout.SOUTH, textFieldHourStart);
		springLayout.putConstraint(SpringLayout.EAST, textFieldMaxTotal, frmNewRace.getWidth()/2 + textFieldMaxTotal.getPreferredSize().width/2, SpringLayout.WEST, frmNewRace.getContentPane());
		textFieldMaxTotal.setText("");
		textFieldMaxTotal.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewRace.getContentPane().add(textFieldMaxTotal);
		textFieldMaxTotal.addActionListener(this);

		labelMaxTotal = new JLabel(BUNDLE.getString("MaxTotalDurationString") + " :"); //$NON-NLS-1$	
		springLayout.putConstraint(SpringLayout.EAST, labelMaxTotal, -10, SpringLayout.WEST, textFieldMaxTotal);
		springLayout.putConstraint(SpringLayout.NORTH, labelMaxTotal, 0, SpringLayout.NORTH, textFieldMaxTotal);
		labelMaxTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewRace.getContentPane().add(labelMaxTotal);

		// //DUREE MAX CONSEC/PILOTE////		
		textFieldMaxConsec = new JTextField();
		textFieldMaxConsec.setPreferredSize(new Dimension(50,20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldMaxConsec, 10, SpringLayout.SOUTH, textFieldMaxTotal);
		springLayout.putConstraint(SpringLayout.EAST, textFieldMaxConsec, frmNewRace.getWidth()/2 + textFieldMaxConsec.getPreferredSize().width/2, SpringLayout.WEST, frmNewRace.getContentPane());
		textFieldMaxConsec.setText("");
		textFieldMaxConsec.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewRace.getContentPane().add(textFieldMaxConsec);
		textFieldMaxConsec.addActionListener(this);

		labelMaxConsec = new JLabel(BUNDLE.getString("MaxConsecutiveDurationString") + " :"); //$NON-NLS-1$	
		springLayout.putConstraint(SpringLayout.EAST, labelMaxConsec, -10, SpringLayout.WEST, textFieldMaxConsec);
		springLayout.putConstraint(SpringLayout.NORTH, labelMaxConsec, 0, SpringLayout.NORTH, textFieldMaxConsec);
		labelMaxConsec.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewRace.getContentPane().add(labelMaxConsec);

		// //FIN DE LA COURSE////
		labelEndRace = new JLabel(BUNDLE.getString("EndRaceString")+" :"); //$NON-NLS-1$
		labelEndRace.setPreferredSize(new Dimension(120,20));
		springLayout.putConstraint(SpringLayout.SOUTH, labelEndRace, 60, SpringLayout.SOUTH, textFieldMaxConsec);
		springLayout.putConstraint(SpringLayout.EAST, labelEndRace, frmNewRace.getWidth()/2 + labelEndRace.getPreferredSize().width/2, SpringLayout.WEST, frmNewRace.getContentPane());
		labelEndRace.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewRace.getContentPane().add(labelEndRace);


		// //HEURE FIN DE COURSE////		
		labelHourEnd = new JLabel(BUNDLE.getString("EndHourString")+" :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, labelHourEnd, frmNewRace.getWidth()/2 - 180, SpringLayout.WEST, frmNewRace.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, labelHourEnd, 10, SpringLayout.SOUTH, labelEndRace);
		frmNewRace.getContentPane().add(labelHourEnd);

		textFieldHourEnd = new JTextField();
		textFieldHourEnd.setPreferredSize(new Dimension(50,20));
		springLayout.putConstraint(SpringLayout.WEST, textFieldHourEnd, 10, SpringLayout.EAST, labelHourEnd);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldHourEnd, 0, SpringLayout.NORTH, labelHourEnd);
		textFieldHourEnd.setText("");
		textFieldHourEnd.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewRace.getContentPane().add(textFieldHourEnd);
		textFieldHourEnd.addActionListener(this);


		// //NB TOURS MAX COURSE////		
		textFieldMaxLaps = new JTextField();
		textFieldMaxLaps.setPreferredSize(new Dimension(50,20));		
		springLayout.putConstraint(SpringLayout.EAST, textFieldMaxLaps, frmNewRace.getWidth()/2 + 180, SpringLayout.WEST, frmNewRace.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, textFieldMaxLaps, 10, SpringLayout.SOUTH, labelEndRace);		
		textFieldMaxLaps.setText("");
		textFieldMaxLaps.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewRace.getContentPane().add(textFieldMaxLaps);
		textFieldMaxLaps.addActionListener(this);

		labelMaxLaps = new JLabel(BUNDLE.getString("MaxLapsString")+" :"); //$NON-NLS-1$		
		springLayout.putConstraint(SpringLayout.EAST, labelMaxLaps, -10, SpringLayout.WEST, textFieldMaxLaps);
		springLayout.putConstraint(SpringLayout.NORTH, labelMaxLaps, 0, SpringLayout.NORTH, textFieldMaxLaps);
		frmNewRace.getContentPane().add(labelMaxLaps);

		// //COMMENTAIRE////				
		textAreaRemark = new JTextArea();
		textAreaRemark.setText("");
		springLayout.putConstraint(SpringLayout.EAST, textAreaRemark, -250, SpringLayout.EAST, frmNewRace.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textAreaRemark, 250, SpringLayout.WEST, frmNewRace.getContentPane());

		labelRemark = new JLabel(BUNDLE.getString("RemarkString")+" :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelRemark, 100, SpringLayout.SOUTH, labelMaxLaps);
		springLayout.putConstraint(SpringLayout.EAST, labelRemark, -10, SpringLayout.WEST, textAreaRemark);
		frmNewRace.getContentPane().add(labelRemark);		

		springLayout.putConstraint(SpringLayout.NORTH, textAreaRemark, -40, SpringLayout.NORTH, labelRemark);
		springLayout.putConstraint(SpringLayout.SOUTH, textAreaRemark, 40, SpringLayout.SOUTH, labelRemark);		
		frmNewRace.getContentPane().add(textAreaRemark);

		// //BOUTON ENREGISTRER////
		buttonSave = new JButton(BUNDLE.getString("SaveString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonSave, -10, SpringLayout.SOUTH, frmNewRace.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonSave,  frmNewRace.getWidth()/2-50, SpringLayout.WEST,  frmNewRace.getContentPane());
		buttonSave.setPreferredSize(new Dimension(120, 30));
		frmNewRace.getContentPane().add(buttonSave);
		buttonSave.addActionListener(this);

		// //BOUTON ANNULER////
		buttonCancel = new JButton(BUNDLE.getString("CancelString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCancel, -10, SpringLayout.SOUTH, frmNewRace.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonCancel, frmNewRace.getWidth()/2+50, SpringLayout.WEST,  frmNewRace.getContentPane());
		buttonCancel.setPreferredSize(new Dimension(120, 30));
		frmNewRace.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(this);


		frmNewRace.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttonSave) {

			String heureDebut = textFieldHourStart.getText();
			String heureFin = textFieldHourEnd.getText();
			String nomCourse = textFieldRaceName.getText();
			String dureeConsMaxPilote = textFieldMaxConsec.getText();
			String dureeMaxPilote = textFieldMaxTotal.getText();
			String commentaire = textAreaRemark.getText();
			int nbMaxTours = 0;
			try {
				nbMaxTours = Integer.parseInt(textFieldMaxLaps.getText());
			}
			catch(Exception e){
				if (heureFin.isEmpty()) {  
					this.popUpErreur("nb max de tours ou heure fin (remplir un ou les deux)");
					return;
				}
			}
			if (heureFin.isEmpty()) {  
				heureFin="00:00:00";
			}
			if (nomCourse.isEmpty()) {  
				this.popUpErreur("nom de de la course");
			}
			else if (heureDebut.isEmpty()) {  
				this.popUpErreur("heure debut");
			}
			else {
				try {
					Date dHeureDebut = dateFormat.parse(heureDebut);
					Date dHeureFin = dateFormat.parse(heureFin);
					Date dDureeMaxPilote = dateFormat.parse(dureeMaxPilote);
					Date dDureeConsMaxPilote = dateFormat.parse(dureeConsMaxPilote);
					if (typeCourse.equals("Seance d'essai")) {
						controlSE.enregistrerSE(nomCourse, dHeureDebut, nbMaxTours, dHeureFin, dDureeConsMaxPilote, dDureeMaxPilote, commentaire);
						frmNewRace.dispose();
					}
					else {
						controlC.enregistrerC(nomCourse, dHeureDebut, nbMaxTours, dHeureFin, dDureeConsMaxPilote, dDureeMaxPilote, commentaire);
						frmNewRace.dispose();
					}
				}
				catch (ParseException e) {
					e.printStackTrace();
					popUpErreurDate();
				}
			}
		}
		if (arg0.getSource() == buttonCancel) {
			
			try {
				if (typeCourse.equals("Seance d'essai")) {
					fenetreEvent.supprimerSeanceEnCoursCreation(idRace);
				}
				else {
					fenetreEvent.supprimerCourseEnCoursCreation(idRace);
				}
			}
			catch(Exception e){
			}
			
			frmNewRace.dispose();
		}
	}
	
	public void modifierCourse(String nomCourse, Date dHeureDebut, int nbMaxTours, Date dHeureFin, Date dDureeConsMaxPilote, Date dDureeMaxPilote, String commentaire) {
		
		textFieldHourStart.setText(dateFormat.format(dHeureDebut));
		textFieldHourEnd.setText(dateFormat.format(dHeureFin));
		textFieldRaceName.setText(nomCourse);
		textFieldMaxConsec.setText(dateFormat.format(dDureeConsMaxPilote));
		textFieldMaxTotal.setText(dateFormat.format(dDureeMaxPilote));
		textAreaRemark.setText(commentaire);
		textFieldMaxLaps.setText(String.valueOf(nbMaxTours));
		
	}

	public void popUpErreur(String texte){
		JOptionPane.showMessageDialog(this, "Veillez remplir le champ : " + texte , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpErreurDate(){
		JOptionPane.showMessageDialog(this, "Il y a une erreur dans un des champs contenant une heure/date/durée => (HH:mm:ss)" , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void setFenetreNewEvent(FenetreNewEvent f) {
		fenetreEvent = f;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
}

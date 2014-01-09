package vue;

import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import model.Pilote;
import model.TrainPneu;


import controleur.ControleurPilote;
import controleur.ControleurTrainPneu;
import controleur.ControleurVoiture;

public class FenetreNewCar extends JFrame implements ActionListener, Observer{
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	// fenetre evenement, dans le cas où la creation de la voiture est annulée, cela permet de supprimer la voiture ajoutée dans la liste de la fenetre evenement 

	FenetreNewEvent fenetreEvent;

	// id de la voiture, du train de pneu et pilote courant 

	private int idVoiture;
	private int idTrain;
	private int idPilote;

	//Déclaration des différents controlleurs

	private ControleurVoiture controlV;
	private ControleurPilote controlP;
	private ControleurTrainPneu controlTP ;

	//Declaration du booleen indiquant si on modifie une voiture

	private boolean isEdit = false;

	// Déclarations des éléments de la fenêtre
	private JFrame frmNewCar;
	private JLabel labelCarNumber;
	private JLabel lblTitleCar;
	private JLabel labelColor;
	private JLabel labelImagePath;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JTextField textFieldCarNumber;
	private JTextField textFieldImagePath;

	ImageIcon topIcon = new ImageIcon(FenetreNewCar.class.getResource("/resources/topIcon.png"));
	ImageIcon bottomIcon = new ImageIcon(FenetreNewCar.class.getResource("/resources/bottomIcon.png"));
	ImageIcon addIcon = new ImageIcon(FenetreNewCar.class.getResource("/resources/addIcon.png"));	
	ImageIcon imageCar = new ImageIcon(FenetreNewCar.class.getResource("/resources/imageCar.png"));
	ImageIcon editIcon = new ImageIcon(this.getClass().getResource("/resources/editIcon.png"));
	ImageIcon delIcon = new ImageIcon(this.getClass().getResource("/resources/delIcon.png"));


	private JTextField textFieldColor;
	private JTextField textFieldLapsRelay;
	private JLabel labelLapsRelay;
	private JLabel labelImageCar;
	private JLabel labelDriversList;
	private List listDrivers;
	private JButton button;

	private JLabel labelTyresList;

	private List listTyres;

	private JButton buttonAddDriver;

	private JButton buttonAddTyre;

	private JButton buttonTopDrivers;

	private JButton buttonBottomDrivers;

	private JButton buttonEditDriver;

	private AbstractButton buttonDelDriver;

	private AbstractButton buttonEditTyre;

	private AbstractButton buttonDelTyre;


	/**
	 * Create the application.
	 */
	public FenetreNewCar(ControleurVoiture controlV, ControleurPilote controlP, ControleurTrainPneu controlTP, int idVoiture) {
		this.controlV = controlV;
		this.controlP = controlP;
		this.controlTP = controlTP;
		this.idVoiture = idVoiture;
		controlV.setVoiture(idVoiture);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewCar = new JFrame();
		frmNewCar.setResizable(false);
		frmNewCar.setSize(800, 700);
		frmNewCar.setLocation((screenSize.width - frmNewCar.getWidth()) / 2 + 50, (screenSize.height - frmNewCar.getHeight()) / 3 + 50);
		frmNewCar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmNewCar.setTitle(BUNDLE.getString("CustomizationCarString")); //$NON-NLS-1$
		SpringLayout springLayout = new SpringLayout();
		frmNewCar.getContentPane().setLayout(springLayout);

		// //TITRE////
		lblTitleCar = new JLabel(BUNDLE.getString("CustomizationCarString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, lblTitleCar, 0, SpringLayout.WEST, frmNewCar.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTitleCar, 16, SpringLayout.NORTH, frmNewCar.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTitleCar, frmNewCar.getWidth(), SpringLayout.WEST, frmNewCar.getContentPane());
		lblTitleCar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleCar.setFont(Police.getFontTitle());
		frmNewCar.getContentPane().add(lblTitleCar);

		// //NUMERO DE LA VOIURE////
		labelCarNumber = new JLabel(BUNDLE.getString("NumberCarString") + " :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, labelCarNumber, 80, SpringLayout.WEST, frmNewCar.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, labelCarNumber, 70, SpringLayout.SOUTH, lblTitleCar);
		labelCarNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewCar.getContentPane().add(labelCarNumber);

		textFieldCarNumber = new JTextField();
		textFieldCarNumber.setPreferredSize(new Dimension(50, 20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldCarNumber, 0, SpringLayout.NORTH, labelCarNumber);
		springLayout.putConstraint(SpringLayout.WEST, textFieldCarNumber, 10, SpringLayout.EAST, labelCarNumber);
		textFieldCarNumber.setText("");
		textFieldCarNumber.setHorizontalAlignment(SwingConstants.LEFT);
		frmNewCar.getContentPane().add(textFieldCarNumber);
		textFieldCarNumber.addActionListener(this);

		// //COULEUR DE LA VOITURE////
		labelColor = new JLabel(BUNDLE.getString("ColorCarString") + " :"); //$NON-NLS-1$	 //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, labelColor, 20, SpringLayout.EAST, textFieldCarNumber);
		springLayout.putConstraint(SpringLayout.NORTH, labelColor, 0, SpringLayout.NORTH, textFieldCarNumber);
		labelColor.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewCar.getContentPane().add(labelColor);

		textFieldColor = new JTextField();
		textFieldColor.setPreferredSize(new Dimension(150, 20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldColor, 0, SpringLayout.NORTH, labelColor);
		springLayout.putConstraint(SpringLayout.WEST, textFieldColor, 10,SpringLayout.EAST, labelColor);
		textFieldColor.setText("");
		textFieldColor.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewCar.getContentPane().add(textFieldColor);
		textFieldColor.addActionListener(this);

		// //CHEMIN VERS L'IMAGE////
		labelImagePath = new JLabel(BUNDLE.getString("PicturePathString")+" :"); //$NON-NLS-1$	 //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.EAST, labelImagePath, 0, SpringLayout.EAST, labelCarNumber);
		springLayout.putConstraint(SpringLayout.NORTH, labelImagePath, 10, SpringLayout.SOUTH, labelCarNumber);
		labelImagePath.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewCar.getContentPane().add(labelImagePath);

		textFieldImagePath = new JTextField();
		textFieldImagePath.setPreferredSize(new Dimension(50,20));
		springLayout.putConstraint(SpringLayout.EAST, textFieldImagePath, 0, SpringLayout.EAST, textFieldColor);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldImagePath, 0, SpringLayout.NORTH, labelImagePath);
		springLayout.putConstraint(SpringLayout.WEST, textFieldImagePath,10, SpringLayout.EAST, labelImagePath);
		textFieldImagePath.setText("");
		textFieldImagePath.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewCar.getContentPane().add(textFieldImagePath);
		textFieldImagePath.addActionListener(this);

		// //NB TOURS/RELAIS////
		labelLapsRelay = new JLabel(BUNDLE.getString("LapsRelayString")+" :"); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelLapsRelay, 10, SpringLayout.SOUTH, labelImagePath);
		springLayout.putConstraint(SpringLayout.EAST, labelLapsRelay, 0, SpringLayout.EAST, labelCarNumber);
		frmNewCar.getContentPane().add(labelLapsRelay);

		textFieldLapsRelay = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldLapsRelay, 0, SpringLayout.NORTH, labelLapsRelay);
		springLayout.putConstraint(SpringLayout.WEST, textFieldLapsRelay, 10, SpringLayout.EAST, labelLapsRelay);
		textFieldLapsRelay.setPreferredSize(new Dimension(50, 20));
		textFieldLapsRelay.setText("");
		textFieldLapsRelay.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewCar.getContentPane().add(textFieldLapsRelay);
		textFieldLapsRelay.addActionListener(this);

		// //IMAGE////		
		labelImageCar = new JLabel(""); //$NON-NLS-1$
		labelImageCar.setPreferredSize(new Dimension(150,150));
		labelImageCar.setIcon(imageCar);
		springLayout.putConstraint(SpringLayout.NORTH, labelImageCar, -50, SpringLayout.NORTH, labelCarNumber);
		springLayout.putConstraint(SpringLayout.SOUTH, labelImageCar, 50, SpringLayout.SOUTH, labelImagePath);
		springLayout.putConstraint(SpringLayout.WEST, labelImageCar, 70, SpringLayout.EAST, textFieldColor);
		frmNewCar.getContentPane().add(labelImageCar);

		// //LISTE PILOTES////
		labelDriversList = new JLabel(BUNDLE.getString("DriversString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelDriversList, 60, SpringLayout.SOUTH, textFieldLapsRelay);
		springLayout.putConstraint(SpringLayout.EAST, labelDriversList, frmNewCar.getSize().width/2-100, SpringLayout.WEST, frmNewCar.getContentPane());
		labelDriversList.setPreferredSize(new Dimension(200, 40));
		labelDriversList.setHorizontalAlignment(SwingConstants.CENTER);
		labelDriversList.setFont(Police.getFontSubtitle());		
		frmNewCar.getContentPane().add(labelDriversList);

		listDrivers = new List();
		springLayout.putConstraint(SpringLayout.NORTH, listDrivers, 5, SpringLayout.SOUTH, labelDriversList);
		springLayout.putConstraint(SpringLayout.SOUTH, listDrivers, -130, SpringLayout.SOUTH, frmNewCar.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, listDrivers, 0, SpringLayout.WEST, labelDriversList);
		springLayout.putConstraint(SpringLayout.EAST, listDrivers, 0, SpringLayout.EAST, labelDriversList);
		frmNewCar.getContentPane().add(listDrivers);
		listDrivers.setMultipleMode(false);

		buttonAddDriver = new JButton(""); //$NON-NLS-1$
		buttonAddDriver.setIcon(addIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonAddDriver, 0, SpringLayout.SOUTH, listDrivers);
		springLayout.putConstraint(SpringLayout.WEST, buttonAddDriver, 0, SpringLayout.WEST, listDrivers);
		buttonAddDriver.setOpaque(false);
		buttonAddDriver.setContentAreaFilled(false);
		buttonAddDriver.setBorder(null);
		buttonAddDriver.setFocusPainted(false);
		frmNewCar.getContentPane().add(buttonAddDriver);
		buttonAddDriver.addActionListener(this);

		buttonEditDriver = new JButton(""); //$NON-NLS-1$
		buttonEditDriver.setIcon(editIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonEditDriver, 0, SpringLayout.NORTH, buttonAddDriver);
		springLayout.putConstraint(SpringLayout.WEST, buttonEditDriver, 5, SpringLayout.EAST, buttonAddDriver);
		buttonEditDriver.setOpaque(false);
		buttonEditDriver.setContentAreaFilled(false);
		buttonEditDriver.setBorder(null);
		buttonEditDriver.setFocusPainted(false);
		frmNewCar.getContentPane().add(buttonEditDriver);
		buttonEditDriver.addActionListener(this);

		buttonDelDriver = new JButton(""); //$NON-NLS-1$
		buttonDelDriver.setIcon(delIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonDelDriver, 0, SpringLayout.NORTH, buttonEditDriver);
		springLayout.putConstraint(SpringLayout.WEST, buttonDelDriver, 5, SpringLayout.EAST, buttonEditDriver);
		buttonDelDriver.setOpaque(false);
		buttonDelDriver.setContentAreaFilled(false);
		buttonDelDriver.setBorder(null);
		buttonDelDriver.setFocusPainted(false);
		frmNewCar.getContentPane().add(buttonDelDriver);
		buttonDelDriver.addActionListener(this);


		// //BOUTONS MONTER/DESCENDRE
		buttonTopDrivers = new JButton(""); //$NON-NLS-1$
		buttonTopDrivers.setIcon(topIcon);
		buttonTopDrivers.setOpaque(false);
		buttonTopDrivers.setFocusPainted(false);
		buttonTopDrivers.setContentAreaFilled(false);
		buttonTopDrivers.setBorder(null);
		springLayout.putConstraint(SpringLayout.NORTH,buttonTopDrivers,20,SpringLayout.NORTH, listDrivers);
		springLayout.putConstraint(SpringLayout.WEST,buttonTopDrivers,8,SpringLayout.EAST, listDrivers);
		frmNewCar.getContentPane().add(buttonTopDrivers);
		buttonTopDrivers.addActionListener(this);
		
		buttonBottomDrivers = new JButton(""); //$NON-NLS-1$
		buttonBottomDrivers.setIcon(bottomIcon);
		buttonBottomDrivers.setOpaque(false);
		buttonBottomDrivers.setFocusPainted(false);
		buttonBottomDrivers.setContentAreaFilled(false);
		buttonBottomDrivers.setBorder(null);
		springLayout.putConstraint(SpringLayout.NORTH,buttonBottomDrivers,2,SpringLayout.SOUTH, buttonTopDrivers);
		springLayout.putConstraint(SpringLayout.WEST,buttonBottomDrivers,8,SpringLayout.EAST, listDrivers);
		frmNewCar.getContentPane().add(buttonBottomDrivers);
		buttonBottomDrivers.addActionListener(this);

		// //LISTE PNEUS////
		labelTyresList = new JLabel(BUNDLE.getString("TyresString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.NORTH, labelTyresList, 60, SpringLayout.SOUTH, textFieldLapsRelay);
		springLayout.putConstraint(SpringLayout.WEST, labelTyresList, frmNewCar.getSize().width/2+100, SpringLayout.WEST, frmNewCar.getContentPane());
		labelTyresList.setPreferredSize(new Dimension(200, 40));
		labelTyresList.setHorizontalAlignment(SwingConstants.CENTER);
		labelTyresList.setFont(Police.getFontSubtitle());		
		frmNewCar.getContentPane().add(labelTyresList);

		listTyres = new List();
		springLayout.putConstraint(SpringLayout.NORTH, listTyres, 5, SpringLayout.SOUTH, labelTyresList);
		springLayout.putConstraint(SpringLayout.SOUTH, listTyres, -130, SpringLayout.SOUTH, frmNewCar.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, listTyres, 0, SpringLayout.WEST, labelTyresList);
		springLayout.putConstraint(SpringLayout.EAST, listTyres, 0, SpringLayout.EAST, labelTyresList);
		frmNewCar.getContentPane().add(listTyres);
		listTyres.setMultipleMode(false);

		buttonAddTyre = new JButton(""); //$NON-NLS-1$
		buttonAddTyre.setIcon(addIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonAddTyre, 0, SpringLayout.SOUTH, listTyres);
		springLayout.putConstraint(SpringLayout.WEST, buttonAddTyre, 0, SpringLayout.WEST, listTyres);
		buttonAddTyre.setOpaque(false);
		buttonAddTyre.setContentAreaFilled(false);
		buttonAddTyre.setBorder(null);
		buttonAddTyre.setFocusPainted(false);
		frmNewCar.getContentPane().add(buttonAddTyre);
		buttonAddTyre.addActionListener(this);

		buttonEditTyre = new JButton(""); //$NON-NLS-1$
		buttonEditTyre.setIcon(editIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonEditTyre, 0, SpringLayout.NORTH, buttonAddTyre);
		springLayout.putConstraint(SpringLayout.WEST, buttonEditTyre, 5, SpringLayout.EAST, buttonAddTyre);
		buttonEditTyre.setOpaque(false);
		buttonEditTyre.setContentAreaFilled(false);
		buttonEditTyre.setBorder(null);
		buttonEditTyre.setFocusPainted(false);
		frmNewCar.getContentPane().add(buttonEditTyre);
		buttonEditTyre.addActionListener(this);


		buttonDelTyre = new JButton(""); //$NON-NLS-1$
		buttonDelTyre.setIcon(delIcon);
		springLayout.putConstraint(SpringLayout.NORTH, buttonDelTyre, 0, SpringLayout.NORTH, buttonEditTyre);
		springLayout.putConstraint(SpringLayout.WEST, buttonDelTyre, 5, SpringLayout.EAST, buttonEditTyre);
		buttonDelTyre.setOpaque(false);
		buttonDelTyre.setContentAreaFilled(false);
		buttonDelTyre.setBorder(null);
		buttonDelTyre.setFocusPainted(false);
		frmNewCar.getContentPane().add(buttonDelTyre);
		buttonDelTyre.addActionListener(this);



		// //BOUTON ENREGISTRER////
		buttonSave = new JButton(BUNDLE.getString("SaveString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonSave, -10, SpringLayout.SOUTH, frmNewCar.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonSave, frmNewCar.getWidth() / 2 - 50, SpringLayout.WEST, frmNewCar.getContentPane());
		buttonSave.setPreferredSize(new Dimension(120, 30));
		frmNewCar.getContentPane().add(buttonSave);
		buttonSave.addActionListener(this);

		// //BOUTON ANNULER////
		buttonCancel = new JButton(BUNDLE.getString("CancelString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCancel, -10, SpringLayout.SOUTH, frmNewCar.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonCancel, frmNewCar.getWidth() / 2 + 50, SpringLayout.WEST, frmNewCar.getContentPane());
		buttonCancel.setPreferredSize(new Dimension(120, 30));
		frmNewCar.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(this);


		JList list_1 = new JList();
		frmNewCar.getContentPane().add(list_1);

		frmNewCar.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == buttonSave) {
			try {
				String num = textFieldCarNumber.getText();
				String couleur = textFieldColor.getText();
				int nbTourRelai = Integer.parseInt(textFieldLapsRelay.getText());
				String lien = textFieldImagePath.getText();

				if (num.isEmpty()) {
					popUpErreur("numéro de la voiture");
				}
				else if (couleur.isEmpty()) {
					popUpErreur("couleur de la voiture");
				}
				else { 
					controlV.enregistrerVoiture(num, couleur, nbTourRelai, lien, this, isEdit);
				}
			}
			catch(Exception e){
				popUpErreurInt();
			}
		}
		else if (arg0.getSource() ==  buttonDelTyre) {
			idTrain = listTyres.getSelectedIndex();
			listTyres.remove(idTrain);
			controlV.supprimerTrainPneu(idTrain);
		}
		else if (arg0.getSource() ==  buttonEditTyre) {
			try {
				idTrain = listTyres.getSelectedIndex();
				FenetreNewTyres fenetre = new FenetreNewTyres(controlTP, idVoiture, idTrain);
				controlTP.modifierCourse(fenetre);
				controlTP.ajouterObserver(this);
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
		else if (arg0.getSource() ==  buttonAddTyre) {
			controlV.ajouterTP();
			idTrain = listTyres.getItemCount();
			listTyres.add("Train en cours de creation", idTrain);
			FenetreNewTyres fenetre = new FenetreNewTyres(controlTP, idVoiture, idTrain);
			fenetre.setFenetreCar(this);
			controlTP.ajouterObserver(this);
		}

		else if (arg0.getSource() ==  buttonDelDriver) {
			idPilote = listDrivers.getSelectedIndex();
			listDrivers.remove(idPilote);
			controlV.supprimerPilote(idPilote);
		}
		else if (arg0.getSource() ==  buttonEditDriver) {
			try {
				idPilote = listDrivers.getSelectedIndex();
				FenetreNewPilote fenetre = new FenetreNewPilote(controlP, idVoiture, idPilote);
				controlP.modifierCourse(fenetre);
				controlP.ajouterObserver(this);
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
		else if (arg0.getSource() ==  buttonAddDriver) {
			controlV.ajouterPil();
			idPilote = listDrivers.getItemCount();
			listDrivers.add("Pilote en cours de creation", idPilote);
			FenetreNewPilote fenetre = new FenetreNewPilote(controlP, idVoiture, idPilote);
			fenetre.setFenetreCar(this);
			controlP.ajouterObserver(this);
		}
		else if (arg0.getSource() == buttonCancel) {
			try {
				fenetreEvent.supprimerVoitureEnCoursCreation(idVoiture);
			}
			catch(Exception e){
			}
			frmNewCar.dispose();
		}
		if(arg0.getSource() == buttonBottomDrivers) {

			try {
				String piloteCourant = listDrivers.getSelectedItem();
				int idpiloteCourant = listDrivers.getSelectedIndex();
				if (idpiloteCourant < listDrivers.getItemCount()-1) {
					listDrivers.replaceItem(listDrivers.getItem(idpiloteCourant+1), idpiloteCourant);
					listDrivers.replaceItem(piloteCourant, idpiloteCourant+1);
				}
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
		if(arg0.getSource() == buttonTopDrivers) {

			try {
				String piloteCourant = listDrivers.getSelectedItem();
				int idpiloteCourant = listDrivers.getSelectedIndex();
				if (idpiloteCourant != 0) {
					listDrivers.replaceItem(listDrivers.getItem(idpiloteCourant-1), idpiloteCourant);
					listDrivers.replaceItem(piloteCourant, idpiloteCourant-1);
				}
			}
			catch(Exception e){
				this.popUpErreurSelection();
			}
		}
	}

	public void modifierVoiture(String num, String couleur, int nbTourRelai, String lien, java.util.List<String> listPilotes, java.util.List<String> listTrainsPneu) {
		textFieldCarNumber.setText(num);
		textFieldColor.setText(couleur);
		textFieldLapsRelay.setText(String.valueOf(nbTourRelai));
		textFieldImagePath.setText(lien);
		Iterator<String> itPil = listPilotes.iterator();
		Iterator<String> itTp = listTrainsPneu.iterator();

		while(itPil.hasNext()){
			listDrivers.add(itPil.next());
		}
		while(itTp.hasNext()){
			listTyres.add(itTp.next());
		}

	}

	public void popUpErreurSelection(){
		JOptionPane.showMessageDialog(this, "Veuillez selectionner un et un seul item", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpErreur(String texte){
		JOptionPane.showMessageDialog(this, "Veillez remplir le champ : " + texte , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpErreurInt(){
		JOptionPane.showMessageDialog(this, "Le champ nombre de tours par relai n'est pas un nombre ou pas rempli" , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void popUpErreurVoiture(){
		JOptionPane.showMessageDialog(this, "Il existe déjà une voiture avec ce numéro" , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public void fermerFenetre() {
		frmNewCar.dispose();
	}

	public void setFenetreNewEvent(FenetreNewEvent f) {
		fenetreEvent = f;
	}

	@Override
	public void update(Observable o, Object arg) {
		String[] res = ((String) arg).split("-");

		if(res[0].equals("Pilote")) {
			listDrivers.replaceItem(res[1], idPilote);
		}
		else{
			listTyres.replaceItem(res[1], idTrain);
		}		
	}

	public void setIsEdit(boolean b) {
		isEdit = true;
	}

	public void supprimerPiloteEnCoursCreation(int idVoiture) {
		listDrivers.remove(idVoiture);		
	}

	public void supprimerTrainEnCoursCreation(int idVoiture) {
		listTyres.remove(idVoiture);	
	}
}
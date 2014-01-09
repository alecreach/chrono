package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import controleur.ControleurPilote;

public class FenetreNewPilote extends JFrame implements ActionListener, Observer {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("vue.messages"); //$NON-NLS-1$

	// Récupère les dimensions de l'écran pour centrer la fenêtre
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	// fenetre de la voiture courante :
	
	FenetreNewCar fenetreCar;
	
	// id de la voiture courante et du pilote :

	int idPilote;
	int idVoiture;

	//Declaration du controleur

	ControleurPilote controlP;

	// Déclarations des éléments de la fenêtre
	private JFrame frmNewDriver;
	private JLabel labelDriverName;
	private JLabel lblTitleDriver;
	private JLabel labelHelmetColor;
	private JLabel labelPicturePath;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JTextField textFieldDriverName;
	private JTextField textFieldPicturePath;
	ImageIcon imageDriver = new ImageIcon(FenetreNewPilote.class.getResource("/resources/imageDriver.png"));
	private JTextField textFieldHelmetColor;
	private JLabel labelDriverPicture;

	/**
	 * Create the application.
	 */
	public FenetreNewPilote(ControleurPilote controlP, int idVoiture,  int idPilote) {
		this.idVoiture = idVoiture;
		this.idPilote = idPilote;
		this.controlP = controlP;
		controlP.setPilote(idVoiture, idPilote);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewDriver = new JFrame();
		frmNewDriver.setResizable(false);
		frmNewDriver.setSize(800, 550);
		frmNewDriver.setLocation((screenSize.width - frmNewDriver.getWidth()) / 2 + 50, (screenSize.height - frmNewDriver.getHeight()) / 3 + 50);
		frmNewDriver.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmNewDriver.setTitle(BUNDLE.getString("CustomizationDriverString")); //$NON-NLS-1$
		SpringLayout springLayout = new SpringLayout();
		frmNewDriver.getContentPane().setLayout(springLayout);

		// //TITRE////
		lblTitleDriver = new JLabel(BUNDLE.getString("CustomizationDriverString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.WEST, lblTitleDriver, 0, SpringLayout.WEST, frmNewDriver.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTitleDriver, 16, SpringLayout.NORTH, frmNewDriver.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTitleDriver, frmNewDriver.getWidth(), SpringLayout.WEST, frmNewDriver.getContentPane());
		lblTitleDriver.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleDriver.setFont(Police.getFontTitle());
		frmNewDriver.getContentPane().add(lblTitleDriver);

		// //NOM DU PILOTE////		
		textFieldDriverName = new JTextField();
		textFieldDriverName.setPreferredSize(new Dimension(300, 20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldDriverName, 15, SpringLayout.SOUTH, lblTitleDriver);
		springLayout.putConstraint(SpringLayout.EAST, textFieldDriverName, frmNewDriver.getWidth()/2 + textFieldDriverName.getPreferredSize().width/2, SpringLayout.WEST, frmNewDriver.getContentPane());
		textFieldDriverName.setText("");
		textFieldDriverName.setHorizontalAlignment(SwingConstants.LEFT);
		frmNewDriver.getContentPane().add(textFieldDriverName);
		textFieldDriverName.addActionListener(this);

		labelDriverName = new JLabel(BUNDLE.getString("NameString")); //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.EAST, labelDriverName, -10, SpringLayout.WEST, textFieldDriverName);
		springLayout.putConstraint(SpringLayout.NORTH, labelDriverName, 0, SpringLayout.NORTH, textFieldDriverName);
		labelDriverName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDriverName.setPreferredSize(new Dimension(120, 20));
		frmNewDriver.getContentPane().add(labelDriverName);

		// //COULEUR DU CASQUE////		
		textFieldHelmetColor = new JTextField();
		textFieldHelmetColor.setPreferredSize(new Dimension(300,20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldHelmetColor, 10, SpringLayout.SOUTH, textFieldDriverName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldHelmetColor, frmNewDriver.getWidth()/2 + textFieldHelmetColor.getPreferredSize().width/2, SpringLayout.WEST, frmNewDriver.getContentPane());
		textFieldHelmetColor.setText("");
		textFieldHelmetColor.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewDriver.getContentPane().add(textFieldHelmetColor);
		textFieldHelmetColor.addActionListener(this);

		labelHelmetColor = new JLabel(BUNDLE.getString("ColorHelmetString")+" :"); //$NON-NLS-1$	 //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.EAST, labelHelmetColor, -10, SpringLayout.WEST, textFieldHelmetColor);
		springLayout.putConstraint(SpringLayout.NORTH, labelHelmetColor, 0, SpringLayout.NORTH, textFieldHelmetColor);
		labelHelmetColor.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewDriver.getContentPane().add(labelHelmetColor);

		// //CHEMIN VERS L'IMAGE////		
		textFieldPicturePath = new JTextField();
		textFieldPicturePath.setPreferredSize(new Dimension(400,20));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPicturePath, 10, SpringLayout.SOUTH, textFieldHelmetColor);
		springLayout.putConstraint(SpringLayout.EAST, textFieldPicturePath, frmNewDriver.getWidth()/2 + textFieldPicturePath.getPreferredSize().width/2, SpringLayout.WEST, frmNewDriver.getContentPane());
		textFieldPicturePath.setText("");
		textFieldPicturePath.setHorizontalAlignment(SwingConstants.CENTER);
		frmNewDriver.getContentPane().add(textFieldPicturePath);
		textFieldPicturePath.addActionListener(this);

		labelPicturePath = new JLabel(BUNDLE.getString("PicturePathString")+" :"); //$NON-NLS-1$	 //$NON-NLS-1$
		springLayout.putConstraint(SpringLayout.EAST, labelPicturePath, -10, SpringLayout.WEST, textFieldPicturePath);
		springLayout.putConstraint(SpringLayout.NORTH, labelPicturePath, 0, SpringLayout.NORTH, textFieldPicturePath);
		labelPicturePath.setHorizontalAlignment(SwingConstants.RIGHT);
		frmNewDriver.getContentPane().add(labelPicturePath);

		// //IMAGE////		
		labelDriverPicture = new JLabel("");
		labelDriverPicture.setPreferredSize(new Dimension(190,190));
		labelDriverPicture.setIcon(imageDriver);
		springLayout.putConstraint(SpringLayout.NORTH, labelDriverPicture, 40, SpringLayout.SOUTH, textFieldPicturePath);
		springLayout.putConstraint(SpringLayout.EAST, labelDriverPicture, (frmNewDriver.getWidth()/2+labelDriverPicture.getPreferredSize().width/2), SpringLayout.WEST, frmNewDriver.getContentPane());
		frmNewDriver.getContentPane().add(labelDriverPicture);

		// //BOUTON ENREGISTRER////
		buttonSave = new JButton(BUNDLE.getString("SaveString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonSave, -10, SpringLayout.SOUTH, frmNewDriver.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonSave,  frmNewDriver.getWidth()/2-50, SpringLayout.WEST,  frmNewDriver.getContentPane());
		buttonSave.setPreferredSize(new Dimension(120, 30));
		frmNewDriver.getContentPane().add(buttonSave);
		buttonSave.addActionListener(this);

		// //BOUTON ANNULER////
		buttonCancel = new JButton(BUNDLE.getString("CancelString"));
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCancel, -10, SpringLayout.SOUTH, frmNewDriver.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonCancel, frmNewDriver.getWidth()/2+50, SpringLayout.WEST,  frmNewDriver.getContentPane());
		buttonCancel.setPreferredSize(new Dimension(120, 30));
		frmNewDriver.getContentPane().add(buttonCancel);		
		buttonCancel.addActionListener(this);


		frmNewDriver.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == buttonSave) {
			String nomPilote = textFieldDriverName.getText();
			String couleur = textFieldHelmetColor.getText();
			String lien = textFieldPicturePath.getText();

			if (nomPilote.isEmpty()) {
				popUpErreur("nom du pilote");
			}
			else if (couleur.isEmpty()) {
				popUpErreur("couleur du casque");
			}
			else { 
				controlP.enregistrerPil(nomPilote, couleur, lien);
				frmNewDriver.dispose();
			}

		}
		if (arg0.getSource() == buttonCancel) {
			try {
				fenetreCar.supprimerPiloteEnCoursCreation(idVoiture);
			}
			catch(Exception e){
			}
			frmNewDriver.dispose();
		}
	}


	public void modifierPil(String nomPilote, String couleur, String lien) {
		textFieldDriverName.setText(nomPilote);
		textFieldHelmetColor.setText(couleur);
		textFieldPicturePath.setText(lien);

	}


	public void popUpErreur(String texte){
		JOptionPane.showMessageDialog(this, "Veillez remplir le champ : " + texte , "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public void setFenetreCar(FenetreNewCar fenetreNewCar) {
		fenetreCar = fenetreNewCar;
	}
}

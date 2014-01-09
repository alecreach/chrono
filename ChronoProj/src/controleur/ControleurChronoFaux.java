package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.FenetreNewEvent;
import vue.FenetreNewRace;

public class ControleurChronoFaux  {

	private FenetreNewEvent fenetreEvent;
	private ControleurChronoFaux modele;

	public ControleurChronoFaux(FenetreNewEvent fenetreEvent, ControleurChronoFaux modele) {
		this.fenetreEvent = fenetreEvent;
		this.modele = modele;
		fenetreEvent.addEnregisterEvenementListener(new ControleurEvenement());
	}

	class ControleurEvenement implements ActionListener{ 
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getSource() == fenetreEvent.getButtonSave()) {

				String nameEvent = fenetreEvent.getTextFieldEventName();
				String nameCircuit = fenetreEvent.getTextFieldTrackName();
				String lenght = fenetreEvent.getTextFieldTrackLength();
				if (nameEvent.isEmpty()) {  
					fenetreEvent.popUpErreur("nom de l'evenement");
				}
				else if (nameCircuit.isEmpty()) {  
					fenetreEvent.popUpErreur("nom du circuit");
				}
				else if (lenght.isEmpty()) {  
					fenetreEvent.popUpErreur("longueur du circuit");
				}
				else {modele.enregistrerEve(nameEvent, nameCircuit, lenght);}
			}

			if (arg0.getSource() == fenetreEvent.getButtonAddRace()) {
				fenetreEvent.createWindowRace().addCourseListener(new ControleurCourse());
			}

			if (arg0.getSource() == fenetreEvent.getButtonEditRace()) {
				FenetreNewRace newrace = fenetreEvent.createWindowRace();
				newrace.addCourseListener(new ControleurCourse());
				modele.getEve().getCourseStandard(fenetreEvent.getRankRace());
			}

			if (arg0.getSource() == fenetreEvent.getButtonDelRace()) {

			}

			if (arg0.getSource() == fenetreEvent.getButtonAddCar()) {

			}

			if (arg0.getSource() == fenetreEvent.getButtonDelCar()) {

			}

			if (arg0.getSource() == fenetreEvent.getButtonEditCar()) {

			}

			if (arg0.getSource() == fenetreEvent.getButtonAddPracticeSession()) {

			}

			if (arg0.getSource() == fenetreEvent.getButtonDelPracticeSession()) {

			}

			if (arg0.getSource() == fenetreEvent.getButtonEditPracticeSession()) {

			}
		}
	}

	class ControleurCourse implements ActionListener{ 
		public void actionPerformed(ActionEvent arg0) {

		}
	}
}

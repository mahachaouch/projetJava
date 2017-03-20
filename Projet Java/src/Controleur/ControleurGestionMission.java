package Controleur;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Modele.CSV;
import Modele.Competence;
import Vue.VueGestionMission;

public class ControleurGestionMission implements ListSelectionListener {

	VueGestionMission VGM;

	public ControleurGestionMission(VueGestionMission f) {
		this.VGM = f;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getValueIsAdjusting())
			return;
		ListSelectionModel lsm = (ListSelectionModel) arg0.getSource();
		if (lsm.isSelectionEmpty()) {
			System.out.println("No rows selected");
		} else {
			int selectedRow = lsm.getMinSelectionIndex();
			CSV csv = new CSV();
			// System.out.println(VGM.tableview.getValueAt(VGM.tableview.getSelectedRow(),0));
			try {
				Competence[] comp = csv.getCompetences(
						Integer.parseInt(VGM.tableview.getValueAt(VGM.tableview.getSelectedRow(), 0).toString()));
				String[] s = new String[15];
				int i = 0;
				int nbComp = 0;
				for (Competence c : comp) {
					if (c != null) {
						s[i] = (c.getNomAng() + "//" + c.getNomFr());
						nbComp++;
					}
					i++;
				}
				String[] s2 = new String[nbComp];
				i = 0;
				DefaultListModel model = new DefaultListModel();
				for (String c : s) {
					if (c != null)
						model.addElement(c);
				}
				VGM.list.setModel(model);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

package Controleur;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Modele.CSV;
import Vue.VueGestionMission;

public class ControleurGestionMission implements ListSelectionListener {

	VueGestionMission VGM;
	
	public ControleurGestionMission(VueGestionMission f){
		this.VGM=f;
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getValueIsAdjusting())
            return;
        ListSelectionModel lsm = (ListSelectionModel)arg0.getSource();
        if (lsm.isSelectionEmpty()) {
            System.out.println("No rows selected");
        }
        else{
            int selectedRow = lsm.getMinSelectionIndex();
            CSV csv=new CSV();
            //System.out.println(VGM.tableview.getValueAt(VGM.tableview.getSelectedRow(),0));
            csv.getCompetences(Integer.parseInt(VGM.tableview.getValueAt(VGM.tableview.getSelectedRow(),0).toString()));
 
        }
    }
	}



package TestCSV;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

class Modele extends AbstractTableModel {
	private String[] columnNames = {};
	private ArrayList<String[]> donnee = new ArrayList<String[]>();
	private int nbChamps = 0;

	public void ajouterDonnee(ArrayList<String[]> donneeEntree) {
		for (String[] ligne : donneeEntree) {
			if (ligne.length > this.nbChamps)
				this.nbChamps = ligne.length;
		}
		this.columnNames = new String[this.nbChamps];
		for (int i = 0; i < donneeEntree.get(0).length; i++) {
			this.columnNames[i] = donneeEntree.get(0)[i];
		}
		for (int j = 0; j < this.columnNames.length; j++) {
			if (this.columnNames[j] == null) {
				this.columnNames[j] = "";
			}
		}
		donneeEntree.remove(0);
		this.donnee = donneeEntree;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return donnee.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// ATTENTION ça pique les yeux !!!
		try {
			return donnee.get(row)[col];
		} catch (ArrayIndexOutOfBoundsException e) {
			return "";
		}
	}

	public void deleteRow(int row) {
		if (row == -1)
			return;
		donnee.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow() {
		donnee.add(new String[] { "", "", "", "" });
		this.fireTableDataChanged();
	}
}
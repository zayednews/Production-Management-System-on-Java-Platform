import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchResult extends JFrame {
	
	JTable table;	
	DefaultTableModel model;
	JScrollPane scroll;
	Vector<Vector<String>> vss;
	
	public SearchResult() {
	}
	
	public SearchResult(Vector<Vector<String>> vss) {
		if(vss.isEmpty()) {
			return;
		}
		this.vss = vss;
		initComps();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initComps() {
		this.setTitle("Product List");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800, 500);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Product ID");
		columnNames.add("Pname");
		//columnNames.add("Delivery Time");
		
		model = new DefaultTableModel(vss, columnNames);
		
		table = new JTable(model);
		
		scroll = new JScrollPane(table);
		
		this.add(scroll);
	}

}

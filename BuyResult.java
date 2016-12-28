import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BuyResult extends JFrame {
	
	JTable table;	
	DefaultTableModel model;
	JScrollPane scroll;
	Vector<Vector<String>> vss;
	
	public BuyResult() {
	}
	
	public BuyResult(Vector<Vector<String>> vss) {
		if(vss.isEmpty()) {
			return;
		}
		this.vss = vss;
		initComps();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initComps() {
		this.setTitle("Order List");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800, 500);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Product ID");
		columnNames.add("Pname");
		columnNames.add("Delivery Time");
		columnNames.add("Order Time");
		columnNames.add("Order ID");
		
		model = new DefaultTableModel(vss, columnNames);
		
		table = new JTable(model);
		
		scroll = new JScrollPane(table);
		
		this.add(scroll);
	}

}

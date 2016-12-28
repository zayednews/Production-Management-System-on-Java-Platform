import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class WindowSensor extends WindowAdapter{
	public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
		System.exit(0);
	}
}
class MyFrame extends Frame{
	public String msg;
	public TextField pid,pName,dTime;
	public Label message;
	
	
	
	public MyFrame(){
		super("Product Inquiry");
		msg="Window Created";		
		
		message=new Label();
		Label pNoLabel=new Label("Product ID");
		Label pNameLabel=new Label("Product Name");
		Label dTimeLabel=new Label("Delivery Time in Weeks");
		Button readButton=new Button("Read");
		Button updateButton=new Button("Update/Add");
		Button deleteButton=new Button("Delete");
		Button viewButton=new Button("View All Product");
		Button buyButton=new Button("Buy");
		Button logButton=new Button("Login");
		
		
		pid=new TextField(30);
		pName=new TextField(30);
		dTime=new TextField(30);
	
		add(pNoLabel);
		add(pid);
		add(pNameLabel);
		add(pName);
		add(dTimeLabel);
		add(dTime);
		
		add(readButton);
		//add(updateButton);
		//add(deleteButton);
		add(viewButton);
		add(buyButton);
		add(logButton);
		add(message);
		
		ButtonSensor bs=new ButtonSensor(this);
		readButton.addActionListener(bs);
		updateButton.addActionListener(bs);
		deleteButton.addActionListener(bs);
		viewButton.addActionListener(bs);
		buyButton.addActionListener(bs);
		logButton.addActionListener(bs);
		
		setSize(280,300);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
	}
}
class Utility{
	public void readData(MyFrame frame){
		
		DataAccess db = new DataAccess();
		if(db.getData(frame.pid.getText())==null)
		{
			JOptionPane.showMessageDialog(null, "No Product Found!", "Empty", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				new SearchResult(db.getData(frame.pid.getText()));
	}
	public void logData(MyFrame frame){
		
		AdminLogin.main(new String[]{});
				frame.dispose();
	}
	public void buyData(MyFrame frame){
		
		DataAccess db = new DataAccess();
		int k;
		if(db.getData(frame.pid.getText())==null)
		{
			JOptionPane.showMessageDialog(null, "No Product Found!", "Empty", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				//int l=Integer.parseInt(frame.pid.getText());
				if(Integer.parseInt(frame.dTime.getText())>=6){
					k=Integer.parseInt(frame.dTime.getText())-2;
					
					}
				else if(Integer.parseInt(frame.dTime.getText())>1){
					k=Integer.parseInt(frame.dTime.getText())-1;
					
					}
					else 
					{k=Integer.parseInt(frame.dTime.getText());}
				
				db.addToOrderDB(frame.pName.getText(), frame.dTime.getText(), frame.pid.getText(), k,db.countDB());
			}
	}
	public void allData(MyFrame frame){
		
		DataAccess db = new DataAccess();
		if(db.getAllData()==null)
		{
			JOptionPane.showMessageDialog(null, "No Product Found!", "Empty", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				new SearchResult(db.getAllData());
	}
	public void updateData(MyFrame frame){
		String sql="update PRODUCT set PNAME='"+frame.pName.getText()+"', DTIME='"+frame.dTime.getText()+"' where PID='"+frame.pid.getText()+"'";
		//System.out.println(sql);
		DataAccess db = new DataAccess();
		if(db.getData(frame.pid.getText())==null)
		{
				db.addToDB(frame.pName.getText(), frame.dTime.getText(), frame.pid.getText());
			//db.updateDB(sql);
		}
		else
			db.updateDB(sql);
	}
	public void deleteData(MyFrame frame){
		String sql="delete from PRODUCT where PID='"+frame.pid.getText()+"'";
		System.out.println(sql);
		DataAccess db = new DataAccess();
		if(db.getData(frame.pid.getText())==null)
		{
			JOptionPane.showMessageDialog(null, "No Product Found!", "Empty", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				db.delateDB(sql);
	}
}

class ButtonSensor implements ActionListener{
	MyFrame mf;
	Utility u;
	public ButtonSensor(MyFrame f){
		mf=f;
		u=new Utility();
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag=true;
		
				
			
		String dn=mf.pid.getText();
		/*
		if(dn.length()==0){
			JOptionPane.showMessageDialog(mf,"You must provide a Product ID.");
			System.out.println("You must provide a Product ID .");
			flag=false;
		}
		*/
		String s=ae.getActionCommand();
		if(flag && s.equals("Read")){
			if(dn.length()==0){
			JOptionPane.showMessageDialog(mf,"You must provide a Product ID.");
			System.out.println("You must provide a Product ID .");
			flag=false;
			}
			u.readData(mf);
		}
		else if(flag && s.equals("Update/Add")){
			if(dn.length()==0){
			JOptionPane.showMessageDialog(mf,"You must provide a Product ID.");
			System.out.println("You must provide a Product ID .");
			flag=false;
			}
			u.updateData(mf);
		}
		else if(flag && s.equals("Delete")){
			if(dn.length()==0){
			JOptionPane.showMessageDialog(mf,"You must provide a Product ID.");
			System.out.println("You must provide a Product ID .");
			flag=false;
			}
			u.deleteData(mf);
		}
		else if(flag && s.equals("View All Product")){
			u.allData(mf);
		}
		else if(flag && s.equals("Buy")){
			
			if(dn.length()==0){
			JOptionPane.showMessageDialog(mf,"You must provide a Product ID.");
			System.out.println("You must provide a Product ID .");
			flag=false;
			}
			u.buyData(mf);
			
		}
		else if(flag && s.equals("Login")){
			u.logData(mf);
			
		}
		System.out.println("button pressed");
	}
}

public class RUD{
    public static void main(String str[]){
        MyFrame mf=new MyFrame();
        mf.addWindowListener(new WindowSensor());
        mf.setVisible(true);
    }
}
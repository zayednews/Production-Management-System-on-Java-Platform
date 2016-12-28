import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.Vector;
import java.sql.*;

public class DataAccess{
	String query;
	
    Connection conn;
    String JDBC_DRIVER;  
    String DB_URL;
    Statement stmt;
    String USER;
    String PASS;
    ResultSet rs;
    public DataAccess(){
        /*JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/std2";
        USER = "root";
        PASS = "";*/
	    
	JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        USER = "system";
        PASS = "tiger";
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void connectToDB()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			stmt = conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    public void close()throws SQLException{
        if(rs!=null)rs.close();
        if(rs!=null)stmt.close();
    }

   public void addToDB(String pName, String dTime, String pid) {
		connectToDB();
		try {
			query = "INSERT INTO PRODUCT"
					+ "(PNAME, DTIME, PID) "
					+ "VALUES"
					+ "('"+pName+"', '"+dTime+"', '"+pid+"')";
			stmt.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Product Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addToOrderDB(String pName, String dTime, String pid,int otime,int oid) {
		connectToDB();
		try {
			query = "INSERT INTO PORDER"
					+ "(PNAME, DTIME, PID,OTIME,OID) "
					+ "VALUES"
					+ "('"+pName+"', '"+dTime+"', '"+pid+"',+'"+otime+"',+'"+oid+"')";
			stmt.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Your product is ordered ! ! !", "Success", JOptionPane.INFORMATION_MESSAGE);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Vector<String>> getData(String pid) {
		Vector<Vector<String>> vss = new Vector<Vector<String>>();
		connectToDB();
		try {
			query = "SELECT * FROM PRODUCT WHERE PID = '"+pid+"'";
			rs = stmt.executeQuery(query);
			if(!rs.next()) {
				
				return null;
			}
			else {
				rs.close();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Vector<String> vc = new Vector<String>();
					for(int i=1; i<=3; i++) {
						vc.add(rs.getString(i));
					}
					vss.add(vc);
				}
			}
		} catch (Exception e) {
		}
		return vss;
	}
	public Vector<Vector<String>> buy() {
		Vector<Vector<String>> vss = new Vector<Vector<String>>();
		connectToDB();
		try {
			query = "SELECT * FROM PORDER ";
			rs = stmt.executeQuery(query);
			if(!rs.next()) {
				
				return null;
			}
			else {
				rs.close();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Vector<String> vc = new Vector<String>();
					for(int i=1; i<=5; i++) {
						vc.add(rs.getString(i));
					}
					vss.add(vc);
				}
			}
		} catch (Exception e) {
		}
		return vss;
	}
	public Vector<Vector<String>> getAllData() {
		Vector<Vector<String>> vss = new Vector<Vector<String>>();
		connectToDB();
		try {
			query = "SELECT PID,PNAME FROM PRODUCT ";
			rs = stmt.executeQuery(query);
			if(!rs.next()) {
				
				return null;
			}
			else {
				rs.close();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Vector<String> vc = new Vector<String>();
					for(int i=1; i<=2; i++) {
						vc.add(rs.getString(i));
					}
					vss.add(vc);
				}
			}
		} catch (Exception e) {
		}
		return vss;
	}
    public int updateDB(String sql){
        int numOfRowsUpdated=0;
        try{
            stmt = conn.createStatement(); 
            numOfRowsUpdated=stmt.executeUpdate(sql);
            System.out.println(numOfRowsUpdated+" row(s) updated");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return numOfRowsUpdated;
    }
    public int countDB(){
        int numOfRowsUpdated=0;
        try{
            stmt = conn.createStatement(); 
            numOfRowsUpdated=stmt.executeUpdate("SELECT * FROM PORDER");
            System.out.println(numOfRowsUpdated+" row(s) updated");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return numOfRowsUpdated;
    }
    public int delateDB(String sql){
        int numOfRowsUpdated=0;
        try{
            stmt = conn.createStatement(); 
            numOfRowsUpdated=stmt.executeUpdate(sql);
            System.out.println(numOfRowsUpdated+" row(s) delated");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return numOfRowsUpdated;
    }
}
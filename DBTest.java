//package dbtest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest{
    public static void main(String[] args)throws SQLException{
        // TODO code application logic here
        DataAccess da=new DataAccess();
        ResultSet rs=null;
        /*String q="select * from mytable where id>1";
        rs=da.getData(q);
		String nm="Murphy";
        while(rs.next()){
            int id = rs.getInt("id");
            String n = rs.getString("name");
			String l = rs.getString("loc");
			//if(nm.equals(n) && id==2)System.out.println("found");
			
			System.out.println(n+" is from "+l);
            //System.out.println("ID of "+n+" is : " + id);
        }
        da.close();*/
        
        /*String q="insert into mytable values(2,'second','raj')";
        int c=da.updateDB(q);
        da.close();*/
		/*int id=2;
        String q="delete from mytable";
        System.out.println(q);
		int c=da.updateDB(q);*/
        String q="update mytable set name='bob2' where id=5";
        int c=da.updateDB(q);
        da.close();
        //System.out.println(rs.toString());
    }
}
package cbir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO {
	Connection con = null;

	ArrayList list = null;
	
	private String role=null;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cbir", "root", "");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean insert(String name, String description, String path) {
		PreparedStatement ps = null;
		String query = "insert into ImageInfo(name,description,path) values(?,?,?)";
		try {
			getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setString(3, path);
			ps.executeUpdate();
			closeConnection();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			closeConnection();
			return false;
		}
	}

	public boolean aduser(String name, String password, String role) {
		PreparedStatement ps = null;
		String query = "insert into user_details values(?,?,?)";
		try {
			getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, role);
			ps.executeUpdate();
			closeConnection();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			closeConnection();
			return false;
		}
	}
	
	public boolean deleteUser(String name) {
		PreparedStatement ps = null;
		String query = "delete from user_details where userid='"+name+"'";
		try {
			getConnection();
			ps = con.prepareStatement(query);
			ps.executeUpdate();
			closeConnection();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			closeConnection();
			return false;
		}
	}
	public boolean preInsert(String path) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = true;
		String query = "select * from ImageInfo where Path=?";
		try {
			getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, path);
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = false;
				break;
			}

			closeConnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
			closeConnection();
		}
		return flag;
	}
	public boolean searchUser() throws SQLException {
		
		
		boolean flag = true;
		Statement ps =con.createStatement() ;
		ResultSet rs=ps.executeQuery("select * from user_details");
		try {
			getConnection();
			while (rs.next()) {
				String user=rs.getString(1);
				break;
			}

			closeConnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
			closeConnection();
		}
		return flag;
	}

	public int retrive() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList list = new ArrayList();
		String query = "select * from imageinfo";
		try {
			getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(3));
				count += 1;
			}
			setFileList(list);
			closeConnection();
		} catch (Exception e) {
			System.out.println(e);
			closeConnection();
		}
		return count;
	}

	public void setFileList(ArrayList list) {
		this.list = list;
	}

	public ArrayList getFileList() {
		return list;
	}
	
	//************************
	
	public boolean verifyUser(String userName,String password)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean flag=false;
		role=null;
		String query="select * from user_details where userid=?";
		try
		{
			getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,userName);
			rs=ps.executeQuery();
			while(rs.next())
			{
				String passwordOne=rs.getString("password");
				if(password.equals(passwordOne))
				{
					setRole(rs.getString("usertype"));
					flag=true;
					break;
				}
			}
			closeConnection();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
			//System.out.println(e);
			closeConnection();
		}
		return flag;
	}
	
	
	public void setRole(String role)
	{
		this.role=role;
	}
	public String getRole()
	{
		return role;
	}
	

}

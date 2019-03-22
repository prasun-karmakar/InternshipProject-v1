package com.signify.internshipproject.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.signify.internshipproject.DTO.UserDTO;
import com.signify.internshipproject.dao.UserDAO;
import com.signify.internshipproject.db.DBManager;

public class UserDAOImpl implements UserDAO {
	static Connection con = null;
	static PreparedStatement ps = null;

	public int insertUserRegistrationDetails(UserDTO userDTO) {
		int status = 0;
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement("insert into users_detail values(?,?,?,?,?)");
			ps.setString(1, userDTO.getUsername());
			ps.setString(2, userDTO.getPassword());
			ps.setString(3, userDTO.getEmail());
			ps.setString(4, userDTO.getMobileno());
			ps.setString(5, userDTO.getTimezone_id());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public ArrayList<UserDTO> userDetails(UserDTO userDTO)
	{   
		UserDTO userDTOResult=null;
		ArrayList<UserDTO> UserRec=new ArrayList<>();
	  	try{
			con=DBManager.getCon();
			ps=con.prepareStatement("SELECT username,email,mobileno,timezone_id FROM users_detail");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				{
				userDTOResult=new UserDTO();
					 
					 userDTOResult.setUsername(rs.getString("username"));
				     userDTOResult.setEmail(rs.getString("email"));
				     userDTOResult.setMobileno(rs.getString("mobileno"));
				     userDTOResult.setTimezone_id(rs.getString("timezone_id"));
				     UserRec.add(userDTOResult);
				     userDTOResult.setUserRec(UserRec);
			     }
			con.close();
			}
			catch(Exception e)
			{
			System.out.println(e);
			}
			return UserRec;
			}

	public Map<Integer, String> getTimezoneid_name(UserDTO userDTO) {

		Map<Integer, String> results = new HashMap<Integer, String>();
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement("SELECT timezone_id,name FROM user_time");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer column1 = rs.getInt("timezone_id");
				String column2 = rs.getString("name");
				results.put(column1, column2);
				userDTO.setResults(results);

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return results;
	}

	public UserDTO getUserDetails(UserDTO userDTO) {

		UserDTO userDTOResult=null;
		
		try {

			con = DBManager.getCon();
			ps = con.prepareStatement("SELECT username,password from users_detail where username=? and password=?");
			ps.setString(1, userDTO.getUsername());
			ps.setString(2, userDTO.getPassword());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String usernameDB = rs.getString("username").trim();
				String passwordDB = rs.getString("password").trim();
				
				userDTOResult =new UserDTO();
				userDTOResult.setUsername(usernameDB);
				userDTOResult.setPassword(passwordDB);
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return userDTOResult;

	}

	public String getUsersLastLoginTime(UserDTO userDTO) {
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement("SELECT lastlogin from users_detail where username=?");
			ps.setString(1, userDTO.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String lastlogin = rs.getString("lastlogin");
				userDTO.setZonedDateTime(lastlogin);
				return "timestamp";

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "invalid";
	}

	
	public UserDTO validatePasswordRequest(UserDTO userDTO) {
		UserDTO userDTOResult=null;
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement("SELECT username,email FROM users_detail where username=? and email=?");
			ps.setString(1,userDTO.getUsername());
			ps.setString(2,userDTO.getEmail());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String usernameDB = rs.getString("username").trim();
				String emailDB = rs.getString("email").trim();
				userDTOResult=new UserDTO();
				userDTOResult.setUsername(usernameDB);
				userDTOResult.setEmail(emailDB);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return userDTOResult;

	}

	public int updateLastLoginTime(UserDTO userDTO) {
		int status = 0;
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement("UPDATE users_detail SET lastlogin=? where username=?");
			ps.setString(1, userDTO.getLastlogin());
			ps.setString(2, userDTO.getUsername());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public String getTimezoneName(UserDTO userDTO) {
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement(
					"SELECT name FROM users_detail INNER JOIN user_time ON users_detail.timezone_id =user_time.timezone_id where username=?");
			ps.setString(1, userDTO.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				userDTO.setName(name);
				return "timezone";

			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return "invalid";
	}

	public int updatePassword(UserDTO userDTO) {
		int status = 0;
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement("UPDATE users_detail SET password=? where username=?");
			ps.setString(1, userDTO.getPassword2());
			ps.setString(2, userDTO.getUsername());
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public UserDTO validateUserDetails(UserDTO userDTO) {
		UserDTO userDTOResult=null;
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement("select username from users_detail where username=?");
			ps.setString(1, userDTO.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String usernameDB = rs.getString("username").trim();
				userDTOResult=new UserDTO();
				userDTOResult.setUsername(usernameDB);
				//if (username.equals(usernameDB1)) {
					//return true;
				//}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return userDTOResult;
	}

public int updateUserProfile(UserDTO userDTO) {
		int status = 0;
		try {
			con = DBManager.getCon();
			ps = con.prepareStatement(
					"UPDATE users_detail SET username=? password=?,email=?, mobileno=?,timezone_id=? where username=?");
			ps.setString(1, userDTO.getUsername());
			ps.setString(2, userDTO.getPassword());
			ps.setString(3, userDTO.getEmail());
			ps.setString(4, userDTO.getMobileno());
			ps.setString(5, userDTO.getTimezone_id());
			status = ps.executeUpdate();
			con.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}

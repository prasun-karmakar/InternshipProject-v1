package com.signify.internshipproject.db;
import java.sql.Connection;
import java.sql.DriverManager;

import com.signify.internshipproject.db.DBManager;

	public class DBManager{

	private static String username="postgres";
	private static String pwd="test123";
	private static String connURL="jdbc:postgresql://localhost:5432/Login";
	
	static Connection con=null;

	public static Connection getCon(){
	try{
		Class.forName("org.postgresql.Driver");
		con=DriverManager.getConnection(connURL,username,pwd);
		
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	return con;
	}
	}


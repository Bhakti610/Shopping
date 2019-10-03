package com.ltil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ltil.model.User;

import com.ltil.utilities.ConnectionManager2;

public class UserDaoImpl2 implements UserDao
{
	private Connection connection= ConnectionManager2.getConnection();
	private String query ="insert into users values(?,?)";
	private String query1 ="select * from users where username=? and password=?";
	private int i;
	private PreparedStatement ps = null;
	
	int b= 0;
	
	
	@Override
	public boolean validateUser(User user) {
		try
		{
			ps = connection.prepareStatement(query1);
			ps.setString(1, user.getUsername());
			ps.setString(2,user.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
			return true;
			}	
			
		}
		catch(SQLException sq)
		{
			sq.printStackTrace();
		}
		return false;
		
	}
	@Override
	public int insertUser(User user) 
	{
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2,user.getPassword());
			i = ps.executeUpdate();
		if(i==1)
		{
			b= 1;
		}
		else
		{
			b= 0;
		}
			
		}
		catch(SQLException sq){
			sq.printStackTrace();
		}
		return b;
	}
	
}

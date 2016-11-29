package com.svecw.obtr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.svecw.obtr.domain.CityList;

public class CityListDAO implements ICityListDAO {
	private Connection connection = null;
	public CityListDAO() {
		Context initContext;
		Context envContext;
		DataSource dataSource;
		try {
			initContext = new InitialContext();
			envContext  = (Context)initContext.lookup("java:/comp/env");
	          dataSource = (DataSource)envContext.lookup("jdbc/testdb");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<CityList> getCities() {
		// TODO Auto-generated method stub
		List<CityList> citiesList=null;
		Statement statement = null;
		ResultSet resultSet = null;
	    String query = "select * from city";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if(resultSet.next()){
				citiesList = new ArrayList<CityList>();
				resultSet.beforeFirst();
			}
			while(resultSet.next()){
				CityList cityListDomain = new CityList();
				cityListDomain.setCityId(resultSet.getInt(1));
				cityListDomain.setCityName(resultSet.getString(2));
				citiesList.add(cityListDomain);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return citiesList;
	}
	
	
}

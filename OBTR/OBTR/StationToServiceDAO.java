package com.svecw.obtr.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.svecw.obtr.domain.Service;

public class StationToServiceDAO implements IStationToService {

	private Connection connection = null;
	public StationToServiceDAO() {
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
	public int addServiceToStation(Service serviceDomain) {
		Statement statement = null;
		int status = 0;
		try {
			statement = connection.createStatement();
			status = statement.executeUpdate("insert into serviceStation values("+ serviceDomain.getServiceId() +","+ serviceDomain.getSourceStationId() + ","+ serviceDomain.getDestinationStationId() +")" );
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

}

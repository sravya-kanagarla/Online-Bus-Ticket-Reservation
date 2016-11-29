package com.svecw.obtr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.svecw.obtr.domain.Service;

public class ServiceDAO implements IServiceDAO {
	private Connection connection = null;
	public ServiceDAO() {
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
	@SuppressWarnings("resource")
	@Override
	public int addService(Service serviceDomain) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int status = 0;
		int serviceId = 0;
		try {
			preparedStatement = connection.prepareStatement("insert into service (sourceId, destinationId, noOfSeats, fare, distance, journeyDate, arrivalTime, departureTime, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, serviceDomain.getSourceId());
			preparedStatement.setInt(2, serviceDomain.getDestinationId());
			preparedStatement.setInt(3, serviceDomain.getNoOfSeats());
			preparedStatement.setInt(4, serviceDomain.getFare());
			preparedStatement.setInt(5, serviceDomain.getDistance());
			preparedStatement.setString(6, serviceDomain.getJourneyDate());
			preparedStatement.setString(7, serviceDomain.getArrivalTime());
			preparedStatement.setString(8, serviceDomain.getDepartureTime());
			preparedStatement.setString(9, serviceDomain.getStatus());
			status = preparedStatement.executeUpdate();
			if (status != 0) {
				preparedStatement = connection.prepareStatement("select serviceId from service where sourceId = ? and destinationId = ? and noOfSeats = ? and fare = ? and distance = ? and journeyDate = ? and arrivalTime = ? and departureTime = ? and status = ?");
				preparedStatement.setInt(1, serviceDomain.getSourceId());
				preparedStatement.setInt(2, serviceDomain.getDestinationId());
				preparedStatement.setInt(3, serviceDomain.getNoOfSeats());
				preparedStatement.setInt(4, serviceDomain.getFare());
				preparedStatement.setInt(5, serviceDomain.getDistance());
				preparedStatement.setString(6, serviceDomain.getJourneyDate());
				preparedStatement.setString(7, serviceDomain.getArrivalTime());
				preparedStatement.setString(8, serviceDomain.getDepartureTime());
				preparedStatement.setString(9, serviceDomain.getStatus());
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					serviceId = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try {
				if(null != preparedStatement){
					preparedStatement.close();
				}
				if(null != connection){
				connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return serviceId;
	}

}

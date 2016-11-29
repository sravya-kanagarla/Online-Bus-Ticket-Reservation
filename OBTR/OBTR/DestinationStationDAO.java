package com.svecw.obtr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.svecw.obtr.domain.DestinationStations;
import com.svecw.obtr.domain.ServiceStation;

public class DestinationStationDAO implements IDestinationStationDAO{
	private Connection connection = null;
	public DestinationStationDAO() {
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
	public List<DestinationStations> getDestinationStations(ServiceStation serviceStationDomain) {
		List<DestinationStations> destinationStationList = null;
		PreparedStatement preparedStatement= null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("select * from station where CityId = ?");
			preparedStatement.setInt(1, serviceStationDomain.getDestinationId());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				destinationStationList = new ArrayList<DestinationStations>();
				resultSet.beforeFirst();
			}
			while(resultSet.next()){
				DestinationStations destinationStationsDomain = new DestinationStations();
				destinationStationsDomain.setStationId(resultSet.getInt(1));
				destinationStationsDomain.setStationName(resultSet.getString(2));
				destinationStationList.add(destinationStationsDomain);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return destinationStationList;
	}
}

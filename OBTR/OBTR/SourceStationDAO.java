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

import com.svecw.obtr.domain.ServiceStation;
import com.svecw.obtr.domain.SourceStations;

public class SourceStationDAO implements ISourceStationDAO {
	private Connection connection = null;
	public SourceStationDAO() {
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
	public List<SourceStations> getSourceStations(ServiceStation serviceStationDomain) {
		// TODO Auto-generated method stub
		List<SourceStations> sourceStationList = null;
		PreparedStatement preparedStatement= null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("select * from station where CityId = ?");
			preparedStatement.setInt(1, serviceStationDomain.getSourceId());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				sourceStationList = new ArrayList<SourceStations>();
				resultSet.beforeFirst();
			}
			while(resultSet.next()){
				SourceStations sourceStationsDomain = new SourceStations();
				sourceStationsDomain.setStationId(resultSet.getInt(1));
				sourceStationsDomain.setStationName(resultSet.getString(2));
				sourceStationList.add(sourceStationsDomain);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sourceStationList;
	}
}

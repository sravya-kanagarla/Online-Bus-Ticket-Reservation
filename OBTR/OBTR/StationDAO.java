package com.svecw.obtr.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.svecw.obtr.domain.Station;


	public class StationDAO implements IStationDAO {

		private Connection connection = null;
		public StationDAO() {
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
		public int addStation(Station stationDomain) {
			PreparedStatement preparedStatement = null;
			int status = 0;
			try {
				preparedStatement = connection.prepareStatement("insert into station (stationName,cityId) values (?, ?)");
				preparedStatement.setString(1, stationDomain.getStationName());
				preparedStatement.setInt(2, stationDomain.getCityId());
				status = preparedStatement.executeUpdate();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// TODO Auto-generated method stub
			return status;
		}

	}



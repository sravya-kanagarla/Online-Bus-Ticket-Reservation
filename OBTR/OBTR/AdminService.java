package com.svecw.obtr.service;


import java.util.ArrayList;
import java.util.List;

import com.svecw.obtr.dao.CityDAO;
import com.svecw.obtr.dao.CityListDAO;
import com.svecw.obtr.dao.ICityDAO;
import com.svecw.obtr.dao.ICityListDAO;
import com.svecw.obtr.dao.IServiceDAO;
import com.svecw.obtr.dao.IStationDAO;
import com.svecw.obtr.dao.IStationToService;
import com.svecw.obtr.dao.ServiceDAO;
import com.svecw.obtr.dao.StationDAO;
import com.svecw.obtr.dao.StationToServiceDAO;
import com.svecw.obtr.dto.CityList;
import com.svecw.obtr.dto.City;
import com.svecw.obtr.dto.Service;
import com.svecw.obtr.dto.SourceStations;
import com.svecw.obtr.dto.DestinationStations;
import com.svecw.obtr.dto.Station;
import com.svecw.obtr.dto.ServiceStation;
import com.svecw.obtr.dao.ISourceStationDAO;
import com.svecw.obtr.dao.SourceStationDAO;
import com.svecw.obtr.dao.IDestinationStationDAO;
import com.svecw.obtr.dao.DestinationStationDAO;

public class AdminService implements IAdminService {

	ICityDAO iCityDAO = null;
	private ICityListDAO iCityListDAO =null;
	IStationDAO iStationDAO =null;
	IServiceDAO iServiceDAO = null;
	ISourceStationDAO iSourceStationDAO = null;
	IDestinationStationDAO iDestinationStationDAO = null;
	IStationToService iStationToService = null;
	
	public AdminService() {
		iCityDAO = new CityDAO();
		iCityListDAO = new CityListDAO();
		iStationDAO = new StationDAO();
		iServiceDAO = new ServiceDAO();
		iSourceStationDAO = new SourceStationDAO();
		iDestinationStationDAO = new DestinationStationDAO();
		iStationToService = new StationToServiceDAO();
	}
	
	@Override
	public int addCity(City cityDTO) {
		
		com.svecw.obtr.domain.City cityDomain = new com.svecw.obtr.domain.City();
		cityDomain.setCityName(cityDTO.getCityName());
		return iCityDAO.addCity(cityDomain);
	}

	@Override
	public List<CityList> getCities() {
		// TODO Auto-generated method stub
		List<CityList> citiesListDTO =null;
		List<com.svecw.obtr.domain.CityList> citiesListDomain = iCityListDAO.getCities();
		
		if(null!=citiesListDomain){
			citiesListDTO = new  ArrayList<CityList>();
			for(com.svecw.obtr.domain.CityList cityListDomain : citiesListDomain){
				CityList cityListDTO = new CityList();
				cityListDTO.setCityId(cityListDomain.getCityId());
				cityListDTO.setCityName(cityListDomain.getCityName());
				citiesListDTO.add(cityListDTO);
			}
		}
		
		
		return citiesListDTO;
	}

	@Override
	public int addStation(Station stationDTO) {
		// TODO Auto-generated method stub
		com.svecw.obtr.domain.Station stationDomain = new com.svecw.obtr.domain.Station();
		stationDomain.setStationName(stationDTO.getStationName());
		stationDomain.setCityId(stationDTO.getCityId());
		return iStationDAO.addStation(stationDomain);
	}

	@Override
	public int addService(Service serviceDTO) {
		// TODO Auto-generated method stub
		com.svecw.obtr.domain.Service serviceDomain = new com.svecw.obtr.domain.Service();
		serviceDomain.setSourceId(serviceDTO.getSourceId());
		serviceDomain.setDestinationId(serviceDTO.getDestinationId());
		serviceDomain.setNoOfSeats(serviceDTO.getNoOfSeats());
		serviceDomain.setFare(serviceDTO.getFare());
		serviceDomain.setDistance(serviceDTO.getDistance());
		serviceDomain.setJourneyDate(serviceDTO.getJourneyDate());
		serviceDomain.setArrivalTime(serviceDTO.getArrivalTime());
		serviceDomain.setDepartureTime(serviceDTO.getDepartureTime());
		serviceDomain.setStatus(serviceDTO.getStatus());
		return iServiceDAO.addService(serviceDomain);
	}

	@Override
	public List<SourceStations> sourceStation(ServiceStation serviceStationDTO) {
		com.svecw.obtr.domain.ServiceStation serviceDomain = new com.svecw.obtr.domain.ServiceStation();
		serviceDomain.setSourceId(serviceStationDTO.getSourceId());
		// TODO Auto-generated method stub
		List<SourceStations> sourceStationsDTO = null;
		List<com.svecw.obtr.domain.SourceStations> sourceStationsDomain = iSourceStationDAO.getSourceStations(serviceDomain);
		if (null != sourceStationsDomain) {
			sourceStationsDTO = new ArrayList<SourceStations>();
			for (com.svecw.obtr.domain.SourceStations sourceStation: sourceStationsDomain) {
				SourceStations sourceStationDTO = new SourceStations();
				sourceStationDTO.setStationId(sourceStation.getStationId());
				sourceStationDTO.setStationName(sourceStation.getStationName());
				sourceStationsDTO.add(sourceStationDTO);
			}
			
		}
		return sourceStationsDTO;
	}

	public List<DestinationStations> destinationStation(ServiceStation serviceStationDTO) {
		com.svecw.obtr.domain.ServiceStation serviceDomain = new com.svecw.obtr.domain.ServiceStation();
		serviceDomain.setDestinationId(serviceStationDTO.getDestinationId());
		List<DestinationStations> destinationStationsDTO = null;
		List<com.svecw.obtr.domain.DestinationStations> destinationStationsDomain = iDestinationStationDAO.getDestinationStations(serviceDomain);
		if (null != destinationStationsDomain) {
			destinationStationsDTO = new ArrayList<DestinationStations>();
			for (com.svecw.obtr.domain.DestinationStations destinationStation: destinationStationsDomain) {
				DestinationStations destinationStationDTO = new DestinationStations();
				destinationStationDTO.setStationId(destinationStation.getStationId());
				destinationStationDTO.setStationName(destinationStation.getStationName());
				destinationStationsDTO.add(destinationStationDTO);
			}
			
		}
		return destinationStationsDTO;
	}

	@Override
	public int addStationstoServices(Service serviceDTO) {
		// TODO Auto-generated method stub
		com.svecw.obtr.domain.Service serviceDomain = new com.svecw.obtr.domain.Service();
		serviceDomain.setSourceStationId(serviceDTO.getSourceStationId());
		serviceDomain.setDestinationStationId(serviceDTO.getDestinationStationId());
		serviceDomain.setServiceId(serviceDTO.getServiceId());
		System.out.println(serviceDTO.getServiceId());
		//System.out.println(serviceDTO.getDestinationStationId());
		return iStationToService.addServiceToStation(serviceDomain);
	}

}

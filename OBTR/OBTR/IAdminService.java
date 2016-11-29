package com.svecw.obtr.service;

import java.util.List;

import com.svecw.obtr.dto.CityList;
import com.svecw.obtr.dto.City;
import com.svecw.obtr.dto.DestinationStations;
import com.svecw.obtr.dto.Service;
import com.svecw.obtr.dto.ServiceStation;
import com.svecw.obtr.dto.SourceStations;
import com.svecw.obtr.dto.Station;

public interface IAdminService {
	public int addCity(City cityDTO);
	public List<CityList> getCities();
	public int addStation(Station stationDTO);
	public int addService(Service serviceDTO);
	//public List<SourceStations> sourceStation(Service serviceStationDTO);
	//public List<DestinationStations> destinationStation(Service serviceStationDTO);
	public int addStationstoServices(Service serviceDTO);
	public List<SourceStations> sourceStation(ServiceStation serviceStationDTO);
	public List<DestinationStations> destinationStation(ServiceStation serviceStationDTO);
}

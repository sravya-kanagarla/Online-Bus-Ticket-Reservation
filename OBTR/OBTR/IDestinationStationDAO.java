package com.svecw.obtr.dao;

import java.util.List;

import com.svecw.obtr.domain.ServiceStation;
import com.svecw.obtr.domain.DestinationStations;

public interface IDestinationStationDAO {
	public List<DestinationStations> getDestinationStations(ServiceStation serviceDomain);
}



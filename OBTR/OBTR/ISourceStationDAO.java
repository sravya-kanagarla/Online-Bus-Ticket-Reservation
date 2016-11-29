package com.svecw.obtr.dao;

import java.util.List;

import com.svecw.obtr.domain.ServiceStation;
import com.svecw.obtr.domain.SourceStations;

public interface ISourceStationDAO {
	public List<SourceStations> getSourceStations(ServiceStation serviceStationDAO);
}

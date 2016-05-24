package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.InfoPark;

public interface ParkService {
	List<InfoPark> getParks(String sql);
}

package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.InfoCarIo;

public interface CarIoService {
	public List<InfoCarIo> getCarIos(String sql);
}

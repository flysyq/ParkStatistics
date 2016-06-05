package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.InfoCarParkPay;

public interface CarParkPayService {
	List<InfoCarParkPay> getCarParkPays(String sql);
}

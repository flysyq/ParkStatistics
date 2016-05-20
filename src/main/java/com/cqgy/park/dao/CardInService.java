package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.InfoCardIn;

public interface CardInService {
	List<InfoCardIn> getCardIns(String sql);
}

package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.InfoGateOpenHand;

public interface GateOpenService {
	List<InfoGateOpenHand> getGateOpens(String sql);
}

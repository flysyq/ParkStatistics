package com.cqgy.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoPark;

public interface ParkRepository extends JpaRepository<InfoPark, Long>{
	
}

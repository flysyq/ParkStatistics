package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoCarIo;

public interface InfoCarIoRepository extends JpaRepository<InfoCarIo, Long>{
	List<InfoCarIo> findByAccTypeAndCardNoOrderByComeTime(Integer accType,String cardNo);
}

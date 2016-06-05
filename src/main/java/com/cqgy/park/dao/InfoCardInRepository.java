package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoCardIn;

public interface InfoCardInRepository extends JpaRepository<InfoCardIn,Long>{
	List<InfoCardIn> findByCardNo(String cardNo);
}

package com.cqgy.park.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoCard;

public interface InfoCardRepository extends JpaRepository<InfoCard,Long>{
	List<InfoCard> findByCardNo(String cardNo);
}

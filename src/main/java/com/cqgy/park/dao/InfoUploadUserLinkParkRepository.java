package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoUploadUserLinkPark;

public interface InfoUploadUserLinkParkRepository extends JpaRepository<InfoUploadUserLinkPark, Long> {

	List<InfoUploadUserLinkPark> findByUserCodeAndParkCode(String userCode,String parkCode);
}

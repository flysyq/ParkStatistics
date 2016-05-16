package com.cqgy.park.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoUploadUserLinkParkRepository extends JpaRepository<InfoUploadUserLinkPark, Long> {

	List<InfoUploadUserLinkPark> findByUserCodeAndParkCode(String userCode,String parkCode);
}

package com.jdfaster.jdfsample.services.lot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MesLotRepository extends JpaRepository<MesLot, String>{

	List<MesLot> findByOrderIdAndOperCodeAndPLotId(String orderId, String operCode, String packedFlag);
}

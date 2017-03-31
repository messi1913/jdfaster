package com.jdfaster.jdfsample.qm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdfaster.jdfsample.qm.model.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, String>{
	
	public Shipment readShipmentByLotNo(String lotNo);
	
	public Long countByLotNoLike(String lotNo);
	
}

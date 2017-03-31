package com.jdfaster.jdfsample.qm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.qm.model.entity.Shipment;
import com.jdfaster.jdfsample.qm.model.repository.ShipmentRepository;

@Transactional
@RestController
@RequestMapping("/qm/shipping/")
public class QmShipmentController {
	@Autowired
	private ShipmentRepository sRep;
	
	@RequestMapping(value = "/{lotNo}", method = RequestMethod.GET)
	public Shipment getLotNo(@PathVariable("lotNo") String lotNo){
		System.out.println("getMethod : "+lotNo);
		return sRep.readShipmentByLotNo(lotNo);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Shipment create(@RequestBody Shipment shipment) {
		System.out.println("createMethod : "+shipment.toString());
		return sRep.save(shipment);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Shipment update(@RequestBody Shipment shipment) {
		System.out.println("updateMethod : "+shipment.toString());
		return sRep.save(shipment);
	}

	@RequestMapping(value = "/{lotNo}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("lotNo") String lotNo) {
		System.out.println("deleteMethod : "+lotNo);
		sRep.delete(lotNo);
	}
	@RequestMapping(method = RequestMethod.GET)
	public List<Shipment> findAll(){
		return sRep.findAll();
	}
	
	
}

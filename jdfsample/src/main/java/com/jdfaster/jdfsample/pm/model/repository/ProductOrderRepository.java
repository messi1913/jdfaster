package com.jdfaster.jdfsample.pm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdfaster.jdfsample.pm.model.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, String>{
	
	public ProductOrder readProductOrderById(String id);
	
	public List<ProductOrder> findByNameLike(String name);
	
}

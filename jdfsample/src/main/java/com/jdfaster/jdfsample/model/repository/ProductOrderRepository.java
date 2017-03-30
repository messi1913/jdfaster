package com.jdfaster.jdfsample.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdfaster.jdfsample.model.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, String>{
	
	public ProductOrder readProductOrderById(String id);
	
	public List<ProductOrder> findByNameLike(String name);
	
}

package com.maint.dict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.along.entity.DbSupplier;
import com.maint.dict.mapper.IDbSupplierMapper;

@Service
public class SupplierService {
	
	@Autowired
	private IDbSupplierMapper supplierMapper;
	
	
	
	public	List<DbSupplier> listSupplier(){
		return this.supplierMapper.listSupplier();
	}

	public	int addSupplier(DbSupplier supplier) {
		return this.supplierMapper.addSupplier(supplier);
	}

	public	int updateSupplier(DbSupplier supplier) {
		return this.supplierMapper.updateSupplier(supplier);
	}

	public	int deleteSupplier(DbSupplier supplier) {
		return this.supplierMapper.deleteSupplier(supplier);
	}
	
	 /*下拉配件*/
	public List<DbSupplier> findAllcombox(int statisticsId){
		return this.supplierMapper.findAllcombox(statisticsId);
	 }
		
}

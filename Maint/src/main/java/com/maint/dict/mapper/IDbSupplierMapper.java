package com.maint.dict.mapper;

import java.util.List;

import com.along.entity.DbSupplier;

public interface IDbSupplierMapper {
	
	List<DbSupplier> listSupplier();

	int addSupplier(DbSupplier supplier);

	int updateSupplier(DbSupplier supplier);

	int deleteSupplier(DbSupplier supplier);
	
	 /*下拉配件*/
	 List<DbSupplier> findAllcombox(int statisticsId);
}

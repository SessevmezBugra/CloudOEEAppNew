package com.oee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.StockInfo;

public interface StockRepository extends JpaRepository<StockInfo, Long>{

}

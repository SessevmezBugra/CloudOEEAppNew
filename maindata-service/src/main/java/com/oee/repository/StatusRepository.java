package com.oee.repository;

import com.oee.entity.PlantInfo;
import com.oee.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {


}

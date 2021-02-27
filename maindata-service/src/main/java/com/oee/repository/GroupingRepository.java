package com.oee.repository;

import com.oee.entity.GroupingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupingRepository extends JpaRepository<GroupingEntity, Long> {

}

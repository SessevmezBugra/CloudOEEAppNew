package com.oee.repository;

import com.oee.entity.ResponsibleArea;
import com.oee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findByResponsibleAreasAreaIdIn(List<Long> areaIds);
}
